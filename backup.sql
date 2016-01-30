-- MySQL dump 10.13  Distrib 5.5.16, for Win64 (x86)
--
-- Host: localhost    Database: exchange
-- ------------------------------------------------------
-- Server version	5.5.16

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `idClient` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nameClient` varchar(20) NOT NULL,
  `famClient` varchar(20) NOT NULL,
  `passport` varchar(10) NOT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Степан','Петров','MP1234567'),(2,'Елена','Сидорова','MP7654321'),(3,'Алексей','Васечкин','МС5254624'),(4,'Петр','Валуев','НА6752744'),(5,'Александра','Захаренко','ВМ3587284'),(44,'Иван','Попов','РЕ3653735'),(45,'Павел','Незнаев','КВ3327537'),(46,'Вера','Кукушкина','РК5753745');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `idCourse` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `idCurrency` int(11) unsigned NOT NULL,
  `sell` int(11) unsigned NOT NULL,
  `buy` int(11) unsigned NOT NULL,
  PRIMARY KEY (`idCourse`),
  KEY `idCurrency` (`idCurrency`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`idCurrency`) REFERENCES `currency` (`idCurrency`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (2,5,13120,13050),(3,4,8390,8360),(4,6,8250,8180),(5,8,15760,15670),(6,3,10980,10930),(7,7,3210,3150),(8,1,280,250),(9,10,2480,2450),(10,11,1060,1030);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `idCurrency` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `currencyCode` varchar(5) NOT NULL,
  `currencyName` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  PRIMARY KEY (`idCurrency`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'RUB','Рос. рубль','Россия'),(2,'AUD','Австр. доллар','Австралия'),(3,'EUR','Евро','Европа'),(4,'USD','Амер. доллар','США'),(5,'GBP','Фунт стерлингов','Великобритания'),(6,'CAD','Канад. доллар','Канада'),(7,'LTL','Лит','Литва'),(8,'LVL','Лат','Латвия'),(9,'CHF','Швейц. франк','Швейцария'),(10,'PLN','Злотый','Польша'),(11,'UAH','Гривна','Украина'),(12,'CZK','Чеш. крона','Чехия'),(13,'JPY','Иена','Япония'),(14,'CNY','Юань','Китай'),(15,'EEK','Эстон. крона','Эстония');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operation` (
  `idOperation` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `idType` smallint(6) unsigned NOT NULL,
  `idCourse` int(11) unsigned NOT NULL,
  `summ` int(11) unsigned NOT NULL,
  `summBYR` int(11) unsigned NOT NULL,
  `idOperator` int(11) unsigned NOT NULL,
  `idClient` int(11) unsigned NOT NULL,
  PRIMARY KEY (`idOperation`),
  KEY `idClient` (`idClient`),
  KEY `idOperator` (`idOperator`),
  KEY `idType` (`idType`),
  KEY `date` (`date`),
  KEY `idCurrency` (`idCourse`),
  CONSTRAINT `operation_ibfk_4` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  CONSTRAINT `operation_ibfk_1` FOREIGN KEY (`idType`) REFERENCES `operationtype` (`idType`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operation_ibfk_2` FOREIGN KEY (`idCourse`) REFERENCES `course` (`idCourse`),
  CONSTRAINT `operation_ibfk_3` FOREIGN KEY (`idOperator`) REFERENCES `operator` (`idOperator`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
INSERT INTO `operation` VALUES (1,'2011-12-25 13:59:11',2,2,10,63430,1,1),(2,'2011-12-25 14:23:12',1,6,2,100000,1,2),(3,'2011-12-27 21:41:25',1,4,22,99594,1,3),(4,'2011-12-27 21:44:19',2,3,100,4346400,1,4),(5,'2011-12-27 21:48:41',1,7,219,999516,1,5),(6,'2011-12-28 12:01:17',2,10,50,51500,2,44),(7,'2011-12-28 12:04:16',1,6,9,98820,2,44),(8,'2011-12-29 10:57:45',2,5,500,7835000,1,45),(9,'2011-12-29 12:22:42',1,9,201,498480,2,46);
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operationtype`
--

DROP TABLE IF EXISTS `operationtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operationtype` (
  `idType` smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  `typeOperation` varchar(10) NOT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operationtype`
--

LOCK TABLES `operationtype` WRITE;
/*!40000 ALTER TABLE `operationtype` DISABLE KEYS */;
INSERT INTO `operationtype` VALUES (1,'Продажа'),(2,'Покупка');
/*!40000 ALTER TABLE `operationtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operator`
--

DROP TABLE IF EXISTS `operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operator` (
  `idOperator` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `nameOperator` varchar(20) NOT NULL,
  `famOperator` varchar(20) NOT NULL,
  `positionOperator` varchar(20) NOT NULL,
  PRIMARY KEY (`idOperator`),
  KEY `login` (`login`),
  CONSTRAINT `operator_ibfk_1` FOREIGN KEY (`login`) REFERENCES `users` (`login`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operator`
--

LOCK TABLES `operator` WRITE;
/*!40000 ALTER TABLE `operator` DISABLE KEYS */;
INSERT INTO `operator` VALUES (1,'user','Иван','Иванов','кассир'),(2,'pupkin','Василий','Пупкин','старший кассир');
/*!40000 ALTER TABLE `operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `login` varchar(16) NOT NULL DEFAULT '',
  `pass` varchar(16) NOT NULL,
  `isAdmin` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','admin',1),('pupkin','pupkin',0),('user','user',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-12-29 14:10:39
