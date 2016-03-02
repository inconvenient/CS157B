package PizzaProject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Order {

	private enum PaymentMethod {
		CASH, VISA, MASTER
	}

	private enum PizzaSize {

		SMALL(3), MEDIUM(5), LARGE(5);
		private int cost;

		PizzaSize(int cost) {
			this.cost = cost;
		}
	}

	@Column(name = "ORDER_ID")
	private int orderId;
	@Column(name = "PRICE")
	private int price;
	@Column(name = "DELIVERY_TIME")
	private Date deliveryTime;
	@Column(name = "PAYMENT_METHOD")
	private PaymentMethod paymentMethod;
	@Column(name = "SIZE")
	private PizzaSize size;

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PizzaSize getSize() {
		return size;
	}

	public void setSize(PizzaSize size) {
		this.size = size;
	}

	public PaymentMethod getPm() {
		return paymentMethod;
	}

	public void setPm(PaymentMethod pm) {
		this.paymentMethod = pm;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getPrice() {
		return size.cost;
	}

	public void setPrice(int price) {
		this.size.cost = price;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

}
