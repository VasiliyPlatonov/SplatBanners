CREATE TABLE IF NOT EXISTS `banner_system_test`.`banner` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `imgSrc` VARCHAR(1024) NOT NULL,
  `width` INT NOT NULL,
  `height` INT NOT NULL,
  `targetUrl` VARCHAR(1024) NOT NULL,
  `langId` VARCHAR(5) NULL,
  PRIMARY KEY (`id`));