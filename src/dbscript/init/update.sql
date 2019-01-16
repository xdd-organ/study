-- 添加新字段：
alter table `user` add `ticket` varchar(32) DEFAULT NULL COMMENT 'ticket';
alter table `user` add `score` int DEFAULT 0 COMMENT '分';

alter table `article` add `article_type` INT DEFAULT 0 COMMENT '0:资讯,1朋友圈:,2:做题';
alter table `article` add `category` INT DEFAULT 0 COMMENT '0:学生,1:老师:,2:家长,3:教育,4:动漫,5:音乐';

alter table `article_comment` add `img_urls` varchar(1024) DEFAULT NULL COMMENT '文件url';

alter table `institute` add `img_urls` varchar(1024) DEFAULT NULL COMMENT '文件url';
alter table `institute` add `institute_type` varchar(64) DEFAULT NULL COMMENT '大分类';
alter table `institute` add `institute_type_child` varchar(256) DEFAULT NULL COMMENT '小分类';
alter table `institute` add `status` int(11) DEFAULT 0 COMMENT '0:待审核，1：审核通过，2：审核不通过';
