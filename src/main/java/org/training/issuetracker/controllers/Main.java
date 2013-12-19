package org.training.issuetracker.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.factories.DAOFactory;
import org.training.issuetracker.model.beans.Role;
import org.training.issuetracker.model.ifaces.IRoleDAO;
import org.training.issuetracker.model.impl.RoleImpXML;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//String schemaPath = request.getServletContext().getRealPath("/WEB-INF/classes/xml/role.xsd");
		//String xmlPath = request.getServletContext().getRealPath("/WEB-INF/classes/xml/role.xml");
		
		RoleImpXML.resourceRealPath = request.getServletContext().getRealPath(Constants.ROOT_PATH);
		
		//RoleImpXML impl = new RoleImpXML(schemaPath, xmlPath);
		
		IRoleDAO roleDAO = DAOFactory.getDAO(IRoleDAO.class);
		List<Role> roles = null;
		
		try {
			roles = roleDAO.getRoles();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		/*
		try {
			roles = impl.getRoles();
		} catch (DaoException e) {
			e.printStackTrace();
		}	
		*/
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head><body>");
		out.println("<b>Yesss! This servlet is working!!!</b>");
		//out.println(realPath);
		
		if (roles != null) {
			for (Role role : roles) {
				String str = role.getId() + "---" + role.getName() + "; ";
				out.println(str);
			}
		} else {
			out.println("<b>Error</b>");
		}
		
		out.println("<b>This is second servlet</b>");
		out.println("</body></html>");
		out.close();
	}

}
