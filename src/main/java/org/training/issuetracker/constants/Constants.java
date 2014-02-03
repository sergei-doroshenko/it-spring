package org.training.issuetracker.constants;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

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

    /**url part of jsp pages folder.*/
    public static final  String URL_PREFIX = "/WEB-INF/jsp";

    /** Constants string literal for page-url of index.jsp */
	public static final String URL_MAIN = "index.jsp";

	/** Constants string literal for page-url of view-issue.jsp */
	public static final String URL_VIEW_ISSUE = "/WEB-INF/jsp/view-issue.jsp";

	/** Constants string literal for page-url of edit-issue.jsp */
	public static final String URL_EDIT_ISSUE = "/WEB-INF/jsp/edit-issue.jsp";

    /** Constants string literal for page-url of details.jsp */
	public static final String URL_DETAILS = "details.jsp";

	/** Constants string literal for page-url of error.jsp */
	public static final String URL_ERROR = "/WEB-INF/jsp/error.jsp";

	/** Constants string literal for page-url of search.jsp */
	public static final String URL_SEARCH = "search.jsp";

	/** Constants string literal for page-fragment-url of header.jsp */
	public static final String URL_HEADER = "/WEB-INF/jsp/header.jsp";

	/** Constants string literal for page-fragment-url of header.jsp */
	public static final String URL_MENU_TOP = "/WEB-INF/jsp/menu_top.jsp";

	/** Constants string literal for page-fragment-url of footer.jsp */
	public static final String URL_FOOTER = "/WEB-INF/jsp/footer.jsp";

	/** Constants string literal for page-fragment-url of error-content.jsp */
	public static final String URL_ERROR_CONTENT = "/WEB-INF/jsp/error-content.jsp";

	/** Constants string literal for page-fragment-url of issue-details.jsp */
	public static final String URL_ISSUE_DETAILS = "/WEB-INF/jsp/issue-details.jsp";

	/** Constants string literal for page-fragment-url of buttons-view.jsp */
	public static final String URL_BUTTONS_VIEW = "/WEB-INF/jsp/buttons-view.jsp";

	/** Constants string literal for page-fragment-url of buttons-edit.jsp */
	public static final String URL_BUTTONS_EDIT = "/WEB-INF/jsp/buttons-edit.jsp";

	/** Constants string literal for submitissue-url  */
	public static final String URL_MAIN_COMMAND = "/issuetracker/Main.do?command=";

	/** Constants string literal for localize-english url  */
	public static final String URL_LOCALIZE_EN_COMMAND = "Main.do?command=localize&lang=en_EN&backurl=";

	/** Constants string literal for localize-russian url  */
	public static final String URL_LOCALIZE_RU_COMMAND = "Main.do?command=localize&lang=ru_RU&backurl=";

//***************** Commands *****************************************************
	/** Constants string literal for submit issue command */
	public static final String COMMAND_SUBMIT_ISSUE = "submitissue";


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
//************************ ROLES *****************************************
	/** Constants string literal for role - user. */
	public static final String ROLE_USER = "USER";

	/** Constants string literal for role - admin. */
	public static final String ROLE_ADMIN = "ADMINISTRATOR";

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
    public static final String ERROR_EMAIL = "login invalid!";

    /**errorMessage string literal.
     */
    public static final String ERROR_FIND_USER = "User not found!";

    /**errorMessage string literal.
     */
    public static final String KEY_ERROR_MESSAGE = "errorMessage";

    /**
     * errorMessage appear when constant map not created
     */
    public static final String CONSTANTS_MAP_INIT_ERR = "Error of constants initialization!";

	/**This constant assign default language that used for.
	 * internationalization - English language.
	 */
	public static final String DEFAULT_LANGUAGE = "en_en";

//********************* Attributes *******************************************
	/**
	 * Contains string literal for localizer attribute.
	 */
	public static final String KEY_LOCALIZER = "localizer";

	/** String literal for entity type*/
	public static final String ENTITY_TYPE = "type";

	/** Sting literal for issue type */
	public static final String ISSUE = "issue";

	/** Sting literal for comments on jsp */
	public static final String COMMENTS = "comments";

	/** String literal for entity */
	public static final String ENTITY = "entity";

	/** String literal for backurl */
	public static final String KEY_BACK_URL = "backurl";

	/** String literal for locale attribute */
	public static final String KEY_LOCALE = "locale";

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

	/**Method create map consist from static final constants.
	 * declared in this Constants class.
	 * @return TreeMap of String, Object.
	 */
	public static final Map<String, Object> getConstMap() {
		Logger log = Logger.getLogger("org.training.issuetracker.constants");
		Map<String, Object> map = new TreeMap<String, Object>();

		Field[] fields = Constants.class.getDeclaredFields();

		for (Field field : fields) {

		   int modifier = field.getModifiers();
		   log.debug("Mod = " + modifier);

		   if (Modifier.isPublic(modifier) && Modifier.isStatic(modifier) && Modifier.isFinal(modifier)) {
		      try {
		    	 log.debug("field name = " + field.getName() + " /val name = " + field.get(null));
		         map.put(field.getName(), field.get(null));//Obj param of get method is ignored for static fields
		      } catch (IllegalAccessException e) { /* ignorable due to modifiers check */ }
		   }
		}
		return map;
	}

}
