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
  `person_id` varchar(25) DEFAULT NULL COMMENT 'ӵ����',
  `auth_type` tinyint(4) DEFAULT NULL COMMENT '��֤���:1,���ˣ�2����λ��',
  `name` varchar(20) DEFAULT NULL COMMENT '����',
  `mobile` varchar(26) DEFAULT NULL COMMENT '�ֻ���',
  `address` varchar(200) DEFAULT NULL COMMENT '��ַ',
  `office_address` varchar(200) DEFAULT NULL COMMENT '�칫��ַ',
  `auth_status` tinyint(4) DEFAULT NULL COMMENT '��֤״̬��1��δ��֤��2����֤����У�3����֤���ͨ����4����֤��˲�ͨ����',
  `img_front_id` varchar(25) DEFAULT NULL COMMENT 'ǰͼƬID',
  `img_back_id` varchar(25) DEFAULT NULL COMMENT '��ͼƬID',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��֤��';

-- ----------------------------
-- Table structure for t_authority_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_authority`;
CREATE TABLE `t_authority_authority` (
  `id` varchar(25) NOT NULL COMMENT 'Ȩ��ID',
  `code` varchar(200) DEFAULT NULL COMMENT 'Ȩ�ޱ���',
  `relative_url` varchar(256) DEFAULT NULL COMMENT '�˵����url�ǲ���ϵͳurl�Ĳ���',
  `descr` varchar(40) DEFAULT NULL COMMENT '����',
  `authority_group` varchar(100) DEFAULT NULL COMMENT 'Ȩ����:ϵͳ����Ȩ����ȵȣ�ͨ��ö����ͳһ����',
  `authority_type` tinyint(4) DEFAULT NULL COMMENT 'Ȩ������:1.��Ȩ�޵�;2��˲ˣ�����˵�ͨ��controller����)��3ǰ�˲˵�(ǰ�����ʱview�㶨��˵�,�����url,����˵�����controller���ƣ�ǰ��js���п���)��',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ȩ�ޱ�(Ȩ��)';

-- ----------------------------
-- Table structure for t_books_hot
-- ----------------------------
DROP TABLE IF EXISTS `t_books_hot`;
CREATE TABLE `t_books_hot` (
  `id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����ͼ��';

