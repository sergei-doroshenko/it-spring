package org.training.issuetracker.inter;

/**Class that implements English language localization.
 * @author Sergei_Doroshenko
 */
public class LocalizerEN implements Localizer {

	@Override
	public String getElementValue(String key) {

		return "English";
	}

}
