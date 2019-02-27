-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: ksca
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `agency`
--
DROP DATABASE ksca;
CREATE DATABASE ksca;
USE ksca;
DROP TABLE IF EXISTS `agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agency` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `area` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `manager` varchar(12) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `area` (`area`),
  CONSTRAINT `agency_ibfk_1` FOREIGN KEY (`area`) REFERENCES `area` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency`
--

LOCK TABLES `agency` WRITE;
/*!40000 ALTER TABLE `agency` DISABLE KEYS */;
INSERT INTO `agency` VALUES (1,'03-01','�߱�ü��ȸ',NULL,NULL),(2,'03-01','�߱����Ǽ�',NULL,NULL),(3,'03-01','�ǰ��������',NULL,NULL);
/*!40000 ALTER TABLE `agency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `code` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `city_code` varchar(5) NOT NULL,
  `gu` varchar(20) NOT NULL,
  `gu_code` varchar(5) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES ('01-01','����','01','�߱�','01'),('03-01','�뱸','03','�߱�','01');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `area_code` varchar(20) NOT NULL,
  `branch_code` varchar(10) NOT NULL,
  `branch` varchar(50) NOT NULL,
  PRIMARY KEY (`area_code`,`branch_code`),
  CONSTRAINT `branch_ibfk_1` FOREIGN KEY (`area_code`) REFERENCES `area` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES ('03-01','01','��굿'),('03-01','02','�޼���'),('03-01','03','���굿'),('03-01','04','���'),('03-01','99','����');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat1`
--

DROP TABLE IF EXISTS `cat1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat1` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat1`
--

