package de.philipphauer.prozu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.inject.Inject;

import com.google.common.base.Throwables;
import com.mongodb.BasicDBList;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;

public class MongoDbTestUtil {

	@Inject
	private DBCollection employeesCollection;

	public void writeJsonFileToDb(Path file) {
		try {
			byte[] allBytes = Files.readAllBytes(file);
			String json = new String(allBytes);
			writeJsonStringToDb(json);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void writeJsonStringToDb(String json) {
		System.out.println(json);
		BasicDBList dbObject = (BasicDBList) JSON.parse(json);
		employeesCollection.insert((List) dbObject);
	}

	public void clearEmployees() {
		employeesCollection.drop();
	}

}
