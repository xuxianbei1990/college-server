#初始化100门课程
CREATE DEFINER=`root`@`localhost` PROCEDURE `initcourse`()
BEGIN
declare i int ;
set i = 0;

start transaction; 

while i <= 100 DO
insert into course(c_id, name) value (i, CONCAT('语文', i));
set i = i +1;
END while;
commit;

END

#批量生成学生数据
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_student_memory`(IN n int)
BEGIN
  declare i int default 1;
  declare id int default 0;
  while i < n do
  set id = floor (1 + rand() * 10000);
  insert into student_memory values (null, rand_string(8));
  set i = i + 1;
  end while;
END
