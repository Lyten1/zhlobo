Create table Customers
(ID_C Number(7)
  CONSTRAINT Cus_ID_C PRIMARY KEY,
  Full_name VARCHAR2(30),
  Email  VARCHAR2(30),
  Phone_number  VARCHAR2(30),
  Password_  VARCHAR2(30),
  Created_at TIMESTAMP,
  ID_B Number(7)
  CONSTRAINT Cus_ID_B_fk REFERENCES Balances(ID_B));
  
  INSERT INTO Customers (ID_C, Full_name, Email, Phone_number, Password_, created_at)
Values (01, "Артем Новак", "whereismycat@gmail.com", +380834020665, "fjhvjgvhnei", 2022-10-30 10:45),
       (02, "Денис Ковальский", "dondeestamigato@gmail.com", +380370272338, "fberikerkrg", 2022-10-30 14:09),
       (03, "Данило Вишневський", "ondeestaomeugato@gmail.com", +380589144501, 2022-10-30 14:24);
COMMIT;
       
Create table Balances
(ID_B NUMBER(7)
  CONSTRAINT Bal_ID_B PRIMARY KEY,
  Balances_status,
  Value Decimal,
ID_C Number(7)
  CONSTRAINT Bal_ID_C_fk REFERENCES Customers(ID_C),
 ID_S Number(7)
  CONSTRAINT Bal_ID_S_fk REFERENCES Sellers(ID_S));
  
CREATE TABLE Images
(ID_I Number(7)
 CONSTRAINT Ima_ID_I PRIMARY KEY,
 Uni VARCHAR2(30);
 
 
CREATE TABLE Products
(ID_P Number(7)
 CONSTRAINT Pr_ID_P PRIMARY KEY,
 Products_status,
 Title VARCHAR2(30),
 Price Decimal,
 Img,
 ID_I Number(7)
  CONSTRAINT Pro_ID_I_fk REFERENCES Images(ID_I),
 ID_S Number(7)
  CONSTRAINT Pro_ID_S_fk REFERENCES Sellers(ID_S));
  
  INSERT INTO Products (ID_P, ID_S, Title, Price, img)
Values (01, SELECT ID_S FROM Sellers, "Coca-Cola Light", 0,5l, 14.40, img),
       (02, SELECT ID_S FROM Sellers, "Chicken McNuggets", 6st, 37.20, img),
       (03, SELECT ID_S FROM Sellers, "Pepsi Zero", 1,5l, 19.60, img);
COMMIT;


CREATE TABLE Orders
(ID_O Number(7)
 CONSTRAINT OR_ID_O PRIMARY KEY,
 Created_at TIMESTAMP,
 Order_time TIMESTAMP,
 Delivery_address VARCHAR2(40),
 Order_status,
 ID_C Number(7)
  CONSTRAINT Bal_ID_C_fk REFERENCES Customers(ID_C));
  
INSERT INTO Orders (ID_O, ID_C, Created_at, Address)
VALUES (01, SELECT ID_C FROM Customers, 2023-09-03 14:09, "Жуйка 3"),
       (02, SELECT ID_C FROM Customers, 2023-09-03 14:10, "Івана Садовського 5А"),
       (03, SELECT ID_C FROM Customers, 2023-09-03 14:19, "Корабельна вулиця 8");
COMMIT;


CREATE TABLE Cities
(ID_Ci Number(7)
 CONSTRAINT Cit_ID_Ci PRIMARY KEY,
 Title VARCHAR2(30));

CREATE TABLE Orders_Products
(ID_OP Number(7) 
 CONSTRAINT Ord_ID_OP PRIMARY KEY,
 Quantity Number(7),
 ID_O Number(7)
  CONSTRAINT OP_ID_O_fk REFERENCES Orders(ID_O),
ID_P Number(7)
 CONSTRAINT OP_ID_P_fk REFERENCES Products(ID_P));


INSERT INTO orders_products (ID_OP, ID_O, ID_P, count)
VALUES (01, SELECT ID_O FROM Orders, SELECT ID_P FROM Products, 37.20),
       (02, SELECT ID_O FROM Orders, SELECT ID_P FROM Products, 19.60),
       (03, SELECT ID_O FROM Orders, SELECT ID_P FROM Products, 14.40);
COMMIT;


CREATE TABLE Couriers
(ID_Co Number(7)
 CONSTRAINT Cou_ID_Co PRIMARY KEY,
  Full_name VARCHAR2(30),
  Couriere_type VARCHAR2(30),
  Email  VARCHAR2(30),
  Phone_number  VARCHAR2(30),
  Password_  VARCHAR2(30),
 ID_Ci Number(7)
   CONSTRAINT Cou_ID_Ci_fk REFERENCES Cities(ID_Ci));
 ID_B Number(7)
  CONSTRAINT Cou_ID_B_fk REFERENCES Balances(ID_B));

INSERT INTO Couriers (ID_Co, Courier_type, Full_name, Email, Phone_number)
VALUES (01, "Бесконтактна доставка", "ЄваВойцеховська", "woistmeinekatze@gmail.com", +380236251451),
      (02, "Overnight доставка", "СофіяКовальчук", "ngendikucingku@gmail.com", +380501679396),
      (03, NULL, "Ольга Каміньска", "ouestmonchat@gmail.com", +380488946584);
COMMIT;


CREATE TABLE Sellers
(ID_S Number(7)
 CONSTRAINT Sel_ID_S PRIMARY KEY,
  Full_name VARCHAR2(30),
  Email  VARCHAR2(30),
  Phone_number  VARCHAR2(30),
  Password_  VARCHAR2(30)
  Company_name VARCHAR2(30),
  Address VARCHAR2(30),
  City VARCHAR2(30),
  Seller_type Varchar2(30),
 ID_I Number(7)
  CONSTRAINT Sel_ID_I_fk REFERENCES Images(ID_I),
 ID_Ci Number(7)
   CONSTRAINT Sel_ID_Ci_fk REFERENCES Cities(ID_Ci));
   
   INSERT INTO Sellers (ID_S, Seller_type, Password_, City, Phone_number, Company_name, Address)
Values (01, "Продавці усього", "fvsdtbdbrr", "Житомир", +380125140340, "McDonald's", "Київська 77"),
       (02, "Продавці усього", "vjneujkrn", "Житомир", +380490568285, "KFC", "Київська 77"),
       (03, NULL, "ihc3hignv", "Житомир", +380377835473, "Bufet", "Київська 64");
COMMIT;

CREATE TABLE Couriers_Orders
(ID_Cou Number(7)
CONSTRAINT CO_ID_Cou PRIMARY KEY,
 ID_O Number(7)
  CONSTRAINT CO_ID_O_fk REFERENCES Orders(ID_O),
 ID_Co Number(7)
  CONSTRAINT CO_ID_Co_fk REFERENCES Couriers(ID_Co));
  
  INSERT INTO couriers_orders (ID_Cou, ID_Co, ID_O)
VALUES (01, SELECT ID_Co FROM Couriers, SELECT ID_O FROM Orders),
       (02, SELECT ID_Co FROM Couriers, SELECT ID_O FROM Orders),
       (03, SELECT ID_Co FROM Couriers, SELECT ID_O FROM Orders);
COMMIT;
 