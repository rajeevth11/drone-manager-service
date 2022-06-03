
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
  `weight` double NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `serial_num` varchar(100) NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_user` varchar(100) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `fk_drone_serial_num_idx` (`serial_num`),
  CONSTRAINT `fk_drone_serial_num` FOREIGN KEY (`serial_num`) REFERENCES `drone` (`serial_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Details of the medication entity'$$


INSERT INTO `drone_manager`.`drone` (`serial_number`, `model`, `weight_limit`, `battery_capacity`, `state`, `modified_user`) VALUES ('12344', 'Lightweight', '100', '100', 'LOADING', 'rajeev');
INSERT INTO `drone_manager`.`drone` (`serial_number`, `model`, `weight_limit`, `battery_capacity`, `state`, `modified_user`) VALUES ('12345', 'Middleweight', '500', '80', 'IDLE', 'rajeev');
INSERT INTO `drone_manager`.`drone` (`serial_number`, `model`, `weight_limit`, `battery_capacity`, `state`, `modified_user`) VALUES ('12346', 'Cruiserweight', '500', '75', 'IDLE', 'rajeev');
INSERT INTO `drone_manager`.`drone` (`serial_number`, `model`, `weight_limit`, `battery_capacity`, `state`, `modified_user`) VALUES ('12347', 'Middleweight', '500', '30', 'IDLE', 'rajeev');
INSERT INTO `drone_manager`.`drone` (`serial_number`, `model`, `weight_limit`, `battery_capacity`, `state`, `modified_user`) VALUES ('12348', 'Cruiserweight', '500', '100', 'IDLE', 'rajeev');

INSERT INTO `drone_manager`.`medication` (`code`, `name`, `weight`, `serial_num`, `modified_user`) VALUES ('MED_001', 'med_new', '80', '12344', 'rajeev');
INSERT INTO `drone_manager`.`medication` (`code`, `name`, `weight`, `serial_num`, `modified_user`) VALUES ('MED_002', 'med_new_test', '80', '12344', 'rajeev');
INSERT INTO `drone_manager`.`medication` (`code`, `name`, `weight`, `serial_num`, `modified_user`) VALUES ('MED_003', 'my_med', '80', '12348', 'rajeev');

COMMIT;