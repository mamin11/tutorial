package theoneamin.com.tutorial.config;

import lombok.extern.slf4j.Slf4j;
import models.Order;
import models.OrderKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    // Read kafka topic name from properties file
    @Value("${theoneamin.code.kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<OrderKey, Order> kafkaTemplate;


    public void sendMessageToBalanceTransaction(OrderKey key, Order order) {
        log.info(" Sending order {} to Kafka topic {} ", order, topic);
        this.kafkaTemplate.send(this.topic, key, order);
    }
}
