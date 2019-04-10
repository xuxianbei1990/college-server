package college.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import javax.annotation.Resource;

/**
 * Name
 *
 * @author xxb
 * Date 2019/4/10
 * VersionV1.0
 * @description
 */
@Configuration
//@EnableKafka
public class KafkaConfig {

    @Resource
    private ConsumerFactory<String, String> consumerFactory;

    @Bean/*("kafkaListenerContainerFactory")*/
//    @Primary
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> getKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(2); // 小于或者等于分区
//        factory.setBatchListener(true);
//        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

}
