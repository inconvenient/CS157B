package PizzaProject;

import java.util.ArrayList;
import java.util.List;

public interface PizzaShopDAO {

	User createUser(User user);

	User login(String username, String password);

	void createOrder(User user, Order order);
	
	void createDiscountedOrder(User user, DiscountedOrder order);

	List viewOrder(User user);

	void changeOrder(User user, int orderID, Order order);

	int cancelOrder(User user, int orderID);

}
