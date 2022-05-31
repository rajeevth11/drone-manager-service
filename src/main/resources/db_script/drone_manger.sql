
CREATE DATABASE `drone_manager` /*!40100 DEFAULT CHARACTER SET latin1 */$$


CREATE TABLE `drone` (
  `serial_number` varchar(100) NOT NULL,
  `model` varchar(30) NOT NULL,
  `weight_limit` double NOT NULL,
  `battery_capacity` double NOT NULL,
  `state` varchar(20) NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_user` varchar(100) NOT NULL,
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Details of the drone entity'$$


CREATE TABLE `medication` (
  `code` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `weight` decimal(3,2) NOT NULL,
  `image` varchar(500) NOT NULL,
  `serial_number` varchar(100) NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_user` varchar(100) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_drone_serial_num_idx` (`serial_number`),
  CONSTRAINT `fk_drone_serial_num` FOREIGN KEY (`serial_number`) REFERENCES `drone` (`serial_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Details of the medication entity'$$

