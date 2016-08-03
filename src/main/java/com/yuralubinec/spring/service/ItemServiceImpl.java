package com.yuralubinec.spring.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuralubinec.spring.dao.ItemDaoImpl;
import com.yuralubinec.spring.model.Item;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = Logger.getLogger(ItemServiceImpl.class);

    @Autowired
    ItemDaoImpl dao;

    @Override
    public Item findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Item findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Item> findAll() {
        return dao.findAllItems();
    }

    @Override
    public void update(Item item) {
        Item entity = dao.findById(item.getId());
        if (entity != null) {
            entity.setName(item.getName());
            entity.setDescription(item.getDescription());
            entity.setPhoto(item.getPhoto());
            entity.setAmount(item.getAmount());
        }
    }

    @Override
    public void save(Item item) {
        dao.save(item);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

}
