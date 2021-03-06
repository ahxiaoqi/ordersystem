/*
SQLyog Ultimate
MySQL - 8.0.15 : Database - ordersystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ordersystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `ordersystem`;

/*Table structure for table `t_account` */

DROP TABLE IF EXISTS `t_account`;

CREATE TABLE `t_account` (
  `accountId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userName` varchar(10) NOT NULL DEFAULT '' COMMENT '用户名',
  `passWord` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `nickName` varchar(10) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `accountType` int(10) NOT NULL DEFAULT '3' COMMENT '用户类型1管理员 2 商家 3 普通用户',
  `headImg` varchar(100) NOT NULL DEFAULT '' COMMENT '头像',
  PRIMARY KEY (`accountId`)
)  ;

/*Data for the table `t_account` */

insert  into `t_account`(`accountId`,`userName`,`passWord`,`nickName`,`createTime`,`accountType`,`headImg`) values 
(1,'admin','8df531cff0113e78f0ed08650c781f76','','2019-12-29 01:32:12',1,''),
(2,'user1','8df531cff0113e78f0ed08650c781f76','','2019-12-29 01:33:46',3,''),
(3,'user2','8df531cff0113e78f0ed08650c781f76','','2020-01-21 09:41:37',3,''),
(9,'user3','8df531cff0113e78f0ed08650c781f76','','2020-03-05 22:56:54',3,'');

/*Table structure for table `t_activity` */

DROP TABLE IF EXISTS `t_activity`;

CREATE TABLE `t_activity` (
  `activityId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '标题',
  `activityStr` varchar(20) NOT NULL DEFAULT '' COMMENT '活动文字',
  `description` varchar(50) DEFAULT '' COMMENT '活动描述',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '位置类型 1 普通 2 主页',
  `priceParam1` int(11) DEFAULT NULL COMMENT '原价',
  `priceParam2` int(11) DEFAULT NULL COMMENT '优惠价格',
  `href` varchar(100) DEFAULT '' COMMENT '按钮地址',
  `hrefStr` varchar(50) DEFAULT '' COMMENT '按钮文字',
  `image` varchar(100) NOT NULL DEFAULT '',
  `productId` int(11) DEFAULT NULL COMMENT '活动商品id',
  `activityType` int(11) DEFAULT '1' COMMENT '活动类型 1:活动价 2:折扣',
  `status` int(3) NOT NULL DEFAULT '2' COMMENT '开启关闭',
  PRIMARY KEY (`activityId`)
)  ;

/*Data for the table `t_activity` */

insert  into `t_activity`(`activityId`,`title`,`activityStr`,`description`,`type`,`priceParam1`,`priceParam2`,`href`,`hrefStr`,`image`,`productId`,`activityType`,`status`) values 
(2,'呵呵呵','降价啦降价啦','主页大活动',2,998,2333,'https://baidu.com','链接','uploadPath/405881050107904.jpg',1,1,2),
(7,'活动添加测试','哈哈哈哈','梵蒂冈分店刚发的',2,343,22,'','按钮','/res/buy_more/images/ad-2.jpg',2,1,2),
(9,'儿放啥的防守打法','违反手动阀啥的ad','123 手动阀',2,NULL,123123,'','手动阀','',NULL,1,2),
(12,'儿放啥的防守打法','违反手动阀啥的ad','123 手动阀',2,NULL,123123,'','手动阀','',NULL,1,2),
(14,'儿放啥的防守打法','违反手动阀啥的ad','123 手动阀',2,NULL,123123,'','手动阀','',NULL,1,2),
(15,'儿放啥的防守打法','违反手动阀啥的ad','123 手动阀',2,NULL,123123,'','手动阀','',NULL,1,2),
(16,'儿放啥的防守打法','违反手动阀啥的ad','123 手动阀',2,NULL,123123,'','手动阀','',NULL,1,1);

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `categoryId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `categoryName` varchar(10) NOT NULL DEFAULT '' COMMENT '主分类名称',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL DEFAULT '2' COMMENT '分类状态：1开启2：关闭',
  `orders` int(11) DEFAULT '0' COMMENT '分类订单数',
  `description` varchar(50) DEFAULT '' COMMENT '描述',
  `image` varchar(100) DEFAULT '',
  `tag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '标签',
  PRIMARY KEY (`categoryId`)
)  ;

/*Data for the table `t_category` */

insert  into `t_category`(`categoryId`,`categoryName`,`createTime`,`status`,`orders`,`description`,`image`,`tag`) values 
(1,'河北菜地方','2020-01-26 14:08:30',1,45,'我也不知道写什么','uploadPath/405726714363904.jpg','New Arrival'),
(3,'浙江菜','2020-01-26 14:08:36',1,34325,'我也不知道写什么','/res/buy_more/images/bag1.png','New Trendy'),
(4,'新疆菜','2020-01-26 14:08:56',1,123,'我也不知道写什么','uploadPath/405726719959040.jpg','New Trendy'),
(5,'河南菜,卖完了','2020-03-15 11:28:46',1,0,'其实我和上面写的差不多','uploadPath/405726788894720.jpg','qishiwoshihanyupinyin');

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '标题',
  `detail` varchar(200) NOT NULL DEFAULT '' COMMENT '详情',
  `image` varchar(100) NOT NULL DEFAULT '',
  `month` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1',
  `day` int(11) NOT NULL DEFAULT '1',
  `year` varchar(11) DEFAULT '',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `orderId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  PRIMARY KEY (`commentId`)
)   ;

