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

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.xml.ConstantsXML;
import org.training.issuetracker.data.xml.DataStorage;
import org.training.issuetracker.domain.Issue;
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
		
		DataStorage data = DataStorage.getInstance();
		JsonObject json = JSONCreator.createBulkIssueJson(data.getIssuesMap());
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();

		
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<title>Sample Servlet interface implementation</title>");
//		out.println("</head><body>");
//		out.println(json.toString() + "<br>");
//		data.printDataMap(out, data.getRolesMap());
//		data.printDataMap(out, data.getTypesMap());
//		data.printDataMap(out, data.getPrioritiesMap());
//		data.printDataMap(out, data.getResolutionsMap());
//		data.printDataMap(out, data.getStatusesMap());
//		data.printDataMap(out, data.getUsersMap());
//		data.printDataMap(out, data.getProjectsMap());
//		data.printDataMap(out, data.getIssuesMap());
//		out.println("</body></html>");
		
		out.close();
//		out.println(realPath);
//		out.println(ConstantsXML.RESOURCE_REAL_PATH);
		
	}
	
}
