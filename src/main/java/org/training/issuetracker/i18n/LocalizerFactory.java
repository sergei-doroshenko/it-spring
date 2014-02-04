package org.training.issuetracker.i18n;

import java.net.MalformedURLException;
import java.util.Locale;


/**Factory that create Localizer object.
 * @author Sergei_Doroshenko
 *
 */
public class LocalizerFactory {

	/**Method create and return Localizer object.
	 * @param lang - Enum contains languages.
	 * @return - Localizer object
	 * @throws MalformedURLException
	 */
	public static Localizer getLocalizer (Locale locale) throws MalformedURLException {
		String language = null;
		if (locale != null) {
			language = locale.getLanguage();
		} else {
			language = "en";
		}

		Localizer localizer = null;
		switch (language) {
			case "en" : {
				localizer = new LocalizerEN();
				break;
			}
			case "ru" : {
				localizer = new LocalizerRU();
				break;
			}
		default:
			localizer = new LocalizerEN();
			break;
		}

		return localizer;
	};
}
