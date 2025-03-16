package vending;

import java.util.ArrayList;

public class Vending {
	private int money;
	private ArrayList<Drink> drinks = new ArrayList<Drink>();
	
	public void addVending(Drink drink) {
		drinks.add(drink);
	}
	
	public void changeVending(int seq, int price, int count) {
		drinks.get(seq-1).drinkChange(price, count);
	}
	
	public void printDrink() {
		for (Drink drink : drinks) {
			drink.printDrink();
		}
	}
	
	public void addMoney(int money) {
		this.money += money;
	}
	
	public int buyDrink(int num) {
		int price = drinks.get(num -1).getPrice();
		if(money >= price)  {
			money -= price;
			return drinks.get(num -1).sellDrink();
		}
		return 0;
	}
}
