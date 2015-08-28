package de.philipphauer.prozu.rest.responses;

import java.net.URI;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

import de.philipphauer.prozu.rest.URLConstants;

@XmlRootElement
public class EmployeeResponse {

	private String id;

	private String name;

	private URI baseUri;

	public EmployeeResponse() {
	}

	public EmployeeResponse(String id, String name, URI baseUri) {
		this.id = id;
		this.name = name;
		this.baseUri = baseUri;
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
		URI detailsUrl = baseUri.resolve(URLConstants.EMPLOYEES_RELATIVE_URL + "/" + id);
		URI projectDaysUrl = baseUri.resolve(URLConstants.EMPLOYEES_RELATIVE_URL + "/" + id + "/projectdays");
		List<LinkResponse> links = Lists.newArrayList(new LinkResponse("details", detailsUrl), new LinkResponse(
				"projectdays", projectDaysUrl));
		return links;
	}

}
