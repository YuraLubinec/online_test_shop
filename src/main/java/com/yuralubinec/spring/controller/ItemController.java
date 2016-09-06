package com.yuralubinec.spring.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.yuralubinec.spring.dto.ItemDTO;
import com.yuralubinec.spring.dto.ItemFilterDTO;
import com.yuralubinec.spring.model.Item;
import com.yuralubinec.spring.model.User;
import com.yuralubinec.spring.service.ItemService;
import com.yuralubinec.spring.service.UserService;

@Controller
@RequestMapping(value = "/")
public class ItemController {

    private static final Logger LOGGER = Logger.getLogger(ItemController.class);

    public static final String ITEMS = "items";

    private static final String ITEM = "itemDTO";

    private static final String CURRENT_USER_NAME = "userName";

    private static final String TYPE_ERROR = "photo_type_error";

    @Autowired
    ItemService itemServiceImpl;

    @Autowired
    UserService userServiceImpl;

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

    @RequestMapping(value = "/item/{id}", method = RequestMethod.POST)
    public String updateItem(@PathVariable int id, @RequestParam(required = false) MultipartFile photo,
            @Validated @ModelAttribute ItemDTO itemDTO, BindingResult result, Model model) {

        Item item = itemServiceImpl.findById(id);

        if (result.hasErrors()) {
            itemDTO.setId(id);
            itemDTO.setName(item.getName());
            itemDTO.setDescription(item.getDescription());
            model.addAttribute(ITEM, itemDTO);
            return "itemInfo";
        }

        if (!photo.isEmpty() && !photo.getContentType().equals("image/jpeg")) {
            model.addAttribute(ITEM, item);
            model.addAttribute(TYPE_ERROR, "must be jpg format");
            return "itemInfo";
        }

        if (!photo.isEmpty()) {
            try {
                item.setPhoto(photo.getBytes());
            } catch (IOException e) {
                LOGGER.error("unable to get photo from request parameter", e);
            }
        }

        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        itemServiceImpl.update(item);
        return "redirect:/item/" + String.valueOf(id);
    }

    @RequestMapping(value = "/item/newItem", method = RequestMethod.GET)
    public String getItemCreationPage(Model model) {

        model.addAttribute(ITEM, new Item());
        return "itemCreate";
    }

    @RequestMapping(value = "/item/newItem", method = RequestMethod.POST)
    public String createItem(@RequestParam(required = false) MultipartFile photo,
            @Validated @ModelAttribute ItemDTO itemDTO, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute(ITEM, itemDTO);
            return "itemCreate";
        }

        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());

        if (!photo.isEmpty() && !photo.getContentType().equals("image/jpeg")) {
            model.addAttribute(ITEM, itemDTO);
            model.addAttribute(TYPE_ERROR, "must be jpg format");
            return "itemCreate";
        }

        try {
            item.setPhoto(photo.getBytes());
        } catch (IOException e) {
            LOGGER.error("unable to get photo from request parameter", e);
        }
        itemServiceImpl.save(item);
        return "redirect:/";
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

    @RequestMapping(value = "/item/addToUserCart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addItemToCart(@RequestBody int id) {

        // need to add validation for unique value
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            User user = userServiceImpl.findById(Integer.parseInt(authentication.getName()));
            user.getUserItems().add(itemServiceImpl.findById(id));
            userServiceImpl.update(user);
        } catch (NullPointerException e) {
            LOGGER.error("User is not authorised", e);
            throw e;
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

    @RequestMapping(value = "/item/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@RequestBody int id) {

        itemServiceImpl.delete(id);
    }
}
