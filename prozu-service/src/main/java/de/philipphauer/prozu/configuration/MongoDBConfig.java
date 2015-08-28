package de.philipphauer.prozu.configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MongoDBConfig {

	@NotEmpty
	private String databaseName;

	@NotEmpty
	private String employeeCollectionName;

	private int port = 27017;

	private String host = "localhost";

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

	@JsonProperty
	public int getPort() {
		return port;
	}

	@JsonProperty
	public void setPort(int port) {
		this.port = port;
	}

	@JsonProperty
	public String getHost() {
		return host;
	}

	@JsonProperty
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "MongoDBConfig [databaseName=" + databaseName + ", employeeCollectionName=" + employeeCollectionName
				+ ", port=" + port + ", host=" + host + "]";
	}

}
