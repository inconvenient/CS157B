package Test;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	// @Column(name = "STREET_NAME")
	private String streetName;
	// @Column(name = "CITY_NAME")
	private String city;
	// @Column(name = "STATE_NAME")
	private String state;
	// @Column(name = "ZIPCODE_NAME")
	private String zipcode;

	public String getStreetName() {
		return streetName;
	}

	public void setStreet(String street) {
		this.streetName = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
		;
	}
}
