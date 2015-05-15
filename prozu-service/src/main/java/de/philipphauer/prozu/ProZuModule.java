package de.philipphauer.prozu;

import org.mongojack.internal.MongoJackModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mongodb.DBCollection;

import de.philipphauer.prozu.repo.EmployeeDAO;
import de.philipphauer.prozu.repo.mongodb.MongoDBEmployeeDAO;
import de.philipphauer.prozu.repo.mongodb.MongoDBProvider;
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
		bind(DBCollection.class).toProvider(MongoDBProvider.class);

		// bind(EmployeeDAO.class).to(InMemoryEmployeeDAO.class);
		// bind(DummyDataGenerator.class);
	}

	@Provides
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

}
