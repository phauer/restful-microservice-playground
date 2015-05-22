package de.philipphauer.prozu.rest;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.nio.file.Paths;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.JsonNode;

import de.philipphauer.prozu.MongoDbTestUtil;
import de.philipphauer.prozu.ProZuApplication;
import de.philipphauer.prozu.configuration.ProZuConfiguration;
import de.philipphauer.prozu.di.GuiceRunner;

@RunWith(GuiceRunner.class)
public class EmployeeResourceTest {

	@ClassRule
	public static final DropwizardAppRule<ProZuConfiguration> RULE =
			new DropwizardAppRule<ProZuConfiguration>(ProZuApplication.class,
					ResourceHelpers.resourceFilePath("test-config.yml"));
	//TODO hm test-config.yml is not known to the TestModule?! every field is null!
	// use	BootstrapUtils.reset()?  see https://github.com/HubSpot/dropwizard-guice

	@Inject
	private MongoDbTestUtil testUtil;

	@Before
	public void init() {
		testUtil.clearEmployees();
	}

	@Test
	public void getAllEmployees() {
		testUtil.writeJsonFileToDb(Paths.get("src/test/resources/employees_10.json"));

		Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");
		String url = String.format("http://localhost:%d/v1/employees", RULE.getLocalPort());
		Response response = client.target(url).request().get();
		JsonNode json = response.readEntity(JsonNode.class);

		assertThat(response.getStatus(), equalTo(200));
		assertThat(json.get("totalCount"), equalTo(10));
	}
}
