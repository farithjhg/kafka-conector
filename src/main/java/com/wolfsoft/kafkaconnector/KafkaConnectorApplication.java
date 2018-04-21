package com.wolfsoft.kafkaconnector;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class KafkaConnectorApplication {
    @Value("${kafka.brokers.url}")
    private String bootstrapServers;
    
	public static void main(String[] args) {
		SpringApplication.run(KafkaConnectorApplication.class, args);
	}

	@Bean
	public KafkaAdmin admin() {
	    Map<String, Object> configs = new HashMap<>();
	    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
	    return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic topic1() {
	    return new NewTopic("my-topic1", 10, (short) 2);
	}

	@Bean
	public NewTopic topic2() {
	    return new NewTopic("my-topic1", 10, (short) 2);
	}

}
