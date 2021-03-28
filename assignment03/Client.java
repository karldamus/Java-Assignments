/**
 * Client.java
 * @author Karl Damus
 * @author Jason Hinek
 */

import java.util.Date;

public abstract class Client {
    private static final int MAX_POLICIES_PER_CLIENT = 10;

    private static int NEXT_CLIENT_ID = 1;

    private   String        name;
    private   int           id;
    protected Policy[]      policies;
    protected int           numPolicies;

	/**
	 * Constructor for a new Client obj
	 * @param n the name of the Client
	 */
    public Client(String n) {
        name = n;
        id = NEXT_CLIENT_ID++;
        policies = new Policy[MAX_POLICIES_PER_CLIENT];
        numPolicies = 0;
    }

	// getters
	public String 	getName() 			{ return name; }
    public int 		getId() 			{ return id; }
    public Policy[] getPolicies() 		{ return policies; }
    public int 		getNumPolicies()	{ return numPolicies; }

	/**
	 * Calculate final amount of coverage across ALL policies in Client.policies[]
	 * @return the amount of totalCoverage() on the respective policy
	 */
	public float totalCoverage() {
		float totalCoverage = 0f; // will be over-written by sum of all policy amounts

		for (Policy policy : policies) {
			if (notNull(policy)) {
				totalCoverage += policy.amount;
			}
		}
		return totalCoverage;
	}

	/**
	 * Add a new Policy obj to Client.policies[]
	 * @param policy policy to be added to Client.policies[]
	 * @return Policy obj (if added); else null
	 */
	public Policy addPolicy(Policy policy) {
		if (this.numPolicies < MAX_POLICIES_PER_CLIENT) {
			policies[numPolicies] = policy;
			this.numPolicies++;

			return policy;
		} else {
			return null;
		}
	}

	/**
	 * Create and add a general Policy object
	 * @param amt the $ amount for the policy
	 * @return Policy obj (if created); else null
	 * @see Client#addPolicy(Policy policy)
	 */
	public Policy openPolicyFor(float amt) {
		Policy newPolicy = new Policy(amt);		
		return addPolicy(newPolicy);
	}

	/**
	 * Create and add a DepreciatingPolicy object
	 * @param amt the $ amount for the policy
	 * @param rate the % amount of depreciation
	 * @return Policy obj (if created); else null
	 * @see Client#addPolicy(Policy policy)
	 */
	public Policy openPolicyFor(float amt, float rate) {
		Policy newDepreciatingPolicy = new DepreciatingPolicy(amt, rate);		
		return addPolicy(newDepreciatingPolicy);
	}

	/**
	 * Create and add an ExpiringPolicy object
	 * @param amt the $ amount for the policy
	 * @param expire the expiry date of the policy
	 * @return Policy obj (if created); else null
	 * @see Client#addPolicy(Policy policy)
	 */
	public Policy openPolicyFor(float amt, Date expire) {
		Policy newExpiringPolicy = new ExpiringPolicy(amt, expire);
		return addPolicy(newExpiringPolicy);
	}

	/**
	 * Locate a policy in Client.policies[]
	 * @param polNum the ID# of the Policy to be found
	 * @return Policy obj (if found); else null
	 */
	public Policy getPolicy(int polNum) {
		for (Policy policy : policies) {
			if ( notNull(policy) && policy.getPolicyNumber() == polNum ) {
				return policy;
			}
		}
		return null;
	}

	/**
	 * Cancel a policy in Client.policies[]
	 * @param polNum the ID# of the Policy to be cancelled
	 * @return true (if cancelled); else false
	 * @see Client#removeFromPoliciesArray(Policy policyToRemove)
	 */
	public boolean cancelPolicy(int polNum) {
		// check if Policy exists
		if (notNull(getPolicy(polNum))) {
			removeFromPoliciesArray(getPolicy(polNum)); // helper function -> see below
			
			return true;
		}
		return false; // Policy DNE
	}

	/**
	 * Create a claim on a Policy obj
	 * @param polNum the ID# of the Policy
	 * @return this.amount (float)
	 * @see IndividualClient
	 * @see CompanyClient
	 */
	public abstract float makeClaim(int polNum);

	/**
	 * Deletes Policy from policies[] and moves last Policy into its place
	 * @param policyToRemove the Policy object to be removed
	 * @see Client#cancelPolicy(int polNum)
	 */
	private void removeFromPoliciesArray(Policy policyToRemove) {
		// local obj's used for cleanliness
		Policy lastPolicy = policies[numPolicies];
		Policy currentPolicy;

		// loop through policies[]
		for (int i = 0; i < this.numPolicies; i++) {
			currentPolicy = policies[i];

			// edge case: policyToRemove == lastPolicy
			if ( currentPolicy.equals(lastPolicy) ) {
				policies[numPolicies] = null; // lastPolicy = null
			}

			// found policy, lets swap
			else if ( currentPolicy.equals(policyToRemove) ) {
				policies[i] = policies[numPolicies]; // swap currentPolicy and lastPolicy
				policies[numPolicies] = null;		// lastPolicy = null
			}
		}

		numPolicies--; // we removed a policy, reduce numPolicies
	}

	/**
	 * Helper function for all sub-classes
	 * > Check if Policy object is equal to null
	 * @param policy the Policy object in question
	 * @return boolean
	 */
	public boolean notNull(Policy policy) {
		if (policy != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
    public String toString() {
        return String.format("Client: %06d amount: %s", id, name);
    }
}
