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
-- Table structure for table `badge`
--

DROP TABLE IF EXISTS `badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `badge` (
  `badge_no` bigint NOT NULL AUTO_INCREMENT,
  `badge_condition` varchar(20) DEFAULT NULL,
  `badge_content` varchar(100) DEFAULT NULL,
  `badge_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`badge_no`),
  UNIQUE KEY `UK_brxud5a0bx0sc6dvhafilbmrs` (`badge_name`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badge`
--

LOCK TABLES `badge` WRITE;
/*!40000 ALTER TABLE `badge` DISABLE KEYS */;
INSERT INTO `badge` VALUES (1,'첫 일기 작성','어서와 몽글이는 처음이지?','몽글이와의 첫만남'),(2,'3일 연속 일기 작성','일기쓰는 습관들이기. 시작이 반이에요.','습관들이기'),(3,'7일 연속 일기 작성','한주간 꾸준히 일기 쓴 당신.\\n지금 기분은 어떠신가요?','한주간 고생했어'),(4,'14일 연속 일기 작성','금메달! 이런 습관을 지킬 수 있다면\\n앞으로도 무엇이든 가능합니다.','뭘 해도 될 사람'),(5,'7일 누적 일기 작성','반가워요. VVIP가 될때까지 화이팅!','신입회원'),(6,'30일 누적 일기 작성','잊지않고 찾아와주는 고마운 고객님.\\n사랑합니다.','단골손님'),(7,'60일 누적 일기 작성','우리 서비스 최고 존엄 고객님.\\n늘 감사합니다.','VVIP'),(8,'평온 감정 1회','평범한 하루가 나쁜 것은 아니에요.','무난한 하루'),(9,'평온 감정 5회','평범한 하루가 누군가에게는 특별한 하루','무소식이 희소식'),(10,'평온 감정 10회','일상이 있기에 특별한 날의 소중함을 알 수 있어요.','사는게 그렇지 뭐'),(11,'기쁨 감정 1회','오 해피 데이~ 이 감정 그대로~','오 해피 데이'),(12,'기쁨 감정 5회','하하하하하하! 기쁜날에는 소리내서 웃어봐요.','하하하하하하'),(13,'기쁨 감정 10회','\"그래도 인생은 아름다워\"\\nby.귀도','인생은 아름다워'),(14,'사랑 감정 1회','두근 두근..\\n누군가를 사랑하고 계신가요?','첫사랑'),(15,'사랑 감정 5회','\"사랑은 무엇보다도 자신을 위한 선물이다.\"\\nby.장 아누이','건축학개론'),(16,'사랑 감정 10회','혼자 앓고 있는건 아니죠?\\n사랑한다고 말해보세요!','지금 만나러 갑니다'),(17,'창피 감정 1회','찰나의 실수 귀엽게 봐줄게요.','이불킥'),(18,'창피 감정 5회','네? 무슨일 있으셨나요? 저는 모르겠네요 ~','못본척 해줘'),(19,'창피 감정 10회','\"피할수 없으면 즐겨라.\"\\nby.로버트 엘리엇','은밀하게 위대하게'),(20,'기대 감정 1회','어떤 일이 이루어지길 기대중인가요?\\n아마 하실 수 있을거에요!','나는 할 수 있다!'),(21,'기대 감정 5회','기대하는 모든 일이 이뤄질거에요.\\n몽글이가 장담합니다.','곧 그렇게 될 거야'),(22,'기대 감정 10회','마음먹은 대로 생각한 대로,\\n도전은 무한히 인생은 영원히','말하는 대로'),(23,'슬픔 감정 1회','우울할 땐 맛있는 걸 먹어서 풀어봐요!','우울할땐 울면'),(24,'슬픔 감정 5회','슬픈 고민이 있으면 누군가한테 털어놓는 것도 도움이 될거에요.','무슨 고민 있어?'),(25,'슬픔 감정 10회','비온 뒤에 땅이 굳듯이 슬픈일이 지나면 모두 잘 될거에요.','다 잘될거야'),(26,'짜증 감정 1회','짜증날 땐 맛있는 걸 먹어서 풀어봐요!','짜증날땐 짜장면'),(27,'짜증 감정 5회','접근 금지! 짜증나게 만드는 사람을 피해봐요.','접근 금지'),(28,'짜증 감정 10회','아무리 짜증나도 얼굴 찌푸리지 말아요.\\n웃으면 복이 찾아올거에요.','얼굴 찌푸리지 말아요'),(29,'피곤 감정 1회','피로는 간때문이야~ 오늘은 집에서 푹 쉬세요.','간때문이야'),(30,'피곤 감정 5회','피곤할 때는 커피 한 잔의 여유도 즐겨보아요.','커피 한잔 할래요'),(31,'피곤 감정 10회','피곤이 쌓인 당신.\\n주말에는 침대 밖에서 벗어나지마세요.','침대 밖은 위험해'),(32,'화 감정 1회','화가 나는 감정이 나쁜것만은 아닙니다.\\n자기 감정을 잘 알고 조율해봐요.','앵그리몽글'),(33,'화 감정 5회','누가 화나게 만들었나요.\\n취미 생활을 즐기며 화를 다스려봐요.','워~ 워~'),(34,'화 감정 10회','혼자만으로 힘들다면 주변에 도움을 요청하세요.','흥 칫 뿡'),(35,'공포 감정 1회','공포 영화를 보셨나요?\\n오늘 밤은 발 안내놓고 자기','누구냐 넌'),(36,'공포 감정 5회','\"공포를 느껴라, 그리고 그래도 도전하라\"\\nby.수잔 제퍼스','제발 그만해 나 무서워'),(37,'공포 감정 10회','공포를 즐기는 당신. 공포영화 매니아?','깜찍한 공포');
/*!40000 ALTER TABLE `badge` ENABLE KEYS */;
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
