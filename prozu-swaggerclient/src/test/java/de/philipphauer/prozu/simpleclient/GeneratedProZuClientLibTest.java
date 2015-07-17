package de.philipphauer.prozu.simpleclient;

import org.junit.Assert;
import org.junit.Test;

import de.philipphauer.prozu.service.client.ApiClient;
import de.philipphauer.prozu.service.client.ApiException;
import de.philipphauer.prozu.service.client.api.EmployeesApi;
import de.philipphauer.prozu.service.client.model.EmployeesResponse;

/**
 * testing the swagger-generated client library.
 */
public class GeneratedProZuClientLibTest {

	/*
	 * it would be nice to move this test code to the service project (automatically start server, use configured port etc),
	 * but the generated client library uses an outdated jersey version.
	 * This leads to an conflict with the jersey version of the service project.
	 */

	@Test
	public void test() throws ApiException {

		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath("http://localhost:8080");

		EmployeesApi employeesApi = new EmployeesApi(apiClient);

		EmployeesResponse employees = employeesApi.getAllEmployees(20, 0, "peter");
		Assert.assertEquals((Long) 38l, employees.getTotalCount());

	}
}
