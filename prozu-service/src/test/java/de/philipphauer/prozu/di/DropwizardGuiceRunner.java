package de.philipphauer.prozu.di;

import io.dropwizard.testing.junit.DropwizardAppRule;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

import org.junit.ClassRule;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import com.google.common.base.Throwables;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.philipphauer.prozu.configuration.ProZuConfiguration;

public class DropwizardGuiceRunner extends BlockJUnit4ClassRunner {

	public DropwizardGuiceRunner(final Class<?> classToRun) throws InitializationError {
		super(classToRun);
	}

	@Override
	protected Object createTest() throws Exception {
		Object test = super.createTest();

		ProZuConfiguration config = getConfiguration(test);
		TestGuiceModule testGuiceModule = new TestGuiceModule(config);
		Injector testInjector = Guice.createInjector(testGuiceModule);
		testInjector.injectMembers(test);
		return test;
	}

	@SuppressWarnings("rawtypes")
	private ProZuConfiguration getConfiguration(Object test) {
		Class<?> classToRun = test.getClass();
		try {
			Field[] fields = classToRun.getFields();
			Optional<Field> ruleField = Arrays
					.stream(fields)
					.filter(field -> field.isAnnotationPresent(ClassRule.class)
							&& field.getType() == DropwizardAppRule.class)
					.findAny();
			if (ruleField.isPresent()) {
				DropwizardAppRule rule = (DropwizardAppRule) ruleField.get().get(test);
				return (ProZuConfiguration) rule.getConfiguration();
			}
			throw new RuntimeException(
					"Couldn't initialize test due to missing field of type DropwizardAppRule with Annotation @ClassRule in test");
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			Throwables.propagate(e);
		}
		return null;
	}
}