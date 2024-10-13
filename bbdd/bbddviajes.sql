-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: viajes
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `tarjeta` varchar(45) DEFAULT NULL,
  `dni` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('client1','client1','C/ Asturias','112233223','1234B'),('client2','client2','c/ Marques','5674455','321T');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoteles`
--

DROP TABLE IF EXISTS `hoteles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoteles` (
  `idHotel` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `categoria` int unsigned NOT NULL,
  `precio` double NOT NULL,
  `disponible` tinyint unsigned NOT NULL,
  `localizacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idHotel`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoteles`
--

LOCK TABLES `hoteles` WRITE;
/*!40000 ALTER TABLE `hoteles` DISABLE KEYS */;
INSERT INTO `hoteles` VALUES (1,'Norte',3,120,1,'Málaga'),(2,'Ritz',5,400,1,'Paris'),(3,'Estrella sur',4,190,0,'Málaga'),(4,'Meridian',4,210,1,'Estocolmo'),(5,'Sol',4,180,1,'Madrid'),(6,'Emperator',4,160,1,'Madrid'),(7,'Candy',3,110,1,'Málaga'),(8,'Irish',5,210,1,'Estocolmo'),(9,'PanAir',4,170,1,'Londres'),(10,'Mundial',4,150,1,'Buenos Aires'),(11,'Elenco',3,100,1,'Buenos Aires'),(12,'Marfil',4,150,1,'Málaga'),(13,'Renoir',4,180,1,'Paris'),(14,'Layos',4,130,1,'Buenos Aires');
/*!40000 ALTER TABLE `hoteles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `idreserva` int unsigned NOT NULL AUTO_INCREMENT,
  `hotel` int unsigned NOT NULL,
  `vuelo` int unsigned NOT NULL,
  `precio` double DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idreserva`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (43,9,5,160,'client1'),(44,9,5,160,'client1'),(45,9,5,160,'client1'),(46,9,5,160,'client1'),(47,5,6,200,'client1'),(48,10,4,230,'client1'),(49,10,4,230,'client1'),(50,10,4,230,'client1'),(51,9,5,160,'client1'),(52,9,5,320,'client1'),(53,9,5,320,'client1'),(54,9,5,320,'client1');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuelos`
--

DROP TABLE IF EXISTS `vuelos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vuelos` (
  `idvuelo` int unsigned NOT NULL AUTO_INCREMENT,
  `company` varchar(45) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  `precio` double NOT NULL,
  `plazas` int unsigned NOT NULL,
  `destino` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idvuelo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelos`
--

LOCK TABLES `vuelos` WRITE;
/*!40000 ALTER TABLE `vuelos` DISABLE KEYS */;
INSERT INTO `vuelos` VALUES (1,'Iberia','10-12-2025',200,10,'Málaga'),(2,'Air Europa','11-12-2027',170,2,'Paris'),(3,'Air Lingus','29-04-2024',160,7,'Londres'),(4,'Iberia','11-08-2023',230,8,'Buenos Aires'),(5,'Airways','04-11-2024',160,7,'Londres'),(6,'Iberia','10-03-2024',200,14,'Madrid'),(7,'Air Europa','22-09-2023',120,4,'Málaga'),(8,'Panam','14-04-2022',230,6,'Estocolmo'),(9,'Air Lingus','30-05-2024',130,20,'Madrid'),(10,'Iberia','16-06-2024',80,5,'Málaga'),(11,'Panam','01-02-2026',200,10,'Londres'),(12,'Air France','13-08-2022',130,4,'Paris'),(13,'Air Europa','17-02-2023',140,14,'Paris'),(14,'Iberia','25-07-2024',300,10,'Buenos Aires'),(15,'Air Lingus','04-04-2025',170,20,'Málaga'),(16,'Air France','09-11-2024',150,8,'Madrid'),(17,'Airways','16-12-2024',120,6,'Londres'),(18,'Panam','21-12-2024',210,25,'Buenos Aires'),(19,'Airways','20-03-2022',170,11,'Estocolmo');
/*!40000 ALTER TABLE `vuelos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-04 23:04:51
