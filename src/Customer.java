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
	
	private int customerID;
	private String customerName;
	private boolean discountEligible;
	
	// Customer constructor
	// Requires customer ID, customer name, and their discount eligibility.
	public Customer(int customerID, String customerName, String discountEligible) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.discountEligible = discountEligible.equals("YES")?true:false;
	}
	
	// Empty Customer constructor, used for erroneous cases
	public Customer() {
		this.customerID = -1;
		this.customerName = "";
		this.discountEligible = false;
	}
	
	public int getCustomerID() {
		return this.customerID;
	}
	
	public void setCustomerID(int newCustomerID) {
		this.customerID = newCustomerID;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public void setCustomerName(String newCustomerName) {
		this.customerName = newCustomerName;
	}
	
	public boolean getDiscountEligibility() {
		return this.discountEligible;
	}
	
	public void setDiscountEligibility(String newDiscountEligibility) {
		this.discountEligible = newDiscountEligibility.equals("YES")?true:false;
	}
	
	public String toString() {
		return "Customer - Customer ID: " + this.customerID + "\t| Customer Name: " + this.customerName + "\t| Discount Eligible: " + (this.discountEligible == true?"Yes":"No");
	}

}
