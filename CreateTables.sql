-- Create User table
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255),
    Email VARCHAR(255),
    Password VARCHAR(255),
    RoleID INT,
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);

-- Create Role table
CREATE TABLE Role (
    RoleID INT AUTO_INCREMENT PRIMARY KEY,
    RoleName VARCHAR(255)
);

-- Create Restaurant table
CREATE TABLE Restaurant (
    RestaurantID INT AUTO_INCREMENT PRIMARY KEY,
    RestaurantName VARCHAR(255),
    Location VARCHAR(255),
    ContactInfo VARCHAR(255),
    MenuID INT,
    FOREIGN KEY (MenuID) REFERENCES Menu(MenuID)
);

-- Create Menu table
CREATE TABLE Menu (
    MenuID INT AUTO_INCREMENT PRIMARY KEY,
    MenuItemID INT AUTO_INCREMENT PRIMARY KEY,
    ItemName VARCHAR(255),
    Description VARCHAR(255),
    Price DECIMAL(10, 2),
    RestaurantID INT,
    FOREIGN KEY (RestaurantID) REFERENCES Restaurant(RestaurantID)
);

-- Create Order table
CREATE TABLE Order (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    RestaurantID INT,
    OrderDate DATE,
    Status VARCHAR(255),
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (RestaurantID) REFERENCES Restaurant(RestaurantID)
);

-- Create OrderItem table
CREATE TABLE OrderItem (
    OrderItemID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT,
    MenuItemID INT,
    Quantity INT,
    FOREIGN KEY (OrderID) REFERENCES Order(OrderID),
    FOREIGN KEY (MenuItemID) REFERENCES Menu(MenuItemID)
);
