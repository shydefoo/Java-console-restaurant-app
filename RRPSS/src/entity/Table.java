package entity;

import java.util.Date;

public class Table {
	private int tableNumber;
	private int tableSeats;
	private boolean isReservedAM;
	private boolean isReservedPM;
	private boolean isOccupied;
	private OrderSheetPerTable orderSheet;
	
/**
 * Constructor for Table
 * @param tableNumber The number assigned to the table
 * @param seats The number of seats on this table
 * @param isReserved Whether the table has been reserved
 * @param isOccupied Whether the table is currently being occupied
 * @param customerID The customer assigned to this table as it is being occupied
 */
	public Table (int tableNumber, int tableSeats, boolean isReservedAM, boolean isReservedPM, boolean isOccupied){
		this.tableNumber = tableNumber;
		this.tableSeats = tableSeats;
		this.isReservedAM = isReservedAM;
		this.isReservedPM = isReservedPM;
		this.isOccupied = isOccupied;
		this.orderSheet = null;
	
	}
	
	/**
	 * Getter and Setter Methods	
	 */

	public int getTableNumber(){
		return tableNumber;
	}

	public void setTableNumber(int number){
		this.tableNumber = number;
	}

	public int getSeats(){
		return tableSeats;
	}

	public void setSeats (int number){
		this.tableSeats = number;
	}

	public boolean isReservedAM(){
		return isReservedAM;
	}

	public void setIsReservedAM(boolean b){
		this.isReservedAM = b;
	}

	public boolean isReservedPM(){
		return isReservedPM;
	}

	public void setIsReservedPM(boolean b){
		this.isReservedPM = b;
	}

	public boolean isOccupied(){
		return isOccupied;
	}

	public void setIsOccupied(boolean b){
		this.isOccupied = b;
	}
	
	public OrderSheetPerTable getOrderSheet(){
		return orderSheet;
	}

	public void displayTable(){
		System.out.println("");
		System.out.println("Table Number: "+getTableNumber());
		System.out.println("Number of Seats: "+getSeats());
		//System.out.println(isOccupied() ? "Occupied" : 
			//(isReservedAM() ? "Reserved" : "Available"));
		if(isReservedAM){
			System.out.println("Reserved in AM");
		}
		if(isReservedPM){
			System.out.println("Reserved in PM");
		}
		if(isOccupied){
			System.out.println("Table Occupied");
		}
	}

	public void initOrderSheet(int tableNum, int staffId, Date today){
		orderSheet = new OrderSheetPerTable(tableNum, staffId, today);
	}
		
	}