/*Data for the table `t_comment` */

insert  into `t_comment`(`commentId`,`title`,`detail`,`image`,`month`,`day`,`year`,`createTime`,`orderId`,`productId`) values 
(1,'评论1','答复的','/res/buy_more/images/b-post-1.jpg','2',2,'','2020-02-01 00:37:01',0,1),
(2,'评论2','答复大师傅第三方收到但是','/res/buy_more/images/b-post-1.jpg','8',11,'','2020-02-01 00:37:05',0,1),
(3,'评论3','奥德赛范德萨是的发送到','/res/buy_more/images/b-post-1.jpg','5',22,'','2020-02-01 00:37:06',0,2),
(4,'评论4','大发大使馆的购房贷款来给你','/res/buy_more/images/b-post-1.jpg','3',24,'','2020-02-01 00:37:06',0,3),
(5,'评论5','答复看了','/res/buy_more/images/b-post-1.jpg','9',4,'','2020-02-01 00:37:07',0,4),
(6,'评论6',';l,gs;lfkhl;摸排','/res/buy_more/images/b-post-1.jpg','12',1,'','2020-02-01 00:37:08',0,5),
(7,'评论7','雷克第三方机构考虑对方及欧盟','/res/buy_more/images/b-post-1.jpg','1',2,'','2020-02-01 00:37:08',0,6),
(8,'评论8','fdslkgmljm','/res/buy_more/images/b-post-1.jpg','4',23,'','2020-02-01 00:37:09',0,7),
(9,'Latest Trendy Fashion for Women & Man Limited Stock','这是一个很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的评论','/res/buy_more/images/b-post-1.jpg','2',5,'','2020-02-01 00:37:10',0,1),
(10,'评论10','空白','/res/buy_more/images/b-post-1.jpg','8',12,'','2020-02-01 00:37:12',0,1);

/*Table structure for table `t_double_slide` */

DROP TABLE IF EXISTS `t_double_slide`;

