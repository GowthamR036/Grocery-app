package com.grocery.application.constants;

public enum ItemType{
	EATABLES("Eatables"),
	DAILYUSABLE("Daily Usable"),
	COSMETIC("Cosmetic");

	private String type;

	private ItemType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}
}