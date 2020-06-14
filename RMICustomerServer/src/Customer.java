import java.io.Serializable;

/**
 * This is the Costumer class with it's attributes.
 * @author samuelnegash1
 *
 */
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int CustomerID;
	private String name;
	private String zipCode;
	
	public Customer(int id, String n, String z) {
		this.CustomerID = id;
		this.name = n;
		this.zipCode = z;
	}
	
	public int getCustomerID() {
		return this.CustomerID;
	}
	public void setCustomerID(int customerID) {
		this.CustomerID = customerID;
	}

	public String getName() {
		return name;
	}
	public void setName(String n) {
		this.name = n;
	}
	public String getZipCode() {
		return this.zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
