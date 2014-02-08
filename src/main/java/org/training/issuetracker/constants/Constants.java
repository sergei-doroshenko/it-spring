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
	/**Real path to files stored in application folders.
	 * Init in ApplicationContextListener.
	 */
	private static String realPath;

	/**Constant for root path.
	 *
	 */
	public static final String ROOT_PATH = "\\";
    /**Constant for path where resources contains.
     *
     */
    public static final String RESOURCE_PATHS = "/WEB-INF/classes/xml/";

    /**url i18n properties files.*/
    public static final String RESOURCE_I18N_PATHS = "WEB-INF\\classes\\i18n\\";

    /**url part of jsp pages folder.*/
    public static final  String URL_PREFIX = "/WEB-INF/jsp";

    /** Constants string literal for page-url of view-issue.jsp */
	public static final String URL_VIEW_ISSUE = URL_PREFIX + "/view-issue.jsp";

	/** Constants string literal for page-url of edit-issue.jsp */
	public static final String URL_EDIT_ISSUE = URL_PREFIX + "/edit-issue.jsp";

	/** Constants string literal for page-url of edit-issue.jsp */
	public static final String URL_NEW_ISSUE = URL_PREFIX + "/new-issue.jsp";

	/** Constants string literal for page-url of error.jsp */
	public static final String URL_ERROR = URL_PREFIX + "/error.jsp";

	/** Constants string literal for page-fragment-url of header.jsp */
	public static final String URL_HEADER = URL_PREFIX + "/header.jsp";

	/** Constants string literal for page-fragment-url of header.jsp */
	public static final String URL_HEADER_SIMPLE = URL_PREFIX + "/header-simple.jsp";

	/** Constants string literal for page-fragment-url of header.jsp */
	public static final String URL_MENU_TOP = URL_PREFIX + "/menu_top.jsp";

	/** Constants string literal for page-fragment-url of footer.jsp */
	public static final String URL_FOOTER = URL_PREFIX + "/footer.jsp";

	/** Constants string literal for page-fragment-url of error-content.jsp */
	public static final String URL_ERROR_CONTENT = URL_PREFIX + "/error-content.jsp";

	/** Constants string literal for page-fragment-url of issue-details.jsp */
	public static final String URL_ISSUE_DETAILS = URL_PREFIX + "/issue-details.jsp";

	/** Constants string literal for page-fragment-url of buttons-view.jsp */
	public static final String URL_BUTTONS_VIEW = URL_PREFIX + "/buttons-view.jsp";

	/** Constants string literal for page-fragment-url of buttons-view-user.jsp */
	public static final String URL_BUTTONS_VIEW_USER = URL_PREFIX + "/buttons-view-user.jsp";

	/** Constants string literal for page-fragment-url of buttons-edit.jsp */
	public static final String URL_BUTTONS_EDIT = URL_PREFIX + "/buttons-edit.jsp";

	/** Constants string literal for view-user.jsp url  */
	public static final String URL_VIEW_USER = URL_PREFIX + "/view-user.jsp";

	/** Constants string literal for edit-user.jsp url  */
	public static final String URL_EDIT_USER = URL_PREFIX + "/edit-user.jsp";

	/** Constants string literal for comments-block.jsp url  */
	public static final String URL_COMMENTS_BLOCK = URL_PREFIX + "/comments-block.jsp";

	/** Constants string literal for attachments-block.jsp url  */
	public static final String URL_ATTACHMENTS_BLOCK = URL_PREFIX + "/attachments-block.jsp";

    /** Constants string literal for page-url of details.jsp */
	public static final String URL_DETAILS = "details.jsp";

	/** Constants string literal for page-url of index.jsp */
	public static final String URL_MAIN = "index.jsp";

	/** Constants string literal for page-url of search.jsp */
	public static final String URL_SEARCH = "search.jsp";

	/**
     * Directory where uploaded files will be saved, its relative to
     * the web application directory.
     */
    public static final String URL_UPLOAD_DIR = "uploads";

    /**
     * Directory for downloaded files from jsp.
     */
    public static final String URL_DOWNLOAD_DIR = getRealPath() + URL_UPLOAD_DIR;

	/** Base name for errors.properties files */
	public static final String PROPERTIES_ERRORS = RESOURCE_I18N_PATHS + "errors";


