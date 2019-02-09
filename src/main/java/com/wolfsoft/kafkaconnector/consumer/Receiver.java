package com.wolfsoft.kafkaconnector.consumer;

import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import myasesor.server.fe.model.AvroHeader;
import myasesor.server.fe.util.Utilidades;

@Service
@Slf4j
public class Receiver {
	
	//@KafkaListener(topics = "${kafka.cloudkarafka.topicIn}")
	public void listen(byte[] avroRecord) {
		log.info("Message avro received ='{}'", avroRecord);	
        GenericRecord record = Utilidades.getRecord(avroRecord);
        
        String headerRecord = record.get("header").toString();
        String payload = record.get("payload").toString();
		log.info("Message header ='{}'", headerRecord);	
		log.info("Message payload ='{}'", payload);
        
		AvroHeader avroHeader = new Gson().fromJson(headerRecord, AvroHeader.class);
		log.info("Message AvroHeader ='{}'", avroHeader);	
	}
}
