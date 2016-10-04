package com.yuralubinec.spring.service;

import java.util.List;

import com.yuralubinec.spring.dto.BannerDTO;
import com.yuralubinec.spring.model.Banner;

/**
 * BannerService is the interface for implementing CRUD operation on {@link Banner} instance in the database
 */

public interface BannerService {
    
    List <Banner> getAllBanners();
    Banner getBunnerById(int id);
    void deleteBanner(int id);
    void update(BannerDTO bannerDTO);
    void save(BannerDTO bannerDTO);
    

}
