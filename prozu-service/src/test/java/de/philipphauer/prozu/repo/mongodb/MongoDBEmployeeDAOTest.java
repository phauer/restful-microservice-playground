package de.philipphauer.prozu.repo.mongodb;

import static org.junit.Assert.assertEquals;

import java.time.YearMonth;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.philipphauer.prozu.di.GuiceRunner;
import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.model.ProjectDays;

@RunWith(GuiceRunner.class)
public class MongoDBEmployeeDAOTest {

	//TODO fix bug when using queryString (broken regex)

	@Inject
	private MongoDBEmployeeDAO dao;

	// TODO close mongoClient

	@Before
	public void init() {
		dao.deleteAllEmployees();
	}

	@Test
	public void find() {
		Employee employee = new Employee("Albert Stark", 0);
		employee.addProjectDays(new ProjectDays(YearMonth.now(), 5));
		dao.save(employee);

		List<Employee> employees = dao.getAllEmployees();
		assertEquals(1, employees.size());
		assertEquals("Albert Stark", employees.get(0).getName());
	}

}
