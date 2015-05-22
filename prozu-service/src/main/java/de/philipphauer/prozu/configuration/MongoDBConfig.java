package de.philipphauer.prozu.configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MongoDBConfig {

	@NotEmpty
	private String databaseName;

	@NotEmpty
	private String employeeCollectionName;

	@JsonProperty
	public String getDatabaseName() {
		return databaseName;
	}

	@JsonProperty
	public String getEmployeeCollectionName() {
		return employeeCollectionName;
	}

	@JsonProperty
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	@JsonProperty
	public void setEmployeeCollectionName(String employeeCollectionName) {
		this.employeeCollectionName = employeeCollectionName;
	}
}
