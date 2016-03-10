package PizzaProject;

import javax.persistence.Embeddable;

@Embeddable
public class Topping {

	private String topping;

	public Topping(String toppingName) {
		this.topping = toppingName;
	}

	public String getTopping() {
		return topping;
	}

	public void setTopping(String topping) {
		this.topping = topping;
	}

}
