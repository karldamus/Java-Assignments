/*****************************
 * PoliceDatabase.java 
 * Author: Karl Damus 
 * ©2021, All Rights Reserved
 *****************************/

/**
 * TODO:
 * ----------
 * * Solve error messages from PoliceDatabaseTestProgram + 2
 */

import java.util.Date;

public class PoliceDatabase {
	
	private static final int driverLimit = 2000;
	private static final int vehicleLimit = 1000;
	private static final int infractionLimit = 800;

	public Vehicle[] vehicles = new Vehicle[vehicleLimit];
	public Driver[] drivers = new Driver[driverLimit];
	public Infraction[] infractions = new Infraction[infractionLimit];

	public int numDrivers = getNumberOfObjectsInArray(drivers);
	public int numVehicles = getNumberOfObjectsInArray(vehicles);
	public int numInfractions = getNumberOfObjectsInArray(infractions);

	public static void main(String[] args) {
		// empty on purpose
	}

	/**
	 * @param aDriver is a Driver object to be inserted into drivers[]
	 */
	public void registerDriver(Driver aDriver) {

		if ( roomInDatabase(numDrivers, driverLimit) ) {
			insertIntoDatabase(aDriver, drivers, numDrivers);
			numDrivers += 1;
		}
	}

	/**
	 * @param aVehicle is a Vehicle object to be inserted into vehicles[]
	 * @param license is a String value that is to be associated with a Driver object 
	 * 				  associated with the Vehicle object above
	 */
	public void registerVehicle(Vehicle aVehicle, String license) {

		if ( roomInDatabase(numVehicles, vehicleLimit) ) {

			setVehicleOwner(aVehicle, license);
			insertIntoDatabase(aVehicle, vehicles, numVehicles);

			numVehicles += 1;
		}
	}

	public void unregisterVehicle(String plate) {
		// loop through vehicles until we find the vehicle with String plate
		int posOfVehicle = 0;
		while (posOfVehicle < numVehicles) {
			
			if(sameString(vehicles[posOfVehicle].plate, plate)) {
			 	// remove Vehicle object
				vehicles[posOfVehicle] = null;
				numVehicles--;

				// shift rest of vehicles by 1
				for (int i = posOfVehicle; i < numVehicles; i++) {
					vehicles[i] = vehicles[i+1];
					vehicles[i+1] = null;
				}

				break;
			}
			posOfVehicle++;
		}
	}

	public void reportStolen(String plate) {
		for (int posOfVehicle = 0; posOfVehicle < numVehicles - 1; posOfVehicle += 1) {
			Vehicle currentVehicle = vehicles[posOfVehicle]; // used for cleanliness

			if (notNull(currentVehicle)) {
				if (sameString(currentVehicle.plate, plate)) {
					vehicles[posOfVehicle].reportedStolen = true;
				}
			}
		}
	}

	public void changeOwner(String plate, String license) {
		Driver newOwner = new Driver(); // default init

		// find driver associated with @param license
		for (Driver driver : drivers) {
			if (notNull(driver) && sameString(driver.license, license)) {
				newOwner = driver;
			}
		}
		// find vehicle associated with @param plate
		for (Vehicle vehicle : vehicles) {
			if (notNull(vehicle) && sameString(vehicle.plate, plate)) {
				if (notNull(newOwner)) {
					vehicle.owner = newOwner;
				}
			}
		}
	}

	public Infraction issueInfraction(String license, float amount, String description, Date date) {
		Infraction newInfraction = new Infraction(amount, description, date); // instantiate a new infraction with parameters given
		Driver affectedDriver = new Driver();
		boolean foundDriver = false;

		for (Driver driver : drivers) {
			if (notNull(driver)) {
				if (sameString(driver.license, license)) {
					foundDriver = true;
					affectedDriver = driver;
				}
			}
		}
		// avoid accidentally inserting an infraction with an invalid driver
		if (foundDriver) {
			newInfraction.driver = affectedDriver;

			if (roomInDatabase(numInfractions, infractionLimit)) {
				insertIntoDatabase(newInfraction, infractions, numInfractions);
				numInfractions++;
			}
		}

		return newInfraction;
	}

	public boolean hasOutStandingInfractions(Driver driver) {
		for (Infraction infraction : infractions) {
			// System.out.println(infraction);
			if (notNull(infraction)) {
				if ( sameObject(infraction.driver, driver) && infraction.outstanding ) {
					return true;
				}
			}
		}
		return false; // no outstanding infractions were found
	}

	public boolean shouldStopVehicle(String plate) {
		for (Vehicle vehicle : vehicles) {

			if (notNull(vehicle) && sameString(vehicle.plate, plate)) {
				if (( vehicle.reportedStolen ) || ( hasOutStandingInfractions(vehicle.owner) )) {
					return true;
				}
			}
		}
		return false; // this vehicle should NOT be pulled over
	}
	
	public void showInfractionsFor(String license) {
		for (Infraction infraction : infractions) {

			if (notNull(infraction) && notNull(infraction.driver)) {
				if (sameString(infraction.driver.license, license)) {
					System.out.println(infraction);
				}
			}
		}
	}

