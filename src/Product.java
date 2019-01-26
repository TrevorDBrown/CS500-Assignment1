/*
 * 	Bill's Computer Parts
 *  An application for managing orders for Bill's Computer Parts
 *  
 *  CS 500 - Spring 2019
 *  Trevor D. Brown
 *  
 *  Product.java - the Product class.
 */

public class Product {
	
	// Private variables
	private int productID;			// productID - the numeric representation of a unique product
	private String productName;		// productName - the common name of a unique product
	private double productPrice;	// productPrice - the price of a product
	
	// Product constructor - requires a productID, productName, and price
	public Product(int productID, String productName, double price) {
		this.productID = productID;
		this.productName = productName;
		this.productPrice = price;
	}
	
	// Product constructor - parameterless; used in erroneous cases.
	// i.e. if a Product object needs to be returned, but one cannot be found.
	public Product() {
		this.productID = -1;
		this.productName = "";
		this.productPrice = -1;
	}
	
	// productID getter
	public int getProductID() {
		return this.productID;
	}
	
	// productID setter
	public void setProductID(int newProductID) {
		this.productID = newProductID;
	}
	
	// productName getter
	public String getProductName() {
		return this.productName;
	}
	
	// productName setter 
	public void setProductName(String newProductName) {
		this.productName = newProductName;
	}
	
	// productPrice getter
	public double getProductPrice() {
		return this.productPrice;
	}
	
	// productPrice setter
	public void setProductPrice(double newProductPrice) {
		this.productPrice = newProductPrice;
	}
	
	// toString override (prints Product objects in a logical format)
	public String toString() {
		return "Product - Product ID: " + this.productID + "\t| Product Name: " + this.productName + "\t| Price: " + this.productPrice;
	}

}
