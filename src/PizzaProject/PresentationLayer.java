package PizzaProject;

import java.util.Scanner;

public class PresentationLayer {

	// Variables
	static int option;
	static boolean loggedIn = false;
	static Scanner in = new Scanner(System.in);

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
				loggedIn = true;
				sl.login();
			} else if (option == 2) {
				loggedIn = true;
				sl.createUser();
			} else {
				System.out.println("Not an option, please enter 1 to log in or 2 to register.");
			}
		}

	}
}
