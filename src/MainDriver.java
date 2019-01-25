/*
 * 	Bill's Computer Parts
 *  An application for managing orders for Bill's Computer Parts
 *  
 *  CS 500 - Spring 2019
 *  Trevor D. Brown
 *  
 *  MainDriver.java - the main driver for the application.
 */

import java.io.*;
import java.util.Scanner;

public class MainDriver {

	public static void main(String[] args) {
		
		// Files to be used in the program
		File customers = new File("customers.txt");
		File products = new File("products.txt");
		File orders = new File("orders.txt");
		
		// Scanner object for file reading.
		Scanner fileReader;
		
		// Custom objects for program
		Store ourStore = new Store();
		
		// Try/catch for Customers File
		try {
			System.out.println("Reading Customers file (customers.txt)...");
			fileReader = new Scanner(customers);
			
			while (fileReader.hasNextLine()) {
				Customer newCustomer;
				
				int customerID = fileReader.nextInt();
				String customerName = fileReader.next();
				String customerDiscount = fileReader.next();
				
				newCustomer = new Customer(customerID, customerName, customerDiscount);
				ourStore.addCustomer(newCustomer);
			}
			
			fileReader.close();
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		// Try/catch for Products File
		try {
			System.out.println("Reading Products file (products.txt)...");
			fileReader = new Scanner(products);
			
			while (fileReader.hasNextLine()) {
				Product newProduct;
				
				int productID = fileReader.nextInt();
				String productName = fileReader.next();
				double productPrice = fileReader.nextDouble();
				
				newProduct = new Product(productID, productName, productPrice);
				ourStore.addProduct(newProduct);
			}
			
			fileReader.close();
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
				
		// Try/catch for Orders File
		try {
			System.out.println("Reading Orders file (orders.txt)...");
			fileReader = new Scanner(orders);
			
			while (fileReader.hasNextLine()) {
				Order newOrder;
				
				int customerID = fileReader.nextInt();
				int productID = fileReader.nextInt();
				int quantity = fileReader.nextInt();
				
				newOrder = new Order(customerID, productID, quantity);
				ourStore.addOrder(newOrder);
			}
			
			fileReader.close();
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}

		// Print the Store Content (Customers, Products, and Orders)
		ourStore.printStoreContent();
		
		// Generate the Order Details from the existing Orders
		ourStore.generateOrderDetails();
		
		ourStore.generateOrderSummary();
	
	}

}
