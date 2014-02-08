package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.ParameterParser;

public class EditStatusCommand extends AbstractWebCommand {
	private Logger logger = Logger.getLogger("org.training.issuetracker.command");
	
	public EditStatusCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());
		User user = (User) getRequest().getSession().getAttribute(Constants.KEY_USER);
		
		if (user == null || !user.getRole().getName().equals(Constants.ROLE_ADMIN)) {
			return;
		}
		
		PropDAO propDAO = DAOFactory.getDAO(PropDAO.class);
		try {
			String oper = parser.getStringParameter(Constants.KEY_OPERATION);
			long result = 0;
			switch (oper) {
				case "add" : {
					String name = parser.getStringParameter(Constants.KEY_NAME);
					Status status = new Status();
					status.setName(name);
					result = propDAO.insertProp(PropertyType.STATUS, status);
					break;
				}
				case "edit" : {
					long id = Long.parseLong(parser.getStringParameter(Constants.KEY_ID));
					String name = parser.getStringParameter(Constants.KEY_NAME);
					Status status = new Status();
					status.setId(id);
					status.setName(name);
					result = propDAO.updateProp(PropertyType.STATUS, status);
					break;
				}
				case "del" : {
					long id = Long.parseLong(parser.getStringParameter(Constants.KEY_ID));
					result = propDAO.deleteProp(PropertyType.STATUS, id);
					break;
				}
				default : {
					break;
				}
			}
			
			out.print(result);
		} catch (DaoException | ParameterNotFoundException e) {
			e.printStackTrace();
			out.print(e.getMessage());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} finally {
			out.flush();
			out.close();
		}
	}
	
	

}
