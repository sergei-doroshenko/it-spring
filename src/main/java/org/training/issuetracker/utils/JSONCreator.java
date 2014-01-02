package org.training.issuetracker.utils;

import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import org.training.issuetracker.domain.Issue;

public class JSONCreator {
	private static JsonBuilderFactory factory = Json.createBuilderFactory(null); 
	
	public static JsonObject createBulkIssueJson (Map<Long, Issue> issueMap) {
		
		JsonArrayBuilder arrBuild = factory.createArrayBuilder();
		
		if (issueMap != null) {
			for (Entry<Long, Issue> entry : issueMap.entrySet()) {
				Issue issue = entry.getValue();
				arrBuild.add(issue.toJson());
			}
		}
		JsonArray array = arrBuild.build();
		
		JsonObject value = factory.createObjectBuilder()
				.add("page", 1)
				.add("records", 10)
				.add("rows", array).build();
		
		return value;
	}
	
}
