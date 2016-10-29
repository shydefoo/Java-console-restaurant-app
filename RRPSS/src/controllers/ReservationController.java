package controllers;
import entity.ReservationAll;
import entity.Table;
import entity.TableAll;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		allTheTables = new TableAll(5,8,10,10);
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
		boolean x = true;
		while(x){
			
			int cont = 0;
			
			do{
				System.out.println("###### Create Reservation ######");
				System.out.println("Input date to reserve on: (Enter -1 to go back)");
				inputDate = input.next();
				if (inputDate.equals("-1"))return;
				System.out.println("Input time of arrival: (Enter -1 to go back)");
				inputTime = input.next();
				if (inputTime.equals("-1"))return;
				String atThisMoment = returnNowInString("dd-MM-yyyy");
				try {
					Calendar dntFromUser = stringToCalender("dd-MM-yyyy HHMM", inputDate,inputTime);
					//Calendar rightNow = Calendar.getInstance();
					Calendar y = addDays(30);
					Calendar z = addDays(0);
					//System.out.println("AtThisMoment:" + atThisMoment);
					//System.out.println("One month from now: " + oneMonthFromNow);
					if(inputDate.equals(atThisMoment) || y.before(dntFromUser)){
						System.out.println("You cannot reserve on this day");
					}
					else{
						System.out.println("Please wait...");
						cont = 1;
						x = false;
					}
				} catch (ParseException e) {
					System.out.println("Please enter date in the format dd-mm-yyyy, and time in HHMM.");
					input.nextLine();
				}
			}while(x);
			if(cont==1){
				//indexOfReserved contains all the tableNumbers that are reserved on the inputDate. 
				//Compare that with all the tables, seive out those not in indexOfReserved == unreserved tables!
				ArrayList<Integer> indexOfReserved = allTheReservations.reservedTablesOnDate(inputDate);
				ArrayList<Table> unReservedTables = new ArrayList<Table>();
				ArrayList<Table> tempTableAll = allTheTables.getAllTheTables();
				if(indexOfReserved.isEmpty()){
					System.out.println("Goodnight time to sleep");
					System.out.println("Enter number of people");
					allTheReservations.createReservation(0, 2, "97811150", "Foo Shi De", "01-10-2016", "1600");
				}
				else{
					for(int i = 0; i<allTheTables.getTotalNumOfTables();i++){
						if(!(indexOfReserved.contains(tempTableAll.get(i).getTableNumber()))){
							Table temp = tempTableAll.get(i);
							unReservedTables.add(temp);
							temp.displayTable();
						}
					}
				}
				
				
				
			}
		}		
		
	}

	public void checkReservation() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter date to check: ");
		String dateInput = input.next();
		allTheReservations.checkReservationsOnDate(dateInput);
		
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
			System.out.println("##### Remove reservation ######");
			System.out.println("Enter name: (Enter -1 to go back) ");
			name = input.nextLine();
			if(name.equals("-1")) return;
			System.out.println("Enter handphone number: (Enter -1 to go back)");
			number = input.nextLine();
			if(name.equals("-1")) return;
			System.out.println("name:" + name + " number: "+ number);
			
			ArrayList<Reservation> tempList = new ArrayList<Reservation>();
			for(int i=0;i<rAll.size();i++){
				Reservation temp = rAll.get(i);
				if(temp.getCustomerName().equals(name) && temp.getHpNumber().equals(number)){
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
					if(choice == -1) return;
					try{
						Reservation todelete = tempList.get(choice);
						allTheReservations.deleteReservation(todelete); 
						z = false;
						System.out.println("This reservation has been removed");
						return;
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
	
	public String returnNowInString(String format){
		Date d = Calendar.getInstance().getTime(); // Current time
		SimpleDateFormat sdf = new SimpleDateFormat(format); // Set your date format
		String currentData = sdf.format(d); // Get Date String according to date format
		return currentData;
	}
	public String returnOneMonthFromNow(String format){
		Calendar d = Calendar.getInstance();
		d.add(Calendar.DAY_OF_MONTH, 30);
		Date date = d.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format); // Set your date format
		String currentData = sdf.format(date); // Get Date String according to date format
		return currentData;
	}
	
	public Calendar addDays(int days){
		Calendar d = Calendar.getInstance();
		d.add(Calendar.DAY_OF_MONTH, days);
		return d;
	}
	
	public Calendar stringToCalender(String format, String inputDate, String inputTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(format);
		Date reservationDate = sdf.parse(inputDate + " " + inputTime);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(reservationDate);
		return calendar;
	}
}