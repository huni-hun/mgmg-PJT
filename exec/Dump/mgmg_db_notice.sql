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
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `notice_no` bigint NOT NULL AUTO_INCREMENT,
  `fixed_flag` bit(1) DEFAULT NULL,
  `notice_content` varchar(500) NOT NULL,
  `notice_date` varchar(16) DEFAULT NULL,
  `notice_title` varchar(50) NOT NULL,
  PRIMARY KEY (`notice_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,_binary '','몽글몽글에 참여해주신 신규 회원님들 중 랜덤으로 추첨하여 몽글이 캐릭터 인형을 증정할 예정입니다. 당첨 여부는 10.7일 13:00에 이메일로 발송됩니다.','2022-10-04','(공지)몽글몽글 런칭 기념 이벤트'),(2,_binary '','몽글몽글 서비스 내 제공하는 일기작성 기능을 이용하여 일주일 출석체크를 제공한 회원님들 중 추첨을 통한 일주일째 작성된 일기의 나에게주는 선물을 지급할 예정입니다. 많은 참여 부탁드립니다.','2022-10-04','(공지)몽글몽글 출석체크 이벤트'),(3,_binary '','몽글몽글 서비스 확대를 위한 10.7일 11:30 PM ~ 10.8일 02:00 AM 서비스 점검이 이루어질 예정입니다. 서비스이용에 불편을 드려 죄송합니다.','2022-10-04','(공지)(10.7~10.8)서버점검으로 인한 서비스 일시중지 안내'),(4,_binary '\0','몽글몽글 서비스 오픈베타 이벤트에 참여해주신 회원님들에게 소정의 상품이 배송될 예정입니다. 서비스내 알림을 확인하시고 사전에 작성하신 이메일로 회신 부탁드립니다.','2022-10-01','(마감)몽글몽글 오픈베타참여 이벤트');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-07  3:05:33
