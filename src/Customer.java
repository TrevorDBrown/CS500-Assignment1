/*
 * 	Bill's Computer Parts
 *  An application for managing orders for Bill's Computer Parts
 *  
 *  CS 500 - Spring 2019
 *  Trevor D. Brown
 *  
 *  Customer.java - the Customer class.
 */

public class Customer {
	
	// Private variables
	private int customerID;				// customerID - the numeric value representing a unique customer.
	private String customerName;		// customerName - the common name of the unique customer.
	private boolean discountEligible;	// discountEligible - a boolean representation of the eligibility a customer has for a discount.
	
	// Customer constructor: requires a customer ID, customer name, and their discount eligibility.
	public Customer(int customerID, String customerName, String discountEligible) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.discountEligible = discountEligible.equals("YES")?true:false;
	}
	
	// Customer constructor: parameterless; used for erroneous cases.
	// i.e. if a Customer object needs to be returned, but one cannot be found.
	public Customer() {
		this.customerID = -1;
		this.customerName = "";
		this.discountEligible = false;
	}
	
	// customerID getter
	public int getCustomerID() {
		return this.customerID;
	}
	
	// customerID setter
	public void setCustomerID(int newCustomerID) {
		this.customerID = newCustomerID;
	}
	
	// customerName getter
	public String getCustomerName() {
		return this.customerName;
	}
	
	// customerName setter
	public void setCustomerName(String newCustomerName) {
		this.customerName = newCustomerName;
	}
	
	// discountEligible getter
	public boolean getDiscountEligibility() {
		return this.discountEligible;
	}
	
	// discountEligible setter
	public void setDiscountEligibility(String newDiscountEligibility) {
		this.discountEligible = newDiscountEligibility.equals("YES")?true:false;
	}
	
	// toString override (prints Customer objects in a logical format)
	public String toString() {
		return "Customer - Customer ID: " + this.customerID + "\t| Customer Name: " + this.customerName + "\t| Discount Eligible: " + (this.discountEligible == true?"Yes":"No");
	}

}
