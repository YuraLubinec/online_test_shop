package com.yuralubinec.spring.dao;

import com.yuralubinec.spring.model.User;

/**
 * User is the interface for CRUD operations on {@link User} instance in the
 * database
 *
 */

public interface UserDao {

    User findById(int id);

    User findByLogin(String login);

    void save(User user);

    void delete(int id);
}
