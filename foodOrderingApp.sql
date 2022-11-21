DROP DATABASE IF EXISTS foodorderingapp;
CREATE DATABASE foodorderingapp; 
USE foodorderingapp;

Create Table User( 
    userID INT,
    userName VARCHAR (45) NOT NULL,
    userAddress VARCHAR (45) NOT NULL,
    loginUser VARCHAR (45) NOT NULL,
    userEmail INT (45) NOT NULL,
    loginPassword VARCHAR (45) NOT NULL,
    accessType VARCHAR (45) NOT NULL,
    PRIMARY KEY (userID)
    );
    
Create Table Payment( 
    userID INT (6), 
    customerName VARCHAR (45),
    cardType VARCHAR (45) NOT NULL,
    cardNumber INT NOT NULL,
    cardExp INT NOT NULL,
    cardSecure INT NOT NULL, 
    FOREIGN KEY (userID) REFERENCES User(userID),
    PRIMARY KEY (cardNumber)
    );
    
Create Table customerOrder(
	orderID INT,
    userID INT,
    orderDate DATETIME,
    orderTime DATETIME,
    numOfProducts INT NOT NULL,
    cardNumber INT NOT NULL,
    orderType VARCHAR (45),
    orderStatus VARCHAR (45),
    FOREIGN KEY (cardNumber) REFERENCES Payment(cardNumber),
    PRIMARY KEY (orderID)
    );
    
    Create Table menuItems(
	itemID INT,
    itemName VARCHAR (45),
    itemDescription VARCHAR (45) NOT NULL,
    itemPrice DECIMAL (2,0) NOT NULL,
    itemImage LONGBLOB,
    itemAvailability VARCHAR (45) NOT NULL,
    PRIMARY KEY (itemID)
    );
    
Create Table orderItems(
	itemID INT,
    itemQuantity INT NOT NULL,
    FOREIGN KEY (itemID) REFERENCES menuItems(itemID),
    PRIMARY KEY (itemID)
    );
