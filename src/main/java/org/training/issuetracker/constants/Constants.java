package org.training.issuetracker.constants;

import org.training.issuetracker.i18n.LocalizerLanguage;

/**This class contains constants.
 * @author Sergei_Doroshenko
 *
 */
public final class Constants {
//********************	Path ***********************************************
	/**Constant for root path.
	 *
	 */
	public static final String ROOT_PATH = "\\";
    /**Constant for path where resources contains.
     *
     */
    public static final String RESOURCE_PATHS = "/WEB-INF/classes/xml/";
    /**Url part for error page.
     *
     */
    public static final String JUMP_ERROR = "/error.html";

//************* Parameters Keys *****************************************************
    /**String literal for id parameter.
     */
    public static final String KEY_ID = "id";
    /**login string literal.
     */
    public static final String KEY_LOGIN = "login";
	/**password string literal.
	 */
	public static final String KEY_PASSWORD = "password";

	/**user string literal.
	 */
	public static final String KEY_USER = "user";

	/**
	 * Contains string literal for language parameter.
	 */
	public static final String KEY_LANGUAGE = "lang";

	/**
	 *  Contains string literal for command parameter.
	 */
	public static final String KEY_COMMAND = "command";
//*************** Errors ****************************************************
    /**Error caused by wrong input.
     *
     */
    public static final String ERROR_SOURCE = "Input source proccessing problems.";

    /**Error caused by wrong url without command parameter.
     *
     */
    public static final String ERROR_COMMAND_PARAM = "Command parameter not found.";
    /**Error caused by wrong login enter.
     *
     */
    public static final String ERROR_LOGIN = "login illegal!";
    /**Error occurred when login is wrong.
     *
     */
    public static final String ERROR_PASSWORD = "password illegal!";
    /**Text of error message occurred when login or password are wrong.
     *
     */
    public static final String LOGIN_OR_PASSWORD_WRONG = "check login and password!";
    /**Error message cased by wrong e-mail input.
     *
     */
    public static final String ERROR_EMAIL = null;

    /**errorMessage string literal.
     */
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    /**url part of jsp pages folder.
     *
     */
    public static final  String URL_PREFIX = "/WEB-INF/jsp";

	/**This constant assign default language that used for.
	 * internationalization - English language.
	 */
	public static final LocalizerLanguage DEFAULT_LANGUAGE = LocalizerLanguage.EN;

//********************* Attributes *******************************************
	/**
	 * Contains string literal for localizer attribute.
	 */
	public static final String KEY_LOCALIZER = "localizer";

	/**Real path to files stored in application folders.
	 * Init in ApplicationContextListener.
	 */
	private static String realPath;

    /**
	 * Private constructor
	 */
	private Constants() { }

	/**Getter for realPath field.
	 * @return App realPath.
	 */
	public static String getRealPath() {
		return realPath;
	}

	/**Setter for realPath field.
	 * @param realPath - string url part.
	 */
	public static void setRealPath(String realPath) {
		Constants.realPath = realPath;
	}
}
