package PizzaProject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findByUsername", query = "from User where username = :username")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int userId;

	@Column(unique = true, name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "ADDRESS")
	private Address address;
	@OneToMany(mappedBy = "user", targetEntity = Order.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(name = "ORDERS")
	private List<Order> orders = new ArrayList<Order>();

	public User() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void addOrder(Order order) {
		this.orders.add(order);
	}

	public List<Order> getOrder() {
		return this.orders;
	}

	public String toString() {
		return this.username;
	}

}
