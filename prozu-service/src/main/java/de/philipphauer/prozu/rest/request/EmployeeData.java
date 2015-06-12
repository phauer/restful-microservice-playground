package de.philipphauer.prozu.rest.request;

import javax.xml.bind.annotation.XmlRootElement;

import com.wordnik.swagger.annotations.ApiModelProperty;

@XmlRootElement
public class EmployeeData {

	@ApiModelProperty(required = true)
	private String name;

	public String getName() {
		return name;
	}

}