CREATE TABLE `t_double_slide` (
  `doubleSlideId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '标题',
  `detail` varchar(50) NOT NULL DEFAULT '' COMMENT '详情',
  `image` varchar(100) NOT NULL DEFAULT '' COMMENT '图片',
  `href` varchar(100) NOT NULL DEFAULT '' COMMENT '链接地址',
  `hrefStr` varchar(50) NOT NULL DEFAULT '' COMMENT '链接文字',
  `status` int(11) DEFAULT '2' COMMENT '状态',
  PRIMARY KEY (`doubleSlideId`)
)   ;

/*Data for the table `t_double_slide` */

insert  into `t_double_slide`(`doubleSlideId`,`title`,`detail`,`image`,`href`,`hrefStr`,`status`) values 
(2,'电放费的','3日发大水 ','uploadPath/405886562623488.jpg','','手动阀阿萨德',2),
(3,'发光飞碟','而特工分店','uploadPath/405886805221376.jpg','','电饭锅地方',1);

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `orderId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderCode` varchar(12) NOT NULL DEFAULT '' COMMENT '主订单编号',
  `accountId` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `price` int(10) NOT NULL DEFAULT '0' COMMENT '支付价格',
  `status` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderId`)
)   ;

/*Data for the table `t_order` */

insert  into `t_order`(`orderId`,`orderCode`,`accountId`,`price`,`status`,`createTime`) values 
(1,'N1234567890',1,345,1,'2020-03-29 23:46:43'),
(2,'N1234567891',1,345,1,'2020-03-29 23:46:43'),
(3,'N1234567892',1,345,1,'2020-03-29 23:46:43'),
(4,'N1234567893',1,345,1,'2020-03-29 23:46:43'),
(5,'N1234567894',1,345,1,'2020-03-29 23:46:43'),
(6,'N1234567895',1,345,1,'2020-03-29 23:46:43'),
(7,'N1234567896',1,345,1,'2020-03-29 23:46:43'),
(8,'N1234567897',1,345,1,'2020-03-29 23:46:43');

/*Table structure for table `t_order_sub` */

DROP TABLE IF EXISTS `t_order_sub`;

