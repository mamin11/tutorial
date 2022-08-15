package theoneamin.com.tutorial.batch;

import models.Order;
import models.OrderKey;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import theoneamin.com.tutorial.config.KafkaProducer;

import java.util.List;

public class OrdersWriter implements ItemWriter<Order> {
    @Autowired
    KafkaProducer kafkaProducer;


    @Override
    public void write(List<? extends Order> orders) throws Exception {
        orders.forEach(order -> kafkaProducer
                .sendMessageToBalanceTransaction(
                        OrderKey.builder().orderId(order.getOrderId()).shipDate(order.getShipDate()).build(),
                order));
    }
}
