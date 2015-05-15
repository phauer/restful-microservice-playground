package de.philipphauer.prozu.repo.mongodb;

public class MongoDBConfig {

	//TODO use ProZuConfiguration instead

	private String databaseName;
	private String employeeCollectionName;

	public MongoDBConfig(String databaseName, String employeeCollectionName) {
		super();
		this.databaseName = databaseName;
		this.employeeCollectionName = employeeCollectionName;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public String getEmployeeCollectionName() {
		return employeeCollectionName;
	}

}
