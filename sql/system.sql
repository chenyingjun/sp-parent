CREATE TABLE `system_user` (
  `id` varchar(40) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `name` varchar(45) COLLATE utf8mb4_bin NOT NULL COMMENT '用户姓名',
  `account` varchar(45) COLLATE utf8mb4_bin NOT NULL COMMENT '登录名',
  `nick_name` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户昵称',
  `pass_word` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '登录密码',
  `email` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `sex` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别,1.男;2.女',
  `fail_num` int(2) DEFAULT '0' COMMENT '登录失败次数',
  `del_flag` int(1) NOT NULL DEFAULT '1' COMMENT '是否删除   0.已删除；1.可用',
  `status` int(2) DEFAULT '1' COMMENT '状态  1.可用;0.禁用',
  `login_time` datetime DEFAULT NULL COMMENT '此次登录时间',
  `login_ip` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '此次登录IP',
  `last_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_ip` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上次登录IP',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_UNIQUE` (`account`),
  KEY `system_user_status_index` (`status`),
  KEY `system_user_delFlag_index` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统用户表'


CREATE TABLE `system_role` (
  `id` varchar(40) COLLATE utf8mb4_bin NOT NULL COMMENT '角色主键',
  `code` varchar(45) COLLATE utf8mb4_bin NOT NULL COMMENT '角色编号',
  `name` varchar(45) COLLATE utf8mb4_bin NOT NULL COMMENT '角色名',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '状态  1.可用;0.禁用',
  `del_flag` int(2) NOT NULL DEFAULT '1' COMMENT '是否删除   0.已删除；1.可用',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统角色'


CREATE TABLE `system_user_role` (
  `user_id` varchar(40) COLLATE utf8mb4_bin NOT NULL,
  `role_id` varchar(40) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_sur_role_id_idx` (`role_id`),
  CONSTRAINT `fk_sur_role_id` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sur_user_id` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统用户角色'