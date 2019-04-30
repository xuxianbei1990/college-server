package college.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

/**
 * Name
 *
 * @author xxb
 * Date 2019/4/22
 * VersionV1.0
 * @description 风控模拟消费
 * Topics.TOKEN_OUT
 * Topics.NORMAL_ORDER_MAKE
 */
//@Service
@Slf4j
public class KafkaRiskConsumerService {

    @KafkaListener(topics = Topics.USER_LOGIN, containerFactory = "getKafkaListenerContainerFactory", groupId = "risk")
    public void userLogin(ConsumerRecord<String, String> msg, @Headers MessageHeaders headers) {
        log.info("消费消息" + msg.value());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = Topics.TOKEN_OUT, containerFactory = "getKafkaListenerContainerFactory", groupId = "risk")
    public void toenOut(ConsumerRecord<String, String> msg) {
        log.info("消费消息" + msg.value());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = Topics.NORMAL_ORDER_MAKE, containerFactory = "getKafkaListenerContainerFactory", groupId = "risk")
    public void orderMake(ConsumerRecord<String, String> msg) {
        log.info("消费消息" + msg.value());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
