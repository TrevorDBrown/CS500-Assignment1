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
	
	private int customerID;
	private int productID;
	private int quantity;
	
	public Order(int customerID, int productID, int quantity) {
		this.customerID = customerID;
		this.productID = productID;
		this.quantity = quantity;
	}
	
	public Order() {
		this.customerID = -1;
		this.productID = -1;
		this.quantity = -1;
	}

	public int getCustomerID() {
		return this.customerID;
	}
	
	public void setCustomerID(int newCustomerID) {
		this.customerID = newCustomerID;
	}
	
	public int getProductID() {
		return this.productID;
	}
	
	public void setProductID(int newProductID) {
		this.productID = newProductID;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}
	
	public String toString() {
		return "Order - Customer ID: " + this.customerID + "\t| Product ID: " + this.productID + "\t| Quantity: " + this.quantity;
	}
	
	
}
