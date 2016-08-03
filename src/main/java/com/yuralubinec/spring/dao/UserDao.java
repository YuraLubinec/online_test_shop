package com.yuralubinec.spring.dao;

import com.yuralubinec.spring.model.User;

public interface UserDao {
    
    User findById (int id);
    
    User findByLogin (String login);
    
    void save(User user);
    
    void delete(int id);

}
