package college.dao;

import college.login.UserPo;

public interface LoginMapper {

	UserPo selectUser(UserPo userPo);
}
