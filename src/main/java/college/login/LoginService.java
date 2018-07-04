package college.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import college.dao.LoginMapper;

@Service
public class LoginService {
	
	@Autowired
	LoginMapper loginMapper;
	
	public boolean doLogin(UserPo userPo) {
		return null != loginMapper.selectUser(userPo);
	}
}
