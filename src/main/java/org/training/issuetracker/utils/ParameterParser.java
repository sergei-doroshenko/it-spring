package org.training.issuetracker.utils;

import javax.servlet.ServletRequest;

import org.training.issuetracker.exceptions.ParameterNotFoundException;

/**Class that encapsulate login of getting parameters from request.
 * @author Sergei_Doroshenko
 *
 */
public class ParameterParser {
	/**
	 * Servlet request.
	 */
	private ServletRequest request;

	/**
	 * Default constructor.
	 */
	public ParameterParser() {	}

	/**General purpose constructor.
	 * @param request an ServletRequest
	 */
	public ParameterParser(ServletRequest request) {
		super();
		this.request = request;
	}

	/**Get string parameter from request.
	 * @param name - name of requested parameter.
	 * @return - parameter value.
	 * @throws ParameterNotFoundException
	 */
	public String getStringParameter(String name) throws ParameterNotFoundException {
		String[] values = null;
		String param = null;
		try {
			values = request.getParameterValues(name);
		} catch (NullPointerException e) {
			throw new ParameterNotFoundException(e.getMessage());
		}

		if (values == null) {
			throw new ParameterNotFoundException(name + " not found");
		} else if (values.length > 1) {
			throw new ParameterNotFoundException(name + " double parameter");
		} else if (values[0].length() == 0) {
			throw new ParameterNotFoundException(name + " not found");
		} else if (values[0].isEmpty()) {
			throw new ParameterNotFoundException(name + " not found");
		} else {
			param = values[0];
		}

		return param;
	}

	/**Method get integer parameter from request.
	 * @param name - parameter name.
	 * @return - int value of requested parameter.
	 * @throws ParameterNotFoundException
	 */
	public int getIntParameter(String name)
			throws ParameterNotFoundException {
		return Integer.parseInt(getStringParameter(name));

	}

	/**Method get long parameter from request.
	 * @param name - parameter name.
	 * @return - long value of requested parameter.
	 * @throws ParameterNotFoundException
	 */
	public long getLongParameter(String name)
			throws ParameterNotFoundException {
		return Long.parseLong(getStringParameter(name));

	}

}
