package de.philipphauer.prozu.configuration;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/** mapping: YAML file to this class */
public class ProZuConfiguration extends Configuration {

	@NotNull
	@Valid
	private MongoDBConfig mongoDB;

	@JsonProperty("swagger")
	public SwaggerBundleConfiguration swaggerBundleConfiguration;

	@JsonProperty
	public MongoDBConfig getMongoDB() {
		return mongoDB;
	}

	@JsonProperty
	public void setMongoDB(MongoDBConfig mongoDB) {
		this.mongoDB = mongoDB;
	}
}
