/* 
 * The purpose of this skeleton code is to explain the attributes of an order. 
 * langauage: java
 * @author Priscilla
 * Date modified: Oct 22, 22
 */

import java.io.*;
import java.util.*;

public class Order 
{
    //Atributes of Order
        int orderID;
        int customerID;
        String orderDate;
        String orderTime;
        int numberOfProducts;
        String paymentType;
        String orderType;
        boolean orderStatus;

    //Constructor of Order class
        public Order(int orderID, int customerID, String orderDate, String orderTime, int numberOfProducts, String paymentType, String orderType)
        {
            this.orderID = orderID;
            this.customerID = customerID;
            this.orderDate = orderDate;
            this.orderTime = orderTime;
            this.numberOfProducts = numberOfProducts;
            this.paymentType = paymentType;
            this.orderType = orderType;
            this.orderStatus = orderStatus;

        }
        
    //setter methods for each class attribute 

        public void setOrderID(int orderID)
        {

        }
        public void setCustomerID(int customerID)
        {

        }
        public void setOrderDate(int orderDate)
        {

        }
        public void setOrderTime(int orderTime)
        {

        }
        public void setNumberOfProducts(int numberOfProducts)
        {

        }
        public void setPaymentType(int paymentType)
        {

        }
        public void setOrderType(int orderType)
        {

        }
        public void setOrderStatus(boolean orderStatus)
        {

        }

    //getter method for each class attribute

        public int getOrderID()
        {

        }
        public int getCustomerID()
        {

        }
        public String getOrderDate()
        {

        }
        public String getOrderTime()
        {

        }
        public int getNumberOfProducts()
        {

        }
        public String getPaymentType()
        {

        }
        public String getOrderType()
        {

        }
        public boolean getOrderStatus(boolean orderStatus)
        {

        }
}

