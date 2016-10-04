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

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = Logger.getLogger(ItemServiceImpl.class);

    @Autowired
    ItemDao dao;

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

    @Transactional
    @Override
    public void update(ItemDTO item) {

        Item entity = null;
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
