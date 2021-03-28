/**
 * ExpiringPolicy.java
 * @author Karl Damus
 */

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ExpiringPolicy extends Policy {
		
	private Date expiryDate;

	/**
	 * Create an ExpiringPolicy obj with no given date
	 * @param amount the amount of the ExpiringPolicy
	 * @see Policy#Policy(float amt)
	 */
	public ExpiringPolicy(float amount) {
		super(amount);
		GregorianCalendar newCal = new GregorianCalendar(); newCal.add(Calendar.YEAR,1); // expiry date (1 yr. ahead)
		this.expiryDate = newCal.getTime();
	}
	
	/**
	 * Create an ExpiringPolicy obj with a given date
	 * @param amount the amount of the ExpiringPolicy
	 * @param expiryDate date the policy expires
	 * @see Policy#Policy(float amt)
	 */
	public ExpiringPolicy(float amount, Date expiryDate) {
		super(amount); // see Policy.java
		this.expiryDate = expiryDate;
	}

	/**
	 * Check to see if the expiry date of the Policy has passed
	 * @return true (if expired); false (if not expired)
	 */
	public boolean isExpired() {
		Date today = new Date(); // used to compare against getExpiryDate

		if (today.before(this.getExpiryDate())) {
			return false; // policy is NOT expired
		} else {
			return true; // policy is expired
		}
	}

	/**
	 * @return the amount of this ExpiringPolicy
	 * @see CompanyClient
	 * @see IndivdualClient
	 */
	public float handleClaim() {
		return this.getAmount();
	}

	// getter for the expiry date
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	/**
	 * @return ExpiringPolicy in a readable format
	 * "expired on:" if expired
	 * "expires:" if not expired
	 */
	@Override
	public String toString() {
		String formattedExpiryDate = String.format("%tB %td, %tY (%tI:%tM %Tp)", this.expiryDate, this.expiryDate, this.expiryDate, this.expiryDate, this.expiryDate, this.expiryDate);

		if (this.isExpired()) {
			return String.format("ExpiringPolicy: %04d amount: $%.2f expired on: ", this.getPolicyNumber(), this.getAmount()) + formattedExpiryDate;
		} else {
			return String.format("ExpiringPolicy: %04d amount: $%.2f expires: ", this.getPolicyNumber(), this.getAmount()) + formattedExpiryDate;
		}
	}
	
}
