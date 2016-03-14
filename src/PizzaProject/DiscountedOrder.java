package PizzaProject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import PizzaProject.Order.PizzaSize;

@Entity
public class DiscountedOrder extends Order {

	private static int discountRate = 10; // 10 Percent

	public DiscountedOrder() {

	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		DiscountedOrder.discountRate = discountRate;
	}
}
