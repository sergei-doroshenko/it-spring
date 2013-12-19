package org.training.issuetracker.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SampleServlet implements Servlet {

	private ServletConfig servletConfig;

	@Override
	public void init(ServletConfig config) throws ServletException {
		servletConfig = config;
	}

	@Override
	public void destroy() {
	}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public String getServletInfo() {
		return "Sample Servlet interface implementation";
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		int i = 0;
		i++;
		RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/Main.do");
		rd.forward(request, response);
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head><body>");
		out.println("<b>Yesss! This servlet is working!!!</b>");
		out.close();
		
		
		
		
	}
}
