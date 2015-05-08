package de.philipphauer.prozu.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import org.junit.ClassRule;
import org.junit.Test;

import de.philipphauer.prozu.ProZuApplication;
import de.philipphauer.prozu.ProZuConfiguration;

public class HelloWorldResourceTest {

	@ClassRule
	public static final DropwizardAppRule<ProZuConfiguration> RULE =
			new DropwizardAppRule<ProZuConfiguration>(ProZuApplication.class,
					ResourceHelpers.resourceFilePath("test-config.yml"));

	@Test
	public void helloWorld() {
		Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");
		String url = String.format("http://localhost:%d/hello-world", RULE.getLocalPort());
		Response response = client.target(url).request().get();
		String json = response.readEntity(String.class);
		assertThat(json, equalTo("{\"id\":1,\"content\":\"Hello, Stranger!\"}"));
		assertThat(response.getStatus(), equalTo(200));
	}
}
