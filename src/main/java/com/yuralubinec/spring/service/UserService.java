package com.yuralubinec.spring.service;

import com.yuralubinec.spring.model.User;

public interface UserService {
    
    public User findById(int id);

    public User findByLogin(String login);

    public void save(User user);

    public void delete(int id);

    public void update(User user);

}
