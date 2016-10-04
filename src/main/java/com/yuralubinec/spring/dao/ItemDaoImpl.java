package com.yuralubinec.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.yuralubinec.spring.model.Item;

/**
 * ItemDaoImp is the {@link ItemDao} implementation for CRUD operations on
 * {@link Item} instance in the database
 *
 */

@Repository
public class ItemDaoImpl extends AbstractDao<Integer, Item> implements ItemDao {

    /**
     * Finds {@link Item} in DB by item id
     * 
     * @param id of the Item instance stored in DB
     * @return Item object
     * @throws DataAccessException
     */
    @Override
    public Item findById(int id) {
        return getById(id);
    }

    /**
     * Finds all {@link Item} in DB which satisfy filter conditions
     * 
     * @param part of the Item instance name stored in DB
     * @return List of Items
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findItemsWithFilter(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.ilike("name", "%" + name + "%"));
        crit.addOrder(Order.desc("id"));
        return (List<Item>) crit.list();
    }

    /**
     * Finds all {@link Item} in DB
     * 
     * @return List of Items
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findAllItems() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.desc("id"));
        return (List<Item>) crit.list();
    }

    /**
     * Saves {@link Item} to DB
     * 
     * @param Item instance which should be saved to DB
     * @throws DataAccessException
     */
    @Override
    public void save(Item item) {
        persist(item);
    }

    /**
     * Deletes {@link Item} from DB
     * 
     * @param id of the item which should be deleted from DB
     * @throws DataAccessException
     */
    @Override
    public void delete(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Item item = (Item) crit.uniqueResult();
        delete(item);
    }
}
