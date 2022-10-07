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
-- Table structure for table `music_genre`
--

DROP TABLE IF EXISTS `music_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music_genre` (
  `music_genre_no` bigint NOT NULL AUTO_INCREMENT,
  `emotion_name` varchar(3) NOT NULL,
  `music_genre_name` varchar(10) DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`music_genre_no`),
  KEY `FKbroald9k5o5fdu6o6h1oxut62` (`user_no`),
  CONSTRAINT `FKbroald9k5o5fdu6o6h1oxut62` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_genre`
--

LOCK TABLES `music_genre` WRITE;
/*!40000 ALTER TABLE `music_genre` DISABLE KEYS */;
INSERT INTO `music_genre` VALUES (1,'공포','트로트',1),(2,'기대','댄스',1),(3,'기쁨','발라드',1),(4,'기쁨','트로트',1),(5,'기쁨','댄스',1),(6,'사랑','댄스',1),(7,'사랑','랩/힙합',1),(8,'슬픔','발라드',1),(9,'슬픔','트로트',1),(10,'슬픔','R&B/Soul',1),(11,'짜증','댄스',1),(12,'창피','발라드',1),(13,'평온','댄스',1),(14,'피곤','트로트',1),(15,'화','댄스',1),(16,'공포','록/메탈',2),(18,'공포','포크/블루스',2),(19,'기대','댄스',2),(20,'기대','랩/힙합',2),(21,'기대','록/메탈',2),(22,'기대','발라드',2),(24,'기쁨','댄스',2),(25,'기쁨','랩/힙합',2),(26,'기쁨','록/메탈',2),(29,'사랑','댄스',2),(30,'사랑','랩/힙합',2),(32,'사랑','발라드',2),(33,'사랑','인디음악',2),(34,'사랑','포크/블루스',2),(36,'슬픔','발라드',2),(37,'슬픔','인디음악',2),(38,'슬픔','트로트',2),(40,'짜증','댄스',2),(41,'짜증','랩/힙합',2),(42,'짜증','록/메탈',2),(43,'짜증','포크/블루스',2),(45,'창피','댄스',2),(46,'창피','랩/힙합',2),(47,'창피','록/메탈',2),(50,'평온','R&B/Soul',2),(51,'평온','댄스',2),(52,'평온','랩/힙합',2),(53,'평온','록/메탈',2),(54,'평온','발라드',2),(55,'평온','인디음악',2),(57,'피곤','R&B/Soul',2),(58,'피곤','발라드',2),(59,'피곤','인디음악',2),(61,'피곤','포크/블루스',2),(62,'화','댄스',2),(63,'화','랩/힙합',2),(64,'화','록/메탈',2),(65,'화','발라드',2),(66,'화','포크/블루스',2),(67,'공포','R&B/Soul',3),(68,'기대','댄스',3),(69,'기쁨','댄스',3),(72,'사랑','인디음악',3),(73,'슬픔','발라드',3),(75,'짜증','록/메탈',3),(76,'창피','인디음악',3),(77,'평온','R&B/Soul',3),(78,'피곤','발라드',3),(79,'피곤','인디음악',3),(81,'화','랩/힙합',3),(157,'공포','발라드',5),(159,'기대','R&B/Soul',5),(160,'기대','댄스',5),(161,'기대','랩/힙합',5),(162,'기대','록/메탈',5),(163,'기대','발라드',5),(164,'기대','인디음악',5),(165,'기대','트로트',5),(166,'기대','포크/블루스',5),(167,'기쁨','발라드',5),(168,'사랑','발라드',5),(169,'슬픔','록/메탈',5),(170,'짜증','인디음악',5),(171,'창피','랩/힙합',5),(172,'평온','발라드',5),(173,'피곤','랩/힙합',5),(174,'화','발라드',5),(175,'공포','인디음악',4),(176,'기대','R&B/Soul',4),(177,'기쁨','댄스',4),(178,'사랑','댄스',4),(179,'사랑','발라드',4),(180,'사랑','인디음악',4),(181,'슬픔','R&B/Soul',4),(182,'슬픔','발라드',4),(183,'짜증','트로트',4),(184,'창피','트로트',4),(185,'평온','R&B/Soul',4),(186,'평온','포크/블루스',4),(187,'피곤','R&B/Soul',4),(188,'피곤','인디음악',4),(189,'화','댄스',4),(190,'화','트로트',4);
/*!40000 ALTER TABLE `music_genre` ENABLE KEYS */;
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
