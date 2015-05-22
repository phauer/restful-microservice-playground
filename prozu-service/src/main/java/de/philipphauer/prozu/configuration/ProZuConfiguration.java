package de.philipphauer.prozu.configuration;

import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/** mapping: YAML file to this class */
public class ProZuConfiguration extends Configuration {

	@NotEmpty
	private String template;

	@NotEmpty
	private String defaultName;

	@NotNull
	@Valid
	private MongoDBConfig mongoDB;

	@JsonProperty
	public String getTemplate() {
		return template;
	}

	@JsonProperty
	public void setTemplate(String template) {
		this.template = template;
	}

	@JsonProperty
	public String getDefaultName() {
		return defaultName;
	}

	@JsonProperty
	public void setDefaultName(String name) {
		this.defaultName = name;
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
