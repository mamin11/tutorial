package theoneamin.com.tutorial.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Order;
import models.OrderKey;
import models.ShippedOrder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import theoneamin.com.tutorial.mapper.OrderValueMapper;

import java.util.Map;
import java.util.function.Function;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StreamProcessor {
    @Bean
    public Function<KStream<OrderKey, Order>, KStream<OrderKey, ShippedOrder>> process() {

        return input -> {
            Map<String, KStream<OrderKey, Order>> branchedStream = input
                    .split(Named.as("branch-"))
                    .branch((orderKey, order) -> order.isNextDay(), Branched.as("nextDay"))
                    .defaultBranch((Branched.as("normal")));

            KStream<OrderKey, Order> nextDayDeliveryStream = branchedStream.get("branch-nextDay")
                    .peek((orderKey, order) -> log.info("next-day-delivery stream: k: {}, v: {}", orderKey, order));

            KStream<OrderKey, Order> normalDeliveryStream = branchedStream.get("branch-normal")
                    .peek((orderKey, order) -> log.info("normal-delivery stream: k: {}, v: {}", orderKey, order));

          return nextDayDeliveryStream.merge(normalDeliveryStream).mapValues(new OrderValueMapper());
        };

    }
}
