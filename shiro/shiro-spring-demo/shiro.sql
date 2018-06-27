/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-06-27 16:30:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permissions
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions`;
CREATE TABLE `t_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) NOT NULL,
  `permission` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permissions
-- ----------------------------
INSERT INTO `t_permissions` VALUES ('1', 'admin', 'delete');
INSERT INTO `t_permissions` VALUES ('2', 'admin', 'update');

-- ----------------------------
-- Table structure for t_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_roles`;
CREATE TABLE `t_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) NOT NULL,
  `role_name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_roles
-- ----------------------------
INSERT INTO `t_roles` VALUES ('1', 'jas', 'admin');
INSERT INTO `t_roles` VALUES ('2', 'jas', 'user');
INSERT INTO `t_roles` VALUES ('3', 'zc', 'user');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'jas', 'd4cfbe5d3cf6b49653c4b641b044df76');
INSERT INTO `t_user` VALUES ('2', 'zc', 'c4af508c6ef1619e755d14fc6d29e1d4');