//***************** Commands *****************************************************
	/** Constants string literal for view issue list command */
	public static final String COMMAND_VIEW_ISSUE_LIST = "issuelist";

	/** Constants string literal for view issue command */
	public static final String COMMAND_VIEW_ISSUE = "issue";

	/** Constants string literal for submit issue command */
	public static final String COMMAND_SUBMIT_ISSUE = "submitissue";

	/** Constants string literal for edit issue command */
	public static final String COMMAND_EDIT_ISSUE = "editissue";

	/** Constants string literal for delete issue command */
	public static final String COMMAND_DELETE_ISSUE = "deleteissue";

	/** Constants string literal for update issue command */
	public static final String COMMAND_UPDATE_ISSUE = "update_issue";

	/** Constants string literal for update issue command */
	public static final String COMMAND_INSERT_ISSUE = "insert_issue";

	/** Constants string literal for login command  */
	public static final String COMMAND_LOGIN = "login";

	/** Constants string literal for logout command  */
	public static final String COMMAND_LOGOUT = "logout";

	/** Constants string literal for view user info command  */
	public static final String COMMAND_VIEW_USER = "viewuser";

	/** Constants string literal for edit user info command  */
	public static final String COMMAND_EDIT_USER = "edituser";
	
	/** Constants string literal for view user info command  */
	public static final String COMMAND_ADD_USER = "adduser";
	
	/** Constants string literal for edit user info command  */
	public static final String COMMAND_LOCALE = "locale";

	/** Constants string literal for edit user info command  */
	public static final String COMMAND_GET_PROJECT_BUILDS = "get_project_builds";
	
	/** Constants string literal for view statuses list command  */
	public static final String COMMAND_VIEW_STATUSES_LIST = "statuses_list";
	
	/** Constants string literal for edit status command  */
	public static final String COMMAND_EDIT_STATUS = "edit_status";

	/** Constants string literal for submitissue-url  */
	public static final String URL_MAIN_COMMAND = "/issuetracker/Main.do?command=";

	/** Constants string literal for Main.do-url  */
	public static final String URL_MAIN_DO = "Main.do";

	/** Constants string literal for submitissue-url  */
	public static final String URL_LOGOUT_COMMAND = URL_MAIN_COMMAND + COMMAND_LOGOUT;

	/** Constants string literal for submitissue-url  */
	public static final String URL_SAVEISSUE_COMMAND = URL_MAIN_COMMAND + COMMAND_UPDATE_ISSUE;

	/** Constants string literal for localize-english url  */
	public static final String URL_LOCALIZE_EN_COMMAND = "/?locale=en";

	/** Constants string literal for localize-russian url  */
//	public static final String URL_LOCALIZE_RU_COMMAND = URL_MAIN_COMMAND +"localize&lang=ru_RU&backurl=";
	public static final String URL_LOCALIZE_RU_COMMAND = URL_MAIN_COMMAND +"localize&lang=ru_RU&backurl=";

	/** Constants string literal for view user info url  */
	public static final String URL_VIEW_USER_COMMAND = URL_MAIN_COMMAND + COMMAND_VIEW_USER;

	/** Constants string literal for edit user info url  */
	public static final String URL_EDIT_USER_COMMAND = URL_MAIN_COMMAND + COMMAND_EDIT_USER;

	public static final String URL_DOWNLOAD_COMMAND = "FileUploadDownload.do?fileName=";

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
	
	/** String literal for oper parameter */
	public static final String KEY_OPERATION = "oper";
	
	/** String literal for name parameter */
	public static final String KEY_NAME = "name";
	
	/** String literal for backurl */
	public static final String KEY_BACK_URL = "backurl";

	/** String literal for locale attribute */
	public static final String KEY_LOCALE = "locale";

	/** String literal for request parameter type */
	public static final String KEY_TYPE = "type";

	/** String literal for request parameter priority */
	public static final String KEY_PRIORITY = "priority";

	/** String literal for request parameter status */
	public static final String KEY_STATUS = "status";

	/** String literal for request parameter resolution */
	public static final String KEY_RESOLUTION = "resolution";

	/** String literal for request parameter project */
	public static final String KEY_PROJECT = "project";

	/** String literal for request parameter projectbuild */
	public static final String KEY_PROJECT_BUILD = "projectbuild";

	/** String literal for request parameter assignee */
	public static final String KEY_ASSIGNEE = "assignee";

	/** String literal for request parameter summary */
	public static final String KEY_SUMMARY = "summary";

	/** String literal for request parameter description */
	public static final String KEY_DESCRIPTION = "description";

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

	/** Sting literal for getting attachments attribute on jsp */
	public static final String ATTACHMENTS = "attachments";

	/** Sting literal for getting statuses attribute on jsp */
	public static final String STATUSES = "statuses";

	/** Sting literal for getting resolutions attribute on jsp */
	public static final String RESOLUTIONS = "resolutions";

	/** Sting literal for getting priorities attribute on jsp */
	public static final String PRIORITIES = "priorities";

	/** Sting literal for getting types attribute on jsp */
	public static final String TYPES = "types";

	/** Sting literal for getting projects attribute on jsp */
	public static final String PROJECTS = "projects";

	/** Sting literal for getting build attribute on jsp */
	public static final String BUILDS = "build";

	/** Sting literal for getting assignee attribute on jsp */
	public static final String ASSIGNEES = "assignee";

	/** String literal for entity */
	public static final String ENTITY = "entity";

//****************************** OTHERS *********************************************/
	/** String literal for NEW status */
	public static final String STATUS_NEW = "NEW";

	/** String literal for NEW status id */
	public static final long DEFAULT_STATUS = 2;

	/** String literal for empty string */
	public static final String EMPTY_STRING = "";
	
//********************************************** *************************************/
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
