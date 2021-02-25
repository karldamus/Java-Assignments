/*****************************
 * Driver.java
 * Author: Karl Damus
 * ©2021, All Rights Reserved
*****************************/

public class Driver {

	Driver driver;

	String license;
	String name;
	String street;
	String city;
	String province;

	public static void main(String[] args) {
		Driver driver = new Driver();
		System.out.println(driver.toString());
	}

	public Driver() {
		// Driver class called with no args given. Create new EMPTY driver -- no NULL values
		this("", "", "", "", "");
	}

	public Driver(String license, String name, String street, String city, String province) {
		this.license = license;
		this.name = name;
		this.street = street;
		this.city = city;
		this.province = province;
	}

	@Override
	public String toString() {
		return (
		"#" + this.license + " "
		+ this.name
		+ " living at " + this.street + "., "
		+ city + ", " + province );
	}
}
