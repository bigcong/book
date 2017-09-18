/*
Navicat MySQL Data Transfer

Source Server         : localhost_5.7.17_3307
Source Server Version : 50717
Source Host           : localhost:3307
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-17 17:37:53
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
-- Table structure for t_books_hot
-- ----------------------------
DROP TABLE IF EXISTS `t_books_hot`;
CREATE TABLE `t_books_hot` (
  `id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热门图书';

-- ----------------------------
-- Table structure for t_books_suggest
-- ----------------------------
DROP TABLE IF EXISTS `t_books_suggest`;
CREATE TABLE `t_books_suggest` (
  `id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推荐图书';

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
