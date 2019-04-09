package college.controller;

import college.result.ResultVO;
import college.annotation.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 个人信息
 * 
 * @author xuxb
 *
 */
@Controller
@RequestMapping("/personal")
public class PersonalInfoController {

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	@ResponseBody
	@Token(remove=true)
	public ResultVO doFillPersonalStudentInfo() {
		return new ResultVO();
	}
	
	
	
	@RequestMapping(value = "/gettoken", method = RequestMethod.GET)
	@ResponseBody
	@Token(save=true)
	public ResultVO doGetToken(HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		return new ResultVO("success", token);
	}
}
