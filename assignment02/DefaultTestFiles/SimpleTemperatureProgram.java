/*****************************
 * SimpleTemperatureProgram.java
 * Author: Jason Hinek 
 * ©2021, All Rights Reserved
*****************************/

public class SimpleTemperatureProgram{

    // Code from Assignment Specification
    public static void main(String[] args){
		
System.out.println("");
        System.out.println("Make a Temperature object");
        System.out.println("> Temperature t = new Temperature(10.1)");
        Temperature t = new Temperature(10.1);
        System.out.println(">> t.getScale() -> " + t.getScale());    // outputs Scale.CELSIUS.toString()
        System.out.println(">> t.toString() -> " + t);               // outputs 10.1C
    
System.out.println("");
		System.out.println("> t.setScale(Scale.KELVIN)");
        t.setScale(Scale.KELVIN);                                // change scale
        System.out.println(">> t.toString() -> " + t);               // outputs 50.18F
        System.out.println(">> t.getScale() -> " + t.getScale());    // outputs Scale.FAHREHEIT.toString()
System.out.println("");
		System.out.println("> t.setScale(Scale.FAHRENHEIT)");
        t.setScale(Scale.FAHRENHEIT);                                // change scale
        System.out.println(">> t.toString() -> " + t);               // outputs 50.18F
        System.out.println(">> t.getScale() -> " + t.getScale());    // outputs Scale.FAHREHEIT.toString()
System.out.println("");
        System.out.println("> t = new Temperature(12.25, \"Kel\")");
        t = new Temperature(12.25, "Kel");              // must recognize short form
        System.out.println(">> t.toString() -> " + t);  // outputs 12.25K
System.out.println("");
        System.out.println("Make a Temperature object");
        
		System.out.println("> Temperature t = new Temperature(-280.5)");
       	t = new Temperature(-280.5);
        System.out.println(">> t.getScale() -> " + t.getScale());    // outputs Scale.CELSIUS.toString()
        System.out.println(">> t.toString() -> " + t);               // outputs 10.1C
    }

}