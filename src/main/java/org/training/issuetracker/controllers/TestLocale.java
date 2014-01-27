package org.training.issuetracker.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test.loc")
public class TestLocale extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Locale locale = new Locale("ru","RU");
		response.setLocale(locale);

		String path = Constants.getRealPath() + "WEB-INF\\classes\\i18n\\";
		File file = new File(path);
		URL[] urls = {file.toURI().toURL()};
		ClassLoader loader = new URLClassLoader(urls);
		//ResourceBundle bundle = ResourceBundle.getBundle("utf-test", locale, loader);
		ResourceBundle bundle = PropertyResourceBundle.getBundle("utf-test", locale, loader);
		
		//ResourceBundle bundle = ResourceBundle.getBundle("test", locale);
		String one = bundle.getString("one");
		String res = new String (one.getBytes("ISO-8859-1"), "UTF-8");
		request.getSession().setAttribute("test", one);
//		out.println("<html>");
//        out.println("<head>");
//        out.println("<title>Sample Servlet interface implementation</title>");
//        out.println("</head>");
//        out.println("<body><b>i18n test</b>");
//
//        System.out.println(one);
//
//        out.println("<p>" + one + "</p>");
//        out.println("<p>" + res + "</p>");
//
//		out.println("</body>");
//        out.println("</html>");
//        out.close();
		RequestDispatcher disp = request.getRequestDispatcher("search.jsp");
		disp.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
