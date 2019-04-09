package college.login;

import college.dao.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Autowired
    LoginMapper loginMapper;
	
	public boolean doLogin(UserPo userPo) {
		return null != loginMapper.selectUser(userPo);
	}
}
