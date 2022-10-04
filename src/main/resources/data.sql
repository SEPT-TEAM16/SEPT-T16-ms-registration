INSERT INTO `doctors` (`user_id`, `email`, `dob`, `account_active`, `address`,  `first_name`, `last_name`, `mobile_number`, `password`, `role`)
VALUES (1, 'wd@gmail.com', 19961202, TRUE, '35 Oxford St Epping', 'Wayne', 'Dang', '0412345678', 'abc123', 'DOCTOR');

INSERT INTO `doctors` (`user_id`, `email`, `dob`, `account_active`, `address`,  `first_name`, `last_name`, `mobile_number`, `password`, `role`)
VALUES (2, 'ed@gmail.com', 19950130, FALSE, '123 Evelyn St Clayton', 'Eveline', 'Dang', '0412345678', 'abc123', 'DOCTOR');

INSERT INTO `admins` (`user_id`, `email`, `dob`, `account_active`, `address`, `first_name`, `last_name`, `mobile_number`, `password`, `role`)
VALUES (1, 'rw@gmail.com', 19970223, TRUE, 'Not_Ur_Business', 'Rico', 'Wu', '0412345678', 'abc123', 'ADMIN');

INSERT INTO `patients` (`user_id`, `email`, `dob`, `account_active`, `address`, `first_name`, `last_name`, `mobile_number`, `password`, `role`)
VALUES (1, 'dg@gmail.com', 20010101, TRUE, 'Esshay Village', 'Daniel', 'Gao', '0412345678', 'abc123', 'PATIENT');

INSERT INTO `patients` (`user_id`, `email`, `dob`, `account_active`, `address`, `first_name`, `last_name`, `mobile_number`, `password`, `role`)
VALUES (2, 'wj@gmail.com', 19950101, FALSE, 'Saddle Club Village', 'Wyatt', 'Jenkins', '0412345678', 'abc123', 'PATIENT');