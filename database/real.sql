-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: realmadrid
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `allplayers`
--

DROP TABLE IF EXISTS `allplayers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allplayers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clubName` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `weight_kg` int DEFAULT NULL,
  `height_cm` int DEFAULT NULL,
  `ratingFifaOverall` int DEFAULT NULL,
  `midIssues` varchar(255) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allplayers`
--

LOCK TABLES `allplayers` WRITE;
/*!40000 ALTER TABLE `allplayers` DISABLE KEYS */;
INSERT INTO `allplayers` VALUES (1,'Real Madrid','Kylian Mbappé','ST','France',26,'M',73,178,91,'No issues',NULL),(2,'Real Madrid','Vinícius Júnior','LW','Brazil',23,'M',70,176,88,'No issues',NULL),(3,'Real Madrid','Jude Bellingham','CM','England',21,'M',74,186,86,'Minor ankle injury',NULL),(4,'Real Madrid','Luka Modrić','CM','Croatia',38,'M',66,172,86,'No issues',NULL),(5,'Real Madrid','Antonio Rüdiger','CB','Germany',30,'M',85,190,84,'No issues',NULL),(6,'Real Madrid','Eduardo Camavinga','CM','France',21,'M',73,178,84,'No issues',NULL),(7,'Real Madrid','Éder Militão','CB','Brazil',26,'M',82,186,83,'No issues',NULL),(8,'Real Madrid','Thibaut Courtois','GK','Belgium',32,'M',96,199,90,'No issues',NULL),(9,'Real Madrid','Toni Kroos','CM','Germany',34,'M',78,183,85,'No issues',NULL),(10,'Barcelona','Robert Lewandowski','ST','Poland',36,'M',81,185,89,'No issues',NULL),(12,'Barcelona','Frenkie de Jong','CM','Netherlands',26,'M',75,180,86,'No issues',NULL),(13,'Barcelona','Marc-André ter Stegen','GK','Germany',32,'M',85,187,90,'Recovering from knee surgery',NULL),(14,'Barcelona','Ronald Araújo','CB','Uruguay',25,'M',83,185,84,'No issues',NULL),(15,'Barcelona','Jules Koundé','CB','France',26,'M',80,180,83,'No issues',NULL),(16,'Barcelona','Ousmane Dembélé','LW','France',26,'M',67,178,83,'Hamstring strain',NULL),(17,'Barcelona','Raphinha','RW','Brazil',27,'M',70,176,84,'No issues',NULL),(18,'Barcelona','Franck Kessié','CM','Ivory Coast',27,'M',83,179,83,'No issues',NULL),(19,'Barcelona','Sergi Roberto','RB','Spain',31,'M',75,177,80,'No issues',NULL),(20,'Manchester City','Erling Haaland','ST','Norway',23,'M',87,194,91,'No issues',NULL),(21,'Manchester City','Kevin De Bruyne','CM','Belgium',32,'M',68,181,89,'No issues',NULL),(22,'Manchester City','Phil Foden','CM','England',23,'M',70,171,85,'No issues',NULL),(23,'Bayern Munich','Joshua Kimmich','CM','Germany',28,'M',75,176,87,'No issues',NULL),(24,'Bayern Munich','Sadio Mané','LW','Senegal',31,'M',69,175,85,'No issues',NULL),(25,'Bayern Munich','Manuel Neuer','GK','Germany',37,'M',93,193,89,'No issues',NULL),(26,'Paris Saint-Germain','Neymar Jr.','LW','Brazil',31,'M',68,175,87,'No issues',NULL),(27,'Paris Saint-Germain','Lionel Messi','RW','Argentina',36,'M',72,170,86,'No issues',NULL),(28,'Paris Saint-Germain','Gianluigi Donnarumma','GK','Italy',24,'M',90,196,86,'No issues',NULL),(29,'Liverpool','Mohamed Salah','RW','Egypt',31,'M',71,175,89,'No issues',NULL),(30,'Liverpool','Virgil van Dijk','CB','Netherlands',32,'M',92,193,88,'No issues',NULL),(31,'Liverpool','Alisson Becker','GK','Brazil',31,'M',91,191,87,'No issues',NULL),(32,'Manchester City','Erling Haaland','ST','Norway',23,'M',87,194,91,'No issues',NULL),(33,'Manchester City','Kevin De Bruyne','CM','Belgium',32,'M',68,181,89,'No issues',NULL),(34,'Manchester City','Phil Foden','CM','England',23,'M',70,171,85,'No issues',NULL),(35,'Bayern Munich','Joshua Kimmich','CM','Germany',28,'M',75,176,87,'No issues',NULL),(36,'Bayern Munich','Sadio Mané','LW','Senegal',31,'M',69,175,85,'No issues',NULL),(37,'Bayern Munich','Manuel Neuer','GK','Germany',37,'M',93,193,89,'No issues',NULL),(38,'Paris Saint-Germain','Neymar Jr.','LW','Brazil',31,'M',68,175,87,'No issues',NULL),(39,'Paris Saint-Germain','Lionel Messi','RW','Argentina',36,'M',72,170,86,'No issues',NULL),(40,'Paris Saint-Germain','Gianluigi Donnarumma','GK','Italy',24,'M',90,196,86,'No issues',NULL),(41,'Liverpool','Mohamed Salah','RW','Egypt',31,'M',71,175,89,'No issues',NULL),(42,'Liverpool','Virgil van Dijk','CB','Netherlands',32,'M',92,193,88,'No issues',NULL),(43,'Liverpool','Alisson Becker','GK','Brazil',31,'M',91,191,87,'No issues',NULL),(44,'Manchester City','Erling Haaland','ST','Norway',23,'M',87,194,91,'No issues',NULL),(45,'Manchester City','Kevin De Bruyne','CM','Belgium',32,'M',68,181,89,'No issues',NULL),(46,'Manchester City','Phil Foden','CM','England',23,'M',70,171,85,'No issues',NULL),(47,'Bayern Munich','Joshua Kimmich','CM','Germany',28,'M',75,176,87,'No issues',NULL),(48,'Bayern Munich','Sadio Mané','LW','Senegal',31,'M',69,175,85,'No issues',NULL),(49,'Bayern Munich','Manuel Neuer','GK','Germany',37,'M',93,193,89,'No issues',NULL),(50,'Paris Saint-Germain','Neymar Jr.','LW','Brazil',31,'M',68,175,87,'No issues',NULL),(51,'Paris Saint-Germain','Lionel Messi','RW','Argentina',36,'M',72,170,86,'No issues',NULL),(52,'Paris Saint-Germain','Gianluigi Donnarumma','GK','Italy',24,'M',90,196,86,'No issues',NULL),(53,'Liverpool','Mohamed Salah','RW','Egypt',31,'M',71,175,89,'No issues',NULL),(54,'Liverpool','Virgil van Dijk','CB','Netherlands',32,'M',92,193,88,'No issues',NULL),(55,'Liverpool','Alisson Becker','GK','Brazil',31,'M',91,191,87,'No issues',NULL),(56,'Manchester City','Erling Haaland','ST','Norway',23,'M',87,194,91,'No issues',NULL),(57,'Manchester City','Kevin De Bruyne','CM','Belgium',32,'M',68,181,89,'No issues',NULL),(58,'Manchester City','Phil Foden','CM','England',23,'M',70,171,85,'No issues',NULL),(59,'Bayern Munich','Joshua Kimmich','CM','Germany',28,'M',75,176,87,'No issues',NULL),(60,'Bayern Munich','Sadio Mané','LW','Senegal',31,'M',69,175,85,'No issues',NULL),(61,'Bayern Munich','Manuel Neuer','GK','Germany',37,'M',93,193,89,'No issues',NULL),(62,'Paris Saint-Germain','Neymar Jr.','LW','Brazil',31,'M',68,175,87,'No issues',NULL),(63,'Paris Saint-Germain','Lionel Messi','RW','Argentina',36,'M',72,170,86,'No issues',NULL),(64,'Paris Saint-Germain','Gianluigi Donnarumma','GK','Italy',24,'M',90,196,86,'No issues',NULL),(65,'Liverpool','Mohamed Salah','RW','Egypt',31,'M',71,175,89,'No issues',NULL),(66,'Liverpool','Virgil van Dijk','CB','Netherlands',32,'M',92,193,88,'No issues',NULL),(67,'Liverpool','Alisson Becker','GK','Brazil',31,'M',91,191,87,'No issues',NULL),(68,'Manchester City','Erling Haaland','ST','Norway',23,'M',87,194,91,'No issues',NULL),(69,'Manchester City','Kevin De Bruyne','CM','Belgium',32,'M',68,181,89,'No issues',NULL),(70,'Manchester City','Phil Foden','CM','England',23,'M',70,171,85,'No issues',NULL),(71,'Bayern Munich','Joshua Kimmich','CM','Germany',28,'M',75,176,87,'No issues',NULL),(72,'Bayern Munich','Sadio Mané','LW','Senegal',31,'M',69,175,85,'No issues',NULL),(73,'Bayern Munich','Manuel Neuer','GK','Germany',37,'M',93,193,89,'No issues',NULL),(74,'Paris Saint-Germain','Neymar Jr.','LW','Brazil',31,'M',68,175,87,'No issues',NULL),(75,'Paris Saint-Germain','Lionel Messi','RW','Argentina',36,'M',72,170,86,'No issues',NULL),(76,'Paris Saint-Germain','Gianluigi Donnarumma','GK','Italy',24,'M',90,196,86,'No issues',NULL),(77,'Liverpool','Mohamed Salah','RW','Egypt',31,'M',71,175,89,'No issues',NULL),(78,'Liverpool','Virgil van Dijk','CB','Netherlands',32,'M',92,193,88,'No issues',NULL),(79,'Liverpool','Alisson Becker','GK','Brazil',31,'M',91,191,87,'No issues',NULL),(80,'Manchester City','Erling Haaland','ST','Norway',23,'M',87,194,91,'No issues',NULL),(81,'Manchester City','Kevin De Bruyne','CM','Belgium',32,'M',68,181,89,'No issues',NULL),(82,'Manchester City','Phil Foden','CM','England',23,'M',70,171,85,'No issues',NULL),(83,'Bayern Munich','Joshua Kimmich','CM','Germany',28,'M',75,176,87,'No issues',NULL),(84,'Bayern Munich','Sadio Mané','LW','Senegal',31,'M',69,175,85,'No issues',NULL),(85,'Bayern Munich','Manuel Neuer','GK','Germany',37,'M',93,193,89,'No issues',NULL),(86,'Paris Saint-Germain','Neymar Jr.','LW','Brazil',31,'M',68,175,87,'No issues',NULL),(87,'Paris Saint-Germain','Lionel Messi','RW','Argentina',36,'M',72,170,86,'No issues',NULL),(88,'Paris Saint-Germain','Gianluigi Donnarumma','GK','Italy',24,'M',90,196,86,'No issues',NULL),(89,'Liverpool','Mohamed Salah','RW','Egypt',31,'M',71,175,89,'No issues',NULL),(90,'Liverpool','Virgil van Dijk','CB','Netherlands',32,'M',92,193,88,'No issues',NULL),(91,'Liverpool','Alisson Becker','GK','Brazil',31,'M',91,191,87,'No issues',NULL),(92,'Manchester City','Erling Haaland','ST','Norway',23,'M',87,194,91,'No issues',NULL),(93,'Manchester City','Kevin De Bruyne','CM','Belgium',32,'M',68,181,89,'No issues',NULL),(94,'Manchester City','Phil Foden','CM','England',23,'M',70,171,85,'No issues',NULL),(95,'Bayern Munich','Joshua Kimmich','CM','Germany',28,'M',75,176,87,'No issues',NULL),(96,'Bayern Munich','Sadio Mané','LW','Senegal',31,'M',69,175,85,'No issues',NULL),(97,'Bayern Munich','Manuel Neuer','GK','Germany',37,'M',93,193,89,'No issues',NULL),(98,'Paris Saint-Germain','Neymar Jr.','LW','Brazil',31,'M',68,175,87,'No issues',NULL),(99,'Paris Saint-Germain','Lionel Messi','RW','Argentina',36,'M',72,170,86,'No issues',NULL),(100,'Paris Saint-Germain','Gianluigi Donnarumma','GK','Italy',24,'M',90,196,86,'No issues',NULL),(101,'Liverpool','Mohamed Salah','RW','Egypt',31,'M',71,175,89,'No issues',NULL),(102,'Liverpool','Virgil van Dijk','CB','Netherlands',32,'M',92,193,88,'No issues',NULL),(103,'Liverpool','Alisson Becker','GK','Brazil',31,'M',91,191,87,'No issues',NULL),(104,'Manchester City','Erling Haaland','ST','Norway',23,'M',87,194,91,'No issues',NULL),(105,'Manchester City','Kevin De Bruyne','CM','Belgium',32,'M',68,181,89,'No issues',NULL),(106,'Manchester City','Phil Foden','CM','England',23,'M',70,171,85,'No issues',NULL),(107,'Bayern Munich','Joshua Kimmich','CM','Germany',28,'M',75,176,87,'No issues',NULL),(108,'Bayern Munich','Sadio Mané','LW','Senegal',31,'M',69,175,85,'No issues',NULL),(109,'Bayern Munich','Manuel Neuer','GK','Germany',37,'M',93,193,89,'No issues',NULL),(110,'Paris Saint-Germain','Neymar Jr.','LW','Brazil',31,'M',68,175,87,'No issues',NULL),(111,'Paris Saint-Germain','Lionel Messi','RW','Argentina',36,'M',72,170,86,'No issues',NULL),(112,'Paris Saint-Germain','Gianluigi Donnarumma','GK','Italy',24,'M',90,196,86,'No issues',NULL),(113,'Liverpool','Mohamed Salah','RW','Egypt',31,'M',71,175,89,'No issues',NULL),(114,'Liverpool','Virgil van Dijk','CB','Netherlands',32,'M',92,193,88,'No issues',NULL),(115,'Liverpool','Alisson Becker','GK','Brazil',31,'M',91,191,87,'No issues',NULL),(117,'Real Madrid','Azizul','CF','Bangladesh',23,'M',55,180,99,'fit','null'),(122,'Varca Real','Luis Moreno','striker','Spain',27,'M',42,424,84,'Fit','null'),(123,'','','','',0,'',0,0,0,'','');
/*!40000 ALTER TABLE `allplayers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `champions_league_points_table`
--

