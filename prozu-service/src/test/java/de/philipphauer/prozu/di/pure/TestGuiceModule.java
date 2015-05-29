package de.philipphauer.prozu.di.pure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;

import de.philipphauer.prozu.ProZuModule;
import de.philipphauer.prozu.configuration.MongoDBConfig;
import de.philipphauer.prozu.configuration.ProZuConfiguration;

public class TestGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		ObjectMapper objectMapper = new ObjectMapper();
		install(new ProZuModule(objectMapper));

		ProZuConfiguration config = createTestConfiguration();
		bind(ProZuConfiguration.class).toInstance(config);
	}

	private ProZuConfiguration createTestConfiguration() {
		MongoDBConfig mongoDBConfig = new MongoDBConfig();
		mongoDBConfig.setDatabaseName("test");
		mongoDBConfig.setEmployeeCollectionName("employeesTest");
		ProZuConfiguration config = new ProZuConfiguration();
		config.setMongoDB(mongoDBConfig);
		return config;
	}

}
