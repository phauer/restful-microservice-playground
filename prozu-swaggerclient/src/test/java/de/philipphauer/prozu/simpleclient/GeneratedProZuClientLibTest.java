package de.philipphauer.prozu.simpleclient;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.philipphauer.prozu.service.client.ApiClient;
import de.philipphauer.prozu.service.client.ApiException;
import de.philipphauer.prozu.service.client.api.EmployeesApi;
import de.philipphauer.prozu.service.client.model.EmployeeData;
import de.philipphauer.prozu.service.client.model.EmployeesResponse;

/**
 * testing the swagger-generated client library.
 */
public class GeneratedProZuClientLibTest {

	private EmployeesApi employeesApi;

	/*
	 * it would be nice to move this test code to the service project (automatically start server, use configured port
	 * etc), but the generated client library uses an different jersey and jackson version than dropwizard. I guess, I
	 * should start the dropwizard by running the executable jar in order to avoid version conflicts.
	 */

	@BeforeClass
	public static void startServer() throws Exception {
		// new ProZuApplication().run("server", "src/test/resources/test-config.yml");
		// TODO doesn't work. jersey NoSuchMethodeError... conflicting jersey and jackson versions...
		// https://github.com/swagger-api/swagger-codegen/issues/949 <- see my comments.
		// https://github.com/swagger-api/swagger-codegen/pull/950
	}

	@Before
	public void init() {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath("http://localhost:8080");
		employeesApi = new EmployeesApi(apiClient);
	}

	@Test
	public void employeeCount() throws ApiException {
		EmployeesResponse employees = employeesApi.getAllEmployees(20, 0, "peter");
		Assert.assertEquals((Long) 38l, employees.getTotalCount());
	}

	@Test
	public void createEmployeeAndGet() throws ApiException {
		EmployeesResponse employeesBefore = employeesApi.getAllEmployees(9999, 0, "");
		Long employeeCountBefore = employeesBefore.getTotalCount();

		EmployeeData employeeData = new EmployeeData();
		employeeData.setName("Bruce Wayne");
		employeesApi.createEmployee(employeeData);
		// TODO how do get the generated id in the location http header? maybe i have to return the id in the http
		// response body...

		EmployeesResponse employeesAfter = employeesApi.getAllEmployees(9999, 0, "");
		Long employeeCountAfter = employeesAfter.getTotalCount();

		Assert.assertEquals((long) employeeCountBefore + 1L, (long) employeeCountAfter);
	}
}
