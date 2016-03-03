package PizzaProject;

import java.util.Scanner;

public class PresentationLayer {

	// Variables
	static int option;
	static boolean loggedIn = false;
	static boolean exit = false;
	static Scanner in = new Scanner(System.in);
	static User currentUser = null;

	// Service Layer
	static ServiceLayer sl = new ServiceLayer();

	// Main
	public static void main(String[] args) {
		// Sign up or Login use case
		System.out.println("Welcome to Mr.Flaaf's Pizzeria. \nType 1 to log in or type 2 to register.");
		System.out.println("Option:");
		// Login/Register loop
		while (!loggedIn) {
			option = in.nextInt();
			if (option == 1) {
				currentUser = sl.login();
				if (currentUser != null) {
					loggedIn = true;
				}
			} else if (option == 2) {
				currentUser = sl.createUser();
				if (currentUser != null) {
					loggedIn = true;
					System.out.println("You are now logged in.");
				}

			} else {
				System.out.println("Not an option, please enter 1 to log in or 2 to register.");
			}
		}
		// User actions
		while (!exit) {
			int choice;
			System.out.println("-----------------------------");
			System.out.println("What would you like to do?");
			System.out.println("1. Make an order");
			System.out.println("2. Change an order");
			System.out.println("3. Cancel an order");
			System.out.println("4. View all orders");
			System.out.println("5. Exit");
			choice = in.nextInt();
			switch (choice) {
			case 1:
				sl.createOrder();
			case 2:
				sl.changeOrder();
			case 3:
				sl.cancelOrder();
			case 4:
				sl.viewOrders();
			case 5:
				exit = true;
				System.exit(0);
			default:
				System.out.println("Not an option. Please choose again");
			}

		}

	}
}
