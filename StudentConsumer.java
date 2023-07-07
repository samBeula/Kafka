package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.kafka.connect.json.JsonSerializer;

import java.util.Arrays;
import java.util.Properties;

public class StudentConsumer {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String topic = "testtopic25";
        String group = "cgrp1";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", group);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                JsonDeserializer.class.getName());
        KafkaConsumer<String, JsonNode> consumer = new KafkaConsumer<String, JsonNode>(props);

        consumer.subscribe(Arrays.asList(topic));
        System.out.println("Subscribed to topic " + topic);
        int i = 0;

        while (true) {
            ConsumerRecords<String, JsonNode> records = consumer.poll(100);
            for (ConsumerRecord<String, JsonNode> record : records){
                Student s=mapper.treeToValue(record.value(),Student.class);
                System.out.printf("offset = %d, key = %s, values: college = %s, name = %s, percentage = %f\n",
                        record.offset(), record.key(), s.getCollege(),s.getName(),s.getPercentage());
        }
    }
}
}