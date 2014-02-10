package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.security.PermissionInspector;
import org.training.issuetracker.utils.ParameterParser;

public class EditUserCommand extends AbstractWebCommand {
	private Logger logger = Logger.getLogger("org.training.issuetracker.command");

	public EditUserCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());
					
		UserDAO userDAO = DAOFactory.getDAO(UserDAO.class);
		try {
			String oper = parser.getStringParameter(Constants.KEY_OPERATION);
			long result = 0;
			switch (oper) {
				case Constants.OPER_ADD : {
					String firstName = parser.getStringParameter(Constants.KEY_FIRST_NAME);
					String lastName = parser.getStringParameter(Constants.KEY_LAST_NAME);
					String  email = parser.getStringParameter(Constants.KEY_EMAIL);
					String  password = parser.getStringParameter(Constants.KEY_PASSWORD);
					User user = new User();
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
					user.setPassword(password);
					PropDAO propDAO = DAOFactory.getDAO(PropDAO.class);
					Role role = (Role) propDAO.getProp(PropertyType.ROLE, Constants.DEFAULT_ROLE_ID);
					user.setRole(role);
					result = userDAO.insertUser(user);
					
					logger.debug("Inserted user  = " + user);
					logger.debug("Inserted user with id = " + result);
					
					if (result > 0) {
						getRequest().getSession().setAttribute(Constants.KEY_USER, userDAO.getUser(result));
					}
					break;
				}
				case Constants.OPER_EDIT : {
					User user = (User) getRequest().getSession().getAttribute(Constants.KEY_USER);					
					PermissionInspector.checkPermission(user, this.getClass().getSimpleName());
					
					long id = Long.parseLong(parser.getStringParameter(Constants.KEY_ID));
					String firstName = parser.getStringParameter(Constants.KEY_FIRST_NAME);
					String lastName = parser.getStringParameter(Constants.KEY_LAST_NAME);
					String  email = parser.getStringParameter(Constants.KEY_EMAIL);
					String  password = parser.getStringParameter(Constants.KEY_PASSWORD);
					
					User updateUser = new User();
					updateUser.setId(id);
					updateUser.setFirstName(firstName);
					updateUser.setLastName(lastName);
					updateUser.setEmail(email);
					updateUser.setPassword(password);
										
					if (PermissionInspector.checkPermission(user, "ChangeUserRole")) {
						long roleId = Long.parseLong(parser.getStringParameter(Constants.KEY_ROLE_ID));
						PropDAO propDAO = DAOFactory.getDAO(PropDAO.class);
						Role role = (Role) propDAO.getProp(PropertyType.ROLE, roleId);
						updateUser.setRole(role);
					} else {
						updateUser.setRole(user.getRole());
					}
						
					result = userDAO.updateUser(updateUser);
					if (result > 0) {
						getRequest().getSession().removeAttribute(Constants.KEY_USER);
						getRequest().getSession().setAttribute(Constants.KEY_USER, updateUser);
					}
					break;
				}
				case Constants.OPER_DELETE : {
					long id = Long.parseLong(parser.getStringParameter(Constants.KEY_ID));
					result = userDAO.deleteUser(id);
					break;
				}
				default : {
					break;
				}
			}
							
			out.print(Constants.URL_MAIN);
		} catch (DaoException e) {
			e.printStackTrace();
			out.print(e.getMessage());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);

		} catch (ParameterNotFoundException | NumberFormatException e) {
			out.print(e.getMessage());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);

		} finally {
			out.flush();
			out.close();
		}
		
	}
		
}
