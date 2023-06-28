
CREATE TABLE `users_table` (
  `usersId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `userRole` varchar(100) DEFAULT NULL,
  `createdAt` date DEFAULT NULL,
  `updatedAt` date DEFAULT current_timestamp(),
  PRIMARY KEY (`usersId`),
  UNIQUE KEY `users_table_un` (`username`)
);