package controllers;

import java.util.Scanner;
import entity.Menu;
import entity.MenuItem;

public class MenuController {
	private Menu menu;
	
	public MenuController(){
		menu = new Menu();
	}
	
	public void CreateMenuItem(){
		Scanner input = new Scanner(System.in);
		System.out.println("Create new menu item");
		System.out.println("What type of item do you wish to add?");
		System.out.println("Please select option: (Enter -1 to go back)");
		System.out.println("1. Main course");
		System.out.println("2. Drinks");
		System.out.println("3. Desserts");
		
		int choice = input.nextInt(); String type = "";
		String name = ""; double price = 0; String desc = ""; 
			switch(choice){
			case(1): type = "mains";break;
			case(2): type = "drinks";break;
			case(3): type = "desserts";break;
			default: System.out.println("Invalid Option. No items added");return;
			}
			if(type != ""){
				System.out.println("Enter name of new menu item: (Enter -1 to go back)");
				input.nextLine();
				name = input.nextLine();
				if(name == "-1")return;
				System.out.println("Enter price: (Enter -1 to go back)");
				price = input.nextDouble();
				if(price == -1)return;
				System.out.println("Enter description of new item: (Enter -1 to go back)");
				input.nextLine();
				desc = input.nextLine();
				if(desc == "-1")return;
				/*System.out.println("test");
				System.out.println(name);
				System.out.println(desc);*/
				menu.addItem(name, price, desc, type);
			}
			
			
			
			
	}
	public void DeleteMenuItem(){
		Scanner input = new Scanner(System.in);
		System.out.println("Choose type of item to be removed (Enter -1 to go back)");
		System.out.println("1. Main course");
		System.out.println("2. Drinks");
		System.out.println("3. Desserts");
		int choice = input.nextInt(); String type = "";
		switch(choice){
		case(1): type = "mains";break;
		case(2): type = "drinks";break;
		case(3): type = "desserts";break;
		default: System.out.println("Invalid Option. No items added");return;
		}
		
	}
	
	
	public void printMenuL(){
		for(MenuItem s: menu.getMainsM()){
			s.printItem();
		}
		for(MenuItem s: menu.getDrinksM()){
			s.printItem();
		}
		for(MenuItem s: menu.getDessertsM()){
			s.printItem();
		}
	}
		
	
}
