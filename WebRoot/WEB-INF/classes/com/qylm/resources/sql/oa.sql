/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.1.46-community : Database - ba
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`gcoa` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `gcoa`;

/*Table structure for table `entityclass` */

DROP TABLE IF EXISTS `entityclass`;

CREATE TABLE `entityclass` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `cls` varchar(200) DEFAULT NULL,
  `createrId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_entityclass_createrId` (`createrId`),
  CONSTRAINT `FK_entityclass_createrId` FOREIGN KEY (`createrId`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `entityclass` */



DROP TABLE IF EXISTS `filecontrol`;

CREATE TABLE `filecontrol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `originalName` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `currentName` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `folder` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `entityType` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `entityId` bigint(20) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `suffix` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `sizeLimit` bigint(20) DEFAULT NULL,
  `encryption` bit(1) DEFAULT NULL,
  `encryptionType` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `encryptionPass` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `createrId` bigint(20) DEFAULT NULL,
  `defaultState` tinyint(1) DEFAULT '0',
  `entityclassId` bigint(20) DEFAULT NULL,
  `objectId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_filecontrol_entityclass` (`entityType`),
  KEY `FK_filecontrol_createrId` (`createrId`),
  CONSTRAINT `FK_filecontrol_createrId` FOREIGN KEY (`createrId`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `filecontrol` */

insert  into `filecontrol`(`id`,`version`,`createDate`,`updateDate`,`originalName`,`currentName`,`folder`,`type`,`description`,`entityType`,`entityId`,`url`,`suffix`,`sizeLimit`,`encryption`,`encryptionType`,`encryptionPass`,`createrId`,`defaultState`,`entityclassId`,`objectId`) values (3,0,NULL,NULL,'18.jpg','com.qylm.entity.GeneralIntroduction_2_f07c36f3-f1f3-4a1a-9e14-62b7078a272d.jpg',NULL,'1',NULL,'generalIntroduction',2,'fileUpload\\GeneralIntroduction\\com.qylm.entity.GeneralIntroduction_2_f07c36f3-f1f3-4a1a-9e14-62b7078a272d.jpg','jpg',137324,'\0',NULL,NULL,NULL,0,NULL,NULL),(4,0,NULL,NULL,'5.jpg','com.qylm.entity.GeneralDeeds_1_42265dcf-85a5-42fd-bdb7-c8e6c66989b1.jpg',NULL,'1',NULL,NULL,1,'fileUpload\\GeneralDeeds\\com.qylm.entity.GeneralDeeds_1_42265dcf-85a5-42fd-bdb7-c8e6c66989b1.jpg','jpg',189556,'\0',NULL,NULL,NULL,0,NULL,NULL),(5,4,NULL,NULL,'5.jpg','com.qylm.entity.Brand_1_b4faae74-c1e1-490d-b617-91af5c626926.jpg',NULL,'1',NULL,'brand',1,'fileUpload\\Brand\\com.qylm.entity.Brand_1_b4faae74-c1e1-490d-b617-91af5c626926.jpg','jpg',189556,'\0',NULL,NULL,NULL,0,NULL,NULL),(6,3,'2015-04-05 11:34:47',NULL,'18.jpg','com.qylm.entity.Brand_1_90167986-d3d0-4b46-bcdb-f76e786b88cf.jpg',NULL,'1',NULL,'brand',1,'fileUpload\\Brand\\com.qylm.entity.Brand_1_90167986-d3d0-4b46-bcdb-f76e786b88cf.jpg','jpg',137324,'\0',NULL,NULL,NULL,1,NULL,NULL),(7,1,'2015-04-05 12:16:43',NULL,'6.jpg','com.qylm.entity.Brand_2_0cd31d89-af36-40a7-90b4-9bb16ad0394f.jpg',NULL,'1',NULL,'brand',2,'fileUpload\\Brand\\com.qylm.entity.Brand_2_0cd31d89-af36-40a7-90b4-9bb16ad0394f.jpg','jpg',156734,'\0',NULL,NULL,NULL,1,NULL,NULL),(13,0,'2015-04-05 12:46:13',NULL,'13.jpg','com.qylm.entity.Brand_2_df64d865-f4b3-462f-9fb1-596c09379115.jpg',NULL,'1',NULL,'brand',2,'fileUpload\\Brand\\com.qylm.entity.Brand_2_df64d865-f4b3-462f-9fb1-596c09379115.jpg','jpg',106023,'\0',NULL,NULL,NULL,0,NULL,NULL),(14,0,'2015-04-05 12:49:19',NULL,'1.jpg','com.qylm.entity.Brand_3_01f84581-2e55-4516-95f1-4946de4c4b6f.jpg',NULL,'1',NULL,'brand',3,'fileUpload\\Brand\\com.qylm.entity.Brand_3_01f84581-2e55-4516-95f1-4946de4c4b6f.jpg','jpg',161239,'\0',NULL,NULL,NULL,0,NULL,NULL),(15,0,'2015-04-05 19:07:11',NULL,'13.jpg','com.qylm.entity.Brand_3_9e681769-1347-4b38-bc34-a9b44a6bc63a.jpg',NULL,'1',NULL,'brand',3,'fileUpload\\Brand\\com.qylm.entity.Brand_3_9e681769-1347-4b38-bc34-a9b44a6bc63a.jpg','jpg',106023,'\0',NULL,NULL,NULL,0,NULL,NULL);

/*Table structure for table `function` */

DROP TABLE IF EXISTS `function`;

CREATE TABLE `function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `createrId` bigint(20) DEFAULT NULL,
  `treeItemId` bigint(20) DEFAULT NULL,
  `label` varchar(100) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `rank` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `function` */

insert  into `function`(`id`,`version`,`createDate`,`updateDate`,`createrId`,`treeItemId`,`label`,`enable`,`rank`) values (2,0,'2015-04-01 00:00:00',NULL,1,9,'添加',0,1),(3,0,'2015-04-01 00:00:00',NULL,1,9,'修改',0,2),(4,0,'2015-04-01 00:00:00',NULL,1,9,'删除',0,3),(5,0,'2015-04-01 00:00:00',NULL,1,2,'添加',0,1),(6,0,'2015-04-01 00:00:00',NULL,1,2,'删除',0,2),(7,0,'2015-04-01 00:00:00',NULL,1,3,'添加',0,1),(8,0,'2015-04-01 00:00:00',NULL,1,3,'删除',0,2),(9,0,'2015-04-01 00:00:00',NULL,1,12,'添加',0,1),(10,0,'2015-04-01 00:00:00',NULL,1,12,'修改',0,2),(11,0,'2015-04-01 00:00:00',NULL,1,12,'删除',0,3);



DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `createrId` bigint(20) DEFAULT NULL,
  `roleName` varchar(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_leaguer_role_createrId` (`createrId`),
  CONSTRAINT `FK_leaguer_role_createrId` FOREIGN KEY (`createrId`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`version`,`createDate`,`updateDate`,`createrId`,`roleName`,`description`) values (1,14,'2015-02-11 00:00:00','2015-03-18 00:00:00',1,'理发店用户','理发店管理员'),(2,13,'2015-02-12 00:00:00','2015-04-05 00:00:00',1,'系统管理员','管理整个系统');

/*Table structure for table `role_detail` */

DROP TABLE IF EXISTS `role_detail`;

CREATE TABLE `role_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `createrId` bigint(20) DEFAULT NULL,
  `treeItemId` bigint(20) DEFAULT NULL,
  `functionId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_leaguer_role_createrId` (`createrId`),
  KEY `FK_leaguer_roledetail_treeItemId` (`treeItemId`),
  KEY `FK_leaguer_roledetail_functionId` (`functionId`),
  KEY `FK_leaguer_roledetail_roleId` (`roleId`),
  CONSTRAINT `FK_leaguer_roledetail_createrId` FOREIGN KEY (`createrId`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_leaguer_roledetail_functionId` FOREIGN KEY (`functionId`) REFERENCES `function` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_leaguer_roledetail_roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_leaguer_roledetail_treeItemId` FOREIGN KEY (`treeItemId`) REFERENCES `treeitem` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=393 DEFAULT CHARSET=utf8;

/*Data for the table `role_detail` */

insert  into `role_detail`(`id`,`version`,`createDate`,`updateDate`,`createrId`,`treeItemId`,`functionId`,`roleId`) values (206,0,'2015-03-18 00:16:20',NULL,1,23,NULL,1),(207,0,'2015-03-18 00:16:20',NULL,1,24,NULL,1),(209,0,'2015-03-18 00:16:20',NULL,1,26,NULL,1),(347,0,'2015-04-05 12:59:56',NULL,1,1,NULL,2),(348,0,'2015-04-05 12:59:56',NULL,1,2,5,2),(349,0,'2015-04-05 12:59:56',NULL,1,2,6,2),(350,0,'2015-04-05 12:59:56',NULL,1,3,7,2),(351,0,'2015-04-05 12:59:56',NULL,1,3,8,2),(352,0,'2015-04-05 12:59:56',NULL,1,8,NULL,2),(353,0,'2015-04-05 12:59:56',NULL,1,9,2,2),(354,0,'2015-04-05 12:59:56',NULL,1,9,3,2),(355,0,'2015-04-05 12:59:56',NULL,1,9,4,2),(356,0,'2015-04-05 12:59:56',NULL,1,12,9,2),(357,0,'2015-04-05 12:59:56',NULL,1,12,10,2),(358,0,'2015-04-05 12:59:56',NULL,1,12,11,2),(359,0,'2015-04-05 12:59:56',NULL,1,52,NULL,2),(360,0,'2015-04-05 12:59:56',NULL,1,53,NULL,2),(361,0,'2015-04-05 12:59:56',NULL,1,54,NULL,2),(362,0,'2015-04-05 12:59:56',NULL,1,55,NULL,2),(363,0,'2015-04-05 12:59:56',NULL,1,56,NULL,2),(364,0,'2015-04-05 12:59:56',NULL,1,57,NULL,2),(365,0,'2015-04-05 12:59:56',NULL,1,58,NULL,2),(366,0,'2015-04-05 12:59:56',NULL,1,59,NULL,2),(367,0,'2015-04-05 12:59:56',NULL,1,60,NULL,2),(368,0,'2015-04-05 12:59:56',NULL,1,62,NULL,2),(369,0,'2015-04-05 12:59:56',NULL,1,65,NULL,2),(370,0,'2015-04-05 12:59:56',NULL,1,66,NULL,2),(371,0,'2015-04-05 12:59:56',NULL,1,67,NULL,2),(372,0,'2015-04-05 12:59:56',NULL,1,68,NULL,2),(373,0,'2015-04-05 12:59:56',NULL,1,69,NULL,2),(374,0,'2015-04-05 12:59:56',NULL,1,70,NULL,2),(375,0,'2015-04-05 12:59:56',NULL,1,71,NULL,2),(376,0,'2015-04-05 12:59:56',NULL,1,72,NULL,2),(377,0,'2015-04-05 12:59:56',NULL,1,73,NULL,2),(378,0,'2015-04-05 12:59:56',NULL,1,74,NULL,2),(379,0,'2015-04-05 12:59:56',NULL,1,75,NULL,2),(380,0,'2015-04-05 12:59:56',NULL,1,76,NULL,2),(381,0,'2015-04-05 12:59:56',NULL,1,77,NULL,2),(382,0,'2015-04-05 12:59:56',NULL,1,78,NULL,2),(383,0,'2015-04-05 12:59:56',NULL,1,79,NULL,2),(384,0,'2015-04-05 12:59:56',NULL,1,80,NULL,2),(385,0,'2015-04-05 12:59:56',NULL,1,81,NULL,2),(386,0,'2015-04-05 12:59:56',NULL,1,82,NULL,2),(387,0,'2015-04-05 12:59:56',NULL,1,83,NULL,2),(388,0,'2015-04-05 12:59:56',NULL,1,84,NULL,2),(389,0,'2015-04-05 12:59:56',NULL,1,85,NULL,2),(390,0,'2015-04-05 12:59:56',NULL,1,86,NULL,2),(391,0,'2015-04-05 12:59:56',NULL,1,87,NULL,2),(392,0,'2015-04-05 12:59:56',NULL,1,88,NULL,2);

/*Table structure for table `treeitem` */

DROP TABLE IF EXISTS `treeitem`;

CREATE TABLE `treeitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `createrId` bigint(20) DEFAULT NULL,
  `nodeId` varchar(200) DEFAULT NULL,
  `displayChildRen` tinyint(1) DEFAULT NULL,
  `label` varchar(50) DEFAULT NULL,
  `action` varchar(200) DEFAULT NULL,
  `target` varchar(50) DEFAULT NULL,
  `sequence` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nodeId` (`nodeId`),
  KEY `FK_system_treeitem_createrId` (`createrId`),
  CONSTRAINT `FK_system_treeitem_createrId` FOREIGN KEY (`createrId`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

/*Data for the table `treeitem` */

insert  into `treeitem`(`id`,`version`,`createDate`,`updateDate`,`createrId`,`nodeId`,`displayChildRen`,`label`,`action`,`target`,`sequence`) values (1,2,NULL,NULL,NULL,'system',0,'系统管理',NULL,'',99),(2,2,NULL,NULL,NULL,'system_treeItem',0,'模块管理','#{treeItemManageBean.getAll}','mainFrame',1),(3,3,NULL,NULL,1,'system_function',0,'功能管理','#{functionManageBean.getAll}','mainFrame',2),(8,3,'2014-10-01 00:00:00',NULL,1,'company',0,'用户管理',NULL,'',98),(9,3,'2014-10-01 00:00:00',NULL,1,'company_user',0,'用户管理','#{userManageBean.getAll}','mainFrame',1),(12,3,'2014-10-01 00:00:00',NULL,1,'company_role',0,'角色管理','#{roleManageBean.getAll}','mainFrame',2),(23,12,'2015-02-01 00:00:00',NULL,1,'forum_forumInfo',0,'论坛信息管理','#{forumInfoManageBean.getAll}','mainFrame',1),(24,0,'2015-02-01 00:00:00',NULL,1,'forum_forumStatistics',0,'论坛信息统计','#{forumStatisticsManageBean.getAll}','mainFrame',2),(26,0,'2015-02-01 00:00:00',NULL,1,'messageInfo_messageInfo',0,'留言信息管理','#{messageInfoManageBean.getAll}','mainFrame',1),(28,0,'2015-02-01 00:00:00',NULL,1,'general_generalIntroduction',0,'简介发布','#{generalIntroductionManageBean.getAll}','mainFrame',1),(29,0,'2015-02-01 00:00:00',NULL,1,'general_generalDeeds',0,'将军事迹发布','#{generalDeedsManageBean.getAll}','mainFrame',2),(30,0,'2015-02-01 00:00:00',NULL,1,'general_generalGathering',0,'将军雅集发布','#{generalGatheringManageBean.getAll}','mainFrame',3),(31,0,'2015-02-01 00:00:00',NULL,1,'general_generalInscription',0,'将军题词发布','#{generalInscriptionManageBean.getAll}','mainFrame',4),(33,0,'2015-02-01 00:00:00',NULL,1,'fuhuGeneral_fuhuIntroduction',0,'简介发布','#{fuhuIntroductionManageBean.getAll}','mainFrame',1),(34,0,'2015-02-01 00:00:00',NULL,1,'fuhuGeneral_fuhuArtLife',0,'艺术人生发布','#{fuhuArtLifeManageBean.getAll}','mainFrame',2),(35,0,'2015-02-01 00:00:00',NULL,1,'fuhuGeneral_fuhuGroupPhoto',0,'将军合影发布','#{fuhuGroupPhotoManageBean.getAll}','mainFrame',3),(36,0,'2015-02-01 00:00:00',NULL,1,'fuhuGeneral_fuhuPersonalWorks',0,'个人作品发布','#{fuhuPersonalWorksManageBean.getAll}','mainFrame',4),(38,1,'2015-02-01 00:00:00',NULL,1,'famous_famousInfo',0,'名家信息管理','#{famousInfoManageBean.getAll}','mainFrame',1),(39,0,'2015-02-01 00:00:00',NULL,1,'famous_famousWorks',0,'名家作品发布','#{famousWorksManageBean.getAll}','mainFrame',2),(40,0,'2015-02-01 00:00:00',NULL,1,'famous_famousMessage',0,'名家寄语发布','#{famousMessageManageBean.getAll}','mainFrame',3),(42,0,'2015-02-01 00:00:00',NULL,1,'synthetical_artExhibitionInfo',0,'画展资讯信息','#{artExhibitionInfoManageBean.getAll}','mainFrame',1),(43,0,'2015-02-01 00:00:00',NULL,1,'synthetical_mediaInfo',0,'媒体资讯信息','#{mediaInfoManageBean.getAll}','mainFrame',2),(44,0,'2015-02-01 00:00:00',NULL,1,'synthetical_industryNewsInfo',0,'行业新闻信息','#{industryNewsInfoManageBean.getAll}','mainFrame',3),(45,0,'2015-02-01 00:00:00',NULL,1,'synthetical_exhibitionInfo',0,'展会信息','#{exhibitionInfoManageBean.getAll}','mainFrame',4),(46,0,'2015-02-01 00:00:00',NULL,1,'synthetical_auctionInfo',0,'拍卖信息','#{auctionInfoManageBean.getAll}','mainFrame',5),(48,0,'2015-02-01 00:00:00',NULL,1,'fileSecure_fileUp',0,'文件上传','#{fileUpManageBean.getAll}','mainFrame',1),(49,0,'2015-02-01 00:00:00',NULL,1,'fileSecure_fileEncryption',0,'文件加密处理','#{fileEncryptionManageBean.getAll}','mainFrame',2),(51,0,'2015-02-01 00:00:00',NULL,1,'member_member',0,'会员信息管理','#{memberManageBean.getAll}','mainFrame',1),(52,0,'2015-02-01 00:00:00',NULL,1,'basicData',0,'基础数据管理',NULL,'',97),(53,0,'2015-02-01 00:00:00',NULL,1,'basicData_dictionary',0,'数据字典','#{dictionaryManageBean.getAll}','mainFrame',1),(54,1,'2015-04-01 00:00:00',NULL,1,'order',0,'订单管理',NULL,'',10),(55,4,'2015-04-01 00:00:00',NULL,1,'order_orderMasters',0,'订单列表','#{orderMasterManageBean.getAll}','mainFrame',1),(56,0,'2015-04-01 00:00:00',NULL,1,'order_orderMasterShip',0,'批量发货','#{orderMasterShipManageBean.getAll}','mainFrame',2),(57,0,'2015-04-01 00:00:00',NULL,1,'order_orderMasterPrint',0,'订单打印','#{orderMasterPrintManageBean.getAll}','mainFrame',3),(58,0,'2015-04-01 00:00:00',NULL,1,'order_orderMasterRetreat',0,'售后管理','#{orderMasterRetreatManageBean.getAll}','mainFrame',4),(59,1,'2015-04-01 00:00:00',NULL,1,'depot',0,'仓库管理',NULL,'',20),(60,1,'2015-04-01 00:00:00',NULL,1,'depot_depotInfo',0,'仓库管理','#{depotManageBean.getAll}','mainFrame',1),(62,0,'2015-04-01 00:00:00',NULL,1,'depot_depotImport',0,'仓库导入','#{depotImportManageBean.getAll}','mainFrame',3),(65,1,'2015-04-01 00:00:00',NULL,1,'express',0,'快递管理',NULL,'',30),(66,0,'2015-04-01 00:00:00',NULL,1,'express_express',0,'快递管理','#{expressManageBean.getAll}','mainFrame',1),(67,0,'2015-04-01 00:00:00',NULL,1,'brand',0,'品牌管理',NULL,'',40),(68,0,'2015-04-01 00:00:00',NULL,1,'brand_brand',0,'品牌管理','#{brandManageBean.getAll}','mainFrame',1),(69,0,'2015-04-01 00:00:00',NULL,1,'product',0,'商品管理',NULL,'',15),(70,0,'2015-04-01 00:00:00',NULL,1,'product_product',0,'商品库存管理','#{productManageBean.select}','mainFrame',1),(71,0,'2015-04-01 00:00:00',NULL,1,'agent',0,'代理信息管理',NULL,'',50),(72,0,'2015-04-01 00:00:00',NULL,1,'agent_agentUser',0,'代理用户管理','#{agentUserManageBean.getAll}','mainFrame',1),(73,0,'2015-04-01 00:00:00',NULL,1,'agent_agentLevel',0,'代理级别管理','#{agentLevelManageBean.getAll}','mainFrame',2),(74,0,'2015-04-01 00:00:00',NULL,1,'finance',0,'财务管理',NULL,'',60),(75,0,'2015-04-01 00:00:00',NULL,1,'finance_financeStatistics',0,'财务统计','#{financeStatisticsManageBean.select}','mainFrame',1),(76,0,'2015-04-01 00:00:00',NULL,1,'purchase',0,'采购管理',NULL,'',70),(77,1,'2015-04-01 00:00:00',NULL,1,'purchase_purchaseApp',0,'采购单申请','#{purchaseManageBean.getAll}','mainFrame',1),(78,0,'2015-04-01 00:00:00',NULL,1,'purchase_purchaseExamine',0,'采购单审核','#{purchaseExamineManageBean.getAll}','mainFrame',2),(79,0,'2015-04-01 00:00:00',NULL,1,'purchase_procedureSet',0,'采购单审核设定','#{procedureSetManageBean.getAll}','mainFrame',3),(80,0,'2015-04-01 00:00:00',NULL,1,'purchase_purchaseBuy',0,'采购单采购','#{purchaseBuyManageBean.getAll}','mainFrame',4),(81,0,'2015-04-01 00:00:00',NULL,1,'assistant',0,'办公助手',NULL,'',1),(82,0,'2015-04-01 00:00:00',NULL,1,'assistant_bulletin',0,'公告管理','#{bulletinManageBean.getAll}','mainFrame',1),(83,0,'2015-04-01 00:00:00',NULL,1,'assistant_qqService',0,'QQ服务管理','#{qqServiceManageBean.getAll}','mainFrame',2),(84,0,'2015-04-01 00:00:00',NULL,1,'depot_purchaseStore',0,'入库管理','#{purchaseStoreManageBean.getAll}','mainFrame',3),(85,0,'2015-04-01 00:00:00',NULL,1,'depot_storage',0,'出库管理','#{storageManageBean.getAll}','mainFrame',4),(86,0,'2015-04-01 00:00:00',NULL,1,'depot_stocktaking',0,'库存盘点','#{stocktakingManageBean.getAll}','mainFrame',5),(87,0,'2015-04-01 00:00:00',NULL,1,'transforSize',0,'尺码管理',NULL,'',96),(88,0,'2015-04-01 00:00:00',NULL,1,'transforSize_transforSize',0,'尺码转化','#{transforSizeManageBean.select}','mainFrame',1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `loginName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `stop` tinyint(1) NOT NULL,
  `userTel` varchar(255) DEFAULT NULL,
  `userMobile1` varchar(255) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `createrId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK_user_loginName` (`loginName`),
  KEY `FK_leaguer_user_createrId` (`createrId`),
  CONSTRAINT `FK_leaguer_user_createrId` FOREIGN KEY (`createrId`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`version`,`userName`,`loginName`,`password`,`stop`,`userTel`,`userMobile1`,`lastLoginTime`,`loginTime`,`createDate`,`updateDate`,`createrId`) values (1,18,'系统管理员','sanli','11',0,'89898989898','23423423423',NULL,NULL,NULL,'2015-04-04 00:00:00',NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `createrId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_leaguer_userrole_createrId` (`createrId`),
  KEY `FK_leaguer_userrole_roleId` (`roleId`),
  KEY `FK_leaguer_userrole_userId` (`userId`),
  CONSTRAINT `FK_leaguer_userrole_createrId` FOREIGN KEY (`createrId`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_leaguer_userrole_roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_leaguer_userrole_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`version`,`createDate`,`updateDate`,`createrId`,`userId`,`roleId`) values (14,0,'2015-04-04 20:14:00',NULL,1,1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
