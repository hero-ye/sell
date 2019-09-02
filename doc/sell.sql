/*
 Navicat MySQL Data Transfer

 Source Server         : sell
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 192.168.0.117:3306
 Source Schema         : sell

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 03/09/2019 00:37:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_detail`;
CREATE TABLE `tbl_order_detail`  (
  `detail_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '详情ID',
  `order_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联订单主表ID(tbl_order_main.order_id)',
  `product_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联商品表ID(tbl_product_info.product_id)',
  `product_name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `product_quantity` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `product_icon` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `create_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_order_main
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_main`;
CREATE TABLE `tbl_order_main`  (
  `order_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单ID',
  `buyer_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家姓名',
  `buyer_phone` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家电话',
  `buyer_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8, 2) NULL DEFAULT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NULL DEFAULT NULL COMMENT '订单状态：(默认)0 新下单',
  `pay_status` tinyint(3) NULL DEFAULT 0 COMMENT '支付状态：(默认)0 未支付',
  `create_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `idx_buyer_openid`(`buyer_openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_order_main
-- ----------------------------
INSERT INTO `tbl_order_main` VALUES ('402880e86cf2b30d016cf2b36be90000', '叶金雄', '17671616677', '湖北省武汉市东西湖区联通路环湖东路中国联通湖北分公司', '1001011', 72.90, 0, 0, 'yejx', '2019-09-02 23:59:49', 'yejx', '2019-09-02 23:59:52');
INSERT INTO `tbl_order_main` VALUES ('402880e86cf2bf42016cf2c1f8030001', '叶金雄', '15927125366', '湖北省武汉市东西湖区联通路环湖东路中国联通湖北分公司', '1001011', 38.00, 0, 0, 'yejx', '2019-09-03 00:15:06', 'yejx', '2019-09-03 00:15:08');
INSERT INTO `tbl_order_main` VALUES ('402880e86cf2c337016cf2c79a120000', '叶金雄', '15927124358', '湖北省武汉市江汉区常青路万景国际B座11楼', '1001011', 58.00, 0, 0, 'yejx', '2019-09-03 00:21:03', 'yejx', '2019-09-03 00:21:03');

-- ----------------------------
-- Table structure for tbl_product_category
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_category`;
CREATE TABLE `tbl_product_category`  (
  `category_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `category_name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类目名称',
  `category_code` int(11) NULL DEFAULT NULL COMMENT '类目编号',
  `create_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `uqe_category_type`(`category_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品类目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_product_category
-- ----------------------------
INSERT INTO `tbl_product_category` VALUES ('2c9180896b27f578016b2a9a63e00003', '热销榜', 2, '叶金雄', '2019-08-25 23:24:17', '叶金雄', '2019-08-25 23:24:24');
INSERT INTO `tbl_product_category` VALUES ('2c9180926880db770168813692c70002', '天天半价', 3, '叶金雄', '2019-08-26 00:09:54', '叶金雄', '2019-08-26 00:09:51');
INSERT INTO `tbl_product_category` VALUES ('402880e86cec24bd016cec2639df0000', '九月特惠', 4, 'yejx', NULL, 'yejx', '2019-09-01 17:57:25');

-- ----------------------------
-- Table structure for tbl_product_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_info`;
CREATE TABLE `tbl_product_info`  (
  `product_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品ID',
  `product_name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NULL DEFAULT NULL COMMENT '单价',
  `product_stock` int(11) NULL DEFAULT NULL COMMENT '库存',
  `product_desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `product_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品状态：0 正常，1 下架',
  `category_code` int(11) NULL DEFAULT NULL COMMENT '类目编号',
  `create_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_product_info
-- ----------------------------
INSERT INTO `tbl_product_info` VALUES ('402880e86ced40cb016ced44a7330000', '奥尔良鸡肉披萨', 72.90, 38, '精选奥尔良上等鸡肉', '/icon/2019/09/01/pizza.jpg', '0', 2, 'yejx', '2019-09-01 22:40:12', 'yejx', '2019-09-01 22:40:14');
INSERT INTO `tbl_product_info` VALUES ('402880e86ced40cb016ced47c5b00001', '地狱拉面', 38.00, 22, '不一样的拉面', '/icon/2019/09/01/hellramen.jpg', '0', 2, 'yejx', '2019-09-01 22:48:17', 'yejx', '2019-09-01 22:48:15');
INSERT INTO `tbl_product_info` VALUES ('402880e86ced40cb016ced49a6b60002', '北极贝刺身', 58.00, 1, '6片/份。加拿大冷水生长北极贝。', '/icon/2019/09/01/scallops sashimi.jpg', '0', 3, 'yejx', '2019-09-01 22:48:20', 'yejx', '2019-09-01 22:48:22');

-- ----------------------------
-- Table structure for tbl_seller_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_seller_info`;
CREATE TABLE `tbl_seller_info`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `openid` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `create_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '卖家信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
