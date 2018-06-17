#返回指定随机字符串
CREATE FUNCTION `rand_string` (n int)
RETURNS varchar(255) charset 'utf8'
BEGIN
  declare char_str varchar(100) default 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
  declare return_str varchar(255) default '';
  declare i int default 0;
  while i < n do
	set return_str = concat(return_str, substring(char_str, FLOOR(1 + RAND() * 62), 1));
    set i = i + 1;
  end while;
  return return_str;
END
