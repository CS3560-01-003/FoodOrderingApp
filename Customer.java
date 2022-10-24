/** 
 * The purpose of this skeleton code is to explain the attributes and methods of an customer class. 
 * langauage: java
 * @author Jasper Obiacoro
 * Date modified: Oct 23, 2022
 */


public class Customer 
{
	
	//class attributes of customer
	private int customerID;
	private String customerName;
	private String address;
	private String phoneNum;
	private String email;
	
	//Constructor method for Customer object
	public Customer(int customerID, String customerName, String address, String phoneNum, String email)
	{
		this.customerID = customerID;
		this.customerName = customerName;
		this.address = address;
		this.phoneNum = phoneNum;
		this.email = email;
	}
	
	//Setter method for each class attribute
	public void setCustomerID(int CustomerID)
	{
		
	}
	
	public void setCustomerName(String CustomerName)
	{
		
	}
	
	public void setAddress(String address)
	{
		
	}
	
	public void setPhoneNum(String phoneNum)
	{
		
	}
	
	public void setEmail(String email)
	{
		
	}
	
	//Getter methods for each class attribute
	public int getCustomerID()
	{
		return customerID;
	}
	
	public String getCustomerName()
	{
		return customerName;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getPhoneNum()
	{
		return phoneNum;
	}
	
	
	public String getEmail()
	{
		return email;
	}
	
}
