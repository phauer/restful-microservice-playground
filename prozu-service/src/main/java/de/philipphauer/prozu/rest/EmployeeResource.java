package de.philipphauer.prozu.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.inject.Inject;

import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.model.ProjectDays;
import de.philipphauer.prozu.repo.EmployeeDAO;
import de.philipphauer.prozu.rest.exception.ProZuClientErrorException;
import de.philipphauer.prozu.rest.responses.EmployeeResponse;
import de.philipphauer.prozu.rest.responses.EmployeesResponse;
import de.philipphauer.prozu.rest.responses.ProjectDaysResponse;
import de.philipphauer.prozu.rest.util.EntityMapper;
import de.philipphauer.prozu.rest.util.MediaTypeWithCharset;

@Path(URLConstants.EMPLOYEES)
public class EmployeeResource {

	@Context
	private UriInfo context;
	@Context
	private HttpHeaders headers;
	@Inject
	private EmployeeDAO dao;
	@Inject
	private EntityMapper mapper;

	// TODO better http status code on failures, illegal inputs, exceptions

	/*
	 * GET, Read
	 */

	@GET
	@Path("/")
	@Produces(MediaTypeWithCharset.APPLICATION_JSON_UTF8)
	public EmployeesResponse getAllEmployees(@QueryParam("limit") Integer limit,
			@QueryParam("offset") Integer offset, @QueryParam("search") String search) {
		int usedLimit = limit == null ? 10 : limit;
		int usedOffset = offset == null ? 0 : offset;
		Optional<String> usedSearch = Optional.ofNullable(search);
		//		List<Employee> employees = dao.getEmployees(usedLimit, usedOffset, usedSearch);
		List<Employee> employees = dao.getAllEmployees();
		long totalCount = dao.getEmployeeCount(usedSearch);
		EmployeesResponse response = mapper.mapToEmployeesResponse(employees, usedLimit, usedOffset, totalCount,
				usedSearch);
		return response;
	}

	@GET
	@Path("/{employeeId}")
	@Produces(MediaTypeWithCharset.APPLICATION_JSON_UTF8)
	public EmployeeResponse getEmployee(@PathParam("employeeId") int employeeId) {
		Optional<Employee> employee = dao.getEmployee(employeeId);
		if (employee.isPresent()) {
			EmployeeResponse rEmployee = mapper.mapToREmployee(employee.get());
			return rEmployee;
		}
		throw new ProZuClientErrorException("No Employee with id " + employeeId + " found.", 9);
	}

	@GET
	@Path("/{employeeId}/projectdays/")
	@Produces(MediaTypeWithCharset.APPLICATION_JSON_UTF8)
	public List<ProjectDaysResponse> getAllProjectDays(
			@PathParam("employeeId") int employeeId) {
		List<ProjectDays> projectDays = dao.getAllProjectDays(employeeId);
		List<ProjectDaysResponse> rProjectDays = mapper.mapToRProjectDays(projectDays);
		return rProjectDays;
	}

	/*
	 * POST, Create
	 */

	@POST
	@Path("/")
	@Consumes(MediaTypeWithCharset.APPLICATION_JSON_UTF8)
	public Response createEmployee(EmployeeResponse newEmployeeData) throws URISyntaxException {
		Employee employee = dao.createEmployee(newEmployeeData.getName());

		URI uri = createNewLocationURI(employee.getId());
		Response response = Response.created(uri).build();

		return response;
	}

	private URI createNewLocationURI(long employeeId) throws URISyntaxException {
		String uriString = context.getAbsolutePath().toString();
		if (!uriString.endsWith("/")) {
			uriString += "/";
		}
		return new URI(uriString + employeeId);
	}

	/*
	 * PUT, Update
	 */

	@PUT
	@Path("/{employeeId}")
	@Consumes(MediaTypeWithCharset.APPLICATION_JSON_UTF8)
	public Response updateEmployee(@PathParam("employeeId") long employeeId,
			EmployeeResponse newEmployeeData) {
		String name = newEmployeeData.getName();
		dao.updateEmployee(employeeId, name);
		return Response.ok().build();
	}

	/*
	 * DELETE, delete
	 */

	@DELETE
	@Path("/{employeeId}")
	@Consumes(MediaTypeWithCharset.APPLICATION_JSON_UTF8)
	public Response deleteEmployee(@PathParam("employeeId") long employeeId) {
		dao.deleteEmployee(employeeId);
		return Response.ok().build();
	}

}
