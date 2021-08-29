CREATE DATABASE  IF NOT EXISTS `tenetdb`;
--Drop Table --
DROP TABLE IF EXISTS `trn_dependent`;
DROP TABLE IF EXISTS `trn_profile`;
DROP TABLE IF EXISTS `mst_role`;
DROP TABLE IF EXISTS `trn_mass_core_team`;
DROP TABLE IF EXISTS `trn_mass_time`;
DROP TABLE IF EXISTS `mst_community_allocation`;
DROP TABLE IF EXISTS `mst_global_parameter`;

--Drop sequence --
DROP TABLE IF EXISTS `dependent_sequence`;
DROP TABLE IF EXISTS `profile_sequence`;
DROP TABLE IF EXISTS `mass_time_sequence`;
DROP TABLE IF EXISTS `mass_core_team_sequence`;

--Create sequence --
CREATE TABLE `dependent_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `profile_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mass_time_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mass_core_team_sequence` (
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

CREATE TABLE `trn_mass_core_team` (
  `id` bigint NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `created_by_user` varchar(255) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updated_by_user` varchar(255) DEFAULT NULL,
  `version_number` bigint DEFAULT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `contact_number` varchar(16) DEFAULT NULL,
  `mass_time_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_trn_mass_time_mass_time_id` (`mass_time_id`),
  CONSTRAINT `FK_trn_mass_time_mass_time_id` FOREIGN KEY (`mass_time_id`) REFERENCES `trn_mass_time` (`id`)
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


