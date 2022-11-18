DROP DATABASE IF EXISTS foodorderingapp;
CREATE DATABASE foodorderingapp; 
USE foodorderingapp;

Create Table Customer( 
    customer_ID VARCHAR (50),
    customer_Name VARCHAR (50),
    address VARCHAR (10),
    phone_Number INT (11),
    email VARCHAR (50),
    PRIMARY KEY (customer_ID)
    );
    
Create Table Payment( 
    payment_ID INT (6), 
    customer_ID VARCHAR (50),
    card_Name VARCHAR (50) NOT NULL,
    cardType VARCHAR (10) NOT NULL,
    card_Number INT (16) NOT NULL,
    cardExp INT (4) NOT NULL,
    cardSecure int (3) NOT NULL, 
    FOREIGN KEY (customer_ID) REFERENCES customer(customer_ID),
    PRIMARY KEY (payment_ID)
    );
    

