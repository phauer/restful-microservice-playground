package de.philipphauer.prozu.rest;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.nio.file.Paths;

import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;

import de.philipphauer.prozu.MongoDbTestUtil;
import de.philipphauer.prozu.ProZuApplication;
import de.philipphauer.prozu.configuration.ProZuConfiguration;

public class AbstractEmployeeResourceTest {

	@ClassRule
	public static final DropwizardAppRule<ProZuConfiguration> RULE =
			new DropwizardAppRule<ProZuConfiguration>(ProZuApplication.class,
					ResourceHelpers.resourceFilePath("test-config.yml"));

	protected static WebTarget client;

	@Inject
	private MongoDbTestUtil testUtil;

	@BeforeClass
	public static void initClient() {
		String baseUrl = String.format("http://localhost:%d/employees", RULE.getLocalPort());
		client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client").target(baseUrl);
	}

	@Before
	public void init() {
		testUtil.clearEmployees();
		testUtil.writeJsonFileToDb(Paths.get("src/test/resources/employees_10.json"));
	}

}
