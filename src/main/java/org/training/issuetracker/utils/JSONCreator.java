package org.training.issuetracker.utils;

import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import org.training.issuetracker.data.xml.DataStorage;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;

public class JSONCreator {
	private static JsonBuilderFactory factory = Json.createBuilderFactory(null);

	public static JsonObject createBulkIssueJson (User user, Map<Long, Issue> issueMap) {

		JsonArrayBuilder arrBuild = factory.createArrayBuilder();

		if (issueMap != null) {
			for (Entry<Long, Issue> entry : issueMap.entrySet()) {
				Issue issue = entry.getValue();
				if (null != user) {
					if (issue.getAssignee().equals(user)) {
						arrBuild.add(issue.toJson());
					}
				} else {
					arrBuild.add(issue.toJson());
				}

			}
		}
		JsonArray array = arrBuild.build();

		JsonObject value = factory.createObjectBuilder()
				.add("page", 1)
				.add("records", 10)
				.add("userdata", createUserData(user))
				.add("rows", array).build();

		return value;
	}

	public static JsonObject createUserData (User user) {

		JsonObject userdata = factory.createObjectBuilder()
				.add("role", "guest")
				.add("name", "guest")
				.build();

		if (null != user) {
			userdata = user.toJsonObj();
		}

		return userdata;
	}

	public static JsonObject createLoginData (User user) {
		return factory.createObjectBuilder()
				.add("userdata", user.toJsonObj())
				.build();
	}

	public static JsonObject createSingleIssueJson (User user, long id) {
		DataStorage data = DataStorage.getInstance();
		JsonObject value = factory.createObjectBuilder()
				.add("userdata", createUserData(user))
				.add("issue", data.getIssue(id).toJsonObj()).build();

		return value;
	}
}
