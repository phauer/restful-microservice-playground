package de.philipphauer.prozu.rest;

import java.text.MessageFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("debug")
public class DebugResource {

	private static Logger logger = LoggerFactory.getLogger(DebugResource.class);

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String debug() {
		String dbAddr = System.getProperty("dbHost");
		String dbPort = System.getProperty("dbPort");
		String message = MessageFormat.format("db host: {0}, db port: {1}", dbAddr, dbPort);
		logger.info(message);
		return message;
	}

}
