package de.philipphauer.prozu.repo.mongodb;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import jersey.repackaged.com.google.common.collect.Lists;

import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.DBCollection;

import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.model.ProjectDays;
import de.philipphauer.prozu.repo.EmployeeDAO;

@Singleton
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
	public Optional<Employee> getEmployee(long employeeId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Employee> getEmployees(int limit, int offset, Optional<String> search) {
		DBCursor<Employee> cursor = getSearchCursor(search).skip(offset).limit(limit);
		List<Employee> list = Lists.newArrayList((Iterator<Employee>) cursor);
		return list;
	}

	private DBCursor<Employee> getSearchCursor(Optional<String> search) {
		if (search.isPresent()) {
			Pattern pattern = Pattern.compile(".*" + search.get().toLowerCase() + ".*");
			return col.find(DBQuery.regex(Employee.NAME, pattern));
		} else {
			return col.find();
		}
	}

	@Override
	public List<ProjectDays> getAllProjectDays(long employeeId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Employee createEmployee(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateEmployee(long id, String name) {
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
	public void deleteEmployee(long employeeId) {
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
