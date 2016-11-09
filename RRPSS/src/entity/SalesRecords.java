package entity;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import controllers.ReservationController;
import java.text.DecimalFormat;

/**
 * SalesRecords class contains all the sales records
 * @since 9/11/2016
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class SalesRecords {

    /**
     * allTheSalesRecords ArrayList collates all the completed orders
     */
    public static ArrayList<OrderSheetPerTable> allTheSalesRecords;
    
    /**
     * SPACING2 for formatting user input
     */
    private final static String SPACING2 = "##############################################";

    /**
     * SalesRecords Constructor will create a new ArrayList of allTheSalesRecords
     */
    public SalesRecords() {
        allTheSalesRecords = new ArrayList<OrderSheetPerTable>();

    }

    /**
     * getSalesRecords method will retrieve all the recorded sales
     * @return all the recorded sales
     */
    public ArrayList<OrderSheetPerTable> getSalesRecords() {
        return allTheSalesRecords;
    }

    /**
     * setSalesRecords assigns the recorded sales into allTheSalesRecords
     * @param sales the assigned recorded sales
     */
    public void setSalesRecords(ArrayList<OrderSheetPerTable> sales) {
        allTheSalesRecords = sales;
    }

    /**
     * printAllSalesRecords method will display the recorded sales
     */
    public void printAllSalesRecords() {
        for (int i = 0; i < allTheSalesRecords.size(); i++) {
            OrderSheetPerTable tempOrderSheet = allTheSalesRecords.get(i);
            tempOrderSheet.printOrderSheetDetails();
        }
    }

    /**
     * QuerySalesRecordsOnDate method will search sales records by date
     * @param dateToCheck the date that will be queried
     * @param format of the date
     * @return the sales records by date
     */
    public ArrayList<OrderSheetPerTable> QuerySalesRecordsOnDate(String dateToCheck, String format) {
        ArrayList<OrderSheetPerTable> tempRecords = new ArrayList<OrderSheetPerTable>();
        for (int i = 0; i < allTheSalesRecords.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String dateInvoiceString = sdf.format(allTheSalesRecords.get(i).getInvoiceDateTime());
            if (dateToCheck.equals(dateInvoiceString)) {
                tempRecords.add(allTheSalesRecords.get(i));
            }
        }
        if (tempRecords.isEmpty()) {
            System.out.println("There were no records on this day or the format of the date/month entered is incorrect. Please ensure that correct date/month format is used.");
        }
        return tempRecords;
    }

    /**
     * CalculateRevenueOnDate will tally all the sales records by date
     * @param dateToCheck the date that will be queried
     * @param format of the date
     * @return  the total sales records by date
     */
    public double CalculateRevenueOnDate(String dateToCheck, String format) {
        double revenue = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        ArrayList<Double> breakDown = new ArrayList<Double>();
        ArrayList<OrderSheetPerTable> tempRecords = QuerySalesRecordsOnDate(dateToCheck, format);
        if (!(tempRecords.isEmpty())) {
            for (int i = 0; i < tempRecords.size(); i++) {
                tempRecords.get(i).printOrderSheetDetails();
                revenue += tempRecords.get(i).getTotalBill();
            }
            
            System.out.println("Time period: " + dateToCheck);
            System.out.printf("###### Revenue in " + dateToCheck + "   "+ new DecimalFormat("$###,##0.00").format(revenue) + " #######");
            System.out.println("");
            System.out.println(SPACING2);

        }
        return revenue;
    }

}
