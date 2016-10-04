package com.yuralubinec.spring.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yuralubinec.spring.dao.BannerDao;
import com.yuralubinec.spring.dto.BannerDTO;
import com.yuralubinec.spring.model.Banner;

/**
 * BannerServiceImpl is the class which implements CRUD operation on {@link Banner} instance in the database.
 * The service uses Spring HibernateTransactionManager for managing transaction with the database and log4j for
 * logging.
 */

@Service
public class BannerServiceImpl implements BannerService {

    private static final Logger LOGGER = Logger.getLogger(BannerServiceImpl.class);

    @Autowired
    BannerDao dao;

    /**
     * Finds all {@link Banner} in DB
     * 
     * @return List of Banners
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public List<Banner> getAllBanners() {

        try {
            return dao.getAllBanners();
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load banners from DB", e);
            throw e;
        }
    }

    /**
     * Finds {@link Banner} in DB by banner id
     * 
     * @param id of the Banner instance stored in DB
     * @return Banner object
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public Banner getBunnerById(int id) {

        try {
            return dao.findBannerById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load banner with id" + id, e);
            throw e;
        }
    }

    /**
     * Deletes {@link Banner} from DB
     * 
     * @param id of the Banner instance stored in DB
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public void deleteBanner(int id) {

        try {
            dao.deleteBanner(id);
        } catch (Exception e) {
            LOGGER.error("Unable to delete banner with id" + id, e);
            throw e;
        }
    }

    /**
     * Updates {@link Banner} in DB
     * 
     * @param BannerDTO instance which should be updated
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public void update(BannerDTO bannerDTO) {

        Banner banner;
        int id = bannerDTO.getId();
        try {
            banner = dao.findBannerById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete banner with id" + id, e);
            throw e;
        }
        banner.setName(bannerDTO.getName());
        MultipartFile photo = bannerDTO.getPhoto();
        if (photo != null) {
            try {
                banner.setPhoto(photo.getBytes());
            } catch (IOException e) {
                LOGGER.error("unable to get photo from request parameter", e);
            }
        }
    }

    /**
     * Saves {@link Banner} to DB
     * 
     * @param BannerDTO instance which should be saved to DB
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public void save(BannerDTO bannerDTO) {

        Banner banner = new Banner();
        banner.setName(bannerDTO.getName());
        MultipartFile photo = bannerDTO.getPhoto();
        if (photo != null) {
            try {
                banner.setPhoto(photo.getBytes());
            } catch (IOException e) {
                LOGGER.error("unable to get photo from request parameter", e);
            }
        }
        try {
            dao.saveBanner(banner);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to save banner", e);
            throw e;
        }
    }
}
