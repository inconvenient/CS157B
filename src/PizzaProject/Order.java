package PizzaProject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Embeddable
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private int orderId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER")
	private User user;
	@Column(name = "PRICE")
	private int price;
	@Column(name = "DELIVERY_TIME")
	private Date deliveryTime;
	@Column(name = "PAYMENT_METHOD")
	private PaymentMethod paymentMethod;
	@Column(name = "SIZE")
	private PizzaSize size;
	@Column(name = "TOPPINGS")
	@ElementCollection(targetClass = Topping.class)
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

}
