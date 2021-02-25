/*****************************
 * Infraction.java 
 * Author: Karl Damus 
 * ©2021, All Rights Reserved
 *****************************/

import java.util.Date;

public class Infraction {

	float amount;
	String description;
	Date dateIssued;
	boolean outstanding;

	Driver driver;


	public Infraction() {
		this(0.0f, "", new Date(01/01/1970));
	}

	public Infraction(float amount, String description, Date dateIssued) {
		this.amount = amount;
		this.description = description;
		this.dateIssued = dateIssued;

		this.outstanding = true; // if it is a new infraction it has to be outstanding
	}

	public void pay() {
		this.outstanding = false;
	}


	@Override
	public String toString() {
		String outstandingString;

		if (outstanding) { 
			outstandingString = " [OUTSTANDING] "; } 
		else { 
			outstandingString = " [PAID IN FULL] "; }

		return (
		"$" + amount
		+ " Infraction on " + dateIssued
		+ outstandingString
		);
	}

}

// "$100.00 Infraction on Sun Jul 14 07:08:00 EDT 2002 [PAID IN FULL]"