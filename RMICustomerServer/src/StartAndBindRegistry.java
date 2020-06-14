import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * This class starts and binds the registry.
 * @author samuelnegash1
 *
 */
public class StartAndBindRegistry {

	public static void main(String[] args) throws MalformedURLException, AlreadyBoundException, NotBoundException, FileNotFoundException {		
		try {
			LocateRegistry.createRegistry(1099);

			CustomerManager am=new CustomerManager();
			am.initializeCustomerManager();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("could not start registry");
		}

	}

}