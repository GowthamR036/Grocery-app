package com.grocery.application;

import com.grocery.application.constants.ItemType;
import com.grocery.application.models.*;
import java.util.*;

public class GroceryApplication{
	private static HashMap<Integer, Item> codeVsItemMap = new HashMap<Integer, Item>();
	private static Float totalPrice = 0.0f;
	private static Float totalPriceAfterDiscount;
	private static Float discountPrice;

	public static HashMap<String, Float> calculateTotalItemPrice(HashMap<Item,Integer> purchasedItems){
		HashMap<String, Float> itemVsPrice = new HashMap<String,Float>();
		for(Map.Entry<Item,Integer> map : purchasedItems.entrySet()){
			Item item = map.getKey();
			Integer quantity = map.getValue();
			String name = item.getItemName();
			Float itemTotalPrice = item.calculateTotalPrice(quantity) + item.calculateTaxAmount(quantity);
			totalPrice += itemTotalPrice;
			itemVsPrice.put(name, itemTotalPrice);
		}
		DiscountStrategy discount = DiscountStrategyFactory.getDiscountStrategy(totalPrice);
		discountPrice = discount != null?discount.calculateDiscountAmount(totalPrice):0;
		System.out.println("discountPrice :	" +discountPrice);
		totalPriceAfterDiscount = totalPrice - discountPrice;

		return itemVsPrice;
	}

	public static void printBill(HashMap<String, Float> itemVsPrice,String customerName){
		System.out.println("Following is the invoice that is generated based on the above items that Customer has bourght"+ "\n");
		System.out.println("Customer : "+ customerName + "\n");
		
		System.out.println("-----------------------------------------------------------------------------");
       	System.out.printf("%10s %20s", "Item", "Amount");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");

		for(Map.Entry<String,Float> map: itemVsPrice.entrySet()){
			String name = map.getKey();
			Float price = map.getValue();
			System.out.format("%10s %20.2f",name , price);
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("TotalAmount %20.2f",totalPriceAfterDiscount);
        System.out.println();
        if(discountPrice > 0){
        	System.out.printf("You saved %20.2f - %.2f" +" = %.2f",totalPrice,totalPriceAfterDiscount,(totalPrice - totalPriceAfterDiscount));
        	System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
	}

	static {

		ItemTax eatableTax = new ItemTax(ItemType.EATABLES, 5);
		ItemTax dailyUsableTax = new ItemTax(ItemType.DAILYUSABLE, 8);
		ItemTax cosmeticTax = new ItemTax(ItemType.COSMETIC, 15);

		codeVsItemMap.put(1, new Item(00001L, "Coconut Oil", eatableTax, 200.00f));
		codeVsItemMap.put(2, new Item(00002L, "Rice", eatableTax, 60.00f));
		codeVsItemMap.put(3, new Item(00003L, "Dhal", eatableTax, 120.50f));
		codeVsItemMap.put(4, new Item(00004L, "Tooth Paste", dailyUsableTax, 45.25f));
		codeVsItemMap.put(5, new Item(00005L, "MakeUp Kit", cosmeticTax, 300.00f));
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		HashMap<Item, Integer> purchasedItems = new HashMap<Item,Integer>();
		System.out.println("Grocery Application");
		System.out.println("Start Billing \n");

		System.out.println("Enter Customer Name");

		String customerName = sc.nextLine();
		System.out.println("Select Item Needed");
		System.out.println("1 -> Coconut Oil");
		System.out.println("2 -> Rice");
		System.out.println("3 -> Dhal");
		System.out.println("4 -> Tooth Paste");
		System.out.println("5 -> MakeUp Kit");
		Boolean isContinue = true;

		while(isContinue){
			System.out.println("Select Item Number");
			Integer itemNumber = Integer.valueOf(sc.nextLine());
			System.out.println("Enter Quantity");
			Integer quantity = Integer.valueOf(sc.nextLine());
			System.out.println();
			purchasedItems.put(codeVsItemMap.get(itemNumber), quantity);
			System.out.println("continue?Yes or No");
			String input = sc.nextLine();
			isContinue = input.equals("Yes")?true:false;
			System.out.println();
		}

		HashMap<String, Float> itemVsPrice = calculateTotalItemPrice(purchasedItems);
		printBill(itemVsPrice, customerName);
	}
}