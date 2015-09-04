package de.philipphauer.prozu.integration;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.philipphauer.prozu.di.IntegrationTest;

@Category(IntegrationTest.class)
public class EmployeeResourceTestViaDocker {

	@Test
	public void testConnection() throws IOException {
		String urlString = System.getProperty("service.url");
		System.out.println("testing url:" + urlString);

		URL serviceUrl = new URL(urlString + "employees");
		HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();
		int responseCode = connection.getResponseCode();
		assertEquals(200, responseCode);
	}
}