CREATE TABLE `t_order_sub` (
  `orderSubId` int(10) NOT NULL AUTO_INCREMENT,
  `orderSubCode` varchar(12) NOT NULL DEFAULT '',
  `orderId` int(10) NOT NULL DEFAULT '0',
  `orderCode` varchar(12) DEFAULT '',
  `specId` int(10) NOT NULL DEFAULT '0',
  `productId` int(10) NOT NULL DEFAULT '0',
  `count` int(10) NOT NULL DEFAULT '0',
  `price` int(10) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderSubId`)
)  ;

/*Data for the table `t_order_sub` */

insert  into `t_order_sub`(`orderSubId`,`orderSubCode`,`orderId`,`orderCode`,`specId`,`productId`,`count`,`price`,`createTime`) values 
(1,'S1234567890',1,'N1234567890',1,1,1,1,'2020-03-30 00:01:32'),
(2,'S1234567891',1,'N1234567890',2,1,1,1,'2020-03-30 00:01:46');

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `productId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `categoryId` int(10) NOT NULL DEFAULT '0' COMMENT '主分类id',
  `subCategoryId` int(10) NOT NULL DEFAULT '0' COMMENT '子分类id',
  `productName` varchar(20) NOT NULL DEFAULT '' COMMENT '产品名称',
  `price` int(10) NOT NULL DEFAULT '0' COMMENT '价格 ,存储值为显示值乘以100',
  `image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '产品图,暂时用逗号分隔',
  `detailImage` varchar(200) DEFAULT NULL COMMENT '产品详情图',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '产品描述',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `productTagId` int(11) NOT NULL DEFAULT '0' COMMENT '产品标签id',
  `activityId` int(11) NOT NULL DEFAULT '0' COMMENT '活动id',
  `isNew` int(11) DEFAULT '2' COMMENT '是否新品2不是1是',
  PRIMARY KEY (`productId`)
)  ;

/*Data for the table `t_product` */

insert  into `t_product`(`productId`,`categoryId`,`subCategoryId`,`productName`,`price`,`image`,`detailImage`,`description`,`createTime`,`productTagId`,`activityId`,`isNew`) values 
(1,3,2,'产品一',33,'/res/buy_more/images/women1.jpg','/res/buy_more/images/single-product.jpg','描述一','2020-01-29 13:31:20',2,1,2),
(4,3,2,'产品四',45,'/res/buy_more/images/women1.jpg','/res/buy_more/images/single-product.jpg','描述四','2020-01-29 13:31:53',1,4,2),
(5,3,2,'产品五',234,'uploadPath/405732267085824.jpg','uploadPath/405732359553024.jpg','描述五','2020-01-29 13:32:25',1,3,2),
(8,3,2,'产品一',33,'/res/buy_more/images/women1.jpg','/res/buy_more/images/single-product.jpg','描述一','2020-01-29 13:31:20',3,1,2),
(9,5,3,'产品一',33,'/res/buy_more/images/women1.jpg','/res/buy_more/images/single-product.jpg','描述一','2020-01-29 13:31:20',0,1,2),
(10,3,2,'产品一',33,'uploadPath/405797530923008.jpg','uploadPath/405797531508736.jpg','描述一','2020-01-29 13:31:20',0,1,2),
(11,5,3,'产品一',33,'/res/buy_more/images/women1.jpg','/res/buy_more/images/single-product.jpg','描述一','2020-01-29 13:31:20',0,1,2),
(13,1,3,'产品一',33,'/res/buy_more/images/women1.jpg','/res/buy_more/images/single-product.jpg','描述一','2020-01-29 13:31:20',0,1,2),
(15,5,3,'产品一',33,'/res/buy_more/images/women1.jpg','/res/buy_more/images/single-product.jpg','描述一','2020-01-29 13:31:20',0,1,2),
(16,5,15,'产品一',33,'/res/buy_more/images/women1.jpg','/res/buy_more/images/single-product.jpg','描述一','2020-01-29 13:31:20',1,1,2),
(19,5,6,'产品一',33,'/res/buy_more/images/women1.jpg','/res/buy_more/images/single-product.jpg','描述一','2020-01-29 13:31:20',0,1,2),
(23,1,3,'商',44,'uploadPath/405867776106496.jpg','uploadPath/405867777740800.jpg','','2020-03-29 02:43:31',3,0,1),
(24,1,3,'对方水电费',656,'uploadPath/405867846524928.jpg','uploadPath/405867777740800.jpg','','2020-03-29 02:48:06',2,0,1);

/*Table structure for table `t_product_tag` */

DROP TABLE IF EXISTS `t_product_tag`;

CREATE TABLE `t_product_tag` (
  `productTagId` int(11) NOT NULL AUTO_INCREMENT,
  `productTagStr` varchar(20) DEFAULT '' COMMENT '产品标签描述',
  PRIMARY KEY (`productTagId`)
)   ;

/*Data for the table `t_product_tag` */

insert  into `t_product_tag`(`productTagId`,`productTagStr`) values 
(1,'热销'),
(2,'精选'),
(3,'主打');

/*Table structure for table `t_slide` */

DROP TABLE IF EXISTS `t_slide`;

CREATE TABLE `t_slide` (
  `slideId` int(11) NOT NULL AUTO_INCREMENT,
  `mainTitle` varchar(11) NOT NULL DEFAULT '' COMMENT '主标题',
  `doubleTitle` varchar(11) NOT NULL DEFAULT '' COMMENT '副标题',
  `description` varchar(50) NOT NULL DEFAULT '' COMMENT '描述',
  `href` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '链接地址',
  `hrefStr` varchar(11) DEFAULT '我是链接' COMMENT '链接文字',
  `image` varchar(100) NOT NULL DEFAULT '' COMMENT '图片',
  `slideOrder` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `status` int(11) DEFAULT '2' COMMENT '状态 1:开启 2关闭',
  PRIMARY KEY (`slideId`)
)   ;

/*Data for the table `t_slide` */

insert  into `t_slide`(`slideId`,`mainTitle`,`doubleTitle`,`description`,`href`,`hrefStr`,`image`,`slideOrder`,`status`) values 
(1,'轮播图测试','副标题1','我是个轮播图','https://baidu.com','我是链接','/res/buy_more/images/fashion-banner2.jpg',1,1),
(2,'轮播图测试2','副标题2','哈哈','','我是链接','uploadPath/405885360148480.jpg',2,1),
(3,'轮播图测试3','副标题3','的否','','我是链接','',2,1),
(4,'轮播图测试4','副标题4','大发','','我是链接','uploadPath/405886552236032.jpg',2,1),
(6,'轮播图测试6','副标题6','水电费按时','','我是链接','',2,2),
(7,'的法国恢复','45突然','回复广告费','','按钮','',1,1);

/*Table structure for table `t_spec` */

DROP TABLE IF EXISTS `t_spec`;

CREATE TABLE `t_spec` (
  `specId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `specTitle` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规格标题',
  `price` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '逗号,分隔价格,暂时按大中小三个分量存储',
  `description` varchar(500) NOT NULL DEFAULT '' COMMENT '描述',
  `image` varchar(500) DEFAULT '' COMMENT '规格图',
  `productId` int(10) NOT NULL COMMENT '产品id',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`specId`)
)   ;

