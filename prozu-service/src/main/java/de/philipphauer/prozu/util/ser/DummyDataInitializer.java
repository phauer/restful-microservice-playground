package de.philipphauer.prozu.util.ser;

import java.util.List;

import javax.inject.Inject;

import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.repo.EmployeeDAO;
import de.philipphauer.prozu.repo.shared.DummyDataGenerator;

public class DummyDataInitializer {

	@Inject
	private DummyDataGenerator generator;
	@Inject
	private EmployeeDAO dao;

	public void initDummyData() {
		dao.deleteAllEmployees();
		List<Employee> employees = generator.createEmployees(500);
		dao.saveAll(employees);
	}
}
