package de.philipphauer.prozu.rest.responses;

import java.time.YearMonth;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProjectDaysResponse {

	private YearMonth month;

	private int daysCount;

	public ProjectDaysResponse() {
	}

	public ProjectDaysResponse(YearMonth month, int daysCount) {
		this.month = month;
		this.daysCount = daysCount;
	}

	public YearMonth getMonth() {
		return month;
	}

	public int getDaysCount() {
		return daysCount;
	}

}
