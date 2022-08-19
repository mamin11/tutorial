package theoneamin.com.tutorial.batch.processor;

import lombok.extern.slf4j.Slf4j;
import models.Order;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class OrderProcessor implements ItemProcessor<Order, Order> {
    @Override
    public Order process(Order order) throws Exception {
        if (order.isNextDay()) {
            log.info("Do some processing");
        }

        return order;
    }
}
