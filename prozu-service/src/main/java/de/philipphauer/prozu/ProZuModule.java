package de.philipphauer.prozu;

import com.google.inject.AbstractModule;

import de.philipphauer.prozu.repo.EmployeeDAO;
import de.philipphauer.prozu.repo.inmemory.InMemoryEmployeeDAO;

public class ProZuModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EmployeeDAO.class).to(InMemoryEmployeeDAO.class);
		// bind(DummyDataGenerator.class);
	}

}
