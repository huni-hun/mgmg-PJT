-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: j7b306.p.ssafy.io    Database: mgmg_db
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `achieved_badge`
--

DROP TABLE IF EXISTS `achieved_badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `achieved_badge` (
  `achieved_badge_no` bigint NOT NULL AUTO_INCREMENT,
  `achieved_badge_date` date DEFAULT NULL,
  `badge_no` bigint DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`achieved_badge_no`),
  KEY `FK1lyirqs8633qc85cp5bghuiqs` (`badge_no`),
  KEY `FKqufjr2ktyy8mfnwmnq0rpyofe` (`user_no`),
  CONSTRAINT `FK1lyirqs8633qc85cp5bghuiqs` FOREIGN KEY (`badge_no`) REFERENCES `badge` (`badge_no`),
  CONSTRAINT `FKqufjr2ktyy8mfnwmnq0rpyofe` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achieved_badge`
--

LOCK TABLES `achieved_badge` WRITE;
/*!40000 ALTER TABLE `achieved_badge` DISABLE KEYS */;
INSERT INTO `achieved_badge` VALUES (1,'2022-10-04',1,1),(2,'2022-10-04',11,1),(3,'2022-10-04',6,1),(4,'2022-10-04',29,1),(5,'2022-10-04',1,2),(6,'2022-10-04',8,2),(7,'2022-10-05',2,1),(8,'2022-10-05',14,1),(9,'2022-10-05',7,1),(10,'2022-10-05',1,4),(11,'2022-10-05',35,4),(12,'2022-10-05',23,4),(13,'2022-10-05',6,4),(14,'2022-10-05',26,4),(15,'2022-10-05',17,4),(16,'2022-10-05',11,4),(17,'2022-10-05',7,4),(18,'2022-10-05',5,4),(19,'2022-10-05',32,4),(20,'2022-10-05',14,4),(21,'2022-10-05',20,4),(22,'2022-10-05',8,4),(23,'2022-10-05',24,4),(24,'2022-10-05',29,4),(25,'2022-10-05',12,4),(26,'2022-10-06',1,5),(27,'2022-10-06',29,5),(29,'2022-10-06',11,5),(30,'2022-10-06',17,5),(31,'2022-10-06',6,5),(32,'2022-10-06',15,4),(39,'2022-10-06',30,4),(40,'2022-10-06',31,4);
/*!40000 ALTER TABLE `achieved_badge` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-07  3:05:31
