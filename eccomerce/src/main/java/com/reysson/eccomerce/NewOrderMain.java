package com.reysson.eccomerce;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class NewOrderMain {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var producer = new KafkaProducer<String,String>(properties());
        String key = UUID.randomUUID().toString();
        String value = key+",456,789";
        var record = new ProducerRecord("ECCOMERCE_NEW_ORDER",key,value);

        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println(data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
        };
        var email = "Welcome! Processing your order";
        var emailRecorded = new ProducerRecord<>("ECCOMERCE_SEND_EMAIL",email,email);

        producer.send(record, callback).get();
        producer.send(emailRecorded).get();
    }

    static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
