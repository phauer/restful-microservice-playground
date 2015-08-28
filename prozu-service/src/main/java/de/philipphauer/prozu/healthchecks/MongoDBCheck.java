package de.philipphauer.prozu.healthchecks;

import javax.inject.Inject;

import com.hubspot.dropwizard.guice.InjectableHealthCheck;
import com.mongodb.DBCollection;

import de.philipphauer.prozu.configuration.ProZuConfiguration;

public class MongoDBCheck extends InjectableHealthCheck {

	@Inject
	private ProZuConfiguration config;
	@Inject
	private DBCollection collection;

	@Override
	protected Result check() throws Exception {
		try {
			collection.count();
			return Result.healthy();
		} catch (Exception e) {
			return Result.unhealthy(new RuntimeException("Couldn't connect to mongoDB. Config: " + config.getMongoDB(),
					e));
		}
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

}
