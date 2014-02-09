package org.training.issuetracker.i18n;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.training.issuetracker.constants.Constants;

/**Class that implements Russian language localization.
 * @author Sergei_Doroshenko
 */
public class LocalizerRU implements Localizer {
	private static final String path = Constants.getRealPath() + "WEB-INF\\classes\\i18n\\";
	private ResourceBundle bundle;
	private final Locale locale;
	private final ClassLoader loader;

	public LocalizerRU (Locale locale) throws MalformedURLException {
		File file = new File(path);
		URL[] urls = {file.toURI().toURL()};
		loader = new URLClassLoader(urls);
		this.locale = locale;
	}

	@Override
	public String getElementValue(String key) throws UnsupportedEncodingException {
		return new String (bundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
	}

	@Override
	public ResourceBundle getBundle(String fileName) {
		return PropertyResourceBundle.getBundle(fileName, locale, loader);
	}

	@Override
	public Map<String, String> getValuesMap()
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

}
