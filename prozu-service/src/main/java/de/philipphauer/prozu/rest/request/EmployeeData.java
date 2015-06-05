package de.philipphauer.prozu.rest.request;

import javax.xml.bind.annotation.XmlRootElement;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "information about a single employee.")
@XmlRootElement
public class EmployeeData {

	@ApiModelProperty(required = true)
	private String name;

	public String getName() {
		return name;
	}

}
