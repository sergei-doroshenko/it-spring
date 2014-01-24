package org.training.issuetracker.i18n;

import java.net.MalformedURLException;


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
	public static Localizer getLocalizer (LocalizerLanguage lang) throws MalformedURLException {

		Localizer localizer = null;
		switch (lang) {
			case EN : {
				localizer = new LocalizerEN();
				break;
			}
			case RU : {
				localizer = new LocalizerRU();
				break;
			}
		default:
			break;
		}

		return localizer;
	};
}
