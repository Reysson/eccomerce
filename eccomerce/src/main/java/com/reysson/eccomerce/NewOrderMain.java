package com.reysson.eccomerce;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        try (var orderDispatcher = new KafkaDispatcher<Order>()) {
            try(var emailDispatcher = new KafkaDispatcher<String>()){
                for (var i = 0; i < 10; i++) {
                    String userId = UUID.randomUUID().toString();
                    String orderId = UUID.randomUUID().toString();
                    BigDecimal value = BigDecimal.valueOf(Math.random() * 500 + 1);
                    var order = new Order(userId, orderId, value);
                    orderDispatcher.send("ECCOMERCE_NEW_ORDER", userId, order);

                    var email = "Welcome! Processing your order";
                    emailDispatcher.send("ECCOMERCE_SEND_EMAIL", userId, email);
                }
            }

        }
    }
}
