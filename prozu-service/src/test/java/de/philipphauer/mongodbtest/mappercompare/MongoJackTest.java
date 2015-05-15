package de.philipphauer.mongodbtest.mappercompare;

import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoJackTest {

	/*
	 * ODM-Wahl: MongoJack vs Morphia vs QueryDsl-MongoDB
	 * - MongoDB eigener Driver zu verbose, 3.x version macht dort vieles besser, aber ODMs unterstützen diese Version noch nicht. 
	 * - MongoJack macht besten ersten subjektiven Eindruck. schön leichtgewichtig (kaum Annotationen nötig); soll extrem schnell sein. aber kein check f typos (properties als strings)
	 * - Morphia: mehr annotationen; zusätzliches "className" property mit persistiert (doof: package verschieben); validierung nett (keine typos mögl)
	 * - MongoJack und Morphia werden ungefähr gleich aktiv auf github entwickelt. Morphia mehr commits
	 * - QueryDSl-Mongo-DB nutzt Morphia; typsichere querys möglich (aber zusätzlicher generierungsschritt)
	 * - Jongo: query als json strings übergeben -> doof. 
	 */

	// http://mongojack.org/tutorial.html

	private JacksonDBCollection<Person, String> coll;
	private MongoClient mongoClient;

	@Before
	public void init() throws UnknownHostException {
		mongoClient = new MongoClient();
		DB db = mongoClient.getDB("test");
		DBCollection col = db.getCollection("persons");
		coll = JacksonDBCollection.wrap(col, Person.class, String.class);
	}

	@After
	public void cleanup() {
		mongoClient.close();
	}

	@Test
	public void insert() {
		coll.insert(new Person("Paul", 15),
				new Person("Mary", 30),
				new Person("Ron", 45),
				new Person("Till", 7));
	}

	@Test
	public void find() {
		Person person = coll.findOne(DBQuery.is("name", "Paul"));
		System.out.println(person);
	}

	@Test
	public void findMany() {
		DBCursor<Person> cursor = coll.find(DBQuery.greaterThan("age", 10));
		cursor.forEach(System.out::println);
	}
}
