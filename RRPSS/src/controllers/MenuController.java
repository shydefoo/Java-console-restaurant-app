package controllers;
import entity.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Menu;
import entity.MenuItem;

public class MenuController {
	private Menu menu;
	
	public MenuController(){
		menu = new Menu();
	}
	
	public Menu getMenu(){
		return this.menu;
	}
	
	public void run() throws InputMismatchException{ 
		boolean x = true; 
		Scanner input = new Scanner(System.in);
		while(x){
			boolean y = true;
			this.displayMenuOptions();
			int choice = input.nextInt();
			switch(choice){
				case(1):
					do{
						try{
							this.createMenuItem();
							y = false;
						}
						catch(InputMismatchException e){
							System.out.println("Invalid Input. 1");
						}
					}while(y);
					break;
				case(2): break;
				case(3): 
					do{
						try{
							this.deleteMenuItem();
							y = false;
						}
						catch(InputMismatchException e){
							System.out.println("Invalid Input. 3");
						}
						catch(ArrayIndexOutOfBoundsException e){
							System.out.println("There is no such option!");
						}
					}while(y);
					break;
				case(4): this.menu.printMenuL();break;
				case(-1):return;
				default: System.out.println("Please select a valid option");
			}
		}
	}
		
	

	
	//createMenuItem() - 1st option, to add item into menu. Introduce file writing operator
	public void createMenuItem(){
		Scanner input = new Scanner(System.in);
		boolean x = true;
		String type = "";
		String name = ""; 
		double price = 0; 
		String desc = ""; 
		//these variables are need for addItem() method
		while(x){	//while loop to remain this method if wrong type selected
			int choice;
			System.out.println("Create new menu item");
			System.out.println("What type of item do you wish to add?");
			this.showMenuTypes();
			choice = input.nextInt();
			if(choice == -1) return;
			switch(choice){
				case(1): type = "mains";break;
				case(2): type = "drinks";break;
				case(3): type = "desserts";break;
				default: System.out.println("Invalid Option. No items added");		
			}
			if(!type.equals("")){
				//Input name
				boolean y = true;
				System.out.println("Enter name of new menu item: (Enter -1 to go back)");
				input.nextLine();
				name = input.nextLine();
				if(name.equals("-1")) continue; //continue used to go back to choose mains, drinks or desserts
				
				//Input price
				do{
					try{
						System.out.println("Enter price: (Enter -1 to go back)");
						price = input.nextDouble();
						y = false;
					}
					catch(InputMismatchException e){
						System.out.println("Please enter the price in digits");
						input.nextLine();
					}
				}while(y);
				if(price == -1) continue;
				
				//input description
				System.out.println("Enter description of new item: (Enter -1 to go back)");
				input.nextLine();
				desc = input.nextLine();
				if(desc.equals("-1")) continue;
				
				//Call method to add item into menu by type
				menu.addItem(name, price, desc, type);return; //exit createMenuItem() once new item is added
			} 				
		}		
	}
	
	public void updateMenuItem(){
		Scanner input = new Scanner(System.in);
		boolean x = true;
		String type = "";
		String name = ""; 
		double price = 0; 
		String desc = "";
		while(x){	//while loop to remain this method if wrong type selected
			int choice;
			System.out.println("Update menu item");
			System.out.println("What type of item do you wish to add?");
			this.showMenuTypes();
			choice = input.nextInt();
			if(choice == -1) return;
			switch(choice){
				case(1): type = "mains";break;
				case(2): type = "drinks";break;
				case(3): type = "desserts";break;
				default: System.out.println("Invalid Option. No items added");		
			}
		}
	}
			
			
			
	public void deleteMenuItem(){
		Scanner input = new Scanner(System.in);
		menu.printMenuL();
		boolean x = true;
		int index=0;
		while(x){
			System.out.println("Remove Item:");
			this.showMenuTypes();
			int choice = input.nextInt(); String type = "";
			switch(choice){
			case(1): 
				type = "mains";
				menu.printMains();
				break;
			case(2): 
				type = "drinks";
				menu.printDrinks();break;
			case(3): 
				type = "desserts";
				menu.printDesserts();break;
			case(-1): return;
			default: System.out.println("Invalid Option. No items removed");
			}
			ArrayList<MenuItem> temp = menu.getTypeM(type); //temp is a reference to the selected type of menu		
			
			
			//this can be substituted using try catch block. kiv. 
			boolean y = true;
			do{
				try{
					System.out.println("Choose item to remove: (Enter -1 to go back)");
					index = input.nextInt();
					if(index == -1) {
						y=false;continue;
					}
					index--;
					menu.removeItem(index,type);
					y = false;return;
					
				}
				catch(InputMismatchException e){
					System.out.println("Please enter index in digits");
					input.nextLine();
				}
				catch(IllegalStateException e){
					System.out.println("Please enter index in digits");
				}
				catch(IndexOutOfBoundsException e){
					System.out.println("Item does not exist. Please try again");
				}
			}while(y);
			
			
			
			
			 //id of item shown is 1 more than index in ArrayList
			//temp.get(index).printItem();
			
			
		}
		
		
	}
	
	public void displayMenuOptions(){		//Display main menu options
		System.out.println("Menu Options (Enter -1 to go back)");
		System.out.println("1. Create new menu item");
		System.out.println("2. Update menu item");
		System.out.println("3. Remove menu item");
		System.out.println("4. Display menu");
	}
	
	public void showMenuTypes(){
		System.out.println("Please select item type: (Enter -1 to go back)");
		System.out.println("1. Main course");
		System.out.println("2. Drinks");
		System.out.println("3. Desserts");
		
	}
	
	
	
	
	
		
	
}
