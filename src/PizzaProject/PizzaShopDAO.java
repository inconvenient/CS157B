package PizzaProject;

import java.util.ArrayList;

public interface PizzaShopDAO {

	User createUser(User user);

	User login(String username, String password);

	Order createOrder(Order order);

	ArrayList<Order> viewOrder();

	Order changeOrder(Order order);

	boolean cancelOrder(Order order);

}
