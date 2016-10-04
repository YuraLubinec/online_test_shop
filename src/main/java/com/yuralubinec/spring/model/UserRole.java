package com.yuralubinec.spring.model;

/**
 * Enumeration of the user role types can be used in the {@link User}
 * instance.
 * 
 */

public enum UserRole {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");
    
    String userRole;
    private UserRole(String userType){
        this.userRole = userType;
    }
    
    public String getUserRole() {
        return userRole;
        
    }
    
}
