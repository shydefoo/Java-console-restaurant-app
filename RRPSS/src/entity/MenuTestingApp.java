package entity;

public class MenuTestingApp {
	public static void main(String[] args){
		Menu menu = new Menu();
		menu.addItem("potato", 5.00, "Chips","desserts");
		MenuItem temp = menu.getDessertsM().get(0);
		temp.printItem();
		menu.addItem("chicken rice", 4.00, "Hainanese chicken rice there you go", "mains");
		menu.getMainsM().get(0).printItem();
		
	}
}
