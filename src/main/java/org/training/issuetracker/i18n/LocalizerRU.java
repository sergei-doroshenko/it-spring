package org.training.issuetracker.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.ResourceBundle;

/**Class that implements Russian language localization.
 * @author Sergei_Doroshenko
 */
public class LocalizerRU implements Localizer {

	@Override
	public String getElementValue(String key) {

		return "Русский";
	}

	@Override
	public ResourceBundle getBundle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getValuesMap()
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

}
