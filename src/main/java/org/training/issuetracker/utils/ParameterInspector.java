package org.training.issuetracker.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.ValidationException;

/**Class encapsulate validation and inspection.
 * parameter values received from request.
 * @author Sergei_Doroshenko
 *
 */
public final class ParameterInspector {
	/**
	 * User name pattern.
	 */
	public static final String NAME_PATTERN = "[a-zA-Z0-9]{3,10}";
    /**
     * User password pattern.
     */
    public static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{3,6}";
    /**
     * User e-mail pattern.
     */
    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+";

	/**
	 * Default constructor.
	 */
	private ParameterInspector() { }

	/**Method checks login parameter value.
	 * @param name - name value.
	 * @return - true if name is valid or false if not.
	 * @throws ValidationException
	 */
	public static boolean checkName(String name) throws ValidationException {
		Pattern pattern = Pattern.compile(NAME_PATTERN);

		if (name == null) {
			throw new ValidationException("Wrong parameter enter!");
		} else {
			Matcher matcher = pattern.matcher(name);
			if (!matcher.matches()) {
				throw new ValidationException(Constants.ERROR_LOGIN);
			}

			return true;
		}
	}

	/**Check password value for validation rule and equals with.
	 * password confirmation.
	 * @param pass - password parameter value.
	 * @param confpass - password confirmation value.
	 * @return - true if password and confirmation is valid.
	 * @throws ValidationException
	 */
	public static boolean checkPassword(String pass, String confpass) throws ValidationException {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(pass);

		if (!matcher.matches() || !pass.equals(confpass)) {
			throw new ValidationException(Constants.ERROR_PASSWORD);
		}

		return true;
	}

	/**Check password value for validation rule.
	 * @param pass - password parameter value.
	 * @return - true if password and confirmation is valid.
	 * @throws ValidationException
	 */
	public static boolean checkPassword(String pass) throws ValidationException {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(pass);

		if (!matcher.matches()) {
			throw new ValidationException(Constants.ERROR_PASSWORD);
		}

		return true;
	}


	/**Check e-mail value for validation certain e-mail pattern.
	 * see above.
	 * @param name - email value.
	 * @return - true if password and confirmation is valid.
	 * @throws ValidationException
	 */
	public static boolean checkEmail(String name) throws ValidationException {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(name);

		if (!matcher.matches()) {
			throw new ValidationException(Constants.ERROR_EMAIL);
		}

		return true;
	}

	public static boolean checkId(String id) {
		if (id == null || id.isEmpty()) {
			return false;
		}
		return true;
	}

	/**Check all user credentials.
	 * @param firstName - user first name.
	 * @param lastName - user last name.
	 * @param mail - user e-mail.
	 * @param pass - user password.
	 * @param confpass - user password confirmation.
	 * @return - User object if all credentials is valid with fields populated
	 * by arguments.
	 * @throws ValidationException if some of credentials in invalid.
	 */
	public static User checkUser(String firstName, String lastName, String mail,
			String pass, String confpass) throws ValidationException {

		User user = new User();

		if (checkName(firstName) && checkName(lastName) && checkPassword(pass, confpass)
				&& checkEmail(mail)) {
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(mail);
			user.setPassword(pass);
		}

		return user;

	}
}
