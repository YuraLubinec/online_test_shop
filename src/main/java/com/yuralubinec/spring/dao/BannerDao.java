package com.yuralubinec.spring.dao;

import java.util.List;
import com.yuralubinec.spring.model.Banner;

/**
 * BannerDao is the interface for CRUD operations on {@link Banner} instance 
 * in the database
 *
 */

public interface BannerDao {
    
    Banner findBannerById(int id);
    
    List <Banner> getAllBanners();
    
    void deleteBanner(int id);
    
    void saveBanner(Banner banner);

}
