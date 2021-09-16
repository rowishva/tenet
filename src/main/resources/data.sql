INSERT INTO `mst_role` (`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`description`,`privilege`,`role_code`)
VALUES (1,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'User Role','Login;Registration;ManageDependents;MassBooking','USER'),
(2,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'RT User','Login;WalkInRegistration;MassAttendanceConfirmation','RTUSR'),
(3,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'RT Admin','Login;UserCreation;WalkInRegistration;MassAttendanceConfirmation','RTADMN'),
(4,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Admin','Login;UserCreation;CommunityAllocation;SeatingAllocation;MassTiming;MassCoreTeam;WalkInRegistration;MassAttendanceConfirmation','ADMN'),
(5,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Super Admin','Login;GlobalParameter;UserCreation','SUADMN');

INSERT INTO `trn_profile`(`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`community_category`,`contact_number`,`date_of_birth`,`full_name`,`password`,`special_needs`,`status`,`username`,`role_id`,`otp`,`reset_token`,`reset_token_create_time`)
VALUES (10000,'2021-07-14 09:19:47.345000','Admin','\0','2021-07-14 09:19:56.116000','Admin',1,'BEC','01564446445','2000-01-07','Do Chen Hao','$2a$10$VjLhEdP.kkbbhDUm2zPYHO0xE0DTX.vroX9WF8yDorLIrRX1RUXUS','NONE','ACTIVE','test@gmail.com',1,'','',NULL);
INSERT INTO `profile_sequence` VALUES (10001);

INSERT INTO `trn_dependent`(`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`community_category`,`contact_number`,`date_of_birth`,`full_name`,`relationship`,`special_needs`,`profile_id`)
VALUES (10000,'2021-07-14 09:19:49.319000','Admin','\0',NULL,'Admin',0,'BEC','10293756','2003-01-17','Do Chun','Son','NONE',10000),
(10001,'2021-07-14 09:19:50.223000','Admin','\0',NULL,'Admin',0,'BEC','1425374586','2005-01-17','Do Chon Chun','Son','SPECIALNEED_HELPER',10000);
INSERT INTO `dependent_sequence` VALUES (10003);

INSERT INTO `mass_time_sequence` VALUES (1000);
INSERT INTO `mass_core_team_sequence` VALUES (1000);

INSERT INTO `mst_community_allocation` (`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`description`,`code`,`total_allocation`,`available_allocation`)
VALUES (1,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'BEC','BEC',0,0),
(2,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Parish Group','PG',0,0),
(3,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Parishioners','PS',0,0),
(4,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Core Team','CT',0,0);

INSERT INTO `mst_global_parameter` (`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`description`,`parameter_value`,`code`)
VALUES (1,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Seating Allocation','YES','SEALL'),
(2,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Total Capacity','239','TC');

INSERT INTO `mst_seating_category` (`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`description`,`code`,`total_allocation`)
VALUES (1,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Caretaker','CARE',0),
(2,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Hospitality Minister','HM',0),
(3,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Elderly','SNR',0),
(4,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Registration Team','RT',0),
(5,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Lector','LEC',0),
(6,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'EMOHC','EMOHC',0),
(7,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Priest & Alterboys','SAN',0),
(8,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Choir & Organist','MM',0),
(9,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Audio Video','AV',0),
(10,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MC','MC',0),
(11,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'PUBLIC','PUBLIC',0),
(12,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'WCB','WCB',0);

INSERT INTO `mst_seating_prefix` (`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`prefix`,`tag`,`start_no`,`end_no`,`allocation_capacity`,`carpark_allocation`)
VALUES (1,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'SAN','SAN',1, 7, 7,'CarPark C'),
(2,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'AV','AV',1, 3, 3,'CarPark C'),
(3,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MM','MM',1, 6, 6,'CarPark C'),
(4,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'WCB','WCB',1, 2, 2,'CarPark A'),
(5,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MR','MC',1, 1, 1,'CarPark A'),
(6,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MR','PUBLIC', 2, 18, 17,'CarPark A'),
(7,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MR','CARE',19, 20, 2,'CarPark A'),
(8,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MR','PUBLIC',21, 24, 4,'CarPark A'),
(9,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MR','HM',25, 32, 8,'CarPark A'),
(10,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MR','RT',33, 36, 4,'CarPark A'),
(11,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MR','PUBLIC', 37, 37, 1,'CarPark A'),
(12,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MR','LEC', 38, 38, 1,'CarPark A'),
(13,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MR','PUBLIC', 39, 64, 26,'CarPark A'),
(14,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'MR','SNR', 65, 74, 10,'CarPark A'),
(15,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'ML','PUBLIC', 1, 36, 36,'CarPark C'),
(16,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'ML','RT', 37, 37, 1,'CarPark C'),
(17,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'ML','PUBLIC', 38, 63, 26,'CarPark C'),
(18,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'SH','EMOHC', 1, 5, 5,'CarPark C'),
(19,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'SH','PUBLIC', 6, 13, 8,'CarPark C'),
(20,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'SH','HM', 14, 14, 1,'CarPark C'),
(21,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'SH','PUBLIC', 15, 26, 12,'CarPark C'),
(22,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'SH','HM', 27, 27, 1,'CarPark C'),
(23,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'SH','PUBLIC', 28, 42, 15,'CarPark C'),
(24,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'BVM','PUBLIC', 1, 13, 13,'CarPark A'),
(25,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'BVM','HM', 14, 14, 1,'CarPark A'),
(26,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'BVM','PUBLIC', 15, 30, 16,'CarPark A'),
(27,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'BVM','HM', 31, 31, 1,'CarPark A'),
(28,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'BVM','PUBLIC', 32, 42, 11,'CarPark A');

