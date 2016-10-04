package com.yuralubinec.spring.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.yuralubinec.spring.model.User;

/**
 * UserDaoImp is the {@link UserDao} implementation for CRUD operations on {@link User} instance 
 * in the database
 *
 */

@Repository
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
   
    /**
     * Finds {@link User} in DB by user id
     * 
     * @param id of the User instance stored in DB
     * @return User object
     * @throws DataAccessException
     */
    @Override
    public User findById(int id) {
        User user = getById(id);
        if (user != null) {
            Hibernate.initialize(user.getUserItems());
        }
        return user;
    }

    /**
     * Finds {@link User} in DB by user login
     * 
     * @param login of the User stored in DB
     * @return User object
     * @throws DataAccessException
     */
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

    /**
     * Saves {@link User} to DB 
     * 
     * @param User instance which should be saved in DB
     * @throws DataAccessException
     */
    @Override
    public void save(User user) {
        persist(user);

    }

    /**
     * Deletes {@link User} from DB 
     * 
     * @param id of the User stored in DB
     * @throws DataAccessException
     */
    @Override
    public void delete(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        User user = (User) crit.uniqueResult();
        delete(user);

    }

}
