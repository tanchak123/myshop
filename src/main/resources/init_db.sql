CREATE SCHEMA `myshop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `users` (
                                      `user_id` mediumint NOT NULL AUTO_INCREMENT,
                                      `login` varchar(45) NOT NULL,
                                      `password` varchar(45) NOT NULL,
                                      `roles` varchar(45) NOT NULL,
                                      PRIMARY KEY (`user_id`),
                                      UNIQUE KEY `login_UNIQUE` (`login`)
                                    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
  CREATE TABLE `myshop`.`buckets` (
                                  `bucket_id` INT NOT NULL AUTO_INCREMENT,
                                  `products` VARCHAR(225) NULL,
                                  `user` VARCHAR(225) NULL,
                                  PRIMARY KEY (`bucket_id`),
                                  UNIQUE INDEX `bucket_id_UNIQUE` (`bucket_id` ASC) VISIBLE);

  CREATE TABLE `myshop`.`products` (
                                  `product_id` INT NOT NULL AUTO_INCREMENT,
                                  `name` VARCHAR(45) BINARY NOT NULL,
                                  `price` DECIMAL(45) NOT NULL,
                                  PRIMARY KEY (`product_id`),
                                  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE);


