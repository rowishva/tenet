INSERT INTO `mst_role` (`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`description`,`privilege`,`role_code`)
VALUES (1,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'User Role','Login;Registration;ManageDependents;MassBooking','USER'),
(2,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'RT User','Login;WalkInRegistration;MassAttendanceConfirmation','RTUSR'),
(3,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'RT Admin','Login;UserCreation;WalkInRegistration;MassAttendanceConfirmation','RTADMN'),
(4,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Admin','Login;UserCreation;CommunityAllocation;SeatingAllocation;MassTiming;MassCoreTeam;WalkInRegistration;MassAttendanceConfirmation','ADMN'),
(5,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Super Admin','Login;GlobalParameter;UserCreation','SUADMN');

INSERT INTO `trn_profile`(`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`community_category`,`contact_number`,`date_of_birth`,`full_name`,`password`,`special_needs`,`status`,`username`,`role_id`,`otp`)
VALUES (10000,'2021-07-14 09:19:47.345000','Admin','\0','2021-07-14 09:19:56.116000','Admin',1,'BEC','01564446445','2000-01-07','Do Chen Hao','$2a$10$VjLhEdP.kkbbhDUm2zPYHO0xE0DTX.vroX9WF8yDorLIrRX1RUXUS','NONE','ACTIVE','test@gmail.com',1,'');
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
VALUES (1,'2021-07-14 09:45:06.863000','Admin','\0',NULL,'Admin',0,'Seating Allocation','YES','SEALL');