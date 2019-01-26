/*
 * 	Bill's Computer Parts
 *  An application for managing orders for Bill's Computer Parts
 *  
 *  CS 500 - Spring 2019
 *  Trevor D. Brown
 *  
 *  Store.java - the Store class (central point for accessing other classes).
 */

import java.util.ArrayList;		// ArrayList object (from util package; a collection used for dynamic memory allocation)
import java.io.*;				// io package (for File operations)

public class Store {

	// Private variables
	private ArrayList<Customer> ourCustomers;	// ourCustomers - an ArrayList of Customer objects
	private ArrayList<Order> ourOrders;			// ourOrders - an ArrayList of Order objects
	private ArrayList<Product> ourProducts;		// ourProducts - an ArrayList of Product objects
		
	// Store constructor - paramaterless; initializes the ArrayLists;
	public Store() {
		this.ourCustomers = new ArrayList<Customer>();
		this.ourOrders = new ArrayList<Order>();
		this.ourProducts = new ArrayList<Product>();
	}
	
	/*
	 * addCustomer: Customer object parameter; adds a Customer object to the ourCustomers ArrayList, 
	 * if the Customer does not currently exist.
	 */
	public void addCustomer(Customer newCustomer) {
		if (!this.ourCustomers.contains(newCustomer)) {
			this.ourCustomers.add(newCustomer);
		}
	}
	
	/*
	 * removeCustomer: Customer object parameter; removes a Customer object to the ourCustomers ArrayList, 
	 * if the Customer currently exist.
	 */
	public void removeCustomer(Customer currentCustomer) {
		if(this.ourCustomers.contains(currentCustomer)) {
			this.ourCustomers.remove(currentCustomer);			
		}
	}
	
	/*
	 * getCustomer: customerID (int) parameter; returns Customer object, if one exists.
	 * Otherwise, a generic "erroneous" Customer is returned to indicate failure.
	 */
	public Customer getCustomer(int customerID) {
		for (Customer currentCustomer: this.ourCustomers) {
			if (currentCustomer.getCustomerID() == customerID) {
				return currentCustomer;
			}
		}
		
		return new Customer();
	}
	
	/*
	 * addOrder: Order object parameter; adds an Order object to the ourOrders ArrayList, 
	 * if the Order does not currently exist.
	 */
	public void addOrder(Order newOrder) {
		if(!this.ourOrders.contains(newOrder)) {
			this.ourOrders.add(newOrder);
		}
	}
	
