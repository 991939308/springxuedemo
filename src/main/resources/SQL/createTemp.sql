DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(20) NOT NULL DEFAULT '' COMMENT '用户密码',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(20) NOT NULL DEFAULT '' COMMENT '电子邮箱',
  `role` varchar(16) NOT NULL DEFAULT '' COMMENT '角色',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `creater` varchar(8) NOT NULL DEFAULT '' COMMENT '创建人工号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
-- ALTER table sys_user add phone varchar(11) NOT NULl DEFAULT '' COMMENT '手机号';
-- ALTER table sys_user add email varchar(25) NOT NULl DEFAULT '' COMMENT '电子邮箱';

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(16) NOT NULL DEFAULT '' COMMENT '角色',
  `permission` varchar(16) NOT NULL DEFAULT '' COMMENT '权限',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';



DROP TABLE IF EXISTS `energy_price`;
CREATE TABLE `energy_price` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `energy_type` varchar(15) NOT NULL DEFAULT '' COMMENT '能源类型',
  `energy_quantity` varchar(20) NOT NULL DEFAULT '' COMMENT '资源加个',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(20) NOT NULL DEFAULT '' COMMENT '电子邮箱',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `creater` varchar(8) NOT NULL DEFAULT '' COMMENT '创建人工号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='能源价格表';