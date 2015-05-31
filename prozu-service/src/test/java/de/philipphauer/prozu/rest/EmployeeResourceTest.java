package de.philipphauer.prozu.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.nio.file.Paths;

import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import de.philipphauer.prozu.MongoDbTestUtil;
import de.philipphauer.prozu.ProZuApplication;
import de.philipphauer.prozu.configuration.ProZuConfiguration;
import de.philipphauer.prozu.di.service.ServiceTestRunner;

@RunWith(ServiceTestRunner.class)
public class EmployeeResourceTest {

	@ClassRule
	public static final DropwizardAppRule<ProZuConfiguration> RULE =
			new DropwizardAppRule<ProZuConfiguration>(ProZuApplication.class,
					ResourceHelpers.resourceFilePath("test-config.yml"));

	private static WebTarget client;

	@Inject
	private MongoDbTestUtil testUtil;

	@BeforeClass
	public static void initClient() {
		String baseUrl = String.format("http://localhost:%d/v1/employees", RULE.getLocalPort());
		client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client").target(baseUrl);
	}

	@Before
	public void init() {
		testUtil.clearEmployees();
		testUtil.writeJsonFileToDb(Paths.get("src/test/resources/employees_10.json"));
	}

	@Test
	public void getAllEmployees() {
		Response response = client.request().get();
		JsonNode json = response.readEntity(JsonNode.class);

		assertThat(response.getStatus(), equalTo(200));
		assertThat(json.get("totalCount").asInt(), equalTo(10));
	}

	@Test
	public void getAllEmployeesSearch() {
		Response response = client.queryParam("search", "Paul").request().get();
		JsonNode json = response.readEntity(JsonNode.class);

		assertThat(response.getStatus(), equalTo(200));
		assertThat(json.get("totalCount").asInt(), equalTo(2));
	}

	@Test
	public void getAllEmployee() {
		Response response = client.path("0").request().get();
		JsonNode json = response.readEntity(JsonNode.class);

		assertThat(response.getStatus(), equalTo(200));
		assertThat(json.get("name").asText(), equalTo("Paul Persch"));
	}

	@Test
	public void getAllProjectDays() {
		Response response = client.path("0/projectdays").request().get();
		ArrayNode json = response.readEntity(ArrayNode.class);

		assertThat(response.getStatus(), equalTo(200));
		assertThat(json.size(), is(5));
		for (JsonNode projectDay : json) {
			assertThat(projectDay.hasNonNull("month"), is(true));
			assertThat(projectDay.hasNonNull("daysCount"), is(true));
		}
	}

}
