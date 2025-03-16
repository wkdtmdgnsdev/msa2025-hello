package vending;

public class Drink {
	private int num;
	private String name;
	private int price;
	private int count;
	
	public Drink(int num,String name, int price, int count) {
		this.num = num;
		this.name = name;
		this.price = price;
		this.count = count;
	}
	
	public void drinkChange(int price, int count) {
		this.price = price;
		this.count = count;
	}
	
	public void printDrink() {
		System.out.println(num+1 +". " +name +" " +price);
		
		if(count == 0) {
			System.out.print("재고 없음");
		}
	}
	
	public int sellDrink() {
		if(count == 0) {
			return 0;
		}
		count--;
		return 1;
	}
	
	public int getPrice() {
		return price;
	}
}
