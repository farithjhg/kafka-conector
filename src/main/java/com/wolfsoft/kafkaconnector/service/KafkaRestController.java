package com.wolfsoft.kafkaconnector.service;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import com.wolfsoft.kafkaconnector.producer.Sender;

@RestController
@RequestMapping("/kafka")
public class KafkaRestController {
	@Autowired
	private Sender sender;

	@Value("${kafka.eventhub.topic}")
	private String topic;

	public static final String USER_SCHEMA = "{" 
				+ "\"type\":\"record\"," + "\"name\":\"communication_bb\","
				+ "\"fields\":[" 
					+ "  { \"name\":\"emailTo\", \"type\":\"string\" },"
					+ "  { \"name\":\"message\", \"type\":\"string\" }" 
				+ "]}";
	/**
	 * Kafka Produce Services
	 * 
	 * @param message
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/produce/avro", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> produceAvro(@RequestBody String message) {
		String response = "";
		try {
			Schema.Parser parser = new Schema.Parser();
			Schema schema = parser.parse(USER_SCHEMA);
			Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);
            
			GenericData.Record avroRecord = new GenericData.Record(schema);
            avroRecord.put("emailTo", "fheras.garcia@gmail.com");
            avroRecord.put("message", message);
            //EmailConfig emailConfig = new EmailConfig( "fheras.garcia@gmail.com", message);
            byte[] data = recordInjection.apply(avroRecord);
			sender.sendAsBytes(topic, data);

			response = "Message AVRO Published";
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
