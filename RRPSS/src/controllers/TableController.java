package controllers;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import entity.Reservation;
import entity.ReservationAll;
import entity.Table;
import entity.TableAll;

public class TableController {	//This class is just to settle which tables are being reserved, and which to be unreserved.
	public static TableAll TableRecords;
	private ReservationAll rL;
	
	public TableController(){
		TableRecords = new TableAll(5,5,10,10);
		rL = ReservationController.allTheReservations;
	}
	

	
	public ReservationAll getReservationAll(){
		return rL;
	}
	
	public void setRl(ReservationAll rA){
		rL = rA;
	}
	
	public void run(){
		Date today = new GregorianCalendar().getTime();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String todayString = df.format(today);
		
		
		System.out.println("Syncing tables with reservations list");
		TableRecords.syncTables(todayString, this.rL);
		try{
			//System.out.println("Testing removal of expired reservations");
			TableRecords.expireReservations(rL, todayString, 30);
		}catch(ParseException e){
			System.out.println("OH SHIT.");
		}
		//TableRecords.showTableStatuses();
			//System.out.println("Please Select option: (Enter -1 to go back)");
	}
	
	public void getAvailableTables(){
		System.out.println("These are the tables available at the moment:");
		ArrayList<Table> tempTableList = TableRecords.getAvailableTables();
		if(tempTableList.isEmpty()){
			System.out.println("All the tables are currently occupied or reserved during this timeslot. Please wait");
		}else{
			for(int i=0;i<tempTableList.size();i++){
				tempTableList.get(i).displayTable();
			}
		}
	}
	
	public void showAllTableStatuses(){
		System.out.println("Curent status of all tables: ");
		TableRecords.showTableStatuses();
	}
}
