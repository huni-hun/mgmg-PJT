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
-- Table structure for table `gift`
--

DROP TABLE IF EXISTS `gift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gift` (
  `gift_no` bigint NOT NULL AUTO_INCREMENT,
  `gift_img` varchar(255) DEFAULT NULL,
  `gift_name` varchar(100) DEFAULT NULL,
  `gift_price` bigint DEFAULT NULL,
  `gift_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gift_no`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gift`
--

LOCK TABLES `gift` WRITE;
/*!40000 ALTER TABLE `gift` DISABLE KEYS */;
INSERT INTO `gift` VALUES (1,'https://shopping-phinf.pstatic.net/main_8226014/82260147718.2.jpg','닌텐도 스위치 프로컨트롤러 프로콘 아날로그 스틱커버 스컬앤코',6000,'https://search.shopping.naver.com/gate.nhn?id=82260147718'),(2,'https://shopping-phinf.pstatic.net/main_8312688/83126880295.1.jpg','이이네 컨트롤러 어시스트 쿠션 링 FPS 에임링 갈림방지 스위치 프로콘 PS4 XBOX 엑박 무선 패드 PS5 듀얼센스 아날로그 스틱 커버 호환 악세사리',4000,'https://search.shopping.naver.com/gate.nhn?id=83126880295'),(3,'https://shopping-phinf.pstatic.net/main_8372169/83721693871.jpg','카매트위코엣지코일매트운전석(1P)',49000,'https://search.shopping.naver.com/gate.nhn?id=83721693871'),(4,'https://shopping-phinf.pstatic.net/main_8223104/82231045817.jpg','은침007팬던트체인귀걸이 남여공용 남자악세사리 데일리룩 코디 패션 빈티지',12800,'https://search.shopping.naver.com/gate.nhn?id=82231045817'),(5,'https://shopping-phinf.pstatic.net/main_3461610/34616102813.jpg','부드러운 이어캡 PC방 인강 헤드셋 10대남자 강의용',51400,'https://search.shopping.naver.com/gate.nhn?id=34616102813'),(6,'https://shopping-phinf.pstatic.net/main_8261110/82611102016.jpg','미지아 SL20 저자극전기면도기 10대남자친구생일선물',53320,'https://search.shopping.naver.com/gate.nhn?id=82611102016'),(7,'https://shopping-phinf.pstatic.net/main_1174690/11746900134.jpg','기스방지 스페셜엣지 카본 도어커버/그랜져TG',16500,'https://search.shopping.naver.com/gate.nhn?id=11746900134'),(8,'https://shopping-phinf.pstatic.net/main_8334325/83343256278.3.jpg','아미 반팔 카라티 22SS 당일출고',133000,'https://search.shopping.naver.com/gate.nhn?id=83343256278'),(9,'https://shopping-phinf.pstatic.net/main_8256189/82561896670.14.jpg','(매장판)나이키 에어 포스 1 07 로우 올백 올흰 올검 흰검 검흰 검창 빨창 스티치',159000,'https://search.shopping.naver.com/gate.nhn?id=82561896670'),(10,'https://shopping-phinf.pstatic.net/main_3082727/30827272512.20220206214508.jpg','남성 회사원 20대 백팩 정장 캐주얼 책가방',127880,'https://search.shopping.naver.com/gate.nhn?id=30827272512'),(11,'https://shopping-phinf.pstatic.net/main_8454882/84548821569.jpg','[백화점]폴로랄프로렌 남성 프렙스터 반바지 22SS',109800,'https://search.shopping.naver.com/gate.nhn?id=84548821569'),(12,'https://shopping-phinf.pstatic.net/main_8454882/84548821569.jpg','[백화점]폴로랄프로렌 남성 프렙스터 반바지 22SS',109800,'https://search.shopping.naver.com/gate.nhn?id=84548821569'),(13,'https://shopping-phinf.pstatic.net/main_8396331/83963315259.jpg','아미 ami 빅하트패치 맨투맨',129000,'https://search.shopping.naver.com/gate.nhn?id=83963315259'),(14,'https://shopping-phinf.pstatic.net/main_3030896/30308964094.20211227024436.jpg','20대남자 신입사원 새내기 남자가죽백팩 비지니스',118570,'https://search.shopping.naver.com/gate.nhn?id=30308964094'),(15,'https://shopping-phinf.pstatic.net/main_8458236/84582367179.jpg','겐조 Kenzo 22 남성 11주년 클래식 타이거 자수 후드티 4XA 99 멀티 4XA 99 5SW415',159000,'https://search.shopping.naver.com/gate.nhn?id=84582367179'),(16,'https://shopping-phinf.pstatic.net/main_8246479/82464790274.3.jpg','(국내매장판) 뉴발란스 530 스틸 그레이 블루 어스 마스 베이지 실버',129000,'https://search.shopping.naver.com/gate.nhn?id=82464790274'),(17,'https://shopping-phinf.pstatic.net/main_8361551/83615511114.jpg','피어오브갓 에센셜 21SS 백로고 피오갓 긴팔 FOG 후드티',162000,'https://search.shopping.naver.com/gate.nhn?id=83615511114'),(18,'https://shopping-phinf.pstatic.net/main_3416299/34162998323.jpg','[남학생크로스벡 대학생메신저백 20대남성크로스벡]',124257,'https://search.shopping.naver.com/gate.nhn?id=34162998323'),(19,'https://shopping-phinf.pstatic.net/main_8486156/84861568720.jpg','면 남성의류 남성티셔츠 선물 여름 20대 반팔티 남성',101280,'https://search.shopping.naver.com/gate.nhn?id=84861568720'),(20,'https://shopping-phinf.pstatic.net/main_8408689/84086890042.jpg','[국내매장판]라코스테 남자 맨투맨, 커플 스웻셔츠',155000,'https://search.shopping.naver.com/gate.nhn?id=84086890042'),(21,'https://shopping-phinf.pstatic.net/main_8461213/84612132772.1.jpg','[백화점판] 폴로 랄프로렘 퍼포먼스 집업 후드티 (블랙&amp;화이트포니)',199000,'https://search.shopping.naver.com/gate.nhn?id=84612132772'),(22,'https://shopping-phinf.pstatic.net/main_3426711/34267112526.jpg','[의류 남성 남성의류 티셔츠 면 20대 남자티 빅사이즈]',195270,'https://search.shopping.naver.com/gate.nhn?id=34267112526'),(23,'https://shopping-phinf.pstatic.net/main_3426711/34267112526.jpg','[의류 남성 남성의류 티셔츠 면 20대 남자티 빅사이즈]',195270,'https://search.shopping.naver.com/gate.nhn?id=34267112526'),(24,'https://shopping-phinf.pstatic.net/main_3426765/34267657917.jpg','[20대 남성의류 캐쥬얼 남자친구 면 남편 반팔 모임]',179210,'https://search.shopping.naver.com/gate.nhn?id=34267657917'),(25,'https://shopping-phinf.pstatic.net/main_3475611/34756117270.jpg','남성가방 20대남자가방 크로스백 B2275-2 (블랙',179580,'https://search.shopping.naver.com/gate.nhn?id=34756117270'),(26,'https://shopping-phinf.pstatic.net/main_3485382/34853826617.jpg','남성 크로스백 트렌디 블랙 메신저 디자인백 20대 패션',135610,'https://search.shopping.naver.com/gate.nhn?id=34853826617'),(27,'https://shopping-phinf.pstatic.net/main_8246479/82464790274.3.jpg','(국내매장판) 뉴발란스 530 스틸 그레이 블루 어스 마스 베이지 실버',129000,'https://search.shopping.naver.com/gate.nhn?id=82464790274'),(28,'https://shopping-phinf.pstatic.net/main_3082727/30827272512.20220206214508.jpg','남성 회사원 20대 백팩 정장 캐주얼 책가방',127880,'https://search.shopping.naver.com/gate.nhn?id=30827272512'),(29,'https://shopping-phinf.pstatic.net/main_3030896/30308964094.20211227024436.jpg','20대남자 신입사원 새내기 남자가죽백팩 비지니스',118570,'https://search.shopping.naver.com/gate.nhn?id=30308964094'),(30,'https://shopping-phinf.pstatic.net/main_9665060/9665060275.21.jpg','탄틴 가죽 남자 서류가방 기획전',152000,'https://search.shopping.naver.com/gate.nhn?id=9665060275'),(31,'https://shopping-phinf.pstatic.net/main_8454882/84548821569.jpg','[백화점]폴로랄프로렌 남성 프렙스터 반바지 22SS',109800,'https://search.shopping.naver.com/gate.nhn?id=84548821569'),(32,'https://shopping-phinf.pstatic.net/main_8225232/82252327381.4.jpg','남자등산복상의 작업티 긴팔작업복 티셔츠 빅사이즈',12500,'https://search.shopping.naver.com/gate.nhn?id=82252327381'),(33,'https://shopping-phinf.pstatic.net/main_8370333/83703333503.jpg','[닥스지갑]22년 남자 반지갑 DIG1ACP1105-K1',146520,'https://search.shopping.naver.com/gate.nhn?id=83703333503'),(34,'https://shopping-phinf.pstatic.net/main_3408766/34087668725.20220815154443.jpg','20대 남자 여행 보관용 슬링백 옆가방 백팩킹',100740,'https://search.shopping.naver.com/gate.nhn?id=34087668725'),(35,'https://shopping-phinf.pstatic.net/main_3030896/30308964094.20211227024436.jpg','20대남자 신입사원 새내기 남자가죽백팩 비지니스',118490,'https://search.shopping.naver.com/gate.nhn?id=30308964094'),(36,'https://shopping-phinf.pstatic.net/main_8260140/82601403368.6.jpg','뉴발란스 574 레거시 네이비 블랙 클래식 그레이 트리플블랙 베이지 아이보리',109000,'https://search.shopping.naver.com/gate.nhn?id=82601403368'),(37,'https://shopping-phinf.pstatic.net/main_3475616/34756169794.jpg','남성가방 20대남자가방 크로스백 B2275-2 (브라운',175590,'https://search.shopping.naver.com/gate.nhn?id=34756169794'),(38,'https://shopping-phinf.pstatic.net/main_3426782/34267825700.jpg','[20대 반팔카라티 반팔 카라티 남편 캐쥬얼 남성의류]',172660,'https://search.shopping.naver.com/gate.nhn?id=34267825700'),(39,'https://shopping-phinf.pstatic.net/main_3460753/34607531572.jpg','[오버핏 봄 남자의류 남성티 남성의류 40대 남성 20대]',262660,'https://search.shopping.naver.com/gate.nhn?id=34607531572'),(40,'https://shopping-phinf.pstatic.net/main_2945786/29457867115.20211030004911.jpg','몽블랑 126203 마이스터스튁 6cc 남자 반지갑 가죽 소가죽 20대',296010,'https://search.shopping.naver.com/gate.nhn?id=29457867115'),(41,'https://shopping-phinf.pstatic.net/main_3437872/34378723486.jpg','[가벼운선물 소가죽 남성 20대남성 남자 반지갑]',243800,'https://search.shopping.naver.com/gate.nhn?id=34378723486'),(42,'https://shopping-phinf.pstatic.net/main_2282925/22829252493.20200512011910.jpg','30대 40대 남자 봄 여름 작업복 바지 AM5947B',14590,'https://search.shopping.naver.com/gate.nhn?id=22829252493'),(43,'https://shopping-phinf.pstatic.net/main_8355998/83559985649.1.jpg','웨스트우드 남성 플리스 자켓_WL4MOJF297',19200,'https://search.shopping.naver.com/gate.nhn?id=83559985649'),(44,'https://shopping-phinf.pstatic.net/main_3437872/34378723486.jpg','[가벼운선물 소가죽 남성 20대남성 남자 반지갑]',243800,'https://search.shopping.naver.com/gate.nhn?id=34378723486');
/*!40000 ALTER TABLE `gift` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-07  3:05:29
