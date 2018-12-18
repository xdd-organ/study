CREATE TABLE article
(
  id            INT AUTO_INCREMENT
    PRIMARY KEY,
  title         VARCHAR(64)                         NULL
  COMMENT '文章标题',
  type          INT                                 NULL
  COMMENT '文章类型，0：新闻，1：视频',
  content       VARCHAR(2048)                       NULL
  COMMENT '内容',
  insert_author INT                                 NULL,
  insert_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_author INT                                 NULL,
  update_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  state         INT DEFAULT '0'                     NULL
  COMMENT '0:有效,1:删除',
  status        INT DEFAULT '0'                     NULL
  COMMENT '0:待审核，1：审核通过，2：审核不通过',
  comment        INT DEFAULT '0'                     NULL
  COMMENT '评论数量',
  star        INT DEFAULT '0'                     NULL
  COMMENT '点赞数',
  collect        INT DEFAULT '0'                     NULL
  COMMENT '收藏数',
  self_star        INT DEFAULT '0'                     NULL
  COMMENT '0:自己未点赞,1:自己点赞',
  self_collect        INT DEFAULT '0'                     NULL
  COMMENT '0:自己未收藏,1:自己收藏',
  img_urls        VARCHAR(1024)                  NULL
  COMMENT '图片url',
  cover_urls        VARCHAR(1024)                  NULL
  COMMENT '封面图片url',
  CONSTRAINT article_id_uindex
  UNIQUE (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='文章';

CREATE TABLE article_comment
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  article_id  INT                                 NULL
  COMMENT '文章id',
  user_id     INT                                 NULL
  COMMENT '评论人id',
  to_user_id  INT                                 NULL
  COMMENT '评论谁',
  content     VARCHAR(128)                       NULL
  COMMENT '评论内容',
  state       INT                                 NULL,
  insert_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT article_comment_id_uindex
  UNIQUE (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='文章评论';

CREATE TABLE article_info
(
  id         INT AUTO_INCREMENT
    PRIMARY KEY,
  article_id INT                                     NULL,
  user_id    INT                                     NULL,
  star       INT DEFAULT '0'                         NULL
  COMMENT '0:未点赞，1：点赞',
  star_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP     NOT NULL
  COMMENT '点赞时间',
  collect      INT DEFAULT '0'                         NULL
  COMMENT '0：未收藏，1，收藏',
  collect_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '收藏时间',
  CONSTRAINT article_info_id_uindex
  UNIQUE (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='文章信息';;

 CREATE TABLE `dictionary` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `name` varchar(32) DEFAULT NULL COMMENT '字典名称',
   `key` varchar(32) DEFAULT NULL COMMENT '字典key',
   `value` varchar(1024) DEFAULT NULL COMMENT '字典value',
   `status` int(11) DEFAULT '0' COMMENT '0：有效，1删除',
   `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `insert_author` int(11) DEFAULT NULL,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_author` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='数据字典';

CREATE TABLE `user` (
  id            INT AUTO_INCREMENT
  COMMENT '主键'
    PRIMARY KEY,
  username      VARCHAR(32)                         NULL
  COMMENT '用户名',
  password      VARCHAR(64)                         NULL
  COMMENT '密码',
  telphone      VARCHAR(16)                         NULL
  COMMENT '电话',
  avatar        VARCHAR(64)                         NULL
  COMMENT '头像',
  register_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '注册时间',
  is_disable    INT DEFAULT '0'                     NULL
  COMMENT '1：禁用，0：启用',
  state        INT DEFAULT '0'                     NULL
  COMMENT '0:有效，1:删除',
  status        INT DEFAULT '0'                     NULL
  COMMENT '0:未认证，1:已认证，2：认证失败',
  insert_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  insert_author INT                                 NULL,
  update_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
  update_author INT                                 NULL,
  nickname      VARCHAR(32)                         NULL
  COMMENT '昵称',
  session_key   VARCHAR(32)                         NULL
  COMMENT '微信session_key',
  openid        VARCHAR(64)                         NULL,
  unionId       VARCHAR(64)                         NULL,
  gender        INT                                 NULL
  COMMENT '值为1时是男性，值为2时是女性，值为0时是未知',
  type          INT DEFAULT '0'                     NULL
  COMMENT '0:教师，1：家长，2：学生，3：头条用户'
) ENGINE=InnoDB AUTO_INCREMENT=100000001 DEFAULT CHARSET=utf8mb4 AVG_ROW_LENGTH=1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户';

CREATE TABLE `file` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `key` varchar(32) DEFAULT NULL COMMENT '文件唯一标识',
   `name` varchar(128) DEFAULT NULL COMMENT '本地文件名',
   `file_name` varchar(128) DEFAULT NULL COMMENT '文件名',
   `url` varchar(1024) DEFAULT NULL COMMENT '文件地址',
   `size` int(11) DEFAULT NULL COMMENT '文件大小 KB',
   `status` int(11) DEFAULT '0' COMMENT '0：有效，1：删除',
   `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `insert_author` int(11) DEFAULT '0',
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_author` int(11) DEFAULT '0',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='文件';

CREATE TABLE `wx_pay_info` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `appid` varchar(64) DEFAULT NULL COMMENT '小程序ID',
   `mch_id` varchar(64) DEFAULT NULL COMMENT '商户号',
   `nonce_str` varchar(64) DEFAULT NULL COMMENT '随机字符串',
   `sign` varchar(64) DEFAULT NULL COMMENT '签名',
   `body` varchar(256) DEFAULT NULL COMMENT '商品描述',
   `out_trade_no` varchar(64) DEFAULT NULL COMMENT '商户订单号',
   `total_fee` int(11) DEFAULT NULL COMMENT '订单总金额，单位为分',
   `spbill_create_ip` varchar(32) DEFAULT NULL COMMENT 'APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。',
   `time_expire` varchar(16) DEFAULT NULL COMMENT '订单失效时间，格式为yyyyMMddHHmmss',
   `notify_url` varchar(512) DEFAULT NULL COMMENT '通知地址',
   `trade_type` varchar(32) DEFAULT NULL COMMENT '交易类型,小程序取值如下：JSAPI',
   `openid` varchar(256) DEFAULT NULL COMMENT '用户标识,trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识',
   `result` varchar(16) DEFAULT NULL COMMENT '支付结果：SUCCESS/FAIL',
   `time_end` varchar(16) DEFAULT NULL COMMENT '支付完成时间，格式为yyyyMMddHHmmss',
   `transaction_id` varchar(64) DEFAULT NULL COMMENT '微信支付订单号',
   `user_id` int(11) DEFAULT NULL COMMENT '用户id',
   `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='微信支付信息';

CREATE TABLE `trans_flow_info` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `type` int(11) DEFAULT '0' COMMENT '0:消费，1：退费，2：押金，3：退押金，4：充值',
   `fee` int(11) DEFAULT '0' COMMENT '金额：单位：分',
   `desc` varchar(256) DEFAULT NULL COMMENT '描述',
   `status` int(11) DEFAULT '0' COMMENT '0:有效，1：删除',
   `user_id` int(11) DEFAULT NULL COMMENT '用户id',
   `insert_author` int(11) DEFAULT NULL,
   `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `update_author` int(11) DEFAULT NULL,
   `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='交易流水';

CREATE TABLE `fault_feedback` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `user_id` int(11) DEFAULT NULL,
   `type` int(11) DEFAULT '0' COMMENT '处理状态，0：未处理，1：已处理',
   `result` varchar(512) DEFAULT NULL COMMENT '处理结果描述',
   `fault_type` int(11) DEFAULT NULL COMMENT '0：，1：，2：，3：',
   `device_no` varchar(64) DEFAULT NULL COMMENT '设备编号',
   `imgs` varchar(512) DEFAULT NULL COMMENT '图片',
   `desc` varchar(512) DEFAULT NULL COMMENT '备注',
   `status` int(11) DEFAULT '0' COMMENT '0：有效，1：删除',
   `insert_author` int(11) DEFAULT NULL,
   `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `update_author` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='问题反馈';

CREATE TABLE user_watch
(
  id            INT AUTO_INCREMENT
    PRIMARY KEY,
  user_id       INT                                 NULL
  COMMENT '被关注用户id',
  watch_user_id INT                                 NULL
  COMMENT '关注用户id',
  watch_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT user_watch_id_uindex
  UNIQUE (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户关注';
