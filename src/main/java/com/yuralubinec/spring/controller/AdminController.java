package com.yuralubinec.spring.controller;

import org.apache.log4j.Logger;
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
import com.yuralubinec.spring.model.Item;
import com.yuralubinec.spring.service.BannerService;
import com.yuralubinec.spring.service.ItemService;
import com.yuralubinec.spring.validator.BannerDTOValidator;
import com.yuralubinec.spring.validator.ItemDTOValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = Logger.getLogger(AdminController.class);

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

    @RequestMapping(value = "/item/{id}", method = RequestMethod.POST)
    public String updateItem(@PathVariable int id, @Validated @ModelAttribute ItemDTO itemDTO, BindingResult result,
            Model model) {

        Item item = itemServiceImpl.findById(id);
        if (result.hasErrors()) {
            return "itemCreateUpdate";
        }
        itemServiceImpl.update(itemDTO);
        return "redirect:/";
    }

    @RequestMapping(value = "/item/newItem", method = RequestMethod.GET)
    public String getItemCreationPage(Model model) {

        model.addAttribute(ITEM, new ItemDTO());

        return "itemCreateUpdate";
    }

    @RequestMapping(value = "/item/newItem", method = RequestMethod.POST)
    public String createItem(@Validated @ModelAttribute ItemDTO itemDTO, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "itemCreateUpdate";
        }

        itemServiceImpl.save(itemDTO);
        return "redirect:/";
    }

    @RequestMapping(value = "/item/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@RequestBody int id) {

        itemServiceImpl.delete(id);
    }

    @RequestMapping(value = "/banners", method = RequestMethod.GET)
    public String getBannersPage(Model model) {
 
        model.addAttribute(BANNER_LIST, bannerServiceImpl.getAllBanners());
        return "banners";
    }
    
    @RequestMapping(value = "/banner/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBanner(@RequestBody int id) {

        bannerServiceImpl.deleteBanner(id);
    }
    
    @RequestMapping(value = "/banners/banner/newBanner", method = RequestMethod.GET)
    public String getBannerCreationPage(Model model) {
 
        model.addAttribute(BANNER_DTO, new BannerDTO());
        return "bannerCreate";
    }

    @RequestMapping(value = "/banners/banner/newBanner", method = RequestMethod.POST)
    public String saveBanner(@Validated @ModelAttribute BannerDTO bannerDTO, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "bannerCreate";
        }
        bannerServiceImpl.save(bannerDTO);
        return "redirect:/admin/banners";
    }
}
