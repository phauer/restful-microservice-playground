package de.philipphauer.prozu;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.hubspot.dropwizard.guice.GuiceBundle;

import de.philipphauer.prozu.healthchecks.TemplateHealthCheck;
import de.philipphauer.prozu.rest.EmployeeResource;
import de.philipphauer.prozu.util.ser.Java8TimeModule;

public class ProZuApplication extends Application<ProZuConfiguration> {

	private GuiceBundle<ProZuConfiguration> guiceBundle;

	public static void main(String[] args) throws Exception {
		new ProZuApplication().run(args);
	}

	@Override
	public String getName() {
		return "prozu";
	}

	@Override
	public void initialize(Bootstrap<ProZuConfiguration> bootstrap) {

		guiceBundle = GuiceBundle.<ProZuConfiguration> newBuilder()
				.addModule(new ProZuModule())
				.setConfigClass(ProZuConfiguration.class)
				// .enableAutoConfig("de.itemis.prozu.repo.inmemory")
				.enableAutoConfig(getClass().getPackage().getName())
				.build();
		bootstrap.addBundle(guiceBundle);

	}

	@Override
	public void run(ProZuConfiguration configuration, Environment environment) {
		TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);

		// EmployeeResource resource = new
		// EmployeeResource(configuration.getTemplate(),
		// configuration.getDefaultName());
		environment.jersey().register(EmployeeResource.class);

		environment.getObjectMapper().registerModule(new Java8TimeModule());
	}
}
