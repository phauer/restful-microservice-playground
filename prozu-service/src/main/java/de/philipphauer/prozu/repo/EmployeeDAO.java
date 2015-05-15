package de.philipphauer.prozu.repo;

import java.util.List;
import java.util.Optional;

import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.model.ProjectDays;

public interface EmployeeDAO {

	List<Employee> getAllEmployees();

	Optional<Employee> getEmployee(long employeeId);

	List<Employee> getEmployees(int limit, int offset, Optional<String> search);

	List<ProjectDays> getAllProjectDays(long employeeId);

	Employee createEmployee(String name);

	void updateEmployee(long id, String name);

	void save(Employee employee);

	void saveAll(List<Employee> employees);

	void deleteEmployee(long employeeId);

	long getEmployeeCount(Optional<String> usedSearch);

	void deleteAllEmployees();

}
