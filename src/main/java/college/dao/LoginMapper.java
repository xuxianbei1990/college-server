package college.dao;

import college.login.UserPo;

import java.util.List;

public interface LoginMapper {

    UserPo selectUser(UserPo userPo);

    List<UserPo> selectAll(Integer count);
}
