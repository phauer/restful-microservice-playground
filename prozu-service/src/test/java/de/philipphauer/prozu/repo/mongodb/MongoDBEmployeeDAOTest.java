package de.philipphauer.prozu.repo.mongodb;

import static org.junit.Assert.assertEquals;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import jersey.repackaged.com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.philipphauer.prozu.di.GuiceRunner;
import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.model.ProjectDays;

@RunWith(GuiceRunner.class)
public class MongoDBEmployeeDAOTest {

	@Inject
	private MongoDBEmployeeDAO dao;

	// TODO close mongoClient

	@Before
	public void init() {
		dao.deleteAllEmployees();
	}

	@Test
	public void find() {
		String name = "Albert Stark";
		Employee employee = new Employee(name, 0);
		YearMonth yearMonth = YearMonth.now();
		employee.addProjectDays(new ProjectDays(yearMonth, 5));

		dao.save(employee);
		List<Employee> employees = dao.getAllEmployees();

		assertEquals(1, employees.size());
		Employee albert = employees.get(0);
		assertEquals(name, albert.getName());
		List<ProjectDays> projectDays = albert.getProjectDays();
		assertEquals(1, projectDays.size());
		assertEquals(yearMonth, projectDays.get(0).getMonth());
	}

	@Test
	public void countWithSearch() {
		ArrayList<Employee> employees = Lists.newArrayList(
				new Employee("Albert Stark", 0),
				new Employee("Peter Müller", 0),
				new Employee("Paul Köhler", 0));
		dao.saveAll(employees);

		long count = dao.getEmployeeCount(Optional.of("P"));
		assertEquals(2, count);
	}

}
