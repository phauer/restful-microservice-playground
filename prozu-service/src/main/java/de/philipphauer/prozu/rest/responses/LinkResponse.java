package de.philipphauer.prozu.rest.responses;

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
	private String reference;

	public LinkResponse(String relation, String reference) {
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
	public String getReference() {
		return reference;
	}

}
