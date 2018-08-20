package com.wolfsoft.kafkaconnector.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {
	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);
	
	@KafkaListener(topics = "bvnnlu23-default")
	public void listen(@Payload String message) {
		logger.info("Message received ='{}'", message);
	}
}
