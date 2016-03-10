package PizzaProject;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Embeddable
public class Topping {

	@Id
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
