CREATE DATABASE IF NOT EXISTS `mydb(mow-app)`
/*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */
/*!80016 DEFAULT ENCRYPTION='N' */;

USE `mydb(mow-app)`;

-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb(mow-app)
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `caregiver_member`
--

DROP TABLE IF EXISTS `caregiver_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caregiver_member` (
  `Caregiver_ID` int NOT NULL,
  `Member_ID` int NOT NULL,
  PRIMARY KEY (`Caregiver_ID`,`Member_ID`),
  KEY `FK_Caregiver_Member_Member` (`Member_ID`) /*!80000 INVISIBLE */,
  KEY `FK_Caregiver_Member_Caregiver` (`Caregiver_ID`),
  CONSTRAINT `FK_Caregiver_Member_Caregiver` FOREIGN KEY (`Caregiver_ID`) REFERENCES `profile` (`ID`),
  CONSTRAINT `FK_Caregiver_Member_Member` FOREIGN KEY (`Member_ID`) REFERENCES `profile` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caregiver_member`
--

LOCK TABLES `caregiver_member` WRITE;
/*!40000 ALTER TABLE `caregiver_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `caregiver_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NRIC_UEN` varchar(10) NOT NULL,
  `Names` varchar(80) NOT NULL,
  `Surname` varchar(40) DEFAULT NULL,
  `Gender` varchar(6) DEFAULT NULL,
  `Role` varchar(16) NOT NULL,
  `Email` varchar(80) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Unit` varchar(10) DEFAULT NULL,
  `Postal` varchar(6) NOT NULL,
  `Region` varchar(10) DEFAULT NULL,
  `Status` varchar(10) NOT NULL,
  `Notes` varchar(255) DEFAULT NULL,
  `Created` datetime DEFAULT NULL,
  `Updated` datetime DEFAULT NULL,
  `Scheduled` datetime DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Disabilities` varchar(255) DEFAULT NULL,
  `Income` int DEFAULT NULL,
  `Household` int DEFAULT NULL,
  `File1` varchar(255) DEFAULT NULL,
  `File2` varchar(255) DEFAULT NULL,
  `File3` varchar(255) DEFAULT NULL,
  `Password` varchar(64) DEFAULT NULL,
  `Diet` varchar(10) DEFAULT NULL,
  `Allergies` varchar(255) DEFAULT NULL,
  `Menu_Halal_ID` int DEFAULT NULL,
  `Menu_Veg_ID` int DEFAULT NULL,
  `Menu_Soft_ID` int DEFAULT NULL,
  `Menu_Normal_ID` int DEFAULT NULL,
  `Provider_ID` int DEFAULT NULL,
  `Password_API` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_Profile_NRIC_UEN` (`NRIC_UEN`),
  UNIQUE KEY `UK_Profile_Email` (`Email`),
  KEY `FK_Profile_Menu_Halal` (`Menu_Halal_ID`),
  KEY `FK_Profile_Menu_Veg` (`Menu_Veg_ID`),
  KEY `FK_Profile_Menu_Soft` (`Menu_Soft_ID`),
  KEY `FK_Profile_Menu_Normal` (`Menu_Normal_ID`),
  KEY `FK_Profile_Provider` (`Provider_ID`),
  CONSTRAINT `FK_Profile_Menu_Halal` FOREIGN KEY (`Menu_Halal_ID`) REFERENCES `menu` (`ID`),
  CONSTRAINT `FK_Profile_Menu_Normal` FOREIGN KEY (`Menu_Normal_ID`) REFERENCES `menu` (`ID`),
  CONSTRAINT `FK_Profile_Menu_Soft` FOREIGN KEY (`Menu_Soft_ID`) REFERENCES `menu` (`ID`),
  CONSTRAINT `FK_Profile_Menu_Veg` FOREIGN KEY (`Menu_Veg_ID`) REFERENCES `menu` (`ID`),
  CONSTRAINT `FK_Profile_Provider` FOREIGN KEY (`Provider_ID`) REFERENCES `profile` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'S****218F','E-wen Ivonne','Lim','Female','Administrator','management.merrymeal@gmail.com','63249730','11 Eunos Road 8','#07-02','408601',NULL,'Active',NULL,'2025-06-03 00:00:00',NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,'$2a$10$X7M1MkdCipa.Ol5c8MUCv.nNVphxZyC6/FOaZZ4zGyl5GWeg44irC',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'o20pB+Z8G4BOaJymTtOvTD9CsGKHTE2LUMoFkB+sDdkkk5hXgOWlnISGL3jcGPhy');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Rate_Meal` int NOT NULL,
  `Remarks_Meal` varchar(255) DEFAULT NULL,
  `Rate_Delivery` int NOT NULL,
  `Remarks_Delivery` varchar(255) DEFAULT NULL,
  `Status` varchar(10) NOT NULL,
  `Member_ID` int NOT NULL,
  `Pickup_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Feedback_Member` (`Member_ID`),
  KEY `FK_Feedback_Pickup` (`Pickup_ID`),
  CONSTRAINT `FK_Feedback_Member` FOREIGN KEY (`Member_ID`) REFERENCES `profile` (`ID`),
  CONSTRAINT `FK_Feedback_Pickup` FOREIGN KEY (`Pickup_ID`) REFERENCES `pickup` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pickup`
--

DROP TABLE IF EXISTS `pickup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pickup` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Time_Accept` datetime NOT NULL,
  `Time_Start` datetime DEFAULT NULL,
  `Time_End` datetime DEFAULT NULL,
  `Status` varchar(10) NOT NULL,
  `Rider_ID` int NOT NULL,
  `Meal_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Pickup_Rider` (`Rider_ID`),
  KEY `FK_Pickup_Meal` (`Meal_ID`),
  CONSTRAINT `FK_Pickup_Meal` FOREIGN KEY (`Meal_ID`) REFERENCES `meal` (`ID`),
  CONSTRAINT `FK_Pickup_Rider` FOREIGN KEY (`Rider_ID`) REFERENCES `profile` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pickup`
--

LOCK TABLES `pickup` WRITE;
/*!40000 ALTER TABLE `pickup` DISABLE KEYS */;
/*!40000 ALTER TABLE `pickup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Time_Avail` datetime DEFAULT NULL,
  `Time_Start` datetime DEFAULT NULL,
  `Time_End` datetime DEFAULT NULL,
  `Status` varchar(10) NOT NULL,
  `Menu_ID` int NOT NULL,
  `Provider_ID` int NOT NULL,
  `Member_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Meal_Menu` (`Menu_ID`),
  KEY `FK_Meal_Provider` (`Provider_ID`),
  KEY `FK_Meal_Member` (`Member_ID`),
  CONSTRAINT `FK_Meal_Member` FOREIGN KEY (`Member_ID`) REFERENCES `profile` (`ID`),
  CONSTRAINT `FK_Meal_Menu` FOREIGN KEY (`Menu_ID`) REFERENCES `menu` (`ID`),
  CONSTRAINT `FK_Meal_Provider` FOREIGN KEY (`Provider_ID`) REFERENCES `profile` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Seq_Day` int NOT NULL,
  `Seq_Time` int NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Diet` varchar(10) NOT NULL,
  `Frozen` varchar(3) NOT NULL,
  `Status` varchar(10) NOT NULL,
  `Provider_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_Menu_Seq` (`Seq_Day`,`Seq_Time`, `Diet`, `Provider_ID`),
  KEY `FK_Menu_Provider` (`Provider_ID`),
  CONSTRAINT `FK_Menu_Provider` FOREIGN KEY (`Provider_ID`) REFERENCES `profile` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-14 21:40:00
