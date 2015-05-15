package de.philipphauer.prozu;

import org.mongojack.internal.MongoJackModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.DBCollection;

import de.philipphauer.prozu.repo.EmployeeDAO;
import de.philipphauer.prozu.repo.mongodb.MongoDBConfig;
import de.philipphauer.prozu.repo.mongodb.MongoDBEmployeeDAO;
import de.philipphauer.prozu.repo.mongodb.MongoDBProvider;
import de.philipphauer.prozu.util.ser.Java8TimeModule;

public class ProZuModule extends AbstractModule {

	private ObjectMapper objectMapper;
	private MongoDBConfig config;//TODO use ProZuConfiguration instead

	public ProZuModule(ObjectMapper objectMapper, MongoDBConfig config) {
		this.objectMapper = objectMapper;
		this.config = config;
		objectMapper.registerModule(new Java8TimeModule());
		MongoJackModule.configure(objectMapper);
	}

	@Override
	protected void configure() {
		//mongodb
		bind(EmployeeDAO.class).to(MongoDBEmployeeDAO.class);
		bind(DBCollection.class).toProvider(MongoDBProvider.class);
		bind(MongoDBConfig.class).toInstance(config);

		// bind(EmployeeDAO.class).to(InMemoryEmployeeDAO.class);
	}

	@Provides
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

}
