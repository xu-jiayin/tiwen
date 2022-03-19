/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : tiwen

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 17/03/2022 22:36:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user`  (
  `id` bigint NOT NULL,
  `userID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passWord` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES (954124157556097024, '1326200871', 'e10adc3949ba59abbe56e057f20f883e', '83cd25e99b1c4884b55e34e2fd21b31e');

SET FOREIGN_KEY_CHECKS = 1;
