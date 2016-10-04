package com.yuralubinec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yuralubinec.spring.dto.BannerDTO;
import com.yuralubinec.spring.dto.ItemDTO;
import com.yuralubinec.spring.model.Banner;
import com.yuralubinec.spring.model.Item;
import com.yuralubinec.spring.service.BannerService;
import com.yuralubinec.spring.service.ItemService;
import com.yuralubinec.spring.validator.BannerDTOValidator;
import com.yuralubinec.spring.validator.ItemDTOValidator;

/**
 * Handles and retrieves pages which are using by administrator depending on the URI template. A user must be log-in as ADMIN first he
 * can access those pages.
 * 
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String ITEM = "itemDTO";

    private static final String BANNER_LIST = "banners";

    private static final String BANNER_DTO = "bannerDTO";

    @Autowired
    ItemService itemServiceImpl;

    @Autowired
    BannerService bannerServiceImpl;

    @Autowired
    ItemDTOValidator itemValidator;

    @Autowired
    BannerDTOValidator bannerValidator;

    @InitBinder(BANNER_DTO)
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(bannerValidator);
    }

    @InitBinder(ITEM)
    public void initItemBinder(WebDataBinder binder) {
        binder.addValidators(itemValidator);
    }

    /**
     * Retrieves item edit page
     * 
     * @param id identifier of editing {@link Item} instance
     * @param model container for {@link ItemDTO} instance
     * @return name of view
     */ 
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String getItemPage(@PathVariable int id, Model model) {

        Item item = itemServiceImpl.findById(id);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        model.addAttribute(ITEM, itemDTO);
        return "itemCreateUpdate";
    }

    /**
     * Handles item updating.
     * 
     * @param id identifier of item should be updated
     * @param itemDTO {@link ItemDTO} instance should be updated
     * @param result {@link BindingResult} instance
     * @return if success, redirects to start page;
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.POST)
    public String updateItem(@PathVariable int id, @Validated @ModelAttribute ItemDTO itemDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "itemCreateUpdate";
        }
        itemServiceImpl.update(itemDTO);
        return "redirect:/";
    }

    /**
     * Retrieves page for creating new items with empty item instance.
     * 
     * @param model container for {@link Item} instance
     * @return 'itemCreateUpdate' view with itemDTO instance
     */
    @RequestMapping(value = "/item/newItem", method = RequestMethod.GET)
    public String getItemCreationPage(Model model) {

        model.addAttribute(ITEM, new ItemDTO());
        return "itemCreateUpdate";
    }

    /**
     * Handles creating new item.
     * 
     * @param itemDTO instance should be saved
     * @param result {@link BindingResult} validation handle object
     * @return if success, redirects to start page
     */
    @RequestMapping(value = "/item/newItem", method = RequestMethod.POST)
    public String createItem(@Validated @ModelAttribute ItemDTO itemDTO, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "itemCreateUpdate";
        }
        itemServiceImpl.save(itemDTO);
        return "redirect:/";
    }

    /**
     * Handles deleting items. If success, returns 204 (NO_CONTENT) HTTP status.
     * 
     * @param id of item which should be deleted
     */
    @RequestMapping(value = "/item/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@RequestBody int id) {

        itemServiceImpl.delete(id);
    }

    /**
     * Retrieves page with all existing banners.
     * 
     * @param model container for {@link Banner} instances
     * @return 'banners' view and founded banners
     */
    @RequestMapping(value = "/banners", method = RequestMethod.GET)
    public String getBannersPage(Model model) {
 
        model.addAttribute(BANNER_LIST, bannerServiceImpl.getAllBanners());
        return "banners";
    }
    
    /**
     * Handles deleting banners. If success, returns 204 (NO_CONTENT) HTTP status.
     * 
     * @param id of banner which should be deleted
     */
    @RequestMapping(value = "/banner/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBanner(@RequestBody int id) {

        bannerServiceImpl.deleteBanner(id);
    }
    
    /**
     * Retrieves page for creating new banners with empty banner instance.
     * 
     * @param model container for {@link Banner} instance
     * @return 'bannerCreate' view with bannerDTO instance
     */
    @RequestMapping(value = "/banners/banner/newBanner", method = RequestMethod.GET)
    public String getBannerCreationPage(Model model) {
 
        model.addAttribute(BANNER_DTO, new BannerDTO());
        return "bannerCreate";
    }

    /**
     * Handles creating new banner.
     * 
     * @param bannerDTO instance should be saved
     * @param result {@link BindingResult} validation handle object
     * @return if success, redirects to banners page
     */
    @RequestMapping(value = "/banners/banner/newBanner", method = RequestMethod.POST)
    public String saveBanner(@Validated @ModelAttribute BannerDTO bannerDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "bannerCreate";
        }
        bannerServiceImpl.save(bannerDTO);
        return "redirect:/admin/banners";
    }
}
