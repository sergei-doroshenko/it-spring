package org.training.issuetracker.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.ResourceBundle;

/**Interface for localization web application.
 * @author Sergei_Doroshenko
 *
 */
public interface Localizer {


	/**Method used in jsp pages.
	 * @param key - the key that maped concrete element.
	 * @return - String value that will placed in jsp.
	 * @throws UnsupportedEncodingException
	 */
	String getElementValue(String key) throws UnsupportedEncodingException;

	ResourceBundle getBundle();

	Map<String, String> getValuesMap() throws UnsupportedEncodingException;

}
