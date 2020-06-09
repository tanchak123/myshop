CREATE SCHEMA `myshop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `buckets` (
  `bucket_id` mediumint NOT NULL AUTO_INCREMENT,
  `product_id` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` mediumint DEFAULT NULL,
  PRIMARY KEY (`bucket_id`),
  UNIQUE KEY `bucket_id_UNIQUE` (`bucket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8

CREATE TABLE `orders` (
  `order_id` mediumint NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `count` varchar(115) DEFAULT NULL,
  `sum` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8

CREATE TABLE `pictures` (
  `picture_id` int NOT NULL AUTO_INCREMENT,
  `picture` varchar(225) NOT NULL,
  PRIMARY KEY (`picture_id`),
  UNIQUE KEY `picture_id_UNIQUE` (`picture_id`),
  UNIQUE KEY `picture_UNIQUE` (`picture`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `price` decimal(15,0) NOT NULL,
  `image_url` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `image_url_UNIQUE` (`image_url`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8

CREATE TABLE `users` (
  `user_id` mediumint NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `roles` varchar(225) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8