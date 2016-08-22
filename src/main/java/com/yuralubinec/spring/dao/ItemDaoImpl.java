package com.yuralubinec.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.yuralubinec.spring.model.Item;

@Repository
public class ItemDaoImpl extends AbstractDao<Integer, Item> implements ItemDao {

    @Override
    public Item findById(int id) {
        return getById(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findItemsWithFilter(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.ilike("name","%" + name + "%"));
        crit.addOrder(Order.desc("id"));
        return (List<Item>) crit.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findAllItems() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.desc("id"));
        return (List<Item>) crit.list();
    }

    @Override
    public void save(Item item) {
        persist(item);
    }

    @Override
    public void delete(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Item item = (Item) crit.uniqueResult();
        delete(item);
    }

}
