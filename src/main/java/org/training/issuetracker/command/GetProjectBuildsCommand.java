package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.simple.JSONValue;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.ParameterParser;

public class GetProjectBuildsCommand extends AbstractWebCommand {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.command");

	public GetProjectBuildsCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		getResponse().setContentType(MediaType.APPLICATION_JSON);
		PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());
		List<Build> builds = null;

		ProjectDAO dao = DAOFactory.getDAO(ProjectDAO.class);

		try {
			long id = parser.getLongParameter(Constants.KEY_ID);
			builds = dao.getProjectBuilds(id);

			String json = JSONValue.toJSONString(builds);
			logger.debug(json);
			out.print(json);
		} catch (DaoException | NumberFormatException | ParameterNotFoundException e) {
			e.printStackTrace();
			out.print(e.getMessage());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} finally {
			out.flush();
			out.close();
		}
	}



}
