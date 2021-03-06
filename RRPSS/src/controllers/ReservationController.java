package controllers;

import static controllers.MenuController.menu;
import entity.MenuItem;
import entity.ReservationAll;

import entity.Table;
import entity.TableAll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import entity.Reservation;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ReservationController Class facilitates the reservation process
 * 
 * @since 9/11/2016
 *
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class ReservationController {

    public static ReservationAll allTheReservations;
    private TableAll allTheTables;
    
    private final static String SPACING = "**********************************************";
    private final static String SPACING2 = "##############################################";

    /**
     * ReservationController constructor assigns the tables 
     */
    public ReservationController() {
        allTheReservations = new ReservationAll();
        allTheTables = new TableAll(5, 5, 10, 10);
    }

    /**
     * run Method will run the controller upon start
     * @throws InputMismatchException write or read error
     * @throws NoSuchElementException  when there is no element error
     */
    public void run() throws InputMismatchException, NoSuchElementException {
        boolean x = true;
        Scanner input = new Scanner(System.in);
        int choice;
        while (x) {
            boolean y = true;
            this.displayMenuOptions();
            choice = input.nextInt();
            System.out.println(SPACING2);
            switch (choice) {
                case (1):
                    do {
                        try {
                            this.createReservation();
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;
                case (2):
                    do {
                        try {
                            
                            this.checkReservation();
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;

                case (3):
                    do {
                        try {
                            this.removeReservation(1);
                            
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input. 3");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("There is no such option!");
                        }
                    } while (y);
                    break;
                case (4):
                    do {
                        try {
                            this.removeReservation(0);
                            
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input. 3");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("There is no such option!");
                        }
                    } while (y);
                    break;
                case (5):
                    do {
                        try {
                            allTheReservations.showAllReservations();;
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input. 3");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("There is no such option!");
                        }
                    } while (y);
                    break;
                case (-1):
                    return;
                default:
                    System.out.println("Please select a valid option");
            }
        }
    }

    /**
     * createReservation method facilitates a reservation
     */
    public void createReservation() {
        
        Scanner input = new Scanner(System.in);
        Scanner inputString = new Scanner(System.in);
        String inputDate;
        String inputTime;
        boolean x = true;
        while (x) {
            int cont = 0;
            String slot = "";
            do {
                System.out.println("###### Create Reservation ######");
                System.out.println("Input date to reserve on: (Enter -1 to go back)");
                inputDate = input.next();		//Get input date from user
                if (inputDate.equals("-1")) {
                    return;
                }
                System.out.println("Input time of arrival: (Enter -1 to go back)");
                inputTime = input.next();	 	//Get input time from user
                if (inputTime.equals("-1")) {
                    return;
                }
                Calendar today = new GregorianCalendar();		//Get current date and time
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String todayString = sdf.format(today.getTime());
                try {
                    slot = checkSlot(inputTime);
                    if (slot.equals("")) {
                        System.out.println("Restaurant is closed");
                        break;
                    }
                    System.out.println("SLOT: " + slot);
                    Calendar dntFromUser = stringToCalender("dd-MM-yyyy HHmm", inputDate, inputTime);	//Convert input date & time to calendar object           
                    Calendar oneMonthFromNow = new GregorianCalendar();
                    oneMonthFromNow.add(Calendar.DAY_OF_MONTH, 30);//Get date one month from now
                    if (dntFromUser.compareTo(today) <= 0 || dntFromUser.compareTo(oneMonthFromNow) == 1 || todayString.equals(inputDate)) {
                        System.out.println("You cannot reserve on this date");
                    } else {
                        System.out.println("Please wait...");
                        cont = 1;
                        x = false;
                    }
                } catch (ParseException e) {
                    System.out.println("Please enter date in the format dd-mm-yyyy, and time in HHMM.");
                    input.nextLine();
                }
            } while (x);
            if (cont == 1) {
                //indexOfReserved contains all the tableNumbers that are reserved on the inputDate. 
                //Compare that with all the tables, seive out those not in indexOfReserved == unreserved tables!
                //allTheReservations.checkReservationsOnDate(inputDate);
                ArrayList<Integer> indexOfReserved = allTheReservations.reservedTablesOnDate(inputDate, slot); //Contains all tables numbers that are reserved on input date
                ArrayList<Table> unReservedTables = new ArrayList<Table>(); //ArrayList to store unreserved tables
                ArrayList<Table> tempTableAll = allTheTables.getAllTheTables();

                for (int i = 0; i < allTheTables.getTotalNumOfTables(); i++) {
                    if (!(indexOfReserved.contains(tempTableAll.get(i).getTableNumber()))) {
                        Table temp = tempTableAll.get(i);
                        unReservedTables.add(temp);
                        //temp.displayTable();
                    }
                }
                System.out.println("Enter handphone number: (Enter -1 to return)");
                String hpNumber = inputString.nextLine();
                if (hpNumber.equals("-1") || hpNumber.equals("")){
                    break;
                }
                System.out.println("Enter name: (Enter -1 to return)");
                
                input.nextLine();
                String name = inputString.nextLine();
                if (name.equals("-1") || name.equals("")) {
                    break;
                }
                cont = 0;
                System.out.println("Enter number of people: (Enter -1 to return)");
                int numpax = 0;
                do {
                    try {
                        numpax = input.nextInt();
                        System.out.println(SPACING2);
                        cont = 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number");
                        input.nextLine();
                    }
                } while (cont == 0);
                if (numpax == -1) {
                    break;
                }
                System.out.println("Name:" + name + " handphone: " + hpNumber + " numpax: " + numpax);

                for (int i = 0; i < unReservedTables.size(); i++) {

                    if ((unReservedTables.get(i).getSeats() >= numpax) && (unReservedTables.get(i).getSeats() <= numpax + 3)) {
                        Table temp = unReservedTables.get(i);
                        System.out.println("Table: " + temp.getTableNumber() + ", Number of seats: " + temp.getSeats());
                        allTheReservations.createReservation(temp.getTableNumber(), numpax, hpNumber, name, inputDate, inputTime, slot);
                        try {
                            saveReservations();
                        } catch (IOException ex) {
                            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        x = false;
                        return;
                    }
                }
                System.out.println("There were no tables with sufficient seats for " + numpax + " people");
            }
        }
        
    }

    /**
     * checkReservation method will check if there is a reservation on a 
     * given date
     */
    public void checkReservation() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter date to check: ");
        String dateInput = input.next();
        allTheReservations.checkReservationsOnDate(dateInput);

    }

    /**
     * removeReservation method will remove a reservation
     * @param hpOrdate can be phone number or a date
     */
    public void removeReservation(int hpOrdate) {
        //1. input name and handphone number
        //2. check reservationList for match
        //3. if no match, nothing changed
        //4. if more than 1 match, select which to remove
        Scanner input = new Scanner(System.in);
        String number;
        ArrayList<Reservation> rAll = allTheReservations.getReservationList();
        boolean x = true;
        while (x) {
            ArrayList<Reservation> tempList = new ArrayList<Reservation>();
            if (hpOrdate == 1) {
                System.out.println("##### Remove reservation ######");
                System.out.println("Enter handphone number: (Enter -1 to go back)");
                number = input.nextLine();
                if (number.equals("-1")) {
                    return;
                }
                System.out.println(" number: " + number);
                for (int i = 0; i < rAll.size(); i++) {
                    Reservation temp = rAll.get(i);
                    if (temp.getHpNumber().equals(number)) {
                        tempList.add(temp);
                    }
                }
            } else if (hpOrdate == 0) {
                System.out.println("Input date in dd-mm-yyyy format to delete from on: (Enter -1 to go back)");
                String inputDate = input.next();		//Get input date from user
                if (inputDate.equals("-1")) {
                    return;
                }
                System.out.println("Date: " + inputDate);

                for (int i = 0; i < rAll.size(); i++) {
                    Reservation temp = rAll.get(i);
                    if (temp.getDateReserved().equals(inputDate)) {
                        tempList.add(temp);
                    }
                }
            }
            if (tempList.isEmpty()) {
                System.out.println("There are no reservations under this handphone number/date, or that the format entered is incorrect.");
                return;
            } else {
                System.out.println("Reservations made under this name: ");
                for (int i = 0; i < tempList.size(); i++) {
                    System.out.print(i + 1 + ". ");
                    tempList.get(i).printReservation();
                }
                int choice = 0;
                boolean y = true;
                boolean z = true;
                do {
                    do {
                        try {
                            System.out.println("Select reservation to remove: (Enter -1 to go back)");
                            choice = input.nextInt();
                            System.out.println(SPACING2);
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    if (choice == -1) {
                        return;
                    }
                    try {
                        Reservation todelete = tempList.get(choice - 1);
                        allTheReservations.deleteReservation(todelete);
                        saveReservations();
                        z = false;
                        x = false;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid choice entered.");
                    } catch (IOException ex) {
                        Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } while (z);
            }
        }
    }

    //Converts string given into format to create date object
    /**
     * stringToCalendar method will convert the string date to a calendar date
     * @param format the format pattern
     * @param inputDate the date
     * @param inputTime the time
     * @return the converted date
     * @throws ParseException parsing in the wrong format
     */
    public static Calendar stringToCalender(String format, String inputDate, String inputTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(format);
        Date reservationDate = sdf.parse(inputDate + " " + inputTime);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(reservationDate);

        //System.out.println("Date and time: "+ calendar.getTime());
        return calendar;
    }

    /**
     * checkSlot method will check if the reservation is in the AM or PM
     * session of a given date
     * @param slot the time
     * @return AM or PM
     * @throws ParseException parsing in the wrong format
     */
    public static String checkSlot(String slot) throws ParseException {	//method to check if reservation slot is in AM / PM period
        String timeSlot = "";
        Date slotD = new SimpleDateFormat("HHmm").parse(slot);
        String amStart = "1059";
        String amEnd = "1500";
        String pmStart = "1759";
        String pmEnd = "2300";
        Date amStartD = new SimpleDateFormat("HHmm").parse(amStart);
        Date amEndD = new SimpleDateFormat("HHmm").parse(amEnd);
        Date pmStartD = new SimpleDateFormat("HHmm").parse(pmStart);
        Date pmEndD = new SimpleDateFormat("HHmm").parse(pmEnd);
        if (amStartD.before(slotD) && amEndD.after(slotD)) {
            timeSlot = "AM";
        } else if (pmStartD.before(slotD) && pmEndD.after(slotD)) {
            timeSlot = "PM";
        }
        return timeSlot;
    }

    /**
     * checkSlot method of the current date and checks whether it is in the 
     * AM or PM slot
     * @param now the current date
     * @return AM or PM
     * @throws ParseException parsing in the wrong format 
     */
    public static String checkSlot(Date now) throws ParseException {	//method to check if reservation slot is in AM / PM period
        String timeSlot = "";
        Calendar x = new GregorianCalendar();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        String dateToday = sdf1.format(x.getTime());
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy HHmm");

        String amStart = "1059";
        String amEnd = "1500";
        String pmStart = "1759";
        String pmEnd = "2300";
        Date amStartD = sdf2.parse(dateToday + " " + amStart);
        Date amEndD = sdf2.parse(dateToday + " " + amEnd);
        Date pmStartD = sdf2.parse(dateToday + " " + pmStart);
        Date pmEndD = sdf2.parse(dateToday + " " + pmEnd);
        if (amStartD.before(now) && amEndD.after(now)) {
            timeSlot = "AM";
        } else if (pmStartD.before(now) && pmEndD.after(now)) {
            timeSlot = "PM";
        } else {
            timeSlot = "Closed";
        }
        return timeSlot;
    }

    /**
     * saveReservations method saves the reservations to a file
     * @throws FileNotFoundException the file could not be found
     * @throws IOException writing of the file
     * @throws ClassNotFoundException the class could not be found
     */
    public static void saveReservations() throws FileNotFoundException, IOException, ClassNotFoundException {
        //Testing File IO
        File file = new File("Reservations.txt");

        //replace with existing AllReservations array
        ArrayList<Reservation> reservationsArray = allTheReservations.getReservationList();

        //Serialize collection of menu items
        //Takes File
        FileOutputStream fo = new FileOutputStream(file);
        //Make Object and takes FileOutputStream
        ObjectOutputStream output = new ObjectOutputStream(fo);
        //Write object into file
        for (int i = 0; i < reservationsArray.size(); i++) {
            output.writeObject(reservationsArray.get(i));

        }
        System.out.println("Reservations saved successfully");

        output.close();
        fo.close();
    }

    /**
     * loadReservations method loads all the saved reservations from the file
     * @throws IOException reading of the file
     * @throws ClassNotFoundException the class not found
     */
    public void loadReservations() throws IOException, ClassNotFoundException {
        //Import back into collection
        File file = new File("Reservations.txt");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input1 = new ObjectInputStream(fi);
        ArrayList<Reservation> reservationArray = new ArrayList<Reservation>();

        try {
            while (true) {
                Reservation r = (Reservation) input1.readObject();
                reservationArray.add(r);
            }
        } catch (EOFException ex) {
            //Printing the menu items using displayMenuAndItems()()
            //Print the reservation as well as passing the updated reservation list to reservationall
            allTheReservations.setReservation(reservationArray);
            System.out.println("Reservations loaded successfully");
            //allTheReservations.showAllReservations();
        }

    }

    /**
     * displayMenuOptions method will display all the reservation options
     */
    public void displayMenuOptions() {		//Display main menu options
        System.out.println(SPACING);
        System.out.println("*            RESERVATION OPTIONS             *");
        System.out.println("*  Please select option: (Enter -1 to return)*");
        System.out.println("*  1. Create new reservation                 *");
        System.out.println("*  2. Check reservation                      *");
        System.out.println("*  3. Remove reservation (by Phone Number)   *");
        System.out.println("*  4. Remove reservation (by Date)           *");
        System.out.println("*  5. Show all reservations                  *");
        System.out.println(SPACING);
    }
}
