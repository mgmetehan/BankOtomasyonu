-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: db_bank
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `tblkisiler`
--

DROP TABLE IF EXISTS `tblkisiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblkisiler` (
  `KisiId` tinyint NOT NULL AUTO_INCREMENT,
  `KisiAd` varchar(20) DEFAULT NULL,
  `KisiSoyad` varchar(20) DEFAULT NULL,
  `KisiTc` varchar(11) DEFAULT NULL,
  `KisiIl` varchar(13) DEFAULT NULL,
  `KisiAnneSoyad` varchar(20) DEFAULT NULL,
  `KisiPara` decimal(18,2) DEFAULT NULL,
  `KisiBorc` decimal(18,2) DEFAULT NULL,
  `KisiTelefon` varchar(10) DEFAULT NULL,
  `KisiSifre` varchar(6) DEFAULT NULL,
  `KisiCinsiyet` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`KisiId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblkisiler`
--

LOCK TABLES `tblkisiler` WRITE;
/*!40000 ALTER TABLE `tblkisiler` DISABLE KEYS */;
INSERT INTO `tblkisiler` VALUES (1,'Metehan','Gültekin','11111111111','Ankara','Dona',31500.00,2000.00,'5315558020','111111','Erkek'),(2,'Buse','Attık','98745698563','Bursa','Ateş',10000.00,2000.00,'5458907652','134687','Kadın'),(3,'Hilal','Tanyeri','14569845897','Muş','Yurt',3500.00,500.00,'5678970934','576983','Kadın'),(4,'Emre','Güven','63445457894','Adana','Pala',7000.00,4000.00,'5550989776','093422','Erkek'),(6,'Burak','Özcan','46578453428','Maraş','Anime',8743.50,0.00,'5324897886','678793','Erkek'),(7,'Fuat','Abi','34895232359','Istanbul','Yangın',65708.50,0.00,'5429575849','893420','Erkek'),(8,'Fadime','Hamsi','45363457468','Trabzon','Fındık',10000.00,6800.00,'5628592385','738702','Kadın'),(10,'Mert','Uzun','34565685742','Ordu','Çay',5790.00,12000.00,'5610348925','763892','Erkek'),(11,'Murat','Akbablar','78347832531','Ankara','Goril',30.00,1100.00,'5347859340','327905','Erkek'),(12,'Walter','White','24453890509','Los Angeles','Heisenberg',100000.00,0.00,'5314535698','590781','Erkek'),(13,'Saul','Goodman','52349823580','Cicero','Mcgull',750000.00,0.00,'5559398523','803672','Erkek'),(14,'Margot','Robbie','10723674698','Dalby','Ritter',500000.00,245089.00,'5413507849','436891','Kadın'),(16,'Jesse ','Pinkman','63493120480','Idaho','Aaron',2000.00,40500.00,'5392587235','123456','Erkek');
/*!40000 ALTER TABLE `tblkisiler` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-02 22:14:44
