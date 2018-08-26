package com.wolfsoft.kafkaconnector.consumer;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import com.wolfsoft.kafkaconnector.service.KafkaRestController;

@Service
public class Receiver {
	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);
	
	@KafkaListener(topics = "bvnnlu23-default")
	public void listen(byte[] avroRecord) {
		logger.info("Message received ='{}'", avroRecord);
		
		Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(KafkaRestController.USER_SCHEMA);
        Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);
        GenericRecord record = recordInjection.invert(avroRecord).get();

        System.out.println("emailTo= " + record.get("emailTo")
                + ", message= " + record.get("message"));		
	}
}
