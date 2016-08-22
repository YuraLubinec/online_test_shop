package com.yuralubinec.spring.dao;

import java.util.List;

import com.yuralubinec.spring.model.Item;

public interface ItemDao {

    Item findById(int id);

    List <Item> findItemsWithFilter(String name);

    List<Item> findAllItems();

    void save(Item item);

    void delete(int id);
}
