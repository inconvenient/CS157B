package PizzaProject;

import java.util.ArrayList;

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
	@Column(name = "ORDERS")
	private ArrayList<Order> orders;

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

	public ArrayList<Order> getOrder() {
		return this.orders;
	}

	public String toString() {
		return this.username;
	}

}
