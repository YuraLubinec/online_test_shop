package com.yuralubinec.spring.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        return dao.findById(id);
    }

    @Transactional
    @Override
    public User findByLogin(String login) {
        return dao.findByLogin(login);
    }

    @Transactional
    @Override
    public void save(User user) {
        dao.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        User entity = dao.findById(user.getId());
        if (entity != null) {
            entity.setLogin(user.getLogin());
            entity.setName(user.getName());
            entity.setPassword(user.getPassword());
            entity.setSurname(user.getSurname());
            entity.setUserItems(user.getUserItems());
        }
    }

    public boolean isLoginUnique(String login, Integer id) {
        User user = findByLogin(login);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

}
