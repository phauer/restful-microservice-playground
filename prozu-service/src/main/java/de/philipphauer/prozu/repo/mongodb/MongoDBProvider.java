package de.philipphauer.prozu.repo.mongodb;

import java.net.UnknownHostException;

import javax.inject.Inject;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import de.philipphauer.prozu.repo.exception.RepositoryException;

public class MongoDBProvider implements Provider<DBCollection> {

	private DBCollection employeesCollection;
	private MongoClient mongoClient;
	@Inject
	private MongoDBConfig config;

	//TODO close MongoClient!

	@Override
	@Singleton
	public DBCollection get() {
		try {
			mongoClient = new MongoClient();
			DB personDb = mongoClient.getDB(config.getDatabaseName());
			employeesCollection = personDb.getCollection(config.getEmployeeCollectionName());
			return employeesCollection;
		} catch (UnknownHostException e) {
			throw new RepositoryException("Couldn't create MongoDB Client.", e);
		}
	}

}
