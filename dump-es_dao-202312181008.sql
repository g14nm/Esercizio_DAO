-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: es_dao
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `autori`
--

DROP TABLE IF EXISTS `autori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autori` (
  `codiceA` int NOT NULL AUTO_INCREMENT,
  `nomeA` varchar(255) DEFAULT NULL,
  `cognome` varchar(255) NOT NULL,
  `annoN` int NOT NULL,
  `annoM` int DEFAULT NULL,
  `sesso` char(1) NOT NULL,
  `nazione` varchar(255) NOT NULL,
  PRIMARY KEY (`codiceA`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autori`
--

LOCK TABLES `autori` WRITE;
/*!40000 ALTER TABLE `autori` DISABLE KEYS */;
INSERT INTO `autori` VALUES (1,'Alessandro','Manzoni',1785,1873,'M','Italia'),(2,'Lev','Tolstoj',1828,1910,'M','Russia'),(3,'Bruno','Vespa',1944,NULL,'M','Italia'),(4,'Stephen','King',1947,NULL,'M','Stati Uniti'),(5,'Ernest','Hemingway',1899,1961,'M','Stati Uniti'),(6,'Umberto','Eco',1932,2016,'M','Italia'),(7,'Susanna','Tamaro',1957,NULL,'F','Italia'),(8,'Virginia','Woolf',1882,1941,'F','Inghilterra'),(9,'Agatha','Christie',1890,1976,'F','Inghilterra');
/*!40000 ALTER TABLE `autori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editori`
--

DROP TABLE IF EXISTS `editori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editori` (
  `codiceE` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`codiceE`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editori`
--

LOCK TABLES `editori` WRITE;
/*!40000 ALTER TABLE `editori` DISABLE KEYS */;
INSERT INTO `editori` VALUES (1,'Rizzoli'),(2,'Mondadori'),(3,'Laterza'),(4,'De Agostini'),(5,'Bompiani'),(7,'Laterza'),(8,'De Agostini'),(9,'Bompiani'),(11,'Laterza'),(12,'De Agostini'),(13,'Bompiani'),(15,'Laterza'),(16,'De Agostini'),(17,'Bompiani'),(19,'Laterza'),(20,'De Agostini'),(21,'Bompiani'),(23,'Laterza'),(24,'De Agostini'),(25,'Bompiani'),(27,'Laterza'),(28,'De Agostini'),(29,'Bompiani'),(31,'Laterza'),(32,'De Agostini'),(33,'Bompiani');
/*!40000 ALTER TABLE `editori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generi`
--

DROP TABLE IF EXISTS `generi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generi` (
  `codiceG` int NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(255) NOT NULL,
  PRIMARY KEY (`codiceG`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generi`
--

LOCK TABLES `generi` WRITE;
/*!40000 ALTER TABLE `generi` DISABLE KEYS */;
INSERT INTO `generi` VALUES (1,'giallo'),(2,'horror'),(3,'storico'),(4,'romanzo');
/*!40000 ALTER TABLE `generi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libri`
--

DROP TABLE IF EXISTS `libri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libri` (
  `codiceL` int NOT NULL AUTO_INCREMENT,
  `titolo` varchar(255) NOT NULL,
  `numPag` int NOT NULL,
  `anno` int NOT NULL,
  `codiceA` int NOT NULL,
  `codiceG` int DEFAULT NULL,
  `codiceE` int DEFAULT NULL,
  PRIMARY KEY (`codiceL`),
  KEY `fk_libri_autori` (`codiceA`),
  KEY `fk_libri_generi` (`codiceG`),
  KEY `fk_libri_editori` (`codiceE`),
  CONSTRAINT `fk_libri_autori` FOREIGN KEY (`codiceA`) REFERENCES `autori` (`codiceA`),
  CONSTRAINT `fk_libri_editori` FOREIGN KEY (`codiceE`) REFERENCES `editori` (`codiceE`),
  CONSTRAINT `fk_libri_generi` FOREIGN KEY (`codiceG`) REFERENCES `generi` (`codiceG`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libri`
--

LOCK TABLES `libri` WRITE;
/*!40000 ALTER TABLE `libri` DISABLE KEYS */;
INSERT INTO `libri` VALUES (1,'I promessi sposi',720,1827,1,4,1),(2,'Storia della colonna infame',200,1809,1,3,2),(3,'Guerra e pace',1440,1869,2,3,1),(4,'Anna Karenina',864,1877,2,4,2),(5,'Donne al potere',394,2000,3,4,1),(6,'La grande tempesta',270,2009,3,4,2),(7,'Misery',320,1987,4,4,1),(8,'IT',1138,1986,4,2,2),(9,'Shining',447,1977,4,2,1),(10,'Il Vecchio e il Mare',127,1952,5,4,2),(11,'Per chi suona la campana',480,1940,5,3,1),(12,'Fiesta',251,1926,5,4,2),(13,'Il nome della rosa',503,1980,6,3,1),(14,'Il pendolo di Foucault',671,1988,6,4,2),(15,'Và dove ti porta il cuore',127,1994,7,4,1),(16,'Gita al faro',209,1927,8,4,2),(17,'Orlando',336,1928,8,4,1),(18,'Assassinio sull\'Orient Express',274,1934,9,1,2),(19,'Sipario',272,1975,9,1,1);
/*!40000 ALTER TABLE `libri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'es_dao'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-18 10:08:35
