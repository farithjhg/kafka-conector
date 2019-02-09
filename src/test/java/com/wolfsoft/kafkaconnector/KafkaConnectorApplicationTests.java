package com.wolfsoft.kafkaconnector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wolfsoft.kafkaconnector.dto.AvroObject;

import lombok.extern.slf4j.Slf4j;
import myasesor.server.fe.model.AvroHeader;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaConnectorApplicationTests {

	@Test
	public void contextLoads() {
		AvroHeader avroHeader = new AvroHeader();
		avroHeader.setOrigin("ublTransfromedDocument");
		avroHeader.setProcessId("982792789");
		avroHeader.setClientId("7597133");
		AvroObject avro = new AvroObject();
		avro.setHeader(avroHeader);
		Gson gson =new Gson();
		
		log.info("JSON "+gson.toJson(avro).toString());
	}

}
