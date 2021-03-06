package entity;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import controllers.ReservationController;

/**
 * TableAll Class contains all the tables
 * @since 9/11/2016
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class TableAll {
    //Private variables

    /**
     * allTheTables ArrayList contains a list of all the tables
     */
    private ArrayList<Table> allTheTables;
    /**
     * numTenSeats count of the tables with 10 seats
     */
    private static int numTenSeats;
    /**
     * numEightSeats count of the table with 8 seats
     */
    private static int numEightSeats;
    /**
     * numFourSeats count of the table with 4 seats
     */
    private static int numFourSeats;
    /**
     * numTwoSeats count of the tables with 2 seats
     */
    private static int numTwoSeats;
    /**
     * totalNumOfTables count of all the tables
     */
    private int totalNumOfTables;

    /**
     * SeatsPerTable assigns seats of the table to a letter
     */
    private enum SeatsPerTable {
        A(10), B(8), C(4), D(2); //So that we can change the number of seats at each table if need be.

        private int value;

        /**
         * SeatsPerTable assigns the number of seats for the tables
         *
         * @param value number of seats for the tables
         */
        private SeatsPerTable(int value) {
            this.value = value;
        }

        /**
         * getValue method retrieves the number of seats for the tables
         *
         * @return number of seats for the tables
         */
        public int getValue() {
            return value;
        }
    }

    private ArrayList<Reservation> reservationsTodayAM;
    private ArrayList<Reservation> reservationsTodayPM;

    /**
     * TableAll Constructor assigns and sets up all the tables in the restaurant
     *
     * @param a the number of tables for 10 seats
     * @param b the number of tables for 8 seats
     * @param c the number of tables for 4 seats
     * @param d the number of tables for 2 seats
     */
    public TableAll(int a, int b, int c, int d) {
        allTheTables = new ArrayList<Table>();
        numTenSeats = a;
        numEightSeats = b;
        numFourSeats = c;
        numTwoSeats = d;
        totalNumOfTables = a + b + c + d;
        setUpTables();

        reservationsTodayAM = new ArrayList<Reservation>();
        reservationsTodayPM = new ArrayList<Reservation>();
    }

    /**
     * getAllTheTables retrieves all the tables
     *
     * @return all the tables
     */
    public ArrayList<Table> getAllTheTables() {
        return allTheTables;
    }

    /**
     * setAllTheTables assigns all the tables
     *
     * @param allTheTables all the tables
     */
    public void setAllTheTables(ArrayList<Table> allTheTables) {
        this.allTheTables = allTheTables;
    }

    /**
     * getNumTenSeats retrieves all the tables with 10 seats
     *
     * @return tables with 10 seats
     */
    public static int getNumTenSeats() {
        return numTenSeats;
    }

    /**
     * setNumTenSeats assigns the number of tables with 10 seats
     *
     * @param numTenSeats tables with 10 seats
     */
    public static void setNumTenSeats(int numTenSeats) {
        TableAll.numTenSeats = numTenSeats;
    }

    /**
     * getNumEightSeats retrieves all the tables with 8 seats
     *
     * @return tables with 8 seats
     */
    public static int getNumEightSeats() {
        return numEightSeats;
    }

    /**
     * setNumEightSeats assigns all the tables with 8 seats
     *
     * @param numEightSeats tables with 8 seats
     */
    public static void setNumEightSeats(int numEightSeats) {
        TableAll.numEightSeats = numEightSeats;
    }

    /**
     * getNumFourSeats retrieves all the tables with four seats
     *
     * @return tables with 4 seats
     */
    public static int getNumFourSeats() {
        return numFourSeats;
    }

    /**
     * setNumFourSeats assigns all the tables with four seats
     *
     * @param numFourSeats tables with four seats
     */
    public static void setNumFourSeats(int numFourSeats) {
        TableAll.numFourSeats = numFourSeats;
    }

    /**
     * getNumTwoSeats retrieves all the tables with two seats
     *
     * @return tables with 2 seats
     */
    public static int getNumTwoSeats() {
        return numTwoSeats;
    }

    /**
     * setNumTwo seats assigns all the tables with two seats
     *
     * @param numTwoSeats tables with 2 seats
     */
    public static void setNumTwoSeats(int numTwoSeats) {
        TableAll.numTwoSeats = numTwoSeats;
    }

    /**
     * getTotalNumOfTables retrieves the total number of tables
     *
     * @return total number of tables
     */
    public int getTotalNumOfTables() {
        return totalNumOfTables;
    }

    /**
     * AddTable method will add a new table with a specified number of seats
     *
     * @param temp number of seats for table
     */
    public void AddTable(Table temp) {
        allTheTables.add(temp);
    }

    /**
     * getReservationsTodayAM retrieves all the tables at the AM slot
     *
     * @return tables at the AM slot
     */
    public ArrayList<Reservation> getReservationsTodayAM() {
        return reservationsTodayAM;
    }

    /**
     * getReservationsTodayPM retrieves all the tables at the PM slot
     *
     * @return tables at the PM slot
     */
    public ArrayList<Reservation> getReservationsTodayPM() {
        return reservationsTodayPM;
    }

    /**
     * setUpTables method will insert tables into the ArrayList allTheTables
     */
    public void setUpTables() {	//This function inserts tables into ArrayList allTheTables
        int count = 1;
        for (int i = 0; i < numTwoSeats; i++) {
            Table table = new Table(count, SeatsPerTable.D.getValue(), false, false, false);
            AddTable(table);
            count++;
        }
        for (int i = 0; i < numFourSeats; i++) {
            Table table = new Table(count, SeatsPerTable.C.getValue(), false, false, false);
            AddTable(table);
            count++;
        }
        for (int i = 0; i < numEightSeats; i++) {
            Table table = new Table(count, SeatsPerTable.B.getValue(), false, false, false);
            AddTable(table);
            count++;
        }
        for (int i = 0; i < numTenSeats; i++) {
            Table table = new Table(count, SeatsPerTable.A.getValue(), false, false, false);
            AddTable(table);
            count++;
        }

    }

    /**
     * showAvailableTables method will display all the available tables
     */
    public void showAvailableTables() {
        for (int i = 0; i < allTheTables.size(); i++) {
            if (!(allTheTables.get(i).isOccupied())) {
                allTheTables.get(i).displayTable();
            }
        }
    }

    /**
     * showTableStatuses will show all the table status
     */
    public void showTableStatuses() {
        for (int i = 0; i < allTheTables.size(); i++) {
            allTheTables.get(i).displayTable();
        }
    }

    /**
     * syncTables method will sync the tables according to AM and PM
     *
     * @param todayString Todays Date
     * @param rL Reservation List
     */
    public void syncTables(String todayString, ReservationAll rL) {
        ArrayList<Integer> reservedTablesAM = rL.reservedTablesOnDate(todayString, "AM");
        ArrayList<Integer> reservedTablesPM = rL.reservedTablesOnDate(todayString, "PM");
        for (int i = 0; i < totalNumOfTables; i++) {
            if (reservedTablesAM.contains(allTheTables.get(i).getTableNumber())) {
            	
                Table temp1 = allTheTables.get(i);
                temp1.setIsReservedAM(true);
            }
            if (reservedTablesPM.contains(allTheTables.get(i).getTableNumber())) {
            	
                Table temp1 = allTheTables.get(i);
                
                temp1.setIsReservedPM(true);
            }
        }
    }

    /**
     * getReservedTables retrieves all the reserved tables for the day
     *
     * @return reserved tables for the day
     */
    public ArrayList<Table> getReservedTables() {
        ArrayList<Table> tablesReservedToday = new ArrayList<Table>();
        Table temp = null;
        for (int i = 0; i < totalNumOfTables; i++) {
            if (allTheTables.get(i).isReservedAM() || allTheTables.get(i).isReservedPM()) {
                temp = allTheTables.get(i);
                tablesReservedToday.add(temp);
            }
        }
        return tablesReservedToday;
    }

    /**
     * getAvailableTables method retrieves all the available tables for the day
     *
     * @return all the available tables for the day
     */
    public ArrayList<Table> getAvailableTables() {
        ArrayList<Table> tablesAvailable = new ArrayList<Table>();
        Table temp = null;
        Date now = new GregorianCalendar().getTime();
        String Slot = "";
        try {
			Slot = ReservationController.checkSlot(now);
			if(Slot.equals("AM")){
				for(int i=0;i<totalNumOfTables;i++){
					if (!(allTheTables.get(i).isOccupied()) && !( allTheTables.get(i).isReservedAM())) {
	                    temp = allTheTables.get(i);
	                    tablesAvailable.add(temp);
	                }
				}
			}
			else if(Slot.equals("PM")){
				for(int i=0;i<totalNumOfTables;i++){
					if (!(allTheTables.get(i).isOccupied()) && !(allTheTables.get(i).isReservedPM())) {
	                    temp = allTheTables.get(i);
	                    tablesAvailable.add(temp);
	                }
				}
			}
			else if(Slot.equals("Closed")){
				System.out.println("");
				System.out.println("Unable to check tables right now because the restaurant is closed");
				System.out.println("");
			}

		} catch (ParseException e) {
			System.out.println("Unable to check available tables at the moment. ");
		}
        
        return tablesAvailable;
    }

    //Problem here...only removes reservations on today. Not on days before today...hmmmm
    /**
     * expireReservations method will expire after the time slot
     *
     * @param rL the reservation list
     * @param todayString the date on the day
     * @param time_limit 30 minute window between reservation and current time
     * @throws ParseException for SDF if the format is not parsed correctly
     */
    public void expireReservations(ReservationAll rL, String todayString, int time_limit) throws ParseException {
        Date today = new GregorianCalendar().getTime();
        String restaurantShift = ReservationController.checkSlot(today);
        //System.out.println("Restaurant shift: " + restaurantShift);
        reservationsTodayAM = rL.reservationsMadeOnDate(todayString, "AM");
        reservationsTodayPM = rL.reservationsMadeOnDate(todayString, "PM");
        ArrayList<Reservation> holdingArrayList = null;
        ArrayList<Calendar> timeOfArrival = new ArrayList<Calendar>();
        int cont = 0;
        if (restaurantShift == "AM") {
            holdingArrayList = reservationsTodayAM;
            cont = 1;
        } else if (restaurantShift == "PM") {
            holdingArrayList = reservationsTodayPM;
            cont = 1;
        } else {
            System.out.println("Restaurant is closed at the moment. Removal of expired reservations will resume once the restaurant re-opens.");
        }
        if (cont == 1) {
            for (int i = 0; i < holdingArrayList.size(); i++) {
                String timeTemp = holdingArrayList.get(i).getTimeOfArrival();
                String dateTemp = holdingArrayList.get(i).getDateReserved();
                Calendar dateObjectTemp = ReservationController.stringToCalender("dd-MM-yyyy HHmm", dateTemp, timeTemp);
                timeOfArrival.add(dateObjectTemp); //Order of calendar objects in timeOfArrival is the same as reservation objects in reservationsTodayAM
            }
            for (int i = 0; i < timeOfArrival.size(); i++) {
                //if (timeOfArrivalAM + 30min) is still before now, that means their reservation should expire. 
                Calendar temp = timeOfArrival.get(i);
                temp.add(Calendar.MINUTE, time_limit);
                if (temp.getTime().before(today)) {
                    int tableNumber = holdingArrayList.get(i).getTableNumber();
                    //System.out.println("TableNumber:" + tableNumber);
                    Table tempTable = allTheTables.get(tableNumber - 1);
                    //if(!(tempTable.isOccupied())){
                    //1.remove this reservation from reservation list
                    //2.set isReservedAM/PM for that table to false.
                    if (restaurantShift == "AM") {
                        tempTable.setIsReservedAM(false);
                    } else if (restaurantShift == "PM") {
                        tempTable.setIsReservedPM(false);
                    }
                    Reservation todelete = holdingArrayList.get(i);
                    System.out.println("Removing reservation: ");
                    todelete.printReservation();
                    rL.deleteReservation(todelete);
                    
                }
            }
            reservationsTodayAM = rL.reservationsMadeOnDate(todayString, "AM");//Refresh reservationsTodayAM.
            reservationsTodayPM = rL.reservationsMadeOnDate(todayString, "PM");
        }

    }

    /**
     * printReservationsArrayList will print out the reservations
     *
     * @param r the reservation ArrayList
     */
    public void printReservationsArrayList(ArrayList<Reservation> r) {
        for (int i = 0; i < r.size(); i++) {
            r.get(i).printReservation();
        }
    }

    /**
     * getTable method will retrieve the table inside all the tables array
     *
     * @param tableNumber the table number
     * @return the table
     */
    public Table getTable(int tableNumber) {
        Table temp = allTheTables.get(tableNumber - 1);
        return temp;
    }

    /**
     * checkOrderSheetInit method initializes the table to check order or not
     *
     * @param tableNumber the table number
     * @return true or false
     */
    public boolean checkOrderSheetInit(int tableNumber) {
        Table temp = getTable(tableNumber);
        if (temp.getOrderSheet() == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * initialiseORderSheetForTable method instantiates order sheet object.
     *
     * @param tableNumber table number
     * @param staffId staff that did it.
     * @param today date and time order sheet was instantiated.
     */
    public void initialiseOrderSheetForTable(int tableNumber, String staffId, Date today) {
        if (!(checkOrderSheetInit(tableNumber))) {
            Table temp = getTable(tableNumber);
            temp.initOrderSheet(tableNumber, staffId, today);
        }
    }

    /**
     * consolidateOrderSheets method Takes order sheet from each table and puts
     * them into an ArrayList.
     *
     * @return ArrayList containing all OrderSheetsPerTable objects.
     */
    public ArrayList<OrderSheetPerTable> consolidateOrderSheets() {
        ArrayList<OrderSheetPerTable> allTheOrderSheets = new ArrayList<OrderSheetPerTable>();
        for (int i = 0; i < allTheTables.size(); i++) {
            Table tempTable = allTheTables.get(i);
            if (tempTable.getOrderSheet() != null) {
                allTheOrderSheets.add(tempTable.getOrderSheet());
            }
        }

        return allTheOrderSheets;
    }

}
