/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : htz_server

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-11-13 06:08:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级机构ID，一级机构为0',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='机构管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('3', 'male', '男', 'sex', '性别', '0', 'admin', '2018-09-23 19:52:54', null, null, '性别', '0');
INSERT INTO `sys_dict` VALUES ('4', 'female', '女', 'sex', '性别', '1', 'admin', '2018-09-23 19:53:17', null, null, '性别', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_user`;
CREATE TABLE `sys_login_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '随机值',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `telephone` varchar(255) DEFAULT NULL COMMENT '手机',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `status` int(11) DEFAULT NULL COMMENT '当前状态',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_login_user
-- ----------------------------
INSERT INTO `sys_login_user` VALUES ('1', '13585863020', 'ac13d3d9a26778e2ee346501d37af661', 'k0C0GgCw4g87qwBDYIGEGA==', '华松', null, '12341234', '13585863020', '1234f@qq.com', '2019-11-13', '1', '1', 'app', '2019-11-12 21:32:00', null, '2019-11-12 21:32:00');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=453 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', null, '', '0', 'el-icon-setting', '3', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('2', '后台系统用户管理', '1', '/sys/user', '', '1', 'el-icon-service', '2', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', '1', '/sys/role', '', '1', 'el-icon-view', '4', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('5', '菜单管理', '1', '/sys/menu', '', '1', 'el-icon-menu', '5', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('7', '字典管理', '1', '/sys/dict', '', '1', 'el-icon-collection', '7', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('8', '系统日志', '43', '/sys/log', 'sys:log:view', '1', 'el-icon-tickets', '8', null, null, 'admin', '2018-09-23 19:32:28', '0');
INSERT INTO `sys_menu` VALUES ('9', '查看', '2', null, 'sys:user:view', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('10', '新增', '2', null, 'sys:user:add', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('11', '修改', '2', null, 'sys:user:edit', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('12', '删除', '2', null, 'sys:user:delete', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('17', '查看', '4', null, 'sys:role:view', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('18', '新增', '4', null, 'sys:role:add', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('19', '修改', '4', null, 'sys:role:edit', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('20', '删除', '4', null, 'sys:role:delete', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('21', '查看', '5', null, 'sys:menu:view', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('22', '新增', '5', null, 'sys:menu:add', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('23', '修改', '5', null, 'sys:menu:edit', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('24', '删除', '5', null, 'sys:menu:delete', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('31', '查看', '7', null, 'sys:dict:view', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('32', '新增', '7', null, 'sys:dict:add', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('33', '修改', '7', null, 'sys:dict:edit', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('34', '删除', '7', null, 'sys:dict:delete', '2', null, '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('35', 'icon图标', '0', '/icon/index', '', '1', 'el-icon-picture-outline', '4', null, null, 'admin', '2018-12-27 11:04:18', '0');
INSERT INTO `sys_menu` VALUES ('38', '服务监控', '43', 'http://139.196.87.48:8000/', '', '1', 'el-icon-view', '1', 'admin', '2018-11-02 20:02:15', 'admin', '2018-12-27 11:03:53', '0');
INSERT INTO `sys_menu` VALUES ('43', '系统监控', '0', '', '', '0', 'el-icon-info', '5', 'admin', '2018-12-27 10:57:29', 'admin', '2018-12-27 11:02:26', '0');
INSERT INTO `sys_menu` VALUES ('44', '系统主页', '0', '/', '', '1', 'el-icon-monitor', '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('45', '文件管理', '0', '', null, '0', 'el-icon-monitor', '1', 'admin', null, 'admin', null, '0');
INSERT INTO `sys_menu` VALUES ('47', 'jvm信息', '43', '/monitor/jvm', '', '1', 'el-icon-set-up', '2', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('48', 'Tomcat信息', '43', '/monitor/tomcat', '', '1', 'el-icon-date', '3', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('49', '系统信息', '43', '/monitor/system', '', '1', 'el-icon-cpu', '4', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('445', '上传文件', '45', '/file/upload', '', '1', 'el-icon-monitor', '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('446', '文件管理', '45', '', '', '1', 'el-icon-monitor', '1', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('447', '专辑管理', '45', '/sys/dept', '', '1', 'el-icon-monitor', '2', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('448', 'APP用户管理', '0', '/sys/loginUser', '', '1', 'el-icon-monitor', '2', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('449', '查看', '448', '', 'sys:loginUser:view', '2', '', '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('450', '新增', '448', '', 'sys:loginUser:add', '2', '', '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('451', '修改', '448', '', 'sys:loginUser:edit', '2', '', '0', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('452', '删除', '448', '', 'sys:loginUser:delete', '2', '', '0', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='角色管理';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员', 'admin', '2018-08-14 11:11:11', 'admin', '2018-09-23 19:07:18', '0');
INSERT INTO `sys_role` VALUES ('2', 'dev', '开发人员', 'admin', '2018-08-14 11:11:11', 'admin', '2018-08-14 11:11:11', '0');
INSERT INTO `sys_role` VALUES ('3', 'test', '测试人员', 'admin', '2018-08-14 11:11:11', 'admin', '2018-08-14 11:11:11', '0');
INSERT INTO `sys_role` VALUES ('8', 'mng', '部门经理', 'admin', '2018-09-23 19:09:52', null, null, '0');
INSERT INTO `sys_role` VALUES ('10', '123', '123', 'admin', '2019-06-29 14:08:09', 'admin', '2019-06-29 14:16:45', '0');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色机构';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=414 DEFAULT CHARSET=utf8 COMMENT='角色菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('224', '1', '1', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('225', '1', '2', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('226', '1', '9', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('227', '1', '3', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('228', '1', '13', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('229', '1', '4', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('230', '1', '17', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('231', '1', '5', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('232', '1', '21', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('233', '1', '6', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('234', '1', '7', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('235', '1', '31', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('236', '1', '8', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('237', '1', '25', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('238', '1', '26', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('239', '1', '27', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('240', '1', '28', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('241', '1', '29', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('242', '1', '30', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('243', '1', '35', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO `sys_role_menu` VALUES ('388', '2', '1', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('389', '2', '2', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('390', '2', '9', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('391', '2', '3', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('392', '2', '13', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('393', '2', '17', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('394', '2', '5', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('395', '2', '21', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('396', '2', '7', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('397', '2', '31', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('398', '2', '8', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('399', '2', '6', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('400', '2', '35', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('401', '2', '28', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('402', '2', '29', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('403', '2', '30', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO `sys_role_menu` VALUES ('404', '3', '1', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO `sys_role_menu` VALUES ('405', '3', '2', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO `sys_role_menu` VALUES ('406', '3', '9', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO `sys_role_menu` VALUES ('407', '3', '3', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO `sys_role_menu` VALUES ('408', '3', '13', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO `sys_role_menu` VALUES ('409', '3', '8', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO `sys_role_menu` VALUES ('410', '3', '6', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO `sys_role_menu` VALUES ('411', '3', '28', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO `sys_role_menu` VALUES ('412', '3', '29', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO `sys_role_menu` VALUES ('413', '3', '30', 'admin', '2018-09-23 19:51:55', null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(40) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e9e38f4785af638faee697314387606d', '8A0SUW+RWGipDZgEevhplg==', 'admin@qq.com', '13612345678', '1', '4', 'admin', '2018-08-14 11:11:11', 'admin', '2019-11-12 21:48:48', '0');
INSERT INTO `sys_user` VALUES ('34', 'test', '7e564fd8ec6edf2f887be13f9fc0fc49', 'XF2Txq5CeBkYRxHXA/Mnvw==', 'test@qq.com', '13889700023', '1', '1', null, null, null, '2019-11-02 07:47:06', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('2', '2', '1', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('26', '5', '3', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('33', '6', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('34', '4', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('35', '9', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('36', '10', '3', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('37', '11', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('38', '12', '3', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('39', '15', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('41', '16', '3', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('42', '8', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('43', '7', '4', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('45', '18', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('46', '17', '3', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('47', '3', '4', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('48', '21', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('52', '25', '8', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('53', '26', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('54', '27', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('56', '29', '8', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('58', '30', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('71', '28', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('72', '32', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('73', '23', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('74', '24', '2', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('76', '31', '8', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('78', '33', '3', null, null, null, null);
INSERT INTO `sys_user_role` VALUES ('79', '34', '8', null, null, null, null);
