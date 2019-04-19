package college.controller;

import college.annotation.ActionDetail;
import college.annotation.MethodLog;
import college.po.SamplePo;
import college.service.SampleService;
import college.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    RedisUtil redisCacheManager;

    @RequestMapping("/test/{id}")
    @ResponseBody
    public SamplePo testSample(@PathVariable("id") Integer id) {
        return sampleService.testSample(id);
    }

    @MethodLog("sample")
    @RequestMapping("/test/aspect")
    @ResponseBody
    public SamplePo testAspect() {
        return sampleService.testSample(1);
    }

    @RequestMapping("/test/redis/set")
    public void testSetRedis() {
        redisCacheManager.set("adf", 1);
    }

    @RequestMapping("/test/redis/get")
    @ResponseBody
    public String testGetRedis() {
        return redisCacheManager.get("adf").toString();
    }

    @GetMapping("/aop/detail")
    @ActionDetail(value = "xxb")
    public String annotationDetail(HttpServletRequest request, String id) {
        return id + request.getRequestedSessionId();
    }
}
