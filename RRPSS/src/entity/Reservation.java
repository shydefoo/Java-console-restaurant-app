package entity;

import java.util.Date;

public class Reservation {
	private int tableNumber;
	private int numberOfPeople;
	private String hpNumber;
	private String customerName;
	//private Date start;
	private String dateReserved;
	private String timeOfArrival;
	
	
	//Constructors 
	
	public Reservation(int tableNumber, int numberOfPeople, String hpNumber, String customerName, String dateReserved, String timeOfArrival){
		this.setTableNumber(tableNumber);
		this.setNumberOfPeople(numberOfPeople);
		this.setHpNumber(hpNumber);
		this.setCustomerName(customerName);
		this.setDateReserved(dateReserved);
		this.setTimeOfArrival(timeOfArrival);
	}


	


	public int getTableNumber() {
		return tableNumber;
	}


	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}


	public int getNumberOfPeople() {
		return numberOfPeople;
	}


	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}


	public String getHpNumber() {
		return hpNumber;
	}


	public void setHpNumber(String hpNumber) {
		this.hpNumber = hpNumber;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setDateReserved(String dateReserved) {
		this.dateReserved = dateReserved;
		
	}
	
	public String getDateReserved() {
		return dateReserved;
	}
	
	public String getTimeOfArrival(){
		return this.timeOfArrival;
	}

	
	public void setTimeOfArrival(String timeOfArrival){
		this.timeOfArrival = timeOfArrival;
	}
	
	public void printReservation(){
		System.out.println("Table: " + tableNumber + ", Number of people: " + numberOfPeople + ", date reserved: " + dateReserved + ", Time of arrival: " + timeOfArrival+ ", Customer name: " + customerName );
	}
	
	
	
	
	
	
}
