package com.grocery.application.models;

import com.grocery.application.constants.ItemType;

public class ItemTax{
	private ItemType type;
	private Integer percentage;

	public ItemTax(ItemType type, Integer percentage){
		this.type = type;
		this.percentage = percentage;
	}

	public Integer getPercentage(){
		return percentage;
	}

	public String getType(){
		return type.getType();
	}
}