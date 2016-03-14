package PizzaProject;

import java.util.List;
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

		// Prepare toppings table
		sl.prepareToppings();

		// User actions
		while (!exit) {
			int choice;
			System.out.println("-----------------------------");
			System.out.println("What would you like to do?");
			System.out.println("1. Make an order");
			System.out.println("2. Change an order");
			System.out.println("3. Cancel an order");
			System.out.println("4. View all orders");
			System.out.println("5. Make a discounted order");
			System.out.println("6. Exit");
			choice = in.nextInt();
			switch (choice) {
			case 1:
				sl.createOrder(currentUser);
				break;
			case 2:
				List<Order> orders2 = sl.viewOrders(currentUser);
				System.out.println("Here are your orders.");
				System.out.println("---------------------------------");
				for (Order o : orders2) {
					System.out.println("Order ID: " + o.getOrderId());
					System.out.println("Delivery Time: " + o.getDeliveryTime());
					System.out.println("Price: " + o.getPrice());
					System.out.println("Size: " + o.getSize());
					System.out.print("Toppings: ");
					for (Topping t : o.getToppings()) {
						System.out.print(t.toString() + " ");
					}
					System.out.println();
					System.out.println("Payment Method: " + o.getPaymentMethod());
					System.out.println("---------------------------------");
				}
				System.out.println("Which order would you like to change?");
				int orderID1 = in.nextInt();
				sl.changeOrder(currentUser, orderID1);
				break;
			case 3:
				List<Order> orders3 = sl.viewOrders(currentUser);
				System.out.println("Here are your orders.");
				System.out.println("---------------------------------");
				for (Order o : orders3) {
					System.out.println("Order ID: " + o.getOrderId());
					System.out.println("Delivery Time: " + o.getDeliveryTime());
					System.out.println("Price: " + o.getPrice());
					System.out.println("Size: " + o.getSize());
					System.out.print("Toppings: ");
					for (Topping t : o.getToppings()) {
						System.out.print(t.toString() + " ");
					}
					System.out.println();
					System.out.println("Payment Method: " + o.getPaymentMethod());
					System.out.println("---------------------------------");
				}
				System.out.println("Which order would you like to cancel?");
				int orderID2 = in.nextInt();
				int successes = sl.cancelOrder(currentUser, orderID2);
				if (successes == 0) {
					System.out.println("Unsuccessful cancellation, please try again.");
				} else {
					System.out.println("Successfully deleted " + successes + " orders.");
				}
				break;
			case 4:
				List<Order> orders4 = sl.viewOrders(currentUser);
				System.out.println("Here are your orders.");
				System.out.println("---------------------------------");
				for (Order o : orders4) {
					System.out.println("Order ID: " + o.getOrderId());
					System.out.println("Delivery Time: " + o.getDeliveryTime());
					System.out.println("Price: " + o.getPrice());
					System.out.println("Size: " + o.getSize());
					System.out.print("Toppings: ");
					for (Topping t : o.getToppings()) {
						System.out.print(t.toString() + " ");
					}
					System.out.println();
					System.out.println("Payment Method: " + o.getPaymentMethod());
					System.out.println("---------------------------------");
				}
				break;
			case 5:
				sl.createDiscountedOrder(currentUser);
				break;
			case 6:
				exit = true;
				System.out.println("Bye");
				System.exit(0);
			default:
				System.out.println("Not an option. Please choose again");
				break;
			}

		}

	}
}
