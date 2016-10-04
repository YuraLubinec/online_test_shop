package com.yuralubinec.spring.dto;

import java.io.Serializable;

import com.yuralubinec.spring.model.Item;

/**
 * DTO class using for filtering {@link Item} instances.
 * 
 */

public class ItemFilterDTO implements Serializable {
	
	private static final long serialVersionUID = 5353939012699990547L;
	
	private String itemNameFilter;

	public String getItemNameFilter() {
		return itemNameFilter;
	}

	public void setItemNameFilter(String itemNameFilter) {
		this.itemNameFilter = itemNameFilter;
	}
}
