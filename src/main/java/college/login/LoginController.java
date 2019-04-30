package college.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    //首先要明白从一个请求过来需要经过tomcat，然后经过业务代码，然后再到数据库。

    //如果每个请求延迟0.001s那么10万请求过来，就会导致16分钟延迟。
    // 那么这对服务器是绝对不允许的 这里的压力应该不是数据库
    @RequestMapping("/do/sleep")
    public String doLoginSleep(UserPo userPo) {
        return loginService.doLoginSleep(userPo) ? "ok" : "fail";
    }

    //即使没有延迟，好像也只能承受住3200请求，之后的请求都会超过1.5s
    @RequestMapping("/do")
    public String doLogin(UserPo userPo) {
        return loginService.doLogin(userPo) ? "ok" : "fail";
    }

    //
    @GetMapping("/db")
    public String doLoginDb(Integer dbCount) {
        return loginService.loginDb(dbCount);
    }

    @GetMapping("/getUser")
    public List<UserPo> getUser() {
        return loginService.selectAll(10);
    }

    @GetMapping("/insert")
    public String insert() {
        try {
            return loginService.insert();
        } catch (DuplicateKeyException e) {
            System.out.println("success");
        }
        return "ok";
    }

}
