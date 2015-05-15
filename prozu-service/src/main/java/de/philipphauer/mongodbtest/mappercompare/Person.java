package de.philipphauer.mongodbtest.mappercompare;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * for testing mongojack
 */
public class Person {

	private String id;
	private String name;
	private int age;

	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@ObjectId
	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
