CREATE DATABASE IF NOT EXISTS `internet-shop`;
USE `internet-shop`;

CREATE TABLE `roles`
(
    `role_id`   bigint       NOT NULL AUTO_INCREMENT,
    `role_name` varchar(225) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

INSERT INTO `roles`
VALUES (1, 'ADMIN'),
       (2, 'USER');

CREATE TABLE `users`
(
    `user_id`       bigint       NOT NULL AUTO_INCREMENT,
    `user_name`     varchar(225) NOT NULL,
    `user_login`    varchar(225) NOT NULL,
    `user_password` varchar(500) NOT NULL,
    `user_salt`     varbinary(16) DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `user_login_UNIQUE` (`user_login`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 92
  DEFAULT CHARSET = utf8;

INSERT INTO `users`
VALUES (65, 'Bob', 'bob',
        'e1b7c12ba888d0845e90e50e9d70edcfc93d40efb39b184e482092de6d7e86006fe2dbb6df8a11fd61ad52e3f50c6822d1a7f41d78fe0c54b6cd6db3d301dd6c',
        _binary '&¢ß FCY­*þd');

CREATE TABLE `users_roles`
(
    `user_id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    KEY `users_roles_users_fk_idx` (`user_id`),
    KEY `users_roles_roles_fk_idx` (`role_id`),
    CONSTRAINT `users_roles_roles_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
    CONSTRAINT `users_roles_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `users_roles`
VALUES (65, 2);

CREATE TABLE `products`
(
    `id`    bigint         NOT NULL AUTO_INCREMENT,
    `name`  varchar(225)   NOT NULL,
    `price` decimal(11, 0) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 114
  DEFAULT CHARSET = utf8;

INSERT INTO `products`
VALUES (95, 'dress', 499),
       (96, 'mobile', 9999),
       (99, 'meat', 120),
       (103, 'bmw', 10000000),
       (105, 'pig', 499),
       (113, 'apple', 25);

CREATE TABLE `shopping_carts`
(
    `cart_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`cart_id`),
    KEY `carts_users_fk_idx` (`user_id`),
    CONSTRAINT `carts_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARSET = utf8;

INSERT INTO `shopping_carts`
VALUES (15, 65);

CREATE TABLE `shopping_carts_products`
(
    `cart_id`    bigint NOT NULL,
    `product_id` bigint NOT NULL,
    KEY `carts_products_carts_fk_idx` (`cart_id`),
    KEY `carts_products_products_fk_idx` (`product_id`),
    CONSTRAINT `carts_products_carts_fk` FOREIGN KEY (`cart_id`) REFERENCES `shopping_carts` (`cart_id`),
    CONSTRAINT `carts_products_products_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `orders`
(
    `order_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id`  bigint NOT NULL,
    PRIMARY KEY (`order_id`),
    KEY `orders_users_fk_idx` (`user_id`),
    CONSTRAINT `orders_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 36
  DEFAULT CHARSET = utf8;

CREATE TABLE `orders_products`
(
    `order_id`   bigint NOT NULL,
    `product_id` bigint NOT NULL,
    KEY `orders_products_orders_fk_idx` (`order_id`),
    KEY `orders_products_products_fk_idx` (`product_id`),
    CONSTRAINT `orders_products_orders_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
    CONSTRAINT `orders_products_products_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
