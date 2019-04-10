package college.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * Name
 *
 * @author xxb
 * Date 2019/4/9
 * VersionV1.0
 * @description https://www.cnblogs.com/scode2/p/8984937.html
 */
@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "test", containerFactory = "getKafkaListenerContainerFactory", groupId = "test")
    public void receive(@Payload String message, @Headers MessageHeaders headers,
                        ConsumerRecord<String, String> msg/*, Acknowledgment ack*/) {
        log.info("KafkaMessageCOnsumer 接收到消息:" + message);
        headers.keySet().forEach(key -> log.info("第一个消费对象{}:{}", key, headers.get(key)));
    }

//    @KafkaListener(topics = "test", containerFactory = "getKafkaListenerContainerFactory", groupId = "test")
//    public void receive2(@Payload String message, @Headers MessageHeaders headers, ConsumerRecord<String, String> msg,
//                         Acknowledgment ack) {
//        log.info("KafkaMessageCOnsumer 接收到消息:" + message + "partition"  + msg.partition());
////        headers.keySet().forEach(key -> log.info("第二个消费对象{}:{}", key, headers.get(key)));
//    }
//
//    @KafkaListener(topics = "test", containerFactory = "getKafkaListenerContainerFactory", groupId = "test")
//    public void receive3(@Payload String message, @Headers MessageHeaders headers) {
//        log.info("KafkaMessageCOnsumer 接收到消息:" + message);
//        headers.keySet().forEach(key -> log.info("第三个消费对象{}:{}", key, headers.get(key)));
//    }
//
//    @KafkaListener(topics = "test", containerFactory = "getKafkaListenerContainerFactory", groupId = "test")
//    public void receive4(@Payload String message, @Headers MessageHeaders headers) {
//        log.info("KafkaMessageCOnsumer 接收到消息:" + message);
//        headers.keySet().forEach(key -> log.info("第四个消费对象{}:{}", key, headers.get(key)));
//    }
}
