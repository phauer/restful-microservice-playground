package de.philipphauer.prozu.di.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;

import de.philipphauer.prozu.ProZuModule;
import de.philipphauer.prozu.configuration.ProZuConfiguration;

public class ServiceTestModule extends AbstractModule {

	private ProZuConfiguration config;

	public ServiceTestModule(ProZuConfiguration config) {
		this.config = config;
	}

	@Override
	protected void configure() {
		ObjectMapper objectMapper = new ObjectMapper();
		install(new ProZuModule(objectMapper));
		bind(ProZuConfiguration.class).toInstance(config);
	}

}