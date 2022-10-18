package com.springboot.jakson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaCoreDataTypeMapping {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		Map<String, Object> map = new HashMap<>();

		int[] marks = { 1, 2, 3 };

		Student student = new Student();
		student.setAge(10);
		student.setName("Mahesh");

		map.put("marks", marks);
		map.put("student", student);
		map.put("boolean", Boolean.TRUE);
		map.put("name", "Ramesh");

		System.out.println("map: " + map);

		ObjectMapper mapper = new ObjectMapper();
		File file = new File(
				"E:\\Java\\Workspaces\\Spring\\spring-boot-test\\src\\test\\resources\\JavaCoreDataTypeMapping.txt");
		mapper.writeValue(file, map);
		System.out.println("Written to file");

		map = mapper.readValue(file, Map.class);

		System.out.println("marks:" + map.get("marks"));
		//Student s2 = (Student) map.get("student");
		System.out.println("student:" + map.get("student"));
		System.out.println("boolean:" + map.get("boolean"));
		System.out.println("name:" + map.get("name"));

		System.out.println("End!!!!!!!!");

	}
}
