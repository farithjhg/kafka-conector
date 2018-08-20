package com.wolfsoft.kafkaconnector.producer;

import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;

public class AvroSerializer<T extends SpecificRecordBase> implements Serializer<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AvroSerializer.class);

	@Override
	public void close() {
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
	}

	@Override
	public byte[] serialize(String topic, T data) {
		LOGGER.debug("data to serialize='{}'", data);

		Injection<GenericRecord, byte[]> genericRecordInjection = GenericAvroCodecs.toBinary(data.getSchema());
		byte[] result = genericRecordInjection.apply(data);

		LOGGER.debug("serialized data='{}'", DatatypeConverter.printHexBinary(result));
		return result;
	}
}