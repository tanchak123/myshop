CREATE SCHEMA `myshop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `myshop`.`users` (
                                  `user_id` bigint NOT NULL AUTO_INCREMENT,
                                  `login` varchar(225) NOT NULL,
                                  `password` varchar(225) NOT NULL,
                                  PRIMARY KEY (`user_id`),
                                  UNIQUE KEY `login_UNIQUE` (`login`)
                                  );