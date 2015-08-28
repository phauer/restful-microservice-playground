package de.philipphauer.prozu.rest.responses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorResponse {

	private String message;
	private long id;

	public ErrorResponse(String message, long id) {
		this.message = message;
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public long getId() {
		return id;
	}

	public String getDetailsURL() {
		return "/doc/" + id;
	}
}
