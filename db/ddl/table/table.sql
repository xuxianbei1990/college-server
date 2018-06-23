#测试表
CREATE TABLE `sample` (
  `id` int(11) NOT NULL,
  `name` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `course` (
  `c_id` int(11) NOT NULL comment '课程id',
  `name` varchar(10) DEFAULT NULL '课程名称',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

CREATE TABLE `student` (
  `id` int(11) NOT NULL comment '学生id',
  `name` varchar(10) CHARACTER SET utf8 DEFAULT NULL comment '学生姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

#学生临时表，用于生成70000数据
drop table if exists student_memory;
create table student_memory (
	id int(11) not null primary key auto_increment,
    name varchar(10) 
) engine = InnoDB AUTO_INCREMENT=1 DEFAULT charset = utf8mb4


CREATE TABLE `sc` (
  `sc_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩id',
  `s_id` int(11) DEFAULT NULL COMMENT '学生id',
  `c_id` int(11) DEFAULT NULL COMMENT '课程id',
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`sc_id`),
  KEY `cid_index` (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12058441 DEFAULT CHARSET=utf8mb4 COMMENT='成绩表';


