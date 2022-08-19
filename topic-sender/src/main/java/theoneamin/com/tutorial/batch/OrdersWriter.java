package theoneamin.com.tutorial.batch;

import lombok.extern.slf4j.Slf4j;
import models.Order;
import models.OrderKey;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import theoneamin.com.tutorial.config.KafkaProducer;

import java.util.List;

@Slf4j
public class OrdersWriter implements ItemWriter<Order> {
    @Autowired
    KafkaProducer kafkaProducer;


    @Override
    public void write(List<? extends Order> orders) throws Exception {
        orders.forEach(order -> log.debug("item writer: {}", order));
    }
}
