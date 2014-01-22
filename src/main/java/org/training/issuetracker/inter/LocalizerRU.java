package org.training.issuetracker.inter;

/**Class that implements Russian language localization.
 * @author Sergei_Doroshenko
 */
public class LocalizerRU implements Localizer {

	@Override
	public String getElementValue(String key) {

		return "Русский";
	}

}
