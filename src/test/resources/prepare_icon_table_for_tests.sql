SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema PackageManager
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PackageManagerTests` ;
USE `PackageManagerTests` ;

-- -----------------------------------------------------
-- Table `PackageManager`.`icon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PackageManagerTests`.`icon` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `imagePath` VARCHAR(200) NOT NULL,
  `size` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `imagePath_UNIQUE` (`imagePath` ASC) VISIBLE)
ENGINE = InnoDB;

DELETE FROM icon WHERE id BETWEEN 1 AND 10;

-- -----------------------------------------------------
-- Filling `PackageManager`.`icon`
-- -----------------------------------------------------
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
