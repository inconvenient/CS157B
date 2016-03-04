package PizzaProject;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import PizzaProject.Order.PizzaSize;

public class ServiceLayer {

	ConcretePizzaShopDAO DAO = new ConcretePizzaShopDAO();

	Scanner in = new Scanner(System.in);

	public User createUser() {
		boolean userCreated = false;
		User user = null;
		while (!userCreated) {
			User newUser = new User();
			Address address = new Address();
			System.out.println("Enter your username: ");
			String username = in.nextLine();
			System.out.println("Enter your password: ");
			String pw = in.nextLine();
			System.out.println("Enter your street name: ");
			String street = in.nextLine();
			System.out.println("Enter your city: ");
			String city = in.nextLine();
			System.out.println("Enter your state: ");
			String state = in.nextLine();
			System.out.println("Enter your zipcode: ");
			String zip = in.nextLine();
			address.setStreet(street);
			address.setZipcode(zip);
			address.setState(state);
			address.setCity(city);
			newUser.setUsername(username);
			newUser.setPassword(pw);
			newUser.setAddress(address);
			// Transfer control to DAO
			user = DAO.createUser(newUser);
			if (user != null) {
				System.out.println("User created succesfully.");
				userCreated = true;
			} else {
				System.out.println("Error creating user. Please try again.");
			}
		}
		return user;
	}

	public User login() {
		boolean loggedIn = false;
		User user = null;
		while (!loggedIn) {
			System.out.println("Please enter your username: ");
			String username = in.nextLine();
			System.out.println("Please enter your password: ");
			String password = in.nextLine();
			// Transfer control to DAO
			user = DAO.login(username, password);
			if (user != null) {
				System.out.println("Logged in successfully as " + user.toString() + ".");
				loggedIn = true;
			} else {
				System.out.println("Error logging in. Please try again.");
				login();
			}
		}
		return user;
	}

	public Order createOrder(User user) {
		// Main Order Components
		boolean orderMade = false;
		boolean toppingsMade = false;
		boolean pickedSize = false;
		boolean pickedPayment = false;

		// User selection loop booleans
		boolean toppingError = false;
		boolean addToppingError = false;
		boolean toppingApprove = false;

		List<Integer> choices = null;
		Order order = new Order();
		// Parsing user input
		while (!orderMade) {
			// Pick size loop
			while (!pickedSize) {
				System.out.println("What size pizza would you like?");
				System.out.println("1. Small\n2. Medium\n3. Large");
				String input = in.nextLine();
				if (input.matches("[1-3]")) {
					int size = Integer.parseInt(input);
					switch (size) {
					case 1:
						order.setSize(Order.PizzaSize.SMALL);
						break;
					case 2:
						order.setSize(Order.PizzaSize.MEDIUM);
						break;
					case 3:
						order.setSize(Order.PizzaSize.LARGE);
						break;
					}
					pickedSize = true;
				} else {
					System.out.println("Error, please pick a valid size");
					continue;
				}
			}
			// Pick toppings loop
			while (!toppingsMade) {
				toppingsMade = false;
				toppingError = false;
				addToppingError = false;
				System.out.println("Please select 3 toppings separated by spaces. Ex: 1 2 3");
				System.out.println("1.Pepperoni\n2.Mushrooms\n3.Onions\n4.Sausage\n5.Bacon");
				System.out.println("6.Extra Cheese\n7.Black Olives\n8.Green Peppers\n9.Pineapple\n10.Spinach");
				List<Topping> toppings;
				String input = in.nextLine();
				choices = new ArrayList<Integer>();
				for (String parsedChoice : input.split(" ")) {
					if (parsedChoice.matches("10|[0-9]")) {
						choices.add(Integer.parseInt(parsedChoice));
					} else {
						System.out.println("Error adding choices to order. Please try again.");
						toppingError = true;
					}
				}
				// Error/Toppings Check
				if (toppingError) {
					continue;
				} else if (choices.size() > 3) {
					continue;
				} else {
					toppings = new ArrayList<Topping>();
					for (int choice : choices) {
						switch (choice) {
						case 1:
							toppings.add(new Topping("Pepperoni"));
							break;
						case 2:
							toppings.add(new Topping("Mushrooms"));
							break;
						case 3:
							toppings.add(new Topping("Onions"));
							break;
						case 4:
							toppings.add(new Topping("Sausage"));
							break;
						case 5:
							toppings.add(new Topping("Bacon"));
							break;
						case 6:
							toppings.add(new Topping("Extra Cheese"));
							break;
						case 7:
							toppings.add(new Topping("Black Olives"));
							break;
						case 8:
							toppings.add(new Topping("Green Peppers"));
							break;
						case 9:
							toppings.add(new Topping("Pineapple"));
							break;
						case 10:
							toppings.add(new Topping("Spinach"));
							break;
						default:
							System.out.println("Error inserting a topping. Please check your choices again.");
							addToppingError = true;
							break;
						}
					}
					// If something went wrong with adding toppings, restart.
					if (addToppingError) {
						continue;
					}
				}
				// Maybe put this in its own while loop...
				// Take choices and create topping objects to place into Order
				int confirmNum = 0;
				while (!toppingApprove) {
					System.out.println("Are these the correct toppings?");
					for (Topping t : toppings) {
						// Fix this toString
						System.out.println(t.toString());
					}
					System.out.println("1. Yes\n2. No");
					String toppingConfirm = in.nextLine();
					if (toppingConfirm.matches("[1-2]")) {
						confirmNum = Integer.parseInt(toppingConfirm);
						toppingApprove = true;
					} else {
						// Need to ask the user again for option.
						System.out.println("Not a valid option. Please try again;");
						continue;
					}
				}
				switch (confirmNum) {
				case 1:
					for (Topping t : toppings) {
						order.addTopping(t);
					}
					toppingsMade = true;
					System.out.println("Success! Toppings have been added.");
				case 2:
					continue;
				default:
					// Bad way to handle this... FIX!
					System.out.println("Error, please select either 1 (Yes) or 2 (No)");
				}
			}
			// Pick payment type loop
			while (!pickedPayment) {
				System.out.println("Please choose your payment method.");
				int i = 1;
				for (Order.PaymentMethod p : Order.PaymentMethod.values()) {
					System.out.println(i + ". " + p);
					i++;
				}
				// Need to type check here for future ref...
				String stringIn = in.nextLine();
				if (stringIn.matches("[1-3]")) {
					int input = Integer.parseInt(stringIn);
					switch (input) {
					case 1:
						order.setPaymentMethod(Order.PaymentMethod.CASH);
						pickedPayment = true;
						break;
					case 2:
						order.setPaymentMethod(Order.PaymentMethod.VISA);
						pickedPayment = true;
						break;
					case 3:
						order.setPaymentMethod(Order.PaymentMethod.MASTER);
						pickedPayment = true;
						break;
					default:
						System.out.println("Error. Please pick a valid payment option");
					}
				} else {
					continue;
				}
				System.out.println("Payment method chosen.");
			}
			// Finalize order and call DAO.
		}

		return null;

	}

	public boolean cancelOrder() {
		return false;

	}

	public Order changeOrder() {
		return null;

	}

	public ArrayList<Order> viewOrders() {
		return null;

	}
}
