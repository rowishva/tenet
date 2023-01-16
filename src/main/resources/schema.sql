CREATE DATABASE  IF NOT EXISTS `tenetdb`;
--Drop Table --
DROP TABLE IF EXISTS `trn_dependent`;
DROP TABLE IF EXISTS `trn_profile`;
DROP TABLE IF EXISTS `mst_role`;
DROP TABLE IF EXISTS `trn_mass_booking`;
DROP TABLE IF EXISTS `trn_mass_booking_category`;
DROP TABLE IF EXISTS `trn_mass_time`;
DROP TABLE IF EXISTS `mst_community_allocation`;
DROP TABLE IF EXISTS `mst_global_parameter`;
DROP TABLE IF EXISTS `mst_seating_category`;
DROP TABLE IF EXISTS `mst_seating_prefix`;
DROP TABLE IF EXISTS `trn_sequence_data`;

--Drop sequence --
DROP TABLE IF EXISTS `trn_dependent_sequence`;
DROP TABLE IF EXISTS `trn_profile_sequence`;
DROP TABLE IF EXISTS `trn_mass_time_sequence`;
DROP TABLE IF EXISTS `trn_mass_booking_sequence`;
DROP TABLE IF EXISTS `trn_mass_booking_category_sequence`;

--Create sequence --
CREATE TABLE `trn_dependent_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trn_profile_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trn_mass_time_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trn_mass_booking_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--Create table --

CREATE TABLE `mst_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `description` varchar(50) NOT NULL,
  `privilege` varchar(1000) DEFAULT NULL,
  `role_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `trn_profile` (
  `id` bigint NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `community_category` varchar(50) DEFAULT NULL,
  `contact_number` varchar(16) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `special_needs` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `otp` varchar(10) DEFAULT NULL,
  `reset_token` varchar(100) DEFAULT NULL,
  `reset_token_create_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_trn_profile_username` (`username`),
  KEY `FK_trn_profile_role_id` (`role_id`),
  CONSTRAINT `FK_trn_profile_role_id` FOREIGN KEY (`role_id`) REFERENCES `mst_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trn_dependent` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `community_category` varchar(50) DEFAULT NULL,
  `contact_number` varchar(16) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `relationship` varchar(50) DEFAULT NULL,
  `special_needs` varchar(20) DEFAULT NULL,
  `profile_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_trn_dependent_profile_id` (`profile_id`),
  CONSTRAINT `FK_trn_dependent_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `trn_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trn_mass_time` (
  `id` bigint NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `available_capacity` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `total_capacity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mst_community_allocation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `description` varchar(50) NOT NULL,
  `code` varchar(10) NOT NULL,
  `total_allocation` int DEFAULT NULL,
  `available_allocation` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mst_global_parameter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `description` varchar(50) NOT NULL,
  `code` varchar(10) NOT NULL,
  `parameter_value` varchar(10) NOT NULL, 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mst_seating_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `description` varchar(50) NOT NULL,
  `tag` varchar(10) NOT NULL,
  `total_capacity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mst_seating_prefix` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `prefix` varchar(10) NOT NULL,
  `tag` varchar(10) NOT NULL,
  `start_no` int DEFAULT NULL,
  `end_no` int DEFAULT NULL,  
  `allocation_capacity` int DEFAULT NULL,
  `carpark_allocation` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trn_mass_booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `prefix` varchar(10) NOT NULL,
  `tag` varchar(10) NOT NULL,
  `seating_no` int DEFAULT NULL,
  `carpark_allocation` varchar(50) DEFAULT NULL,  
  `mass_booking_no` varchar(50) DEFAULT NULL,  
  `attendance` bit(1) DEFAULT NULL,  
  `booked` bit(1) DEFAULT NULL,  
  `full_name` varchar(50) DEFAULT NULL,
  `contact_number` varchar(16) DEFAULT NULL,
  `profile_id` bigint DEFAULT NULL,
  `dependent_id` bigint DEFAULT NULL,
  `mass_time_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trn_mass_booking_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `tag` varchar(10) NOT NULL,
  `total_capacity` int DEFAULT NULL,
  `available_capacity` int DEFAULT NULL,
  `mass_time_id` bigint DEFAULT NULL,
  `next_allocation_sequence` varchar(5) DEFAULT NULL,  
  PRIMARY KEY (`id`),
  KEY `FK_trn_mass_booking_category_mass_time_id` (`mass_time_id`),
  CONSTRAINT `FK_trn_mass_booking_category_mass_time_id` FOREIGN KEY (`mass_time_id`) REFERENCES `trn_mass_time` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trn_sequence_data` (
  `sequence_name` varchar(100) NOT NULL,
  `sequence_increment` int unsigned NOT NULL DEFAULT '1',
  `sequence_min_value` int unsigned NOT NULL DEFAULT '1',
  `sequence_max_value` bigint unsigned NOT NULL DEFAULT '18446744073709551615',
  `sequence_cur_value` bigint unsigned DEFAULT '1',
  `sequence_cycle` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sequence_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/**
DELIMITER $$
CREATE `fnt_mass_booking`(Mass_ID bigint) RETURNS varchar(20) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
    DECLARE return_value VARCHAR(20);
DECLARE prefixC VARCHAR(300);
DECLARE tagC VARCHAR(255);
DECLARE startNo INT;
DECLARE EndNo INT;
DECLARE allocationC INT;
DECLARE carparkAllC VARCHAR(255);
DECLARE countMain INT;
DECLARE Mass_IDd INT;
DECLARE SecondCount INT;
Declare FinalCount int;
Declare FinalCountIncrement int;
   SET Mass_IDd = 142;
   set FinalCountIncrement = 1;
set FinalCount = (select Count(*) from mst_seating_prefix);

while FinalCountIncrement <= FinalCount DO

		SELECT  prefix,tag,start_no,end_no,allocation_capacity,carpark_allocation  INTO 
		prefixC,tagC,startNo,EndNo,allocationC,carparkALLC FROM mst_seating_prefix WHERE ID = FinalCountIncrement;
		SET SecondCount = StartNo;
		set countMain = 1;

		WHILE allocationC >= countMain  DO				
				INSERT INTO `trn_mass_booking` (mass_time_id, prefix, tag, seating_no, carpark_allocation, create_time, created_by_user, is_deleted, version_number)
				VALUES (Mass_ID,prefixC,tagC,SecondCount,carparkALLC, curdate(), 'fnt_mass_booking', '\0', 0);
				set SecondCount = SecondCount + 1;
				set countMain = countMain + 1;
				
			END WHILE; 
		SET FinalCountIncrement = FinalCountIncrement + 1;
	END WHILE;
set return_value = 'DONE';
	-- return return_value
	RETURN (return_value);
END$$
DELIMITER ;
**/