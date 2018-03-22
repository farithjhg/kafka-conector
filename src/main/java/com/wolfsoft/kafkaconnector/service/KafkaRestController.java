package com.wolfsoft.kafkaconnector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wolfsoft.kafkaconnector.producer.Sender;

@RestController
@RequestMapping("/kafka")
public class KafkaRestController {
    @Autowired
    private Sender sender;
    
    @Value("${kafka.cloudkarafka.topic}")
    private String topic;
    
	/**
	 * Kafka Produce Services
	 * @param producerRecord - Record with Header and Body info
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/produce", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> produceRest(@RequestBody String message) {
		String response = "";
		try {
			//Call Produce Process
			sender.send(topic, message);
			
			response = "Message Published";
		} catch (Exception e) {
	        return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

        return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
