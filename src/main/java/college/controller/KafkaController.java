package college.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/test/send")
    @ResponseBody
    public String doTest() {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("test", "Hello world  ");
        future.addCallback(success -> log.info("success:" + success.toString()), fail -> log.info("fail:0"));
        return "";

    }

}
