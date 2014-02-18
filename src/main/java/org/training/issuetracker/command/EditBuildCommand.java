package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.security.PermissionInspector;
import org.training.issuetracker.utils.ParameterParser;

public class EditBuildCommand extends AbstractWebCommand {

	public EditBuildCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());
		User user = (User) getRequest().getSession().getAttribute(Constants.KEY_USER);
		PermissionInspector.checkPermission(user, this.getClass().getSimpleName());

		ProjectDAO buildDAO = DAOFactory.getDAO(ProjectDAO.class);
		try {
			String oper = parser.getStringParameter(Constants.KEY_OPERATION);

			long result = 0;
			switch (oper) {
				case Constants.OPER_ADD : {
					String name = parser.getStringParameter(Constants.KEY_NAME);
					long projectId = parser.getLongParameter(Constants.KEY_PROJECT_ID);

					Build build = new Build();
					build.setName(name);
					build.setProjectId(projectId);

					result = buildDAO.insertBuild(build);
					break;
				}
				case Constants.OPER_EDIT : {
					String name = parser.getStringParameter(Constants.KEY_NAME);
					long projectId = parser.getLongParameter(Constants.KEY_PROJECT_ID);
					long buildId = parser.getLongParameter(Constants.KEY_ID);

					Build build = new Build();
					build.setId(buildId);
					build.setName(name);
					build.setProjectId(projectId);

					result = buildDAO.updateBuild(build);
					break;
				}
				case Constants.OPER_DELETE : {
					long id = Long.parseLong(parser.getStringParameter(Constants.KEY_ID));
					result = buildDAO.deleteBuild(id);
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
