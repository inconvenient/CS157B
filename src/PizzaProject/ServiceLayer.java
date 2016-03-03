package PizzaProject;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
				System.out.println("Logged in successfully.");
				loggedIn = true;
			} else {
				System.out.println("Error logging in. Please try again.");
			}
		}
		return user;
	}

	public Order createOrder() {
		System.out.println("Please select 3 toppings separated by spaces. Ex: 1 2 3");
		System.out.println("1.Pepperoni\n2.Mushrooms\n3.Onions\n4.Sausage\n5.Bacon");
		System.out.println("6.Extra Cheese\n7.Black Olives\n8.Green Peppers\n9.Pineapple\n10.Spinach");
		String input = in.nextLine();
		// Parsing user input
		List<Integer> choices = new ArrayList<Integer>();
		for (String choice : input.split(" ")) {
			if (choice.matches("[0-9]")) {
				choices.add(Integer.parseInt(choice));
			} else {
				System.out.println("Error with choices. Please try again");
			}
		}
		// Take choices and create topping objects to place into Order object.

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
