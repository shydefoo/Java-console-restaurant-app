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

public class MenuTestingApp {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Start of program");
		Scanner input = new Scanner(System.in);
		MenuController mC = new MenuController();
		ReservationController reserveC = new ReservationController();
		TableController tableC = new TableController();
		OrderController orderC = new OrderController();
		SalesRecordsController sRecordsC = new SalesRecordsController();

		mC.loadMenu();
		reserveC.loadReservations();
        sRecordsC.loadSalesRecord();
        try {
			tableC.run();
		} catch (InputMismatchException e) {
			System.out.println("Error caught by MenuTestingApp2");
		}
		boolean x = true;
		while (x) {
			// displayMenu();
			boolean y;
			int choice = 0;
			
			do{
				y = true;
				try{
					displayMenu();
					choice = input.nextInt();
					y = false;
				}catch(InputMismatchException e){
					System.out.println("Please choose a valid option.");
				}
				
			}while(y);
			switch(choice){
				case(1):
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
				case(2):
					try {

						mC.run();
					} catch (InputMismatchException e) {
						System.out.println("Error caught by MenuTestingApp2");
					}
				break;
				case(3):
					try {
						reserveC.run();

					} catch (InputMismatchException e) {
						System.out.println("Error caught by MenuTestingApp");
						input.next();

					}
				break;
				case(4):
					
					break;
				case(5):
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
		System.out.println("Bae restaurant Menu. Please select your action");
		System.out.println("1. Order");
		System.out.println("2. Menu");
		System.out.println("3. Reservations");
		System.out.println("4. Check table availability");
		System.out.println("5. Sales Records.");

	}
}
