/*
 * 	Bill's Computer Parts
 *  An application for managing orders for Bill's Computer Parts
 *  
 *  CS 500 - Spring 2019
 *  Trevor D. Brown
 *  
 *  MainDriver.java - the main driver for the application.
 */

import java.io.*;			// IO package (File operations)
import java.util.Scanner;	// Scanner object (from util package; I/O operations)

public class MainDriver {

	public static void main(String[] args) {
		
		// Files to be used in the program
		File customers = new File("customers.txt");	// Customers file (customerID customerName customerDiscount)
		File products = new File("products.txt");	// Products file (productID productName productPrice)
		File orders = new File("orders.txt");		// Orders file (customerID, productID, quantityOrdered)
		
		// Scanner object for file reading.
		Scanner fileReader;
		
		// Custom objects for program
		Store ourStore = new Store();	// Store (contains collections of Customers, Products, and Orders)
		
		// Try/catch for Customers File
		try {
			System.out.println("Reading Customers file (customers.txt)...");
			fileReader = new Scanner(customers);	// Prepare the Customers file for reading (assignment Scanner object)
			
			/*
			 * While the Customers file has another line to read,
			 * Create a new Customer object
			 * Store the values read from the current line
			 * Add the customer to the Store
			*/ 
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
			// If the file does not exist, print the stack trace at the point of error
			e.printStackTrace();
		}
		
		// Try/catch for Products File
		try {
			System.out.println("Reading Products file (products.txt)...");
			fileReader = new Scanner(products);		// Prepare the Products file for reading (assignment Scanner object)
			
			/*
			 * While the Products file has another line to read,
			 * Create a new Product object
			 * Store the values read from the current line
			 * Add the product to the Store
			*/ 
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
			// If the file does not exist, print the stack trace at the point of error
			e.printStackTrace();
		}
				
		// Try/catch for Orders File
		try {
			System.out.println("Reading Orders file (orders.txt)...");
			fileReader = new Scanner(orders);		// Prepare the Orders file for reading (assignment Scanner object)
			
			
			/*
			 * While the Orders file has another line to read,
			 * Create a new Order object
			 * Store the values read from the current line
			 * Add the order to the Store
			*/ 
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
			// If the file does not exist, print the stack trace at the point of error
			e.printStackTrace();
		}

		// Print the Store Content (Customers, Products, and Orders)
		ourStore.printStoreContent();
		
		// Generate the Order Details from the existing Orders
		ourStore.generateOrderDetails();
		
		// Generate the Order Summary from the existing Orders
		ourStore.generateOrderSummary();
	
	}

}
