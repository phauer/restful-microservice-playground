package de.philipphauer.mongodbtest.mappercompare;

import java.net.UnknownHostException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mysema.query.mongodb.morphia.MorphiaQuery;

public class QueryDslMongoDbTest {

	// https://github.com/querydsl/querydsl/tree/master/querydsl-mongodb

	private static final QAnimal a = new QAnimal("animals");
	private MongoClient mongoClient;
	private MorphiaQuery<Animal> query;

	@Before
	public void init() throws UnknownHostException {
		mongoClient = new MongoClient();
		Morphia morphia = new Morphia();
		morphia.map(Animal.class);// .map(AnotherEmbeddedClass.class)
		Datastore ds = morphia.createDatastore(mongoClient, "test");

		query = new MorphiaQuery<Animal>(morphia, ds, a);
	}

	@After
	public void cleanup() {
		mongoClient.close();
	}

	@Test
	public void find() {
		List<Animal> list = query
				.where(a.age.goe(4))
				.list();
		list.forEach(System.out::println);
	}

}
