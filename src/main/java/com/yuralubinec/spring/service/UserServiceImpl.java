package com.yuralubinec.spring.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuralubinec.spring.dao.UserDao;
import com.yuralubinec.spring.model.User;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao dao;

    @Transactional
    @Override
    public User findById(int id) {

        try {
            return dao.findById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to find user with id" + id, e);
            throw e;
        }
    }

    @Transactional
    @Override
    public User findByLogin(String login) {

        try {
            return dao.findByLogin(login);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to find user with login" + login, e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void save(User user) {

        try {
            dao.save(user);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to save user in DB", e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void delete(int id) {

        try {
            dao.delete(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete user" + id, e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void update(User user) {

        User entity = null;
        int id = user.getId();
        try {
            entity = dao.findById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to get user from DB, id" + id, e);
            throw e;
        }
        if (entity != null) {
            entity.setLogin(user.getLogin());
            entity.setName(user.getName());
            entity.setPassword(user.getPassword());
            entity.setSurname(user.getSurname());
            entity.setUserItems(user.getUserItems());
        }
    }

    @Transactional
    @Override
    public boolean isLoginUnique(String login, Integer id) {

        User user;
        try {
            user = findByLogin(login);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to get user info from db, login:" + login, e);
            throw e;
        }
        return (user == null || ((id != null) && (user.getId() == id)));
    }

}
