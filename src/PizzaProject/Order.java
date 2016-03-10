package PizzaProject;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private int orderId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERNAME")
	private User user;

	@Column(name = "PRICE")
	private int price;

	@Column(name = "DELIVERY_TIME")
	@Temporal(TemporalType.DATE)
	private Date deliveryTime;

	@Column(name = "PAYMENT_METHOD")
	// @Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	@Column(name = "SIZE")
	// @Enumerated(EnumType.ORDINAL)
	private PizzaSize size;

	@Embedded
	@Column(name = "TOPPINGS")
	private List<Topping> toppings = new ArrayList<Topping>();

	enum PaymentMethod {
		CASH, VISA, MASTER
	}

	enum PizzaSize {

		SMALL(3), MEDIUM(5), LARGE(7);
		private int cost;

		PizzaSize(int cost) {
			this.cost = cost;
		}
	}

	public Order() {

	}

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

	public void addTopping(Topping topping) {
		toppings.add(topping);
	}

	public void removeTopping(Topping topping) {
		toppings.remove(topping);
	}

	public void addUser(User u) {
		this.user = u;
	}

}
