package de.philipphauer.prozu.di;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;

import de.philipphauer.prozu.ProZuModule;
import de.philipphauer.prozu.repo.mongodb.MongoDBConfig;

public class TestGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		ObjectMapper objectMapper = new ObjectMapper();
		MongoDBConfig config = new MongoDBConfig("test", "employeesTest");
		install(new ProZuModule(objectMapper, config));
	}

}
