CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(128) DEFAULT NULL COMMENT '注册账号',
  `password` varchar(128) DEFAULT NULL COMMENT '密码，MD5加密',
  `login_session` varchar(40) DEFAULT NULL COMMENT '设备标示符，客户端每次登陆上传当前设备标识符。如果与当前标识符不同，服务器通过IM接口向客户端终端发送一条离线消息',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `account_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(128) DEFAULT NULL COMMENT '注册账号',
  `nickname` varchar(256) DEFAULT NULL COMMENT '用户昵称',
  `sex` int(11) DEFAULT NULL COMMENT '性别，0,男，1女 ',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `description` text COMMENT '个人签名',
  `image` text COMMENT '头像url ',
  `label` text COMMENT '个人标签',
  `fansnum` mediumtext COMMENT '粉丝总数',
  `score` mediumtext COMMENT '积分',
  `focusnum` mediumtext COMMENT '关注人数',
  `location` varchar(128) DEFAULT NULL COMMENT '所在地区',
  `videonums` mediumtext COMMENT '直播视频数目',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `barrage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `Video_ID` varchar(128) DEFAULT NULL COMMENT '被点赞视频id',
  `Content` text COMMENT '弹幕格式内容',
  `public_time` datetime DEFAULT NULL COMMENT '发布时间，服务器按入库时间自动生成',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `black_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(128) DEFAULT NULL COMMENT '账号',
  `blocked_account` varchar(128) DEFAULT NULL COMMENT '被黑账号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `account` varchar(128) DEFAULT NULL COMMENT '评论发起人账号',
  `comment_account` varchar(128) DEFAULT NULL COMMENT '被评论人账号',
  `content` text COMMENT '评论内容',
  `comment_videoid` varchar(128) DEFAULT NULL COMMENT '评论视频条目id',
  `nick` varchar(256) DEFAULT NULL COMMENT '评论人昵称',
  `image_url` text COMMENT '评论人头像url',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `expose` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Account` varchar(128) DEFAULT NULL COMMENT '举报人账号',
  `reported_account` varchar(128) DEFAULT NULL COMMENT '被举报账号',
  `Content` text COMMENT '举报说明  ',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Account` varchar(128) DEFAULT NULL COMMENT '粉丝账号',
  `Image` text COMMENT '头像url',
  `Nickname` varchar(256) DEFAULT NULL COMMENT '粉丝昵称',
  `IsFocused` int(11) DEFAULT NULL COMMENT '是否关注了对方，0否，1是',
  `Ower` varchar(128) DEFAULT NULL COMMENT '粉丝所属人账号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `focuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Account` varchar(128) DEFAULT NULL COMMENT '被关注人账号',
  `Image` text COMMENT '头像url',
  `Nickname` varchar(256) DEFAULT NULL COMMENT '关注账号昵称',
  `IsFocused` int(11) DEFAULT NULL COMMENT '对方是否关注了你，0否，1是',
  `IsSpecial` int(11) DEFAULT NULL COMMENT '是否特别关注，0，否，1是',
  `fansAccount` varchar(128) DEFAULT NULL COMMENT '关注人账号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Account` varchar(128) DEFAULT NULL COMMENT '账号',
  `LocationX` double DEFAULT NULL COMMENT 'X坐标',
  `LocationY` double DEFAULT NULL COMMENT 'Y坐标',
  `IsShow` int(11) DEFAULT NULL COMMENT '1表示其他人可以查看，0表示不可以',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `account` varchar(128) DEFAULT NULL COMMENT '点赞人账号 ',
  `video_id` varchar(128) DEFAULT NULL COMMENT '被点赞视频id ',
  `nick` varchar(256) DEFAULT NULL COMMENT '点赞人昵称 ',
  `image_url` text COMMENT '点赞人头像url',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `test_table` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `track_forbid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(128) DEFAULT NULL COMMENT '账号',
  `forbid_account` varchar(128) DEFAULT NULL COMMENT '被禁止账号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `videoid` varchar(40) DEFAULT NULL COMMENT '视频标示id，全局唯一',
  `account` varchar(128) DEFAULT NULL COMMENT '视频所属账号',
  `accountname` varchar(256) DEFAULT NULL COMMENT '视频所属账号昵称',
  `videosourcetype` int(11) DEFAULT NULL COMMENT '视频源类型，0直播，1点播',
  `videotitle` varchar(256) DEFAULT NULL COMMENT '视频标签',
  `videolable` text COMMENT '视频标签',
  `videotype` int(11) DEFAULT NULL COMMENT '视频所属类型，具体类型具体再定，服务器可动态增加，其中0表示全部',
  `videoimage` text COMMENT '视频第一帧图片url',
  `recomandtype` int(11) DEFAULT NULL COMMENT '推荐类型，0表示无，1表示推荐，显示荐，2表示点播人数多，显示热',
  `locationx` double DEFAULT NULL COMMENT '直播视频上传的X坐标',
  `locationy` double DEFAULT NULL COMMENT '直播视频上传的Y坐标，X,Y坐标仅在直播时候返回，当为点播时忽略此属性',
  `isbigvideo` int(11) DEFAULT NULL COMMENT '0,不是，1是',
  `viewurl` text COMMENT '仅当IsBigVideo为1的时候此属性有值。为一个15s以内的简短预览视频',
  `playurl` text COMMENT '视频源，当直播结束后，服务器自动将此视频url转为点播url',
  `ispublic` int(11) DEFAULT NULL COMMENT '是否公开，1是，0不是',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `video_properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `VideoID` varchar(40) DEFAULT NULL COMMENT '视频标示id，全局唯一',
  `Totals` mediumtext COMMENT '总观众数目',
  `BulletsNum` mediumtext COMMENT '弹幕数目',
  `UpNum` mediumtext COMMENT '赞数目',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `view_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(128) DEFAULT NULL COMMENT '账号',
  `videoid` varchar(128) DEFAULT NULL COMMENT '视频id',
  `type` int(11) DEFAULT NULL COMMENT '1,正在直播，2正在观看 ',
  `is_changed` int(11) DEFAULT NULL COMMENT '1有变化，0没变化',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
