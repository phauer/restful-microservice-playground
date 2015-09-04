package de.philipphauer.prozu;

import java.net.UnknownHostException;

import org.mongojack.internal.MongoJackModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import de.philipphauer.prozu.configuration.MongoDBConfig;
import de.philipphauer.prozu.configuration.ProZuConfiguration;
import de.philipphauer.prozu.repo.EmployeeDAO;
import de.philipphauer.prozu.repo.exception.RepositoryException;
import de.philipphauer.prozu.repo.mongodb.MongoDBEmployeeDAO;
import de.philipphauer.prozu.util.ser.Java8TimeModule;

public class ProZuModule extends AbstractModule {

	private ObjectMapper objectMapper;

	private static Logger logger = LoggerFactory.getLogger(ProZuModule.class);

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
			MongoClientOptions options = new MongoClientOptions.Builder().connectTimeout(1000 * 1).build();
			MongoDBConfig mongoDBConfig = config.getMongoDB();
			logger.info("Using mongodb configuration: {}", mongoDBConfig.toString());
			ServerAddress serverAddress = new ServerAddress(mongoDBConfig.getHost(), mongoDBConfig.getPort());
			MongoClient mongoClient = new MongoClient(serverAddress, options);
			DB personDb = mongoClient.getDB(mongoDBConfig.getDatabaseName());
			DBCollection employeesCollection = personDb.getCollection(mongoDBConfig.getEmployeeCollectionName());
			return employeesCollection;
		} catch (UnknownHostException e) {
			throw new RepositoryException("Couldn't create MongoDB Client.", e);
		}
	}

}
