package PizzaProject;

import java.util.ArrayList;
import java.util.List;

public interface PizzaShopDAO {

	User createUser(User user);

	User login(String username, String password);

	void createOrder(User user, Order order);

	List viewOrder(User user);

	Order changeOrder(Order order);

	boolean cancelOrder(Order order);

}
