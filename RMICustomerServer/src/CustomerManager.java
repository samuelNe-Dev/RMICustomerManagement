import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author samuelnegash1
 *
 */
public class CustomerManager extends UnicastRemoteObject implements ICustomerService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Customer> customerContainer=new ArrayList<Customer>();

	
	/**
	 * Getter for the ArrayList customerContainer.
	 */
	public ArrayList<Customer> getCustomerContainer(){
		return customerContainer;
	}
	
	
	protected CustomerManager() throws RemoteException {
		super();
	}

	
	/**
	 * Adds a Customer to the ArrayList customerContainer.
	 */
	public void createCustomer(Customer input) {
		customerContainer.add(input);
	}

	/**
	 * Get a customer with the chosen ID.
	 */
	public Customer getCustomer(int id) {
		for (int i = 0; i < customerContainer.size(); i++) {
			Customer c=customerContainer.get(i);
			if(c.getCustomerID() == id) {
				return c;
			}
		}
		return null;
	}


	/**
	 * Update information of customer with the chosen ID.
	 */
	public boolean updateCustomer(Customer input) throws RemoteException {
		boolean successfullUpdate = false;
		for(Customer a : customerContainer) {
			if(a.getCustomerID() == input.getCustomerID()) {
				a.setName(input.getName());
				a.setZipCode(input.getZipCode());
				successfullUpdate = true;
				break;
			}
			else {
				continue;
			}
		}
		
		return successfullUpdate;
	}


	/**
	 * Delete customer with the chosen ID.
	 */
	public boolean deleteCustomer(int CustomerID) throws RemoteException {
		for(Customer a : customerContainer) {
			if(a.getCustomerID()==CustomerID) {
				return customerContainer.remove(a);
			}
		}
		return false;
	}


	/**
	 * Get all Customers, which are added to the ArrayList customerContainer.
	 */
	public Customer[] getAllCustomers() throws RemoteException {
		Customer[] allCustomers = new Customer[100];
		for(int i = 0; i < customerContainer.size(); i++) {
			allCustomers[i] = customerContainer.get(i);	
		}
		return allCustomers;
	}


	/**
	 * Loads all customers which has been previously saved.
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws NotBoundException
	 * @throws FileNotFoundException
	 * @throws AlreadyBoundException
	 */
	public void initializeCustomerManager() throws RemoteException, MalformedURLException, NotBoundException, FileNotFoundException, AlreadyBoundException {
		String name="//localhost/CustomerServer";
		Naming.bind(name, this);
		System.out.println("Server started");
		
		File file = new File("/Users/samuelnegash1/Desktop/customerlist.txt/");
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextLine()) {
			String[] costumer = sc.nextLine().split("\\s+");
			customerContainer.add(new Customer(Integer.parseInt(costumer[0]), costumer[1] + " " + costumer[2], costumer[3]));
		}
		sc.close();
	}


	/**
	 * All informations about the customers are saved in a single file, and the server shuts down.
	 */
	public void closeCustomerManager() throws RemoteException, IOException, NotBoundException {
		String filePath = "/Users/samuelnegash1/Desktop/customerlist.txt/";
		
		File file = new File(filePath);
		FileWriter fw = new FileWriter(file,false);
		PrintWriter pw = new PrintWriter(fw);
		
		
		for(Customer a : customerContainer) {
			pw.print(a.getCustomerID() + " ");
			pw.print(a.getName() + " ");
			pw.println(a.getZipCode());
		}
		pw.close();
		Naming.unbind("//localhost/CustomerServer");

        //removes us from the RMI runtime
        UnicastRemoteObject.unexportObject(this, true);

        System.out.println("Server exiting.");
	}

	/**
	 * Gets all customers with the chosen Zip-code.
	 */
	public Customer[] getAllCustomersByZipCode(String zipCode) throws RemoteException {
		Customer[] allCustomers = new Customer[100];
		for(int i = 0; i < customerContainer.size(); i++) {
			if(customerContainer.get(i).getZipCode().equals(zipCode)) {
				allCustomers[i] = customerContainer.get(i);					
			}
			else {
				continue;
			}
		}
		return allCustomers;
	}

}
