package de.philipphauer.prozu.rest.responses;

import java.net.URI;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class LinkResponse {

	/*
	 * "links": [ { "rel": "something-related", "href": "/arbitrary/link" }, {
	 * "rel": "something-else-related", "href": "/another/arbitrary/link" }
	 */
	private String relation;
	private URI reference;

	public LinkResponse(String relation, URI reference) {
		this.relation = relation;
		this.reference = reference;
	}

	@XmlElement(name = "rel")
	// wildfly
	@JsonProperty(value = "rel")
	// dropwizard
	public String getRelation() {
		return relation;
	}

	@XmlElement(name = "href")
	@JsonProperty(value = "href")
	public URI getReference() {
		return reference;
	}

}
