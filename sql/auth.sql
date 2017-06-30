/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : auth

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-06-30 17:05:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_chain_path
-- ----------------------------
DROP TABLE IF EXISTS `auth_chain_path`;
CREATE TABLE `auth_chain_path` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `parent_id` int(20) DEFAULT NULL,
  `system_id` int(20) DEFAULT NULL,
  `path_pattern` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `name` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1100030001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_chain_path
-- ----------------------------
INSERT INTO `auth_chain_path` VALUES ('1100010000', null, '110', '/api/users/**', '用户管理', '2017-06-07 21:50:16', '2017-06-07 17:54:09');
INSERT INTO `auth_chain_path` VALUES ('1100010010', '1100010000', '110', '/api/users/import', '用户管理-导入', '2017-06-07 21:50:16', '2017-06-07 17:54:09');
INSERT INTO `auth_chain_path` VALUES ('1100020000', null, '110', '/api/roles/**', '角色管理', '2017-06-07 21:50:16', '2017-06-07 17:54:09');
INSERT INTO `auth_chain_path` VALUES ('1100030000', null, '110', '/api/organizations/**', '组织管理', '2017-06-07 21:50:16', '2017-06-07 17:54:09');

-- ----------------------------
-- Table structure for auth_chain_path_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_chain_path_permission`;
CREATE TABLE `auth_chain_path_permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `chain_path_id` int(20) NOT NULL,
  `permission_id` int(20) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_chain_path_permission
-- ----------------------------
INSERT INTO `auth_chain_path_permission` VALUES ('2', '1100010010', '1100010080', '2017-06-07 17:55:44', '2017-06-08 11:20:13');
INSERT INTO `auth_chain_path_permission` VALUES ('3', '1100020000', '1100030000', '2017-06-07 17:55:44', '2017-06-08 11:20:30');
INSERT INTO `auth_chain_path_permission` VALUES ('4', '1100030000', '1100020000', '2017-06-07 17:55:44', '2017-06-08 11:20:41');
INSERT INTO `auth_chain_path_permission` VALUES ('5', '1100010010', '1100030030', '2017-06-08 11:21:59', '2017-06-08 11:21:59');

-- ----------------------------
-- Table structure for auth_organization
-- ----------------------------
DROP TABLE IF EXISTS `auth_organization`;
CREATE TABLE `auth_organization` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `parent_id` int(19) DEFAULT NULL,
  `seq` int(2) NOT NULL DEFAULT '0',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of auth_organization
