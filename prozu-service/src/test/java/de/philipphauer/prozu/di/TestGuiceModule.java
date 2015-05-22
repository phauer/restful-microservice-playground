package de.philipphauer.prozu.di;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;

import de.philipphauer.prozu.ProZuModule;

public class TestGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		ObjectMapper objectMapper = new ObjectMapper();
		install(new ProZuModule(objectMapper));
	}

}
