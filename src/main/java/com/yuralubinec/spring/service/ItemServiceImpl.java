package com.yuralubinec.spring.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        return dao.findById(id);
    }

    @Transactional
    @Override
    public List<Item> findWithFilter(String name) {
        return dao.findItemsWithFilter(name);
    }

    @Transactional
    @Override
    public List<Item> findAll() {
        return dao.findAllItems();
    }

    @Transactional
    @Override
    public void update(Item item) {
        Item entity = dao.findById(item.getId());
        if (entity != null) {
            entity.setName(item.getName());
            entity.setDescription(item.getDescription());
            entity.setPhoto(item.getPhoto());
        }
    }

    @Transactional
    @Override
    public void save(Item item) {
        dao.save(item);
    }

    @Transactional
    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Transactional
    @Override
    public void updateMultipart(ItemDTO item) {
        Item entity = dao.findById(item.getId());

        if (entity != null) {
            entity.setName(item.getName());
            entity.setDescription(item.getDescription());
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
