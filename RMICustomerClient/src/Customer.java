import java.io.Serializable;

/*+
 * This is the Customer class with it's attributes.
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
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public String getName() {
		return name;
	}
	public void setName(String n) {
		this.name = n;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
