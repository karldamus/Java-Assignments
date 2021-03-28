/**
 * DepreciatingPolicy.java
 * @author Karl Damus
 */

public class DepreciatingPolicy extends Policy {

	private float rate;

	/**
	 * Create a DepreciatingPolicy obj with an amount and a set rate
	 * @param amount the amount of the DepreciatingPolicy
	 * @param rate the % depreciation rate of the DepreciatingPolicy
	 * @see Policy
	 */
	public DepreciatingPolicy(float amount, float rate) {
		super(amount); // see Policy.java
		this.rate = rate;
	}

	/**
	 * Depreciate the amount of the policy (this.amount)
	 */
	public void depreciate() {
		float depreciatedAmount = this.amount - (this.amount * this.rate);
		this.amount = depreciatedAmount;
	}

	/**
	 * Depreciate this policy's amount (see DepreciatingPolicy.depreciate() above)
	 * @return amount BEFORE depreciation
	 * @see CompanyClient
	 * @see IndividualClient
	 */
	public float handleClaim() {
		float returnAmount = this.getAmount();
		this.depreciate();

		return returnAmount;
	}

	// getter for the depreciation rate
	public float getRate() { return this.rate; }

	// check if the value of the Policy has depreciated to an amount of 0
	public boolean isExpired() {
		return (0 == this.getAmount());
	}

	/**
	 * @return DepreciatingPolicy in a readable format
	 */
	@Override
	public String toString() {
		float returnRate = rate * 100;
		return String.format("DepreciatingPolicy: %04d amount: $%.2f rate: %.1f", this.getPolicyNumber(), this.amount, returnRate) + "%";
	}
}
