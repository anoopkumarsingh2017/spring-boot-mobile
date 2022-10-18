package com.springboot.jakson;

import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TreeModel {
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";

		// create tree from JSON
		JsonNode node = mapper.readTree(jsonString);
		System.out.println("node.name:" + node.path("name"));
		System.out.println("node.verified:" + node.path("verified"));

		JsonNode marksNode = node.path("marks");
		Iterator<JsonNode> itr = marksNode.elements();
		while (itr.hasNext()) {
			JsonNode node1 = itr.next();
			System.out.println("mark:" + node1.intValue());
		}

	}
}
