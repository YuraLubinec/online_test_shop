package com.yuralubinec.spring.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yuralubinec.spring.dao.BannerDao;
import com.yuralubinec.spring.dto.BannerDTO;
import com.yuralubinec.spring.model.Banner;

@Service
public class BannerServiceImpl implements BannerService {

    private static final Logger LOGGER = Logger.getLogger(BannerServiceImpl.class);

    @Autowired
    BannerDao dao;

    @Transactional
    @Override
    public List<Banner> getAllBanners() {

        return dao.getAllBanners();
    }

    @Transactional
    @Override
    public Banner getBunnerById(int id) {

        return dao.findBannerById(id);
    }

    @Transactional
    @Override
    public void deleteBanner(int id) {

        dao.deleteBanner(id);

    }

    @Transactional
    @Override
    public void update(BannerDTO bannerDTO) {

        Banner banner = dao.findBannerById(bannerDTO.getId());
        banner.setName(bannerDTO.getName());
        MultipartFile photo = bannerDTO.getPhoto();
        if (photo != null) {
            try {
                banner.setPhoto(photo.getBytes());
            } catch (IOException e) {
                LOGGER.error("unable to get photo from request parameter", e);
                e.printStackTrace();
            }
        }
    }

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
                e.printStackTrace();
            }
        }
        dao.saveBanner(banner);
    }
}
