package org.training.issuetracker.i18n;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.training.issuetracker.constants.Constants;

/**Class that implements English language localization.
 * @author Sergei_Doroshenko
 */
public class LocalizerEN implements Localizer {
	private static final String path = Constants.getRealPath() + "WEB-INF\\classes\\i18n\\";
	private ResourceBundle bundle;
	private final Locale locale;
	private final ClassLoader loader;

	public LocalizerEN () throws MalformedURLException {
		File file = new File(path);
		URL[] urls = {file.toURI().toURL()};
		loader = new URLClassLoader(urls);
		this.locale = new Locale("en","EN");
	}

	@Override
	public String getElementValue(String key) throws UnsupportedEncodingException {
		return new String (bundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
	}

	@Override
	public ResourceBundle getBundle(String fileName) {
		return PropertyResourceBundle.getBundle(fileName, locale, loader);
	}

	private Map<String, String> convertResourceBundleToMap(ResourceBundle resource)
				throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = new String ((resource.getString(key)).getBytes("ISO-8859-1"), "UTF-8");
            map.put(key, value);
        }

        return map;
    }

	@Override
	public Map<String, String> getValuesMap() throws UnsupportedEncodingException {
		return convertResourceBundleToMap(bundle);
	}
}
