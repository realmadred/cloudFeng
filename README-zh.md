# 脚本说明 

-- 目录 script

## client 

> 存放用于客户端的配置和SQL

- at: AT模式下的 `undo_log` 建表语句
``` $sql
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
- conf: 客户端的配置文件
- saga: SAGA 模式下所需表的建表语句
- spring: SpringBoot 应用支持的配置文件

## server

> 存放server侧所需SQL

- db: server 侧的保存模式为 `db` 时所需表的建表语句

## config-center

> 用于存放各种配置中心的初始化脚本，执行时都会读取 `config.txt`配置文件，并写入配置中心

- nacos: 用于向 Nacos 中添加配置

- 修改 conf/registry.conf 将registry下的 type 修改为nacos，以及把config下的type也改为nacos即可
- 导入配置config.txt 到nacos
``` $bash
sh nacos-config.sh localhost
```

-- 登陆Nacos 控制台 配置列表 筛选 Group=SEATA_GROUP 的配置项

## 启动seata
``` $bash
sh seata-server.sh -p 8091 -m file
```

