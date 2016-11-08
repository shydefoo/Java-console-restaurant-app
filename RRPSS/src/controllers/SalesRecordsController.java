package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.OrderSheetPerTable;
import entity.SalesRecords;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SalesRecordsController {

    private SalesRecords recordsOfSales;
    
    private final static String SPACING = "**********************************************";
    private final static String SPACING2 = "######################################";

    public SalesRecordsController() {
        recordsOfSales = new SalesRecords();
    }

    public SalesRecords getRecordsOfSales() {
        return recordsOfSales;
    }

    public void setRecordsOfSales(SalesRecords s) {
        recordsOfSales = s;
    }

    public void run() throws InputMismatchException {
        System.out.println("###########      Sales Records      ##########");
        boolean x = true;
        Scanner input = new Scanner(System.in);
        while (x) {
            boolean y = true;
            this.displayMenuOptions();
            int choice = input.nextInt();
            switch (choice) {
                case (1):
                    do {
                        try {
                            double revenue = this.getRevenueToday();
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;
                case (2):
                    do {
                        try {
                            double revenue = this.getRevenueOnDate(1);
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid option");
                        }
                    } while (y);
                    break;

                case (3):
                    do {
                        try {
                            double revenue = this.getRevenueOnDate(2);
                            y = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input. 3");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("There is no such option!");
                        }
                    } while (y);
                    break;
                case (4):
                    recordsOfSales.printAllSalesRecords();
                    break;
                case (5):
                    try {
                        saveSalesRecord();
                    } catch (IOException ex) {
                        Logger.getLogger(SalesRecordsController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case (6):
                    try {
                        loadSalesRecord();
                    } catch (IOException ex) {
                        Logger.getLogger(SalesRecordsController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SalesRecordsController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case (-1):
                    return;
                default:
                    System.out.println("Please select a valid option");
            }
        }

    }

    public double getRevenueToday() {
        System.out.println("Get revenue today");
        Date today = new GregorianCalendar().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateToCheck = sdf.format(today);
        double revenue = recordsOfSales.CalculateRevenueOnDate(dateToCheck, "dd-MM-yyyy");
        return revenue;
    }

    public double getRevenueOnDate(int choice) {
        Scanner input = new Scanner(System.in);
        boolean x = true;
        String dateToCheck = "";
        double revenue = 0;
        //these variables are need for addItem() method
        //while loop to remain this method if wrong type selected
        if (choice == 1) { //get revenue on date
            System.out.println("Enter specific date in dd-mm-yyyy format: (Enter -1 to go back)");
            dateToCheck = input.next();
            if (dateToCheck.equals("-1")) {
                return revenue;
            }
            revenue = recordsOfSales.CalculateRevenueOnDate(dateToCheck, "dd-MM-yyyy");
        } else if (choice == 2) { //get revenue in month
            System.out.println("Enter specific month in mm-yy format: (Enter -1 to go back)");
            dateToCheck = input.next();
            if (dateToCheck.equals("-1")) {
                return revenue;
            }
            revenue = recordsOfSales.CalculateRevenueOnDate(dateToCheck, "MM-yy");
        }
        return revenue;

    }

    
    public static void saveSalesRecord() throws FileNotFoundException, IOException {
        File file = new File("SalesRecord.txt");
        ArrayList<OrderSheetPerTable> sales = SalesRecords.allTheSalesRecords;
        FileOutputStream fo = new FileOutputStream(file);
        ObjectOutputStream output = new ObjectOutputStream(fo);

        for (int i = 0; i < sales.size(); i++) {
            output.writeObject(sales.get(i));
        }

        System.out.println("Sales saved Successfully");
        output.close();
        fo.close();
    	
    }

    public void loadSalesRecord() throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("SalesRecord.txt");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input1 = new ObjectInputStream(fi);

        ArrayList<OrderSheetPerTable> sales = new ArrayList<OrderSheetPerTable>();

        try {
            while (true) {
                OrderSheetPerTable o = (OrderSheetPerTable) input1.readObject();
                sales.add(o);
            }
        } catch (EOFException ex) {
            recordsOfSales.setSalesRecords(sales);

            System.out.println("Sales loaded Successfully");
        }
    	

    }

    public void displayMenuOptions() {
        System.out.println(SPACING);
        
        System.out.println("*    Select option: (Enter -1 to go back)    *");
        System.out.println("*    1. Calculate Revenue today              *");
        System.out.println("*    2. Calculate Revenue on specific date   *");
        System.out.println("*    3. Calculate Revenue in specific month  *");
        System.out.println("*    4. See all sales records                *");
        System.out.println(SPACING);
        //System.out.println("5. Save Records");
        //System.out.println("6. Load Records");
    }
}
