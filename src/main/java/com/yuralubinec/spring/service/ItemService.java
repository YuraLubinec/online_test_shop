package com.yuralubinec.spring.service;

import java.util.List;

import com.yuralubinec.spring.dto.ItemDTO;
import com.yuralubinec.spring.model.Item;

public interface ItemService {

    public Item findById(int id);

    public List<Item> findWithFilter(String name);

    public List<Item> findAll();
    
    public void save(ItemDTO item);

    public void delete(int id);
    
    public void update(ItemDTO item);

}
