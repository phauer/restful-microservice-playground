package de.philipphauer.prozu.repo.mongodb;

import java.net.UnknownHostException;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import de.philipphauer.prozu.repo.exception.RepositoryException;

public class MongoDBProvider implements Provider<DBCollection> {

	private DBCollection employeesCollection;
	private MongoClient mongoClient;

	//TODO close MongoClient!

	@Override
	@Singleton
	public DBCollection get() {
		try {
			mongoClient = new MongoClient();
			DB personDb = mongoClient.getDB("test");
			employeesCollection = personDb.getCollection("employees");
			return employeesCollection;
		} catch (UnknownHostException e) {
			throw new RepositoryException("Couldn't create MongoDB Client.", e);
		}
	}

}
