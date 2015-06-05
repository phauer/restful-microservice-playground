package de.philipphauer.prozu.repo.mongodb;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import jersey.repackaged.com.google.common.collect.Lists;

import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.mongodb.DBCollection;

import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.model.ProjectDays;
import de.philipphauer.prozu.repo.EmployeeDAO;
import de.philipphauer.prozu.repo.exception.RepositoryException;

//@Singleton//TODO doesn't work with guice-bundle. see https://github.com/HubSpot/dropwizard-guice/issues/40
public class MongoDBEmployeeDAO implements EmployeeDAO {

	private JacksonDBCollection<Employee, String> col;

	@Inject
	public MongoDBEmployeeDAO(DBCollection employeesCollection, ObjectMapper objectMapper) {
		col = JacksonDBCollection.wrap(employeesCollection, Employee.class, String.class, objectMapper);
	}

	@Override
	public List<Employee> getAllEmployees() {
		DBCursor<Employee> cursor = col.find();
		List<Employee> list = Lists.newArrayList((Iterator<Employee>) cursor);
		return list;
	}

	@Override
	public Optional<Employee> getEmployee(String employeeId) {
		Employee employee = col.findOneById(employeeId);
		return Optional.ofNullable(employee);
	}

	@Override
	public List<Employee> getEmployees(int limit, int offset, Optional<String> search) {
		DBCursor<Employee> cursor = getSearchCursor(search).skip(offset).limit(limit);
		List<Employee> list = Lists.newArrayList((Iterator<Employee>) cursor);
		return list;
	}

	private DBCursor<Employee> getSearchCursor(Optional<String> search) {
		if (search.isPresent()) {
			Pattern pattern = Pattern.compile(".*" + search.get().toLowerCase() + ".*", Pattern.CASE_INSENSITIVE);
			return col.find(DBQuery.regex(Employee.NAME, pattern));
		} else {
			return col.find();
		}
	}

	@Override
	public List<ProjectDays> getAllProjectDays(String employeeId) {
		Optional<Employee> employee = getEmployee(employeeId);
		if (employee.isPresent()) {
			return employee.get().getProjectDays();
		} else {
			throw new RepositoryException("Employee " + employeeId + " doesn't exist.");
		}
	}

	@Override
	public Employee createEmployee(String name) {
		Employee employee = new Employee(name);
		WriteResult<Employee, String> result = col.insert(employee);
		Employee employeeWithId = col.findOneById(result.getSavedId());
		return employeeWithId;
	}

	@Override
	public void updateEmployee(String id, String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void save(Employee employee) {
		col.insert(employee);
	}

	@Override
	public void saveAll(List<Employee> employees) {
		col.insert(employees);
	}

	@Override
	public void deleteEmployee(String employeeId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getEmployeeCount(Optional<String> usedSearch) {
		return getSearchCursor(usedSearch).count();
	}

	@Override
	public void deleteAllEmployees() {
		col.drop();
	}

}