-- ----------------------------
INSERT INTO `auth_organization` VALUES ('1', '01', '总经办', '456', 'icon-company', null, '0', '2014-02-19 01:00:00', '2017-06-08 15:30:01');
INSERT INTO `auth_organization` VALUES ('3', '02', '技术部', '', 'icon-company', null, '1', '2015-10-01 13:10:42', '2017-06-08 15:30:01');
INSERT INTO `auth_organization` VALUES ('6', '04', '测试组1', '123', 'icon-folder', '3', '0', '2015-12-06 13:12:18', '2017-06-08 15:30:01');

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` int(20) unsigned NOT NULL,
  `parent_id` int(20) DEFAULT NULL,
  `system_id` int(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `filter_type` int(2) DEFAULT NULL COMMENT '0:没有参数1:roles;2:perms；3：rest; 4：port',
  `filter_chain` char(64) DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '是否可用，0:不可用;1:可用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_parent_id` (`parent_id`),
  KEY `index_filter_chain` (`filter_chain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限登记表';

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('1000000100', null, '100', 'annon认证过滤器，可以匿名使用', '0', 'anon', '1', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1000000200', null, '100', 'authc认证过滤器，必须已经登录认证', '0', 'authc', '1', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1000000300', null, '100', 'user认证过滤器，存在用户时不需要重新认证', '0', 'user', '1', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1000000400', null, '100', 'authcBasic认证过滤器，httpBasic认证', '0', 'authcBasic', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1000001100', null, '100', '注销', '0', 'logout', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1000001200', null, '100', '阻止在请求期间创建新的会话来保证无状态的体验', '0', 'noSessionCreation', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1000002100', null, '100', 'perms授权过滤器，权限验证', '0', 'perms', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1000002200', null, '100', 'roles授权过滤器，角色验证', '0', 'roles', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1000002300', null, '100', 'rest授权过滤器，RestFul模式权限验证', '0', 'rest', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1000002400', null, '100', 'ssl授权过滤器，安全的url请求', '0', 'ssl', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1000002500', null, '100', 'port授权过滤器，端口验证', '0', 'port', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100001010', null, '110', '系统管理', '1', 'admin', '1', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010000', null, '110', '用户管理', '3', 'auth:user', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010010', '1100010000', '110', '用户管理-查看/GET', '2', 'auth:user:read', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010020', '1100010000', '110', '用户管理-新建/POST', '2', 'auth:user:create', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010030', '1100010000', '110', '用户管理-编辑/PUT', '2', 'auth:user:edit', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010040', '1100010000', '110', '用户管理-局部更新/PATCH', '2', 'auth:user:PATCH', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010050', '1100010000', '110', '用户管理-删除/DELETE', '2', 'auth:user:delete', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010060', '1100010000', '110', '用户管理/HEAD', '2', 'auth:user:read', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010070', '1100010000', '110', '用户管理/OPTIONS', '2', 'auth:user:read', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010080', '1100010000', '110', '用户管理-导入', '2', 'auth:user:import', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010090', '1100010000', '110', '用户管理-导出', '2', 'auth:user:export', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100010100', '1100010000', '110', '用户管理-复核', '2', 'auth:user:check', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020000', null, '110', '组织管理', '3', 'auth:organization', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020010', '1100020000', '110', '组织管理-查看/GET', '2', 'auth:organization:read', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020020', '1100020000', '110', '组织管理-新建/POST', '2', 'auth:organization:create', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020030', '1100020000', '110', '组织管理-编辑/PUT', '2', 'auth:organization:edit', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020040', '1100020000', '110', '组织管理-局部更新/PATCH', '2', 'auth:organization:PATCH', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020050', '1100020000', '110', '组织管理-删除/DELETE', '2', 'auth:organization:delete', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020060', '1100020000', '110', '组织管理/HEAD', '2', 'auth:organization:read', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020070', '1100020000', '110', '组织管理/OPTIONS', '2', 'auth:organization:read', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020080', '1100020000', '110', '组织管理-导入', '2', 'auth:organization:import', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020090', '1100020000', '110', '组织管理-导出', '2', 'auth:organization:export', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100020100', '1100020000', '110', '组织管理-复核', '2', 'auth:organization:check', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030000', null, '110', '角色管理', '3', 'auth:role', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030010', '1100030000', '110', '角色管理-查看/GET', '2', 'auth:role:read', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030020', '1100030000', '110', '角色管理-新建/POST', '2', 'auth:role:create', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030030', '1100030000', '110', '角色管理-编辑/PUT', '2', 'auth:role:edit', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030040', '1100030000', '110', '角色管理-局部更新/PATCH', '2', 'auth:role:PATCH', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030050', '1100030000', '110', '角色管理-删除/DELETE', '2', 'auth:role:delete', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030060', '1100030000', '110', '角色管理/HEAD', '2', 'auth:role:read', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030070', '1100030000', '110', '角色管理/OPTIONS', '2', 'auth:role:read', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030080', '1100030000', '110', '角色管理-导入', '2', 'auth:role:import', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030090', '1100030000', '110', '角色管理-导出', '2', 'auth:role:export', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100030100', '1100030000', '110', '角色管理-复核', '2', 'auth:role:check', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100040000', null, '110', '权限管理', '3', 'auth:permission', '0', null, '2017-06-07 17:23:41');
INSERT INTO `auth_permission` VALUES ('1100040010', '1100040000', '110', '权限管理-查看/GET', '2', 'auth:permission:read', '0', null, '2017-06-30 11:17:31');
INSERT INTO `auth_permission` VALUES ('1100040020', '1100040000', '110', '权限管理-新建/POST', '2', 'auth:permission:create', '0', null, '2017-06-30 11:17:33');
INSERT INTO `auth_permission` VALUES ('1100040030', '1100040000', '110', '权限管理-编辑/PUT', '2', 'auth:permission:edit', '0', null, '2017-06-30 11:17:34');
INSERT INTO `auth_permission` VALUES ('1100040040', '1100040000', '110', '权限管理-局部更新/PATCH', '2', 'auth:permission:PATCH', '0', null, '2017-06-30 11:17:35');
INSERT INTO `auth_permission` VALUES ('1100040050', '1100040000', '110', '权限管理-删除/DELETE', '2', 'auth:permission:delete', '0', null, '2017-06-30 11:17:35');
INSERT INTO `auth_permission` VALUES ('1100040060', '1100040000', '110', '权限管理/HEAD', '2', 'auth:permission:read', '0', null, '2017-06-30 11:17:36');
INSERT INTO `auth_permission` VALUES ('1100040070', '1100040000', '110', '权限管理/OPTIONS', '2', 'auth:permission:read', '0', null, '2017-06-30 11:17:36');
INSERT INTO `auth_permission` VALUES ('1100040080', '1100040000', '110', '权限管理-导入', '2', 'auth:permission:import', '0', null, '2017-06-30 11:17:37');
INSERT INTO `auth_permission` VALUES ('1100040090', '1100040000', '110', '权限管理-导出', '2', 'auth:permission:export', '0', null, '2017-06-30 11:17:38');
INSERT INTO `auth_permission` VALUES ('1100040100', '1100040000', '110', '权限管理-复核', '2', 'auth:permission:check', '0', null, '2017-06-30 11:17:38');

-- ----------------------------
-- Table structure for auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource`;
CREATE TABLE `auth_resource` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `parent_id` int(20) DEFAULT NULL,
  `system_id` int(20) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `path` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `open_mode` int(2) DEFAULT NULL COMMENT '打开方式 ajax,iframe',
  `seq` int(2) NOT NULL DEFAULT '0',
  `status` int(2) NOT NULL DEFAULT '0',
  `resource_type` int(2) NOT NULL DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1100180001 DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of auth_resource
-- ----------------------------
INSERT INTO `auth_resource` VALUES ('1100005000', null, '110', '系统管理', '', '系统管理', 'icon-company', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:05:00');
INSERT INTO `auth_resource` VALUES ('1100010000', '1100005000', '110', '用户管理', '/user/manager', '用户管理', 'icon-folder', null, '3', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:05:01');
INSERT INTO `auth_resource` VALUES ('1100010010', '1100010000', '110', '列表', '/user/dataGrid', '用户列表', 'icon-list', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:02');
INSERT INTO `auth_resource` VALUES ('1100010020', '1100010000', '110', '添加', '/user/add', '用户添加', 'icon-add', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:02');
INSERT INTO `auth_resource` VALUES ('1100010030', '1100010000', '110', '编辑', '/user/edit', '用户编辑', 'icon-edit', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:03');
INSERT INTO `auth_resource` VALUES ('1100010040', '1100010000', '110', '删除', '/user/delete', '用户删除', 'icon-del', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:03');
INSERT INTO `auth_resource` VALUES ('1100020000', '1100005000', '110', '部门管理', '/organization/manager', null, 'icon-folder', null, '4', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:05:04');
INSERT INTO `auth_resource` VALUES ('1100020010', '1100020000', '110', '列表', '/organization/treeGrid', '用户列表', 'icon-list', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:05');
INSERT INTO `auth_resource` VALUES ('1100020020', '1100020000', '110', '添加', '/organization/add', '部门添加', 'icon-add', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:05');
INSERT INTO `auth_resource` VALUES ('1100020030', '1100020000', '110', '编辑', '/organization/edit', '部门编辑', 'icon-edit', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:06');
INSERT INTO `auth_resource` VALUES ('1100020040', '1100020000', '110', '删除', '/organization/delete', '部门删除', 'icon-del', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:06');
INSERT INTO `auth_resource` VALUES ('1100030000', '1100005000', '110', '角色管理', '/role', '角色管理', 'icon-folder', null, '2', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:05:07');
INSERT INTO `auth_resource` VALUES ('1100030010', '1100030000', '110', '列表', '/role/dataGrid', '角色列表', 'icon-list', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:08');
INSERT INTO `auth_resource` VALUES ('1100030020', '1100030000', '110', '添加', '/role/add', '角色添加', 'icon-add', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:08');
INSERT INTO `auth_resource` VALUES ('1100030030', '1100030000', '110', '编辑', '/role/edit', '角色编辑', 'icon-edit', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:09');
INSERT INTO `auth_resource` VALUES ('1100030040', '1100030000', '110', '删除', '/role/delete', '角色删除', 'icon-del', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:10');
INSERT INTO `auth_resource` VALUES ('1100030050', '1100030000', '110', '授权', '/role/grant', '角色授权', 'icon-ok', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:10');
INSERT INTO `auth_resource` VALUES ('1100040000', '1100040000', '110', '资源管理', '/resource', null, 'icon-folder', null, '1', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:05:11');
INSERT INTO `auth_resource` VALUES ('1100040010', '1100040000', '110', '列表', '/resource/treeGrid', '资源列表', 'icon-list', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:11');
INSERT INTO `auth_resource` VALUES ('1100040020', '1100040000', '110', '添加', '/resource/add', '资源添加', 'icon-add', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:12');
INSERT INTO `auth_resource` VALUES ('1100040030', '1100040000', '110', '编辑', '/resource/edit', '资源编辑', 'icon-edit', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:13');
INSERT INTO `auth_resource` VALUES ('1100040040', '1100040000', '110', '删除', '/resource/delete', '资源删除', 'icon-del', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:05:13');
INSERT INTO `auth_resource` VALUES ('1100050000', null, '110', '日志管理', '/sysLog/manager', null, 'icon-company', null, '2', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:15');
INSERT INTO `auth_resource` VALUES ('1100060000', null, '110', '官方网站', 'http://www.dreamlu.net/', null, 'icon-folder', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:18');
INSERT INTO `auth_resource` VALUES ('1100070000', null, '120', '核对估值表', '', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:23');
INSERT INTO `auth_resource` VALUES ('1100080000', '1100070000', '120', '模板信息', '/combination', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:25');
INSERT INTO `auth_resource` VALUES ('1100080010', '1100080000', '120', '列表', '/combination/treeGrid', null, '', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:12:26');
INSERT INTO `auth_resource` VALUES ('1100080020', '1100080000', '120', '添加', '/combination/add', null, '', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:12:27');
INSERT INTO `auth_resource` VALUES ('1100080030', '1100080000', '120', '编辑', '/combination/edit', null, '', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:12:27');
INSERT INTO `auth_resource` VALUES ('1100080040', '1100080000', '120', '删除', '/combination/delete', null, '', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:12:28');
INSERT INTO `auth_resource` VALUES ('1100090000', '1100080000', '120', '本方科目', '/selfAccount', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:28');
INSERT INTO `auth_resource` VALUES ('1100090010', '1100090000', '120', '列表', '/selfAccount/dataGrid', null, '', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:12:29');
INSERT INTO `auth_resource` VALUES ('1100090020', '1100090000', '120', '添加', '/selfAccount/add', null, '', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:12:29');
INSERT INTO `auth_resource` VALUES ('1100090030', '1100090000', '120', '编辑', '/selfAccount/edit', null, '', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:12:30');
INSERT INTO `auth_resource` VALUES ('1100090040', '1100090000', '120', '删除', '/selfAccount/delete', null, '', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:12:31');
INSERT INTO `auth_resource` VALUES ('1100100000', '1100080000', '120', '对方科目', '/otherAccount', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:32');
INSERT INTO `auth_resource` VALUES ('1100100010', '1100100000', '120', '列表', '/otherAccount/dataGrid', null, '', null, '0', '0', '1', '2017-06-07 18:09:56', '2017-06-07 22:12:33');
INSERT INTO `auth_resource` VALUES ('1100110000', '1100070000', '120', '映射规则', '/mapRule', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:34');
INSERT INTO `auth_resource` VALUES ('1100120000', '1100070000', '120', '映射明细', '/mapDetail', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:35');
INSERT INTO `auth_resource` VALUES ('1100130000', '1100070000', '120', '核对估值表', '/singleCheck', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:35');
INSERT INTO `auth_resource` VALUES ('1100140000', null, '120', '任务管理', '', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:36');
INSERT INTO `auth_resource` VALUES ('1100150000', '1100140000', '120', '任务定义', '/task', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:37');
INSERT INTO `auth_resource` VALUES ('1100160000', '1100140000', '120', '任务调度', '/taskSchedule', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:38');
INSERT INTO `auth_resource` VALUES ('1100170000', '1100070000', '120', '接收路径', '/filePath', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:39');
INSERT INTO `auth_resource` VALUES ('1100180000', '1100070000', '120', '证券持仓', '/holdStocks', null, '', null, '0', '0', '0', '2017-06-07 18:09:56', '2017-06-07 22:12:40');

-- ----------------------------
-- Table structure for auth_resource_element
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource_element`;
CREATE TABLE `auth_resource_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_class` varchar(255) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `element_name` varchar(255) DEFAULT NULL,
  `menu_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='页面元素资源表';

-- ----------------------------
-- Records of auth_resource_element
-- ----------------------------
INSERT INTO `auth_resource_element` VALUES ('1', null, '100002', '用户管理-新增', '100001');
INSERT INTO `auth_resource_element` VALUES ('2', null, '100003', '用户管理-编辑', '100001');

-- ----------------------------
-- Table structure for auth_resource_menu
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource_menu`;
CREATE TABLE `auth_resource_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) DEFAULT NULL COMMENT '权限编码',
  `permission_class` varchar(255) DEFAULT NULL COMMENT '权限分类编码',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点',
  `bool` int(11) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='菜单资源表';

-- ----------------------------
-- Records of auth_resource_menu
-- ----------------------------
INSERT INTO `auth_resource_menu` VALUES ('1', '100000', null, '系统管理', null, null, null);
INSERT INTO `auth_resource_menu` VALUES ('2', '100001', null, '用户管理', null, null, null);
INSERT INTO `auth_resource_menu` VALUES ('3', '100005', null, '组织管理', null, null, null);
INSERT INTO `auth_resource_menu` VALUES ('4', null, null, '角色管理', null, null, null);

-- ----------------------------
-- Table structure for auth_resource_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource_permission`;
CREATE TABLE `auth_resource_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) DEFAULT NULL COMMENT '资源编码',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限编码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_resource_id` (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_resource_permission
-- ----------------------------
INSERT INTO `auth_resource_permission` VALUES ('1', '1100010010', '1100010010', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('2', '1100010020', '1100010020', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('3', '1100010030', '1100010010', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('4', '1100010030', '1100010030', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('6', '1100010040', '1100010010', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('7', '1100010040', '1100010050', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('8', '1100020010', '1100020010', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('9', '1100020020', '1100020020', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('10', '1100020030', '1100020030', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('11', '1100020040', '1100020050', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('12', '1100030010', '1100030010', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('13', '1100030020', '1100030020', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('14', '1100030030', '1100030030', '2017-06-08 15:30:47', '0000-00-00 00:00:00');
INSERT INTO `auth_resource_permission` VALUES ('15', '1100030040', '1100030050', '2017-06-08 15:30:47', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `seq` int(2) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `parent_id` int(20) DEFAULT NULL COMMENT '父角色id',
  `level` int(11) DEFAULT NULL COMMENT '所需层次',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('1', 'admin', '超级管理员1', '0', '超级管理员1', '0', null, null, '2017-06-08 16:30:39', '2017-06-08 16:30:39');
INSERT INTO `auth_role` VALUES ('2', '', '技术部经理1', '1', '技术部经理1', '0', null, null, '2017-06-08 16:30:39', '2017-06-08 16:30:39');
INSERT INTO `auth_role` VALUES ('7', '', '产品部经理', '0', '产品部经理', '0', null, null, '2017-06-08 16:30:39', '2017-06-08 16:30:39');
INSERT INTO `auth_role` VALUES ('8', '', '测试账户', '0', '测试账户', '0', null, null, '2017-06-08 16:30:39', '2017-06-08 16:30:39');

-- ----------------------------
-- Table structure for auth_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE `auth_role_permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL,
  `permission_id` int(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色权限映射表';

-- ----------------------------
-- Records of auth_role_permission
-- ----------------------------
INSERT INTO `auth_role_permission` VALUES ('1', '1', '1100010010', '2017-06-08 16:31:23', '2017-06-08 16:31:23');
INSERT INTO `auth_role_permission` VALUES ('2', '1', '1100010020', '2017-06-08 16:31:23', '2017-06-08 16:31:23');
INSERT INTO `auth_role_permission` VALUES ('3', '1', '1100010030', '2017-06-08 16:31:23', '2017-06-08 16:31:23');
INSERT INTO `auth_role_permission` VALUES ('4', '1', '1100020010', '2017-06-08 16:31:23', '2017-06-08 16:31:23');
INSERT INTO `auth_role_permission` VALUES ('5', '1', '1100020030', '2017-06-08 16:31:23', '2017-06-08 16:31:23');
INSERT INTO `auth_role_permission` VALUES ('6', '1', '1100030010', '2017-06-08 16:31:23', '2017-06-08 16:31:23');

-- ----------------------------
-- Table structure for auth_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_resource`;
CREATE TABLE `auth_role_resource` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_id` int(10) NOT NULL,
  `resource_id` int(10) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='角色资源';

-- ----------------------------
-- Records of auth_role_resource
-- ----------------------------
INSERT INTO `auth_role_resource` VALUES ('12', '1', '1100010010', null, null);
INSERT INTO `auth_role_resource` VALUES ('15', '1', '1100010030', null, null);
INSERT INTO `auth_role_resource` VALUES ('26', '1', '1100030020', null, null);
INSERT INTO `auth_role_resource` VALUES ('28', '1', '1100010000', null, null);
INSERT INTO `auth_role_resource` VALUES ('29', '1', '1100010020', null, null);
INSERT INTO `auth_role_resource` VALUES ('31', '8', '1100010010', null, null);
INSERT INTO `auth_role_resource` VALUES ('33', '8', '1100005000', null, null);
INSERT INTO `auth_role_resource` VALUES ('34', '8', '1100010000', null, null);
INSERT INTO `auth_role_resource` VALUES ('36', '8', '1100010030', null, null);
INSERT INTO `auth_role_resource` VALUES ('37', '8', '1100020000', null, null);
INSERT INTO `auth_role_resource` VALUES ('38', '8', '1100020010', null, null);
INSERT INTO `auth_role_resource` VALUES ('39', '8', '1100020020', null, null);
INSERT INTO `auth_role_resource` VALUES ('40', '8', '1100020030', null, null);
INSERT INTO `auth_role_resource` VALUES ('41', '8', '1100020040', null, null);
INSERT INTO `auth_role_resource` VALUES ('44', '1', '1100005000', null, null);
INSERT INTO `auth_role_resource` VALUES ('45', '1', '1100020000', null, null);
INSERT INTO `auth_role_resource` VALUES ('46', '1', '1100020010', null, null);
INSERT INTO `auth_role_resource` VALUES ('47', '1', '1100020020', null, null);
INSERT INTO `auth_role_resource` VALUES ('48', '1', '1100020030', null, null);
INSERT INTO `auth_role_resource` VALUES ('49', '1', '1100020040', null, null);
INSERT INTO `auth_role_resource` VALUES ('50', '1', '1100030000', null, null);
INSERT INTO `auth_role_resource` VALUES ('51', '8', '1100050000', null, null);
INSERT INTO `auth_role_resource` VALUES ('52', '2', '1100060000', null, null);
INSERT INTO `auth_role_resource` VALUES ('53', '1', '1100030030', null, null);
INSERT INTO `auth_role_resource` VALUES ('54', '2', '1100050000', null, null);

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登陆名',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '密码加密盐',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `cell_phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `status` varchar(10) DEFAULT NULL COMMENT '0未激活1已激活\\n2已禁用',
  `description` varchar(100) DEFAULT NULL COMMENT '用户简介',
  `organization_id` int(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_nick_name` (`nick_name`),
  UNIQUE KEY `index_login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', 'admin', '扶竹', '扶竹', 'WN4Ymk0TNLvdhU2X14RmpQ==', null, null, null, null, null, null, '2017-05-01 12:43:04', '2017-05-25 09:50:45', '2017-05-25 09:50:45');
INSERT INTO `auth_user` VALUES ('2', '1', '1', '1', null, null, null, null, null, null, null, '2017-06-13 21:29:02', null, null);
INSERT INTO `auth_user` VALUES ('3', '3', '3', '3', null, null, null, null, null, null, null, '2017-06-13 21:29:05', '2017-06-13 21:29:10', '2017-06-13 21:29:10');

-- ----------------------------
-- Table structure for auth_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_organization`;
CREATE TABLE `auth_user_organization` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `organization_id` int(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_organization_id` (`organization_id`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user_organization
-- ----------------------------

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户角色映射表';

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` VALUES ('2', '3', '1', '2017-06-15 13:32:22', '2017-06-15 13:32:22');
INSERT INTO `auth_user_role` VALUES ('5', '1', '1', '2017-06-16 11:58:53', '2017-06-16 11:58:53');
INSERT INTO `auth_user_role` VALUES ('6', '1', '7', '2017-06-16 12:07:53', '2017-06-16 12:07:53');
