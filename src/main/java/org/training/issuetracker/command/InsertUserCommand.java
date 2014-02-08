package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.Type;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.RoleDAO;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.ParameterInspector;
import org.training.issuetracker.utils.ParameterParser;
import org.training.issuetracker.utils.UrlCreator;

public class InsertUserCommand extends AbstractWebCommand {
	private Logger logger = Logger.getLogger("org.training.issuetracker.command");

	public InsertUserCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());
		User user = (User) getRequest().getSession().getAttribute(Constants.KEY_USER);
		
		if (user == null) {
			return;
		}
		
		try {

			String firstName = parser.getStringParameter(Constants.KEY_FIRST_NAME);
			String lastName = parser.getStringParameter(Constants.KEY_LAST_NAME);
			String  email = parser.getStringParameter(Constants.KEY_EMAIL);
			String  password = parser.getStringParameter(Constants.KEY_PASSWORD);

			User newUser = new User();
			
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setEmail(email);
			newUser.setPassword(password);
			PropDAO propDAO = DAOFactory.getDAO(PropDAO.class);
			Role role = (Role) propDAO.getProp(PropertyType.ROLE, Constants.DEFAULT_ROLE_ID);
			newUser.setRole(role);
			
			UserDAO userDAO = DAOFactory.getDAO(UserDAO.class);
					
			long result = userDAO.insertUser(newUser);

			logger.debug("Inserted issue with id = " + result);
			
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
