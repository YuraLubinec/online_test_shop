package com.yuralubinec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuralubinec.spring.service.ItemService;

@Controller
@RequestMapping(value = "/home")
public class ItemController {

    public static final String ITEMS = "items";

    private static final String ITEM = "item";

    @Autowired
    ItemService itemServiceIml;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllItems(Model model) {
        model.addAttribute(ITEMS, itemServiceIml.findAll());

        return "items";
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String getItemInfo(@PathVariable int id, Model model) {
        model.addAttribute(ITEM, itemServiceIml.findById(id));

        return "itemInfo";

    }
}
