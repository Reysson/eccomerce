package com.reysson.eccomerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {

    public static void main(String[] args) {
        var emailService = new EmailService();
        try(var service = new KafkaService(EmailService.class.getSimpleName(),"ECCOMERCE_SEND_EMAIL",emailService::parse)){
            service.run();
        }
    }

    private void parse(ConsumerRecord<String,String> records) {
        System.out.println("------------------------------------------");
        System.out.println("sending email");
        System.out.println(records.key());
        System.out.println(records.value());
        System.out.println(records.partition());
        System.out.println(records.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email sending.");
    }
}
