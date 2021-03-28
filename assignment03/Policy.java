/**
 * Policy.java
 * March 2021
 * 
 * This Policy class is the basis for all 
 * 
 * @author Karl Damus
 * @author Jason Hinek
 * @version 1.00
 */

public class Policy {
    private static int NEXT_POLICY_NUMBER = 1;

    private     int     policyNumber;
    protected   float   amount;

	/**
	 * Create a Policy object
	 * @param amt the $ amount for the policy
	 */
    public Policy(float amt) {
        amount = amt;
        policyNumber = NEXT_POLICY_NUMBER++;
    }

	/**
	 * General Policy object will never "expire"
	 * @return false
	 */
	public boolean isExpired() {
        return false;
    }

	/**
	 * @return the amount of this Policy
	 * @see CompanyClient
	 * @see IndividualClient
	 */
	public float handleClaim() {
		return this.amount;
	}

	public int getPolicyNumber() { return policyNumber; }
    public float getAmount() { return amount; }

	@Override
    public String toString() {
        return String.format("Policy: %04d amount: $%1.2f", policyNumber, amount);
    }
}