INSERT INTO `mst_role` (`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`description`,`privilege`,`role_code`)
VALUES (1,'2021-07-14 09:45:06.863000','Dummy Created','\0',NULL,'DUMMY_USER',0,'User Role','Login;Registration;ManageDependents;MassBooking','USER');
INSERT INTO `role_sequence` VALUES (2);

INSERT INTO `trn_profile`(`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`community_category`,`contact_number`,`date_of_birth`,`full_name`,`password`,`special_needs`,`status`,`username`,`role_id`,`otp`)
VALUES (10000,'2021-07-14 09:19:47.345000','Dummy Created','\0','2021-07-14 09:19:56.116000','Dummy Updated',1,'BEC','01564446445','2000-01-07','Do Chen Hao','$2a$10$VjLhEdP.kkbbhDUm2zPYHO0xE0DTX.vroX9WF8yDorLIrRX1RUXUS','NONE','ACTIVE','test@gmail.com',1,'');
INSERT INTO `profile_sequence` VALUES (10001);

INSERT INTO `trn_dependent`(`id`,`create_time`,`created_by_user`,`is_deleted`,`update_time`,`updated_by_user`,`version_number`,`community_category`,`contact_number`,`date_of_birth`,`full_name`,`relationship`,`special_needs`,`profile_id`)
VALUES (10000,'2021-07-14 09:19:49.319000','Dummy Created','\0',NULL,'DUMMY_USER',0,'BEC','10293756','2003-01-17','Do Chun','Son','NONE',10000),(10001,'2021-07-14 09:19:50.223000','Dummy Created','\0',NULL,'DUMMY_USER',0,'BEC','1425374586','2005-01-17','Do Chon Chun','Son','SPECIALNEED_HELPER',10000);
INSERT INTO `dependent_sequence` VALUES (10003);