package com.yuralubinec.spring.service;

import java.util.List;

import com.yuralubinec.spring.dto.ItemDTO;
import com.yuralubinec.spring.model.Item;

/**
 * ItemService is the interface for implementing CRUD operation on {@link Item} instance in the database
 */

public interface ItemService {

    public Item findById(int id);

    public List<Item> findWithFilter(String name);

    public List<Item> findAll();
    
    public void save(ItemDTO item);

    public void delete(int id);
    
    public void update(ItemDTO item);

}
