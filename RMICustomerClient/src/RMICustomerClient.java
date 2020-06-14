import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Scanner;

/**
 * This is the RMI customer class, which can control the Customer Manager RMI Server using the console.
 * All return values of the remote function are printed to the client's console.
 * @author samuelnegash1
 *
 */
public class RMICustomerClient {

	public static void main(String[] args) throws NotBoundException, IOException {
		String name = "//localhost/CustomerServer";
		ICustomerService remoteService = (ICustomerService) Naming.lookup(name);

		// Account a=remoteService.getCustomer(1);
		// System.out.println("ID: " + a.getCustomerID() + ", Name: " +a.getFname() + "
		// " + a.getLname() + ", ZIP: "+a.getZipCode());

		Scanner sc = new Scanner(System.in); // Create a Scanner object
		boolean programmRunning = true;
		do {
			System.out.println("-----------------------------");
			System.out.println("Customer-Management programm");
			System.out.println("-----------------------------");

			System.out.println("Enter: ");
			System.out.println("c - createCustomer");
			System.out.println("u - updateCustomer");
			System.out.println("d - deleteCustomer");
			System.out.println("a - getAllCustomers");
			System.out.println("g - getCustomer");
			System.out.println("az - getAllCustomersByZipCode");
			System.out.println("end - closeCustomerManager");
			System.out.println("-----------------------------");

			String command = sc.nextLine();

			int customerID;
			String cname;
			String zipCode;
			switch (command) {
			case "c":
				System.out.println("Please Enter:");
				System.out.println("CustomerID = ");
				customerID = Integer.parseInt(sc.nextLine());
				System.out.print("\nName = ");
				cname = sc.nextLine();
				System.out.print("\nZIP code = ");
				zipCode = sc.nextLine();

				remoteService.createCustomer(new Customer(customerID, cname, zipCode));

				break;
			case "u":
				System.out.println("Please Enter:");
				System.out.println("CustomerID of customer to who will be updated = ");
				customerID = Integer.parseInt(sc.nextLine());
				System.out.print("\nName = ");
				cname = sc.nextLine();
				System.out.print("\nZIP code = ");
				zipCode = sc.nextLine();
				if (remoteService.updateCustomer(new Customer(customerID, cname, zipCode))) {
					System.out.println("Customer update was succesfull!");
				} else {
					System.out.println("Customer update failed! Given Customer ID not existing!");
				}
				break;
			case "d":
				System.out.println("Please Enter:");
				System.out.println("CustomerID = ");
				customerID = Integer.parseInt(sc.nextLine());
				if (remoteService.deleteCustomer(customerID)) {
					System.out.println("Successfully deleted!");
				} else {
					System.out.println("Deleting failed!");
				}
				break;
			case "a":
				Customer[] customerList = remoteService.getAllCustomers();
				if (customerList.length > 0) {
					for (int i = 0; i < customerList.length; i++) {
						if (customerList[i] != null) {
							System.out.println("ID: " + customerList[i].getCustomerID() + ", Name: "
									+ customerList[i].getName() + ", ZIP: " + customerList[i].getZipCode());
						} else {
							break;
						}
					}
				} else {
					System.out.println("Es gibt keine Customer!");
				}
				break;
			case "g":
				System.out.println("Please Enter:");
				System.out.println("CustomerID = ");
				customerID = Integer.parseInt(sc.nextLine());
				Customer customer = remoteService.getCustomer(customerID);
				System.out.println("ID: " + customer.getCustomerID() + ", Name: " + customer.getName() + ", ZIP: "
						+ customer.getZipCode());
				break;
			case "az":
				System.out.println("Get Customers with this ZIPCode:");
				System.out.println("ZIP = ");
				zipCode = sc.nextLine();
				Customer[] customerListZip = remoteService.getAllCustomersByZipCode(zipCode);
				if (customerListZip.length > 0) {
					for (Customer a : customerListZip) {
						if (a != null) {
							System.out.println(
									"ID: " + a.getCustomerID() + ", Name: " + a.getName() + ", ZIP: " + a.getZipCode());
						}
					}
				} else {
					System.out.println("Es gibt keine Customer!");
				}
				break;
			case "end":
				remoteService.closeCustomerManager();
				programmRunning = false;
				break;
			default:
				break;
			}

		} while (programmRunning);
		sc.close();
	}

}
