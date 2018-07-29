package college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import college.log.MethodLog;
import college.po.SamplePo;
import college.service.SampleService;
import college.utils.RedisUtil;

@Controller
@RequestMapping("/sample")
public class SampleController {

	@Autowired
	SampleService sampleService;
	
	@Autowired
	RedisUtil redisCacheManager;
	
	@RequestMapping("/test/{id}")
	@ResponseBody
	public SamplePo testSample(@PathVariable("id")Integer id) {
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
}