/*Data for the table `t_spec` */

insert  into `t_spec`(`specId`,`specTitle`,`price`,`description`,`image`,`productId`,`createTime`) values 
(1,'清蒸','1,2,3','就清蒸呗','',5,'2020-02-23 22:22:41'),
(2,'红烧','2,35,324','','',5,'2020-02-23 22:24:42'),
(3,'炖了吧','1,34,3','','',5,'2020-02-23 22:24:56'),
(4,'干煸','42,43,1','','',5,'2020-02-23 22:25:15');

/*Table structure for table `t_subcategory` */

DROP TABLE IF EXISTS `t_subcategory`;

CREATE TABLE `t_subcategory` (
  `subCategoryId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subCategoryName` varchar(10) NOT NULL DEFAULT '' COMMENT '子分类名称',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `categoryId` int(10) NOT NULL COMMENT '主分类id',
  `status` int(11) NOT NULL DEFAULT '2' COMMENT '子分类状态1：开启2：关闭',
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '/res/buy_more/images/women1.jpg',
  PRIMARY KEY (`subCategoryId`)
)   ;

/*Data for the table `t_subcategory` */

insert  into `t_subcategory`(`subCategoryId`,`subCategoryName`,`createTime`,`categoryId`,`status`,`image`) values 
(2,'河北','2020-02-02 23:09:11',3,1,'/res/buy_more/images/women1.jpg'),
(3,'浙江1','2020-02-02 23:09:12',1,1,'/res/buy_more/images/women1.jpg'),
(4,'新疆','2020-02-02 23:09:13',4,1,'/res/buy_more/images/women1.jpg'),
(14,'河北2','2020-03-15 11:22:31',2,1,''),
(15,'河南1','2020-03-15 11:28:58',5,1,''),
(16,'河南2','2020-03-15 11:29:05',5,1,''),
(17,'河南3','2020-03-15 11:29:10',5,1,''),
(18,'11','2020-03-15 21:02:30',2,2,'/res/buy_more/images/women1.jpg'),
(19,'123','2020-03-15 21:02:32',2,2,'/res/buy_more/images/women1.jpg'),
(20,'12321','2020-03-15 21:02:35',2,2,'/res/buy_more/images/women1.jpg'),
(21,'324324','2020-03-15 21:02:39',2,2,'/res/buy_more/images/women1.jpg'),
(22,'123124','2020-03-15 21:02:42',2,2,'/res/buy_more/images/women1.jpg'),
(23,'12412','2020-03-15 21:02:45',2,2,'/res/buy_more/images/women1.jpg'),
(24,'哈哈哈哈','2020-03-22 15:41:58',1,1,'/res/buy_more/images/women1.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
