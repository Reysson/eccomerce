package com.reysson.eccomerce;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        try (var dispatcher = new KafkaDispatcher()) {

            for (var i = 0; i < 10; i++) {
                String key = UUID.randomUUID().toString();
                String value = key + ",456,789";
                dispatcher.send("ECCOMERCE_NEW_ORDER", key, value);

                var email = "Welcome! Processing your order";
                dispatcher.send("ECCOMERCE_SEND_EMAIL", key, email);
            }
        }
    }
}
