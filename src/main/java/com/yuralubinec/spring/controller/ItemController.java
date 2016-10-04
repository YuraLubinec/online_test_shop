package com.yuralubinec.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yuralubinec.spring.dto.ItemFilterDTO;
import com.yuralubinec.spring.model.Banner;
import com.yuralubinec.spring.model.Item;
import com.yuralubinec.spring.model.User;
import com.yuralubinec.spring.service.BannerService;
import com.yuralubinec.spring.service.ItemService;
import com.yuralubinec.spring.service.UserService;

@Controller
public class ItemController {

    private static final Logger LOGGER = Logger.getLogger(ItemController.class);

    public static final String ITEMS = "items";
    private static final String ITEM = "item";
    private static final String BANNERS_FOR_CAROUSEL = "banners";
    private static final String ACTIVE_BANNER = "activeBannerId";
    private static final int ALOWED_BANNER_QUANTITY = 3;
    private static final int ACTIVE_BANNER_INDEX = 1;

    @Autowired
    ItemService itemServiceImpl;

    @Autowired
    UserService userServiceImpl;

    @Autowired
    BannerService bannerServiceImpl;

    @Autowired
    ApplicationContext appContext;

    /**
     * Retrieves home page.
     * 
     * @param itemFilterDTO {@link ItemFilterDTO} with filter conditions
     * @param model {@link Model} object
     * @return name of view
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllItems(@ModelAttribute ItemFilterDTO itemFilterDTO, Model model) {

        String filterName = itemFilterDTO.getItemNameFilter();
        List<Banner> banners = bannerServiceImpl.getAllBanners();
        List<Item> itemList = itemServiceImpl.findAll();
        int size = banners.size();
        if (size != 0 && size <= ALOWED_BANNER_QUANTITY) {
            model.addAttribute(ACTIVE_BANNER, banners.get(size - ACTIVE_BANNER_INDEX).getId());
            model.addAttribute(BANNERS_FOR_CAROUSEL, banners.subList(0, size - ACTIVE_BANNER_INDEX));
        } else if (size > ALOWED_BANNER_QUANTITY) {
            model.addAttribute(ACTIVE_BANNER, banners.get(size - ACTIVE_BANNER_INDEX).getId());
            model.addAttribute(BANNERS_FOR_CAROUSEL,
                    banners.subList(size - ALOWED_BANNER_QUANTITY, size - ACTIVE_BANNER_INDEX));
        }

        if (filterName != null && filterName.length() != 0) {
            model.addAttribute(ITEMS, itemServiceImpl.findWithFilter(filterName));
        } else {
            model.addAttribute(ITEMS, itemList);
        }
        return "items";
    }

    /**
     * Retrieves item info page.
     * 
     * @param id {@link Item} identifier
     * @param model {@link Model} object
     * @return name of view
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String getItemInfo(@PathVariable int id, Model model) {

        model.addAttribute(ITEM, itemServiceImpl.findById(id));
        return "itemInfo";
    }

    /**
     * Retrieves item photo.
     * 
     * @param response {@link HttpServletResponse} object
     * @param id {@link Item} identifier
     */
    @RequestMapping(value = "/item/{id}/photo", method = RequestMethod.GET)
    public void getItemPhoto(HttpServletResponse response, @PathVariable int id) {

        byte[] data = itemServiceImpl.findById(id).getPhoto();
        if (data != null) {
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            response.setContentLength(data.length);
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                FileCopyUtils.copy(data, outputStream);
            } catch (IOException e) {
            }
        }
    }

    /**
     * Handles adding items to cart. If success, returns success message, else returns fail message .
     * 
     * @param id of item which should be added
     */
    @RequestMapping(value = "/item/addToUserCart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
            "text/html; charset=UTF-8" })
    public @ResponseBody String addItemToUserCart(@RequestBody int id) {

        User user = null;
        try {
            user = userServiceImpl
                    .findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName()));
        } catch (NullPointerException e) {
            LOGGER.error("Security problem, user is not authorised", e);
            throw e;
        }

        List<Item> items = user.getUserItems();
        Item item = itemServiceImpl.findById(id);

        if (!items.contains(item)) {
            items.add(item);
            userServiceImpl.update(user);
            return appContext.getMessage("Item.succes", null, null);
        } else {
            return appContext.getMessage("Item.already.exist", null, null);
        }
    }

    /**
     * Handles removing items from cart. If success, returns 204 (NO_CONTENT) HTTP status.
     * 
     * @param id of item which should be removed
     */
    @RequestMapping(value = "/item/deleteFromUserCart", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItemFromUserCart(@RequestBody int id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            User user = userServiceImpl.findById(Integer.parseInt(authentication.getName()));
            user.getUserItems().remove(itemServiceImpl.findById(id));
            userServiceImpl.update(user);
        } catch (NullPointerException e) {
            LOGGER.error("Security problem, user is not authorised", e);
            throw e;
        }
    }

    /**
     * Retrieves banner photo.
     * 
     * @param response {@link HttpServletResponse} object
     * @param id {@link Banner} identifier
     */
    @RequestMapping(value = "/banner/{id}/photo", method = RequestMethod.GET)
    public void getBannerPhoto(HttpServletResponse response, @PathVariable int id) {

        byte[] data = bannerServiceImpl.getBunnerById(id).getPhoto();
        if (data != null) {
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            response.setContentLength(data.length);
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                FileCopyUtils.copy(data, outputStream);
            } catch (IOException e) {
            }
        }
    }
}
