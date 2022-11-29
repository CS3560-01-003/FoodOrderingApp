DROP DATABASE IF EXISTS foodorderingapp;
CREATE DATABASE foodorderingapp; 
USE foodorderingapp;

Create Table User( 
    userID INT not null auto_increment,
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
	orderID INT not null auto_increment,
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
	itemID INT not null auto_increment,
    itemName VARCHAR (45),
    itemDescription VARCHAR (45) NOT NULL,
    itemPrice DECIMAL (5,2) NOT NULL,
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
    
INSERT INTO `foodorderingapp`.`menuitems` (`itemID`, `itemName`, `itemDescription`, `itemPrice`, `itemImage`, `itemAvailability`) VALUES ('001', 'Classic Cheeseburge', 'juicy', '11.99', ?, 'in stock');
INSERT INTO `foodorderingapp`.`menuitems` (`itemID`, `itemName`, `itemDescription`, `itemPrice`, `itemImage`, `itemAvailability`) VALUES ('002', 'Boneless Chicken Wings', 'saucy', '16.99', ?, 'in stock');
INSERT INTO `foodorderingapp`.`menuitems` (`itemID`, `itemName`, `itemDescription`, `itemPrice`, `itemImage`, `itemAvailability`) VALUES ('003', 'Pecan Pie Slice', 'sweet', '3.99', ?, 'out of stock');
INSERT INTO `foodorderingapp`.`menuitems` (`itemID`, `itemName`, `itemDescription`, `itemPrice`, `itemImage`, `itemAvailability`) VALUES ('004', 'Class Jenny’s Breakfast', 'yum', '11.99', ?, 'in stock');