	public Driver[] cleanDrivers() {
		int uncleanDriverCount = 0;
		Driver[] uncleanDrivers = new Driver[uncleanDriverCount];

		for (Driver driver : drivers) {
			boolean isClean = true;
			for (Infraction infraction : infractions) {

				if (notNull(infraction)) {
					if (sameObject(infraction.driver, driver)) {
						isClean = false;
					}
				}
			}
			if (isClean && notNull(driver)) {
				// overwrite existing array & append new clean driver using helper appendDriver() method
				uncleanDrivers = appendDriver(uncleanDrivers, driver);
				uncleanDriverCount++;
			}
		}
		return uncleanDrivers;
	}

	public void showInfractionReport() {
		for (Driver driver : drivers) {
			if (hasInfraction(driver)) {
				printAllInfractionsOf(driver);
			}
		}
	}

	/** 
	 *  --------------------------------------------------------
	 * 				   *HELPER METHODS BELOW*
	 *  --------------------------------------------------------
	 */

	/**
	 * helper method used by cleanDrivers()
	 * @return returnArray = oldArray + objToAppend
	 */
	public static Driver[] appendDriver(Driver[] oldArray, Driver objToAppend) {
		int newLength = oldArray.length + 1;
		Driver[] returnArray = new Driver[newLength];

		for (int i=0; i<oldArray.length; i++) {
			returnArray[i] = oldArray[i];
		}

		returnArray[newLength-1] = objToAppend;

		return returnArray;
	}

	public static Infraction[] appendInfraction(Infraction[] oldArray, Infraction objToAppend) {
		int newLength = oldArray.length + 1;
		Infraction[] returnArray = new Infraction[newLength];

		for (int i=0; i<oldArray.length; i++) {
			returnArray[i] = oldArray[i];
		}

		returnArray[newLength-1] = objToAppend;

		return returnArray;
	}

	public boolean hasInfraction(Driver driver) {
		for (Infraction infraction : infractions) {
			if (notNull(infraction)) {
				if (sameObject(infraction.driver, driver)) {
					return true;
				}
			}
		}
		return false;
	}

	public void printAllInfractionsOf(Driver driver) {
		Infraction[] personalInfractions = new Infraction[0];

		for (Infraction infraction : infractions) {
			if (notNull(infraction) && sameObject(infraction.driver, driver)) {
				personalInfractions = appendInfraction(personalInfractions, infraction);
			}
		}
		
		String detailedReport = returnDetailedInfractionReport(personalInfractions, driver);
		System.out.println(detailedReport); // display to console
	}

	public String returnDetailedInfractionReport(Infraction[] localInfractions, Driver driver) {
		String name = String.format("%20s", driver.name);
		int numOfInfractions = localInfractions.length;
		float totalPaid = 0f;

		for (Infraction infraction : localInfractions) {
			if (infraction.outstanding == false) {
				totalPaid += infraction.amount;
			}
		}

		String returnTotalPaid = String.format("%.2f", totalPaid);
		returnTotalPaid = String.format("%6s", returnTotalPaid);

		return name + ": " + numOfInfractions + " infractions, total paid = $" + returnTotalPaid;
	}

	public boolean roomInDatabase(int numObjects, int objectLimit) {
		if (numObjects < objectLimit) {
			return true;
		}
		return false;
	}

	public void insertIntoDatabase(Object object, Object[] database, int index) {
		database[index] = object;
	}

	public void setVehicleOwner(Vehicle aVehicle, String license) {
		for (Driver driver : drivers) {
			if ((notNull(driver)) && (driver.license == license)) {
				aVehicle.owner = driver;
			}
		} 
	}

	public boolean sameString(String string1, String string2) {
		if (string1 == string2) {
			return true;
		} 
		return false;
	}

	public boolean sameObject(Object object1, Object object2) {
		if (object1.equals(object2)) {
			return true;
		} 
		return false;
	}

	// simple null value tester for Objects
	public boolean notNull(Object testObject) {
		if (testObject != null) {
			return true;
		}
		return false;
	}

	/**
	 * The following static method takes in
	 * @param array which is one of: vehicles[], drivers[], or infractions[]
	 * @return lastOccupiedIndex + 1 --> this is the TOTAL number of objects in the above 'array' parameter
	 * - - - - - - - - -
	 * this function will not work with an array with out of place values. everything MUST BE CONTINUOUS
	 * example: [null, 1, null] would NOT be valid
	 * the PoliceDatabase class is written specifically to not have to handle these types of cases
	 * see unregisterVehicle(String plate) for a coded example of how the above statement works
	 */
	public static int getNumberOfObjectsInArray(Object[] array) {
		int lastOccupiedIndex = -1; // default init
		boolean isEmpty = (array[0] == null);
		boolean isFull = (array[array.length - 1] != null);

		if (isEmpty) {
			// do nothing, this is default conditional -- stops next 2 conditionals
		} 
		else if (isFull) {
			lastOccupiedIndex = array.length - 1;
		} 
		// executes ONLY IF array is neither full or empty
		else {
			for (int i = 0; i < array.length; i+=1) {
				if (array[i] == null) {
					lastOccupiedIndex = (i - 1);
					break;
				}
			}
		}

		int totalNumberOfObjectsInArray = lastOccupiedIndex + 1;
		
		return totalNumberOfObjectsInArray;
	}

