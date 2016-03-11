package PizzaProject;

import java.util.ArrayList;

public interface PizzaShopDAO {

	User createUser(User user);

	User login(String username, String password);

	void createOrder(User user, Order order);

	ArrayList<Order> viewOrder();

	Order changeOrder(Order order);

	boolean cancelOrder(Order order);

}
