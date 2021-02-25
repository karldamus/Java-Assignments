# Assignment #2
## Temperature.java
This class converts a temperature object between Celsius, Fahrenheit, and Kelvin. 

All scales are ENUM values (see Scale.java).

The testing program for this class can be found [here](https://github.com/karldamus/java-assignments/blob/main/assignment02/DefaultTestFiles/SimpleTemperatureProgram.java).
Sample output here:
```java
System.out.println("Make a Temperature object");
System.out.println("> Temperature t = new Temperature(10.1)");
Temperature t = new Temperature(10.1);
System.out.println(">> t.getScale() -> " + t.getScale());    	// outputs Scale.CELSIUS.toString()
System.out.println(">> t.toString() -> " + t);               		// outputs 10.1C

System.out.println("> t.setScale(Scale.KELVIN)");
 t.setScale(Scale.KELVIN);                               			// change scale
System.out.println(">> t.toString() -> " + t);               		// outputs 50.18F
System.out.println(">> t.getScale() -> " + t.getScale());    	// outputs Scale.FAHREHEIT.toString()
```

## PoliceDatabase.java
> This class simulates a police database that keeps track of a driver’s history with respect to the number of times they were pulled over and ticketed for an infraction (i.e., a driving violation). The database will make use of arrays so as to allow many drivers, vehicles and infractions to be stored together in lists.   

* registerDriver()
* registerVehicle()
* unregisterVehicle()
* reportStolen()
* changeOwner
* issueInfraction()
* hasOutstandingInfractions()
* shouldStopVehicle()
* showInfractionsFor()
* cleanDrivers()
* showInfractionReport()

## Driver.java
* a proper constructor that takes 5 parameters for all 5 attributes in the order shown above 
* a proper zero-parameter constructor that calls the first constructor with appropriate values 
* a toString() method that returns a string in the following format: 
	* “#L0678-67825-83940 John Doe living at 12 Elm St., Ottawa, ON”

## Vehicle.java
* a proper constructor that takes 5 parameters for the first 5 attributes in the order shown above (assume new vehicles are not stolen and there is no owner yet). 
* a proper zero-parameter constructor that calls the first constructor with appropriate values 
* a toString() method that returns a string in the following format: 
	* ”A blue 1998 Honda Civic with plate X5T6Y8”

## Infraction.java
* a proper constructor that takes 3 parameters for the first 3 attributes in the order shown above 
* a proper constructor that takes zero-parameters and calls the above constructor 
* a toString() method that returns a string with the format: 
	* “$100.00 Infraction on Sun Jul 14 07:08:00 EDT 2002 [PAID IN FULL]” 
* If the infraction has been paid, it should state [PAID IN FULL] as shown above, otherwise it should state [OUTSTANDING]. 
* a method called pay() which pays the infraction
