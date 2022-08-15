//package theoneamin.com.tutorial.api;
//
//import lombok.extern.slf4j.Slf4j;
//import models.Order;
//import models.OrderKey;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import theoneamin.com.tutorial.config.KafkaProducer;
//
//@RestController
//@CrossOrigin("*")
//@Slf4j
//public class Controller {
//    @Autowired
//    KafkaProducer kafkaProducer;
//
//    @PostMapping("/order")
//    @ResponseStatus(HttpStatus.OK)
//    public void sendOrder(@RequestBody Order order) {
//        OrderKey key = OrderKey.builder().build();
//
//        kafkaProducer.sendMessageToBalanceTransaction(key, order);
//        log.info("Sent message: {}", order);
//    }
//
//}
