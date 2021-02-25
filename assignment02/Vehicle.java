/*****************************
 * Vehicle.java
 * Author: Karl Damus
 * ©2021, All Rights Reserved
*****************************/

public class Vehicle {

	Vehicle vehicle;

	String make;
	String model;
	int year;
	String color;
	String plate;
	boolean reportedStolen;

	Driver owner;

	public static void main(String[] args) {
		Vehicle vehicle = new Vehicle();
		System.out.println(vehicle);
	}

	public Vehicle() {
		// Driver class called with no args given. Create new EMPTY driver -- no NULL values
		this("", "", 0, "", "");
	}

	public Vehicle(String make, String model, int year, String color, String plate) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.plate = plate;
		this.reportedStolen = false;
	}

	@Override
	public String toString() {
		return (
		"A " + this.color + " "
		+ this.year + " " + this.make + " " + this.model
		+ " with plate " + this.plate );
	}
}

