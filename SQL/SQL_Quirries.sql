CREATE TABLE `employees` (
  `EID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Is_Married` int(11) NOT NULL,
  `Number_Of_Children` int(11) DEFAULT NULL,
  `Position` varchar(45) DEFAULT NULL,
  `Department` varchar(45) DEFAULT NULL,
  `Drafted` varchar(45) DEFAULT NULL,
  `Adress` varchar(45) DEFAULT NULL,
  `Mobile` varchar(45) DEFAULT NULL,
  `Iban` varchar(45) DEFAULT NULL,
  `Bank` varchar(45) DEFAULT NULL,
  `Salary` float DEFAULT NULL,
  `fam_bonus` float DEFAULT NULL,
  `time_bonus` float DEFAULT NULL,
  `final_salary` float DEFAULT NULL,
  PRIMARY KEY (`EID`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



CREATE TABLE `children` (
  `children_id` int(11) NOT NULL AUTO_INCREMENT,
  `EID` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Age` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`children_id`,`EID`),
  KEY `EID_idx` (`EID`) /*!80000 INVISIBLE */,
  CONSTRAINT `EID` FOREIGN KEY (`EID`) REFERENCES `employees` (`EID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `payment_records` (
  `EID` int(11) NOT NULL,
  `Payment_Date` varchar(45) NOT NULL,
  `Amount` float DEFAULT NULL,
  PRIMARY KEY (`EID`,`Payment_Date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


/* Insertion in table Employee starts now */

INSERT INTO sys.employees (Name,Is_Married,Number_Of_Children,Position,Department,Drafted,Adress,Mobile,IBAN,Bank,Salary,fam_bonus,time_bonus,final_salary) VALUES ('dummy0',0,3,'mdy','csd','2020/01/22','ad','69802542428','1034567890','Alpha_Bank',800,0.05,0,800);
INSERT INTO sys.employees (Name,Is_Married,Number_Of_Children,Position,Department,Drafted,Adress,Mobile,IBAN,Bank,Salary,fam_bonus,time_bonus,final_salary) VALUES ('dummy1',1,3,'mdp','tem','2020/01/22','ad','69702542428','1234567890','Euro_Bank',3500,0.05,0,3675);
INSERT INTO sys.employees (Name,Is_Married,Number_Of_Children,Position,Department,Drafted,Adress,Mobile,IBAN,Bank,Salary,fam_bonus,time_bonus,final_salary) VALUES ('dummy2',1,3,'sdy','tety','2020/01/22','ad','69602542428','1234567890','Alpha_Bank',800,0.05,0,845);
INSERT INTO sys.employees (Name,Is_Married,Number_Of_Children,Position,Department,Drafted,Adress,Mobile,IBAN,Bank,Salary,fam_bonus,time_bonus,final_salary) VALUES ('dummy3',0,3,'sdp','elmepa','2020/01/22','ad','69502542428','1334567890','Ethniki_Bank',950,0.05,0,950);
INSERT INTO sys.employees (Name,Is_Married,Number_Of_Children,Position,Department,Drafted,Adress,Mobile,IBAN,Bank,Salary,fam_bonus,time_bonus,final_salary) VALUES ('dummy4',1,2,'mdy','csd','2020/01/22','ad','69402542428','1434567890','Euro_Bank',800,0.05,0,880);

/* Insertion in table Employee ends now */



/* Insertion in table Children starts now */

INSERT INTO sys.children(children_id,EID,Name,Age) VALUES (1,1,'child00',5);
INSERT INTO sys.children(children_id,EID,Name,Age) VALUES (2,1,'child01',10);
INSERT INTO sys.children(children_id,EID,Name,Age) VALUES (3,1,'child10',15);
INSERT INTO sys.children(children_id,EID,Name,Age) VALUES (4,5,'child10',9);
INSERT INTO sys.children(children_id,EID,Name,Age) VALUES (5,5,'child11',11);

/* Insertion in table Children ends now */



/* Insertion in table Payment_Records starts now */

INSERT INTO sys.Payment_Records(EID,Payment_Date,Amount) Values (1,'2020/01/22',800);
INSERT INTO sys.Payment_Records(EID,Payment_Date,Amount) Values (2,'2020/01/22',3675);
INSERT INTO sys.Payment_Records(EID,Payment_Date,Amount) Values (3,'2020/01/22',845);
INSERT INTO sys.Payment_Records(EID,Payment_Date,Amount) Values (4,'2020/01/22',950);
INSERT INTO sys.Payment_Records(EID,Payment_Date,Amount) Values (5,'2020/01/22',880);


/* Insertion in table Payment_Records ends now */