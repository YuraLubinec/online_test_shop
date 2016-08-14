package com.yuralubinec.spring.editor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Component;

import com.yuralubinec.spring.model.Item;
import com.yuralubinec.spring.service.ItemService;

@Component
public class ItemEditor extends CustomCollectionEditor {

    @Autowired
    ItemService itemServiceImpl;

    public ItemEditor() {
        super(List.class);
    }

    @Override
    protected Item convertElement(Object element) {
        if (element != null) {
            int id = Integer.parseInt(element.toString());
            Item item = itemServiceImpl.findById(id);
            return item;
        }
        return null;
    }

}
