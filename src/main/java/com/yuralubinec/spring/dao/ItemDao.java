package com.yuralubinec.spring.dao;

import java.util.List;
import com.yuralubinec.spring.model.Item;

/**
 * Item is the interface for CRUD operations on {@link Item} instance 
 * in the database
 *
 */

public interface ItemDao {

    Item findById(int id);

    List <Item> findItemsWithFilter(String name);

    List<Item> findAllItems();

    void save(Item item);

    void delete(int id);
}
