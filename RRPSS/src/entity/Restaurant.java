package entity;

import controllers.MenuController;
import controllers.OrderController;
import controllers.ReservationController;
import controllers.SalesRecordsController;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import controllers.TableController;

import java.io.EOFException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Restaurant class is the overall controller of the rrpss system 9/11/2016
 *
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 *
 */
public class Restaurant {

    /**
     *  The constant spacing for menu
     */
    private final static String SPACING = "**********************************************";
    
    /**
     * The constant spacing for user input
     */
    private final static String SPACING2 = "##############################################";

    /**
     * Main Method will create the constructors for all the restaurant constants
     * @throws FileNotFoundException if the file cannot be found
     * @throws IOException if there is a problem with the writing and reading of
     * the file
     * @throws ClassNotFoundException class could not be found when loading
     * @param args  main method argument
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println(SPACING2);
        System.out.println("Program Started...");
        Scanner input = new Scanner(System.in);
        MenuController mC = new MenuController();
        ReservationController reserveC = new ReservationController();
        TableController tableC = new TableController();
        OrderController orderC = new OrderController();
        SalesRecordsController sRecordsC = new SalesRecordsController();

        mC.loadMenu();
        reserveC.loadReservations();
        sRecordsC.loadSalesRecord();
        boolean x = true;
        while (x) {
            // displayMenu();
            boolean y;
            int choice = 0;
            try {
                tableC.run();
                
            System.out.println(SPACING2);
            } catch (InputMismatchException e) {
                System.out.println("Error synching tables with reservations.");
            }
            do {
                y = true;
                try {
                    displayMenu();
                    choice = input.nextInt();
                    y = false;
                } catch (InputMismatchException e) {
                    System.out.println("Please choose a valid option.");
                    input.nextLine();
                }

            } while (y);
            switch (choice) {
                case (1):
                    try {
                        orderC.run();
                    } catch (InputMismatchException e) {
                        System.out.println("Error caught by MenuTestingApp");
                        input.next();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid input. Try again");
                        input.nextLine();
                    }
                    break;
                case (2):
                    try {
                        mC.run();
                    } catch (InputMismatchException e) {
                        System.out.println("Error caught by MenuTestingApp");
                    }
                    break;
                case (3):
                    try {
                        reserveC.run();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid option.");
                        input.nextLine();
                    }
                    break;
                case (4):

                    break;
                case (5):
                    try {
                        sRecordsC.run();
                    } catch (InputMismatchException e) {
                        System.out.println("Error caught by MenuTestingApp2");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");

            }

        }
    }

    public static void displayMenu() {
        System.out.println(SPACING);
        System.out.println("*  THE EMPERIAL FORTUNE COOKIES RESTAURANT   *");
        System.out.println("*    1. Order                                *");
        System.out.println("*    2. Menu                                 *");
        System.out.println("*    3. Reservations                         *");
        System.out.println("*    4. Check table availability             *");
        System.out.println("*    5. Sales Records                        *");
        System.out.println(SPACING);
    }
}
