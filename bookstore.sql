/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : onlineshop

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 22/02/2023 16:51:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int NOT NULL,
  `adminName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` (`id`, `adminName`, `password`) VALUES (1, 'admin', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `categoryId` int NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `categoryName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`categoryId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (1, '程序设计');
INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (2, '数据库');
INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (3, '计算机网络');
COMMIT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsId` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goodsName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `categoryId` int NOT NULL COMMENT '分类id，外键',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `goodsDes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品描述',
  `goodsSellCount` int NOT NULL COMMENT '销量',
  `goodsPicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`goodsId`) USING BTREE,
  KEY `goods_category` (`categoryId`) USING BTREE,
  CONSTRAINT `goods_category` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` (`goodsId`, `goodsName`, `categoryId`, `price`, `goodsDes`, `goodsSellCount`, `goodsPicture`) VALUES (1, 'SQL server', 2, 15.00, 'aaa', 16, 'http://www.tup.tsinghua.edu.cn/upload/bigbookimg/093161-01.jpg');
INSERT INTO `goods` (`goodsId`, `goodsName`, `categoryId`, `price`, `goodsDes`, `goodsSellCount`, `goodsPicture`) VALUES (3, 'java从入门到入土', 1, 68.00, 'bbb', 17, 'http://www.tup.tsinghua.edu.cn/upload/bigbookimg/092543-01.jpg');
INSERT INTO `goods` (`goodsId`, `goodsName`, `categoryId`, `price`, `goodsDes`, `goodsSellCount`, `goodsPicture`) VALUES (4, '计算机网络（第6版）', 3, 3600.00, 'ccc', 11, 'http://www.tup.tsinghua.edu.cn/upload/bigbookimg/086647-01.jpg');
COMMIT;

-- ----------------------------
-- Table structure for goods_car_member
-- ----------------------------
DROP TABLE IF EXISTS `goods_car_member`;
CREATE TABLE `goods_car_member` (
  `memberId` int NOT NULL COMMENT '用户id',
  `goodsId` int NOT NULL COMMENT '商品id',
  `count` int NOT NULL COMMENT '商品数量',
  KEY `member_id_0` (`memberId`) USING BTREE,
  KEY `goods_id_0` (`goodsId`) USING BTREE,
  CONSTRAINT `goods_id_0` FOREIGN KEY (`goodsId`) REFERENCES `goods` (`goodsId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `member_id_0` FOREIGN KEY (`memberId`) REFERENCES `member` (`memberId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of goods_car_member
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `memberId` int NOT NULL AUTO_INCREMENT,
  `memberName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `phoneNum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`memberId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------
BEGIN;
INSERT INTO `member` (`memberId`, `memberName`, `sex`, `phoneNum`, `address`, `password`) VALUES (6, 'aaa', '男', '89123', 'dsdeiewo', 'aaa');
COMMIT;

-- ----------------------------
-- Table structure for order_and_goods
-- ----------------------------
DROP TABLE IF EXISTS `order_and_goods`;
CREATE TABLE `order_and_goods` (
  `orderId` int NOT NULL,
  `goodsId` int NOT NULL,
  `count` int NOT NULL,
  KEY `goodsId` (`goodsId`) USING BTREE,
  KEY `orderId` (`orderId`) USING BTREE,
  CONSTRAINT `goodsId` FOREIGN KEY (`goodsId`) REFERENCES `goods` (`goodsId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderId` FOREIGN KEY (`orderId`) REFERENCES `tbl_order` (`orderId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of order_and_goods
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order` (
  `orderId` int NOT NULL AUTO_INCREMENT COMMENT '订单Id',
  `orderDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单日期',
  `memberId` int NOT NULL COMMENT '会员编号',
  `orderNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  PRIMARY KEY (`orderId`) USING BTREE,
  KEY `member_id` (`memberId`) USING BTREE,
  CONSTRAINT `member_id` FOREIGN KEY (`memberId`) REFERENCES `member` (`memberId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tbl_order
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
