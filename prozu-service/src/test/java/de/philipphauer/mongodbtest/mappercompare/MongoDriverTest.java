package de.philipphauer.mongodbtest.mappercompare;

import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Ignore("mongodb has to run.")
public class MongoDriverTest {

	// http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/

	private DBCollection col;
	private MongoClient mongoClient;

	@Before
	public void init() throws UnknownHostException {
		mongoClient = new MongoClient();
		DB db = mongoClient.getDB("test");
		col = db.getCollection("mycol");
	}

	@After
	public void cleanup() {
		mongoClient.close();
	}

	@Test
	public void insert() {
		BasicDBObject doc = new BasicDBObject("name", "MongoDB")
				.append("type", "database")
				.append("count", 1)
				.append("info", new BasicDBObject("x", 203).append("y", 102));
		col.insert(doc);
	}

	@Test
	public void count() {
		System.out.println(col.count());
	}

	// the API has been improved in Driver Version 3.x (Filters class)
	@Test
	public void find() {
		BasicDBObject query = new BasicDBObject("name", new BasicDBObject("$eq", "MongoDB"));
		DBObject result = col.findOne(query);
		System.out.println(result);

	}
}
