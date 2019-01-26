/*
 * 	Bill's Computer Parts
 *  An application for managing orders for Bill's Computer Parts
 *  
 *  CS 500 - Spring 2019
 *  Trevor D. Brown
 *  
 *  Order.java - the Order class.
 */

public class Order {
	
	// Private variables
	private int customerID;		// customerID - the customer associated with the order	
	private int productID;		// productID - the product associated with the order
	private int quantity;		// quantity - the quantity associated with the order.
	
	// Order constructor: requires a customerID, productID, and quantity.
	public Order(int customerID, int productID, int quantity) {
		this.customerID = customerID;
		this.productID = productID;
		this.quantity = quantity;
	}
	
	// Order constructor: paramaterless; used in erroneous cases
	// i.e. if a Order object needs to be returned, but one cannot be found.
	public Order() {
		this.customerID = -1;
		this.productID = -1;
		this.quantity = -1;
	}

	// customerID getter
	public int getCustomerID() {
		return this.customerID;
	}
	
	// customerID setter
	public void setCustomerID(int newCustomerID) {
		this.customerID = newCustomerID;
	}
	
	// productID getter
	public int getProductID() {
		return this.productID;
	}
	
	// productID setter
	public void setProductID(int newProductID) {
		this.productID = newProductID;
	}
	
	// quantity getter
	public int getQuantity() {
		return this.quantity;
	}
	
	// quantity setter
	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}
	
	// toString override (prints Order objects in a logical format)
	public String toString() {
		return "Order - Customer ID: " + this.customerID + "\t| Product ID: " + this.productID + "\t| Quantity: " + this.quantity;
	}
	
	
}
