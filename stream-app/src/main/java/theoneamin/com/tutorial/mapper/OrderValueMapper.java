package theoneamin.com.tutorial.mapper;

import lombok.extern.slf4j.Slf4j;
import models.Order;
import models.ShippedOrder;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Random;

@Slf4j
public class OrderValueMapper implements ValueMapper<Order, ShippedOrder> {
    @Override
    public ShippedOrder apply(Order order) {
        ShippedOrder shippedOrder = new ShippedOrder();
        BeanUtils.copyProperties(order, shippedOrder);

        if (BigDecimal.valueOf(Float.parseFloat(shippedOrder.getCost())).compareTo(BigDecimal.valueOf(50)) > 0) {
            log.info("Order: {} qualifies for free shipping. total greater than 50", order.getOrderId());
            shippedOrder.setFreeShipping(true);
        }

        shippedOrder.setTrackingNumber("TR"+new Random().nextGaussian());

        return shippedOrder;
    }
}
