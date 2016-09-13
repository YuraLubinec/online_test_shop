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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
import com.yuralubinec.spring.model.Item;
import com.yuralubinec.spring.model.User;
import com.yuralubinec.spring.service.ItemService;
import com.yuralubinec.spring.service.UserService;
import com.yuralubinec.spring.validator.ItemDTOValidator;

@Controller
@RequestMapping(value = "/")
public class ItemController {

    private static final Logger LOGGER = Logger.getLogger(ItemController.class);

    public static final String ITEMS = "items";

    private static final String ITEM = "item";

    private static final String CURRENT_USER_NAME = "userName";

    @Autowired
    ItemService itemServiceImpl;

    @Autowired
    UserService userServiceImpl;

    @Autowired
    ItemDTOValidator itemValidator;

    @Autowired
    ApplicationContext appContext;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllItems(@ModelAttribute ItemFilterDTO itemFilterDTO, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute(CURRENT_USER_NAME,
                    userServiceImpl.findById(Integer.parseInt(authentication.getName())).getName());
        }

        String filterName = itemFilterDTO.getItemNameFilter();
        if (filterName != null && filterName.length() != 0) {
            model.addAttribute(ITEMS, itemServiceImpl.findWithFilter(filterName));
        } else {
            model.addAttribute(ITEMS, itemServiceImpl.findAll());
        }
        return "items";
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String getItemInfo(@PathVariable int id, Model model) {

        model.addAttribute(ITEM, itemServiceImpl.findById(id));
        return "itemInfo";
    }

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

    @RequestMapping(value = "/item/addToUserCart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
            "text/html; charset=UTF-8" })
    public @ResponseBody String addItemToCart(@RequestBody int id) {

        User user = null;

        try {
            user = userServiceImpl
                    .findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName()));
        } catch (NullPointerException e) {
            LOGGER.error("User is not authorised", e);
            throw e;
        }

        List<Item> items = user.getUserItems();
        Item item = itemServiceImpl.findById(id);

        if (!items.contains(item)) {
            items.add(item);
            userServiceImpl.update(user);
            return appContext.getMessage("Item.secces", null, null);
        } else {
            return appContext.getMessage("Item.already.exist", null, null);
        }
    }

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

}
