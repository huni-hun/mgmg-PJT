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
-- Table structure for table `emotion`
--

DROP TABLE IF EXISTS `emotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emotion` (
  `emotion_no` bigint NOT NULL AUTO_INCREMENT,
  `emotion_explanation` varchar(100) NOT NULL,
  `emotion_name` varchar(3) NOT NULL,
  `explanation_person` varchar(20) NOT NULL,
  PRIMARY KEY (`emotion_no`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emotion`
--

LOCK TABLES `emotion` WRITE;
/*!40000 ALTER TABLE `emotion` DISABLE KEYS */;
INSERT INTO `emotion` VALUES (1,'기쁨은 사물 안에 있지 않다. 그것은 우리 안에 있다.','기쁨','리하르트 바그너'),(2,'행복은 느낌이고, 기쁨은 자세요, 마음가짐이요, 입장이다.','기쁨','핸실'),(3,'인간은 행복해지기로 마음먹은 만큼 행복해질 수 있다.','기쁨','A. 링컨'),(4,'시작이 반이다.','평온','아리스토 텔레스'),(5,'현재를 사는 법을 배우는 것은 기쁨의 행로의 일부다.','평온','사라 밴 브레스낙'),(6,'산다는것 그것은 치열한 전투이다.','평온','로망로랑'),(7,'실패는 잊어라 그러나 그것이 준 교훈은 절대 잊으면 안된다.','창피','하버트 개서'),(8,'남의 앞에 나서는 것을 두려워하지 말라.','창피','엘마 윌러'),(9,'피할수 없으면 즐겨라.','창피','로버트 엘리엇'),(10,'사랑하는 것은 천국을 살짝 엿보는 것이다.','사랑','카렌 선드'),(11,'만유인력은 사랑에 빠진 사람을 책임지지 않는다.','사랑','아인슈타인'),(12,'사랑은 삶의 최대 청량, 강장제이다.','사랑','파플로 피카소'),(13,'신만이 완벽할 뿐이다. 인간은 완벽을 소망할 뿐이다.','짜증','괴테'),(14,'행복은 습관이다, 그것을 몸에 지녀라.','짜증','허버드'),(15,'눈물 흘리지마라. 화내지 마라. 이해하라.','짜증','바뤼흐 스피노자'),(16,'한계는 없다. 도전을 즐겨라!','기대','칼리 피오리나'),(17,'1퍼센트의 가능성. 그것이 나의 길이다.','기대','나폴레옹'),(18,'오랫동안 꿈을 그려온 사람은 마침내 그 꿈을 닮아 간다.','기대','앙드레 말로'),(19,'고통이 남기고 간 뒤를 보라! 고난이 지나면 반드시 기쁨이 스며든다.','피곤','괴테'),(20,'눈에너지는 영원한 기쁨이다.','피곤','윌리엄 블레이크'),(21,'아무리 어려워도 한 번 시작한 일은 깨지 마라.','피곤','안드레아 정'),(22,'화가 날 때는 100까지 세라. 최악일때는 욕설을 퍼부어라.','화','마크 트웨인'),(23,'분노를 지연시키는 것은 분노에 대한 최선의 묘약이다.','화','세네카'),(24,'화가 났을 때는 이성으로써 억제하라.','화','이이'),(25,'오직 한가지 우리가 두려워해야 할 일은 두려움 그 자체다.','공포','루스벨트'),(26,'위험에 대한 공포는 위험 그 자체보다 천 배나 무겁다.','공포','디포'),(27,'자기가 무서워하는 것을 해라. 그러면 무서움은 없어진다.','공포','에머슨'),(28,'겨울이 오면 봄이 멀지 않으리.','슬픔','셸리'),(29,'한번의 실패와 영원한 실패를 혼동하지 마라.','슬픔','F.스콧 핏제랄드'),(30,'지나간 슬픔에 새로운 눈물을 낭비하지 말라.','슬픔','에우리피데스');
/*!40000 ALTER TABLE `emotion` ENABLE KEYS */;
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
