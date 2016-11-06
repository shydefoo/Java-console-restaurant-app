package entity;

import java.util.ArrayList;

import java.util.Date;

public class ReservationAll {

    private ArrayList<Reservation> reservationList;

    //Constructor
    public ReservationAll() {
        reservationList = new ArrayList<Reservation>();
    }

    /**
     *
     * @param tableNumber the number assigned to the table
     * @param numberOfPeople for how many people
     * @param hpNumber handphone number of customer
     * @param customerName name of customer
     * @param start date and time the table is being reserved for
     */
    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservation(ArrayList<Reservation> reservation) {
        this.reservationList = reservation;
    }

    public void createReservation(int tableNumber, int numberOfPeople, String hpNumber, String customerName, String dateReserved, String timeOfArrival, String slot) {
        Reservation newRes = new Reservation(tableNumber, numberOfPeople, hpNumber, customerName, dateReserved, timeOfArrival, slot);
        reservationList.add(newRes);
        System.out.println("Reservation made");
        newRes.printReservation();
    }

    /**
     *
     * @param todelete Reservation object to remove from reservationList
     */
    public void deleteReservation(Reservation todelete) {
        int msg = 0;
        for (int i = 0; i < reservationList.size(); i++) {
            if (reservationList.get(i) == todelete) {
                reservationList.remove(i);
                msg = 1;
            }
        }
        if (msg == 1) {
            System.out.println("Reservation has been removed.");
        } else {
            System.out.println("Error. No changes have been made");
        }

    }

    /**
     *
     * @param input date object to check which tables are being reserved.
     */
    public void checkReservationsOnDate(String input) {
        System.out.println("Date to check: " + input);
        ArrayList<Reservation> tempList = new ArrayList<Reservation>();
        for (int i = 0; i < reservationList.size(); i++) {
            Reservation temp = reservationList.get(i);
            if (temp.getDateReserved().equals(input)) {
                tempList.add(temp); //In case customer makes multiple reservations.
            }
        }
        if (tempList.isEmpty()) {
            System.out.println("There are no reservations on " + input + ". ");
        } else {
            System.out.println("Reservations made on: " + input);
            for (int i = 0; i < tempList.size(); i++) {
                System.out.print(i + 1 + ". ");
                tempList.get(i).printReservation();
            }
        }

    }

    public ArrayList<Integer> reservedTablesOnDate(String input, String slot) {
        //System.out.println("Input date:" + input);
        ArrayList<Integer> reservedTables = new ArrayList<Integer>();
        ArrayList<Reservation> tempList = reservationsMadeOnDate(input, slot);
        //System.out.println("Reservations made on: " + input);
        if (!(tempList.isEmpty())) {
            for (int i = 0; i < tempList.size(); i++) {
                int x = tempList.get(i).getTableNumber();
                reservedTables.add(x);
            }
        }
        return reservedTables;
    }

    public ArrayList<Reservation> reservationsMadeOnDate(String input, String slot) {
        //System.out.println("Input date:" + input);
        ArrayList<Reservation> tempList = new ArrayList<Reservation>();
        for (int i = 0; i < reservationList.size(); i++) {
            Reservation temp = reservationList.get(i);
            if (temp.getDateReserved().equals(input) && (temp.getSlot().equals(slot))) {
                tempList.add(temp); //In case customer makes multiple reservations.
            }
        }
        if (tempList.isEmpty()) {
            //System.out.println("There are no reservations on " + input + ". ");
        }

        return tempList;
    }

    public void showAllReservations() {
        for (int i = 0; i < reservationList.size(); i++) {
            reservationList.get(i).printReservation();
        }
    }

}
