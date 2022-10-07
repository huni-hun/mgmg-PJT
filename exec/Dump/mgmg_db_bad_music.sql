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
-- Table structure for table `bad_music`
--

DROP TABLE IF EXISTS `bad_music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bad_music` (
  `bad_music_no` bigint NOT NULL AUTO_INCREMENT,
  `music_no` bigint DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`bad_music_no`),
  KEY `FK6fg1dv3fel7r8mmgefpstv2co` (`music_no`),
  KEY `FKae44uack2wnaqhqjlds1xpell` (`user_no`),
  CONSTRAINT `FK6fg1dv3fel7r8mmgefpstv2co` FOREIGN KEY (`music_no`) REFERENCES `music` (`music_no`),
  CONSTRAINT `FKae44uack2wnaqhqjlds1xpell` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bad_music`
--

LOCK TABLES `bad_music` WRITE;
/*!40000 ALTER TABLE `bad_music` DISABLE KEYS */;
INSERT INTO `bad_music` VALUES (1,174,4),(2,373,4),(3,631,4),(4,98,4),(5,1949,4),(6,2887,4),(7,8569,4),(8,2205,4),(9,282,4),(10,6689,4),(11,5,4),(12,6476,4),(13,1841,4),(14,768,4),(15,5528,4),(16,3661,4),(17,410,4),(18,1059,4),(19,134,4),(20,988,4);
/*!40000 ALTER TABLE `bad_music` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-07  3:05:30
