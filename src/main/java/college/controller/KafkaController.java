package college.controller;

import college.DTO.risk.Behavior;
import college.DTO.risk.TokenFlow;
import college.DTO.risk.UserLogin;
import college.constant.Topics;
import com.alibaba.fastjson.JSON;
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

    private void kafkaFuture(ListenableFuture<SendResult<String, String>> future) {
        future.addCallback(success -> log.info("success:" + success.toString()),
                fail -> log.info("fail:" + fail.toString()));
    }

    @RequestMapping("/risk/login")
    @ResponseBody
    public String login(UserLogin login) {
        kafkaFuture(kafkaTemplate.send(Topics.USER_LOGIN, JSON.toJSONString(login)));
        return "ok";
    }

    /**
     * 提币
     *
     * @param flow
     */
    @RequestMapping("/risk/afterOut")
    @ResponseBody
    public String afterTokenOut(TokenFlow flow) {
        kafkaTemplate.send(Topics.TOKEN_OUT, JSON.toJSONString(flow));
        return "ok";
    }


    /**
     * 法币卖出
     *
     * @param flow
     */
    @RequestMapping("/risk/C2COut")
    @ResponseBody
    public String afterC2COut(TokenFlow flow) {
        kafkaTemplate.send(Topics.C2C_OUT, JSON.toJSONString(flow));
        return "ok";
    }

    /**
     * 法币买入
     *
     * @param flow
     */
    @RequestMapping("/risk/C2CIn")
    @ResponseBody
    public String afterC2CIn(TokenFlow flow) {
        kafkaTemplate.send(Topics.C2C_IN, JSON.toJSONString(flow));
        return "ok";
    }

    //****************报/撤单相关****************//

    /**
     * 币币报单
     *
     * @param behavior
     */
    @RequestMapping("/risk/afterNormal")
    @ResponseBody
    public String afterNormalOrderMake(Behavior behavior) {
        kafkaTemplate.send(Topics.NORMAL_ORDER_MAKE, JSON.toJSONString(behavior));
        return "ok";
    }

    /**
     * 杠杆报单
     *
     * @param behavior
     */
    @RequestMapping("/risk/afterLeverage")
    @ResponseBody
    public String afterLeverageOrderMake(Behavior behavior) {
        kafkaTemplate.send(Topics.LEVERAGE_ORDER_MAKE, JSON.toJSONString(behavior));
        return "ok";
    }

    /**
     * 合约报单
     * describe 只是挂单
     *
     * @param behavior
     */
    @RequestMapping("/risk/afterContract")
    @ResponseBody
    public String afterContractOrderMake(Behavior behavior) {
        kafkaTemplate.send(Topics.CONTRACT_ORDER_MAKE, JSON.toJSONString(behavior));
        return "ok";
    }

    //****************开/平仓相关****************//

    /**
     * 杠杆平仓
     *
     * @param behavior
     */
    @RequestMapping("/risk/afterLeverage/PositionClose")
    @ResponseBody
    public String afterLeveragePositionClose(Behavior behavior) {
        kafkaTemplate.send(Topics.LEVERAGE_POSITION_CLOSE, JSON.toJSONString(behavior));
        return "ok";
    }

    /**
     * 合约平仓
     * describe: 只是交易。
     *
     * @param behavior
     */
    @RequestMapping("/risk/afterContract/PositionClose")
    @ResponseBody
    public String afterContractPositionClose(Behavior behavior) {
        kafkaTemplate.send(Topics.CONTRACT_POSITION_CLOSE, JSON.toJSONString(behavior));
        return "ok";
    }

}
