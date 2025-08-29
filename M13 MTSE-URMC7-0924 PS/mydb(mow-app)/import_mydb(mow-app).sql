/******************************************************************************************
 Title: SQL DDL Script for
        Creation of Meals on Wheels (MOW) Database
 Version: 1.0
 Module: MSc Project (CMP060L050) for
         Master of Science in Computing
 University: University of Roehampton (UoR)
 Developer: E-wen Ivonne Lim (LIM24637800)
 Date: 12 May 2025
 ******************************************************************************************/


-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb(mow-app)
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb(mow-app)` ;

-- -----------------------------------------------------
-- Schema mydb(mow-app)
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb(mow-app)` DEFAULT CHARACTER SET utf8 ;
USE `mydb(mow-app)` ;

-- -----------------------------------------------------
-- Table `mydb(mow-app)`.`Profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb(mow-app)`.`Profile` ;

CREATE TABLE IF NOT EXISTS `mydb(mow-app)`.`Profile` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NRIC_UEN` VARCHAR(10) NOT NULL,
  `Names` VARCHAR(80) NOT NULL,
  `Surname` VARCHAR(40) NULL DEFAULT NULL,
  `Gender` VARCHAR(6) NULL DEFAULT NULL,
  `Role` VARCHAR(16) NOT NULL,
  `Email` VARCHAR(80) NOT NULL,
  `Phone` VARCHAR(10) NOT NULL,
  `Address` VARCHAR(255) NOT NULL,
  `Unit` VARCHAR(10) NULL DEFAULT NULL,
  `Postal` VARCHAR(6) NOT NULL,
  `Region` VARCHAR(10) NULL DEFAULT NULL,
  `Status` VARCHAR(10) NOT NULL,
  `Notes` VARCHAR(255) NULL DEFAULT NULL,
  `Created` DATETIME NULL DEFAULT NULL,
  `Updated` DATETIME NULL DEFAULT NULL,
  `Scheduled` DATETIME NULL DEFAULT NULL,
  `DOB` DATE NULL DEFAULT NULL,
  `Disabilities` VARCHAR(255) NULL DEFAULT NULL,
  `Income` INT NULL DEFAULT NULL,
  `Household` INT NULL DEFAULT NULL,
  `File1` VARCHAR(255) NULL DEFAULT NULL,
  `File2` VARCHAR(255) NULL DEFAULT NULL,
  `File3` VARCHAR(255) NULL DEFAULT NULL,
  `Password` VARCHAR(64) NULL DEFAULT NULL,
  `Diet` VARCHAR(10) NULL DEFAULT NULL,
  `Allergies` VARCHAR(255) NULL DEFAULT NULL,
  `Menu_Halal_ID` INT NULL DEFAULT NULL,
  `Menu_Veg_ID` INT NULL DEFAULT NULL,
  `Menu_Soft_ID` INT NULL DEFAULT NULL,
  `Menu_Normal_ID` INT NULL DEFAULT NULL,
  `Provider_ID` INT NULL DEFAULT NULL,
  `Password_API` VARCHAR(64) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `UK_Profile_NRIC_UEN` (`NRIC_UEN` ASC) VISIBLE,
  UNIQUE INDEX `UK_Profile_Email` (`Email` ASC) VISIBLE,
  INDEX `FK_Profile_Menu_Halal` (`Menu_Halal_ID` ASC) VISIBLE,
  INDEX `FK_Profile_Menu_Veg` (`Menu_Veg_ID` ASC) VISIBLE,
  INDEX `FK_Profile_Menu_Soft` (`Menu_Soft_ID` ASC) VISIBLE,
  INDEX `FK_Profile_Menu_Normal` (`Menu_Normal_ID` ASC) VISIBLE,
  INDEX `FK_Profile_Provider` (`Provider_ID` ASC) VISIBLE,
  CONSTRAINT `FK_Profile_Menu_Halal`
    FOREIGN KEY (`Menu_Halal_ID`)
    REFERENCES `mydb(mow-app)`.`Menu` (`ID`),
  CONSTRAINT `FK_Profile_Menu_Veg`
    FOREIGN KEY (`Menu_Veg_ID`)
    REFERENCES `mydb(mow-app)`.`Menu` (`ID`),
  CONSTRAINT `FK_Profile_Menu_Soft`
    FOREIGN KEY (`Menu_Soft_ID`)
    REFERENCES `mydb(mow-app)`.`Menu` (`ID`),
  CONSTRAINT `FK_Profile_Menu_Normal`
    FOREIGN KEY (`Menu_Normal_ID`)
    REFERENCES `mydb(mow-app)`.`Menu` (`ID`),
  CONSTRAINT `FK_Profile_Provider`
    FOREIGN KEY (`Provider_ID`)
    REFERENCES `mydb(mow-app)`.`Profile` (`ID`));


-- -----------------------------------------------------
-- Table `mydb(mow-app)`.`Caregiver_Member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb(mow-app)`.`Caregiver_Member` ;

CREATE TABLE IF NOT EXISTS `mydb(mow-app)`.`Caregiver_Member` (
  `Caregiver_ID` INT NOT NULL,
  `Member_ID` INT NOT NULL,
  PRIMARY KEY (`Caregiver_ID`, `Member_ID`),
  INDEX `FK_Caregiver_Member_Member` (`Member_ID` ASC) INVISIBLE,
  INDEX `FK_Caregiver_Member_Caregiver` (`Caregiver_ID` ASC) VISIBLE,
  CONSTRAINT `FK_Caregiver_Member_Caregiver`
    FOREIGN KEY (`Caregiver_ID`)
    REFERENCES `mydb(mow-app)`.`Profile` (`ID`),
  CONSTRAINT `FK_Caregiver_Member_Member`
    FOREIGN KEY (`Member_ID`)
    REFERENCES `mydb(mow-app)`.`Profile` (`ID`));


-- -----------------------------------------------------
-- Table `mydb(mow-app)`.`Menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb(mow-app)`.`Menu` ;

CREATE TABLE IF NOT EXISTS `mydb(mow-app)`.`Menu` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Seq_Day` INT NOT NULL,
  `Seq_Time` INT NOT NULL,
  `Name` VARCHAR(20) NOT NULL,
  `Diet` VARCHAR(10) NOT NULL,
  `Frozen` VARCHAR(3) NOT NULL,
  `Status` VARCHAR(10) NOT NULL,
  `Provider_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `UK_Menu_Seq` (`Seq_Day` ASC, `Seq_Time` ASC, `Diet` ASC, `Provider_ID` ASC) VISIBLE,
  INDEX `FK_Menu_Provider` (`Provider_ID` ASC) VISIBLE,
  CONSTRAINT `FK_Menu_Provider`
    FOREIGN KEY (`Provider_ID`)
    REFERENCES `mydb(mow-app)`.`Profile` (`ID`));


-- -----------------------------------------------------
-- Table `mydb(mow-app)`.`Meal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb(mow-app)`.`Meal` ;

CREATE TABLE IF NOT EXISTS `mydb(mow-app)`.`Meal` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Time_Avail` DATETIME NULL DEFAULT NULL,
  `Time_Start` DATETIME NULL DEFAULT NULL,
  `Time_End` DATETIME NULL DEFAULT NULL,
  `Status` VARCHAR(10) NOT NULL,
  `Menu_ID` INT NOT NULL,
  `Provider_ID` INT NOT NULL,
  `Member_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_Meal_Menu` (`Menu_ID` ASC) VISIBLE,
  INDEX `FK_Meal_Provider` (`Provider_ID` ASC) VISIBLE,
  INDEX `FK_Meal_Member` (`Member_ID` ASC) VISIBLE,
  CONSTRAINT `FK_Meal_Menu`
    FOREIGN KEY (`Menu_ID`)
    REFERENCES `mydb(mow-app)`.`Menu` (`ID`),
  CONSTRAINT `FK_Meal_Provider`
    FOREIGN KEY (`Provider_ID`)
    REFERENCES `mydb(mow-app)`.`Profile` (`ID`),
  CONSTRAINT `FK_Meal_Member`
    FOREIGN KEY (`Member_ID`)
    REFERENCES `mydb(mow-app)`.`Profile` (`ID`));


