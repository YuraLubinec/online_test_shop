package com.yuralubinec.spring.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yuralubinec.spring.model.Item;

/**
 * AbstractDao is abstract class with hibernate methods for CRUD operations on
 * {@link Item}, {@link User}, {@link Banner} instances in the database
 *
 */

public abstract class AbstractDao<ID extends Serializable, T> {

    private final Class<T> persClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSesssion() {

        return sessionFactory.getCurrentSession();
    }

    public T getById(ID id) {

        return (T) getSesssion().get(persClass, id);
    }

    public void persist(T entity) {

        getSesssion().persist(entity);
    }

    public void delete(T entity) {

        getSesssion().delete(entity);
    }

    protected Criteria createEntityCriteria() {

        return getSesssion().createCriteria(persClass);
    }

}
