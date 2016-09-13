package com.yuralubinec.spring.dto;

import java.io.Serializable;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

public class ItemDTO implements Serializable {

    private static final long serialVersionUID = -6448933390269977215L;

    private int id;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String description;
    
    private MultipartFile photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
