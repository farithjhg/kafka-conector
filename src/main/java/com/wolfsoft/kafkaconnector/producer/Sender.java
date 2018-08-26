package com.wolfsoft.kafkaconnector.producer;

import org.apache.avro.generic.GenericData.Record;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, byte[]> producer;

    
    public void sendAsString(String topic, String message){
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        //kafkaTemplate.send(topic, message);
    }
    
    public void sendAsBytes(String topic, byte[] data){
        LOG.info("sending message='{}' to topic='{}'", data, topic);
        
        ProducerRecord<String, byte[]> record = new ProducerRecord<>(topic, data);
        producer.send(record);
    }
    
}