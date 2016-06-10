/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : music

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-06-08 23:21:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for singers
-- ----------------------------
DROP TABLE IF EXISTS `singers`;
CREATE TABLE `singers` (
  `singerId` int(11) NOT NULL AUTO_INCREMENT,
  `singerName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `singerInfo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`singerId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of singers
-- ----------------------------
INSERT INTO `singers` VALUES ('10', '33', '33');
INSERT INTO `singers` VALUES ('11', '333', '333');
INSERT INTO `singers` VALUES ('12', '4', '145555555555\r\n');
INSERT INTO `singers` VALUES ('13', '恩恩', '恩恩');
INSERT INTO `singers` VALUES ('14', '歌手1', '1');
INSERT INTO `singers` VALUES ('15', '额', '的 得到');
INSERT INTO `singers` VALUES ('16', '特', '二恶');
INSERT INTO `singers` VALUES ('17', '555', '555');
INSERT INTO `singers` VALUES ('18', 'test1', '111');
INSERT INTO `singers` VALUES ('19', 'test2', '222');
INSERT INTO `singers` VALUES ('20', '6', '6');
INSERT INTO `singers` VALUES ('21', '5', '5');

-- ----------------------------
-- Table structure for songs
-- ----------------------------
DROP TABLE IF EXISTS `songs`;
CREATE TABLE `songs` (
  `songId` int(11) NOT NULL AUTO_INCREMENT,
  `songImg` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `songName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `singerId` int(11) DEFAULT NULL,
  `songTime` datetime DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `songWord` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `songHeat` int(11) DEFAULT NULL,
  `songAlbum` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`songId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of songs
-- ----------------------------
INSERT INTO `songs` VALUES ('9', null, '333', '8', '2016-05-16 12:00:40', '4', '333', '33', null);
INSERT INTO `songs` VALUES ('10', null, '444', '8', '2016-05-16 12:01:04', '11', '44', '44', null);
INSERT INTO `songs` VALUES ('11', null, '4444', '8', '2016-05-16 12:01:23', '4', '44', '44', null);
INSERT INTO `songs` VALUES ('12', null, '顶顶顶顶', '4', '2016-05-16 12:02:50', '3', '额恩恩', '22', null);
INSERT INTO `songs` VALUES ('13', null, '333', '4', '2016-05-16 14:35:01', '3', '33', '333', null);
INSERT INTO `songs` VALUES ('14', null, '方法', '4', '2016-05-16 14:35:26', '3', '方法', '22', null);
INSERT INTO `songs` VALUES ('15', null, '恩恩', '4', '2016-05-17 13:43:24', '3', '恩恩', '33', null);
INSERT INTO `songs` VALUES ('16', null, '3', '9', '2016-05-30 12:53:30', '11', '3', '3', '3');
INSERT INTO `songs` VALUES ('17', null, '888', '9', '2016-05-30 12:55:09', '13', '8', '8', '8');
INSERT INTO `songs` VALUES ('18', null, '999', '9', '2016-05-30 12:59:38', '11', '99', '9', '9');
INSERT INTO `songs` VALUES ('19', null, '111', '9', '2016-05-30 13:02:30', '11', '11', '11', '11');
INSERT INTO `songs` VALUES ('20', null, '777', '9', '2016-05-30 13:03:55', '11', '77', '77', '77');
INSERT INTO `songs` VALUES ('21', null, '3', '9', '2016-06-08 19:12:54', '1', '3', '3', null);
INSERT INTO `songs` VALUES ('22', null, '1222', '9', '2016-06-08 19:16:06', '1', '22', '222', null);
INSERT INTO `songs` VALUES ('23', null, '3', '10', '2016-06-08 19:17:25', '1', '3', '3', null);
INSERT INTO `songs` VALUES ('24', null, '1', '10', '2016-06-08 19:41:57', '20', '1', '1', null);
INSERT INTO `songs` VALUES ('25', null, '77', '10', '2016-06-08 19:56:51', '22', '777', '777', null);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `typeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeParentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('2', '流派', '0');
INSERT INTO `type` VALUES ('3', '主题', '0');
INSERT INTO `type` VALUES ('4', '心情', '0');
INSERT INTO `type` VALUES ('5', '场景', '0');
INSERT INTO `type` VALUES ('6', '古风', '0');
INSERT INTO `type` VALUES ('7', '戏曲', '0');
INSERT INTO `type` VALUES ('8', '主题', '0');
INSERT INTO `type` VALUES ('19', '二恶', '1');
INSERT INTO `type` VALUES ('22', 't1', '3');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userPwd` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', '1234');
INSERT INTO `users` VALUES ('2', '456', '456');
INSERT INTO `users` VALUES ('3', '456', '456');
INSERT INTO `users` VALUES ('4', '789', '789');
INSERT INTO `users` VALUES ('5', '1', '1');
INSERT INTO `users` VALUES ('6', '1', '5');
INSERT INTO `users` VALUES ('7', '1', '222');
INSERT INTO `users` VALUES ('8', '1', '2');
INSERT INTO `users` VALUES ('9', '1', '2');
INSERT INTO `users` VALUES ('10', '4', '4444');
INSERT INTO `users` VALUES ('11', '4', '4444');
INSERT INTO `users` VALUES ('12', '4', '6');
INSERT INTO `users` VALUES ('13', '4', '444');
INSERT INTO `users` VALUES ('14', '8', '8');
INSERT INTO `users` VALUES ('15', '0', '0');
INSERT INTO `users` VALUES ('16', '0', '0');
INSERT INTO `users` VALUES ('17', ' f', 'f');
INSERT INTO `users` VALUES ('18', '0', '0');
INSERT INTO `users` VALUES ('19', '0', '0');
INSERT INTO `users` VALUES ('20', '8', '8');
INSERT INTO `users` VALUES ('21', '8', '8');
INSERT INTO `users` VALUES ('22', '8', '8');

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
