CREATE DATABASE  IF NOT EXISTS `stok_takip` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `stok_takip`;
-- MySQL dump 10.13  Distrib 5.5.50, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: stok_takip
-- ------------------------------------------------------
-- Server version	5.5.50-0ubuntu0.14.04.1

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
-- Table structure for table `ürünler`
--

DROP TABLE IF EXISTS `ürünler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ürünler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ürün_adi` varchar(25) NOT NULL,
  `alis_fiyati` int(11) NOT NULL,
  `satis_fiyati` int(11) NOT NULL,
  `ürün_grubu` varchar(25) NOT NULL,
  `stok_miktari` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ürün_adi` (`ürün_adi`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ürünler`
--

LOCK TABLES `ürünler` WRITE;
/*!40000 ALTER TABLE `ürünler` DISABLE KEYS */;
INSERT INTO `ürünler` VALUES (5,'tablet',10,20,'elektronik	',150),(7,'gözlük',10,20,'gözlük',9),(8,'bilgisayar',100,200,'elektronik',500);
/*!40000 ALTER TABLE `ürünler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanici_bilgileri`
--

DROP TABLE IF EXISTS `kullanici_bilgileri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kullanici_bilgileri` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kullanici_adi` varchar(20) NOT NULL,
  `kullanici_sifresi` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `kullanici_adi` (`kullanici_adi`),
  UNIQUE KEY `kullanici_sifresi` (`kullanici_sifresi`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanici_bilgileri`
--

LOCK TABLES `kullanici_bilgileri` WRITE;
/*!40000 ALTER TABLE `kullanici_bilgileri` DISABLE KEYS */;
INSERT INTO `kullanici_bilgileri` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `kullanici_bilgileri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main`
--

DROP TABLE IF EXISTS `main`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kullanici_bilgileri` int(11) DEFAULT NULL,
  `ürün_bilgileri` int(11) DEFAULT NULL,
  `satis_bilgileri` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_kullanici` (`kullanici_bilgileri`),
  KEY `fk_ürün` (`ürün_bilgileri`),
  KEY `fk_satis` (`satis_bilgileri`),
  CONSTRAINT `fk_ürün` FOREIGN KEY (`ürün_bilgileri`) REFERENCES `ürünler` (`id`),
  CONSTRAINT `fk_kullanici` FOREIGN KEY (`kullanici_bilgileri`) REFERENCES `kullanici_bilgileri` (`id`),
  CONSTRAINT `fk_satis` FOREIGN KEY (`satis_bilgileri`) REFERENCES `satislar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main`
--

LOCK TABLES `main` WRITE;
/*!40000 ALTER TABLE `main` DISABLE KEYS */;
/*!40000 ALTER TABLE `main` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `satislar`
--

DROP TABLE IF EXISTS `satislar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `satislar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `satilan_ürün` varchar(25) NOT NULL,
  `satis_miktari` int(11) NOT NULL,
  `satis_tipi` varchar(15) NOT NULL,
  `satis_tarihi` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `satislar`
--

LOCK TABLES `satislar` WRITE;
/*!40000 ALTER TABLE `satislar` DISABLE KEYS */;
INSERT INTO `satislar` VALUES (3,'bilgisayar',1,'Nakit','10/08/2016'),(4,'bilgisayar',45,'Nakit','10/08/2016'),(5,'tablet',22,'Nakit','11/08/2016'),(6,'bilgisayar',5,'Nakit','11/08/2016'),(7,'bilgisayar',1,'Nakit','11/08/2016'),(8,'bilgisayar',2,'Kredi Kart?','11/08/2016'),(10,'bilgisayar',3,'Nakit','12/08/2016'),(11,'bilgisayar',2,'Nakit','12/08/2016'),(12,'pil',2,'Nakit','12/08/2016'),(13,'tablet',11,'Nakit','12/08/2016'),(14,'pil',1,'Nakit','12/08/2016'),(15,'gözlük',1,'Kredi Kart?','12/08/2016'),(16,'pil',1,'Nakit','12/08/2016'),(17,'tablet',1,'Nakit','12/08/2016'),(18,'tablet',1,'Kredi Kart?','12/08/2016');
/*!40000 ALTER TABLE `satislar` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-12 17:39:39
