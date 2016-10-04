package com.yuralubinec.spring.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.yuralubinec.spring.model.Banner;

/**
 * DTO class using for validating {@link Banner} instances.
 * 
 */

public class BannerDTO implements Serializable {

    private static final long serialVersionUID = -7329431844024098106L;

    private int id;
    private String name;
    private MultipartFile photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotBlank
    @Size(max = 75)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

}
