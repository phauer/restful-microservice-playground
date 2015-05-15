package de.philipphauer.prozu.repo.shared;

import java.time.YearMonth;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.philipphauer.prozu.model.Employee;
import de.philipphauer.prozu.model.ProjectDays;
import de.philipphauer.prozu.rest.util.NameGenerator;

@Singleton
public class DummyDataGenerator {

	@Inject
	private AtomicIDGenerator idGenerator;

	public List<Employee> createEmployees(int amount) {
		List<Employee> employees = IntStream.rangeClosed(1, amount)
				.mapToObj((index) -> createEmployee())
				.collect(Collectors.toList());
		return employees;
	}

	private Employee createEmployee() {
		Employee employee = new Employee(NameGenerator.generateName(), idGenerator.generateID());
		List<ProjectDays> bonuses = createRandomProjectDays(employee);
		employee.addProjectDays(bonuses);
		return employee;
	}

	private List<ProjectDays> createRandomProjectDays(Employee employee) {
		int bonusCount = randomInt(0, 12);
		List<ProjectDays> projectDays = IntStream.rangeClosed(0, bonusCount).mapToObj((index) -> {
			int days = randomInt(1, 30);
			int month = randomInt(1, 12);
			YearMonth months = YearMonth.of(2015, month);
			ProjectDays projectDay = new ProjectDays(months, days);
			//			ProjectDays projectDay = new ProjectDays(months, days, employee);
				return projectDay;
			}).collect(Collectors.toList());
		return projectDays;
	}

	private int randomInt(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min) + min;
	}
}
