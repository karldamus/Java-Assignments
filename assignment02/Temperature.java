/*****************************
 * Temperature.java
 * Author: Karl Damus via Jason Hinek
 * ©2021, All Rights Reserved
*****************************/

public class Temperature {

	public static void main(String[] args) {
		// empty on purpose
	}

	Scale scale;
	double temp;

	public Temperature(double temp) {
		if (scale == null) {
			this.scale = Scale.CELSIUS;
		}
		setTemp(temp, this.scale);
	}

	public Temperature(double temp, String scale) {
		char firstCharOfScale = scale.toLowerCase().charAt(0);

		switch(firstCharOfScale) {
			case 'c':
				setTemp(temp, Scale.CELSIUS); break;
			case 'f': 
				setTemp(temp, Scale.FAHRENHEIT); break;
			case 'k': 
				setTemp(temp, Scale.KELVIN); break;
		}
	}

	public Temperature(double temp, Scale scale) {
		setTemp(temp, scale);
	}

	public Scale getScale() {
		return this.scale;
	}

	public double getTemp() {
		return this.temp;
	}

	public void setScale(Scale scale) {
		// convertFromTo(temp, fromScale, toScale) 
		// helper method > see also: convertToCelsius/Fahrenheit/Kelvin
		if (this.scale != scale) {
			convertFromTo(this.temp, this.scale, scale);
		}

		this.scale = scale;

		checkIfBelowAbsoluteZero(this.temp, this.scale);
	}

	public void setTemp(double temp, Scale scale) {
		this.temp = temp;
		this.scale = scale;
		setScale(scale);
	}

	/** 
	 *  --------------------------------------------------------
	 * 				   *HELPER METHODS BELOW*
	 *  --------------------------------------------------------
	 */

	public void convertFromTo(double temp, Scale fromScale, Scale toScale) {
		// CELSIUS to ?
		if (toScale == Scale.CELSIUS) {
			this.temp = convertToCelsius(temp, fromScale);
		}
		// FAHRENHEIT to ?
		if (toScale == Scale.FAHRENHEIT) {
			this.temp = convertToFahrenheit(temp, fromScale);
		}
		// KELVIN to ?
		if (toScale == Scale.KELVIN) {
			this.temp = convertToKelvin(temp, fromScale);
		}
	}

	/**
	 * CONVERSIONS
	 */

	public double convertToCelsius(double temp, Scale fromScale) {
		double returnTemp = 0; // default init

		if (fromScale == Scale.FAHRENHEIT) {
			// C = ( F - 32 ) * ( 5/9 )
			returnTemp = (temp - 32) * ( 5.0/9 );
		}
		if (fromScale == Scale.KELVIN) {
			// C = K - 273.15
			returnTemp = temp - 273.15;
		}

		return returnTemp;
	}

	public double convertToFahrenheit(double temp, Scale fromScale) {
		double returnTemp = 0; // default init

		if (fromScale == Scale.CELSIUS) {
			// F = ( C * ( 9/5 )) + 32
			returnTemp = ((temp * ( 9.0/5 )) + 32);
		}
		if (fromScale == Scale.KELVIN) {
			// F = ( K * ( 9/5 )) - 459.67
			returnTemp = (temp * ( 9.0/5 )) - 459.67;
		}

		return returnTemp;
	}

	public double convertToKelvin(double temp, Scale fromScale) {
		double returnTemp = 0; // default init

		if (fromScale == Scale.CELSIUS) {
			// K = C + 273.15
			returnTemp = temp + 273.15;
		}
		if (fromScale == Scale.FAHRENHEIT) {
			// K = ( F + 459.67 ) * ( 5/9 )
			returnTemp = (temp + 459.67) * ( 5.0/9 );
		}

		return returnTemp;
	}

	/**
	 * END OF CONVERSIONS
	 */

	public Scale returnScaleFromString(String string) {
		char firstCharOfString = string.toLowerCase().charAt(0);

		switch(firstCharOfString) {
			case 'c':
				return Scale.CELSIUS;
			case 'f': 
				return Scale.FAHRENHEIT;
			case 'k': 
				return Scale.KELVIN;
		}

		return Scale.NONE;
	}

	public void checkIfBelowAbsoluteZero(double temp, Scale scale) {
		double absoluteZero = getAbsoluteZero(scale);
		if (temp < absoluteZero) {
			this.temp = absoluteZero;
		}
	}

	public double getAbsoluteZero(Scale scale) {
		double absoluteZero = 0; // default init -- overwrited

		switch(scale) {
			case CELSIUS: 
				absoluteZero = (-273.15); 
				break;
			case FAHRENHEIT: 
				absoluteZero = (-459.67);
				break;
			case KELVIN: 
				absoluteZero = 0;
				break;
			// NONE case should NEVER happen
			case NONE:
				System.out.println("ERROR: NONE case detected in setTemp(double temp, Scale scale)");
				break;
		}

		return absoluteZero;
	}

	/** String representation of a temperature object    */
	@Override
	public String toString(){
		return "" + this.getTemp() + this.getScale().name().charAt(0);
	}

}