## Instructions for creating the schema and tables required to run the application.

Before running the application, you must create a working database and a test database. 
The code for this is presented below.

**For creating the schema and tables in MYSQL**:
``` sql
CREATE SCHEMA IF NOT EXISTS  `banner_system` DEFAULT CHARACTER SET utf8 ;
```
``` sql
CREATE IF NOT EXISTS  TABLE `banner_system`.`banner` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `imgSrc` VARCHAR(1024) NOT NULL,
  `width` INT NOT NULL,
  `height` INT NOT NULL,
  `targetUrl` VARCHAR(1024) NOT NULL,
  `langId` VARCHAR(5) NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `banner_system`.`users` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `second_name` VARCHAR(100) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`username`));
  
CREATE TABLE `banner_system`.`authorities` (
    `username` VARCHAR(50) NOT NULL,
    `authority` VARCHAR(50) NOT NULL,
    INDEX `fk_authorities_users_idx` (`username` ASC),
    CONSTRAINT `fk_authorities_users`
      FOREIGN KEY (`username`)
      REFERENCES `banner_system`.`users` (`username`)
      ON DELETE CASCADE
      ON UPDATE CASCADE);
```

**For creating the test schema and tables in MYSQL:**
``` sql
CREATE SCHEMA IF NOT EXISTS  banner_system_test DEFAULT CHARACTER SET utf8;

```