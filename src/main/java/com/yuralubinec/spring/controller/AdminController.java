package com.yuralubinec.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yuralubinec.spring.dto.ItemDTO;
import com.yuralubinec.spring.model.Item;
import com.yuralubinec.spring.service.ItemService;
import com.yuralubinec.spring.validator.ItemDTOValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = Logger.getLogger(AdminController.class);

    private static final String ITEM = "itemDTO";

    @Autowired
    ItemService itemServiceImpl;

    @Autowired
    ItemDTOValidator itemValidator;
    
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String getItemPage(@PathVariable int id, Model model) {

        model.addAttribute(ITEM, itemServiceImpl.findById(id));
        return "itemCreateUpdate";

    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.POST)
    public String updateItem(@PathVariable int id, @Validated @ModelAttribute ItemDTO itemDTO,
            BindingResult result, Model model) {

//      need to fix problem with validation
        itemValidator.validate(itemDTO, result);

        Item item = itemServiceImpl.findById(id);

        if (result.hasErrors()) {
            itemDTO.setName(item.getName());
            itemDTO.setDescription(item.getDescription());
            model.addAttribute(ITEM, itemDTO);
            return "itemCreateUpdate";
        }

        itemServiceImpl.update(itemDTO);
        return "redirect:/";
    }

    @RequestMapping(value = "/item/newItem", method = RequestMethod.GET)
    public String getItemCreationPage(Model model) {

        model.addAttribute(ITEM, new Item());

        return "itemCreateUpdate";
    }

    @RequestMapping(value = "/item/newItem", method = RequestMethod.POST)
    public String createItem(@Validated @ModelAttribute ItemDTO itemDTO, BindingResult result, Model model) {

//        need to fix problem with validation
        itemValidator.validate(itemDTO, result);

        if (result.hasErrors()) {
            model.addAttribute(ITEM, itemDTO);
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

}
