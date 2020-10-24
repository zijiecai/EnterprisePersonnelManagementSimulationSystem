/*
 Navicat Premium Data Transfer

 Source Server         : mysql1
 Source Server Type    : MySQL
 Source Server Version : 50623
 Source Host           : localhost:3306
 Source Schema         : j2eedb

 Target Server Type    : MySQL
 Target Server Version : 50623
 File Encoding         : 65001

 Date: 19/07/2020 18:09:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `did` int(11) NOT NULL,
  `dname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dremark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '技术部', 'dremark');
INSERT INTO `dept` VALUES (2, '行政部', 'dremark123');
INSERT INTO `dept` VALUES (3, '法律部', '111');
INSERT INTO `dept` VALUES (4, '管理部', '233');
INSERT INTO `dept` VALUES (5, '策划部', '加油');
INSERT INTO `dept` VALUES (6, '设计部', '666');
INSERT INTO `dept` VALUES (7, '摄影部', 'dremark7');
INSERT INTO `dept` VALUES (8, '人力资源部', '冲冲冲123');
INSERT INTO `dept` VALUES (9, '生产部', NULL);
INSERT INTO `dept` VALUES (11, '工程部', '搞起');
INSERT INTO `dept` VALUES (12, '公关部', 'dremark12');
INSERT INTO `dept` VALUES (13, '后勤部', '123');
INSERT INTO `dept` VALUES (100, '财务部', '');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `card_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `post_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `party` enum('团员','党员','群众','其他') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` datetime(0) NOT NULL,
  `race` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `education` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `speciality` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hobby` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dept_employee`(`dept_id`) USING BTREE,
  INDEX `job_employee`(`job_id`) USING BTREE,
  CONSTRAINT `dept_employee` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`did`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `job_employee` FOREIGN KEY (`job_id`) REFERENCES `job` (`jid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 1, 5, '杨过', '44052199107134196', '广东省广州市海珠区', '510000', '13065638647', '13065638647', '3627242737', '13065638647@163.com', '男', '党员', '1991-07-13 00:00:00', '汉族', '硕士', '负责', '羽毛球', 'remark1', '2020-06-22 14:43:41');
INSERT INTO `employee` VALUES (2, 2, 3, '张敏', '44052199804214166', '广东省江门市蓬江区', '529030', '13064568211', '13064568211', '4611574478', '13064568211@163.com', '女', '团员', '1998-04-21 00:00:00', '汉族', '本科', '细心', '乒乓球', 'remark2', '2020-06-22 14:47:12');
INSERT INTO `employee` VALUES (3, 6, 8, '小龙虾', '150102199003078690', '北京市东城区', '450041', '15754744337', '15754744337', '1445245242', '1445245242@qq.com', '男', '团员', '2012-07-19 01:18:55', '汉族', '大学', '吃饭', '睡觉', '打豆豆', '2020-07-19 01:19:59');
INSERT INTO `employee` VALUES (4, 4, 7, '小拉菲', '360102199003078212', '安徽省合肥市瑶海区', '450041', '15765435141', '15765435141', '513541411', '513541411@qq.com', '男', '群众', '2020-07-19 01:24:13', '汉族', '本科', '演讲', '吃饭、睡觉、打豆豆', '', '2020-07-19 01:24:19');
INSERT INTO `employee` VALUES (6, 6, 6, '肖吕生', '360102199003078144', '山东威海荣成市成山镇', '450041', '15765435441', '15765435441', '513535633', '513535633@qq.com', '男', '群众', '2016-07-19 00:00:00', '汉族', '本科', '演讲', '吃饭、睡觉、打豆豆', '', '2020-07-19 01:25:59');
INSERT INTO `employee` VALUES (7, 4, 3, '修奥飞', '360102199003076362', '江苏南通海安县李堡镇', '450041', '14645435785', '14645435785', '145535142', '14645435785@163.com', '男', '群众', '2014-04-19 00:00:00', '汉族', '本科', '演讲', '吃饭、睡觉、打豆豆', '', '2020-07-19 01:26:55');
INSERT INTO `employee` VALUES (20, 3, 5, '小米12', '360102199003078248', '安徽省合肥市瑶海区', '450041', '15765435385', '15765435385', '513535132', '513535132@qq.com', '男', '团员', '2020-07-19 00:00:00', '汉族', '本科', '演讲', '吃饭、睡觉、打豆豆', '', '2020-07-19 10:33:25');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `jid` int(11) NOT NULL,
  `jname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `jremark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`jid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES (1, '前端开发工程师', 'jremark1');
INSERT INTO `job` VALUES (2, '后端开发工程师', 'jremark2');
INSERT INTO `job` VALUES (3, '数据挖掘工程师', 'jremark3');
INSERT INTO `job` VALUES (4, '产品测试工程师', 'jremark4');
INSERT INTO `job` VALUES (5, '云计算工程师', '');
INSERT INTO `job` VALUES (6, '项目经理', 'jremark6');
INSERT INTO `job` VALUES (7, '数据分析工程师', 'jremark7');
INSERT INTO `job` VALUES (8, '总裁', '我1');
INSERT INTO `job` VALUES (12, '销售经理', '');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_notice`(`user_num`) USING BTREE,
  CONSTRAINT `user_notice` FOREIGN KEY (`user_num`) REFERENCES `user` (`number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '小蔡', '100', '通知1', '晋升', '通知小蔡通知晋升组长', 'remark1');
INSERT INTO `notice` VALUES (2, '小林', '101', '通知2', '晋升', '通知小林通知晋升副组长', 'remark2');
INSERT INTO `notice` VALUES (3, '杰尼龟', '111', '通知开会', '开会', '通知全体人员今天下午2点开会！', '');
INSERT INTO `notice` VALUES (4, '小圆', '110', '部门成立', '部门成立', '通知成立新部门', '无');
INSERT INTO `notice` VALUES (5, '小伍', '114', '通知5', '开会', '通知早上9点开会', '111');
INSERT INTO `notice` VALUES (6, '小蔡', '100', '通知6', '出差', '通知早上10点出差', '233');
INSERT INTO `notice` VALUES (7, '小林', '101', '加班', '加班', '今晚加班，一个都不许走', '232');
INSERT INTO `notice` VALUES (8, '小霖', '107', '团建', '开展团建活动', '这个周末团建搞起来', '111');
INSERT INTO `notice` VALUES (9, '小刘', '109', '运动会', '运动会举办', '具备公司内部运动会', '221');
INSERT INTO `notice` VALUES (10, '盖瓦', '105', '年会', '举办年会', '组织年会的活动', '213');
INSERT INTO `notice` VALUES (11, '小智', '103', '庆典', '开展庆典', '庆典的组织事宜', '121');
INSERT INTO `notice` VALUES (12, '李华', '104', '旅游', '集体旅游', '到巴黎旅游', '1231');
INSERT INTO `notice` VALUES (20, '小智', '103', '开会', '通知开会', '开会', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` int(11) NOT NULL,
  `rname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rremark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '普通用户', 'rremark');
INSERT INTO `role` VALUES (2, '管理员', 'rremark');
INSERT INTO `role` VALUES (3, '超级管理员', 'rremark');
INSERT INTO `role` VALUES (4, '总管', NULL);
INSERT INTO `role` VALUES (5, '大总管', NULL);
INSERT INTO `role` VALUES (9, '哒哒哒', '');

-- ----------------------------
-- Table structure for uploadfile
-- ----------------------------
DROP TABLE IF EXISTS `uploadfile`;
CREATE TABLE `uploadfile`  (
  `id` int(11) NOT NULL,
  `user_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uploadName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fileName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uploadDate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_uploadfile`(`user_num`) USING BTREE,
  CONSTRAINT `user_uploadfile` FOREIGN KEY (`user_num`) REFERENCES `user` (`number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of uploadfile
-- ----------------------------
INSERT INTO `uploadfile` VALUES (1, '102', '皮卡丘', '皮卡丘.jpg', '2020-07-18 16:36:36');
INSERT INTO `uploadfile` VALUES (2, '102', '皮卡丘', '猫狗.jpg', '2020-07-18 16:36:40');
INSERT INTO `uploadfile` VALUES (3, '102', '皮卡丘', '参考.jpg', '2020-07-18 16:36:50');
INSERT INTO `uploadfile` VALUES (4, '102', '皮卡丘', '微信图片_20191129230117.jpg', '2020-07-18 16:36:54');
INSERT INTO `uploadfile` VALUES (5, '111', '杰尼龟', '大作业模板.docx', '2020-07-19 01:28:14');
INSERT INTO `uploadfile` VALUES (6, '111', '杰尼龟', '《高级编程实训》项目报告撰写提纲（2020）.doc', '2020-07-19 01:28:44');
INSERT INTO `uploadfile` VALUES (7, '111', '杰尼龟', '123.txt', '2020-07-19 01:29:23');
INSERT INTO `uploadfile` VALUES (8, '111', '杰尼龟', '我是一个测试文档.doc', '2020-07-19 01:30:43');
INSERT INTO `uploadfile` VALUES (10, '111', '杰尼龟', '微信图片_20191129230156.jpg', '2020-07-19 01:35:39');
INSERT INTO `uploadfile` VALUES (11, '103', '小智', '123.txt', '2020-07-19 10:36:32');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` int(11) NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createdate` datetime(0) NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE,
  INDEX `role_user`(`role_id`) USING BTREE,
  CONSTRAINT `role_user` FOREIGN KEY (`role_id`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('100', '123456', 3, '13068941743', '2020-06-22 14:01:00', '小蔡', 'remark');
INSERT INTO `user` VALUES ('101', '654321', 2, '13846377327', '2020-06-22 14:08:00', '小林', 'remark1');
INSERT INTO `user` VALUES ('102', '123123123', 3, '13995442572', '2020-07-13 14:17:11', '皮卡丘123', 'remark213');
INSERT INTO `user` VALUES ('103', '123123', 1, '15674432465', '2020-07-14 00:41:55', '小智', 'remark321');
INSERT INTO `user` VALUES ('104', '222233', 5, '15644322543', '2020-07-14 09:42:29', '李华', 'remark4');
INSERT INTO `user` VALUES ('105', '323232', 1, '16443322322', '2020-07-14 11:43:26', '盖瓦', 'remark5');
INSERT INTO `user` VALUES ('107', '888888', 4, '16754747472', '2020-07-14 10:45:27', '小霖', '123');
INSERT INTO `user` VALUES ('109', '223332', 2, '14545224242', '2020-07-15 08:46:40', '小刘', '222');
INSERT INTO `user` VALUES ('110', '123213', 1, '19485352422', '2020-07-15 11:47:13', '小圆', '311');
INSERT INTO `user` VALUES ('111', '123123', 5, '14543525252', '2020-07-15 07:47:40', '杰尼龟', '杰尼杰尼');
INSERT INTO `user` VALUES ('113', '232323', 3, '14654636355', '2020-07-15 10:49:11', '小风', 'remark13');
INSERT INTO `user` VALUES ('114', '141241', 2, '14464635223', '2020-07-15 15:49:48', '小伍', '你好');
INSERT INTO `user` VALUES ('115', '123124', 5, '13553522411', '2020-07-15 09:50:37', '吴侠', '666');
INSERT INTO `user` VALUES ('120', '123123', 1, '13785437554', '2020-07-19 10:32:17', '小米111', '');

SET FOREIGN_KEY_CHECKS = 1;
