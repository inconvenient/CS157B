package PizzaProject;

import javax.persistence.*;

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
	private double price;

	@Column(name = "DELIVERY_TIME")
	@Temporal(TemporalType.DATE)
	private Date deliveryTime;

	@Enumerated
	@Column(name = "PAYMENT_METHOD")
	private PaymentMethod paymentMethod;

	@Enumerated
	@Column(name = "SIZE")
	private PizzaSize size;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "order_topping", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "topping"))
	@Column(name = "TOPPINGS")
	private List<Topping> toppings = new ArrayList<Topping>();

	public enum PaymentMethod {
		CASH, VISA, MASTER;

	}

	public enum PizzaSize {

		SMALL(3), MEDIUM(5), LARGE(7), 
		dSMALL(2.7), dMEDIUM(4.5), dLARGE(6.3);
		private double cost;

		PizzaSize(double cost) {
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
		this.price = size.cost;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getPrice() {
		return size.cost;
	}

	public void setPrice(double price) {
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

	public void setTopping(List<Topping> toppings) {
		this.toppings = toppings;
	}

	public List<Topping> getToppings() {
		return this.toppings;
	}

	public void addUser(User u) {
		this.user = u;
	}

}
