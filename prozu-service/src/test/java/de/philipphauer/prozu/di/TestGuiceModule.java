package de.philipphauer.prozu.di;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;

import de.philipphauer.prozu.ProZuModule;
import de.philipphauer.prozu.configuration.ProZuConfiguration;

public class TestGuiceModule extends AbstractModule {

	private ProZuConfiguration config;

	public TestGuiceModule(ProZuConfiguration config) {
		this.config = config;
	}

	@Override
	protected void configure() {
		ObjectMapper objectMapper = new ObjectMapper();
		install(new ProZuModule(objectMapper));
		bind(ProZuConfiguration.class).toInstance(config);
	}

}
