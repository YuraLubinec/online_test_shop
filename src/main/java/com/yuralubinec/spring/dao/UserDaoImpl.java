package com.yuralubinec.spring.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.yuralubinec.spring.model.User;

@Repository
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    @Override
    public User findById(int id) {
        User user = getById(id);
        if (user != null) {
            Hibernate.initialize(user.getUserItems());
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));
        User user = (User) criteria.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUserItems());
        }
        return user;
    }

    @Override
    public void save(User user) {
        persist(user);

    }

    @Override
    public void delete(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        User user = (User) crit.uniqueResult();
        delete(user);

    }

}