LOCK TABLES `cat1` WRITE;
/*!40000 ALTER TABLE `cat1` DISABLE KEYS */;
INSERT INTO `cat1` VALUES ('3001','�ǰ���о�'),('3002','�ǰ������о�'),('3003','���������о�'),('3004','�����о�'),('3005','���������о�'),('3006','��ȸ�����о�'),('3007','�����۾���');
/*!40000 ALTER TABLE `cat1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat2`
--

DROP TABLE IF EXISTS `cat2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat2` (
  `code` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `cat1` varchar(10) NOT NULL,
  PRIMARY KEY (`code`,`cat1`),
  KEY `cat1` (`cat1`),
  CONSTRAINT `cat2_ibfk_1` FOREIGN KEY (`cat1`) REFERENCES `cat1` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat2`
--

LOCK TABLES `cat2` WRITE;
/*!40000 ALTER TABLE `cat2` DISABLE KEYS */;
INSERT INTO `cat2` VALUES ('101',' ü�� ��������','3001'),('101',' �ǰ���������','3002'),('101',' ����ȭ����','3003'),('101',' ����Ȱ��','3004'),('101',' ���μ�����','3005'),('101',' �湮 �̤��̿뼭��','3006'),('101',' �����۾���','3007'),('102',' ��������','3001'),('102',' �ѹ�ġ��','3002'),('102',' ����','3003'),('102',' �ٵϤ���ⱳ��','3004'),('102',' �Һ������ؿ��汳��','3005'),('102',' �ü� �湮 ��������','3006'),('103',' �䰡�����','3001'),('103',' �ȸ�����','3002'),('103',' �νİ�������','3003'),('103',' ����Ȱ��','3004'),('103',' �����ڻ���д뿹�汳��','3005'),('103',' �ڿ�����Ȱ��','3006'),('104',' �ǰ��','3001'),('104',' �湮��ȣ','3002'),('104',' ����������','3003'),('104',' �̼�Ȱ��','3004'),('105',' ���λ��','3003'),('105',' ��ȭ������Ȱ��','3004'),('106',' ��Ȱ����������','3003'),('107',' ���α׷���ǥ��ȸ','3003');
/*!40000 ALTER TABLE `cat2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(12) NOT NULL,
  `tel` varchar(30) NOT NULL,
  `mail` varchar(40) NOT NULL,
  `is_approve` tinyint(1) DEFAULT '0',
  `is_exist` tinyint(1) DEFAULT '1',
  `permission` enum('MASTER','MANAGER') DEFAULT 'MANAGER',
  `area` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `area` (`area`),
  CONSTRAINT `manager_ibfk_1` FOREIGN KEY (`area`) REFERENCES `area` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES ('luuzun','1234','�̿���','010-4940-5498','luuzun@naver.com',1,1,'MASTER','01-01'),('mcmoto','1234','�����','010-9004-0726','mcmoto@naver.com',1,1,'MANAGER','03-01');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `area_code` varchar(20) NOT NULL,
  `branch_code` varchar(10) NOT NULL,
  `scc_code` varchar(10) NOT NULL,
  `program` int(11) NOT NULL,
  `monthly_oper` int(11) NOT NULL,
  `active_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `area_code` (`area_code`,`branch_code`,`scc_code`),
  KEY `program` (`program`),
  CONSTRAINT `offer_ibfk_1` FOREIGN KEY (`area_code`, `branch_code`, `scc_code`) REFERENCES `scc` (`area_code`, `branch_code`, `scc_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `offer_ibfk_2` FOREIGN KEY (`program`) REFERENCES `offerprogram` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (1,'03-01','99','001',1,4,31),(2,'03-01','99','002',2,8,20),(3,'03-01','99','004',3,4,0);
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offerprogram`
--

DROP TABLE IF EXISTS `offerprogram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offerprogram` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `program` int(11) NOT NULL,
  `reg_month` date NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `color` varchar(20) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `program` (`program`),
  CONSTRAINT `offerprogram_ibfk_1` FOREIGN KEY (`program`) REFERENCES `program` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offerprogram`
--

LOCK TABLES `offerprogram` WRITE;
/*!40000 ALTER TABLE `offerprogram` DISABLE KEYS */;
INSERT INTO `offerprogram` VALUES (1,1,'2019-02-01','2019-02-01','2019-02-28','ED1C24'),(2,2,'2019-02-01','2019-02-01','2019-02-28','FF7F27'),(3,3,'2019-02-01','2019-02-01','2019-02-28','22B14C');
/*!40000 ALTER TABLE `offerprogram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `area` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `cat1` varchar(10) NOT NULL,
  `cat2` varchar(10) NOT NULL,
  `agency` int(11) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `agency` (`agency`),
  KEY `cat1` (`cat1`,`cat2`),
  KEY `area` (`area`),
  CONSTRAINT `program_ibfk_1` FOREIGN KEY (`agency`) REFERENCES `agency` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `program_ibfk_2` FOREIGN KEY (`cat1`, `cat2`) REFERENCES `cat2` (`cat1`, `code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `program_ibfk_3` FOREIGN KEY (`area`) REFERENCES `area` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES (1,'03-01','��Ʈ��Ī �ǰ�ü��','3001','101',1),(2,'03-01','���ʰ���ü������','3002','101',2),(3,'03-01','�鼼�ǰ������','3001','101',3);
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scc`
--

DROP TABLE IF EXISTS `scc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scc` (
  `area_code` varchar(20) NOT NULL,
  `branch_code` varchar(10) NOT NULL,
  `scc_code` varchar(10) NOT NULL,
  `dong` varchar(50) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `site` float DEFAULT NULL,
  `building` float DEFAULT NULL,
  `member` int(11) DEFAULT NULL,
  `male` int(11) DEFAULT NULL,
  `female` int(11) DEFAULT NULL,
  `own` varchar(10) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  `president` varchar(12) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`area_code`,`branch_code`,`scc_code`),
  CONSTRAINT `scc_ibfk_1` FOREIGN KEY (`area_code`, `branch_code`) REFERENCES `branch` (`area_code`, `branch_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scc`
--

LOCK TABLES `scc` WRITE;
/*!40000 ALTER TABLE `scc` DISABLE KEYS */;
INSERT INTO `scc` VALUES ('03-01','99','001','���굿','�ݿ�������׸��ھ�   ','��ä����� 679-13','1997-04-29',224.8,220.4,28,10,18,'����','053-421-5222','������','010-5135-4777'),('03-01','99','002','���굿','����׸���','���깮ȭ��1-43','1998-04-29',120.4,120.4,38,20,18,'�缳','053-421-5222','�ڼ���','010-5135-4777'),('03-01','99','003','���ε�','���ν�Ƽ','�޼���21�� 44-133','1999-04-29',350.2,345.4,48,30,18,'����','053-421-5222','�嵿��','010-5135-4777'),('03-01','99','004','���ε�','�ǵ����','�ޱ������ 1975','2000-04-29',100.8,98.4,58,40,18,'�缳','053-421-5222','�赿��','010-5135-4777'),('03-01','99','005','������','����1��','�߾Ӵ��62�� 30-3','2001-04-29',214.5,211.4,68,50,18,'����','053-421-5222','�̻���','010-5135-4777'),('03-01','99','006','�޼���','�޼�2��','�����6�� 5','2002-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','007','�޼���','�޼�1��','�޼��� 2246','2003-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','008','���굿','�ݿ�������׸��ھ�','��ä����� 679-13','1997-04-29',224.8,220.4,28,10,18,'����','053-421-5222','������','010-5135-4777'),('03-01','99','009','���굿','����Ȳ��','���깮ȭ��1-43','1998-04-29',120.4,120.4,38,20,18,'�缳','053-421-5222','�ڼ���','010-5135-4777'),('03-01','99','010','���ε�','�ص���ŸŬ����','�޼���21�� 44-133','1999-04-29',350.2,345.4,48,30,18,'����','053-421-5222','�嵿��','010-5135-4777'),('03-01','99','011','���ε�','����׸�Ÿ��','�ޱ������ 1975','2000-04-29',100.8,98.4,58,40,18,'�缳','053-421-5222','�赿��','010-5135-4777'),('03-01','99','012','������','ȿ���ظ���','�߾Ӵ��62�� 30-3','2001-04-29',214.5,211.4,68,50,18,'����','053-421-5222','�̻���','010-5135-4777'),('03-01','99','013','�޼���','����','�����6�� 5','2002-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','014','�޼���','�޼�1��','�޼��� 2246','2003-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','015','�޼���','�޼�1��','�޼��� 2246','2003-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','016','���굿','��ġ����Ʈ','��ä����� 679-13','1997-04-29',224.8,220.4,28,10,18,'����','053-421-5222','������','010-5135-4777'),('03-01','99','017','���굿','����׸���','���깮ȭ��1-43','1998-04-29',120.4,120.4,38,20,18,'�缳','053-421-5222','�ڼ���','010-5135-4777'),('03-01','99','018','���ε�','���ν�Ƽ','�޼���21�� 44-133','1999-04-29',350.2,345.4,48,30,18,'����','053-421-5222','�嵿��','010-5135-4777'),('03-01','99','019','���ε�','�ǵ����','�ޱ������ 1975','2000-04-29',100.8,98.4,58,40,18,'�缳','053-421-5222','�赿��','010-5135-4777'),('03-01','99','020','������','����1��','�߾Ӵ��62�� 30-3','2001-04-29',214.5,211.4,68,50,18,'����','053-421-5222','�̻���','010-5135-4777'),('03-01','99','021','�޼���','�޼�2��','�����6�� 5','2002-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','022','�޼���','�޼�1��','�޼��� 2246','2003-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','023','�޼���','�޼�1��','�޼��� 2246','2003-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','024','���굿','��ġ����Ʈ','��ä����� 679-13','1997-04-29',224.8,220.4,28,10,18,'����','053-421-5222','������','010-5135-4777'),('03-01','99','025','���굿','����׸���','���깮ȭ��1-43','1998-04-29',120.4,120.4,38,20,18,'�缳','053-421-5222','�ڼ���','010-5135-4777'),('03-01','99','026','���ε�','���ν�Ƽ','�޼���21�� 44-133','1999-04-29',350.2,345.4,48,30,18,'����','053-421-5222','�嵿��','010-5135-4777'),('03-01','99','027','���ε�','�ǵ����','�ޱ������ 1975','2000-04-29',100.8,98.4,58,40,18,'�缳','053-421-5222','�赿��','010-5135-4777'),('03-01','99','028','������','����1��','�߾Ӵ��62�� 30-3','2001-04-29',214.5,211.4,68,50,18,'����','053-421-5222','�̻���','010-5135-4777'),('03-01','99','029','�޼���','�޼�2��','�����6�� 5','2002-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','030','�޼���','�޼�1��','�޼��� 2246','2003-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','031','�޼���','�޼�1��','�޼��� 2246','2003-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','032','���굿','��ġ����Ʈ','��ä����� 679-13','1997-04-29',224.8,220.4,28,10,18,'����','053-421-5222','������','010-5135-4777'),('03-01','99','033','���굿','����׸���','���깮ȭ��1-43','1998-04-29',120.4,120.4,38,20,18,'�缳','053-421-5222','�ڼ���','010-5135-4777'),('03-01','99','034','���ε�','���ν�Ƽ','�޼���21�� 44-133','1999-04-29',350.2,345.4,48,30,18,'����','053-421-5222','�嵿��','010-5135-4777'),('03-01','99','035','���ε�','�ǵ����','�ޱ������ 1975','2000-04-29',100.8,98.4,58,40,18,'�缳','053-421-5222','�赿��','010-5135-4777'),('03-01','99','036','������','����1��','�߾Ӵ��62�� 30-3','2001-04-29',214.5,211.4,68,50,18,'����','053-421-5222','�̻���','010-5135-4777'),('03-01','99','037','�޼���','�޼�2��','�����6�� 5','2002-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777'),('03-01','99','038','�޼���','�޼�1��','�޼��� 2246','2003-04-29',124.1,100.4,78,60,18,'�缳','053-421-5222','������','010-5135-4777');
/*!40000 ALTER TABLE `scc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `offer` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`code`),
  KEY `offer` (`offer`),
  CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`offer`) REFERENCES `offer` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,1,'2019-02-01'),(2,1,'2019-02-08'),(3,1,'2019-02-15'),(4,1,'2019-02-22'),(5,2,'2019-02-01'),(6,2,'2019-02-08'),(7,2,'2019-02-15'),(8,2,'2019-02-22'),(9,2,'2019-02-04'),(10,2,'2019-02-11'),(11,2,'2019-02-18'),(12,2,'2019-02-25'),(13,3,'2019-02-06'),(14,3,'2019-02-13'),(15,3,'2019-02-20'),(16,3,'2019-02-27');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER inc_monthly_oper

AFTER INSERT ON schedule 

FOR EACH ROW 

BEGIN

	UPDATE offer 

		SET monthly_oper = monthly_oper + 1 

		WHERE code = NEW.offer;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER dec_monthly_oper

AFTER DELETE ON schedule 

FOR EACH ROW 

BEGIN

	UPDATE offer 

		SET monthly_oper = monthly_oper - 1 

		WHERE code = OLD.offer;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-27 14:34:30
