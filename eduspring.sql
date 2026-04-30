/*
 Navicat Premium Dump SQL

 Source Server         : Service
 Source Server Type    : MySQL
 Source Server Version : 80408 (8.4.8)
 Source Host           : 112.124.106.114:3306
 Source Schema         : eduspring

 Target Server Type    : MySQL
 Target Server Version : 80408 (8.4.8)
 File Encoding         : 65001

 Date: 27/04/2026 16:28:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `biz_id` bigint NULL DEFAULT NULL COMMENT '业务ID',
  `course_id` bigint NOT NULL COMMENT '课程ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动标题',
  `type` int NOT NULL COMMENT '活动类型：1作业 2考试 3讨论 4签到',
  `status` int NULL DEFAULT 1 COMMENT '状态：0关闭 1发布',
  `score` int NULL DEFAULT NULL COMMENT '活动分数',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '活动主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (36, NULL, 2, '课堂调研', 3, 1, 2, '2026-04-17 15:46:11', '2026-04-17 15:46:07', '2026-04-30 00:00:00');
INSERT INTO `activity` VALUES (38, 16, 2, '第一次考试', 2, 1, 10, '2026-04-17 15:53:43', '2026-04-17 15:53:39', '2026-04-25 00:00:00');
INSERT INTO `activity` VALUES (39, NULL, 2, '简单讲讲', 1, 1, 20, '2026-04-23 16:21:10', '2026-04-23 16:21:06', '2026-04-30 00:00:00');

-- ----------------------------
-- Table structure for activity_discussion_record
-- ----------------------------
DROP TABLE IF EXISTS `activity_discussion_record`;
CREATE TABLE `activity_discussion_record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `activity_id` int NULL DEFAULT NULL COMMENT '活动id',
  `student_id` int NULL DEFAULT NULL COMMENT '学生id',
  `submit_time` datetime NULL DEFAULT NULL COMMENT '提交时间',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '提交内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '活动讨论详情' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of activity_discussion_record
-- ----------------------------
INSERT INTO `activity_discussion_record` VALUES (17, 36, 114, '2026-04-18 23:18:32', '123');
INSERT INTO `activity_discussion_record` VALUES (18, 36, 12, '2026-04-18 23:41:56', '321');
INSERT INTO `activity_discussion_record` VALUES (19, 36, 5, '2026-04-19 16:15:35', '123456');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程ID，唯一标识每一门课程',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '课程详细介绍信息',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程封面图片地址',
  `teacher_id` int NULL DEFAULT NULL COMMENT '老师id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '课程创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `title`(`title` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程表，存储平台课程信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (2, 'SpringBoot快速入门', 'SpringBoot企业级开发课程', 'springboot.jpg', 2, '2026-03-10 15:42:04');
INSERT INTO `course` VALUES (3, 'Vue前端开发', 'Vue3前端开发实战', 'vue.jpg', 3, '2026-03-10 15:42:04');
INSERT INTO `course` VALUES (4, 'MySQL数据库', 'MySQL数据库从入门到高级', 'mysql.jpg', 4, '2026-03-10 15:42:04');
INSERT INTO `course` VALUES (5, 'JavaWeb开发', 'JavaWeb项目开发实战', 'javaweb.jpg', 2, '2026-03-10 15:42:04');
INSERT INTO `course` VALUES (6, 'html', '2222', '123.jpg', 3, '2026-03-26 19:27:49');
INSERT INTO `course` VALUES (7, 'JWT', '令牌验证', '1568.jsp', 4, '2026-03-13 21:24:59');
INSERT INTO `course` VALUES (8, 'Java基础入门', '从零开始学习Java编程基础', '/course/java1.jpg', 2, '2026-03-14 18:20:20');
INSERT INTO `course` VALUES (9, 'Java Web开发', '学习Servlet、JSP和Web开发', '/course/java2.jpg', 3, '2026-03-14 18:20:20');
INSERT INTO `course` VALUES (10, 'SpringBoot实战', 'SpringBoot企业级开发教程', '/course/java3.jpg', 4, '2026-03-14 18:20:20');

-- ----------------------------
-- Table structure for course_resource
-- ----------------------------
DROP TABLE IF EXISTS `course_resource`;
CREATE TABLE `course_resource`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '资源ID，主键',
  `course_id` bigint NOT NULL COMMENT '所属课程ID，外键关联course表',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名称（文件名或标题）',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源存储路径（本地或云存储URL）',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源类型（video/doc/pdf）',
  `size` bigint NULL DEFAULT 0 COMMENT '文件大小，单位字节',
  `duration` bigint NULL DEFAULT NULL COMMENT '视频时长，单位秒，文档可为空',
  `upload_user` bigint NOT NULL COMMENT '上传者用户ID，外键关联user表',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id` ASC) USING BTREE,
  INDEX `idx_upload_user`(`upload_user` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '教学资源表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_resource
-- ----------------------------
INSERT INTO `course_resource` VALUES (12, 5, 'cs', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/a650430e1dc146dba0cc4ec8789447bc.mp4', 'video', 277008918, 56, 1, '2026-03-22 18:45:46');
INSERT INTO `course_resource` VALUES (13, 2, '1_导学', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/2e05f1966989476885f608ca813d0f7c.mp4', 'video', 5276864, 440, 1, '2026-03-22 22:21:19');
INSERT INTO `course_resource` VALUES (14, 2, '2_今日内容', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/6f6362b0b8404308ac847a061b3ab040.mp4', 'video', 2974455, 164, 2, '2026-03-25 16:13:30');
INSERT INTO `course_resource` VALUES (15, 2, '3_SpringBoot概述', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/97cf0add1266458db2dad6fe65dc5f75.mp4', 'video', 9388499, 820, 2, '2026-03-25 16:14:14');
INSERT INTO `course_resource` VALUES (16, 2, '4_SpringBoot快速入门', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/25ed4b9ed18c4c8cad1b8b64b59120f4.mp4', 'video', 11963674, 643, 2, '2026-03-25 16:15:03');
INSERT INTO `course_resource` VALUES (17, 2, '5_快速构建SpringBoot工程', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/5e04bc622a16448fb8efc772170548cc.mp4', 'video', 9814679, 514, 2, '2026-03-25 16:26:05');
INSERT INTO `course_resource` VALUES (18, 2, '6_SpringBoot起步依赖原理分析', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/32852f193a274311b6701ed4d015488a.mp4', 'video', 10241341, 396, 2, '2026-03-25 16:26:54');
INSERT INTO `course_resource` VALUES (19, 2, '7_SpringBoot配置-配置文件分类', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/0d85da915ef6482ea72d1d159a33b801.mp4', 'video', 10972889, 619, 2, '2026-03-25 16:27:15');
INSERT INTO `course_resource` VALUES (20, 2, '8_SpringBoot配置-yaml基本语法', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/ded7529ae2be46e99495e8f9b1722d57.mp4', 'video', 4283514, 364, 2, '2026-03-25 16:27:34');
INSERT INTO `course_resource` VALUES (21, 2, '9_SpringBoot配置-yaml数据格式', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/84e3a738b1e542b09cf447aaee0c1633.mp4', 'video', 12062261, 710, 2, '2026-03-25 16:27:52');
INSERT INTO `course_resource` VALUES (22, 2, '10_SpringBoot配置-获取数据', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/47eb733fd8684e6fba871e2a01fc619d.mp4', 'video', 12121822, 573, 2, '2026-03-25 16:28:09');
INSERT INTO `course_resource` VALUES (23, 2, '11_SpringBoot配置-获取数据2', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/b1dcebe0cc4b4be597747794e7447a82.mp4', 'video', 12627326, 629, 2, '2026-03-25 16:28:36');
INSERT INTO `course_resource` VALUES (24, 2, '12_SpringBoot配置-profile', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/dfa93eeb32994eb6be733d253f05f1ce.mp4', 'video', 22524397, 1062, 2, '2026-03-25 16:28:57');
INSERT INTO `course_resource` VALUES (25, 2, '13_SpringBoot配置-项目内部配置文件加载顺序', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/c3a67ab2c8364f2ebe441300cd62ae9e.mp4', 'video', 10733972, 575, 2, '2026-03-25 16:29:13');
INSERT INTO `course_resource` VALUES (26, 2, '14_SpringBoot配置-项目外部配置加载顺序', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/1821e6e60b95411b97c71fb9a77b1d25.mp4', 'video', 19018299, 724, 2, '2026-03-25 16:29:44');
INSERT INTO `course_resource` VALUES (27, 2, '15_SpringBoot整合Junit', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/28ed5582db6c4c91afad400af617b4ce.mp4', 'video', 9938076, 504, 2, '2026-03-25 16:30:07');
INSERT INTO `course_resource` VALUES (28, 2, '16_SpringBoot整合redis', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/8cfaa0741f8246938107d78672bee8dc.mp4', 'video', 9950839, 457, 2, '2026-03-25 16:30:26');
INSERT INTO `course_resource` VALUES (29, 2, '17_SpringBoot整合mybatis', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/e8936b03d8a449d5a84f4ff7f27d9e15.mp4', 'video', 29678440, 1064, 2, '2026-03-25 16:30:55');
INSERT INTO `course_resource` VALUES (30, 2, '18_SpringBoot高级-今日内容', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/cfb6aa79ea16424c892b63c843092d35.mp4', 'video', 1282957, 81, 2, '2026-03-25 16:31:10');
INSERT INTO `course_resource` VALUES (31, 2, '19_SpringBoot自动配置-Condition', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/9cfd3093b8364c1fa30760d513d5909e.mp4', 'video', 20676500, 996, 2, '2026-03-25 16:31:30');
INSERT INTO `course_resource` VALUES (32, 2, '20_SpringBoot自动配置-Condition-2', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/4ff903c637da47a89208b45f09715234.mp4', 'video', 25161973, 1091, 2, '2026-03-25 16:31:55');
INSERT INTO `course_resource` VALUES (33, 2, '21_SpringBoot自动配置-切换内置web服务器', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/5ec8cef46bac46f3b01ab8f21b56dfd7.mp4', 'video', 10082412, 422, 2, '2026-03-25 16:32:09');
INSERT INTO `course_resource` VALUES (34, 2, '22_SpringBoot自动配置-Enable注解原理', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/cf9a27a37bc34a8c96fb3692bbc13e69.mp4', 'video', 17572615, 808, 2, '2026-03-25 16:32:27');
INSERT INTO `course_resource` VALUES (35, 2, '23_SpringBoot自动配置-@Import详解', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/4aadbd4608954b93960c2ecf96c02f0a.mp4', 'video', 19453966, 833, 2, '2026-03-25 16:32:45');
INSERT INTO `course_resource` VALUES (36, 2, '24_SpringBoot自动配置-@EnableAutoConfiguration详解', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/83b1ae3ed8164f4ab8390e94193b968a.mp4', 'video', 10407437, 416, 2, '2026-03-25 16:33:03');
INSERT INTO `course_resource` VALUES (37, 2, '25_SpringBoot自动配置-自定义starter步骤分析', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/3069a487eb954f2d9d6c7c52e2b8b93d.mp4', 'video', 8532534, 416, 2, '2026-03-25 16:33:20');
INSERT INTO `course_resource` VALUES (38, 2, '26_SpringBoot自动配置-自定义starter实现-1', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/32cc560207974f1b83a11401877efb58.mp4', 'video', 15396659, 698, 2, '2026-03-25 16:33:36');
INSERT INTO `course_resource` VALUES (39, 2, '27_SpringBoot自动配置-自定义starter实现-2', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/4b4f2b6b6f2d44ed98539e6d83702ca5.mp4', 'video', 9976388, 429, 2, '2026-03-25 16:34:04');
INSERT INTO `course_resource` VALUES (40, 2, '28_SpringBoot事件监听', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/8251abcfb74a4f459aa3a8f23babb22b.mp4', 'video', 31488921, 1437, 2, '2026-03-25 16:34:25');
INSERT INTO `course_resource` VALUES (41, 2, '29_SpringBoot流程分析-初始化', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/c47c5c9fa69d462aa2d2d2b3005a3c4d.mp4', 'video', 14283850, 496, 2, '2026-03-25 16:34:41');
INSERT INTO `course_resource` VALUES (42, 2, '30_SpringBoot流程分析-run', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/de94144fd1bb47d5838d01fbb60a06c9.mp4', 'video', 21037526, 758, 2, '2026-03-25 16:35:01');
INSERT INTO `course_resource` VALUES (43, 2, '31_SpringBoot监控-actuator基本使用', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/c5b1fa4e031b4173a7c6385f3f931d6a.mp4', 'video', 9639118, 520, 2, '2026-03-25 16:35:19');
INSERT INTO `course_resource` VALUES (44, 2, '32_SpringBoot监控-actuator开启所有endpoint', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/505840e6d99748d9b454b9c15594e941.mp4', 'video', 11198034, 372, 2, '2026-03-25 16:35:35');
INSERT INTO `course_resource` VALUES (45, 2, '33_SpringBoot监控-springboot admin图形化界面使用', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/1863b155003542b4afa0db85ce8066f1.mp4', 'video', 18214191, 915, 2, '2026-03-25 16:35:56');
INSERT INTO `course_resource` VALUES (46, 2, '34_SpringBoot部署', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/778f89888b6641eab3b068ed10c5a349.mp4', 'video', 11608129, 732, 2, '2026-03-25 16:36:13');
INSERT INTO `course_resource` VALUES (47, 8, '第1章-入门篇-01-初始Java', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/837be12a0bb0469b80d94cbb80fdeb21.mp4', 'video', 8928586, 396, 2, '2026-04-07 14:22:09');
INSERT INTO `course_resource` VALUES (48, 8, '第1章-入门篇-02-JDK的下载和安装', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/fbbb6b90cfe44c58b35bcf363bb9334d.mp4', 'video', 6619010, 438, 2, '2026-04-07 14:28:51');
INSERT INTO `course_resource` VALUES (49, 8, '第1章-入门篇-03-环境变量', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/c289a36a3e414d76a6e98ef98cc2223a.mp4', 'video', 7159433, 518, 2, '2026-04-07 14:29:09');
INSERT INTO `course_resource` VALUES (50, 8, '第1章-入门篇-04-JDK配置到环境变量', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/038d85f60f5a4660bae4de82560945a1.mp4', 'video', 4069515, 289, 2, '2026-04-07 14:30:59');
INSERT INTO `course_resource` VALUES (51, 8, '第1章-入门篇-05-IDEA的下载和安装', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/de097796cd5c438283f21a01af324b37.mp4', 'video', 3724784, 269, 2, '2026-04-07 14:31:13');
INSERT INTO `course_resource` VALUES (52, 8, '第1章-入门篇-06-HelloWorld', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/a2210c47233c4b5ab587bb9b2b4e2ed1.mp4', 'video', 8088287, 656, 2, '2026-04-07 14:31:29');
INSERT INTO `course_resource` VALUES (53, 8, '第1章-入门篇-07-IDEA的基本设置和AI插件安装', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/fceb6f09a200454b99f6033b3cf10d00.mp4', 'video', 5135692, 376, 2, '2026-04-07 14:31:50');
INSERT INTO `course_resource` VALUES (54, 8, '第1章-入门篇-08-注释', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/4b97a7a98f714fa2bd1a24d47f723cf5.mp4', 'video', 7070020, 534, 2, '2026-04-07 14:32:02');
INSERT INTO `course_resource` VALUES (55, 8, '第1章-入门篇-09-解释HelloWorld-关键字', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/cda0b7f973564d0680d36e620f9a745b.mp4', 'video', 6239880, 426, 2, '2026-04-07 14:32:25');
INSERT INTO `course_resource` VALUES (56, 8, '第1章-入门篇-10-AI时代下正确的学习方法', 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/videos/10d07829a92c437092d6b0ba103909d9.mp4', 'video', 12843690, 779, 2, '2026-04-07 14:33:07');

-- ----------------------------
-- Table structure for discussion_comment
-- ----------------------------
DROP TABLE IF EXISTS `discussion_comment`;
CREATE TABLE `discussion_comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `post_id` int NOT NULL COMMENT '帖子ID',
  `user_id` int NOT NULL COMMENT '评论用户ID',
  `parent_id` int NULL DEFAULT 0 COMMENT '父评论ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `like_count` int NULL DEFAULT 0 COMMENT '点赞数',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 92 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '讨论区评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of discussion_comment
-- ----------------------------
INSERT INTO `discussion_comment` VALUES (1, 1, 2, 0, '可能是token没有带', 3, '2026-03-11 16:04:22');
INSERT INTO `discussion_comment` VALUES (2, 1, 3, 1, '对，需要检查请求头', 0, '2026-03-11 16:04:26');
INSERT INTO `discussion_comment` VALUES (3, 2, 1, 0, '需要配置跨域Cors', 3, '2026-03-11 16:05:15');
INSERT INTO `discussion_comment` VALUES (4, 3, 2, 0, '检查数据库用户名密码', 1, '2026-03-11 16:05:15');
INSERT INTO `discussion_comment` VALUES (5, 4, 3, 0, '可以使用CDN加速视频', 2, '2026-03-11 16:05:15');
INSERT INTO `discussion_comment` VALUES (6, 5, 2, 0, '可以部署到Linux服务器', 3, '2026-03-11 16:05:15');
INSERT INTO `discussion_comment` VALUES (7, 1, 5, 0, '我也有这个问题，希望老师讲解一下', 6, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (8, 1, 6, 1, '可以看看官方文档，解释得很清楚', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (9, 2, 7, 0, 'SpringBoot启动失败一般是配置问题', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (10, 2, 8, 3, '我之前是端口冲突导致的', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (11, 3, 9, 0, '这门课程确实讲得很好', 6, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (12, 3, 12, 5, '是的，老师讲课很清晰', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (13, 4, 13, 0, '索引一般在查询频繁的字段使用', 5, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (14, 4, 14, 7, '对，而且要避免过多索引', 1, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (15, 5, 16, 0, 'Vue组件通信可以用props和emit', 7, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (16, 5, 87, 9, '还可以使用Vuex管理状态', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (17, 6, 88, 0, 'Servlet建议多写几个小项目练习', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (18, 6, 89, 11, '可以结合SpringBoot一起学习', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (19, 7, 90, 0, '项目结构建议模块化', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (20, 7, 91, 13, '组件和页面分开会更清晰', 1, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (21, 8, 92, 0, 'section语义化更强', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (22, 8, 93, 15, 'div主要是布局容器', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (23, 9, 94, 0, 'async/await写起来更简洁', 5, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (24, 9, 95, 17, '底层其实还是Promise', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (25, 10, 96, 0, 'IOC就是控制反转', 5, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (26, 10, 97, 19, '简单说就是对象由容器管理', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (27, 11, 98, 0, '拦截器主要是SpringMVC用', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (28, 11, 99, 21, '过滤器是Servlet规范的', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (29, 12, 100, 0, '缓存穿透可以用布隆过滤器', 5, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (30, 12, 101, 23, '或者缓存空对象', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (31, 13, 102, 0, 'Linux命令建议多练习', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (32, 13, 103, 25, 'ls、cd、pwd这些最常用', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (33, 14, 104, 0, 'Docker镜像慢可以换国内源', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (34, 14, 105, 27, '阿里云镜像速度很快', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (35, 15, 106, 0, 'Git建议使用git flow', 5, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (36, 15, 107, 29, '团队开发非常方便', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (37, 16, 108, 0, 'Webpack优化可以用缓存', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (38, 16, 109, 31, '还可以减少不必要插件', 1, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (39, 17, 110, 0, 'TS主要是增加类型系统', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (40, 17, 111, 33, '大型项目很有帮助', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (41, 18, 112, 0, 'Python确实适合新手', 6, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (42, 18, 113, 35, '语法简单易学', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (43, 19, 114, 0, '数据分析推荐pandas', 5, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (44, 19, 115, 37, 'matplotlib也很好用', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (45, 20, 116, 0, 'AI入门建议先学Python', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (46, 20, 87, 39, '再学习机器学习基础', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (47, 21, 88, 0, '常见算法有决策树和SVM', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (48, 21, 89, 41, '还有随机森林', 1, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (49, 22, 90, 0, '神经网络其实是多层结构', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (50, 22, 91, 43, '每层有很多神经元', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (51, 23, 92, 0, '算法一定要多刷题', 5, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (52, 23, 93, 45, 'LeetCode很不错', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (53, 24, 94, 0, '进程是资源分配单位', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (54, 24, 95, 47, '线程是调度单位', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (55, 25, 96, 0, 'TCP可靠 UDP速度快', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (56, 25, 97, 49, '使用场景不同', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (57, 26, 98, 0, '常见设计模式有单例', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (58, 26, 99, 51, '还有工厂模式', 1, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (59, 27, 100, 0, '开发框架推荐SpringBoot', 5, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (60, 27, 101, 53, '开发效率很高', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (61, 28, 102, 0, '电商系统需要订单模块', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (62, 28, 103, 55, '还需要支付模块', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (63, 29, 104, 0, '博客系统需要评论功能', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (64, 29, 105, 57, '还有文章分类', 1, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (65, 30, 106, 0, '软件开发流程一般是需求->设计->开发', 5, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (66, 30, 107, 59, '最后是测试上线', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (67, 31, 108, 0, '课程确实有点难', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (68, 31, 109, 61, '多做练习就好了', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (69, 32, 110, 0, '推荐B站学习资料', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (70, 32, 111, 63, '很多免费课程', 1, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (71, 33, 112, 0, '代码规范非常重要', 4, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (72, 33, 113, 65, '团队开发必须统一', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (73, 34, 114, 0, 'SpringBoot部署可以用Docker', 5, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (74, 34, 115, 67, '或者直接jar运行', 2, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (75, 35, 116, 0, '服务器配置看访问量', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (76, 35, 87, 69, '一般4核8G就够', 1, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (77, 36, 88, 0, '毕业设计建议做管理系统', 6, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (78, 36, 89, 71, '比如在线教育系统', 3, '2026-03-14 18:38:04');
INSERT INTO `discussion_comment` VALUES (79, 1, 1, 0, '大大', 1, '2026-03-21 17:12:05');
INSERT INTO `discussion_comment` VALUES (82, 1, 1, 79, '是吗', 0, '2026-03-21 18:51:00');
INSERT INTO `discussion_comment` VALUES (83, 1, 1, 8, '在哪', 0, '2026-03-21 18:51:21');
INSERT INTO `discussion_comment` VALUES (84, 2, 1, 9, 'yes', 0, '2026-03-21 19:05:55');
INSERT INTO `discussion_comment` VALUES (85, 1, 2, 2, '这个同学回答的非常正确', 0, '2026-03-22 14:48:59');
INSERT INTO `discussion_comment` VALUES (86, 1, 2, 0, '检查接口是否一致', 0, '2026-04-20 15:22:39');
INSERT INTO `discussion_comment` VALUES (91, 48, 119, 0, '<svg><h1/a=\"\'>你很优秀', 0, '2026-04-25 08:45:57');

-- ----------------------------
-- Table structure for discussion_post
-- ----------------------------
DROP TABLE IF EXISTS `discussion_post`;
CREATE TABLE `discussion_post`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `user_id` int NOT NULL COMMENT '发布用户ID',
  `course_id` int NULL DEFAULT NULL COMMENT '课程ID(课程讨论区)',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '帖子标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '帖子内容',
  `like_count` int NULL DEFAULT 0 COMMENT '点赞数',
  `is_top` int NULL DEFAULT 0 COMMENT '是否置顶(0否1是)',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '讨论区帖子表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of discussion_post
-- ----------------------------
INSERT INTO `discussion_post` VALUES (1, 5, 2, 'SpringBoot登录问题', '为什么我的接口返回401？', 1, 0, '2026-03-11 16:04:18');
INSERT INTO `discussion_post` VALUES (2, 6, 2, 'Vue前端请求不到接口', '前端axios请求后端一直报跨域', 3, 0, '2026-03-11 16:04:47');
INSERT INTO `discussion_post` VALUES (3, 7, 2, 'MySQL连接失败', '数据库连接一直报错 Communications link failure', 1, 0, '2026-03-11 16:04:47');
INSERT INTO `discussion_post` VALUES (4, 5, 2, '课程视频加载很慢', '视频加载速度很慢，有什么优化方法吗？', 4, 0, '2026-03-11 16:04:47');
INSERT INTO `discussion_post` VALUES (5, 9, 3, '如何部署SpringBoot项目', '项目开发完成后应该如何部署到服务器？', 6, 0, '2026-03-11 16:04:47');
INSERT INTO `discussion_post` VALUES (7, 5, 2, 'Java基础学习问题', '请问Java中的面向对象应该如何理解？', 12, 1, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (8, 6, 3, 'SpringBoot启动失败', '我的SpringBoot项目启动时报错，有人遇到过吗？', 8, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (9, 7, 4, '课程内容很好', '老师讲得非常详细，很容易理解。', 15, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (10, 8, 5, '数据库索引问题', 'MySQL什么时候需要建立索引？', 7, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (11, 9, 6, 'Vue组件通信', 'Vue父子组件通信有哪些方式？', 10, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (12, 12, 7, 'Java Web学习建议', '大家是怎么学习Servlet的？', 5, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (13, 13, 8, '前端项目结构', 'Vue项目结构如何规划比较好？', 9, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (14, 14, 9, 'HTML标签问题', 'section标签和div有什么区别？', 4, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (15, 16, 10, 'JavaScript异步', 'Promise和async/await有什么区别？', 11, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (16, 87, 2, 'Spring IOC理解', 'IOC容器具体是怎么工作的？', 13, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (17, 88, 2, 'SpringMVC拦截器', '拦截器和过滤器有什么区别？', 7, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (18, 89, 2, 'Redis缓存策略', '缓存穿透如何解决？', 16, 1, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (19, 90, 3, 'Linux命令学习', '有哪些常用Linux命令推荐？', 5, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (20, 91, 2, 'Docker镜像问题', 'Docker镜像构建速度很慢怎么办？', 8, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (21, 92, 5, 'Git分支管理', '团队开发如何管理Git分支？', 9, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (22, 93, 6, 'Webpack打包', 'Webpack打包速度慢如何优化？', 6, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (23, 94, 7, 'TypeScript优势', 'TS相比JS优势在哪里？', 10, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (24, 95, 8, 'Python学习路线', 'Python适合新手学习吗？', 14, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (25, 96, 2, '数据分析工具', '大家用什么Python数据分析库？', 7, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (26, 97, 5, '人工智能入门', 'AI入门应该从什么开始？', 18, 1, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (27, 98, 6, '机器学习算法', '最常用的机器学习算法有哪些？', 9, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (28, 99, 3, '神经网络理解', '神经网络结构如何理解？', 6, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (29, 100, 2, '算法学习建议', '数据结构和算法如何系统学习？', 11, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (30, 101, 5, '操作系统进程', '进程和线程区别是什么？', 8, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (31, 102, 4, 'TCP和UDP', 'TCP和UDP主要区别有哪些？', 7, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (32, 103, 9, '设计模式问题', '常见设计模式有哪些？', 12, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (33, 104, 5, '系统开发经验', '大家做项目一般用什么框架？', 6, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (34, 105, 2, '电商系统设计', '电商系统数据库怎么设计？', 13, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (35, 106, 10, '博客系统功能', '博客系统一般需要哪些功能？', 5, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (36, 107, 2, '项目开发流程', '软件开发流程是怎样的？', 9, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (37, 108, 3, '课程难度讨论', '这门课程感觉有点难，大家觉得呢？', 4, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (38, 109, 6, '学习资料分享', '有没有推荐的学习资料？', 10, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (39, 110, 2, '代码规范', '团队开发代码规范重要吗？', 7, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (40, 111, 2, '系统部署问题', 'SpringBoot项目如何部署？', 8, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (41, 112, 2, '服务器配置', '服务器配置一般需要多大？', 6, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (42, 113, 2, '毕业设计经验', '大家毕业设计做什么题目？', 15, 1, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (43, 114, 2, 'Java学习心得', '分享一下我的Java学习经验', 11, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (44, 115, 3, 'SpringBoot学习路线', '新手学习SpringBoot有什么建议？', 9, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (45, 116, 4, '编程入门', '零基础如何开始学编程？', 12, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (46, 87, 5, '数据库优化', '数据库性能优化有哪些方法？', 14, 0, '2026-03-14 18:32:09');
INSERT INTO `discussion_post` VALUES (47, 1, 3, '00000', '00000000', 0, 0, '2026-03-19 20:48:05');
INSERT INTO `discussion_post` VALUES (48, 119, 6, '学习', '</textarea><script>alert(1)</script>', 0, 0, '2026-04-25 08:44:31');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '考试ID',
  `paper_id` bigint NOT NULL COMMENT '试卷ID',
  `duration` int NULL DEFAULT 60 COMMENT '考试时长（分钟）',
  `total_score` int NULL DEFAULT 100 COMMENT '考试总分',
  `allow_retake` int NULL DEFAULT 0 COMMENT '是否允许重考：0否 1是',
  `max_attempt` int NULL DEFAULT 1 COMMENT '最大考试次数',
  `show_result` int NULL DEFAULT 1 COMMENT '是否立即显示成绩：0否 1是',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '考试表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (16, 37, 60, 10, 1, 3, 1, '2026-04-17 15:53:43');

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '提交记录ID',
  `activity_id` bigint NOT NULL COMMENT '作业活动ID',
  `course_id` bigint NOT NULL COMMENT '课程ID',
  `student_id` bigint NOT NULL COMMENT '学生ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '文字答案',
  `submit_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学生作业提交表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES (1, 39, 2, 12, '真的假的？？？', '2026-04-23 19:41:01');

-- ----------------------------
-- Table structure for paper_question
-- ----------------------------
DROP TABLE IF EXISTS `paper_question`;
CREATE TABLE `paper_question`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `paper_id` bigint NOT NULL COMMENT '试卷ID',
  `question_id` bigint NOT NULL COMMENT '题目ID',
  `score` double NULL DEFAULT NULL COMMENT '该题在本试卷中的分值',
  `sort` int NULL DEFAULT 0 COMMENT '题目顺序',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_paper_id`(`paper_id` ASC) USING BTREE,
  INDEX `idx_question_id`(`question_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '试卷题目关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of paper_question
-- ----------------------------
INSERT INTO `paper_question` VALUES (97, 37, 9, 2, 1);
INSERT INTO `paper_question` VALUES (98, 37, 10, 2, 2);
INSERT INTO `paper_question` VALUES (99, 37, 135, 3, 3);
INSERT INTO `paper_question` VALUES (100, 37, 143, 1, 4);
INSERT INTO `paper_question` VALUES (101, 37, 14, 1, 5);
INSERT INTO `paper_question` VALUES (102, 37, 4, 1, 6);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  `type` int NOT NULL COMMENT '题目类型：1单选 2多选 3判断',
  `course_id` bigint NOT NULL COMMENT '课程ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题目内容',
  `option_a` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项A',
  `option_b` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项B',
  `option_c` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项C',
  `option_d` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项D',
  `correct_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '正确答案（如 A / A,B / 1为正确，0为错误）',
  `analysis` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '答案解析',
  `score` int NULL DEFAULT 5 COMMENT '题目分值',
  `difficulty` int NULL DEFAULT 1 COMMENT '难度：1简单 2中等 3困难',
  `create_user` int NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 1, 2, 'Java中哪个关键字用于继承类？', 'this', 'super', 'extends', 'implements', 'C', 'extends用于类继承', 2, 2, 2, '2026-03-24 21:38:51', '2026-04-07 17:18:12');
INSERT INTO `question` VALUES (2, 1, 2, '以下哪个是Java基本数据类型？', 'String', 'Integer', 'int', 'List', 'C', 'int是基本类型', 2, 1, 2, '2026-03-24 21:38:51', '2026-04-07 17:18:12');
INSERT INTO `question` VALUES (3, 2, 2, '以下哪些属于Java集合框架？', 'List', 'Map', 'Set', 'Array', 'A,B,C', 'List/Map/Set都是集合接口', 3, 2, 2, '2026-03-24 21:38:51', '2026-04-07 17:18:13');
INSERT INTO `question` VALUES (4, 3, 2, 'Java支持多继承', '正确', '错误', NULL, NULL, '0', 'Java不支持类多继承', 1, 1, 2, '2026-03-24 21:38:51', '2026-04-07 17:56:55');
INSERT INTO `question` VALUES (6, 1, 2, 'Java中哪个是基本数据类型？', 'String', 'int', 'Integer', 'Object', 'B', NULL, 2, 1, 2, '2026-03-26 22:10:23', '2026-04-07 17:18:16');
INSERT INTO `question` VALUES (7, 2, 2, '以下哪些是Java关键字？', 'public', 'class', 'String', 'static', 'A,B,D', NULL, 3, 1, 2, '2026-03-26 22:10:23', '2026-04-07 17:18:17');
INSERT INTO `question` VALUES (8, 3, 2, 'Java支持多继承', '正确', '错误', NULL, NULL, '0', NULL, 1, 1, 2, '2026-03-26 22:10:23', '2026-04-07 17:56:53');
INSERT INTO `question` VALUES (9, 1, 2, 'Java 中用来声明常量的关键字是？', 'final', 'static', 'const', 'let', 'A', 'Java 使用 final 声明常量', 2, 1, 1, '2026-04-05 21:30:52', '2026-04-07 17:57:56');
INSERT INTO `question` VALUES (10, 1, 2, '以下哪个不是 Spring 框架的模块？', 'Spring Core', 'Spring MVC', 'Spring Boot', 'Spring DB', 'D', 'Spring DB 并不是 Spring 官方模块', 2, 2, 1, '2026-04-05 21:30:52', '2026-04-07 17:57:57');
INSERT INTO `question` VALUES (11, 2, 2, 'Java 中的基本数据类型有哪些？', 'int', 'String', 'double', 'boolean', 'A,C,D', 'Java 基本数据类型包括 int, double, boolean', 3, 1, 1, '2026-04-05 21:30:52', '2026-04-07 17:57:57');
INSERT INTO `question` VALUES (12, 2, 2, '下列哪些是 HTTP 请求方法？', 'GET', 'POST', 'FETCH', 'DELETE', 'A,B,D', 'HTTP 常用方法包括 GET、POST、PUT、DELETE', 3, 2, 1, '2026-04-05 21:30:52', '2026-04-07 17:57:58');
INSERT INTO `question` VALUES (13, 3, 2, 'Java 是面向对象的编程语言。', '正确', '错误', NULL, NULL, '1', 'Java 是面向对象语言', 1, 1, 1, '2026-04-05 21:30:52', '2026-04-07 17:57:58');
INSERT INTO `question` VALUES (14, 3, 2, 'HTML 可以直接用来编写 Java 程序。', '正确', '错误', NULL, NULL, '0', 'HTML 是标记语言，不能编写 Java', 1, 1, 1, '2026-04-05 21:30:52', '2026-04-07 17:57:58');
INSERT INTO `question` VALUES (15, 1, 2, '在 SQL 中，用来删除表中所有数据的语句是？', 'DELETE', 'DROP', 'TRUNCATE', 'REMOVE', 'C', 'TRUNCATE 会清空表数据但保留表结构', 2, 2, 2, '2026-04-05 21:30:52', '2026-04-07 17:18:26');
INSERT INTO `question` VALUES (16, 1, 2, 'MySQL 默认字符集是？', 'utf8', 'utf8mb4', 'latin1', 'ascii', 'B', 'MySQL 8.0 默认字符集是 utf8mb4', 2, 2, 2, '2026-04-05 21:30:52', '2026-04-07 17:18:26');
INSERT INTO `question` VALUES (17, 2, 2, '以下哪些是 MySQL 数据类型？', 'INT', 'VARCHAR', 'BOOLEAN', 'NUMBER', 'A,B,C', 'MySQL 支持 INT, VARCHAR, BOOLEAN 等类型', 3, 1, 2, '2026-04-05 21:30:52', '2026-04-07 17:18:27');
INSERT INTO `question` VALUES (18, 2, 2, '以下哪些属于 HTML5 新增标签？', 'section', 'article', 'font', 'header', 'A,B,D', 'HTML5 新增语义标签 section, article, header', 3, 2, 2, '2026-04-05 21:30:52', '2026-04-07 17:18:28');
INSERT INTO `question` VALUES (122, 1, 2, 'Java 中哪个关键字用于创建类的实例？', 'new', 'create', 'instance', 'build', 'A', 'new 关键字用于实例化对象。', 2, 1, NULL, '2026-04-07 17:54:46', '2026-04-07 17:57:59');
INSERT INTO `question` VALUES (123, 1, 2, 'Spring MVC 中处理请求的控制器注解是？', '@Service', '@Repository', '@Controller', '@Component', 'C', '@Controller 标注 Web 层组件。', 2, 1, NULL, '2026-04-07 17:54:46', '2026-04-07 17:57:59');
INSERT INTO `question` VALUES (124, 1, 2, 'MySQL 默认的端口号是多少？', '8080', '3306', '6379', '27017', 'B', '3306 是 MySQL 默认端口。', 2, 1, NULL, '2026-04-07 17:54:46', '2026-04-07 17:58:00');
INSERT INTO `question` VALUES (125, 1, 2, 'Redis 的主要数据存储在？', '硬盘', '内存', 'CPU缓存', '光盘', 'B', 'Redis 是基于内存的数据库。', 2, 2, NULL, '2026-04-07 17:54:46', '2026-04-07 17:58:00');
INSERT INTO `question` VALUES (126, 1, 2, 'Python 的缩进主要作用是？', '美观', '标识代码块', '没有作用', '增加运行速度', 'B', 'Python 强制使用缩进来定义逻辑结构。', 2, 1, NULL, '2026-04-07 17:54:46', '2026-04-07 17:54:46');
INSERT INTO `question` VALUES (127, 1, 2, 'Git 中将暂存区内容提交到本地仓库的命令是？', 'git add', 'git push', 'git commit', 'git pull', 'C', 'commit 操作提交到本地。', 2, 2, NULL, '2026-04-07 17:54:46', '2026-04-07 17:54:46');
INSERT INTO `question` VALUES (128, 1, 2, 'Maven 的核心配置文件名是？', 'pom.xml', 'setting.xml', 'index.html', 'config.yml', 'A', 'Project Object Model 简称 POM。', 2, 1, NULL, '2026-04-07 17:54:46', '2026-04-07 17:54:46');
INSERT INTO `question` VALUES (129, 1, 2, 'Linux 中查看当前路径的命令是？', 'ls', 'cd', 'pwd', 'mkdir', 'C', 'print working directory。', 2, 1, NULL, '2026-04-07 17:54:46', '2026-04-07 17:58:01');
INSERT INTO `question` VALUES (130, 1, 2, 'Docker 的容器技术属于？', '虚拟机', '进程隔离', '硬件仿真', '云存储', 'B', 'Docker 是轻量级的级虚拟化。', 2, 3, NULL, '2026-04-07 17:54:46', '2026-04-07 17:58:01');
INSERT INTO `question` VALUES (131, 1, 2, '哪个 HTTP 状态码表示“未找到资源”？', '200', '403', '404', '500', 'C', '404 Not Found。', 2, 1, NULL, '2026-04-07 17:54:46', '2026-04-07 17:58:02');
INSERT INTO `question` VALUES (132, 2, 2, '关于 Spring Boot 的优点，说法正确的是？', '简化配置', '内嵌服务器', '不依赖 Spring', '易于部署', 'A,B,D', 'Spring Boot 依然基于 Spring 框架。', 3, 2, NULL, '2026-04-07 17:54:54', '2026-04-07 17:58:02');
INSERT INTO `question` VALUES (133, 2, 2, '以下哪些是 Java 的基本数据类型？', 'int', 'String', 'boolean', 'double', 'A,C,D', 'String 是引用类型。', 3, 1, NULL, '2026-04-07 17:54:54', '2026-04-07 17:58:02');
INSERT INTO `question` VALUES (134, 2, 2, 'HTTP 协议的特点包括？', '无状态', '无连接', '面向连接', '单向性', 'A,B', 'HTTP 本身是无状态无连接的。', 3, 2, NULL, '2026-04-07 17:54:54', '2026-04-07 17:54:54');
INSERT INTO `question` VALUES (135, 2, 2, '常用的消息队列（MQ）产品有？', 'RabbitMQ', 'Kafka', 'RocketMQ', 'MySQL', 'A,B,C', 'MySQL 是关系型数据库。', 3, 2, NULL, '2026-04-07 17:54:54', '2026-04-07 17:54:54');
INSERT INTO `question` VALUES (136, 2, 2, '数据库索引的缺点包括？', '占用磁盘空间', '降低查询速度', '影响增删改效率', '增加开发难度', 'A,C', '索引会占用空间并导致写操作变慢。', 3, 3, NULL, '2026-04-07 17:54:54', '2026-04-07 17:58:03');
INSERT INTO `question` VALUES (137, 2, 2, '哪些属于常见的反爬虫机制？', '验证码', 'IP频率限制', 'User-Agent检查', '数据库加密', 'A,B,C', '反爬主要在前端请求层。', 3, 2, NULL, '2026-04-07 17:54:54', '2026-04-07 17:58:03');
INSERT INTO `question` VALUES (138, 3, 2, 'MyBatis 的 #{} 可以防止 SQL 注入。', '正确', '错误', NULL, NULL, '1', '底层使用 PreparedStatement。', 1, 1, NULL, '2026-04-07 17:55:04', '2026-04-07 17:58:04');
INSERT INTO `question` VALUES (139, 3, 2, 'Spring 默认创建的对象都是单例的。', '正确', '错误', NULL, NULL, '1', '默认 scope 为 singleton。', 1, 1, NULL, '2026-04-07 17:55:04', '2026-04-07 17:58:04');
INSERT INTO `question` VALUES (140, 3, 2, 'TCP 协议是不可靠的传输协议。', '正确', '错误', NULL, NULL, '0', 'TCP 提供可靠传输，UDP 才不可靠。', 1, 1, NULL, '2026-04-07 17:55:04', '2026-04-07 17:55:04');
INSERT INTO `question` VALUES (141, 3, 2, 'Vue.js 是一个后端渲染框架。', '正确', '错误', NULL, NULL, '0', 'Vue 是前端渐进式框架。', 1, 1, NULL, '2026-04-07 17:55:04', '2026-04-07 17:55:04');
INSERT INTO `question` VALUES (142, 3, 2, '分布式系统中，CAP 原则可以同时完全满足。', '正确', '错误', NULL, NULL, '0', '只能满足其中两个。', 1, 3, NULL, '2026-04-07 17:55:04', '2026-04-07 17:58:05');
INSERT INTO `question` VALUES (143, 3, 2, 'Nginx 可以作为负载均衡器使用。', '正确', '错误', NULL, NULL, '1', 'Nginx 常用作反向代理和负载均衡。', 1, 2, NULL, '2026-04-07 17:55:04', '2026-04-23 15:00:29');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL COMMENT '身份id',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身份',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '身份表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 'admin');
INSERT INTO `role` VALUES (2, '老师', 'teacher');
INSERT INTO `role` VALUES (3, '学生', 'student');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '成绩ID',
  `student_id` int NOT NULL COMMENT '学生ID',
  `course_id` int NOT NULL COMMENT '课程ID',
  `exam_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '考试名称',
  `usual_score` decimal(5, 2) NULL DEFAULT 0.00 COMMENT '平时成绩',
  `exam_score` decimal(5, 2) NULL DEFAULT 0.00 COMMENT '考试成绩',
  `total_score` decimal(5, 2) NULL DEFAULT 0.00 COMMENT '总成绩',
  `grade_level` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '成绩等级(A/B/C/D)',
  `teacher_id` int NULL DEFAULT NULL COMMENT '评分教师ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '成绩备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id` ASC, `teacher_id` ASC) USING BTREE,
  INDEX `student_id_2`(`student_id` ASC) USING BTREE,
  INDEX `tea`(`teacher_id` ASC) USING BTREE,
  INDEX `course`(`course_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学生成绩表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (2, 6, 2, 'Java期中考试', 78.00, 82.00, 80.00, 'B', 2, '继续努力');
INSERT INTO `score` VALUES (3, 7, 2, 'Java期中考试', 100.00, 95.00, 97.00, 'A', 2, '太棒啦ovo');
INSERT INTO `score` VALUES (4, 8, 4, '数据库期中考试', 80.00, 88.00, 85.00, 'B', 3, '理解较好');
INSERT INTO `score` VALUES (5, 5, 4, '数据库期中考试', 70.00, 75.00, 73.00, 'C', 3, '需要加强练习');
INSERT INTO `score` VALUES (6, 9, 4, '数据库期中考试', 88.00, 91.00, 90.00, 'A', 3, '表现优秀');
INSERT INTO `score` VALUES (7, 6, 5, 'Web开发期末考试', 90.00, 93.00, 92.00, 'A', 4, '项目完成很好');
INSERT INTO `score` VALUES (8, 7, 5, 'Web开发期末考试', 76.00, 79.00, 78.00, 'C', 4, '代码规范需要加强');
INSERT INTO `score` VALUES (9, 5, 2, '期末考试', 80.00, 7.00, 83.00, 'B', 2, '表现良好');
INSERT INTO `score` VALUES (10, 6, 3, '期末考试', 75.00, 80.00, 78.00, 'C', 3, '继续努力');
INSERT INTO `score` VALUES (11, 7, 4, '期末考试', 90.00, 92.00, 91.00, 'A', 4, '优秀');
INSERT INTO `score` VALUES (12, 8, 5, '期末考试', 65.00, 70.00, 68.00, 'D', 2, '基础一般');
INSERT INTO `score` VALUES (13, 9, 6, '期末考试', 88.00, 90.00, 89.00, 'B', 3, '学习认真');
INSERT INTO `score` VALUES (14, 12, 7, '期末考试', 78.00, 82.00, 80.00, 'B', 4, '成绩稳定');
INSERT INTO `score` VALUES (15, 13, 8, '期末考试', 92.00, 95.00, 94.00, 'A', 2, '优秀');
INSERT INTO `score` VALUES (16, 14, 9, '期末考试', 70.00, 75.00, 73.00, 'C', 3, '中等水平');
INSERT INTO `score` VALUES (17, 16, 10, '期末考试', 60.00, 65.00, 63.00, 'D', 4, '需加强');
INSERT INTO `score` VALUES (45, 114, 2, '期末考试', 70.00, 74.00, 72.00, 'C', 2, '中等');
INSERT INTO `score` VALUES (46, 115, 3, '期末考试', 83.00, 85.00, 84.00, 'B', 3, '表现不错');
INSERT INTO `score` VALUES (47, 116, 4, '期末考试', 88.00, 90.00, 89.00, 'B', 4, '良好');
INSERT INTO `score` VALUES (48, 87, 5, '期末考试', 60.00, 62.00, 61.00, 'D', 2, '基础弱');
INSERT INTO `score` VALUES (49, 88, 6, '期末考试', 75.00, 78.00, 77.00, 'C', 3, '一般');
INSERT INTO `score` VALUES (50, 89, 7, '期末考试', 86.00, 88.00, 87.00, 'B', 4, '良好');
INSERT INTO `score` VALUES (51, 90, 8, '期末考试', 91.00, 94.00, 93.00, 'A', 2, '优秀');
INSERT INTO `score` VALUES (52, 91, 9, '期末考试', 68.00, 70.00, 69.00, 'D', 3, '需努力');
INSERT INTO `score` VALUES (53, 92, 10, '期末考试', 80.00, 82.00, 81.00, 'B', 4, '表现稳定');
INSERT INTO `score` VALUES (63, 12, 2, '第一次考试', 100.00, 6.00, 43.60, 'F', 2, '继续努力！！！！！！');
INSERT INTO `score` VALUES (64, 5, 2, '第一次考试', 100.00, 7.00, 44.20, 'F', 2, '继续加油，相信自己！！');

-- ----------------------------
-- Table structure for student_activity_record
-- ----------------------------
DROP TABLE IF EXISTS `student_activity_record`;
CREATE TABLE `student_activity_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` bigint NOT NULL COMMENT '学生ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `course_id` bigint NOT NULL COMMENT '课程ID',
  `score` int NULL DEFAULT NULL COMMENT '获得的经验值',
  `submit_time` datetime NULL DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_student_activity`(`student_id` ASC, `activity_id` ASC) USING BTREE COMMENT '保证一个学生在一个活动只有一条记录',
  INDEX `idx_student`(`student_id` ASC) USING BTREE,
  INDEX `idx_activity`(`activity_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生活动统一记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_activity_record
-- ----------------------------
INSERT INTO `student_activity_record` VALUES (1, 114, 36, 2, 2, '2026-04-18 23:18:32');
INSERT INTO `student_activity_record` VALUES (2, 114, 38, 2, 10, '2026-04-18 23:32:41');
INSERT INTO `student_activity_record` VALUES (3, 12, 36, 2, 2, '2026-04-18 23:41:56');
INSERT INTO `student_activity_record` VALUES (5, 5, 38, 2, 10, '2026-04-18 23:43:30');
INSERT INTO `student_activity_record` VALUES (6, 12, 38, 2, 10, '2026-04-19 15:58:18');
INSERT INTO `student_activity_record` VALUES (7, 5, 36, 2, 2, '2026-04-19 16:15:35');
INSERT INTO `student_activity_record` VALUES (12, 12, 39, 2, 20, '2026-04-23 19:41:01');

-- ----------------------------
-- Table structure for student_answer
-- ----------------------------
DROP TABLE IF EXISTS `student_answer`;
CREATE TABLE `student_answer`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_paper_id` bigint NOT NULL COMMENT '试卷ID',
  `student_id` bigint NOT NULL COMMENT '学生ID',
  `question_id` bigint NOT NULL COMMENT '题目ID（冗余字段，方便查询）',
  `student_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生答案',
  `correct_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '正确答案（冗余）',
  `score` double NULL DEFAULT 0 COMMENT '该题得分',
  `is_correct` int NULL DEFAULT 0 COMMENT '是否正确：0否 1是',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sp_id`(`student_paper_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 267 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生答案表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_answer
-- ----------------------------
INSERT INTO `student_answer` VALUES (225, 37, 114, 9, 'A', 'A', 2, 1);
INSERT INTO `student_answer` VALUES (226, 37, 114, 10, 'B', 'D', 0, 0);
INSERT INTO `student_answer` VALUES (227, 37, 114, 135, 'A,B', 'A,B,C', 0, 0);
INSERT INTO `student_answer` VALUES (228, 37, 114, 143, '1', '1', 1, 1);
INSERT INTO `student_answer` VALUES (229, 37, 114, 14, '1', '0', 0, 0);
INSERT INTO `student_answer` VALUES (230, 37, 114, 4, '1', '0', 0, 0);
INSERT INTO `student_answer` VALUES (243, 37, 12, 9, 'A', 'A', 2, 1);
INSERT INTO `student_answer` VALUES (244, 37, 12, 10, 'A', 'D', 0, 0);
INSERT INTO `student_answer` VALUES (245, 37, 12, 135, 'A,B,C', 'A,B,C', 3, 1);
INSERT INTO `student_answer` VALUES (246, 37, 12, 143, '1', '1', 1, 1);
INSERT INTO `student_answer` VALUES (247, 37, 12, 14, '1', '0', 0, 0);
INSERT INTO `student_answer` VALUES (248, 37, 12, 4, '1', '0', 0, 0);
INSERT INTO `student_answer` VALUES (261, 37, 5, 9, 'A', 'A', 2, 1);
INSERT INTO `student_answer` VALUES (262, 37, 5, 10, 'D', 'D', 2, 1);
INSERT INTO `student_answer` VALUES (263, 37, 5, 135, 'A,B', 'A,B,C', 0, 0);
INSERT INTO `student_answer` VALUES (264, 37, 5, 143, '1', '1', 1, 1);
INSERT INTO `student_answer` VALUES (265, 37, 5, 14, '0', '0', 1, 1);
INSERT INTO `student_answer` VALUES (266, 37, 5, 4, '0', '0', 1, 1);

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '选课记录ID',
  `student_id` int NOT NULL COMMENT '学生ID',
  `course_id` int NOT NULL COMMENT '课程ID',
  `select_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '选课时间',
  `status` int NULL DEFAULT 1 COMMENT '选课状态 1学习中 2完成',
  `progress` int NULL DEFAULT 0 COMMENT '学习进度(0-100)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_sc_student`(`student_id` ASC) USING BTREE,
  INDEX `fk_sc_course`(`course_id` ASC) USING BTREE,
  CONSTRAINT `fk_sc_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_sc_student` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学生选课表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES (7, 6, 3, '2026-03-13 15:01:22', 1, 0);
INSERT INTO `student_course` VALUES (8, 7, 2, '2026-03-13 15:01:22', 1, 0);
INSERT INTO `student_course` VALUES (11, 9, 2, '2026-03-13 16:13:49', 1, 0);
INSERT INTO `student_course` VALUES (15, 5, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (16, 6, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (17, 7, 3, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (18, 8, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (19, 9, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (21, 13, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (22, 14, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (23, 16, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (24, 87, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (25, 88, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (26, 89, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (27, 90, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (28, 91, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (29, 92, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (30, 93, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (31, 94, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (32, 95, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (33, 96, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (34, 97, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (35, 98, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (36, 99, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (37, 100, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (38, 101, 2, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (39, 102, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (40, 103, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (41, 104, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (42, 105, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (43, 106, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (44, 107, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (45, 108, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (46, 109, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (47, 110, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (48, 111, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (49, 112, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (50, 113, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (51, 114, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (52, 115, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (53, 116, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (54, 87, 8, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (55, 88, 3, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (56, 89, 3, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (57, 90, 3, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (58, 91, 3, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (59, 92, 3, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (60, 93, 3, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (61, 94, 3, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (62, 95, 3, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (63, 96, 3, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (64, 97, 4, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (65, 98, 4, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (66, 99, 4, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (67, 100, 4, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (68, 101, 4, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (69, 102, 4, '2026-03-14 18:25:14', 1, 0);
INSERT INTO `student_course` VALUES (71, 12, 3, '2026-03-17 14:40:29', 1, 0);
INSERT INTO `student_course` VALUES (77, 12, 6, '2026-03-24 17:10:32', 1, 0);
INSERT INTO `student_course` VALUES (79, 12, 2, '2026-04-24 15:32:10', 1, 41);
INSERT INTO `student_course` VALUES (80, 118, 3, '2026-04-24 19:04:06', 1, 0);
INSERT INTO `student_course` VALUES (81, 118, 2, '2026-04-24 19:04:12', 1, 3);
INSERT INTO `student_course` VALUES (82, 118, 5, '2026-04-24 19:05:36', 2, 100);
INSERT INTO `student_course` VALUES (83, 120, 8, '2026-04-26 20:31:02', 1, 0);

-- ----------------------------
-- Table structure for student_paper
-- ----------------------------
DROP TABLE IF EXISTS `student_paper`;
CREATE TABLE `student_paper`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `student_id` bigint NOT NULL COMMENT '学生ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID（考试入口）',
  `paper_id` bigint NOT NULL COMMENT '试卷ID',
  `attempt` int NULL DEFAULT 1 COMMENT '第几次考试',
  `total_score` int NULL DEFAULT 0 COMMENT '总得分',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `submit_time` datetime NULL DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生答卷表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_paper
-- ----------------------------
INSERT INTO `student_paper` VALUES (23, 114, 38, 37, 1, 3, '2026-04-18 23:32:34', '2026-04-18 23:32:41');
INSERT INTO `student_paper` VALUES (26, 12, 38, 37, 1, 6, '2026-04-22 16:38:19', '2026-04-22 16:38:25');
INSERT INTO `student_paper` VALUES (27, 5, 38, 37, 2, 7, '2026-04-22 19:24:09', '2026-04-22 19:24:14');

-- ----------------------------
-- Table structure for test_paper
-- ----------------------------
DROP TABLE IF EXISTS `test_paper`;
CREATE TABLE `test_paper`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '试卷ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '试卷标题',
  `course_id` bigint NULL DEFAULT NULL COMMENT '所属课程ID',
  `total_score` int NULL DEFAULT 100 COMMENT '试卷总分',
  `status` int NULL DEFAULT 1 COMMENT '状态：0禁用 1启用',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人（教师ID）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '试卷表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of test_paper
-- ----------------------------
INSERT INTO `test_paper` VALUES (37, '第一次考试', 2, 10, 1, 2, '2026-04-17 15:53:43');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录密码(加密存储)',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `role` int NOT NULL COMMENT '用户角色 1管理员 2教师 3学生',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  `status` int NULL DEFAULT 1 COMMENT '账号状态 1正常 0禁用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 121 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$RtTRktuIRUUWv9pq.g1PmuubKzkk/Pq8mQqJ9mbJcbr9qX4kxXrhO', '管理员', '111@edu.com', '19999999999', 1, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/aec096511a214bd780c6ab98916928a3.jpeg', 1, '2026-03-10 15:44:00', '2026-03-19 19:50:07');
INSERT INTO `user` VALUES (2, 'teacher1', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '刘一诺', 'zhang@edu.com', '13800000001', 2, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/9e61ed5d80d1433d97d9e1f13baba0ec.jpg', 1, '2026-03-07 18:06:27', '2026-03-22 15:41:10');
INSERT INTO `user` VALUES (3, 'teacher2', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '韩子轩', 'li@edu.com', '13800000002', 2, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10003.jpg', 1, '2026-03-07 18:06:27', NULL);
INSERT INTO `user` VALUES (4, 'teacher3', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '唐子涵', 'wang@edu.com', '13800000003', 2, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10004.jpg', 1, '2026-03-07 18:06:27', NULL);
INSERT INTO `user` VALUES (5, 'student1', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '宋文博', 'ming@edu.com', '13800000004', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10005.jpg', 1, '2026-03-07 18:06:27', NULL);
INSERT INTO `user` VALUES (6, 'student2', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '韩若曦', 'hong@edu.com', '13800000005', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10006.jpg', 1, '2026-03-07 18:06:27', NULL);
INSERT INTO `user` VALUES (7, 'student3', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '彭子珊', 'gang@edu.com', '13800000006', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10007.jpg', 1, '2026-03-07 18:06:27', NULL);
INSERT INTO `user` VALUES (8, 'student4', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '陆一鸣', 'li@edu.com', '13800000007', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10008.jpg', 1, '2026-03-07 18:06:27', NULL);
INSERT INTO `user` VALUES (9, 'student5', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '卢思远', 'qiang@edu.com', '13800000008', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10009.jpg', 1, '2026-03-07 18:06:27', NULL);
INSERT INTO `user` VALUES (12, '123', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '大黑', '111@edu.com', '12345678910', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10010.jpg', 1, '2026-03-10 16:18:44', '2026-03-17 21:09:47');
INSERT INTO `user` VALUES (13, '244820352', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '李翊轩', 'newuser@example.com', '13800138000', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10011.jpg', 1, '2026-03-14 15:32:59', NULL);
INSERT INTO `user` VALUES (14, 'asd', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '小黑', '12456@edu.com', '13256884745', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10012.jpg', 1, '2026-03-14 15:37:41', NULL);
INSERT INTO `user` VALUES (16, '888', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '蒋欣怡', '123456@edu..com', '15698758552', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10013.jpg', 1, '2026-03-14 17:13:46', NULL);
INSERT INTO `user` VALUES (72, '244820349', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '李昊远', 'teacher1@test.com', '13900000001', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10019.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (73, '244820346', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '李东阳', 'teacher2@test.com', '13900000002', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10020.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (74, '244820348', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '李志阔', 'teacher3@test.com', '13900000003', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10021.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (75, 'teacher4', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '马嘉祺', 'teacher4@test.com', '13900000004', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10022.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (76, 'teacher5', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '丁程鑫', 'teacher5@test.com', '13900000005', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10023.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (77, 'teacher6', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '宋亚轩', 'teacher6@test.com', '13900000006', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10024.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (78, 'teacher7', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '刘耀文', 'teacher7@test.com', '13900000007', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10025.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (79, 'teacher8', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '张真源', 'teacher8@test.com', '13900000008', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10026.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (80, 'teacher9', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '严浩翔', 'teacher9@test.com', '13900000009', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10027.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (81, 'teacher10', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '贺峻霖', 'teacher10@test.com', '13900000010', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10028.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (82, 'teacher11', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '白落提', 'teacher11@test.com', '13900000011', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10029.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (83, 'teacher12', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '丰和', 'teacher12@test.com', '13900000012', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10030.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (84, 'teacher13', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '英宋', 'teacher13@test.com', '13900000013', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10031.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (85, 'teacher14', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '桓泽金', 'teacher14@test.com', '13900000014', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10032.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (86, 'teacher15', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '广秀', 'teacher15@test.com', '13900000015', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10033.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (87, 'student31', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '林浩', 'student1@test.com', '13700000001', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10034.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (88, 'student32', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '陈宇', 'student2@test.com', '13700000002', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10035.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (89, 'student33', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '黄轩', 'student3@test.com', '13700000003', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10036.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (90, 'student34', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '许飞', 'student4@test.com', '13700000004', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10037.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (91, 'student35', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '何勇', 'student5@test.com', '13700000005', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10038.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (92, 'student6', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '卢毅', 'student6@test.com', '13700000006', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10039.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (93, 'student7', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '蔡康', 'student7@test.com', '13700000007', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10040.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (94, 'student8', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '余凡', 'student8@test.com', '13700000008', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10041.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (95, 'student9', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '杜淳', 'student9@test.com', '13700000009', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10042.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (96, 'student10', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '戴维', 'student10@test.com', '13700000010', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10043.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (97, 'student11', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '李娜', 'student11@test.com', '13700000011', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10044.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (98, 'student12', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '王芳', 'student12@test.com', '13700000012', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10045.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (99, 'student13', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '张敏', 'student13@test.com', '13700000013', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10046.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (100, 'student14', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '刘婷', 'student14@test.com', '13700000014', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10047.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (101, 'student15', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '赵雅', 'student15@test.com', '13700000015', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10048.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (102, 'student16', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '陈静', 'student16@test.com', '13700000016', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10049.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (103, 'student17', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '周慧', 'student17@test.com', '13700000017', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10050.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (104, 'student18', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '吴迪', 'student18@test.com', '13700000018', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10051.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (105, 'student19', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '林青', 'student19@test.com', '13700000019', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10052.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (106, 'student20', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '刘若英', 'student20@test.com', '13700000020', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10053.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (107, 'student21', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '徐静蕾', 'student21@test.com', '13700000021', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10054.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (108, 'student22', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '汤唯', 'student22@test.com', '13700000022', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10055.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (109, 'student23', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '宋佳', 'student23@test.com', '13700000023', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10056.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (110, 'student24', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '周子涵', 'student24@test.com', '13700000024', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10057.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (111, 'student25', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '吴宇航', 'student25@test.com', '13700000025', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10058.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (112, 'student26', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '郑乐怡', 'student26@test.com', '13700000026', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10059.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (113, 'student27', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '林晓彤', 'student27@test.com', '13700000027', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10060.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (114, 'student28', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '郭子轩', 'student28@test.com', '13700000028', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10061.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (115, 'student29', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '唐雅静', 'student29@test.com', '13700000029', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10062.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (116, 'student30', '$2a$10$d64ji8TERhuKlQvgMrP7TOU3Q0YeTVZiKHd.yiT2t0O7hQVFzNLXa', '沈佳宜', 'student30@test.com', '13700000030', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/10063.jpg', 1, '2026-03-14 18:17:01', NULL);
INSERT INTO `user` VALUES (117, '霍霍霍霍', '$2a$10$s3298lOwqIfrDALezpnj1etD.NQJAedAMGhivI.t5Z9BXicrdxcSq', '霍霍', '1206768302@qq.com', '18568158669', 3, NULL, 1, '2026-04-24 16:51:15', NULL);
INSERT INTO `user` VALUES (118, '123456789', '$2a$10$8QaUKntP/maM8ycbluk1a.DXl5OR7tI.5pcKEj.ZP2miHflpMNUYq', '123456789', '123456789@qq.com', '15036523658', 3, 'https://web-eduspring.oss-cn-shanghai.aliyuncs.com/images/7baae158b77b4af99eb35001a5541680.jpg', 1, '2026-04-24 19:03:29', '2026-04-24 19:04:56');
INSERT INTO `user` VALUES (119, 'cyc', '$2a$10$FSeoKSqS8057M5Wus0OnEOpbpRnGGcceyFeUQ/2buf15hh7zbCgRS', 'cc', 'c@163.com', '', 3, NULL, 1, '2026-04-25 08:34:00', NULL);
INSERT INTO `user` VALUES (120, 'houlai', '$2a$10$B03/X2d0l7.UkrZNAAE01ulzVydUwiHTO2IgEgS6cKvIFfXp2naiK', 'houlai', '', '18603829875', 3, NULL, 1, '2026-04-26 20:30:09', NULL);

-- ----------------------------
-- Table structure for user_video_progress
-- ----------------------------
DROP TABLE IF EXISTS `user_video_progress`;
CREATE TABLE `user_video_progress`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` bigint NOT NULL COMMENT '学生ID，对应 student_course.student_id',
  `video_id` bigint NOT NULL COMMENT '视频ID，对应 course_video.id',
  `progress` decimal(5, 2) NULL DEFAULT 0.00 COMMENT '观看进度百分比',
  `last_watch_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后观看时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_student_video`(`student_id` ASC, `video_id` ASC) USING BTREE COMMENT '保证同一个学生每个视频只有一条进度记录'
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学生观看每个视频的进度，用于计算课程整体进度' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_video_progress
-- ----------------------------
INSERT INTO `user_video_progress` VALUES (3, 12, 13, 100.00, '2026-04-09 17:11:47');
INSERT INTO `user_video_progress` VALUES (4, 12, 26, 1.00, '2026-04-09 17:09:21');
INSERT INTO `user_video_progress` VALUES (5, 12, 14, 75.00, '2026-04-09 17:12:16');
INSERT INTO `user_video_progress` VALUES (6, 12, 22, 93.37, '2026-04-09 17:13:13');
INSERT INTO `user_video_progress` VALUES (7, 12, 19, 61.71, '2026-04-09 17:14:32');
INSERT INTO `user_video_progress` VALUES (8, 12, 28, 97.59, '2026-04-09 17:18:31');
INSERT INTO `user_video_progress` VALUES (9, 5, 13, 28.64, '2026-04-19 16:19:34');
INSERT INTO `user_video_progress` VALUES (10, 12, 15, 61.95, '2026-04-24 15:07:16');
INSERT INTO `user_video_progress` VALUES (11, 12, 16, 12.91, '2026-04-24 15:09:08');
INSERT INTO `user_video_progress` VALUES (12, 12, 17, 0.58, '2026-04-24 15:15:11');
INSERT INTO `user_video_progress` VALUES (13, 12, 18, 1.01, '2026-04-24 15:27:50');
INSERT INTO `user_video_progress` VALUES (14, 12, 20, 4.95, '2026-04-24 15:45:41');
INSERT INTO `user_video_progress` VALUES (15, 12, 21, 8.31, '2026-04-24 15:52:37');
INSERT INTO `user_video_progress` VALUES (16, 12, 23, 5.41, '2026-04-24 16:25:05');
INSERT INTO `user_video_progress` VALUES (17, 12, 24, 2.73, '2026-04-24 16:25:23');
INSERT INTO `user_video_progress` VALUES (18, 118, 13, 8.18, '2026-04-24 19:04:18');
INSERT INTO `user_video_progress` VALUES (19, 118, 12, 37.50, '2026-04-24 19:06:02');

SET FOREIGN_KEY_CHECKS = 1;
