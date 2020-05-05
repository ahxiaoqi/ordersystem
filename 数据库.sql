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

CREATE TABLE `t_account` (
  `accountId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userName` varchar(10) NOT NULL DEFAULT '' COMMENT '用户名',
  `passWord` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `nickName` varchar(10) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `accountType` int(10) NOT NULL DEFAULT '3' COMMENT '用户类型1管理员 2 商家 3 普通用户',
  `headImg` varchar(100) NOT NULL DEFAULT '' COMMENT '头像',
  PRIMARY KEY (`accountId`)
) ;

/*Table structure for table `t_activity` */

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
) ;

/*Table structure for table `t_category` */

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
);

/*Table structure for table `t_comment` */

CREATE TABLE `t_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '标题',
  `detail` varchar(200) NOT NULL DEFAULT '' COMMENT '详情',
  `image` varchar(100) NOT NULL DEFAULT '',
  `month` int(11) NOT NULL DEFAULT '1',
  `day` int(11) NOT NULL DEFAULT '1',
  `year` varchar(11) DEFAULT '',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `orderId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  PRIMARY KEY (`commentId`)
) ;

/*Table structure for table `t_double_slide` */

CREATE TABLE `t_double_slide` (
  `doubleSlideId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '标题',
  `detail` varchar(50) NOT NULL DEFAULT '' COMMENT '详情',
  `image` varchar(100) NOT NULL DEFAULT '' COMMENT '图片',
  `href` varchar(100) NOT NULL DEFAULT '' COMMENT '链接地址',
  `hrefStr` varchar(50) NOT NULL DEFAULT '' COMMENT '链接文字',
  `status` int(11) DEFAULT '2' COMMENT '状态',
  PRIMARY KEY (`doubleSlideId`)
);

/*Table structure for table `t_order` */

CREATE TABLE `t_order` (
  `orderId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主订单编号',
  `accountId` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `price` int(10) NOT NULL DEFAULT '0' COMMENT '支付价格',
  `status` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(100) NOT NULL DEFAULT '' COMMENT '地址',
  `tel` int(100) DEFAULT NULL COMMENT '电话',
  `isComment` int(2) NOT NULL DEFAULT '2' COMMENT '是否评论',
  PRIMARY KEY (`orderId`)
) ;
/*Table structure for table `t_order_sub` */

CREATE TABLE `t_order_sub` (
  `orderSubId` int(10) NOT NULL AUTO_INCREMENT,
  `orderSubCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `orderId` int(10) NOT NULL DEFAULT '0',
  `orderCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `specId` int(10) NOT NULL DEFAULT '0',
  `productId` int(10) NOT NULL DEFAULT '0',
  `count` int(10) NOT NULL DEFAULT '0',
  `price` int(10) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `specType` int(3) NOT NULL DEFAULT '0',
  `isComment` int(2) NOT NULL DEFAULT '2' COMMENT '是否评论',
  PRIMARY KEY (`orderSubId`)
) ;
/*Table structure for table `t_product` */

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
) ;
/*Table structure for table `t_product_tag` */

CREATE TABLE `t_product_tag` (
  `productTagId` int(11) NOT NULL AUTO_INCREMENT,
  `productTagStr` varchar(20) DEFAULT '' COMMENT '产品标签描述',
  PRIMARY KEY (`productTagId`)
) ;
/*Table structure for table `t_slide` */

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
) ;
/*Table structure for table `t_spec` */

CREATE TABLE `t_spec` (
  `specId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `specTitle` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '规格标题',
  `price` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '逗号,分隔价格,暂时按大中小三个分量存储',
  `description` varchar(500) NOT NULL DEFAULT '' COMMENT '描述',
  `image` varchar(500) DEFAULT '' COMMENT '规格图',
  `productId` int(10) NOT NULL DEFAULT '0' COMMENT '产品id',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`specId`)
) ;
/*Table structure for table `t_subcategory` */

CREATE TABLE `t_subcategory` (
  `subCategoryId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subCategoryName` varchar(10) NOT NULL DEFAULT '' COMMENT '子分类名称',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `categoryId` int(10) NOT NULL COMMENT '主分类id',
  `status` int(11) NOT NULL DEFAULT '2' COMMENT '子分类状态1：开启2：关闭',
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '/res/buy_more/images/women1.jpg',
  PRIMARY KEY (`subCategoryId`)
) ;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
