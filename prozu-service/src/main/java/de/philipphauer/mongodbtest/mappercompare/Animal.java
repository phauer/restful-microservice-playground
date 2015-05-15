package de.philipphauer.mongodbtest.mappercompare;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/*
 * for testing morphia & querydsl-mongodb
 */
@Entity("animals")
public class Animal {

	@Id
	private ObjectId id;
	private String name;
	private int age;

	public Animal() {
	}

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
