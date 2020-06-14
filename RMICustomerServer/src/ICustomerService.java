import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This is an interface with all function which are implemented in the CustomerManager class.
 * @author samuelnegash1
 *
 */
public interface ICustomerService extends Remote{
	public ArrayList<Customer> getCustomerContainer() throws RemoteException;
	
	public void createCustomer(Customer input) throws RemoteException;
	
	public Customer getCustomer(int id) throws RemoteException;
	
	public boolean updateCustomer(Customer input) throws RemoteException;
	
	public boolean deleteCustomer(int CustomerID) throws RemoteException;
	
	public Customer[] getAllCustomers() throws RemoteException;
		
	public Customer[] getAllCustomersByZipCode(String zipCode) throws RemoteException;
	
	public void closeCustomerManager() throws RemoteException, IOException, NotBoundException, FileNotFoundException;

}
