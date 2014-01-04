package org.training.issuetracker.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.xml.ConstantsXML;
import org.training.issuetracker.data.xml.DataStorage;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.JSONCreator;
import org.training.issuetracker.utils.ParameterInspector;
import org.training.issuetracker.utils.ParameterParser;

import com.google.gson.Gson;

/**
 * Servlet implementation class Main
 */
@WebServlet(value="/Main.do", loadOnStartup=1)
public class Main extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		ConstantsXML.RESOURCE_REAL_PATH = config.getServletContext().getRealPath(Constants.ROOT_PATH);
	}

	@Override
	void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		DataStorage data = DataStorage.getInstance();
		//JsonObject json = null;
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		//if(null == user) {
			String idStr = request.getParameter("id");
			
			if (null == idStr) {
				JsonObject json = JSONCreator.createBulkIssueJson(data.getIssuesMap());
				out.print(json);
			} else {
				long id = Long.parseLong(idStr);
				JsonObject json = data.getIssue(id).toJsonObj();
				//Gson gson = new Gson();
				//String jsons = gson.toJson(data.getIssue(id));
				System.out.println(json);
				out.print(json);
			}

//		} else {
//			session.removeAttribute(Constants.KEY_USER);
//			response.sendRedirect("http://localhost:8080/issuetracker/");
//			return;
//		}
		
		
		
		out.flush();
		out.close();

	}
	
}
