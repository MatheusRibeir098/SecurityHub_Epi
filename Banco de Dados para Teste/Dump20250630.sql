-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: controle_epi
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `devolucao`
--

DROP TABLE IF EXISTS `devolucao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devolucao` (
  `id_devolucao` int(11) NOT NULL AUTO_INCREMENT,
  `id_emprestimo` int(11) NOT NULL,
  `data_devolucao` datetime NOT NULL,
  PRIMARY KEY (`id_devolucao`),
  KEY `id_emprestimo` (`id_emprestimo`),
  CONSTRAINT `devolucao_ibfk_1` FOREIGN KEY (`id_emprestimo`) REFERENCES `emprestimo` (`id_emprestimo`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucao`
--

LOCK TABLES `devolucao` WRITE;
/*!40000 ALTER TABLE `devolucao` DISABLE KEYS */;
INSERT INTO `devolucao` VALUES (1,5,'2025-05-12 22:02:03'),(2,6,'2025-05-12 23:02:07'),(3,7,'2025-05-12 23:11:43'),(4,9,'2025-05-13 19:03:28'),(6,13,'2025-06-30 21:34:47'),(7,15,'2025-06-30 21:35:26'),(8,17,'2025-06-30 21:35:48'),(9,14,'2025-06-30 21:35:48'),(10,16,'2025-06-30 21:35:49'),(11,18,'2025-06-30 21:42:13'),(12,19,'2025-06-30 21:42:14'),(13,20,'2025-06-30 21:42:14');
/*!40000 ALTER TABLE `devolucao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emprestimo` (
  `id_emprestimo` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_epi` int(11) NOT NULL,
  `data_retirada` datetime NOT NULL,
  `data_prevista_devolucao` datetime NOT NULL,
  `confirmacao_retirada` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_emprestimo`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_epi` (`id_epi`),
  CONSTRAINT `emprestimo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `emprestimo_ibfk_2` FOREIGN KEY (`id_epi`) REFERENCES `epi` (`id_epi`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprestimo`
--

LOCK TABLES `emprestimo` WRITE;
/*!40000 ALTER TABLE `emprestimo` DISABLE KEYS */;
INSERT INTO `emprestimo` VALUES (3,1,3,'2025-05-04 03:02:02','2025-05-04 03:02:02',1),(5,2,3,'2025-05-12 21:59:23','2025-07-06 13:00:00',0),(6,2,4,'2025-05-12 23:01:46','2025-07-06 13:00:00',0),(7,2,4,'2025-05-12 23:11:31','2025-04-06 13:00:00',0),(8,2,5,'2025-05-13 18:59:13','2025-06-01 00:00:00',0),(9,3,5,'2025-05-13 19:00:27','2025-05-04 00:00:00',0),(10,4,6,'2025-05-15 19:15:53','2025-05-01 00:00:00',0),(11,6,7,'2025-05-15 19:32:55','2025-07-01 00:00:00',0),(13,11,4,'2025-06-30 21:18:33','2025-07-30 21:18:33',0),(14,11,9,'2025-06-30 21:35:14','2025-07-30 21:35:14',0),(15,11,4,'2025-06-30 21:35:15','2025-07-30 21:35:15',0),(16,11,8,'2025-06-30 21:35:33','2025-07-30 21:35:33',0),(17,11,4,'2025-06-30 21:35:34','2025-07-30 21:35:34',0),(18,11,3,'2025-06-30 21:41:50','2025-07-30 21:41:50',0),(19,11,3,'2025-06-30 21:41:52','2025-07-30 21:41:52',0),(20,11,3,'2025-06-30 21:41:53','2025-07-30 21:41:53',0);
/*!40000 ALTER TABLE `emprestimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `epi`
--

DROP TABLE IF EXISTS `epi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `epi` (
  `id_epi` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_epi`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `epi`
--

LOCK TABLES `epi` WRITE;
/*!40000 ALTER TABLE `epi` DISABLE KEYS */;
INSERT INTO `epi` VALUES (3,'capacete',20,NULL),(4,'Luva',12,NULL),(5,'Dilma 112',20,NULL),(6,'biqueira de aço',9,NULL),(7,'luva',8,NULL),(8,'Banner',123,NULL),(9,'Eduardo',60,NULL);
/*!40000 ALTER TABLE `epi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `perfil` enum('ADMIN','USUARIO') NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Matheus de Paula Ribeiro','matheus@gmail.com','123','USUARIO'),(2,'Matheus2','matheus2@','123','USUARIO'),(3,'batore','batore@','123','USUARIO'),(4,'leaoXIV','leao@','123','USUARIO'),(6,'maguila','maguila@','123','USUARIO'),(7,'Matheus Admin','matheus3@','$2a$10$m6aZJPL251vc.jpcVCkey.e7toaqyrHWUpTQHWSf7oQCVoHCkNHmu','ADMIN'),(9,'Paulo Ricardo','ricardo@gmail.com','$2a$10$Pt193vLFmXWUaEmZTOd.u.M5X1.m6HIE3KyqLBelR5hqJTqQDg/Yu','ADMIN'),(11,'Matheus Usuario','matheus123123@gmail.com','$2a$10$H4abi5kP0I6VwpjomZc/BeOM3KSQvO7LAIZ2S/IOV4eu.UQ0yy2ye','USUARIO');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'controle_epi'
--

--
-- Dumping routines for database 'controle_epi'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-30 21:47:48
