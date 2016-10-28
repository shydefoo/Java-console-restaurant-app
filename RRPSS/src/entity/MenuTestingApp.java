package entity;
import controllers.MenuController;
import controllers.ReservationController;

import java.util.InputMismatchException;
import java.util.Scanner;


public class MenuTestingApp {
	public static void main(String[] args){
		System.out.println("Start of program");
		Scanner input = new Scanner(System.in);
		MenuController mC = new MenuController();
		ReservationController reserveC = new ReservationController();
		boolean x = true;
		while(x){
			try{
				mC.run();
			}
			catch(InputMismatchException e){
				System.out.println("Error caught by MenuTestingApp");
				x = false;
			}
			try{
				reserveC.run();
			}
			catch(InputMismatchException e){
				System.out.println("Error caught by MenuTestingApp2");
			}
		}
			
		}
}
