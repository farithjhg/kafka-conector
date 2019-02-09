package com.wolfsoft.kafkaconnector.service;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import com.wolfsoft.kafkaconnector.consumer.Receiver;
import com.wolfsoft.kafkaconnector.dto.AvroObject;
import com.wolfsoft.kafkaconnector.producer.Sender;

import lombok.extern.slf4j.Slf4j;
import myasesor.server.fe.util.Utilidades;

@RestController
@Slf4j
@RequestMapping("/kafka")
public class KafkaRestController {
	@Autowired
	private Sender sender;

	@Value("${kafka.cloudkarafka.topicOut}")
	private String topic;

	/**
	 * Kafka Produce Services
	 * 
	 * @param producerRecord
	 *            - Record with Header and Body info
	 * @return ResponseEntity<String>
	 */
	@PostMapping(value = "/produce")
	public @ResponseBody ResponseEntity<String> produceRest(@RequestBody String message) {
		String response = "";
		try {
			sender.sendAsString(topic, message);

			response = "Message Published";
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	/**
	 * Kafka Produce Services
	 * 
	 * @param producerRecord
	 *            - Record with Header and Body info
	 * @return ResponseEntity<String>
	 */
	@PostMapping(value = "/produce/avro")
	public @ResponseBody ResponseEntity<String> produceAvro(@RequestBody AvroObject message) {
		String response = "";
		try {
			log.info("Message received ='{}'", message);	
			Gson gson = new Gson();
			sender.sendAsBytes(topic, Utilidades.getData(gson.toJson(message.getHeader()), message.getPayload()));
			log.info("Enviado a Kafka, Topic: "+topic);

			response = "Message AVRO Published";
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/validate/{sessionId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> validate(@PathVariable String sessionId){
		
		return new ResponseEntity<String>("Session Id: "+sessionId, HttpStatus.OK);
	}

}
