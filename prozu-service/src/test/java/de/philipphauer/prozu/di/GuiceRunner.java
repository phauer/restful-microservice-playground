package de.philipphauer.prozu.di;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceRunner extends BlockJUnit4ClassRunner {

	private Injector testInjector;

	public GuiceRunner(final Class<?> classToRun) throws InitializationError {
		super(classToRun);
		testInjector = Guice.createInjector(new TestGuiceModule());
	}

	@Override
	protected Object createTest() throws Exception {
		Object test = super.createTest();
		testInjector.injectMembers(test);
		return test;
	}

}