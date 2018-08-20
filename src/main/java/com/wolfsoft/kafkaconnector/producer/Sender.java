package com.wolfsoft.kafkaconnector.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaProducer<String, EmailConfig> producer;

    
    public void sendAsString(String topic, String message){
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }
    
    public void sendAsBytes(String topic, EmailConfig message){
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        
        ProducerRecord<String, EmailConfig> record = new ProducerRecord<>(topic, message);
        producer.send(record);
    }
    
}