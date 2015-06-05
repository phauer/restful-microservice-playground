package de.philipphauer.prozu.rest.responses;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

import de.philipphauer.prozu.rest.URLConstants;

@XmlRootElement
public class EmployeeResponse {

	private String id;

	private String name;

	public EmployeeResponse() {
	}

	public EmployeeResponse(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/*
	 * "links": [ { "rel": "something-related", "href": "/arbitrary/link" }, { "rel": "something-else-related", "href":
	 * "/another/arbitrary/link" }
	 */
	@XmlElement(name = "links")
	public List<LinkResponse> getLinks() {
		List<LinkResponse> links = Lists.newArrayList(
				new LinkResponse("details", URLConstants.EMPLOYEES_FULL + "/" + id),
				new LinkResponse("projectdays", URLConstants.EMPLOYEES_FULL + "/" + id + "/projectdays")
				);
		return links;
	}

}
