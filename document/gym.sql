/*
 Navicat Premium Data Transfer

 Source Server         : aliyun_root
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 8.143.9.57:3306
 Source Schema         : gym

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 12/04/2022 01:20:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gym_book
-- ----------------------------
DROP TABLE IF EXISTS `gym_book`;
CREATE TABLE `gym_book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `place_id` int NULL DEFAULT NULL COMMENT '场地id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约人姓名',
  `is_cancel` tinyint(1) NULL DEFAULT 0 COMMENT '是否取消',
  `is_arrive` tinyint(1) NULL DEFAULT 0 COMMENT '是否到达',
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gym_book
-- ----------------------------
INSERT INTO `gym_book` VALUES (30, '2022-02-20 08:01:00', '2022-02-20 19:01:00', 1, '21', 0, 1, NULL);
INSERT INTO `gym_book` VALUES (31, '2022-02-20 15:36:00', '2022-02-20 18:36:00', 2, '1222', 0, 1, 2);
INSERT INTO `gym_book` VALUES (32, '2022-02-20 15:33:00', '2022-02-20 20:33:00', 2, '1222', 1, 0, 2);

-- ----------------------------
-- Table structure for gym_client
-- ----------------------------
DROP TABLE IF EXISTS `gym_client`;
CREATE TABLE `gym_client`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户手机号',
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户性别',
  `age` int NULL DEFAULT NULL COMMENT '客户年龄',
  `birth` date NULL DEFAULT NULL COMMENT '客户生日',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户地址',
  `is_vip` tinyint(1) NULL DEFAULT NULL COMMENT '是否会员',
  `vip_time` date NULL DEFAULT NULL COMMENT '会员到期时间',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`, `tel`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gym_client
-- ----------------------------
INSERT INTO `gym_client` VALUES (1, '客户1', '11111111111', '男', 50, '2022-01-25', '家', 0, NULL, NULL);
INSERT INTO `gym_client` VALUES (2, '陈材2', '123', '女', 1, '2022-02-09', '1', 0, NULL, '2');
INSERT INTO `gym_client` VALUES (4, 'jack', '18', '男', 18, '2022-04-22', '2', 1, NULL, '1');

-- ----------------------------
-- Table structure for gym_place
-- ----------------------------
DROP TABLE IF EXISTS `gym_place`;
CREATE TABLE `gym_place`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场地名称',
  `kind_id` int NULL DEFAULT NULL COMMENT '场地类型id',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场地大小',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '消费说明',
  `insert_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `state` tinyint(1) NULL DEFAULT 0 COMMENT '场地状态(0空闲，1使用中，2已预约，3暂停使用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gym_place
-- ----------------------------
INSERT INTO `gym_place` VALUES (1, '篮球场1', 1, '100x100', 20.00, '2022-04-12 00:57:57', 0);
INSERT INTO `gym_place` VALUES (2, '篮球场2', 1, '100x100', 10.00, '2022-02-20 15:33:33', 2);
INSERT INTO `gym_place` VALUES (3, '足球场1', 2, '100x100', 10.00, '2022-02-20 15:28:36', 0);
INSERT INTO `gym_place` VALUES (4, '足球场2', 2, '100x100', 10.00, '2022-03-10 10:22:42', 0);
INSERT INTO `gym_place` VALUES (5, '1', 1, '1', 1.00, '2022-03-10 10:22:42', 0);
INSERT INTO `gym_place` VALUES (6, '篮球场11', 1, '100×100', 10.00, NULL, 0);

-- ----------------------------
-- Table structure for gym_place_kind
-- ----------------------------
DROP TABLE IF EXISTS `gym_place_kind`;
CREATE TABLE `gym_place_kind`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场地类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gym_place_kind
-- ----------------------------
INSERT INTO `gym_place_kind` VALUES (1, '篮球场');
INSERT INTO `gym_place_kind` VALUES (2, '足球场');
INSERT INTO `gym_place_kind` VALUES (8, '场馆');
INSERT INTO `gym_place_kind` VALUES (9, 'cg');
INSERT INTO `gym_place_kind` VALUES (10, '篮球场');
INSERT INTO `gym_place_kind` VALUES (11, '类型');

-- ----------------------------
-- Table structure for shop_bill
-- ----------------------------
DROP TABLE IF EXISTS `shop_bill`;
CREATE TABLE `shop_bill`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `creat_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '账单创建时间',
  `client_id` int NULL DEFAULT NULL COMMENT '客户id',
  `place_id` int NULL DEFAULT NULL COMMENT '场地id',
  `place_time` int NULL DEFAULT NULL COMMENT '场地使用时间',
  `total` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `is_pay` tinyint(1) NULL DEFAULT 0 COMMENT '是否结账',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shop_bill
-- ----------------------------
INSERT INTO `shop_bill` VALUES (41, '2022-03-29 16:57:52', 1, 2, 1, NULL, 1);
INSERT INTO `shop_bill` VALUES (42, '2022-03-29 16:57:52', 1, 5, 1, NULL, 1);
INSERT INTO `shop_bill` VALUES (43, '2022-03-29 16:58:00', 2, 1, 1, NULL, 1);
INSERT INTO `shop_bill` VALUES (44, '2022-04-12 00:17:48', NULL, 1, 1, NULL, 1);
INSERT INTO `shop_bill` VALUES (45, '2022-04-12 00:42:19', NULL, 1, 1, NULL, 1);
INSERT INTO `shop_bill` VALUES (46, '2022-04-12 00:53:47', NULL, 1, 1, NULL, 1);
INSERT INTO `shop_bill` VALUES (47, '2022-04-12 00:57:56', NULL, 1, 1, NULL, 1);

-- ----------------------------
-- Table structure for shop_bill_type
-- ----------------------------
DROP TABLE IF EXISTS `shop_bill_type`;
CREATE TABLE `shop_bill_type`  (
  `bill_id` int NULL DEFAULT NULL COMMENT '账单id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` int NULL DEFAULT NULL COMMENT 'id',
  `num` int NULL DEFAULT NULL COMMENT '数量'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shop_bill_type
-- ----------------------------
INSERT INTO `shop_bill_type` VALUES (1, 'coach', 1, 20);
INSERT INTO `shop_bill_type` VALUES (44, 'obj', 1, 20);
INSERT INTO `shop_bill_type` VALUES (44, 'obj', 2, 2);
INSERT INTO `shop_bill_type` VALUES (44, 'obj', 4, 10);
INSERT INTO `shop_bill_type` VALUES (44, 'coach', 1, 1);
INSERT INTO `shop_bill_type` VALUES (45, 'coach', 1, 1);
INSERT INTO `shop_bill_type` VALUES (45, 'obj', 1, 10);
INSERT INTO `shop_bill_type` VALUES (46, 'obj', 1, 12);
INSERT INTO `shop_bill_type` VALUES (46, 'coach', 1, 1);
INSERT INTO `shop_bill_type` VALUES (47, 'coach', 1, 1);

-- ----------------------------
-- Table structure for shop_coach
-- ----------------------------
DROP TABLE IF EXISTS `shop_coach`;
CREATE TABLE `shop_coach`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教练姓名',
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教练性别',
  `age` int NULL DEFAULT NULL COMMENT '教练年龄',
  `course` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教授课程',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格（/h）',
  `state` tinyint(1) NULL DEFAULT 0 COMMENT '教练状态（0：空闲，1：上课中，2：已预约）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shop_coach
-- ----------------------------
INSERT INTO `shop_coach` VALUES (1, '陈材', '男', 23, '健美', 500.00, 0);

-- ----------------------------
-- Table structure for shop_object
-- ----------------------------
DROP TABLE IF EXISTS `shop_object`;
CREATE TABLE `shop_object`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `num` int NULL DEFAULT NULL COMMENT '商品库存数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shop_object
-- ----------------------------
INSERT INTO `shop_object` VALUES (1, '可口可乐', 3.50, '喝的', 978);
INSERT INTO `shop_object` VALUES (2, '百事可乐', 3.00, '喝的', 101);
INSERT INTO `shop_object` VALUES (4, '旺仔', 5.00, '好喝', 89);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `parent_id` int NULL DEFAULT NULL COMMENT '父类id',
  `permission_id` int NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '运动馆管理', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (2, '客户管理', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (3, '消费管理', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (4, '系统管理', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (5, '场馆管理', '/place/placePage', 1, NULL);
INSERT INTO `sys_menu` VALUES (6, '预约管理', '/book/bookPage', 1, NULL);
INSERT INTO `sys_menu` VALUES (7, '教练管理', '/coach/coachPage', 1, NULL);
INSERT INTO `sys_menu` VALUES (8, '客户信息', '/client/clientPage', 2, NULL);
INSERT INTO `sys_menu` VALUES (9, '商品列表', '/shopObject/shopObjectPage', 3, NULL);
INSERT INTO `sys_menu` VALUES (10, '消费详情', '/bill/billPage', 3, NULL);
INSERT INTO `sys_menu` VALUES (11, '用户管理', '/user/userPage', 4, NULL);
INSERT INTO `sys_menu` VALUES (12, '菜单设置', '/menu/menuPage', 4, NULL);
INSERT INTO `sys_menu` VALUES (13, '修改密码', '/user/updPwd', 4, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责场地',
  `is_admin` tinyint(1) NULL DEFAULT NULL COMMENT '是否管理员',
  `kind` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场地类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '0', 1, '0');
INSERT INTO `sys_role` VALUES (2, '篮球场管理员', '0', 1, '1');
INSERT INTO `sys_role` VALUES (3, '篮球场员工', '1', 0, '1');
INSERT INTO `sys_role` VALUES (4, '足球场管理员', '0', 1, '2');
INSERT INTO `sys_role` VALUES (5, '足球场员工', '3', 0, '2');
INSERT INTO `sys_role` VALUES (11, '场馆管理员', '0', 1, '8');
INSERT INTO `sys_role` VALUES (12, 'cg管理员', '0', 1, '9');
INSERT INTO `sys_role` VALUES (13, '篮球场管理员', '0', 1, '10');
INSERT INTO `sys_role` VALUES (14, '类型管理员', '0', 1, '11');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '职位',
  `role_time` datetime NULL DEFAULT NULL COMMENT '入职时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'root', 'root', '18250314788', '352202111111111111', 'Y6nw6nu5gFB5a2SehUgYRQ==', '2', '2022-01-04 16:00:00');
INSERT INTO `sys_user` VALUES (2, 'admin', 'admin', '18250314777', '352202111111111111', 'Y6nw6nu5gFB5a2SehUgYRQ==', '4', '2022-01-01 16:00:00');
INSERT INTO `sys_user` VALUES (3, 'aaa', 'aaa', '', '', NULL, '11', NULL);

SET FOREIGN_KEY_CHECKS = 1;
