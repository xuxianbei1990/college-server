package college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Sample")
public class SampleController {

	@RequestMapping("/test/{id}")
	public String testSample(@PathVariable("id")Integer id) {
		return null;
	}
}
