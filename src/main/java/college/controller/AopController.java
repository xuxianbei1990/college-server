package college.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Name
 *
 * @author xxb
 * Date 2019/4/11
 * VersionV1.0
 * @description
 */
@RestController
@RequestMapping("/aop")
public class AopController {

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }

}
