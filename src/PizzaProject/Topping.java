package PizzaProject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topping {

	@Id
	@Column(name = "topping")
	private String topping;

    public Topping() {

    }
	public Topping(String toppingName) {
		this.topping = toppingName;
	}

	public String getTopping() {
		return topping;
	}

	public void setTopping(String topping) {
		this.topping = topping;
	}

    @Override
    public String toString() {
        return this.topping;
    }

}
