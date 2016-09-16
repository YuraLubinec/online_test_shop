package com.yuralubinec.spring.dao;

import java.util.List;

import com.yuralubinec.spring.model.Banner;

public interface BannerDao {
    
    Banner findBannerById(int id);
    
    List <Banner> getAllBanners();
    
    void deleteBanner(int id);
    
    void saveBanner(Banner banner);

}
