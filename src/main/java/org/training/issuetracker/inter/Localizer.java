package org.training.issuetracker.inter;

/**Interface for localization web application.
 * @author Sergei_Doroshenko
 *
 */
public interface Localizer {


	/**Method used in jsp pages.
	 * @param key - the key that maped concrete element.
	 * @return - String value that will placed in jsp.
	 */
	String getElementValue(String key);

}
