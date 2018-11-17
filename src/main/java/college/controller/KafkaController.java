package college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/kafka")
public class KafkaController {
	
//	@Autowired
//	KafkaTemplate<Integer, String> kafkaTemplate;
	
	@RequestMapping("/test")
	@ResponseBody
	public String doTest() {
//		kafkaTemplate.send("test", "Hello world  ");
		return "";
		
	}

}
