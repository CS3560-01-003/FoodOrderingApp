/* 
 * The purpose of this skeleton code is to explain the attributes of an order. 
 * langauage: java
 * @author Priscilla
 * Date modified: Oct 22, 22
 */

import java.io.*;
import java.util.*;

class Order 
{
    //Atributes of Order
        int orderID;
        int customerID;
        String orderDate;
        String orderTime;
        int numberOfProducts;
        String paymentType;
        String orderType;

    //Constructor of Order class
        Order(int orderID, int customerID, String orderDate, String orderTime, int numberOfProducts, String paymentType, String orderType)
        {
            this.orderID = orderID;
            this.customerID = customerID;
            this.orderDate = orderDate;
            this.orderTime = orderTime;
            this.numberOfProducts = numberOfProducts;
            this.paymentType = paymentType;
            this. orderType = orderType;

        }


}

