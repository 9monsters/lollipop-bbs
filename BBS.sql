CREATE DATABASE  IF NOT EXISTS `forum` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `forum`;
-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: forum
-- ------------------------------------------------------
-- Server version	5.6.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `forum_article_table`
--

DROP TABLE IF EXISTS `forum_article_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_article_table` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `is_top` int(11) NOT NULL,
  `total_reply` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `create_person_id` int(11) DEFAULT NULL,
  `last_update_person_id` int(11) DEFAULT NULL,
  `discussion_board_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `theme_article_id` int(11) DEFAULT NULL,
  `is_theme_article` int(11) DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  KEY `FK91929847E58F9333` (`last_update_person_id`),
  KEY `FK919298474C7CCC6A` (`user_id`),
  KEY `FK91929847255B091D` (`create_person_id`),
  KEY `FK9192984780C945A1` (`discussion_board_id`),
  KEY `FK91929847EA1A1A0` (`theme_article_id`),
  CONSTRAINT `FK91929847255B091D` FOREIGN KEY (`create_person_id`) REFERENCES `forum_user_table` (`user_id`),
  CONSTRAINT `FK919298474C7CCC6A` FOREIGN KEY (`user_id`) REFERENCES `forum_user_table` (`user_id`),
  CONSTRAINT `FK9192984780C945A1` FOREIGN KEY (`discussion_board_id`) REFERENCES `forum_board_table` (`board_id`),
  CONSTRAINT `FK91929847E58F9333` FOREIGN KEY (`last_update_person_id`) REFERENCES `forum_user_table` (`user_id`),
  CONSTRAINT `FK91929847EA1A1A0` FOREIGN KEY (`theme_article_id`) REFERENCES `forum_article_table` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum_article_table`
--

LOCK TABLES `forum_article_table` WRITE;
/*!40000 ALTER TABLE `forum_article_table` DISABLE KEYS */;
INSERT INTO `forum_article_table` VALUES (11,'数据结构答疑','数据结构答疑12121',0,0,'2013-04-13 22:55:02','2013-04-16 00:19:43',1,1,12,NULL,NULL,1),(23,'我爱Java','疯狂JAVA工作会',0,0,'2013-04-16 19:56:10',NULL,5,NULL,23,NULL,NULL,1),(27,NULL,'ssssssssss',0,0,'2013-04-16 20:23:42',NULL,2,NULL,12,NULL,11,2),(31,NULL,'踩一下！',0,0,'2013-04-16 20:54:21',NULL,1,NULL,12,NULL,11,2),(32,'admin发表了主题','admin发表了主题',0,0,'2013-04-16 20:54:49',NULL,1,NULL,12,NULL,NULL,1);
/*!40000 ALTER TABLE `forum_article_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum_board_table`
--

DROP TABLE IF EXISTS `forum_board_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_board_table` (
  `board_id` int(11) NOT NULL AUTO_INCREMENT,
  `board_name` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_person_id` int(11) DEFAULT NULL,
  `moderator_id` int(11) DEFAULT NULL,
  `column_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  KEY `FK6EDA6AB73084D0F0` (`moderator_id`),
  KEY `FK6EDA6AB7255B091D` (`create_person_id`),
  KEY `FK6EDA6AB76D6C388A` (`column_id`),
  CONSTRAINT `FK6EDA6AB7255B091D` FOREIGN KEY (`create_person_id`) REFERENCES `forum_user_table` (`user_id`),
  CONSTRAINT `FK6EDA6AB73084D0F0` FOREIGN KEY (`moderator_id`) REFERENCES `forum_user_table` (`user_id`),
  CONSTRAINT `FK6EDA6AB76D6C388A` FOREIGN KEY (`column_id`) REFERENCES `forum_column_table` (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum_board_table`
--

LOCK TABLES `forum_board_table` WRITE;
/*!40000 ALTER TABLE `forum_board_table` DISABLE KEYS */;
INSERT INTO `forum_board_table` VALUES (12,'数据结构 ','2013-04-05 23:38:06',1,2,6),(21,'数据库原理 ','2013-04-05 23:40:37',1,2,6),(23,'JAVA程序设计','2013-04-05 23:41:06',1,2,6),(25,'WebStorm','2015-04-28 00:53:32',1,4,13),(26,'intelliJ IDEA','2015-04-28 00:53:58',1,3,13);
/*!40000 ALTER TABLE `forum_board_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum_column_table`
--

DROP TABLE IF EXISTS `forum_column_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_column_table` (
  `column_id` int(11) NOT NULL AUTO_INCREMENT,
  `column_name` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`column_id`),
  KEY `FKE78AD403255B091D` (`create_person_id`),
  CONSTRAINT `FKE78AD403255B091D` FOREIGN KEY (`create_person_id`) REFERENCES `forum_user_table` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum_column_table`
--

LOCK TABLES `forum_column_table` WRITE;
/*!40000 ALTER TABLE `forum_column_table` DISABLE KEYS */;
INSERT INTO `forum_column_table` VALUES (6,'计算机科学与技术专业','计算机科学与技术专业','2013-04-05 23:31:04',1),(13,'jetBrian','最好用的IDE开发环境','2015-04-28 00:52:56',1);
/*!40000 ALTER TABLE `forum_column_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum_user_table`
--

DROP TABLE IF EXISTS `forum_user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum_user_table` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sexy` int(11) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  `regist_time` datetime NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum_user_table`
--

LOCK TABLES `forum_user_table` WRITE;
/*!40000 ALTER TABLE `forum_user_table` DISABLE KEYS */;
INSERT INTO `forum_user_table` VALUES (1,'admin','123456',1,'13111111111','1111111@163.com',1,3,'2013-03-03 00:00:00'),(2,'lisa','123456',2,'13222222222','22222@forum.com',1,2,'2013-04-04 23:04:25'),(3,'suki','123456',2,'13622298888','suki@forum.com',1,2,'2013-04-05 10:39:12'),(4,'joe','123456',2,'13533333333','abc@forum.com',1,2,'2013-04-06 17:55:10'),(5,'zhangsan','123456',1,'13678900000','zhangsan@forum.com',1,1,'2013-04-16 19:26:12');
/*!40000 ALTER TABLE `forum_user_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-28  0:55:56