	/*
	 * removeOrder: Order object parameter; removes an Order object to the ourOrders ArrayList, 
	 * if the Order currently exist.
	 */
	public boolean removeOrder(Order currentOrder) {
		if(this.ourOrders.contains(currentOrder)) {
			this.ourOrders.remove(currentOrder);
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * getCustomer: customerID and productID (int and int) parameters; returns Order object, if one exists.
	 * Otherwise, a generic "erroneous" Order is returned to indicate failure.
	 */
	public Order getOrder(int customerID, int productID) {
		for (Order currentOrder: this.ourOrders) {
			if (currentOrder.getCustomerID() == customerID 
					&& currentOrder.getProductID() == productID) {
				return currentOrder;
			}
		}
		
		return new Order();
		
	}
	
	/*
	 * addProduct: Product object parameter; adds a Product object to the ourProducts ArrayList, 
	 * if the Product does not currently exist.
	 */
	public void addProduct(Product newProduct) {
		if (!this.ourProducts.contains(newProduct)) {
			this.ourProducts.add(newProduct);
		}
		
	}
	
	/*
	 * removeProduct: Product object parameter; removes a Product object to the ourProducts ArrayList, 
	 * if the Product currently exist.
	 */
	public void removeProduct(Product currentProduct) {
		if (this.ourProducts.contains(currentProduct)) {
			this.ourProducts.remove(currentProduct);
		}
	}
	
	/*
	 * getCustomer: productID (int) parameter; returns Product object, if one exists.
	 * Otherwise, a generic "erroneous" Product is returned to indicate failure.
	 */
	public Product getProduct(int productID) {
		for (Product currentProduct: this.ourProducts) {
			if (currentProduct.getProductID() == productID) {
				return currentProduct;
			}
		}
		
		return new Product();
	}
	
	/*
	 * generateOrderDetails: parameterless;
	 * This method generates a new file "orderdetails.txt", if it doesn't exist.
	 * 
	 * Then, the method iterates through the collection of orders in ourOrders.
	 * 
	 * Additional Customer and Product data are pulled from the associated objects, using
	 * the customerID and productID found in the ourOrders ArrayList. 
	 * 
	 * Then, the discount and price are computed, using data collected from the other objects.
	 * 
	 * Finally, the record is written to the file per order.
	 */
	public void generateOrderDetails() {
		File orderDetails = new File("orderdetails.txt"); // orderdetails.txt File
		
		// Create orderdetails.txt if it does not exist.
		if (!orderDetails.exists()) {
			try {
				System.out.println("orderdetails.txt does not exist. Creating now.");
				orderDetails.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// try/catch for the FileWriter operations.
		try {
			FileWriter fileWriter = new FileWriter(orderDetails);
			
			// Write the File header to the file.
			String orderDetailsFileHeading = "Order Details\n";
			System.out.println("\n" + orderDetailsFileHeading);
			fileWriter.write(orderDetailsFileHeading);
			
			// Write the column headers to the file.
			String orderDetailsColumnHeadings = "Customer Name\tProduct Name\tPrice\tQuantity\tOrder Amount\tDiscount\n";
			System.out.print(orderDetailsColumnHeadings);
			fileWriter.write(orderDetailsColumnHeadings);
			
			/*
			 * Loop through all Orders in ourOrders.
			 * This loop also extrapolates data from other objects (Customer, Product) based on identifiers
			 * found in the Order object.
			 */
			for (Order currentOrder: this.ourOrders) {
				// Get the data from existing objects related to the order.
				String customerName = this.getCustomer(currentOrder.getCustomerID()).getCustomerName();
				// If the customer name is not blank, proceed with calculations.
				// Otherwise, return an error and proceed with next order.
				if (!customerName.equals("")) {
					String productName = this.getProduct(currentOrder.getProductID()).getProductName();
					double productPrice = this.getProduct(currentOrder.getProductID()).getProductPrice();
					int quantity = currentOrder.getQuantity();
					double discount = this.getCustomer(currentOrder.getCustomerID()).getDiscountEligibility() == true?.1:0;
					
					/* 
					 * Compute the order amount using the formula: (price * quantity) * (1 - discount)
					 * discount will either be 0 (no discount) or .1 (10% discount)
					 */ 
					double orderAmount = (productPrice * quantity) * (1 - discount);
					/*
					 * Computer the actual discount using the formula: (price * quantity) * (discount)
					 * discount will either be 0 (making discount 0) or .1 (10% of the original price)
					 */
					double actualDiscount = (productPrice * quantity) * discount;
					
					// Write the record the file
					String orderDetailRecord = customerName + "\t\t" + productName + "\t\t" + productPrice + "\t\t" + quantity + "\t\t" + orderAmount + "\t\t" + actualDiscount + "\n";
					System.out.print(orderDetailRecord);
					fileWriter.write(orderDetailRecord);
				}else {
					// Customer Not Found error message
					System.out.println("Error - Customer does not exist. Skipping order.");
				}
				
			}
			
			fileWriter.close();	// Close the FileWriter object (this frees the file for use elsewhere)
			
		} catch (IOException e) {
			// If a file I/O issue occurs, return the stack trace.
			e.printStackTrace();
		}
		
	}

	/*
	 * generateOrderSummary: parameterless;
	 * This method generates a new file "ordersummary.txt", if it doesn't exist.
	 * 
	 * Then, the method iterates through the collection of orders in ourOrders.
	 * 
	 * Additional Customer and Product data are pulled from the associated objects, using
	 * the customerID and productID found in the ourOrders ArrayList. 
	 * 
	 */
	public void generateOrderSummary() {
		File orderSummary = new File("ordersummary.txt");	// ordersummary.txt File
		
		// Create ordersummary.txt if it does not exist.
		if (!orderSummary.exists()) {
			try {
				System.out.println("ordersummary.txt does not exist. Creating now.");
				orderSummary.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// try/catch for the FileWriter operations.
		try {
			FileWriter fileWriter = new FileWriter(orderSummary);
			
			// Write the file header to the file
			String orderSummaryFileHeading = "Order Summary\n";
			System.out.println("\n" + orderSummaryFileHeading);
			fileWriter.write(orderSummaryFileHeading);
			
			// Write the columns header to the file
			String orderSummaryColumnHeadings = "Customer Name\tTotal Amount\n";
			System.out.print(orderSummaryColumnHeadings);
			fileWriter.write(orderSummaryColumnHeadings);
			
			/*
			 * Use two array lists to store the name of the Customer,
			 * and the current running total of orders the customer.
			*/
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<Double> totals = new ArrayList<Double>();
			
			/*
			 * Loop through all Orders in ourOrders.
			 * This loop also extrapolates data from other objects (Customer, Product) based on identifiers
			 * found in the Order object.
			 */
			for (Order currentOrder: this.ourOrders) {
				// Get the data from existing objects related to the order.
				String customerName = this.getCustomer(currentOrder.getCustomerID()).getCustomerName();
				// If the customer name is not blank, proceed with calculations.
				// Otherwise, return an error and proceed with next order.
				if (!customerName.equals("")){
					double productPrice = this.getProduct(currentOrder.getProductID()).getProductPrice();
					int quantity = currentOrder.getQuantity();
					double discount = this.getCustomer(currentOrder.getCustomerID()).getDiscountEligibility() == true?.1:0;
					
					/* 
					 * Compute the order amount using the formula: (price * quantity) * (1 - discount)
					 * discount will either be 0 (no discount) or .1 (10% discount)
					 */ 
					double orderAmount = (productPrice * quantity) * (1 - discount);
					
					/*
					 * if the name of the Customer does not currently exist in the names ArrayList,
					 * add the name, and the order amount to both the names and totals ArrayLists, respectively.
					 * 
					 * Otherwise, find the index of the Customer's name, and add the current total to the existing total.
					 */
					if (!names.contains(customerName)) {
						names.add(customerName);
						totals.add(orderAmount);
					}else {
						int currentCustomerIndex = names.indexOf(customerName);
						totals.set(currentCustomerIndex, totals.get(currentCustomerIndex) + orderAmount);
					}
				}else {
					// Customer Not Found error message
					System.out.println("Error - Customer does not exist. Skipping order.");
				}
				
			}
			
			/*
			 * While the totals ArrayList is not empty,
			 * Loop through the totals to determine the largest total.
			 * Once the total is found, use the index of that total to get the Customer's name.
			 * Then, write that name and amount record to the file.
			 * Finally, remove the name and amount from their respective ArrayLists.
			 */
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
			
			fileWriter.close();		// Close the FileWriter object (this frees the file for use elsewhere)
		} catch (IOException e) {
			// If a file I/O issue occurs, return the stack trace.
			e.printStackTrace();
		}
		
	}
	
	/*
	 * printStoreContent - parameterless;
	 * This method prints a complete summary of the Store
	 * This includes:
	 * 		The Store Name
	 * 		All Customers
	 * 		All Products
	 * 		All Orders
	 */
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
