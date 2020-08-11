package college.login;

import college.dao.LoginMapper;
import college.dao.TxTestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginService {

    @Resource
    LoginMapper loginMapper;

    @Resource
    TxTestMapper txTestMapper;

    public boolean doLoginSleep(UserPo userPo) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null != loginMapper.selectUser(userPo);
    }

    public boolean doLogin(UserPo userPo) {
        return null != userPo;
    }

    public List<UserPo> selectAll(Integer dbCount) {
        return loginMapper.selectAll(dbCount);
    }

    public String loginDb(Integer dbCount) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<UserPo> list = loginMapper.selectAll(dbCount);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long start = System.currentTimeMillis();
        list.forEach(userPo -> loginMapper.selectUser(userPo));
        return "ok:" + (System.currentTimeMillis() - start) + "ms";
    }

    public String insert() {
        return String.valueOf(txTestMapper.insert(1, 1));
    }
}
