package com.yuralubinec.spring.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yuralubinec.spring.dto.ItemFilterDTO;
import com.yuralubinec.spring.model.Item;
import com.yuralubinec.spring.service.ItemService;
import com.yuralubinec.spring.service.UserService;

@Controller
@RequestMapping(value = "/")
public class ItemController {
	
	private static final Logger LOGGER = Logger.getLogger(ItemController.class);

	public static final String ITEMS = "items";

	private static final String ITEM = "item";

	private static final String CURRENT_USER_NAME = "userName";

	private static final String TYPE_ERROR = "photo_type_error";

	@Autowired
	ItemService itemServiceIml;

	@Autowired
	UserService userServiceImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllItems(@ModelAttribute ItemFilterDTO itemFilterDTO, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			model.addAttribute(CURRENT_USER_NAME,
					userServiceImpl.findById(Integer.parseInt(authentication.getName())).getName());
		}
		if (itemFilterDTO.getItemNameFilter() != null && itemFilterDTO.getItemNameFilter().length() != 0) {
			model.addAttribute(ITEMS, itemServiceIml.findWithFilter(itemFilterDTO.getItemNameFilter()));
		} else {
			model.addAttribute(ITEMS, itemServiceIml.findAll());
		}
		return "items";
	}

	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
	public String getItemInfo(@PathVariable int id, Model model) {

		model.addAttribute(ITEM, itemServiceIml.findById(id));
		return "itemInfo";
	}

	@RequestMapping(value = "/item/newItem", method = RequestMethod.GET)
	public String getItemCreationPage(Model model) {

		model.addAttribute(ITEM, new Item());
		return "itemCreate";
	}

	@RequestMapping(value = "/item/newItem", method = RequestMethod.POST)
	public String createItem(@RequestParam (required = false) MultipartFile photo, @ModelAttribute Item item, BindingResult result, Model model){
		
		if(!photo.isEmpty() && !photo.getContentType().equals("image/jpeg")){
			model.addAttribute(ITEM, new Item());
			model.addAttribute(TYPE_ERROR, "must be jpg format");
			return "itemCreate";
		}
		
		try{
			item.setPhoto(photo.getBytes());
		} catch (IOException e) {
			LOGGER.error("unable to get photo from request parameter", e);
		}
		
		itemServiceIml.save(item);
		return "redirect:/";
	}

	@RequestMapping(value = "/item/{id}/photo", method = RequestMethod.GET)
	public void getItemPhoto(HttpServletResponse response, @PathVariable int id) {

		byte[] data = itemServiceIml.findById(id).getPhoto();
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		response.setContentLength(data.length);
		try (ServletOutputStream outputStream = response.getOutputStream()) {
			FileCopyUtils.copy(data, outputStream);
		} catch (IOException e) {
		}
	}

}
