package com.springboot.jakson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperDemo {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		Student student = new Student();
		student.setAge(10);
		student.setName("Mahesh");

		ObjectMapper mapper = new ObjectMapper();
		File file = new File("E:\\Java\\Workspaces\\Spring\\spring-boot-test\\src\\test\\resources\\student1.txt");

		mapper.writeValue(file, student);
		String json = mapper.writeValueAsString(student);
		System.out.println("Object mapped!");

		Student s2 = mapper.readValue(file, Student.class);
		System.out.println("S2: " + s2.toString());
	}
}

