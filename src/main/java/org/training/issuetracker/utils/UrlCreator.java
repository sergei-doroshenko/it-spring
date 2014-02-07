package org.training.issuetracker.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class UrlCreator {
	private String path;
	private Map<String, String> parameters = new TreeMap<String, String>();

	public static String PATH_SEPARATOR = "?";

	public static String PARAMETERS_SEPARATOR = "&";

	public static String KEY_VALUE_SEPARATOR = "=";

	public UrlCreator() { }

	public UrlCreator(String path, Map<String, String> parameters) {
		super();
		this.path = path;
		this.parameters = parameters;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public void addParameter(String name, Object value) {
		parameters.put(name, value.toString());
	}

	public String create() {
		String queryString = "";

		if (parameters != null) {
			int size = parameters.entrySet().size();
			for (Entry<String, String> entry : parameters.entrySet()) {
				size--;
				queryString += (entry.getKey() + KEY_VALUE_SEPARATOR + entry.getValue());
				if (size > 0)
					queryString += PARAMETERS_SEPARATOR;
			}
		}

		String url = path;
		if (!queryString.isEmpty()) {
			url += PATH_SEPARATOR + queryString;
		}

		return url;
	}
}
