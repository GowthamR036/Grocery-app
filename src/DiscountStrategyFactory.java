package com.grocery.application.models;

import java.util.*;

public class DiscountStrategyFactory{
	public static DiscountStrategy getDiscountStrategy(Float totalPrice){
		if(totalPrice == null){
			return null;
		}
		System.out.println(totalPrice);
		if(Float.compare(totalPrice, 2000.0f) > 0){
			return new PercentageDiscountStrategy(2000.00f,5);
		}else if(Float.compare(totalPrice, 1000.0f) >0){
			return new PercentageDiscountStrategy(1000.00f,2);
		}
		return null;
	}
}