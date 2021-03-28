/**
 * IndividualClient.java
 * @author Karl Damus
 */

public class IndividualClient extends Client {
	
	public IndividualClient(String string) {
		super(string); // see Client.java
	}

	/**
	 * Overridden Abstract Method
	 * Make a claim on a Policy for an IndividualClient
	 * @param polNum the ID# of the claim to be made
	 * @return the amount of the policy (if successful); else 0f
	 * @see Client#makeClaim(int polNum)
	 */
	@Override
	public float makeClaim(int polNum) {
		for (Policy policy : this.policies) {
			if (notNull(policy) && policy.getPolicyNumber() == polNum && !policy.isExpired()) {
				policy.handleClaim();
				
				return policy.getAmount();
			}
		}

		return 0f;
	}
}
