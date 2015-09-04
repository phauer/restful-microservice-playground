package de.philipphauer.mongodbtest.mappercompare;

import java.net.UnknownHostException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

@Ignore("mongodb has to run.")
public class MorphiaTest {

	// https://github.com/mongodb/morphia/wiki/QuickStart
	// https://github.com/mongodb/morphia/wiki

	private MongoClient mongoClient;
	private Datastore ds;

	@Before
	public void init() throws UnknownHostException {
		mongoClient = new MongoClient();
		Morphia morphia = new Morphia();
		morphia.map(Animal.class);// .map(AnotherEmbeddedClass.class)
		ds = morphia.createDatastore(mongoClient, "test");
	}

	@After
	public void cleanup() {
		mongoClient.close();
	}

	@Test
	public void insert() {
		Animal animal = new Animal("Lucky", 11);
		ds.save(animal);
	}

	@Test
	public void insertBatch() {
		ds.save(new Animal("Lucky", 11),
				new Animal("Dreibein", 4),
				new Animal("Schnuff", 1),
				new Animal("Will", 8));
	}

	@Test
	public void find() {
		// List<Animal> list = ds.find(Animal.class, "age >=", 4).asList();
		List<Animal> list = ds.find(Animal.class).field("age").greaterThanOrEq(4).asList();
		list.forEach(System.out::println);
	}
}
