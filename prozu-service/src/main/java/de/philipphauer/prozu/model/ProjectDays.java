package de.philipphauer.prozu.model;

import java.time.YearMonth;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class ProjectDays {

	@ObjectId
	@JsonProperty("_id")
	private String mongoId;

	private long id;

	@NotNull
	private YearMonth month;

	@Min(0)
	@Max(31)
	@NotNull
	private int daysCount;

	// used for RDB/JPA, not necessary with MongoDB
	// private Employee employee;

	public ProjectDays() {
	}

	public ProjectDays(YearMonth month, int daysCount
	// , Employee employee
	) {
		this.month = month;
		this.daysCount = daysCount;
		// this.employee = employee;
	}

	public YearMonth getMonth() {
		return month;
	}

	public void setMonth(YearMonth month) {
		this.month = month;
	}

	public int getDaysCount() {
		return daysCount;
	}

	public void setDaysCount(int daysCount) {
		this.daysCount = daysCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// public void setEmployee(Employee employee) {
	// this.employee = employee;
	// }
	//
	// public Employee getEmployee() {
	// return employee;
	// }

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(ProjectDays.class).add("id", id)
				// .add("employee", employee.getId())
				.add("month", month).add("daysCount", daysCount).toString();
	}
}
