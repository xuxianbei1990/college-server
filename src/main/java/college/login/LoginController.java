package college.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
    LoginService loginService;
	
	@RequestMapping("/do")
	public boolean doLogin(UserPo userPo) {
		return loginService.doLogin(userPo);
	}
}
