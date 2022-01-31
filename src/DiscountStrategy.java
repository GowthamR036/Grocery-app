package com.grocery.application.models;

public interface DiscountStrategy{
	Float calculateDiscountAmount(Float totalPrice);
}