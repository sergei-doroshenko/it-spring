package org.training.issuetracker.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.JsonObject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.xml.ConstantsXML;
import org.training.issuetracker.data.xml.DataStorage;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.utils.JSONCreator;

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
		
		if(null == user) {
			response.setHeader("userrole", "guest");
		} else {
			response.setHeader("userrole", user.getRole().getName());
			response.setHeader("username", user.getFirstName());
		}
		response.setContentType("application/json");
		
		DataStorage data = DataStorage.getInstance();
		JsonObject json = null;
		
		String idStr = request.getParameter("issueId");
		
		if (null == idStr) {
			json = JSONCreator.createBulkIssueJson(user, data.getIssuesMap());
		} else {
			long id = Long.parseLong(idStr);
			json = JSONCreator.createSingleIssueJson(user, id);
			//Gson gson = new Gson();
			//String jsons = gson.toJson(data.getIssue(id));
			
		}
		System.out.println(json);
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/error.html");
//		if (dispatcher != null) dispatcher.forward(request, response);
		
		//response.sendRedirect("http://localhost:8080/issuetracker/");
		return;
		

	}
	
}