	public static PoliceDatabase example() { // Register all drivers and their vehicles
		PoliceDatabase pdb = new PoliceDatabase();
	
		pdb.registerDriver(new Driver("L1567-34323-84980", "Matt Adore",
				"1323 Kenaston St.", "Gloucester", "ON"));
		pdb.registerDriver(new Driver("L0453-65433-87655", "Bob B. Pins",
				"32 Rideau Rd.", "Greely", "ON"));
		pdb.registerDriver(new Driver("L2333-45645-54354", "Stan Dupp",
				"1355 Louis Lane", "Gloucester", "ON"));
		pdb.registerDriver(new Driver("L1234-35489-99837", "Ben Dover",
				"2348 Walkley Rd.", "Ottawa", "ON"));
		pdb.registerDriver(new Driver("L8192-87498-27387", "Patty O'Lantern",
				"2338 Carling Ave.", "Nepean", "ON"));
		pdb.registerDriver(new Driver("L2325-45789-35647", "Ilene Dover",
				"287 Bank St.", "Ottawa", "ON"));
		pdb.registerDriver(new Driver("L1213-92475-03984", "Patty O'Furniture",
				"200 St. Laurant Blvd.", "Ottawa", "ON"));
		pdb.registerDriver(new Driver("L1948-87265-34782", "Jen Tull",
				"1654 Stonehenge Cres.", "Ottawa", "ON"));
		pdb.registerDriver(new Driver("L0678-67825-83940", "Jim Class",
				"98 Oak Blvd.", "Ottawa", "ON"));
		pdb.registerDriver(new Driver("L0122-43643-73286", "Mark Mywords",
				"3 Third St.", "Ottawa", "ON"));
		pdb.registerDriver(new Driver("L6987-34532-43334", "Bob Upandown",
				"434 Gatineau Way", "Hull", "QC"));
		pdb.registerDriver(new Driver("L3345-32390-23789", "Carrie Meehome",
				"123 Thurston Drive", "Kanata", "ON"));
		pdb.registerDriver(new Driver("L3545-45396-88983", "Sam Pull",
				"22 Colonel By Drive", "Ottawa", "ON"));
		pdb.registerDriver(new Driver("L1144-26783-58390", "Neil Down",
				"17 Murray St.", "Nepean", "ON"));
		pdb.registerDriver(new Driver("L5487-16576-38426", "Pete Reedish",
				"3445 Bronson Ave.", "Ottawa", "ON"));
		pdb.registerVehicle(new Vehicle("Honda", "Civic", 2015, "yellow", "W3EW4T"),
				"L0453-65433-87655");
		pdb.registerVehicle(new Vehicle("Pontiac","Grand Prix",2007,"dark green","GO SENS"),
				"L0453-65433-87655");
		pdb.registerVehicle(new Vehicle("Mazda", "RX-8", 2004, "white", "OH YEAH"),
				"L2333-45645-54354");
		pdb.registerVehicle(new Vehicle("Nissan","Altima",2017,"bergundy", "Y6P9O7"),
				"L1234-35489-99837");
		pdb.registerVehicle(new Vehicle("Saturn", "Vue", 2002, "white", "9R6P2P"),
				"L2325-45789-35647");
		pdb.registerVehicle(new Vehicle("Honda", "Accord", 2018, "gray", "7U3H5E"),
				"L2325-45789-35647");
		pdb.registerVehicle(new Vehicle("Chrysler", "PT-Cruiser", 2006, "gold", "OLDIE"),
				"L2325-45789-35647");
		pdb.registerVehicle(new Vehicle("Nissan", "Cube", 2010, "white", "5Y6K8V"),
				"L1948-87265-34782");
		pdb.registerVehicle(new Vehicle("Porsche", "959", 1989, "silver", "CATCHME"),
				"L0678-67825-83940");
		pdb.registerVehicle(new Vehicle("Kia", "Soul", 2018, "red", "J8JG2Z"),
				"L0122-43643-73286");
		pdb.registerVehicle(new Vehicle("Porsche", "Cayenne", 2014, "black", "EXPNSV"),
				"L6987-34532-43334");
		pdb.registerVehicle(new Vehicle("Nissan", "Murano", 2010, "silver", "Q2WF6H"),
				"L3345-32390-23789");
		pdb.registerVehicle(new Vehicle("Honda", "Element", 2008, "black", "N7MB5C"),
				"L3545-45396-88983");
		pdb.registerVehicle(new Vehicle("Toyota", "RAV-4", 2010, "green", "R3W5Y7"),
				"L3545-45396-88983");
		pdb.registerVehicle(new Vehicle("Toyota", "Celica", 2006, "red", "FUNFUN"),
				"L5487-16576-38426");
	
		return pdb;
	}

}
