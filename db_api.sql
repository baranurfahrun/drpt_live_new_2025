-- MariaDB dump 10.19  Distrib 10.4.19-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: db_api
-- ------------------------------------------------------
-- Server version	10.4.19-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `akun_tte`
--

DROP TABLE IF EXISTS `akun_tte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `akun_tte` (
  `nik` varchar(20) NOT NULL,
  `sign_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nik`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `akun_tte`
--

LOCK TABLES `akun_tte` WRITE;
/*!40000 ALTER TABLE `akun_tte` DISABLE KEYS */;
INSERT INTO `akun_tte` VALUES ('0803202100007062','cobaTTE.png');
/*!40000 ALTER TABLE `akun_tte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_berkas_tte`
--

DROP TABLE IF EXISTS `log_berkas_tte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_berkas_tte` (
  `no_ktp` varchar(16) DEFAULT NULL,
  `tanggal` datetime DEFAULT NULL,
  `no_rawat` varchar(255) DEFAULT NULL,
  `kode_berkas` varchar(255) DEFAULT NULL,
  `nama_berkas` varchar(255) DEFAULT NULL,
  `lokasi_file` text DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `status_code` varchar(255) DEFAULT NULL,
  `applications` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_berkas_tte`
--

LOCK TABLES `log_berkas_tte` WRITE;
/*!40000 ALTER TABLE `log_berkas_tte` DISABLE KEYS */;
INSERT INTO `log_berkas_tte` VALUES ('0803202100007062','2024-02-11 18:31:46',NULL,NULL,'2023_11_19_000001.pdf','2023_11_19_000001.pdf','Sukses','200',NULL),('0803202100007062','2024-02-11 18:32:20',NULL,NULL,'2023_11_19_000001.pdf','2023_11_19_000001.pdf','Sukses','200',NULL);
/*!40000 ALTER TABLE `log_berkas_tte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tm_bridgeuser`
--

DROP TABLE IF EXISTS `tm_bridgeuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tm_bridgeuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `token` text DEFAULT NULL,
  `create` datetime DEFAULT NULL,
  `last_access` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `iss` varchar(255) DEFAULT NULL,
  `aud` varchar(255) DEFAULT NULL,
  `exp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tm_bridgeuser`
--

LOCK TABLES `tm_bridgeuser` WRITE;
/*!40000 ALTER TABLE `tm_bridgeuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `tm_bridgeuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tt_logaccess`
--

DROP TABLE IF EXISTS `tt_logaccess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tt_logaccess` (
  `user` varchar(255) DEFAULT NULL,
  `log` text DEFAULT NULL,
  `ip` text DEFAULT NULL,
  `datetime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tt_logaccess`
--

LOCK TABLES `tt_logaccess` WRITE;
/*!40000 ALTER TABLE `tt_logaccess` DISABLE KEYS */;
/*!40000 ALTER TABLE `tt_logaccess` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-11 18:36:15
