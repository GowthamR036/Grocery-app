package com.grocery.application.models;

public class PercentageDiscountStrategy implements DiscountStrategy{
	private Float minimumPurchasedPrice;
	private Integer percentage;

	public PercentageDiscountStrategy(Float minimumPurchasedPrice, Integer percentage){
		this.minimumPurchasedPrice = minimumPurchasedPrice;
		this.percentage = percentage;
	}

	public Float calculateDiscountAmount(Float totalPrice){
		if(Float.compare(totalPrice, minimumPurchasedPrice) > 0 ){
			return totalPrice * ((float)percentage/100);
		}
		return totalPrice;
	}
}