/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : music

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-04-27 17:41:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for singers
-- ----------------------------
DROP TABLE IF EXISTS `singers`;
CREATE TABLE `singers` (
  `singerId` int(11) NOT NULL AUTO_INCREMENT,
  `singerName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `singerImg` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`singerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of singers
-- ----------------------------

-- ----------------------------
-- Table structure for songs
-- ----------------------------
DROP TABLE IF EXISTS `songs`;
CREATE TABLE `songs` (
  `songId` int(11) NOT NULL AUTO_INCREMENT,
  `songName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `singerId` int(11) DEFAULT NULL,
  `songTime` datetime DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `songWord` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `songHeat` int(11) DEFAULT NULL,
  PRIMARY KEY (`songId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of songs
-- ----------------------------

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `typeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeParentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '语种', '0');
INSERT INTO `type` VALUES ('2', '流派', '0');
INSERT INTO `type` VALUES ('3', '主题', '0');
INSERT INTO `type` VALUES ('4', '心情', '0');
INSERT INTO `type` VALUES ('5', '场景', '0');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userPwd` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userImg` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userSongId` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', '1234', null, null);

-- ----------------------------
-- Table structure for usersongs
-- ----------------------------
DROP TABLE IF EXISTS `usersongs`;
CREATE TABLE `usersongs` (
  `songId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `userSongId` int(11) DEFAULT NULL,
  PRIMARY KEY (`songId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of usersongs
-- ----------------------------