-- ----------------------------
-- Table structure for t_books_suggest
-- ----------------------------
DROP TABLE IF EXISTS `t_books_suggest`;
CREATE TABLE `t_books_suggest` (
  `id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�Ƽ�ͼ��';

-- ----------------------------
-- Table structure for t_books_uploaded
-- ----------------------------
DROP TABLE IF EXISTS `t_books_uploaded`;
CREATE TABLE `t_books_uploaded` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT 'ӵ����',
  `name` varchar(300) DEFAULT NULL COMMENT '����',
  `author` varchar(120) DEFAULT NULL COMMENT '����',
  `isbn` varchar(20) DEFAULT NULL COMMENT 'ISBN',
  `publishers` varchar(120) DEFAULT NULL COMMENT '������',
  `pages` varchar(20) DEFAULT NULL COMMENT '�汾��/ҳ��',
  `descr` varchar(2000) DEFAULT NULL COMMENT '�������˵��',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ϴ��鼮��';

-- ----------------------------
-- Table structure for t_book_desired
-- ----------------------------
DROP TABLE IF EXISTS `t_book_desired`;
CREATE TABLE `t_book_desired` (
  `id` varchar(25) NOT NULL,
  `person_id` varchar(25) DEFAULT NULL COMMENT '������',
  `name` varchar(300) DEFAULT NULL COMMENT '����',
  `author` varchar(120) DEFAULT NULL COMMENT '����',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ը�鼮��';

-- ----------------------------
-- Table structure for t_collecting
-- ----------------------------
DROP TABLE IF EXISTS `t_collecting`;
CREATE TABLE `t_collecting` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT '�ղ���',
  `books_uploaded_id` varchar(25) DEFAULT NULL COMMENT '�ϴ��鼮��id',
  `name` varchar(300) DEFAULT NULL COMMENT '����',
  `author` varchar(120) DEFAULT NULL COMMENT '����',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_union_collecting_person_book` (`person_id`,`books_uploaded_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ղر�';

-- ----------------------------
-- Table structure for t_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT 'ӵ����',
  `content` varchar(2000) DEFAULT NULL COMMENT '�����������',
  `tel` varchar(40) DEFAULT NULL COMMENT '��ϵ�绰',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������';

-- ----------------------------
-- Table structure for t_menu_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_authority`;
CREATE TABLE `t_menu_authority` (
  `id` varchar(25) NOT NULL COMMENT '�˵�ID',
  `show_chars` varchar(40) DEFAULT NULL COMMENT '�˵�����',
  `code` varchar(200) DEFAULT NULL COMMENT 'Ȩ�ޱ���:ͬȨ�ޱ���Ȩ�ޱ���',
  `p_menu_id` varchar(25) DEFAULT NULL COMMENT '�ϼ��˵�ID',
  `order_num` int(11) DEFAULT NULL COMMENT '�����',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�˵���(Ȩ��)';

-- ----------------------------
-- Table structure for t_message_general
-- ----------------------------
DROP TABLE IF EXISTS `t_message_general`;
CREATE TABLE `t_message_general` (
  `id` varchar(25) NOT NULL,
  `receiver_id` varchar(25) DEFAULT NULL COMMENT '���շ�:��ԱID��',
  `sender_id` varchar(25) DEFAULT NULL COMMENT '���ͷ�:����ϵͳ֪ͨ��ֱ��д�롰ϵͳ֪ͨ���ĸ���',
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL COMMENT '��Ϣ����',
  `status` tinyint(4) DEFAULT NULL COMMENT '״̬:1��δ����2���Ѷ���',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ϣ��';

-- ----------------------------
-- Table structure for t_org_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_org_authority`;
CREATE TABLE `t_org_authority` (
  `id` varchar(25) NOT NULL COMMENT '��֯ID',
  `name` varchar(20) DEFAULT NULL COMMENT '��֯���ƣ�����֯�������ƻ�¥����¥��',
  `order_num` int(11) DEFAULT NULL COMMENT '�����',
  `p_org_id` varchar(25) DEFAULT NULL COMMENT '�ϼ���֯ID',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��֯��(Ȩ��)';

-- ----------------------------
-- Table structure for t_person_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_person_authority`;
CREATE TABLE `t_person_authority` (
  `id` varchar(25) NOT NULL COMMENT '�û�ID',
  `login_name` varchar(40) DEFAULT NULL COMMENT '��¼��',
  `nickname` varchar(40) DEFAULT NULL COMMENT '�ǳ�',
  `pwd` varchar(40) DEFAULT NULL COMMENT '����',
  `role_code` varchar(200) DEFAULT NULL COMMENT '��Ա���ͣ���ɫ��',
  `head_pic` varchar(500) DEFAULT NULL COMMENT 'ͷ��ͼƬ·��',
  `tel` varchar(15) DEFAULT NULL COMMENT '�绰',
  `mobile` varchar(15) DEFAULT NULL COMMENT '�ֻ�',
  `email` varchar(100) DEFAULT NULL COMMENT '����',
  `birthday` date DEFAULT NULL COMMENT '����',
  `auth_status` tinyint(4) DEFAULT NULL COMMENT '��֤״̬:1��δ��֤��2����֤����У�3����֤���ͨ����4����֤��˲�ͨ����',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_unique_personauthority_login_name` (`login_name`),
  UNIQUE KEY `index_unique_personauthority_email` (`email`),
  UNIQUE KEY `index_unique_personauthority_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ա��(Ȩ��)';

-- ----------------------------
-- Table structure for t_person_org_relation_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_person_org_relation_authority`;
CREATE TABLE `t_person_org_relation_authority` (
  `id` varchar(25) NOT NULL COMMENT '��Ա��֯��ϵID',
  `persoin_id` varchar(25) DEFAULT NULL COMMENT '��ԱID',
  `org_id` varchar(25) DEFAULT NULL COMMENT '��֯ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ա��֯��ϵ��(Ȩ��)';

-- ----------------------------
-- Table structure for t_person_role_relation_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_person_role_relation_authority`;
CREATE TABLE `t_person_role_relation_authority` (
  `id` varchar(25) NOT NULL COMMENT '��ϵ��ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT '��ԱID',
  `role_code` varchar(200) DEFAULT NULL COMMENT '��ɫID',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ա��ɫ��ϵ��(Ȩ��)';

-- ----------------------------
-- Table structure for t_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_role_authority`;
CREATE TABLE `t_role_authority` (
  `id` varchar(25) NOT NULL COMMENT '��ɫID',
  `code` varchar(200) DEFAULT NULL COMMENT '��ɫ��ţ�admin�ȣ����Ψһ���ظ�',
  `name` varchar(20) DEFAULT NULL COMMENT '��ɫ����:��������Ա',
  `descr` varchar(200) DEFAULT NULL COMMENT '��ɫ����',
  `order_num` int(11) DEFAULT NULL COMMENT '�����',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_roleauthority_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ɫ��(Ȩ��)';

-- ----------------------------
-- Table structure for t_role_authority_relation_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_role_authority_relation_authority`;
CREATE TABLE `t_role_authority_relation_authority` (
  `id` varchar(25) NOT NULL COMMENT '��ϵ��ID',
  `role_code` varchar(200) DEFAULT NULL COMMENT '��ɫ���',
  `authority_code` varchar(200) DEFAULT NULL COMMENT 'Ȩ�ޱ��',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ɫȨ�޹�ϵ��(Ȩ��)';

-- ----------------------------
-- Table structure for t_search_his
-- ----------------------------
DROP TABLE IF EXISTS `t_search_his`;
CREATE TABLE `t_search_his` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT 'ӵ����',
  `search_key` varchar(100) DEFAULT NULL COMMENT '�����ؼ���',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������ʷ';

-- ----------------------------
-- Table structure for t_shipping_address
-- ----------------------------
DROP TABLE IF EXISTS `t_shipping_address`;
CREATE TABLE `t_shipping_address` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `person_id` varchar(25) DEFAULT NULL COMMENT 'ӵ����',
  `name` varchar(20) DEFAULT NULL COMMENT '�ջ�������',
  `mobile` varchar(26) DEFAULT NULL COMMENT '�ջ����ֻ�',
  `region` varchar(200) DEFAULT NULL COMMENT '���ڵ���',
  `province` varchar(20) DEFAULT NULL COMMENT 'ʡ',
  `city` varchar(20) DEFAULT NULL COMMENT '��',
  `county` varchar(20) DEFAULT NULL COMMENT '����',
  `post_code` varchar(10) DEFAULT NULL COMMENT '��������',
  `is_default` tinyint(4) DEFAULT NULL COMMENT '��Ĭ��:1 ���ǣ�2�ǣ�',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ջ���ַ��';

-- ----------------------------
-- Table structure for t_zhs_file_authority_general
-- ----------------------------
DROP TABLE IF EXISTS `t_zhs_file_authority_general`;
CREATE TABLE `t_zhs_file_authority_general` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '�ļ���������׺��',
  `thumbnail` varchar(100) DEFAULT NULL COMMENT '����ͼ�ļ���(����׺)',
  `postfix` varchar(40) DEFAULT NULL COMMENT '�ļ���ʽ(����׺)',
  `service_name` varchar(40) DEFAULT NULL COMMENT '��Ӧҵ�����������Ǳ�����',
  `service_id` varchar(40) DEFAULT NULL COMMENT 'ҵ��ID(�����Ǳ�ID)',
  `file_size` bigint(20) DEFAULT NULL COMMENT '�ļ���С:�ֽ�',
  `order_num` bigint(20) DEFAULT NULL COMMENT '�����',
  `location` varchar(500) DEFAULT NULL COMMENT 'λ��',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`),
  KEY `index_zhs_file_service` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ȩ����֤�ļ���';

-- ----------------------------
-- Table structure for t_zhs_file_general
-- ----------------------------
DROP TABLE IF EXISTS `t_zhs_file_general`;
CREATE TABLE `t_zhs_file_general` (
  `id` varchar(25) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '�ļ���������׺��',
  `thumbnail` varchar(100) DEFAULT NULL COMMENT '����ͼ�ļ���(����׺)',
  `postfix` varchar(40) DEFAULT NULL COMMENT '�ļ���ʽ(����׺)',
  `service_name` varchar(40) DEFAULT NULL COMMENT '��Ӧҵ�����������Ǳ�����',
  `service_id` varchar(40) DEFAULT NULL COMMENT 'ҵ��ID(�����Ǳ�ID)',
  `file_size` bigint(20) DEFAULT NULL COMMENT '�ļ���С:�ֽ�',
  `order_num` bigint(20) DEFAULT NULL COMMENT '�����',
  `location` varchar(500) DEFAULT NULL COMMENT 'λ��',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`),
  KEY `index_zhs_file_service` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ļ���';
