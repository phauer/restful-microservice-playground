package de.philipphauer.prozu.repo;

import java.util.List;
import java.util.Optional;

import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.model.ProjectDays;

public interface EmployeeDAO {

	List<Employee> getAllEmployees();

	Optional<Employee> getEmployee(long employeeId);

	List<ProjectDays> getAllProjectDays(long employeeId);

	Employee createEmployee(String name);

	void updateEmployee(long id, String name);

	void save(Object employee);

	void saveAll(List<? extends Object> employees);

	void deleteEmployee(long employeeId);

	List<Employee> getEmployees(int limit, int offset, Optional<String> search);

	long getEmployeeCount(Optional<String> usedSearch);

}