DROP TABLE IF EXISTS `champions_league_points_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `champions_league_points_table` (
  `club_id` int NOT NULL,
  `club_name` varchar(100) DEFAULT NULL,
  `win` int DEFAULT '0',
  `loss` int DEFAULT '0',
  `draw` int DEFAULT '0',
  `goal_distributed` int DEFAULT '0',
  `total_points` int DEFAULT '0',
  PRIMARY KEY (`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `champions_league_points_table`
--

LOCK TABLES `champions_league_points_table` WRITE;
/*!40000 ALTER TABLE `champions_league_points_table` DISABLE KEYS */;
INSERT INTO `champions_league_points_table` VALUES (1,'Manchester City',9,2,1,30,28),(2,'Bayern Munich',8,3,1,25,25),(3,'Liverpool',7,4,1,27,22);
/*!40000 ALTER TABLE `champions_league_points_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_info`
--

DROP TABLE IF EXISTS `club_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `club_name` varchar(100) NOT NULL,
  `strength` varchar(100) DEFAULT NULL,
  `strength_level` enum('Very Strong','Strong','Weak','Very Weak') DEFAULT NULL,
  `weakness` varchar(100) DEFAULT NULL,
  `weakness_level` enum('Very Strong','Strong','Weak','Very Weak') DEFAULT NULL,
  `playing_style` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_info`
--

LOCK TABLES `club_info` WRITE;
/*!40000 ALTER TABLE `club_info` DISABLE KEYS */;
INSERT INTO `club_info` VALUES (1,'FC Barcelona','Possession football','Very Strong',NULL,NULL,NULL),(2,'FC Barcelona','Quick passing','Very Strong',NULL,NULL,NULL),(3,'FC Barcelona','Youth development','Strong',NULL,NULL,NULL),(4,'FC Barcelona','Strong midfield control','Strong',NULL,NULL,NULL),(5,'FC Barcelona',NULL,NULL,'Aerial duels','Weak',NULL),(6,'FC Barcelona',NULL,NULL,'Defensive depth','Weak',NULL),(7,'FC Barcelona',NULL,NULL,'Counter-attacks','Very Weak',NULL),(8,'FC Barcelona',NULL,NULL,'Injuries to key players','Very Weak',NULL),(9,'FC Barcelona',NULL,NULL,NULL,NULL,'Tiki-taka'),(10,'FC Barcelona',NULL,NULL,NULL,NULL,'High pressing'),(11,'FC Barcelona',NULL,NULL,NULL,NULL,'Building from the back'),(12,'Bayern Munich','High pressing','Very Strong',NULL,NULL,NULL),(13,'Bayern Munich','Squad depth','Very Strong',NULL,NULL,NULL),(14,'Bayern Munich','Attacking flexibility','Strong',NULL,NULL,NULL),(15,'Bayern Munich',NULL,NULL,'Overcommitting forward','Weak',NULL),(16,'Bayern Munich',NULL,NULL,'Defensive vulnerability on counter','Weak',NULL),(17,'Bayern Munich',NULL,NULL,'Injuries in key matches','Very Weak',NULL),(18,'Bayern Munich',NULL,NULL,NULL,NULL,'High line with fast transition'),(19,'Bayern Munich',NULL,NULL,NULL,NULL,'Fast wing play'),(20,'Bayern Munich',NULL,NULL,NULL,NULL,'Direct attacking approach'),(21,'Manchester City','Tactical versatility','Very Strong',NULL,NULL,NULL),(22,'Manchester City','Squad rotation','Very Strong',NULL,NULL,NULL),(23,'Manchester City','Ball possession','Very Strong',NULL,NULL,NULL),(24,'Manchester City',NULL,NULL,'Overplaying in defense','Weak',NULL),(25,'Manchester City',NULL,NULL,'Struggles against deep blocks','Weak',NULL),(26,'Manchester City',NULL,NULL,'Fatigue in tight schedule','Very Weak',NULL),(27,'Manchester City',NULL,NULL,NULL,NULL,'Possession-based build-up'),(28,'Manchester City',NULL,NULL,NULL,NULL,'Press and recover quickly'),(29,'Manchester City',NULL,NULL,NULL,NULL,'Overlapping full-backs'),(30,'Chelsea','Solid defense','Strong',NULL,NULL,NULL),(31,'Chelsea','Counter-attacking ability','Strong',NULL,NULL,NULL),(32,'Chelsea','Youth potential','Strong',NULL,NULL,NULL),(33,'Chelsea',NULL,NULL,'Lack of consistent scoring','Weak',NULL),(34,'Chelsea',NULL,NULL,'Managerial inconsistency','Weak',NULL),(35,'Chelsea',NULL,NULL,'Injury-prone squad','Very Weak',NULL),(36,'Chelsea',NULL,NULL,NULL,NULL,'Compact defense with fast break'),(37,'Chelsea',NULL,NULL,NULL,NULL,'Utilizing wing backs'),(38,'Chelsea',NULL,NULL,NULL,NULL,'Structured build-up play'),(39,'Real Madrid','Counter-attacks','Very Strong',NULL,NULL,NULL),(40,'Real Madrid','Experience in big games','Very Strong',NULL,NULL,NULL),(41,'Real Madrid','Creative midfield','Strong',NULL,NULL,NULL),(42,'Real Madrid',NULL,NULL,'Aging key players','Weak',NULL),(43,'Real Madrid',NULL,NULL,'Defensive lapses','Weak',NULL),(44,'Real Madrid',NULL,NULL,'Inconsistency in league matches','Very Weak',NULL),(45,'Real Madrid',NULL,NULL,NULL,NULL,'Quick counter-attacks'),(46,'Real Madrid',NULL,NULL,NULL,NULL,'Midfield dominance'),(47,'Real Madrid',NULL,NULL,NULL,NULL,'Utilizing flanks effectively');
/*!40000 ALTER TABLE `club_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_statistics`
--

DROP TABLE IF EXISTS `club_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_statistics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `club_name` varchar(100) DEFAULT NULL,
  `touches_left_percent` int DEFAULT NULL,
  `touches_middle_percent` int DEFAULT NULL,
  `touches_right_percent` int DEFAULT NULL,
  `touches_own_third_percent` int DEFAULT NULL,
  `touches_middle_third_percent` int DEFAULT NULL,
  `touches_opponent_third_percent` int DEFAULT NULL,
  `shots_left_percent` int DEFAULT NULL,
  `shots_middle_percent` int DEFAULT NULL,
  `shots_right_percent` int DEFAULT NULL,
  `shots_small_box_percent` int DEFAULT NULL,
  `shots_penalty_box_percent` int DEFAULT NULL,
  `shots_outside_box_percent` int DEFAULT NULL,
  `against_shots_left_percent` int DEFAULT NULL,
  `against_shots_middle_percent` int DEFAULT NULL,
  `against_shots_right_percent` int DEFAULT NULL,
  `against_shots_small_box_percent` int DEFAULT NULL,
  `against_shots_penalty_box_percent` int DEFAULT NULL,
  `against_shots_outside_box_percent` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_statistics`
--

LOCK TABLES `club_statistics` WRITE;
/*!40000 ALTER TABLE `club_statistics` DISABLE KEYS */;
INSERT INTO `club_statistics` VALUES (1,'Fc Barcelona',30,40,30,20,35,45,25,50,25,10,70,20,15,60,25,10,65,25),(2,'Bayern Munich',32,38,30,22,33,45,28,48,24,15,65,20,12,58,30,13,63,24),(3,'Manchester City',35,40,25,18,37,45,30,50,20,12,68,20,14,60,26,11,67,22),(4,'Chelsea',32,36,32,28,39,33,27,45,28,18,52,30,30,43,27,22,51,27),(5,'Atlético Madrid',30,40,30,40,35,25,25,42,33,15,48,37,29,46,25,20,50,30);
/*!40000 ALTER TABLE `club_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laliga_points_table`
--

DROP TABLE IF EXISTS `laliga_points_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laliga_points_table` (
  `club_name` varchar(100) NOT NULL,
  `win` int DEFAULT '0',
  `loss` int DEFAULT '0',
  `draw` int DEFAULT '0',
  `goal_distributed` int DEFAULT '0',
  `total_points` int DEFAULT '0',
  PRIMARY KEY (`club_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laliga_points_table`
--

LOCK TABLES `laliga_points_table` WRITE;
/*!40000 ALTER TABLE `laliga_points_table` DISABLE KEYS */;
INSERT INTO `laliga_points_table` VALUES ('Alaves',6,21,11,28,29),('Almeria',3,25,10,27,19),('Athletic Club',13,13,12,42,51),('Atletico Madrid',24,7,7,65,79),('Barcelona',26,5,7,70,85),('Cadiz',5,22,11,25,26),('Celta Vigo',8,19,11,37,35),('Getafe',10,17,11,33,41),('Girona',25,6,7,75,82),('Granada',7,20,11,31,32),('Las Palmas',10,17,11,29,41),('Mallorca',9,15,14,30,41),('Osasuna',11,16,11,36,44),('Rayo Vallecano',9,18,11,34,38),('Real Betis',18,11,9,48,63),('Real Madrid',28,4,6,78,90),('Real Sociedad',19,10,9,52,66),('Sevilla',14,14,10,45,52),('Valencia',12,15,11,38,47),('Villarreal',16,12,10,51,58);
/*!40000 ALTER TABLE `laliga_points_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_statistics`
--

DROP TABLE IF EXISTS `match_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `match_statistics` (
  `matchday` int NOT NULL AUTO_INCREMENT,
  `match_date` date DEFAULT NULL,
  `match_time` time DEFAULT NULL,
  `tournament` enum('SSL','SCDR','UCL','UEL','USC','CWC','IC') NOT NULL,
  `my_club` varchar(100) DEFAULT 'Real Madrid',
  `opponent_club` varchar(100) NOT NULL,
  `venue` enum('Home','Away','Neutral') DEFAULT NULL,
  `stadium` varchar(100) DEFAULT NULL,
  `my_score` int DEFAULT NULL,
  `opponent_score` int DEFAULT NULL,
  `my_goal_scorers` text,
  `opponent_goal_scorers` text,
  `result` enum('Win','Loss','Draw','TBD') DEFAULT NULL,
  `my_total_shots` int DEFAULT NULL,
  `opponent_total_shots` int DEFAULT NULL,
  `my_shots_on_target` int DEFAULT NULL,
  `opponent_shots_on_target` int DEFAULT NULL,
  `my_possession` decimal(5,2) DEFAULT NULL,
  `opponent_possession` decimal(5,2) DEFAULT NULL,
  `my_passes` int DEFAULT NULL,
  `opponent_passes` int DEFAULT NULL,
  `my_pass_accuracy` decimal(5,2) DEFAULT NULL,
  `opponent_pass_accuracy` decimal(5,2) DEFAULT NULL,
  `my_fouls` int DEFAULT NULL,
  `opponent_fouls` int DEFAULT NULL,
  `my_yellow_cards` int DEFAULT NULL,
  `opponent_yellow_cards` int DEFAULT NULL,
  `my_red_cards` int DEFAULT NULL,
  `opponent_red_cards` int DEFAULT NULL,
  `my_offsides` int DEFAULT NULL,
  `opponent_offsides` int DEFAULT NULL,
  `my_corners` int DEFAULT NULL,
  `opponent_corners` int DEFAULT NULL,
  PRIMARY KEY (`matchday`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_statistics`
--

LOCK TABLES `match_statistics` WRITE;
/*!40000 ALTER TABLE `match_statistics` DISABLE KEYS */;
INSERT INTO `match_statistics` VALUES (1,'2024-08-18',NULL,'SSL','Real Madrid','Getafe','Away','Coliseum Alfonso Pérez',2,1,'Vinicius, Rodrygo','Mata','Win',15,10,7,4,60.50,39.50,520,370,88.20,81.30,8,10,2,3,0,0,1,2,5,3),(2,'2024-08-25',NULL,'SSL','Real Madrid','Alavés','Home','Santiago Bernabéu',3,0,'Bellingham, Vinicius, Joselu','','Win',18,5,9,2,65.00,35.00,540,300,90.10,76.40,7,12,1,2,0,0,2,1,7,2),(3,'2024-09-10',NULL,'UCL','Real Madrid','Manchester City','Home','Santiago Bernabéu',1,1,'Bellingham (P)','Haaland','Draw',13,14,6,6,48.70,51.30,460,500,85.00,87.60,11,9,3,2,1,0,3,1,4,4),(4,'2024-12-05',NULL,'SCDR','Real Madrid','Rayo Vallecano','Home','Santiago Bernabéu',2,0,'Modric, Joselu','','Win',17,6,8,1,63.20,36.80,570,310,89.40,78.60,9,14,2,3,0,0,2,1,6,3),(5,'2024-12-09',NULL,'SSL','Real Madrid','Athletico Madrid','Away','Santiago Bernabéu',1,1,'Rodrygo','Alvarez','Draw',14,9,6,3,57.10,42.90,500,390,86.00,80.20,11,10,3,1,0,0,1,2,5,4),(6,'2025-01-10',NULL,'USC','Real Madrid','Fc Barcelona','Neutral','King Saud University Stadium',3,2,'Bellingham (P), Vinicius, Rodrygo','Lewandowski, Gavi','Win',19,13,9,5,50.00,50.00,510,505,88.00,87.80,10,9,2,3,0,0,2,2,7,5),(7,'2025-02-14',NULL,'UCL','Real Madrid','Bayern Munich','Away','Allianz Arena',0,1,'','Kane','Loss',11,15,4,7,45.30,54.70,460,520,85.00,89.30,12,7,3,1,0,0,3,2,4,6),(13,'2025-04-15','21:30:00','SSL','Real Madrid','Fc Barcelona','Away','Camp Nou',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'2025-04-22','21:00:00','SCDR','Real Madrid','Athletico Madrid','Home','Santiago Bernabéu',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'2025-05-01','21:30:00','UCL','Real Madrid','Manchester City','Neutral','Atatürk Olympic Stadium',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'2025-08-10','22:00:00','USC','Real Madrid','Chelsea','Neutral','Estádio do Dragão',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `match_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playerfifaratings`
--

DROP TABLE IF EXISTS `playerfifaratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playerfifaratings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `club` varchar(100) DEFAULT NULL,
  `position` varchar(10) DEFAULT NULL,
  `overallRating` int DEFAULT NULL,
  `pace` int DEFAULT NULL,
  `shooting` int DEFAULT NULL,
  `passing` int DEFAULT NULL,
  `dribbling` int DEFAULT NULL,
  `defending` int DEFAULT NULL,
  `physicality` int DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerfifaratings`
--

LOCK TABLES `playerfifaratings` WRITE;
/*!40000 ALTER TABLE `playerfifaratings` DISABLE KEYS */;
INSERT INTO `playerfifaratings` VALUES (1,'Kylian Mbappé','France','Real Madrid','ST',91,96,88,84,92,40,77,NULL),(2,'Vinícius Júnior','Brazil','Real Madrid','LW',88,95,75,80,91,35,68,NULL),(3,'Jude Bellingham','England','Real Madrid','CM',86,78,76,83,85,70,82,NULL),(4,'Rodrygo','Brazil','Real Madrid','RW',84,89,72,78,87,38,65,NULL),(5,'Federico Valverde','Uruguay','Real Madrid','CM',85,85,73,80,82,75,80,NULL),(6,'Luka Modrić','Croatia','Real Madrid','CM',86,72,77,88,89,60,67,NULL),(7,'Antonio Rüdiger','Germany','Real Madrid','CB',84,76,55,70,70,85,90,NULL),(8,'Eduardo Camavinga','France','Real Madrid','CM',84,80,70,78,82,72,75,NULL),(9,'Éder Militão','Brazil','Real Madrid','CB',83,77,50,68,65,82,88,NULL),(10,'Robert Lewandowski','Poland','Barcelona','ST',89,78,88,82,84,40,78,NULL),(11,'Pedri','Spain','Barcelona','CM',85,77,70,88,86,50,58,NULL),(12,'Frenkie de Jong','Netherlands','Barcelona','CM',86,75,70,85,83,65,72,NULL),(13,'Raphinha','Brazil','Barcelona','RW',84,91,78,80,88,38,65,NULL),(14,'Gavi','Spain','Barcelona','CM',83,79,68,82,85,50,60,NULL),(15,'Jules Koundé','France','Barcelona','CB',83,79,60,70,68,82,80,NULL),(16,'Ousmane Dembélé','France','Barcelona','LW',83,92,75,78,88,35,62,NULL),(17,'Marc-André ter Stegen','Germany','Barcelona','GK',90,65,50,80,75,85,70,NULL),(18,'Ronald Araújo','Uruguay','Barcelona','CB',84,82,55,68,70,85,85,NULL),(19,'Franck Kessié','Ivory Coast','Barcelona','CM',83,78,70,79,80,70,82,NULL);
/*!40000 ALTER TABLE `playerfifaratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playerperformance`
--

DROP TABLE IF EXISTS `playerperformance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playerperformance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clubName` varchar(100) DEFAULT NULL,
  `playerName` varchar(100) DEFAULT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `appearancesTotal` int DEFAULT NULL,
  `appearancesLaLiga` int DEFAULT NULL,
  `appearancesChampionsLeague` int DEFAULT NULL,
  `appearancesCopaDelRey` int DEFAULT NULL,
  `appearancesUEFASuperCup` int DEFAULT NULL,
  `appearancesNationalTeam` int DEFAULT NULL,
  `goalsTotal` int DEFAULT NULL,
  `goalsLaLiga` int DEFAULT NULL,
  `goalsChampionsLeague` int DEFAULT NULL,
  `goalsCopaDelRey` int DEFAULT NULL,
  `goalsUEFASuperCup` int DEFAULT NULL,
  `goalsNationalTeam` int DEFAULT NULL,
  `assistsTotal` int DEFAULT NULL,
  `assistsLaLiga` int DEFAULT NULL,
  `assistsChampionsLeague` int DEFAULT NULL,
  `assistsCopaDelRey` int DEFAULT NULL,
  `assistsUEFASuperCup` int DEFAULT NULL,
  `manOfTheMatchCount` int DEFAULT NULL,
  `averageRating` double DEFAULT NULL,
  `seasonYear` int DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerperformance`
--

LOCK TABLES `playerperformance` WRITE;
/*!40000 ALTER TABLE `playerperformance` DISABLE KEYS */;
INSERT INTO `playerperformance` VALUES (1,'Real Madrid','Kylian Mbappé','France',45,29,8,4,1,3,41,29,7,2,1,2,6,4,1,1,0,7,8.5,2024,NULL),(2,'Real Madrid','Vinícius Júnior','Brazil',44,27,8,4,1,4,21,11,8,1,0,1,9,5,3,1,0,5,8.1,2024,NULL),(3,'Real Madrid','Jude Bellingham','England',43,26,8,4,1,4,14,9,3,1,1,0,8,5,2,1,0,6,8.2,2024,NULL),(4,'Real Madrid','Rodrygo','Brazil',42,30,8,2,1,1,19,6,5,0,0,1,9,5,3,1,0,4,7.9,2024,NULL),(5,'Real Madrid','Federico Valverde','Uruguay',40,31,6,2,1,3,9,6,0,2,1,0,7,4,2,1,0,3,7.8,2024,NULL),(6,'Real Madrid','Endrick','Brazil',25,15,5,4,1,0,7,1,1,5,0,0,2,1,0,1,0,2,7.5,2024,NULL),(7,'Real Madrid','Brahim Díaz','Morocco',30,20,6,3,1,0,6,4,2,0,0,0,4,2,1,1,0,2,7.6,2024,NULL),(8,'Real Madrid','Arda Güler','Turkey',28,18,5,4,1,0,5,3,0,2,0,0,3,2,0,1,0,1,7.4,2024,NULL),(9,'Real Madrid','Luka Modrić','Croatia',35,25,6,3,1,5,4,2,0,2,0,0,6,3,1,2,0,2,7.7,2024,NULL),(10,'Real Madrid','Antonio Rüdiger','Germany',38,30,6,1,1,4,3,0,2,1,0,0,1,0,1,0,0,3,7.6,2024,NULL),(11,'Real Madrid','Eduardo Camavinga','France',36,28,5,2,1,3,2,1,0,1,0,0,4,2,1,1,0,2,7.5,2024,NULL),(12,'Real Madrid','Aurélien Tchouaméni','France',34,26,5,2,1,3,2,0,0,2,0,0,3,1,1,1,0,1,7.4,2024,NULL),(13,'Real Madrid','Lucas Vázquez','Spain',32,28,3,1,0,2,2,1,1,0,0,0,5,3,1,1,0,1,7.3,2024,NULL),(14,'Real Madrid','Dani Carvajal','Spain',30,27,2,1,0,3,1,1,0,0,0,0,2,1,1,0,0,1,7.2,2024,NULL),(15,'Real Madrid','Éder Militão','Brazil',20,15,3,1,1,2,1,1,0,0,0,0,1,1,0,0,0,1,7.1,2024,NULL),(16,'Barcelona','Robert Lewandowski','Poland',44,28,8,5,1,2,33,20,9,3,1,0,8,5,2,1,0,6,8.45,2024,NULL),(17,'Barcelona','Pedri','Spain',43,30,7,4,1,4,10,6,2,1,1,0,14,10,3,1,0,7,8.2,2024,NULL),(18,'Barcelona','Frenkie de Jong','Netherlands',41,28,6,4,1,3,7,4,1,2,0,0,9,6,2,1,0,4,7.95,2024,NULL),(19,'Barcelona','Raphinha','Brazil',40,29,7,3,1,2,14,8,4,1,1,0,6,4,1,1,0,5,8.1,2024,NULL),(20,'Barcelona','Gavi','Spain',38,27,6,3,1,3,8,5,2,0,1,0,10,7,2,1,0,6,8,2024,NULL),(21,'Barcelona','Jules Koundé','France',39,31,5,2,1,3,3,1,1,0,1,0,4,3,1,0,0,2,7.6,2024,NULL),(22,'Barcelona','Ousmane Dembélé','France',35,23,6,3,1,1,6,3,2,1,0,0,7,4,1,1,0,3,7.7,2024,NULL),(23,'Barcelona','Marc-André ter Stegen','Germany',46,38,8,0,1,4,0,0,0,0,0,0,0,0,0,0,0,8,7.9,2024,NULL),(24,'Barcelona','Ronald Araújo','Uruguay',40,31,6,2,1,3,2,1,0,1,0,0,1,1,0,0,0,4,7.7,2024,NULL),(25,'Barcelona','Franck Kessié','Ivory Coast',38,30,5,2,1,3,4,2,1,0,1,0,5,3,1,1,0,3,7.75,2024,NULL);
/*!40000 ALTER TABLE `playerperformance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playerstatus`
--

DROP TABLE IF EXISTS `playerstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playerstatus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `playerName` varchar(100) NOT NULL,
  `playerClub` varchar(100) NOT NULL,
  `salary` decimal(15,2) NOT NULL,
  `joiningDate` date NOT NULL,
  `leavingDate` date DEFAULT NULL,
  `playerBonus` decimal(15,2) DEFAULT '0.00',
  `medicalStatus` varchar(50) NOT NULL,
  `leaveInDate` date DEFAULT NULL,
  `yellowCardsLAL` int DEFAULT '0',
  `redCardsLAL` int DEFAULT '0',
  `yellowCardsUCL` int DEFAULT '0',
  `redCardsUCL` int DEFAULT '0',
  `yellowCardsCDR` int DEFAULT '0',
  `redCardsCDR` int DEFAULT '0',
  `yellowCardsUSC` int DEFAULT '0',
  `redCardsUSC` int DEFAULT '0',
  `missUpcomingGames` int DEFAULT '0',
  `missLAL` int DEFAULT '0',
  `missUCL` int DEFAULT '0',
  `missCDR` int DEFAULT '0',
  `missUSC` int DEFAULT '0',
  `medicalDescription` text,
  `imagePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerstatus`
--

LOCK TABLES `playerstatus` WRITE;
/*!40000 ALTER TABLE `playerstatus` DISABLE KEYS */;
INSERT INTO `playerstatus` VALUES (1,'Thibaut Courtois','Real Madrid',14000000.00,'2018-08-01','2026-06-30',1000000.00,'Fit',NULL,1,0,0,0,0,0,0,0,0,0,0,0,0,'No medical issues',NULL),(2,'Éder Militão','Real Madrid',9000000.00,'2019-07-01','2027-06-30',450000.00,'Fit',NULL,3,0,1,0,0,0,0,0,0,0,0,0,0,'No issues',NULL),(3,'Antonio Rüdiger','Real Madrid',10000000.00,'2022-07-01','2027-06-30',750000.00,'Recovering',NULL,2,0,1,0,0,0,0,0,2,2,1,0,0,'Knee injury, expected return in 3 weeks',NULL),(4,'Jude Bellingham','Real Madrid',20000000.00,'2023-07-01','2029-06-30',1500000.00,'Fit',NULL,1,0,0,0,0,0,0,0,0,0,0,0,0,'No issues',NULL),(5,'Luka Modrić','Real Madrid',16000000.00,'2012-08-27','2025-06-30',900000.00,'Fit',NULL,4,1,3,0,1,0,0,0,1,1,0,0,0,'Minor muscle strain',NULL),(6,'Toni Kroos','Real Madrid',14000000.00,'2014-07-01','2025-06-30',850000.00,'Fit',NULL,3,0,1,0,0,0,0,0,0,0,0,0,0,'No issues',NULL),(7,'Vinícius Júnior','Real Madrid',13000000.00,'2018-07-01','2026-06-30',1500000.00,'Fit',NULL,3,0,2,0,0,0,0,0,0,0,0,0,0,'No issues',NULL),(8,'Rodrygo','Real Madrid',9000000.00,'2019-07-01','2027-06-30',600000.00,'Fit',NULL,2,0,1,0,0,0,0,0,0,0,0,0,0,'No issues',NULL),(9,'Marc-André ter Stegen','Barcelona',12500000.00,'2014-07-01','2025-06-30',1100000.00,'Fit',NULL,0,0,2,0,0,0,0,0,0,0,0,0,0,'No medical issues',NULL),(10,'Ronald Araújo','Barcelona',8000000.00,'2018-07-01','2027-06-30',500000.00,'Fit',NULL,2,0,1,0,0,0,0,0,0,0,0,0,0,'No issues',NULL),(11,'Jules Koundé','Barcelona',9500000.00,'2022-07-01','2027-06-30',600000.00,'Fit',NULL,3,0,2,0,0,0,0,0,0,0,0,0,0,'No issues',NULL),(12,'Frenkie de Jong','Barcelona',11000000.00,'2019-08-01','2026-06-30',700000.00,'Fit',NULL,3,0,1,0,1,0,0,0,0,0,0,0,0,'No issues',NULL),(13,'Pedri','Barcelona',8500000.00,'2020-08-01','2027-06-30',500000.00,'Fit',NULL,2,0,1,0,0,0,0,0,0,0,0,0,0,'No issues',NULL),(14,'Robert Lewandowski','Barcelona',22000000.00,'2022-07-01','2026-06-30',1800000.00,'Fit',NULL,1,0,0,0,0,0,0,0,0,0,0,0,0,'No injuries',NULL),(15,'Raphinha','Barcelona',12000000.00,'2022-07-01','2027-06-30',850000.00,'Fit',NULL,2,0,1,0,0,0,0,0,0,0,0,0,0,'No issues',NULL),(16,'Gavi','Barcelona',7000000.00,'2021-07-01','2027-06-30',450000.00,'Fit',NULL,1,0,0,0,0,0,0,0,0,0,0,0,0,'No issues',NULL),(17,'Jordi Alba','Barcelona',8000000.00,'2012-08-01','2025-06-30',400000.00,'Injured',NULL,4,1,2,0,0,0,0,0,3,3,2,0,0,'Muscle injury, out 2 weeks',NULL),(18,'Thomas Meier','Bayern',5000043.00,'2025-07-05','2025-05-22',3434.00,'Fit','2025-05-01',0,3,2,3,3,3,3,3,3,3,3,3,3,'','');
/*!40000 ALTER TABLE `playerstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_sessions`
--

DROP TABLE IF EXISTS `training_sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `training_sessions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `player_name` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `session_type` varchar(50) DEFAULT NULL,
  `attendance_status` varchar(20) DEFAULT NULL,
  `fitness_level` varchar(20) DEFAULT NULL,
  `injury_notes` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_sessions`
--

LOCK TABLES `training_sessions` WRITE;
/*!40000 ALTER TABLE `training_sessions` DISABLE KEYS */;
INSERT INTO `training_sessions` VALUES (1,'Vinícius Júnior','2025-06-04','Tactical','Present','High',''),(2,'Luka Modrić','2025-06-04','Tactical','Present','Medium',''),(3,'Rodrygo Goes','2025-06-04','Tactical','Late','Medium','Traffic delay'),(4,'Antonio Rüdiger','2025-06-04','Tactical','Present','High',''),(5,'Luka Modrić','2025-06-07','Tactical','Absent','Low','Minor hamstring strain'),(6,'Luka Modrić','2025-06-10','Tactical','Late','Medium','Late arrival due to traffic'),(7,'Luka Modrić','2025-06-11','Tactical','Present','High',''),(8,'Karim Benzema','2025-06-05','Tactical','Present','High',''),(9,'Isco','2025-06-06','Tactical','Late','Medium','Traffic delay'),(10,'Federico Valverde','2025-06-04','Cardio','Present','High',''),(11,'Marcelo','2025-06-06','Cardio','Present','Medium',''),(12,'Jude Bellingham','2025-06-04','Strength','Present','High',''),(13,'Thibaut Courtois','2025-06-04','Strength','Present','High',''),(14,'John','2025-06-20','Strength','Present','High',''),(15,'Vinícius Júnior','2025-06-04','Tactical','Present','High','geting minor knee injury'),(16,'Vinícius Júnior','2025-06-04','Tactical','Present','High','fafafd');
/*!40000 ALTER TABLE `training_sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferwindow`
--

DROP TABLE IF EXISTS `transferwindow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transferwindow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `playerName` varchar(100) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `leftClub` varchar(100) DEFAULT NULL,
  `leftDate` date DEFAULT NULL,
  `joiningClub` varchar(100) DEFAULT NULL,
  `joiningDate` date DEFAULT NULL,
  `marketValue` decimal(15,2) DEFAULT NULL,
  `tradeType` varchar(50) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferwindow`
--

LOCK TABLES `transferwindow` WRITE;
/*!40000 ALTER TABLE `transferwindow` DISABLE KEYS */;
INSERT INTO `transferwindow` VALUES (1,'Jude Bellingham','Midfielder',89,'England','Borussia Dortmund','2023-06-01','Real Madrid','2023-07-01',120000000.00,'Permanent','/images/bellingham.png'),(2,'Vinicius Jr','Forward',91,'Brazil','Flamengo','2018-06-30','Real Madrid','2018-07-01',100000000.00,'Permanent','/images/vini.png'),(3,'Toni Kroos','Midfielder',86,'Germany','Bayern Munich','2014-06-01','Real Madrid','2014-07-01',80000000.00,'Permanent','/images/kroos.png'),(4,'Kylian Mbappe','Forward',92,'France','PSG','2024-06-30','Real Madrid','2024-07-01',150000000.00,'Free Transfer','/images/mbappe.png'),(5,'Eduardo Camavinga','Midfielder',85,'France','Rennes','2021-08-15','Real Madrid','2021-09-01',60000000.00,'Permanent','/images/camavinga.png'),(6,'Erling Haaland','Forward',91,'Norway','Manchester City','2024-06-30','',NULL,180000000.00,'Available','/images/haaland.png'),(7,'Kevin De Bruyne','Midfielder',89,'Belgium','Manchester City','2024-06-30','',NULL,95000000.00,'Available','/images/kdb.png'),(8,'Mohamed Salah','Forward',88,'Egypt','Liverpool','2024-06-30','',NULL,85000000.00,'Available','/images/salah.png'),(9,'Robert Lewandowski','Forward',87,'Poland','Barcelona','2024-06-30','Real Madrid',NULL,75000000.00,'Available',NULL),(10,'Luka Modric','Midfielder',85,'Croatia','Real Madrid','2024-06-30','Real Madrid',NULL,20000000.00,'Available',NULL),(11,'Phil Foden','Midfielder',88,'England','Manchester City','2024-06-30','',NULL,95000000.00,'Available','/images/foden.png'),(12,'Pedri','Midfielder',87,'Spain','Real Madrid','2025-06-23','',NULL,100000000.00,'Available',NULL),(13,'Bukayo Saka','Forward',87,'England','Arsenal','2024-06-30','',NULL,95000000.00,'Available','/images/saka.png'),(14,'Josko Gvardiol','Defender',86,'Croatia','Real Madrid','2025-06-23',NULL,NULL,70000000.00,'Available',NULL),(15,'Thibaut Courtois','Goalkeeper',89,'Belgium','Real Madrid','2024-06-30','',NULL,65000000.00,'Available','/images/courtois.png');
/*!40000 ALTER TABLE `transferwindow` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-23 23:44:06
