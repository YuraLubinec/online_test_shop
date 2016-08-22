package com.yuralubinec.spring.service;

import java.util.List;

import com.yuralubinec.spring.model.Item;

public interface ItemService {

    public Item findById(int id);

    public List<Item> findWithFilter(String name);

    public List<Item> findAll();
    
    public void update(Item item);

    public void save(Item item);

    public void delete(int id);

}
