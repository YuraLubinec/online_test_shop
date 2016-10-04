package com.yuralubinec.spring.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yuralubinec.spring.dao.ItemDao;
import com.yuralubinec.spring.dto.ItemDTO;
import com.yuralubinec.spring.model.Item;

/**
 * ItemServiceImpl is the class which implements CRUD operation on {@link Item}
 * instance in the database. The service uses Spring HibernateTransactionManager
 * for managing transaction with the database and log4j for logging.
 */

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = Logger.getLogger(ItemServiceImpl.class);

    @Autowired
    ItemDao dao;

    /**
     * Finds {@link Item} in DB by item id
     * 
     * @param id of the Item instance stored in DB
     * @return Item object
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public Item findById(int id) {

        try {
            return dao.findById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load item with id" + id, e);
            throw e;
        }
    }

    /**
     * Finds all {@link Item} in DB with name filter
     * 
     * @param part of the Item instance name stored in DB
     * @return List of Items
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public List<Item> findWithFilter(String name) {

        try {
            return dao.findItemsWithFilter(name);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to get item with name" + name, e);
            throw e;
        }
    }

    /**
     * Finds all {@link Item} in DB
     * 
     * @return List of Items
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public List<Item> findAll() {

        try {
            return dao.findAllItems();
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load items from DB", e);
            throw e;
        }
    }

    /**
     * Saves {@link Item} to DB
     * 
     * @param ItemDTO instance which should be saved to DB
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public void save(ItemDTO itemDTO) {

        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        MultipartFile photo = itemDTO.getPhoto();
        if (photo != null) {
            try {
                item.setPhoto(photo.getBytes());
            } catch (IOException e) {
                LOGGER.error("unable to get photo from request parameter", e);
            }

        }
        try {
            dao.save(item);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to save item", e);
            throw e;
        }
    }

    /**
     * Deletes {@link Item} from DB
     * 
     * @param id of the item which should be deleted from DB
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public void delete(int id) {

        try {
            dao.delete(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete item with id" + id, e);
            throw e;
        }
    }

    /**
     * Updates {@link Item} in DB
     * 
     * @param ItemDTO instance which should be updated
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public void update(ItemDTO item) {

        Item entity;
        int id = item.getId();
        try {
            entity = dao.findById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to get item with id" + id, e);
            throw e;
        }
        if (entity != null) {
            entity.setName(item.getName());
            entity.setDescription(item.getDescription());
            entity.setPrice(item.getPrice());
            MultipartFile photo = item.getPhoto();
            if (!photo.isEmpty()) {
                try {
                    entity.setPhoto(photo.getBytes());
                } catch (IOException e) {
                    LOGGER.error("unable to get photo from request parameter", e);
                }
            }
        }
    }
}
