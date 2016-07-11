-- MySQL dump 10.13  Distrib 5.7.11, for osx10.10 (x86_64)
--
-- Host: localhost    Database: expenses
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Current Database: `expenses`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `expenses` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `expenses`;

--
-- Table structure for table `expense_categories`
--

DROP TABLE IF EXISTS `expense_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_categories`
--

LOCK TABLES `expense_categories` WRITE;
/*!40000 ALTER TABLE `expense_categories` DISABLE KEYS */;
INSERT INTO `expense_categories` VALUES (1,'grocery'),(2,'restaurant'),(3,'coffee shop'),(4,'baby_items'),(5,'hospital'),(6,'rent'),(7,'utilities'),(8,'gas'),(9,'drinking water'),(10,'cable'),(11,'phone bill');
/*!40000 ALTER TABLE `expense_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense_log`
--

DROP TABLE IF EXISTS `expense_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `money_source_id` int(11) DEFAULT NULL,
  `amount` double NOT NULL,
  `date` datetime NOT NULL,
  `comments` varchar(1024) DEFAULT NULL,
  `expense_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_log`
--

LOCK TABLES `expense_log` WRITE;
/*!40000 ALTER TABLE `expense_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `expense_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense_type`
--

DROP TABLE IF EXISTS `expense_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_type`
--

LOCK TABLES `expense_type` WRITE;
/*!40000 ALTER TABLE `expense_type` DISABLE KEYS */;
INSERT INTO `expense_type` VALUES (1,'monthly recurring'),(2,'one time');
/*!40000 ALTER TABLE `expense_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `money_source`
--

DROP TABLE IF EXISTS `money_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `money_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `money_source`
--

LOCK TABLES `money_source` WRITE;
/*!40000 ALTER TABLE `money_source` DISABLE KEYS */;
INSERT INTO `money_source` VALUES (1,'credit-ash'),(2,'debit-ash'),(3,'cash'),(4,'credit-sri'),(5,'debit-sri');
/*!40000 ALTER TABLE `money_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_category_map`
--

DROP TABLE IF EXISTS `store_category_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_category_map` (
  `store` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  UNIQUE KEY `store` (`store`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_category_map`
--

LOCK TABLES `store_category_map` WRITE;
/*!40000 ALTER TABLE `store_category_map` DISABLE KEYS */;
INSERT INTO `store_category_map` VALUES ('aachi','restaurant'),('att','phone bill'),('chromatic','coffee shop'),('coconut hill','grocery'),('comcast','cable'),('costco gas','gas'),('IKEA','furniture'),('komala','restaurant'),('NEW INDIA BAZA','grocery'),('pamf','hospital');
/*!40000 ALTER TABLE `store_category_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (1,'Coconut Hill'),(2,'Target'),(3,'Chromatic');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-10 18:27:45
