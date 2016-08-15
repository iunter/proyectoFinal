CREATE DATABASE  IF NOT EXISTS `consola` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `consola`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: consola
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paciente` (
  `idpaciente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` text,
  `apellido` text,
  `dni` int(11) DEFAULT NULL,
  `socio` int(11) DEFAULT NULL,
  `foto` text,
  `lat` int(11) DEFAULT NULL,
  `longg` int(11) DEFAULT NULL,
  `idobrasocial` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpaciente`),
  KEY `PacienteObra_idx` (`idobrasocial`),
  CONSTRAINT `PacienteObra` FOREIGN KEY (`idobrasocial`) REFERENCES `obrasocial` (`idobrasocial`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (1,'s','s',NULL,NULL,NULL,NULL,NULL,NULL),(2,'s','s',1,1,'s',1,1,NULL),(3,'s','s',1,1,'s',1,1,NULL),(4,'roberrrto','otrrrebor',2,666,'ieee',1,2,NULL),(5,'roberrrto','otrrrebor',2,666,'ieee',1,2,NULL),(6,'roberrrto','otrrrebor',2,666,'ieee',1,2,NULL),(7,'roberrrto','otrrrebor',2,666,'ieee',1,2,NULL),(8,'roberrrto','otrrrebor',2,666,'ieee',1,2,NULL),(9,'fsdf','dfsdf',456,87,NULL,NULL,NULL,NULL),(10,'njdsamkln','ndhsmkdnba',798,789,NULL,NULL,NULL,NULL),(11,'njdsamkln','ndhsmkdnba',798,789,NULL,NULL,NULL,NULL),(12,'ghjgj','gjhg',57,69,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-15 16:31:14
