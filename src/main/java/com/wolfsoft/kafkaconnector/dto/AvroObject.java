package com.wolfsoft.kafkaconnector.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import myasesor.server.fe.model.AvroHeader;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class AvroObject {
	private AvroHeader header;
	private String payload;
}
