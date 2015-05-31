package de.philipphauer.prozu.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.nio.file.Paths;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
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

	private static Client client;

	@Inject
	private MongoDbTestUtil testUtil;

	private String baseUrl;
	private String employeesBaseUrl;

	@BeforeClass
	public static void initClient() {
		client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");
	}

	@Before
	public void init() {
		testUtil.clearEmployees();
		baseUrl = String.format("http://localhost:%d/v1/", RULE.getLocalPort());
		employeesBaseUrl = baseUrl + "employees";
	}

	@Test
	public void getAllEmployees() {
		testUtil.writeJsonFileToDb(Paths.get("src/test/resources/employees_10.json"));

		Response response = client.target(employeesBaseUrl).request().get();
		JsonNode json = response.readEntity(JsonNode.class);

		assertThat(response.getStatus(), equalTo(200));
		assertThat(json.get("totalCount").asInt(), equalTo(10));
	}

	@Test
	public void getAllEmployeesSearch() {
		testUtil.writeJsonFileToDb(Paths.get("src/test/resources/employees_10.json"));

		Response response = client.target(employeesBaseUrl + "?search=Paul").request().get();
		JsonNode json = response.readEntity(JsonNode.class);

		assertThat(response.getStatus(), equalTo(200));
		assertThat(json.get("totalCount").asInt(), equalTo(2));
	}

	@Test
	public void getAllEmployee() {
		testUtil.writeJsonFileToDb(Paths.get("src/test/resources/employees_10.json"));

		Response response = client.target(employeesBaseUrl + "/0").request().get();
		JsonNode json = response.readEntity(JsonNode.class);

		assertThat(response.getStatus(), equalTo(200));
		assertThat(json.get("name").asText(), equalTo("Paul Persch"));
	}

	@Test
	public void getAllProjectDays() {
		testUtil.writeJsonFileToDb(Paths.get("src/test/resources/employees_10.json"));

		Response response = client.target(employeesBaseUrl + "/0/projectdays").request().get();
		ArrayNode json = response.readEntity(ArrayNode.class);

		assertThat(response.getStatus(), equalTo(200));
		assertThat(json.size(), is(5));
		for (JsonNode projectDay : json) {
			assertThat(projectDay.hasNonNull("month"), is(true));
			assertThat(projectDay.hasNonNull("daysCount"), is(true));
		}
	}
}
