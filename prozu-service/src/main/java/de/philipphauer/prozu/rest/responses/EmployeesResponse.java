package de.philipphauer.prozu.rest.responses;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

import de.philipphauer.prozu.rest.URLConstants;

/**
 * data & metadata
 *
 */
@XmlRootElement
public class EmployeesResponse {

	private long totalCount;
	private List<EmployeeResponse> employees;
	private int limit;
	private int offset;
	private Optional<String> search;

	public EmployeesResponse(int limit, int offset, long totalEmployeeCount, List<EmployeeResponse> employees,
			Optional<String> search) {
		this.limit = limit;
		this.offset = offset;
		this.totalCount = totalEmployeeCount;
		this.employees = employees;
		this.search = search;
	}

	public int getLimit() {
		return limit;
	}

	public int getOffset() {
		return offset;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public String getSearch() {
		return search.isPresent() ? search.get() : "";
	}

	public List<EmployeeResponse> getEmployees() {
		return employees;
	}

	@XmlElement(name = "links")
	public List<LinkResponse> getLinks() {
		int nextOffset = offset + limit;
		String nextUrl = createUrl(limit, nextOffset, search);
		LinkResponse nextPage = new LinkResponse("nextPage", nextUrl);

		List<LinkResponse> links = Lists.newArrayList(nextPage);

		int previousOffset = offset - limit;
		if (previousOffset >= 0) {
			String previousUrl = createUrl(limit, previousOffset, search);
			LinkResponse previousPage = new LinkResponse("previousPage", previousUrl);
			links.add(previousPage);
		}
		return links;
	}

	private String createUrl(int limit, int offset, Optional<String> search) {
		String nextUrl = URLConstants.EMPLOYEES_FULL + "?limit=" + limit + "&offset=" + offset;
		if (search.isPresent()) {
			nextUrl += "&search=" + search.get();
		}
		return nextUrl;
	}
}
