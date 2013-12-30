package org.training.issuetracker.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.xml.ConstantsXML;
import org.training.issuetracker.utils.DataStorage;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		ConstantsXML.RESOURCE_REAL_PATH = config.getServletContext().getRealPath(Constants.ROOT_PATH);
	}

	@Override
	void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head><body>");
		
		DataStorage data = DataStorage.getInstance();
		
		data.printDataMap(out, data.getRolesMap());
		data.printDataMap(out, data.getTypesMap());
		data.printDataMap(out, data.getPrioritiesMap());
		out.println("</body></html>");
		
		//out.println(realPath);
		//out.println(ConstantsXML.RESOURCE_REAL_PATH);
		out.close();
	}
	
}
