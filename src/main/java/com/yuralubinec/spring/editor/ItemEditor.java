package com.yuralubinec.spring.editor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import com.yuralubinec.spring.model.Item;
import com.yuralubinec.spring.service.ItemServiceImpl;

public class ItemEditor extends CustomCollectionEditor {

    @Autowired
    ItemServiceImpl itemServiceImpl;

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
