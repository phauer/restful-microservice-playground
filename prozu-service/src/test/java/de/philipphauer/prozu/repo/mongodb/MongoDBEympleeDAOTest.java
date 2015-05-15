package de.philipphauer.prozu.repo.mongodb;

import java.net.UnknownHostException;
import java.time.YearMonth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongojack.internal.MongoJackModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.model.ProjectDays;
import de.philipphauer.prozu.util.ser.Java8TimeModule;

public class MongoDBEympleeDAOTest {

	// @Inject
	// private DummyDataGenerator generator;

	// TODO use injection
	private MongoClient mongoClient;
	private MongoDBEmployeeDAO dao;

	@Before
	public void init() throws UnknownHostException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Java8TimeModule());
		MongoJackModule.configure(objectMapper);

		mongoClient = new MongoClient();
		DB db = mongoClient.getDB("test");
		DBCollection col = db.getCollection("employeesTest");
		//		dao = new MongoDBEmployeeDAO(col);
	}

	@After
	public void cleanup() {
		mongoClient.close();
	}

	@Test
	public void save() {
		Employee employee = new Employee("Maria");
		employee.addProjectDays(new ProjectDays(YearMonth.now(), 5));
		// List<Employee> employees = generator.createEmployees(150);
		dao.save(employee);
	}
}
