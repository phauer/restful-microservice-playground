package de.philipphauer.prozu.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

public class Employee {

	private long id;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Za-zöäüÖÄÜ ]*", message = "must contain only letters and spaces")
	private String name;

	private List<ProjectDays> projectDays;

	public Employee() {
		projectDays = Lists.newArrayList();
	}

	public Employee(String name) {
		this.name = name;
		projectDays = Lists.newArrayList();
	}

	public Employee(String name, long id) {
		this.name = name;
		this.id = id;
		projectDays = Lists.newArrayList();
	}

	public void addProjectDays(ProjectDays bonus) {
		projectDays.add(bonus);
	}

	public void addProjectDays(List<ProjectDays> bonus) {
		projectDays.addAll(bonus);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public List<ProjectDays> getProjectDays() {
		return projectDays;
	}

	public void setProjectDays(List<ProjectDays> projectDays) {
		this.projectDays = projectDays;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(Employee.class).add("id", id)
				.add("name", name).toString();
	}

}
