package college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import college.log.MethodLog;
import college.po.SamplePo;
import college.service.SampleService;

@Controller
@RequestMapping("/sample")
public class SampleController {

	@Autowired
	SampleService sampleService;
	
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
}
