package de.philipphauer.prozu.configuration;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/** mapping: YAML file to this class */
public class ProZuConfiguration extends Configuration {

	@NotEmpty
	private String template;

	@NotNull
	@Valid
	private MongoDBConfig mongoDB;

	@JsonProperty("swagger")
	public SwaggerBundleConfiguration swaggerBundleConfiguration;

	@JsonProperty
	public String getTemplate() {
		return template;
	}

	@JsonProperty
	public void setTemplate(String template) {
		this.template = template;
	}

	@JsonProperty
	public MongoDBConfig getMongoDB() {
		return mongoDB;
	}

	@JsonProperty
	public void setMongoDB(MongoDBConfig mongoDB) {
		this.mongoDB = mongoDB;
	}
}
