/**
 * CompanyClient.java
 * @author Karl Damus
 */

public class CompanyClient extends Client {
	
	public CompanyClient(String string) {
		super(string); // see Client.java
	}

	/**
	 * Overridden Abstract Method
	 * Make a claim on a Policy for a CompanyClient
	 * @param polNum the ID# of the claim to be made
	 * @return the amount of the policy (if successful); else 0f
	 * @see Client#makeClaim(int polNum)
	 */
	@Override
	public float makeClaim(int polNum) {
		for (Policy policy : policies) {
			if (notNull(policy) && policy.getPolicyNumber() == polNum && !policy.isExpired()) {
				float returnAmount = policy.getAmount();

				policy.handleClaim();
				return returnAmount;
			}
		}

		return 0f;
	}
}