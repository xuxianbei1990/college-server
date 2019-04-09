package college.dao.classes;

import college.classes.Classes;
import org.apache.ibatis.annotations.Param;

/**
 * User: Administrator
 * Date: 2018/8/10
 * Time: 23:01
 * Version:V1.0
 */
public interface ClassInfoMapper {

    Classes getClass(@Param("classid") int classid);

    Classes getClassA(@Param("classid") int classid);
}
