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
	
	private int productID;
	private String productName;
	private double productPrice;
	
	public Product(int productID, String productName, double price) {
		this.productID = productID;
		this.productName = productName;
		this.productPrice = price;
	}
	
	public Product() {
		this.productID = -1;
		this.productName = "";
		this.productPrice = -1;
	}
	
	public int getProductID() {
		return this.productID;
	}
	
	public void setProductID(int newProductID) {
		this.productID = newProductID;
	}
	
	public String getProductName() {
		return this.productName;
	}
	
	public void setProductName(String newProductName) {
		this.productName = newProductName;
	}
	
	public double getProductPrice() {
		return this.productPrice;
	}
	
	public void setProductPrice(double newProductPrice) {
		this.productPrice = newProductPrice;
	}
	
	public String toString() {
		return "Product - Product ID: " + this.productID + "\t| Product Name: " + this.productName + "\t| Price: " + this.productPrice;
	}

}
