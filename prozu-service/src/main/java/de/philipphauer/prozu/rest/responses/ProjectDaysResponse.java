package de.philipphauer.prozu.rest.responses;

import java.time.YearMonth;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProjectDaysResponse {

	private long id;

	private YearMonth month;

	private int daysCount;

	public ProjectDaysResponse() {
	}

	public ProjectDaysResponse(long id, YearMonth month, int daysCount) {
		this.id = id;
		this.month = month;
		this.daysCount = daysCount;
	}

	public long getId() {
		return id;
	}

	public YearMonth getMonth() {
		return month;
	}

	public int getDaysCount() {
		return daysCount;
	}

}
