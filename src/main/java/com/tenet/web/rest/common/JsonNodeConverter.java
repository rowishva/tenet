package com.tenet.web.rest.common;

import javax.persistence.AttributeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Override
	public String convertToDatabaseColumn(JsonNode jsonNode) {
		if (jsonNode == null) {
			LOGGER.warn("jsonNode is null, returning null");
			return null;
		}

		String jsonNodeString = jsonNode.toPrettyString();
		return jsonNodeString;
	}

	@Override
	public JsonNode convertToEntityAttribute(String jsonNodeString) {

		if (StringUtils.isEmpty(jsonNodeString)) {
			LOGGER.warn("jsonNodeString input is empty, returning null");
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readTree(jsonNodeString);
		} catch (JsonProcessingException e) {
			LOGGER.error("Error parsing jsonNodeString", e);
		}
		return null;
	}

}