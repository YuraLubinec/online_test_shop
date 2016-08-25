package com.yuralubinec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuralubinec.spring.dto.ItemFilterDTO;
import com.yuralubinec.spring.service.ItemService;
import com.yuralubinec.spring.service.UserService;
import com.yuralubinec.spring.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/")
public class ItemController {

	public static final String ITEMS = "items";

	private static final String ITEM = "item";

	private static final String CURRENT_USER_NAME = "userName";

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
}
