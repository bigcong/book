/*
Navicat MySQL Data Transfer

Source Server         : localhost_5.7.17_3307
Source Server Version : 50717
Source Host           : localhost:3307
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-17 16:54:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_authentication
-- ----------------------------
DROP TABLE IF EXISTS `t_authentication`;
CREATE TABLE `t_authentication` (
  `id` varchar(25) NOT NULL,
  `person_id` varchar(25) DEFAULT NULL COMMENT '拥有者',
  `auth_type` tinyint(4) DEFAULT NULL COMMENT '认证类别:1,个人；2，单位；',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(26) DEFAULT NULL COMMENT '手机号',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `office_address` varchar(200) DEFAULT NULL COMMENT '办公地址',
  `auth_status` tinyint(4) DEFAULT NULL COMMENT '认证状态：1，未认证；2，认证审核中；3，认证审核通过；4，认证审核不通过；',
  `img_front_id` varchar(25) DEFAULT NULL COMMENT '前图片ID',
  `img_back_id` varchar(25) DEFAULT NULL COMMENT '后图片ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认证表';

-- ----------------------------
-- Records of t_authentication
-- ----------------------------
INSERT INTO `t_authentication` VALUES ('582i8qcafscqsml8yk594oa9n', '64naifbfa5ckusrmn14wpitie', '1', '冯江涛', '15652963646', '北京', null, '2', '4i2of0krhpsjo2aic3jns8l3q', 'aohm1iv2vzl7wy0w2z3vm3qud', '2017-08-05 09:30:30', '2017-08-05 09:30:30');
INSERT INTO `t_authentication` VALUES ('eozjlq8kjufhfavlrqhasr0mo', '6pw6uj34doh7ibd4y8s2aki8q', '2', 'dfaads', 'adfafa', null, 'adfad', '2', 'aifxp887jgars7h1ihhhzqs2i', '59ccqxnez70e3b26ntgs8yxa5', '2017-09-01 18:42:47', '2017-09-01 18:42:47');

-- ----------------------------
-- Table structure for t_authority_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_authority`;
CREATE TABLE `t_authority_authority` (
  `id` varchar(25) NOT NULL COMMENT '权限ID',
  `code` varchar(200) DEFAULT NULL COMMENT '权限编码',
  `relative_url` varchar(256) DEFAULT NULL COMMENT '菜单相对url是不含系统url的部分',
  `descr` varchar(40) DEFAULT NULL COMMENT '描述',
  `authority_group` varchar(100) DEFAULT NULL COMMENT '权限组:系统设置权限组等等，通过枚举类统一定义',
  `authority_type` tinyint(4) DEFAULT NULL COMMENT '权限类型:1.纯权限点;2后端菜（点击菜单通过controller控制)；3前端菜单(前后分离时view层定义菜单,无相对url,点击菜单不受controller控制，前端js自行控制)；',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表(权限)';

-- ----------------------------
-- Records of t_authority_authority
-- ----------------------------
INSERT INTO `t_authority_authority` VALUES ('1cb24db9a2154e9d9bee51687', '菜单管理', 'tMenuAuthority/list', '菜单管理', '1', '1', '2017-08-19 10:20:46', '2017-08-19 10:20:46');
INSERT INTO `t_authority_authority` VALUES ('52f0b51c4fea4eef9a63febd2', '角色管理', 'tRoleAuthority/list', '角色管理', '1', '1', '2017-08-19 10:25:09', '2017-08-19 10:25:09');
INSERT INTO `t_authority_authority` VALUES ('73fc0addcbee46c893169e305', '用户管理', 'tPersonAuthority/list', '用户管理', '1', '1', '2017-08-19 10:28:03', '2017-08-19 10:28:03');
INSERT INTO `t_authority_authority` VALUES ('77b501f0b1d54b23a2606f0d9', '图书管理', 'tBooksUploaded/list', '图书管理', '1', '1', '2017-08-19 10:30:24', '2017-08-19 10:30:24');
INSERT INTO `t_authority_authority` VALUES ('c20da31c798b4395a04fa443a', '心愿管理', 'tBookDesired/list', '心愿管理', '1', '1', '2017-08-19 10:31:32', '2017-08-19 10:31:32');
INSERT INTO `t_authority_authority` VALUES ('d96cbec605aa4591b1d1283a7', '组织管理', 'tOrgAuthority/list', '组织管理', '1', '1', '2017-08-19 10:23:50', '2017-08-19 10:23:50');
INSERT INTO `t_authority_authority` VALUES ('d9cd0eda9caa401b9bf2661c0', '认证管理', 'tAuthentication/list', '认证管理', '1', '1', '2017-08-19 10:29:31', '2017-08-19 10:29:31');

-- ----------------------------
-- Table structure for t_books_hot
-- ----------------------------
DROP TABLE IF EXISTS `t_books_hot`;
CREATE TABLE `t_books_hot` (
  `id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热门图书';

-- ----------------------------
-- Records of t_books_hot
-- ----------------------------

-- ----------------------------
-- Table structure for t_books_suggest
-- ----------------------------
DROP TABLE IF EXISTS `t_books_suggest`;
CREATE TABLE `t_books_suggest` (
  `id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推荐图书';

-- ----------------------------
-- Records of t_books_suggest
-- ----------------------------

-- ----------------------------
-- Table structure for t_books_uploaded
-- ----------------------------
DROP TABLE IF EXISTS `t_books_uploaded`;
CREATE TABLE `t_books_uploaded` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT '拥有者',
  `name` varchar(300) DEFAULT NULL COMMENT '书名',
  `author` varchar(120) DEFAULT NULL COMMENT '作者',
  `isbn` varchar(20) DEFAULT NULL COMMENT 'ISBN',
  `publishers` varchar(120) DEFAULT NULL COMMENT '出版商',
  `pages` varchar(20) DEFAULT NULL COMMENT '版本号/页数',
  `descr` varchar(2000) DEFAULT NULL COMMENT '添加文字说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传书籍表';

-- ----------------------------
-- Records of t_books_uploaded
-- ----------------------------
INSERT INTO `t_books_uploaded` VALUES ('1cejeb2xcqboqufl7n70evezy', '64naifbfa5ckusrmn14wpitie', 'mycat888', '左右吧', '3569887', '中国工信出版社', '300', '好好奋斗的', '2017-08-11 16:58:38', '2017-08-19 12:30:41');
INSERT INTO `t_books_uploaded` VALUES ('1xv4v84slv5rnjshvqs1ube58', '64naifbfa5ckusrmn14wpitie', 'JAVA学习笔记', '林信良', '55566668874', '清华大学出版社', 'v1/627', '杭锅股份发', '2017-08-05 22:42:32', '2017-08-05 22:42:32');
INSERT INTO `t_books_uploaded` VALUES ('2t92a67b8ip4lkqw8j4auskrf', '64naifbfa5ckusrmn14wpitie', '包包', '陈庚贤', '3657899335', '电子工业', '533', '流量包', '2017-08-12 05:56:22', '2017-08-16 10:23:30');
INSERT INTO `t_books_uploaded` VALUES ('3vu1vuz0dsj0cxe0dxt8bz1fy', '64naifbfa5ckusrmn14wpitie', 'xin1', '冯江涛', '9887766655555', '天津出版社', 'v/321', 'dfadfa fdaf a法国法国的发', '2017-08-19 13:56:18', '2017-08-19 13:56:18');
INSERT INTO `t_books_uploaded` VALUES ('5ncc7n494vfpq0oejk8pzrii5', '64naifbfa5ckusrmn14wpitie', '啊大幅', '阿德法', '阿德法德法', '阿德法', '334', '阿德法阿德法大赛f', '2017-08-19 14:00:33', '2017-08-19 14:00:33');
INSERT INTO `t_books_uploaded` VALUES ('80f1estssw3b94c1917lpi29s', '64naifbfa5ckusrmn14wpitie', '分布式', '陈庚贤', '3657899335', '电子工业', '524', '规规矩矩', '2017-08-12 05:48:49', '2017-08-12 05:48:49');
INSERT INTO `t_books_uploaded` VALUES ('bbc1d7dkzc2e2h43uicm51p2e', '64naifbfa5ckusrmn14wpitie', 'note', '陈庚贤', '3657899335', '电子工业', '533', '看看看', '2017-08-12 05:55:24', '2017-08-12 05:55:24');
INSERT INTO `t_books_uploaded` VALUES ('bbokotwiyfq8suadnzzibgsvd', '64naifbfa5ckusrmn14wpitie', '分布式架构', '周继续', '978-7-121-30287-9', '中国工信，电子工业', '625', '你不v发家看看', '2017-08-06 09:06:20', '2017-08-06 09:06:20');
INSERT INTO `t_books_uploaded` VALUES ('f27j4hyq1vp8zp2u9rmvsqeql', '64naifbfa5ckusrmn14wpitie', '凤飞飞给', '弟弟', '55566', '邮电出版社', '113', '好健健康康', '2017-08-05 22:36:14', '2017-08-05 22:36:14');

-- ----------------------------
-- Table structure for t_book_desired
-- ----------------------------
DROP TABLE IF EXISTS `t_book_desired`;
CREATE TABLE `t_book_desired` (
  `id` varchar(25) NOT NULL,
  `person_id` varchar(25) DEFAULT NULL COMMENT '渴望者',
  `name` varchar(300) DEFAULT NULL COMMENT '书名',
  `author` varchar(120) DEFAULT NULL COMMENT '作者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='心愿书籍表';

-- ----------------------------
-- Records of t_book_desired
-- ----------------------------
INSERT INTO `t_book_desired` VALUES ('1ir6l0whaoxdom0zjg9ya66jk', '64naifbfa5ckusrmn14wpitie', '说说', '彭渊', '2017-08-13 15:38:09', '2017-08-13 15:38:09');
INSERT INTO `t_book_desired` VALUES ('2rgvefxbx6p363ghnjw79rzxz', '64naifbfa5ckusrmn14wpitie', '分布式》', '看看', '2017-08-13 11:52:10', '2017-08-13 11:52:10');
INSERT INTO `t_book_desired` VALUES ('3gtr5jfb7yzkqox5ge30wksp9', '64naifbfa5ckusrmn14wpitie', '来了我去》', '回家看', '2017-08-13 11:56:45', '2017-08-13 11:56:45');
INSERT INTO `t_book_desired` VALUES ('3rgyya9ksxggvzf8pso5q41gr', '64naifbfa5ckusrmn14wpitie', 'test1', '林青霞', '2017-08-19 10:09:27', '2017-08-19 10:09:27');
INSERT INTO `t_book_desired` VALUES ('40d2wch2l5472jp8uib6yexvp', '64naifbfa5ckusrmn14wpitie', '句', '喔喔', '2017-08-13 15:38:37', '2017-08-13 15:38:37');
INSERT INTO `t_book_desired` VALUES ('5zt6gfrvfaopb3r8alxwkd8xs', '64naifbfa5ckusrmn14wpitie', 'JAVA》', '林', '2017-08-13 11:47:24', '2017-08-13 11:47:24');
INSERT INTO `t_book_desired` VALUES ('7o31d62jn7lmq0m6xhwqdshvw', '64naifbfa5ckusrmn14wpitie', '来了》', '看看', '2017-08-13 11:53:08', '2017-08-13 11:53:08');
INSERT INTO `t_book_desired` VALUES ('8ea7zntse1wgccibddfl93zco', '64naifbfa5ckusrmn14wpitie', '《学习革命》', '那你就', '2017-08-08 18:48:27', '2017-08-08 18:48:27');
INSERT INTO `t_book_desired` VALUES ('acnqunlt1ue2ly0m6bvow8n07', '64naifbfa5ckusrmn14wpitie', '大规模', '彭渊', '2017-08-13 11:54:01', '2017-08-13 11:54:01');
INSERT INTO `t_book_desired` VALUES ('agu0cxh511vivm4nxtofk66s5', '64naifbfa5ckusrmn14wpitie', '《谁说了算》', '那你就', '2017-08-08 18:48:26', '2017-08-08 18:48:26');
INSERT INTO `t_book_desired` VALUES ('azr0njx9w4ch7w2aytiqyi6p', '64naifbfa5ckusrmn14wpitie', 'note', '到底', '2017-08-13 11:51:46', '2017-08-13 11:51:46');
INSERT INTO `t_book_desired` VALUES ('dupv8i0qvw62q1aw09utj8s5t', '64naifbfa5ckusrmn14wpitie', '手机', '林清晰', '2017-08-19 10:15:49', '2017-08-19 10:15:49');
INSERT INTO `t_book_desired` VALUES ('e6w5fzdveddr76nh1h8yc9kqs', '64naifbfa5ckusrmn14wpitie', '架构', '陈庚贤', '2017-08-13 11:52:40', '2017-08-13 11:52:40');
INSERT INTO `t_book_desired` VALUES ('eo79hnsjikcfj97ag8agae7bj', '64naifbfa5ckusrmn14wpitie', '啊啊', '陈庚贤', '2017-08-13 11:47:53', '2017-08-13 11:47:53');

-- ----------------------------
-- Table structure for t_collecting
-- ----------------------------
DROP TABLE IF EXISTS `t_collecting`;
CREATE TABLE `t_collecting` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT '收藏者',
  `books_uploaded_id` varchar(25) DEFAULT NULL COMMENT '上传书籍表id',
  `name` varchar(300) DEFAULT NULL COMMENT '书名',
  `author` varchar(120) DEFAULT NULL COMMENT '作者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_union_collecting_person_book` (`person_id`,`books_uploaded_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';

-- ----------------------------
-- Records of t_collecting
-- ----------------------------
INSERT INTO `t_collecting` VALUES ('32mh55he586dcaqlct8wsvzo1', '64naifbfa5ckusrmn14wpitie', '1cejeb2xcqboqufl7n70evezy', 'mycat', '左右吧', '2017-08-12 06:15:50', '2017-08-12 06:15:50');
INSERT INTO `t_collecting` VALUES ('37tsxlnt7e4w9xzv4fpwggbi1', '64naifbfa5ckusrmn14wpitie', '80f1estssw3b94c1917lpi29s', '分布式', '陈庚贤', '2017-08-12 06:12:04', '2017-08-12 06:12:04');
INSERT INTO `t_collecting` VALUES ('4oph0izji5nj9ocs457lur61r', '6pw6uj34doh7ibd4y8s2aki8q', '1xv4v84slv5rnjshvqs1ube58', 'JAVA学习笔记', '林信良', '2017-08-16 15:34:12', '2017-08-16 15:34:12');
INSERT INTO `t_collecting` VALUES ('6sb0cl0ik7f607b7w54ydwdvm', '64naifbfa5ckusrmn14wpitie', '2t92a67b8ip4lkqw8j4auskrf', '包包', '陈庚贤', '2017-08-12 06:11:56', '2017-08-12 06:11:56');
INSERT INTO `t_collecting` VALUES ('80wx2skss04rntvddpvk8x1m4', '64naifbfa5ckusrmn14wpitie', 'f27j4hyq1vp8zp2u9rmvsqeql', '凤飞飞给', '弟弟', '2017-08-11 16:44:52', '2017-08-11 16:44:52');
INSERT INTO `t_collecting` VALUES ('908choiuasg5jl3ubqgvil9nd', '64naifbfa5ckusrmn14wpitie', '1xv4v84slv5rnjshvqs1ube58', 'JAVA学习笔记', '林信良', '2017-08-11 16:44:46', '2017-08-11 16:44:46');
INSERT INTO `t_collecting` VALUES ('b3muchc2xb794hvepir3c8mzw', '6pw6uj34doh7ibd4y8s2aki8q', '1cejeb2xcqboqufl7n70evezy', 'mycat888', '左右吧', '2017-09-01 17:19:19', '2017-09-01 17:19:19');
INSERT INTO `t_collecting` VALUES ('ei0vz45a57dsnys4vww3oai9t', '64naifbfa5ckusrmn14wpitie', '3vu1vuz0dsj0cxe0dxt8bz1fy', 'xin1', '冯江涛', '2017-08-19 16:22:23', '2017-08-19 16:22:23');
INSERT INTO `t_collecting` VALUES ('esyzfjb8v3fmifbs95q4888w', '64naifbfa5ckusrmn14wpitie', 'bbokotwiyfq8suadnzzibgsvd', '分布式架构', '周继续', '2017-08-12 06:21:04', '2017-08-12 06:21:04');
INSERT INTO `t_collecting` VALUES ('ey4fuwavkh7sfmjsyr4w4rf7j', '6pw6uj34doh7ibd4y8s2aki8q', '2t92a67b8ip4lkqw8j4auskrf', '包包', '陈庚贤', '2017-08-16 15:01:53', '2017-08-16 15:01:53');

-- ----------------------------
-- Table structure for t_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT '拥有者',
  `content` varchar(2000) DEFAULT NULL COMMENT '意见反馈内容',
  `tel` varchar(40) DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='反馈表';

-- ----------------------------
-- Records of t_feedback
-- ----------------------------
INSERT INTO `t_feedback` VALUES ('a0jjj9azzpudcoqnz7hv1ib1n', '6pw6uj34doh7ibd4y8s2aki8q', 'adfaf', 'dfadfad', '2017-09-01 19:22:41', '2017-09-01 19:22:41');
INSERT INTO `t_feedback` VALUES ('cozby5zw2s4mq0gu5ie0r70hd', '64naifbfa5ckusrmn14wpitie', '大师傅第三方', '123', '2017-08-18 08:28:34', '2017-08-18 08:28:34');

-- ----------------------------
-- Table structure for t_menu_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_authority`;
CREATE TABLE `t_menu_authority` (
  `id` varchar(25) NOT NULL COMMENT '菜单ID',
  `show_chars` varchar(40) DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(200) DEFAULT NULL COMMENT '权限编码:同权限表中权限编码',
  `p_menu_id` varchar(25) DEFAULT NULL COMMENT '上级菜单ID',
  `order_num` int(11) DEFAULT NULL COMMENT '排序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表(权限)';

-- ----------------------------
-- Records of t_menu_authority
-- ----------------------------
INSERT INTO `t_menu_authority` VALUES ('0947565df66743b78f426d13e', '认证管理', '认证管理', '1', '6', '2017-08-19 10:29:31', '2017-08-19 10:29:31');
INSERT INTO `t_menu_authority` VALUES ('1', '系统管理', '系统管理', '0', '1', '2017-08-18 09:50:00', '2017-08-18 09:50:05');
INSERT INTO `t_menu_authority` VALUES ('6a5b2a7604ab4ecc8eeb67ffb', '心愿管理', '心愿管理', '1', '8', '2017-08-19 10:31:32', '2017-08-19 10:31:32');
INSERT INTO `t_menu_authority` VALUES ('9b13ff1857274d71968e1cfca', '菜单管理', '菜单管理', '1', '1', '2017-08-19 10:20:46', '2017-08-19 10:20:46');
INSERT INTO `t_menu_authority` VALUES ('9e0a301553844103b7cdf86bd', '用户管理', '用户管理', '1', '5', '2017-08-19 10:28:03', '2017-08-19 10:28:03');
INSERT INTO `t_menu_authority` VALUES ('a7c21ff7e1c845e5a6949332a', '组织管理', '组织管理', '1', '2', '2017-08-19 10:23:50', '2017-08-19 10:23:50');
INSERT INTO `t_menu_authority` VALUES ('bb5f5051dbbc4dddbba03436e', '角色管理', '角色管理', '1', '4', '2017-08-19 10:25:09', '2017-08-19 10:25:09');
INSERT INTO `t_menu_authority` VALUES ('edb2fc063fdd4a26a71574b4e', '图书管理', '图书管理', '1', '7', '2017-08-19 10:30:24', '2017-08-19 10:30:24');

-- ----------------------------
-- Table structure for t_message_general
-- ----------------------------
DROP TABLE IF EXISTS `t_message_general`;
CREATE TABLE `t_message_general` (
  `id` varchar(25) NOT NULL,
  `receiver_id` varchar(25) DEFAULT NULL COMMENT '接收方:人员ID等',
  `sender_id` varchar(25) DEFAULT NULL COMMENT '发送方:若是系统通知，直接写入“系统通知”四个字',
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL COMMENT '消息内容',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态:1，未读；2，已读；',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

-- ----------------------------
-- Records of t_message_general
-- ----------------------------
INSERT INTO `t_message_general` VALUES ('1', '64naifbfa5ckusrmn14wpitie', '64naifbfa5ckusrmn14wpitie', '系统通知', '恭喜注册成功', '2', '2017-08-07 04:32:12', '2017-08-12 04:32:17');

-- ----------------------------
-- Table structure for t_org_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_org_authority`;
CREATE TABLE `t_org_authority` (
  `id` varchar(25) NOT NULL COMMENT '组织ID',
  `name` varchar(20) DEFAULT NULL COMMENT '组织名称：各组织或部门名称或楼栋、楼层',
  `order_num` int(11) DEFAULT NULL COMMENT '排序号',
  `p_org_id` varchar(25) DEFAULT NULL COMMENT '上级组织ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织表(权限)';

-- ----------------------------
-- Records of t_org_authority
-- ----------------------------
INSERT INTO `t_org_authority` VALUES ('1', 'java', '1', 'a9899a9d8ef74b63811368e6f', '2017-08-19 11:48:33', '2017-08-19 06:55:51');
INSERT INTO `t_org_authority` VALUES ('2', 'php', '1', 'a9899a9d8ef74b63811368e6f', '2017-08-19 11:48:52', '2017-08-19 06:55:39');
INSERT INTO `t_org_authority` VALUES ('204e73b7da8f4207ae5bdee21', 'nodejs', '3', '1', '2017-08-19 11:49:25', '2017-08-20 12:20:28');
INSERT INTO `t_org_authority` VALUES ('a9899a9d8ef74b63811368e6f', '技术部', '1', '0', '2017-08-19 06:55:27', '2017-08-19 06:55:27');

-- ----------------------------
-- Table structure for t_person_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_person_authority`;
CREATE TABLE `t_person_authority` (
  `id` varchar(25) NOT NULL COMMENT '用户ID',
  `login_name` varchar(40) DEFAULT NULL COMMENT '登录名',
  `nickname` varchar(40) DEFAULT NULL COMMENT '昵称',
  `pwd` varchar(40) DEFAULT NULL COMMENT '密码',
  `role_code` varchar(200) DEFAULT NULL COMMENT '人员类型（角色）',
  `head_pic` varchar(500) DEFAULT NULL COMMENT '头像图片路径',
  `tel` varchar(15) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `auth_status` tinyint(4) DEFAULT NULL COMMENT '认证状态:1，未认证；2，认证审核中；3，认证审核通过；4，认证审核不通过；',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_unique_personauthority_login_name` (`login_name`),
  UNIQUE KEY `index_unique_personauthority_email` (`email`),
  UNIQUE KEY `index_unique_personauthority_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员表(权限)';

-- ----------------------------
-- Records of t_person_authority
-- ----------------------------
INSERT INTO `t_person_authority` VALUES ('64naifbfa5ckusrmn14wpitie', '冯江涛', 'darren', 'a30f6699e15c929ec7a1a2e768f1a93f', null, null, null, '13146313878', 'hggfjt@163.com', '2017-05-20', '3', '2017-08-05 09:29:38', '2017-09-01 23:12:50');
INSERT INTO `t_person_authority` VALUES ('6pw6uj34doh7ibd4y8s2aki8q', 'fengjiangtao', '北京智慧山信息技术有限公司', 'a30f6699e15c929ec7a1a2e768f1a93f', null, '9f72dq8b1bsozc3sx3q27doma', null, '15652963646', '61947666@qq.com', '1991-03-07', '2', '2017-08-15 11:21:56', '2017-09-01 18:42:47');

-- ----------------------------
-- Table structure for t_person_org_relation_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_person_org_relation_authority`;
CREATE TABLE `t_person_org_relation_authority` (
  `id` varchar(25) NOT NULL COMMENT '人员组织关系ID',
  `persoin_id` varchar(25) DEFAULT NULL COMMENT '人员ID',
  `org_id` varchar(25) DEFAULT NULL COMMENT '组织ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员组织关系表(权限)';

-- ----------------------------
-- Records of t_person_org_relation_authority
-- ----------------------------

-- ----------------------------
-- Table structure for t_person_role_relation_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_person_role_relation_authority`;
CREATE TABLE `t_person_role_relation_authority` (
  `id` varchar(25) NOT NULL COMMENT '关系表ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT '人员ID',
  `role_code` varchar(200) DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员角色关系表(权限)';

-- ----------------------------
-- Records of t_person_role_relation_authority
-- ----------------------------

-- ----------------------------
-- Table structure for t_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_role_authority`;
CREATE TABLE `t_role_authority` (
  `id` varchar(25) NOT NULL COMMENT '角色ID',
  `code` varchar(200) DEFAULT NULL COMMENT '角色编号：admin等，编号唯一不重复',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称:超级管理员',
  `descr` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `order_num` int(11) DEFAULT NULL COMMENT '排序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_roleauthority_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表(权限)';

-- ----------------------------
-- Records of t_role_authority
-- ----------------------------

-- ----------------------------
-- Table structure for t_role_authority_relation_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_role_authority_relation_authority`;
CREATE TABLE `t_role_authority_relation_authority` (
  `id` varchar(25) NOT NULL COMMENT '关系表ID',
  `role_code` varchar(200) DEFAULT NULL COMMENT '角色编号',
  `authority_code` varchar(200) DEFAULT NULL COMMENT '权限编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关系表(权限)';

-- ----------------------------
-- Records of t_role_authority_relation_authority
-- ----------------------------

-- ----------------------------
-- Table structure for t_search_his
-- ----------------------------
DROP TABLE IF EXISTS `t_search_his`;
CREATE TABLE `t_search_his` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT '拥有者',
  `search_key` varchar(100) DEFAULT NULL COMMENT '搜索关键字',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='搜索历史';

-- ----------------------------
-- Records of t_search_his
-- ----------------------------
INSERT INTO `t_search_his` VALUES ('2navjhbmx9gvz4rzyg9l1if29', '64naifbfa5ckusrmn14wpitie', '说', '2017-08-15 15:03:12', '2017-08-15 15:03:12');
INSERT INTO `t_search_his` VALUES ('4w3q2e0nne9cidj7fzg41ke5g', '64naifbfa5ckusrmn14wpitie', '包', '2017-08-15 15:01:54', '2017-08-15 15:01:54');
INSERT INTO `t_search_his` VALUES ('7uzvjcx0iefwopyk9981ow1ts', '64naifbfa5ckusrmn14wpitie', 'mycat', '2017-08-15 15:01:04', '2017-08-15 15:01:04');
INSERT INTO `t_search_his` VALUES ('8bch463usnkkdumc1zcw7wn8a', '64naifbfa5ckusrmn14wpitie', '革命', '2017-08-15 15:32:39', '2017-08-15 15:32:39');
INSERT INTO `t_search_his` VALUES ('bnf8gz0pky1y1cdxrgm60260l', '64naifbfa5ckusrmn14wpitie', '学习笔记', '2017-08-15 15:32:48', '2017-08-15 15:32:48');

-- ----------------------------
-- Table structure for t_shipping_address
-- ----------------------------
DROP TABLE IF EXISTS `t_shipping_address`;
CREATE TABLE `t_shipping_address` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT '拥有者',
  `name` varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  `mobile` varchar(26) DEFAULT NULL COMMENT '收货人手机',
  `region` varchar(200) DEFAULT NULL COMMENT '所在地区',
  `province` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '市',
  `county` varchar(20) DEFAULT NULL COMMENT '区县',
  `post_code` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `is_default` tinyint(4) DEFAULT NULL COMMENT '是默认:1 不是；2是；',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址表';

-- ----------------------------
-- Records of t_shipping_address
-- ----------------------------
INSERT INTO `t_shipping_address` VALUES ('5qipr17pkfd5zldkl9pn7k4el', '64naifbfa5ckusrmn14wpitie', 'U', '156', '看看空间', '河北省', '邢台市', '南和县', '100631', '2', '2017-08-06 19:13:39', '2017-08-17 19:44:47');

-- ----------------------------
-- Table structure for t_zhs_file_authority_general
-- ----------------------------
DROP TABLE IF EXISTS `t_zhs_file_authority_general`;
CREATE TABLE `t_zhs_file_authority_general` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '文件名（含后缀）',
  `thumbnail` varchar(100) DEFAULT NULL COMMENT '缩略图文件名(含后缀)',
  `postfix` varchar(40) DEFAULT NULL COMMENT '文件格式(即后缀)',
  `service_name` varchar(40) DEFAULT NULL COMMENT '对应业务名（可以是表名）',
  `service_id` varchar(40) DEFAULT NULL COMMENT '业务ID(可以是表ID)',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小:字节',
  `order_num` bigint(20) DEFAULT NULL COMMENT '排序号',
  `location` varchar(500) DEFAULT NULL COMMENT '位置',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_zhs_file_service` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限验证文件表';

-- ----------------------------
-- Records of t_zhs_file_authority_general
-- ----------------------------
INSERT INTO `t_zhs_file_authority_general` VALUES ('3i0qf9qiidd0i9uuxcgyje5wv', '4bal4o15k60wnho79xws5octa.jpg', '4bal4o15k60wnho79xws5octa_suo.jpg', '.jpg', 't_authentication', 'cpmfk6xcmf5ds6vjdcee7v389', '643314', '1', 'authentication/17/8', '2017-08-16 17:03:32', '2017-08-16 17:03:32');
INSERT INTO `t_zhs_file_authority_general` VALUES ('3jkuczme3mk1oqhva8oy0276', '8f7iszx2rfr68cfri6yvfcjhc.png', '8f7iszx2rfr68cfri6yvfcjhc_suo.png', '.png', 't_authentication', '3oq4h9m5rhhiajdn1sqdu37w6', '5684', '2', 'authentication/17/8', '2017-08-19 17:32:12', '2017-08-19 17:32:12');
INSERT INTO `t_zhs_file_authority_general` VALUES ('4dgeqwsvm8qv80wan82aqular', 'dmjt4qyufhdk1y116da2cxb6f.jpg', 'dmjt4qyufhdk1y116da2cxb6f_suo.jpg', '.jpg', 't_authentication', '3oq4h9m5rhhiajdn1sqdu37w6', '423838', '1', 'authentication/17/8', '2017-08-19 17:32:12', '2017-08-19 17:32:12');
INSERT INTO `t_zhs_file_authority_general` VALUES ('4i2of0krhpsjo2aic3jns8l3q', '1gy33kunu42kms0ye7mxkl6yj.jpg', null, '.jpg', 't_authentication', '582i8qcafscqsml8yk594oa9n', '2892696', '1', 'authentication/17/8', '2017-08-05 09:30:30', '2017-08-05 09:30:30');
INSERT INTO `t_zhs_file_authority_general` VALUES ('59ccqxnez70e3b26ntgs8yxa5', '6fu3rm17p0tjy9xbqt831xt4q.png', '6fu3rm17p0tjy9xbqt831xt4q_suo.png', '.png', 't_authentication', 'eozjlq8kjufhfavlrqhasr0mo', '8565', '2', 'authentication/17/9', '2017-09-01 18:42:47', '2017-09-01 18:42:47');
INSERT INTO `t_zhs_file_authority_general` VALUES ('7bl6l7w0yw5zmbtfot8iebajz', '7852zfnoog32ksyqy85zuhtsi.png', '7852zfnoog32ksyqy85zuhtsi_suo.png', '.png', 't_authentication', '9bojiwf7vkvj4g8ocrtsnj2a9', '15890', '2', 'authentication/17/8', '2017-08-19 18:23:00', '2017-08-19 18:23:00');
INSERT INTO `t_zhs_file_authority_general` VALUES ('7n5h6ljnnep8ajq04zikjjw6k', 'oi8iilm2omkt70xgw4iio4fu.jpg', 'oi8iilm2omkt70xgw4iio4fu_suo.jpg', '.jpg', 't_authentication', '9bojiwf7vkvj4g8ocrtsnj2a9', '569132', '1', 'authentication/17/8', '2017-08-19 18:23:00', '2017-08-19 18:23:00');
INSERT INTO `t_zhs_file_authority_general` VALUES ('9f72dq8b1bsozc3sx3q27doma', '2allx4khmzl1sun4ooj52saj0.jpg', '2allx4khmzl1sun4ooj52saj0_suo.jpg', '.jpg', 't_person_authority', '6pw6uj34doh7ibd4y8s2aki8q', '3146630', '1503141564000000', 'head/17/8', '2017-08-19 19:19:25', '2017-08-19 19:19:25');
INSERT INTO `t_zhs_file_authority_general` VALUES ('aifxp887jgars7h1ihhhzqs2i', 'bxbp6c49u1lovkv4egsgsu96e.jpg', 'bxbp6c49u1lovkv4egsgsu96e_suo.jpg', '.jpg', 't_authentication', 'eozjlq8kjufhfavlrqhasr0mo', '569132', '1', 'authentication/17/9', '2017-09-01 18:42:47', '2017-09-01 18:42:47');
INSERT INTO `t_zhs_file_authority_general` VALUES ('aohm1iv2vzl7wy0w2z3vm3qud', '73yl01rfzr5o8i6fw7902d1ej.jpg', null, '.jpg', 't_authentication', '582i8qcafscqsml8yk594oa9n', '2976841', '2', 'authentication/17/8', '2017-08-05 09:30:30', '2017-08-05 09:30:30');
INSERT INTO `t_zhs_file_authority_general` VALUES ('e9dm4nsuv12i28873p0kbrvcs', 'a7sz4l4vzevkfdl1bp1sakd7p.jpg', 'a7sz4l4vzevkfdl1bp1sakd7p_suo.jpg', '.jpg', 't_authentication', 'cpmfk6xcmf5ds6vjdcee7v389', '214560', '2', 'authentication/17/8', '2017-08-16 17:03:32', '2017-08-16 17:03:32');

-- ----------------------------
-- Table structure for t_zhs_file_general
-- ----------------------------
DROP TABLE IF EXISTS `t_zhs_file_general`;
CREATE TABLE `t_zhs_file_general` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '文件名（含后缀）',
  `thumbnail` varchar(100) DEFAULT NULL COMMENT '缩略图文件名(含后缀)',
  `postfix` varchar(40) DEFAULT NULL COMMENT '文件格式(即后缀)',
  `service_name` varchar(40) DEFAULT NULL COMMENT '对应业务名（可以是表名）',
  `service_id` varchar(40) DEFAULT NULL COMMENT '业务ID(可以是表ID)',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小:字节',
  `order_num` bigint(20) DEFAULT NULL COMMENT '排序号',
  `location` varchar(500) DEFAULT NULL COMMENT '位置',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_zhs_file_service` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';

-- ----------------------------
-- Records of t_zhs_file_general
-- ----------------------------
INSERT INTO `t_zhs_file_general` VALUES ('1mpftjbzh6o54pvaejpxnp7q0', 'cx8ynb4zw3unegricnrow8hfm.jpg', 'cx8ynb4zw3unegricnrow8hfm_suo.jpg', '.jpg', 't_book_desired', '5zt6gfrvfaopb3r8alxwkd8xs', '3349363', '1502596044000000', 'book/17/8', '2017-08-13 11:47:24', '2017-08-13 11:47:24');
INSERT INTO `t_zhs_file_general` VALUES ('1nujajhmn9h7jrq1z6g4sf694', '1i5te6hycyb46wepusgbxt021.jpg', '1i5te6hycyb46wepusgbxt021_suo.jpg', '.jpg', 't_feedback', 'cozby5zw2s4mq0gu5ie0r70hd', '569132', '1503016113000000', 'feedback/17/8', '2017-08-18 08:28:34', '2017-08-18 08:28:34');
INSERT INTO `t_zhs_file_general` VALUES ('21kkwku7e0k1wd8wjd1gho5zy', '6wvdm3zqe87o9aomecrvxknfd.jpg', '6wvdm3zqe87o9aomecrvxknfd_suo.jpg', '.jpg', 't_book_desired', 'acnqunlt1ue2ly0m6bvow8n07', '2349262', '1502596440000000', 'book/17/8', '2017-08-13 11:54:01', '2017-08-13 11:54:01');
INSERT INTO `t_zhs_file_general` VALUES ('2ab7a0in1uh6csvcobedp9ddo', '5qd3gl1q6t8byt2wy4v8pg702.jpg', '5qd3gl1q6t8byt2wy4v8pg702_suo.jpg', '.jpg', 't_books_uploaded', '5ncc7n494vfpq0oejk8pzrii5', '827801', '1503122434000001', 'book/17/8', '2017-08-19 14:00:33', '2017-08-19 14:00:33');
INSERT INTO `t_zhs_file_general` VALUES ('2cqfbkb9u5qgv3tt3wexnvoe1', 'a00qjp0kijdushd7j0u82xyse.jpg', 'a00qjp0kijdushd7j0u82xyse_suo.jpg', '.jpg', 't_books_uploaded', '2t92a67b8ip4lkqw8j4auskrf', '2834958', '1502849320000000', 'book/17/8', '2017-08-16 10:08:41', '2017-08-16 10:08:41');
INSERT INTO `t_zhs_file_general` VALUES ('33sqmvhs1l654zh3iw5j8okx6', '21n9hn9xqepyqk4ojk0a0xoza.jpg', '21n9hn9xqepyqk4ojk0a0xoza_suo.jpg', '.jpg', 't_book_desired', '40d2wch2l5472jp8uib6yexvp', '3007516', '1502609916000000', 'book/17/8', '2017-08-13 15:38:37', '2017-08-13 15:38:37');
INSERT INTO `t_zhs_file_general` VALUES ('39e45sz6d6mwnl0eh4fvwx0vy', 'd1t8ahcw71706oplpuyt8hspq.jpg', 'd1t8ahcw71706oplpuyt8hspq_suo.jpg', '.jpg', 't_book_desired', '3rgyya9ksxggvzf8pso5q41gr', '569132', '1503108567000000', 'book/17/8', '2017-08-19 10:09:27', '2017-08-19 10:09:27');
INSERT INTO `t_zhs_file_general` VALUES ('3rzwa8kju76oi11qrsx8uhj9j', 'aml4e9hmks0xcurket4ld0cyv.jpg', 'aml4e9hmks0xcurket4ld0cyv_suo.jpg', '.jpg', 't_book_desired', 'azr0njx9w4ch7w2aytiqyi6p', '3562559', '1502596306000000', 'book/17/8', '2017-08-13 11:51:46', '2017-08-13 11:51:46');
INSERT INTO `t_zhs_file_general` VALUES ('4f7ikydv0bqprnoksus8go1vg', '2nxds04df5f1yzwkx7p2vxll.jpg', '2nxds04df5f1yzwkx7p2vxll_suo.jpg', '.jpg', 't_books_uploaded', '2t92a67b8ip4lkqw8j4auskrf', '3193621', '1502850210000001', 'book/17/8', '2017-08-16 10:23:30', '2017-08-16 10:23:30');
INSERT INTO `t_zhs_file_general` VALUES ('4qftkinl1o2ythebd4oy8e5o6', 'bmnszie2q73obqy7crrsorgye.jpg', 'bmnszie2q73obqy7crrsorgye_suo.jpg', '.jpg', 't_books_uploaded', 'f27j4hyq1vp8zp2u9rmvsqeql', '2942594', '1501943774000000', 'book/17/8', '2017-08-05 22:36:14', '2017-08-05 22:36:14');
INSERT INTO `t_zhs_file_general` VALUES ('4zysv2l03rm7mvxwc5jp2yet0', '9qz3t79gtkcaazt1iu0ikmixz.jpg', '9qz3t79gtkcaazt1iu0ikmixz_suo.jpg', '.jpg', 't_books_uploaded', null, '569132', '1503052053000000', 'book/17/8', '2017-08-18 18:27:33', '2017-08-18 18:27:33');
INSERT INTO `t_zhs_file_general` VALUES ('53nzrurda54clb5myr856866y', '7uez9n0l87bgn2j3vj64neuuh.jpg', '7uez9n0l87bgn2j3vj64neuuh_suo.jpg', '.jpg', 't_books_uploaded', '2t92a67b8ip4lkqw8j4auskrf', '2875584', '1502850210000000', 'book/17/8', '2017-08-16 10:23:30', '2017-08-16 10:23:30');
INSERT INTO `t_zhs_file_general` VALUES ('5ev84ttoaunixb41kgnuvncvd', 'bywk1g9ekgzwr5a0uaahzc3ar.png', 'bywk1g9ekgzwr5a0uaahzc3ar_suo.png', '.png', 't_books_uploaded', '3vu1vuz0dsj0cxe0dxt8bz1fy', '8565', '1503122178000000', 'book/17/8', '2017-08-19 13:56:18', '2017-08-19 13:56:18');
INSERT INTO `t_zhs_file_general` VALUES ('5ihyjr23lyaq3pfu8f4nb8y57', '1yafzlnufop10zexoc1wggtb.jpg', '1yafzlnufop10zexoc1wggtb_suo.jpg', '.jpg', 't_feedback', 'cozby5zw2s4mq0gu5ie0r70hd', '665614', '1503016113000001', 'feedback/17/8', '2017-08-18 08:28:34', '2017-08-18 08:28:34');
INSERT INTO `t_zhs_file_general` VALUES ('5njtc325lim6lgot3h7istfqi', '3i9vqvpj3pgefck8pioi88v7e.jpg', '3i9vqvpj3pgefck8pioi88v7e_suo.jpg', '.jpg', 't_books_uploaded', '1xv4v84slv5rnjshvqs1ube58', '3255662', '1501944152000000', 'book/17/8', '2017-08-05 22:42:32', '2017-08-05 22:42:32');
INSERT INTO `t_zhs_file_general` VALUES ('5p21ukpjl5klwjnx5jpamh3ti', '4r0i297u72e9hlb3o8y5sr4v5.jpg', '4r0i297u72e9hlb3o8y5sr4v5_suo.jpg', '.jpg', 't_book_desired', '8ea7zntse1wgccibddfl93zco', '2803186', '1502189307000000', 'book/17/8', '2017-08-08 18:48:27', '2017-08-08 18:48:27');
INSERT INTO `t_zhs_file_general` VALUES ('675iixzb9mg7qkdrumjhh8uvr', '4rewmh72pzxamvfrlme5obe6a.jpg', '4rewmh72pzxamvfrlme5obe6a_suo.jpg', '.jpg', 't_books_uploaded', 'bbc1d7dkzc2e2h43uicm51p2e', '2675404', '1502488523000000', 'book/17/8', '2017-08-12 05:55:24', '2017-08-12 05:55:24');
INSERT INTO `t_zhs_file_general` VALUES ('6g5ksavkzripthqiz23ucxg06', '2jid35nqcoey0tdd4vz90gcwt.jpg', '2jid35nqcoey0tdd4vz90gcwt_suo.jpg', '.jpg', 't_books_uploaded', '3vu1vuz0dsj0cxe0dxt8bz1fy', '535578', '1503122178000001', 'book/17/8', '2017-08-19 13:56:18', '2017-08-19 13:56:18');
INSERT INTO `t_zhs_file_general` VALUES ('76c7719s73df1o2yu2fxy9ca', '4ifi113ffoojww1y5d6dnw0ef.jpg', '4ifi113ffoojww1y5d6dnw0ef_suo.jpg', '.jpg', 't_books_uploaded', '1cejeb2xcqboqufl7n70evezy', '3884815', '1502441918000001', 'book/17/8', '2017-08-11 16:58:38', '2017-08-11 16:58:38');
INSERT INTO `t_zhs_file_general` VALUES ('7ap0w1078mp0v55eetftoe26a', 'a620twbyc5y9j9gz0zh1ualew.jpg', 'a620twbyc5y9j9gz0zh1ualew_suo.jpg', '.jpg', 't_books_uploaded', null, '569132', '1503052504000000', 'book/17/8', '2017-08-18 18:35:03', '2017-08-18 18:35:03');
INSERT INTO `t_zhs_file_general` VALUES ('7dccrq1dgo965bvmy4yudw0s4', '5iiej2l8xz5uu1oga6dez2p5d.jpg', '5iiej2l8xz5uu1oga6dez2p5d_suo.jpg', '.jpg', 't_books_uploaded', '1cejeb2xcqboqufl7n70evezy', '3511569', '1502441918000000', 'book/17/8', '2017-08-11 16:58:38', '2017-08-11 16:58:38');
INSERT INTO `t_zhs_file_general` VALUES ('8lbekdnxzhj59wybjrh5gbbc5', '617iczbbw33y7oyozd38e9ocp.jpg', '617iczbbw33y7oyozd38e9ocp_suo.jpg', '.jpg', 't_book_desired', 'eo79hnsjikcfj97ag8agae7bj', '3208589', '1502596072000000', 'book/17/8', '2017-08-13 11:47:53', '2017-08-13 11:47:53');
INSERT INTO `t_zhs_file_general` VALUES ('8lysxnt90ubbwvs9lj4qbu3lf', 'bw9f687w4u98ltg8q6vmv9ful.jpg', 'bw9f687w4u98ltg8q6vmv9ful_suo.jpg', '.jpg', 't_book_desired', '1ir6l0whaoxdom0zjg9ya66jk', '3141647', '1502609889000000', 'book/17/8', '2017-08-13 15:38:09', '2017-08-13 15:38:09');
INSERT INTO `t_zhs_file_general` VALUES ('9cy70n083qm80p4qjzodxq4dy', 'ezjp6r17imv8b4bqor00lmfby.jpg', 'ezjp6r17imv8b4bqor00lmfby_suo.jpg', '.jpg', 't_book_desired', 'agu0cxh511vivm4nxtofk66s5', '2803186', '1502189306000000', 'book/17/8', '2017-08-08 18:48:26', '2017-08-08 18:48:26');
INSERT INTO `t_zhs_file_general` VALUES ('9vih8e9whgdruzi8s5o2vdhiq', 'cq7mp5c8dpr9k4ptbxntiqfp9.jpg', 'cq7mp5c8dpr9k4ptbxntiqfp9_suo.jpg', '.jpg', 't_books_uploaded', null, '569132', '1503052097000000', 'book/17/8', '2017-08-18 18:28:14', '2017-08-18 18:28:14');
INSERT INTO `t_zhs_file_general` VALUES ('affgutonjytvijrwp19amxysa', 'ammfhyb2qal9zmk5p8nwcc79v.jpg', 'ammfhyb2qal9zmk5p8nwcc79v_suo.jpg', '.jpg', 't_books_uploaded', '2t92a67b8ip4lkqw8j4auskrf', '3803881', '1502488581000000', 'book/17/8', '2017-08-12 05:56:22', '2017-08-12 05:56:22');
INSERT INTO `t_zhs_file_general` VALUES ('bchopk65aiayp3ba7er4hal7a', '732ro20qwv9ngt76ufemrmn12.jpg', '732ro20qwv9ngt76ufemrmn12_suo.jpg', '.jpg', 't_book_desired', '2rgvefxbx6p363ghnjw79rzxz', '4141888', '1502596330000000', 'book/17/8', '2017-08-13 11:52:10', '2017-08-13 11:52:10');
INSERT INTO `t_zhs_file_general` VALUES ('byz2xapim1cfovswua5b4qcxh', '82203u3uj45o27t3ol9syhrhg.jpg', '82203u3uj45o27t3ol9syhrhg_suo.jpg', '.jpg', 't_books_uploaded', '1cejeb2xcqboqufl7n70evezy', '665614', '1503052855000000', 'book/17/8', '2017-08-18 18:40:55', '2017-08-18 18:40:55');
INSERT INTO `t_zhs_file_general` VALUES ('c1w6cw5bad8p1zgoe2wp3ta04', 'a7j4wg2x94tvia1h995didywg.jpg', 'a7j4wg2x94tvia1h995didywg_suo.jpg', '.jpg', 't_book_desired', '3gtr5jfb7yzkqox5ge30wksp9', '2611196', '1502596604000000', 'book/17/8', '2017-08-13 11:56:45', '2017-08-13 11:56:45');
INSERT INTO `t_zhs_file_general` VALUES ('cabvfdejwf6hk4mfgfeye5722', 'dw8b5p36o9yu2h2ysudlq5o52.jpg', 'dw8b5p36o9yu2h2ysudlq5o52_suo.jpg', '.jpg', 't_book_desired', '3rgyya9ksxggvzf8pso5q41gr', '665614', '1503108567000001', 'book/17/8', '2017-08-19 10:09:27', '2017-08-19 10:09:27');
INSERT INTO `t_zhs_file_general` VALUES ('cba0gophswuqro5gman572h8g', '6hf18kzo77ki3muj64atzuv5q.jpg', '6hf18kzo77ki3muj64atzuv5q_suo.jpg', '.jpg', 't_book_desired', 'dupv8i0qvw62q1aw09utj8s5t', '423838', '1503108949000000', 'book/17/8', '2017-08-19 10:15:49', '2017-08-19 10:15:49');
INSERT INTO `t_zhs_file_general` VALUES ('cgunxrv5zyo539tb5xhrydd5f', 'bzl09m6lisqodkx1ny3ky98jw.jpg', 'bzl09m6lisqodkx1ny3ky98jw_suo.jpg', '.jpg', 't_books_uploaded', null, '569132', '1503052687000000', 'book/17/8', '2017-08-18 18:37:57', '2017-08-18 18:37:57');
INSERT INTO `t_zhs_file_general` VALUES ('cuijd3ylea3mfz9eabgpcc9ym', '8w2vpg6yxbil0ke28vdsz8m7p.jpg', '8w2vpg6yxbil0ke28vdsz8m7p_suo.jpg', '.jpg', 't_books_uploaded', '80f1estssw3b94c1917lpi29s', '2320450', '1502488129000000', 'book/17/8', '2017-08-12 05:48:49', '2017-08-12 05:48:49');
INSERT INTO `t_zhs_file_general` VALUES ('d7snhr7t8p1zvtk98ock9lv2b', 'c2pzxegho90gbed6ufq65myrs.jpg', 'c2pzxegho90gbed6ufq65myrs_suo.jpg', '.jpg', 't_book_desired', '7o31d62jn7lmq0m6xhwqdshvw', '2723914', '1502596388000000', 'book/17/8', '2017-08-13 11:53:08', '2017-08-13 11:53:08');
INSERT INTO `t_zhs_file_general` VALUES ('eavtn3t217wnci2tpu7l5nanb', 'awvd0xji0dg1c8mek2r886gdp.jpg', 'awvd0xji0dg1c8mek2r886gdp_suo.jpg', '.jpg', 't_books_uploaded', null, '423838', '1503052097000001', 'book/17/8', '2017-08-18 18:28:14', '2017-08-18 18:28:14');
INSERT INTO `t_zhs_file_general` VALUES ('ekbi7i9kp9oan8s0vsupnjfze', 'bafsgnzxw7wln00k8gr2g6fd0.jpg', 'bafsgnzxw7wln00k8gr2g6fd0_suo.jpg', '.jpg', 't_book_desired', 'e6w5fzdveddr76nh1h8yc9kqs', '2747292', '1502596359000000', 'book/17/8', '2017-08-13 11:52:40', '2017-08-13 11:52:40');
INSERT INTO `t_zhs_file_general` VALUES ('eohntzbl0lpgkov55g6ftxffo', 'a3qozylazx38wnrzwvag2scws.jpg', 'a3qozylazx38wnrzwvag2scws_suo.jpg', '.jpg', 't_books_uploaded', '5ncc7n494vfpq0oejk8pzrii5', '665614', '1503122434000000', 'book/17/8', '2017-08-19 14:00:33', '2017-08-19 14:00:33');