-- -----------------------------------------------------
-- Table `mydb(mow-app)`.`Pickup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb(mow-app)`.`Pickup` ;

CREATE TABLE IF NOT EXISTS `mydb(mow-app)`.`Pickup` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Time_Accept` DATETIME NOT NULL,
  `Time_Start` DATETIME NULL DEFAULT NULL,
  `Time_End` DATETIME NULL DEFAULT NULL,
  `Status` VARCHAR(10) NOT NULL,
  `Rider_ID` INT NOT NULL,
  `Meal_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_Pickup_Rider` (`Rider_ID` ASC) VISIBLE,
  INDEX `FK_Pickup_Meal` (`Meal_ID` ASC) VISIBLE,
  CONSTRAINT `FK_Pickup_Rider`
    FOREIGN KEY (`Rider_ID`)
    REFERENCES `mydb(mow-app)`.`Profile` (`ID`),
  CONSTRAINT `FK_Pickup_Meal`
    FOREIGN KEY (`Meal_ID`)
    REFERENCES `mydb(mow-app)`.`Meal` (`ID`));


-- -----------------------------------------------------
-- Table `mydb(mow-app)`.`Feedback`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb(mow-app)`.`Feedback` ;

CREATE TABLE IF NOT EXISTS `mydb(mow-app)`.`Feedback` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Rate_Meal` INT NOT NULL,
  `Remarks_Meal` VARCHAR(255) NULL DEFAULT NULL,
  `Rate_Delivery` INT NOT NULL,
  `Remarks_Delivery` VARCHAR(255) NULL DEFAULT NULL,
  `Status` VARCHAR(10) NOT NULL,
  `Member_ID` INT NOT NULL,
  `Pickup_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_Feedback_Member` (`Member_ID` ASC) VISIBLE,
  INDEX `FK_Feedback_Pickup` (`Pickup_ID` ASC) VISIBLE,
  CONSTRAINT `FK_Feedback_Member`
    FOREIGN KEY (`Member_ID`)
    REFERENCES `mydb(mow-app)`.`Profile` (`ID`),
  CONSTRAINT `FK_Feedback_Pickup`
    FOREIGN KEY (`Pickup_ID`)
    REFERENCES `mydb(mow-app)`.`Pickup` (`ID`));


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
