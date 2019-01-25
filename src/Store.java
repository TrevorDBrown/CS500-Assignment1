/*
 * 	Bill's Computer Parts
 *  An application for managing orders for Bill's Computer Parts
 *  
 *  CS 500 - Spring 2019
 *  Trevor D. Brown
 *  
 *  Store.java - the Store class (central point for accessing other classes).
 */

import java.util.ArrayList;
import java.io.*;

public class Store {

	private ArrayList<Customer> ourCustomers;
	private ArrayList<Order> ourOrders;
	private ArrayList<Product> ourProducts;
		
	public Store() {
		this.ourCustomers = new ArrayList<Customer>();
		this.ourOrders = new ArrayList<Order>();
		this.ourProducts = new ArrayList<Product>();
	}
	
	public boolean addCustomer(Customer newCustomer) {
		if (!this.ourCustomers.contains(newCustomer)) {
			this.ourCustomers.add(newCustomer);
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean removeCustomer(Customer currentCustomer) {
		if(this.ourCustomers.contains(currentCustomer)) {
			this.ourCustomers.remove(currentCustomer);			
			return true;
		}else {
			return false;
		}
	}
	
	public Customer getCustomer(int customerID) {
		for (Customer currentCustomer: this.ourCustomers) {
			if (currentCustomer.getCustomerID() == customerID) {
				return currentCustomer;
			}
		}
		
		return new Customer();
	}
	
	public boolean addOrder(Order newOrder) {
		if(!this.ourOrders.contains(newOrder)) {
			this.ourOrders.add(newOrder);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean removeOrder(Order currentOrder) {
		if(this.ourOrders.contains(currentOrder)) {
			this.ourOrders.remove(currentOrder);
			return true;
		}else {
			return false;
		}
	}
	
	public Order getOrder(int customerID, int productID) {
		for (Order currentOrder: this.ourOrders) {
			if (currentOrder.getCustomerID() == customerID 
					&& currentOrder.getProductID() == productID) {
				return currentOrder;
			}
		}
		
		return new Order();
		
	}
	
	public boolean addProduct(Product newProduct) {
		if (!this.ourProducts.contains(newProduct)) {
			this.ourProducts.add(newProduct);
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean removeProduct(Product currentProduct) {
		if (this.ourProducts.contains(currentProduct)) {
			this.ourProducts.remove(currentProduct);
			return true;
		}else {
			return false;
		}
	}
	
	public Product getProduct(int productID) {
		for (Product currentProduct: this.ourProducts) {
			if (currentProduct.getProductID() == productID) {
				return currentProduct;
			}
		}
		
		return new Product();
	}
	
	public void generateOrderDetails() {
		File orderDetails = new File("orderdetails.txt");
		
		// Create orderdetails.txt if it does not exist.
		if (!orderDetails.exists()) {
			try {
				System.out.println("orderdetails.txt does not exist. Creating now.");
				orderDetails.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileWriter fileWriter = new FileWriter(orderDetails);
			
			String orderDetailsFileHeading = "Order Details\n";
			System.out.println("\n" + orderDetailsFileHeading);
			fileWriter.write(orderDetailsFileHeading);
			
			String orderDetailsColumnHeadings = "Customer Name\tProduct Name\tPrice\tQuantity\tOrder Amount\tDiscount\n";
			System.out.print(orderDetailsColumnHeadings);
			fileWriter.write(orderDetailsColumnHeadings);
			
			for (Order currentOrder: this.ourOrders) {
				String customerName = this.getCustomer(currentOrder.getCustomerID()).getCustomerName();
				String productName = this.getProduct(currentOrder.getProductID()).getProductName();
				double productPrice = this.getProduct(currentOrder.getProductID()).getProductPrice();
				int quantity = currentOrder.getQuantity();
				double discount = this.getCustomer(currentOrder.getCustomerID()).getDiscountEligibility() == true?.1:0;
				double orderAmount = (productPrice * quantity) * (1 - discount);
				double actualDiscount = (productPrice * quantity) * discount;
				
				String orderDetailRecord = customerName + "\t\t" + productName + "\t\t" + productPrice + "\t\t" + quantity + "\t\t" + orderAmount + "\t\t" + actualDiscount + "\n";
				System.out.print(orderDetailRecord);
				fileWriter.write(orderDetailRecord);
			}
			
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public void generateOrderSummary() {
		File orderSummary = new File("ordersummary.txt");
		
		// Create ordersummary.txt if it does not exist.
		if (!orderSummary.exists()) {
			try {
				System.out.println("ordersummary.txt does not exist. Creating now.");
				orderSummary.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileWriter fileWriter = new FileWriter(orderSummary);
			
			String orderSummaryFileHeading = "Order Summary\n";
			System.out.println("\n" + orderSummaryFileHeading);
			fileWriter.write(orderSummaryFileHeading);
			
			String orderSummaryColumnHeadings = "Customer Name\tTotal Amount\n";
			System.out.print(orderSummaryColumnHeadings);
			fileWriter.write(orderSummaryColumnHeadings);
			
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<Double> totals = new ArrayList<Double>();
			
			for (Order currentOrder: this.ourOrders) {
				String customerName = this.getCustomer(currentOrder.getCustomerID()).getCustomerName();
				double productPrice = this.getProduct(currentOrder.getProductID()).getProductPrice();
				int quantity = currentOrder.getQuantity();
				double discount = this.getCustomer(currentOrder.getCustomerID()).getDiscountEligibility() == true?.1:0;
				double orderAmount = (productPrice * quantity) * (1 - discount);
				
				if (!names.contains(customerName)) {
					names.add(customerName);
					totals.add(orderAmount);
				}else {
					int currentCustomerIndex = names.indexOf(customerName);
					totals.set(currentCustomerIndex, totals.get(currentCustomerIndex) + orderAmount);
				}
			}
			
			while (!totals.isEmpty()) {
				int largestTotalIndex = 0;
				double largestTotal = 0;
				
				for (double currentTotal: totals) {
					if (currentTotal > largestTotal) {
						largestTotal = currentTotal;
						largestTotalIndex = totals.indexOf(largestTotal);
					}
				}
				
				String totalsRecord = names.get(largestTotalIndex) + "\t" + totals.get(largestTotalIndex) + "\n";
				System.out.print(totalsRecord);
				fileWriter.write(totalsRecord);
				
				names.remove(largestTotalIndex);
				totals.remove(largestTotalIndex);
				
			}
			
			fileWriter.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void printStoreContent() {
		System.out.println("Bill's Computer Parts");
		
		System.out.println("\nOur Customers:");
		for (Customer currentCustomer: this.ourCustomers) {
			System.out.println(currentCustomer.toString());
		}
		
		System.out.println("\nOur Products:");
		for (Product currentProduct: this.ourProducts) {
			System.out.println(currentProduct.toString());
		}
		
		System.out.println("\nOur Orders:");
		for (Order currentOrder: this.ourOrders) {
			System.out.println(currentOrder.toString());
		}
	}
	
	
}
