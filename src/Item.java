package com.grocery.application.models;

public class Item {
		private Long itemCode;
		private String name;
		private ItemTax itemTax;
		private Float price;

		public Item(Long itemCode, String name, ItemTax itemTax, Float price){
			this.itemCode = itemCode;
			this.name = name;
			this.itemTax = itemTax;
			this.price = price;
		}

		public String getItemName(){
			return name;
		}

		public Float calculateTotalPrice(Integer quantity){
			return quantity * price;
		}

		public Float calculateTaxAmount(Integer quantity){
			return (quantity * price) * itemTax.getPercentage()/100;
		}

}