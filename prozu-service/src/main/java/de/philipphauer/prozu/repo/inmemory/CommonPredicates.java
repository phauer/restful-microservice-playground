package de.philipphauer.prozu.repo.inmemory;

import java.util.function.Predicate;

import de.philipphauer.prozu.model.Employee;

public class CommonPredicates {

	public static Predicate<Employee> searchName(String searchString) {
		return employee -> employee.getName().toLowerCase().contains(searchString.toLowerCase());
	}

}
