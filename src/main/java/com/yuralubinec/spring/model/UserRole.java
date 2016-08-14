package com.yuralubinec.spring.model;

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
