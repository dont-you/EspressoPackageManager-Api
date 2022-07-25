SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `PackageManagerTests` ;
USE `PackageManagerTests` ;

CREATE TABLE IF NOT EXISTS `PackageManagerTests`.`icon` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `imagePath` VARCHAR(200) NOT NULL,
  `size` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `imagePath_UNIQUE` (`imagePath` ASC) VISIBLE)
ENGINE = InnoDB;

DELETE FROM icon WHERE id BETWEEN 1 AND 1000;

INSERT INTO icon (id, imagePath,size) VALUES(1,'src/icons/JuxWAwCbAk.jpg',70),
(2 ,'src/icons/mqGrXzmSRK.jpg',60),
(3 ,'src/icons/9LIcy7Tda5.jpg',18),
(4 ,'src/icons/W6CedTIx9o.jpg',10),
(5 ,'src/icons/67I7TEATbO.jpg',71),
(6 ,'src/icons/7uOKjgHvCL.jpg',72),
(7 ,'src/icons/xRJLpDviuS.jpg',48),
(8 ,'src/icons/vDk48s4cBg.jpg',30),
(9 ,'src/icons/pwZuiyknm5.jpg',49),
(10 ,'src/icons/Wnf6fH27pN.jpg',32);

CREATE TABLE IF NOT EXISTS `PackageManagerTests`.`library_has_application` (
  `library_id` INT UNSIGNED NOT NULL,
  `application_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`library_id`, `application_id`),
  INDEX `fk_library_has_application_application1_idx` (`application_id` ASC) VISIBLE,
  INDEX `fk_library_has_application_library1_idx` (`library_id` ASC) VISIBLE)
ENGINE = InnoDB;

DELETE FROM library_has_application WHERE library_id BETWEEN 1 AND 1000;

INSERT INTO library_has_application (library_id,application_id) VALUES(1,8),(1,13),(1,44),(2,42),(2,1),(2,38),(3,42),(3,2),(3,1),
(4,36),(4,1),(4,24),(5,18),(5,29),(5,7),(6,26),(6,23),(6,38),(7,30),(7,38),(8,3),(9,43),(9,24),(9,45),(10,46)
