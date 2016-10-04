package com.yuralubinec.spring.service;

import com.yuralubinec.spring.model.User;

/**
 * UserService is the interface for implementing CRUD operation on {@link User} instance in the database
 */

public interface UserService {
    
    public User findById(int id);

    public User findByLogin(String login);

    public void save(User user);

    public void delete(int id);

    public void update(User user);

    public boolean isLoginUnique(String login, Integer id);

}
