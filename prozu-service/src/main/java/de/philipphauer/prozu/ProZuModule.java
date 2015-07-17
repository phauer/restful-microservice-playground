package de.philipphauer.prozu;

import java.net.UnknownHostException;

import org.mongojack.internal.MongoJackModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import de.philipphauer.prozu.configuration.ProZuConfiguration;
import de.philipphauer.prozu.repo.EmployeeDAO;
import de.philipphauer.prozu.repo.exception.RepositoryException;
import de.philipphauer.prozu.repo.mongodb.MongoDBEmployeeDAO;
import de.philipphauer.prozu.util.ser.Java8TimeModule;

public class ProZuModule extends AbstractModule {

	private ObjectMapper objectMapper;

	public ProZuModule(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		objectMapper.registerModule(new Java8TimeModule());
		MongoJackModule.configure(objectMapper);
	}

	@Override
	protected void configure() {
		//mongodb
		bind(EmployeeDAO.class).to(MongoDBEmployeeDAO.class);
		// bind(EmployeeDAO.class).to(InMemoryEmployeeDAO.class);
	}

	@Provides
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	//@Singleton//TODO doesn't work with guice-bundle. see https://github.com/HubSpot/dropwizard-guice/issues/40
	//TODO use connection pooling (jetty datasource)
	@Provides
	public DBCollection get(ProZuConfiguration config) {
		try {
			MongoClient mongoClient = new MongoClient();
			DB personDb = mongoClient.getDB(config.getMongoDB().getDatabaseName());
			DBCollection employeesCollection = personDb.getCollection(config.getMongoDB()
					.getEmployeeCollectionName());
			return employeesCollection;
		} catch (UnknownHostException e) {
			throw new RepositoryException("Couldn't create MongoDB Client.", e);
		}
	}

}
