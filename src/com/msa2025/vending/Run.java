package vending;

import java.util.Scanner;

public class Run {
	private static Scanner sc = new Scanner(System.in);
	private static int seq = 0;
	private static Vending vending = new Vending();
	
	public static void main(String[] args) {
		String select = "";
		while(true) {
			menu();
			select = sc.nextLine();
			
			switch (select) {
			case "1": addMoney(); break;
			case "2": printDrinks(); break;
			case "3": buyDrink(); break;
			case "4": addDrink(); break;
			case "5": drinkUpdate(); break;
			case "6": return;
			}
		}
	}
	
	public static void menu() {
		System.out.println("1. 금액 투입");
		System.out.println("2. 음료수 조회");
		System.out.println("3. 음료수 구입");
		System.out.println("4. 음료수 추가");
		System.out.println("5. 음료수 수정");
		System.out.println("6. 종료");
	}
	
	public static void addDrink() {
		System.out.print("음료수 이름 : ");
		String name = sc.nextLine();
		System.out.print("음료수 가격 : ");
		int price = Integer.parseInt(sc.nextLine());
		System.out.print("음료수 수량 : ");
		int count = Integer.parseInt(sc.nextLine());
		
		Drink drink = new Drink(seq, name, price, count);
		vending.addVending(drink);
		seq++;
	}
	
	public static void drinkUpdate() {
		System.out.print("수정할 음료수 번호 : ");
		int num = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 음료수 가격 : ");
		int price = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 음료수 수량 : ");
		int count = Integer.parseInt(sc.nextLine());
		
		vending.changeVending(num, price, count);
	}
	
	public static void printDrinks() {
		vending.printDrink();
	}
	
	public static void addMoney() {
		System.out.print("투입 금액 : ");
		vending.addMoney(Integer.valueOf(sc.nextLine()));
	}
	
	public static void buyDrink() {
		System.out.print("구매할 음료 번호 : ");
		int num = vending.buyDrink(Integer.valueOf(sc.nextLine()));
		if (num!= 0) {
			System.out.println("구매 성공");
			return;
		}
		System.out.println("구매 실패");
	}
}
