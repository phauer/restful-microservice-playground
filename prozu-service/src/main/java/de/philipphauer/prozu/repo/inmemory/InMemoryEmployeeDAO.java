package de.philipphauer.prozu.repo.inmemory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.model.ProjectDays;
import de.philipphauer.prozu.repo.EmployeeDAO;
import de.philipphauer.prozu.repo.exception.RepositoryException;

@Singleton
public class InMemoryEmployeeDAO implements EmployeeDAO {

	private Map<String, Employee> employees;
	@Inject
	private AtomicIDGenerator idGenerator;

	@Override
	public List<Employee> getAllEmployees() {
		return (List<Employee>) employees.values();
	}

	@Override
	public List<Employee> getEmployees(int limit, int offset, Optional<String> search) {
		Collection<Employee> values = employees.values();
		Stream<Employee> employeeStream = values.stream();
		if (search.isPresent()) {
			employeeStream = employeeStream.filter(CommonPredicates.searchName(search.get()));
		}
		employeeStream = employeeStream
				.skip(offset)
				.limit(limit);
		return employeeStream.collect(Collectors.toList());
	}

	@Override
	public long getEmployeeCount(Optional<String> usedSearch) {
		if (usedSearch.isPresent()) {
			return employees.values().stream()
					.filter(CommonPredicates.searchName(usedSearch.get())).count();
		} else {
			return employees.size();
		}
	}

	@Override
	public Optional<Employee> getEmployee(String employeeId) {
		Employee employee = employees.get(employeeId);
		return Optional.ofNullable(employee);
	}

	@Override
	public List<ProjectDays> getAllProjectDays(String employeeId) {
		Optional<Employee> employee = getEmployee(employeeId);
		if (employee.isPresent()) {
			return employee.get().getProjectDays();
		}
		throw new RepositoryException("Invalid employee id " + employeeId);
	}

	@Override
	public Employee createEmployee(String name) {
		String id = idGenerator.generateID();
		Employee employee = new Employee(name);
		employees.put(id, employee);
		return employee;
	}

	@Override
	public void updateEmployee(String id, String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void save(Employee employee) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void saveAll(List<Employee> employeeList) {
		employees = employeeList.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
	}

	@Override
	public void deleteEmployee(String employeeId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAllEmployees() {
		employees.clear();
	}

}
