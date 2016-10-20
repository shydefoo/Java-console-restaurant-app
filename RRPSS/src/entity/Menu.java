package entity;
import java.util.ArrayList;

public class Menu {
	private ArrayList<MenuItem> mains;
	private ArrayList<MenuItem> drinks;
	private ArrayList<MenuItem> desserts; 
	
	//requirement to separate menus into different categories 
	
	//Constructor:
	public Menu(){
		mains = new ArrayList<MenuItem>();
		drinks = new ArrayList<MenuItem>();
		desserts = new ArrayList<MenuItem>();
		
	}
	
	public ArrayList<MenuItem> getMainsM(){
		return this.mains;
	}
	public ArrayList<MenuItem> getDrinksM(){
		return this.drinks;
	}
	public ArrayList<MenuItem> getDessertsM(){
		return this.desserts;
	}
	
	
	public void addItem(String name, double price, String desc, String type){
		MenuItem item = new MenuItem(name,price,desc,type);
		switch(type){
		case "mains": mains.add(item); System.out.println("Item successfully added");break;
		case "drinks": drinks.add(item);System.out.println("Item successfully added"); break;
		case "desserts": desserts.add(item);System.out.println("Item successfully added"); break;
		default: System.out.println("Nothing added");
		}
	}
	
	public void updateItem(int n, String type){
		
	}
	
	public void removeItem(int n, String type){
		
	}
	
}