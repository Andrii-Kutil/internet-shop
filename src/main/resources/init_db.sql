CREATE SCHEMA `internet-shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internet-shop`.`products` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DECIMAL(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `internet-shop`.`products` (`name`, `price`) VALUES ('bananana', '45');
INSERT INTO `internet-shop`.`products` (`name`, `price`) VALUES ('umbrella', '299');
INSERT INTO `internet-shop`.`products` (`name`, `price`) VALUES ('cat', '3000');
INSERT INTO `internet-shop`.`products` (`name`, `price`) VALUES ('mobile phone', '9999');
INSERT INTO `internet-shop`.`products` (`name`, `price`) VALUES ('dress', '799');

ALTER TABLE `internet-shop`.`products`
    DROP INDEX `name_UNIQUE` ;
;

