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

	private Map<Long, Employee> employees;
	@Inject
	private AtomicIDGenerator idGenerator;

	@Inject
	public InMemoryEmployeeDAO(DummyDataGenerator generator) {
		List<Employee> dummies = generator.createEmployees(500);
		employees = dummies.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
	}

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
	public Optional<Employee> getEmployee(long employeeId) {
		Employee employee = employees.get(employeeId);
		return Optional.ofNullable(employee);
	}

	@Override
	public List<ProjectDays> getAllProjectDays(long employeeId) {
		Optional<Employee> employee = getEmployee(employeeId);
		if (employee.isPresent()) {
			return employee.get().getProjectDays();
		}
		throw new RepositoryException("Invalid employee id " + employeeId);
	}

	@Override
	public Employee createEmployee(String name) {
		long id = idGenerator.generateID();
		Employee employee = new Employee(name, id);
		employees.put(id, employee);
		return employee;
	}

	@Override
	public void updateEmployee(long id, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Object employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAll(List<? extends Object> employees) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEmployee(long employeeId) {
		// TODO Auto-generated method stub

	}

}
