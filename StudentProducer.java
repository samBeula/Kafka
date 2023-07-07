package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.connect.json.JsonSerializer;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentProducer {
    public static void main(String[] args) throws InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        AtomicInteger errorCount= new AtomicInteger();
        AtomicInteger successCount=new AtomicInteger();
        Student s1=new Student("sam","gitam",99f);


        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        final KafkaProducer<String, JsonNode> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>("testtopic25", "1", mapper.valueToTree(s1)), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception e) {
                System.out.println("sent successfully");
                if (e != null) {
                    System.out.println("EXCEPTION");
                    errorCount.incrementAndGet();
            } else {
                successCount.incrementAndGet();
            }
            }
        });
        Thread.sleep(1000l);
    }
}