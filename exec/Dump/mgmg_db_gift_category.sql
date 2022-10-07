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
-- Table structure for table `gift_category`
--

DROP TABLE IF EXISTS `gift_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gift_category` (
  `gift_category_no` bigint NOT NULL AUTO_INCREMENT,
  `gift_category_name` varchar(10) DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`gift_category_no`),
  KEY `FKs7kcgqwbou9qdpbisuw8koa55` (`user_no`),
  CONSTRAINT `FKs7kcgqwbou9qdpbisuw8koa55` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gift_category`
--

LOCK TABLES `gift_category` WRITE;
/*!40000 ALTER TABLE `gift_category` DISABLE KEYS */;
INSERT INTO `gift_category` VALUES (3,'디지털/가전',2),(4,'여가/생활편의',2),(5,'패션의류',3),(6,'패션잡화',3),(7,'화장품/미용',3),(13,'악세사리',1),(14,'차량용품',1),(15,'패션잡화',1),(16,'디지털/가전',1),(19,'패션의류',5),(20,'스포츠/레저',5),(21,'여가/생활편의',5),(23,'패션잡화',4);
/*!40000 ALTER TABLE `gift_category` ENABLE KEYS */;
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
