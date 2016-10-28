package controllers;
import entity.ReservationAll;
import entity.TableAll;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.util.InputMismatchException;

import entity.Reservation;

public class ReservationController{
	
	private ReservationAll allTheReservations;
	private TableAll allTheTables;
	
	
	public ReservationController(){
		allTheReservations = new ReservationAll();
		allTheTables = new TableAll();
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
							this.createReservation();
							y = false;
						}
						catch(InputMismatchException e){
							System.out.println("Invalid option");
						}
					}while(y);
					break;
				case(2): 
					do{
						try{
							this.checkReservation();
							y = false;
						}
						catch(InputMismatchException e){
							System.out.println("Invalid option");
						}
					}while(y);
					break;
					
				case(3): 
					do{
						try{
							this.removeReservation();
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
				case(-1):return;
				default: System.out.println("Please select a valid option");
			}
		}
	}
	
	public void createReservation() {
		//1. get date and time as input
		//2. check that not same day or max 1 month
		//3. check reservations on that day by comparing with tables. 
		//3. If unreserved tables exist, ask for pax
		//4. Search for tables that have at most pax + 3 seats. 
		//5. If have, input name, hp number. 
		//6. Create reservation 
		Scanner input = new Scanner(System.in);
		String inputDate; String inputTime;
		Calendar date;
		System.out.println("Input date to reserve on: ");
		inputDate = input.next();
		System.out.println("Input time of arrival: ");
		inputTime = input.next();
		if(date.getTime() == inputDate)
		
	}

	public void checkReservation() {
		// TODO Auto-generated method stub
		
	}

	public void removeReservation() {
		//1. input name and handphone number
		//2. check reservationList for match
		//3. if no match, nothing changed
		//4. if more than 1 match, select which to remove
		Scanner input = new Scanner(System.in);
		String name, number; 
		ArrayList<Reservation> rAll = allTheReservations.getReservationList();	
		boolean x = true;
		while(x){
			System.out.println("##### Reservation ######");
			System.out.println("Enter name: (Enter -1 to go back) ");
			name = input.next();
			if(name.equals("-1")) return;
			System.out.println("Enter handphone number: (Enter -1 to go back)");
			number = input.next();
			if(name.equals("-1")) return;
			System.out.println("name:" + name + "number: "+ number);
			
			ArrayList<Reservation> tempList = new ArrayList<Reservation>();
			for(int i=0;i<rAll.size();i++){
				Reservation temp = rAll.get(i);
				if(temp.getCustomerName() == name && temp.getHpNumber() == number){
					tempList.add(temp);
				}
			}
			if(tempList.isEmpty()){
				System.out.println("There are no reservations under this name"); return;
			}else{
				System.out.println("Reservations made under this name: ");
				for(int i=0;i<tempList.size();i++){
					tempList.get(i).printReservation();
				}
				int choice = 0;
				boolean y = true; boolean z = true;
				do{
					do{
						try{
							System.out.println("Select reservation to remove: (Enter -1 to go back)");
							choice = input.nextInt();
							y = false;
						}
						catch(InputMismatchException e){
							System.out.println("Invalid option");
						}
					}while(y);
					if(choice == -1) break;
					try{
						Reservation todelete = tempList.get(choice);
						allTheReservations.deleteReservation(todelete); 
						z = false;
						System.out.println("This reservation has been removed");
					}
					catch(ArrayIndexOutOfBoundsException e){
						System.out.println("Invalid choice entered.");
					}
					
				}while(z);								
			}
		}		
	}
	

	public void displayMenuOptions(){		//Display main menu options
		System.out.println("Menu Options (Enter -1 to go back)");
		System.out.println("1. Create new reservation");
		System.out.println("2. Check reservation");
		System.out.println("3. Remove reservation");
		
	}
	
}