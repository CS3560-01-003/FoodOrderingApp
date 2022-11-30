DROP DATABASE IF EXISTS foodorderingapp;
CREATE DATABASE foodorderingapp; 
USE foodorderingapp;

Create Table User( 
    userID INT not null auto_increment,
    userName VARCHAR (45) NOT NULL,
    userAddress VARCHAR (45) NOT NULL,
    loginUser VARCHAR (45) NOT NULL,
    userEmail VARCHAR (45) NOT NULL,
    loginPassword VARCHAR (45) NOT NULL,
    accessType VARCHAR (45) NOT NULL,
    PRIMARY KEY (userID)
    );
    
Create Table Payment( 
    userID INT (6), 
    customerName VARCHAR (45),
    cardType VARCHAR (45) NOT NULL,
    cardNumber VARCHAR(45) NOT NULL,
    cardExp INT NOT NULL,
    cardSecure INT NOT NULL, 
    FOREIGN KEY (userID) REFERENCES User(userID),
    PRIMARY KEY (cardNumber)
    );
    
Create Table customerOrder(
	orderID INT not null auto_increment,
    userID INT,
    orderTime DATETIME,
    numOfProducts INT NOT NULL,
    cardNumber VARCHAR (45) NOT NULL,
    orderType VARCHAR (45) NOT NULL,
    orderStatus VARCHAR (45) NOT NULL DEFAULT 'pending',
    FOREIGN KEY (cardNumber) REFERENCES Payment(cardNumber),
    PRIMARY KEY (orderID)
    );
    
    Create Table menuItems(
	itemID INT not null auto_increment,
    itemName VARCHAR (45),
    itemDescription VARCHAR (45) NOT NULL,
    itemPrice DECIMAL (5,2) NOT NULL,
    itemImage LONGBLOB,
    itemAvailability VARCHAR (45) DEFAULT 'in stock',
    PRIMARY KEY (itemID)
    );
    
Create Table orderItems(
	itemID INT,
    itemQuantity INT NOT NULL,
    FOREIGN KEY (itemID) REFERENCES menuItems(itemID),
    PRIMARY KEY (itemID)
    );
    
INSERT INTO `foodorderingapp`.`menuitems` (`itemID`, `itemName`, `itemDescription`, `itemPrice`, `itemImage`, `itemAvailability`) VALUES ('001', 'Cheeseburger', 'juicy', '11.99', 'F:\foodOrderingApp\burger.jpg', 'in stock');
INSERT INTO `foodorderingapp`.`menuitems` (`itemID`, `itemName`, `itemDescription`, `itemPrice`, `itemImage`, `itemAvailability`) VALUES ('002', 'Boneless Wings', 'saucy', '16.99', 'F:\foodOrderingApp\wings.jpg', 'in stock');
INSERT INTO `foodorderingapp`.`menuitems` (`itemID`, `itemName`, `itemDescription`, `itemPrice`, `itemImage`, `itemAvailability`) VALUES ('003', 'Pecan Pie Slice', 'sweet', '3.99', 'F:\foodOrderingApp\pie.jpg', 'out of stock');
INSERT INTO `foodorderingapp`.`menuitems` (`itemID`, `itemName`, `itemDescription`, `itemPrice`, `itemImage`, `itemAvailability`) VALUES ('004', 'Jenny’s Breakfast', 'yum', '11.99', 'F:\foodOrderingApp\breakfastcustomerorder.jpg', 'in stock');

INSERT INTO `foodorderingapp`.`user` (`userID`, `userName`, `userAddress`, `loginUser`, `userEmail`, `loginPassword`, `accessType`) VALUES ('1', 'Priscilla Ballesteros', '3801 W Temple Ave, Pomona, CA 91768', 'Priscilla1', 'priscilla@gmail.com', 'p123', '1');
INSERT INTO `foodorderingapp`.`user` (`userID`, `userName`, `userAddress`, `loginUser`, `userEmail`, `loginPassword`, `accessType`) VALUES ('2', 'Anushka Venkatesh', '3802 W Temple Ave, Pomona, CA 91768', 'Anushka2', 'anushka@gmail.com', 'a123', '1');
INSERT INTO `foodorderingapp`.`user` (`userID`, `userName`, `userAddress`, `loginUser`, `userEmail`, `loginPassword`, `accessType`) VALUES ('3', 'Gelila Chaka', '3803 W Temple Ave, Pomona, CA 91768', 'Gelila3', 'gelila@gmail.com', 'g123', '1');
INSERT INTO `foodorderingapp`.`user` (`userID`, `userName`, `userAddress`, `loginUser`, `userEmail`, `loginPassword`, `accessType`) VALUES ('4', 'Melody Duong', '3804 W Temple Ave, Pomona, CA 91768', 'Melody4', 'melody@gmail.com', 'm123', '2');
INSERT INTO `foodorderingapp`.`user` (`userID`, `userName`, `userAddress`, `loginUser`, `userEmail`, `loginPassword`, `accessType`) VALUES ('5', 'Jasper Obiacoro', '3805 W Temple Ave, Pomona, CA 91768', 'Jasper5', 'jasper@gmail.com', 'j123', '2');
INSERT INTO `foodorderingapp`.`user` (`userID`, `userName`, `userAddress`, `loginUser`, `userEmail`, `loginPassword`, `accessType`) VALUES ('6', 'Sean Solomon', '3806 W Temple Ave, Pomona, CA 91768', 'Sean6', 'sean@gmail.com', 's123', '2');

INSERT INTO `foodorderingapp`.`payment` (`userID`, `customerName`, `cardType`, `cardNumber`, `cardExp`, `cardSecure`) VALUES ('1', 'Priscilla Ballesteros', 'Debit', '1111111111111111', '0122', '123');
INSERT INTO `foodorderingapp`.`payment` (`userID`, `customerName`, `cardType`, `cardNumber`, `cardExp`, `cardSecure`) VALUES ('2', 'Anushka Venkatesh', 'Debit', '2222222222222222', '0223', '456');
INSERT INTO `foodorderingapp`.`payment` (`userID`, `customerName`, `cardType`, `cardNumber`, `cardExp`, `cardSecure`) VALUES ('3', 'Gelila Chaka', 'Credit', '3333333333333333', '0324', '789');
INSERT INTO `foodorderingapp`.`payment` (`userID`, `customerName`, `cardType`, `cardNumber`, `cardExp`, `cardSecure`) VALUES ('4', 'Melody Duong', 'Debit', '4444444444444444', '0425', '987');
INSERT INTO `foodorderingapp`.`payment` (`userID`, `customerName`, `cardType`, `cardNumber`, `cardExp`, `cardSecure`) VALUES ('5', 'Jasper Obiacoro', 'Credit', '5555555555555555', '0526', '654');
INSERT INTO `foodorderingapp`.`payment` (`userID`, `customerName`, `cardType`, `cardNumber`, `cardExp`, `cardSecure`) VALUES ('6', 'Sean Solomon', 'Debit', '6666666666666666', '0627', '321');
