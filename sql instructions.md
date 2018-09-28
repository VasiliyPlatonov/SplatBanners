## Instructions for creating the schema and tables required to run the application.

**For creating the schema and tables in MYSQL:**

``` sql
CREATE SCHEMA `banner_system` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `banner_system`.`banner` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `imgSrc` VARCHAR(1024) NOT NULL,
  `width` INT NOT NULL,
  `height` INT NOT NULL,
  `targetUrl` VARCHAR(1024) NOT NULL,
  `langId` VARCHAR(5) NULL,
  PRIMARY KEY (`id`));

```