## Instructions for creating the schema and tables required to run the application.

Before running the application, you must create a working database and a test database. 
The code for this is presented below.

**For creating the schema and tables in MYSQL**:

``` sql
CREATE SCHEMA IF NOT EXISTS  `banner_system` DEFAULT CHARACTER SET utf8 ;

CREATE IF NOT EXISTS  TABLE `banner_system`.`banner` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `imgSrc` VARCHAR(1024) NOT NULL,
  `width` INT NOT NULL,
  `height` INT NOT NULL,
  `targetUrl` VARCHAR(1024) NOT NULL,
  `langId` VARCHAR(5) NULL,
  PRIMARY KEY (`id`));

```

**For creating the test schema and tables in MYSQL:**
``` sql
CREATE SCHEMA IF NOT EXISTS  banner_system_test DEFAULT CHARACTER SET utf8;

```