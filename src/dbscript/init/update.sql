-- 添加新字段：
alter table `lock_info` add `qr_code_no` varchar(64) DEFAULT NULL COMMENT '二维码编号';