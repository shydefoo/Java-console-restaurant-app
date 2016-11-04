package entity;

import java.util.ArrayList;

public class TableAll {
	private ArrayList<Table> allTheTables;
	private static int numTenSeats;
	private static int numEightSeats;
	private static int numFourSeats;
	private static int numTwoSeats;
	private int totalNumOfTables;
	private enum SeatsPerTable {
	    A(10), B(8), C(4), D(2);
		private int value;
		private SeatsPerTable(int value) {
				this.value = value;
		}
		public int getValue() {
	        return value;
	    }
	}
	public TableAll(int a, int b, int c ,int d){
		allTheTables = new ArrayList<Table>();
		numTenSeats = a;
		numEightSeats = b;
		numFourSeats = c;
		numTwoSeats = d;
		totalNumOfTables = a+b+c+d;
		setUpTables();
	}
	
	
	public ArrayList<Table> getAllTheTables() {
		return allTheTables;
	}
	public void setAllTheTables(ArrayList<Table> allTheTables) {
		this.allTheTables = allTheTables;
	}
	public static int getNumTenSeats() {
		return numTenSeats;
	}
	public static void setNumTenSeats(int numTenSeats) {
		TableAll.numTenSeats = numTenSeats;
	}
	public static int getNumEightSeats() {
		return numEightSeats;
	}
	public static void setNumEightSeats(int numEightSeats) {
		TableAll.numEightSeats = numEightSeats;
	}
	public static int getNumFourSeats() {
		return numFourSeats;
	}
	public static void setNumFourSeats(int numFourSeats) {
		TableAll.numFourSeats = numFourSeats;
	}
	public static int getNumTwoSeats() {
		return numTwoSeats;
	}
	public static void setNumTwoSeats(int numTwoSeats) {
		TableAll.numTwoSeats = numTwoSeats;
	}
	
	public int getTotalNumOfTables(){
		return totalNumOfTables;
	}
	
	public void AddTable(Table temp){
		allTheTables.add(temp);
	}
	
	public void setUpTables(){	//This function inserts tables into ArrayList allTheTables
		int count = 1;
		for(int i=0;i<numTwoSeats;i++){
			Table table = new Table(count, SeatsPerTable.D.getValue(), false, false, false);
			AddTable(table);
			count++;
		}
		for(int i=0;i<numFourSeats;i++){
			Table table = new Table(count, SeatsPerTable.C.getValue(), false, false, false);
			AddTable(table);
			count++;
		}
		for(int i=0;i<numEightSeats;i++){
			Table table = new Table(count, SeatsPerTable.B.getValue(), false, false, false);
			AddTable(table);
			count++;
		}
		for(int i=0;i<numTenSeats;i++){
			Table table  = new Table(count, SeatsPerTable.A.getValue(), false, false, false);
			AddTable(table);
			count++;
		}
		
		
		
	}
	
}
