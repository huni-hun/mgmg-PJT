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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_no` bigint NOT NULL AUTO_INCREMENT,
  `admin` bit(1) DEFAULT b'0',
  `birth` date DEFAULT NULL,
  `diary_continue` int DEFAULT '0',
  `diary_font` int DEFAULT '0',
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(3) DEFAULT NULL,
  `high_price` bigint DEFAULT NULL,
  `low_price` bigint DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `user_id` varchar(16) DEFAULT NULL,
  `username` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`user_no`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '','2022-09-14',4,0,'haha@naver.com','남자',100000,10000,'$2a$10$hoAyDiWpuGdB.SMce42fM.y2K9qX05JcDOPNend07vNJ2nKVudUAO','hahaha123','김태훈'),(2,_binary '\0','1998-05-07',1,0,'yunaghgh@naver.com','여자',200000,100000,'$2a$10$JSkXyJ5Nc6IQ4znJpqUdW.GinaHKkPKtzDiqL.xnvRZXxBWuBzRo.','ssafy123','김싸피'),(3,_binary '\0','1999-05-19',0,0,'dustnzlzl@naver.com','여자',20000,10000,'$2a$10$aPpMT0BuneTrYekWfR44iOgEMfqUExiXn/Qig1PPpWU6z.kBSV1D.','dustn123','김연수'),(4,_binary '\0','1997-02-11',1,0,'t21212@naver.com','남자',300000,200000,'$2a$10$txcXAnhQTrTGkGk38/K7IeDLqD5CYXKGcZKdO5.yfsFsutTSHdaPe','mgmg306','김태훈'),(5,_binary '\0','1993-09-03',1,0,'pc5401@naver.com','남자',20000,10000,'$2a$10$xtwEwXeqBWrbSnV92boSieVl2aaP7M9QvtfQJ.vuV/BDibLDesXkq','pc5401','최명권');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-07  3:05:32
