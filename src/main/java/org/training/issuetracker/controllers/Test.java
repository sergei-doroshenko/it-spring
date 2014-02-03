package org.training.issuetracker.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.utils.ConnectionManager;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test.do")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ConnectionManager manager = new ConnectionManager();
		Connection connection = null;
		//connection = manager.getConnection();
		
		EmbeddedDataSource ds = new EmbeddedDataSource(); 
		ds.setDatabaseName(Constants.getRealPath() + "WEB-INF\\classes\\db\\issuetrackerDB");
		ds.setUser("admin");  
		ds.setPassword("111");

		PrintWriter out = response.getWriter();
		out.println("<html>");
        out.println("<head>");
        out.println("<title>Sample Servlet interface implementation</title>");
        out.println("</head>");
        out.println("<body><b>Hello Buddy!</b>");
        
		try {
			connection = ds.getConnection();
			Statement st = connection.createStatement();
			//st.execute("create table users (name varchar(40), password varchar(40))");
			//String name = "Ivan";
			//String password = "111";
			//st.execute("insert into users values ('Ivan', 'Ivan')");
			ResultSet rs = st.executeQuery("select * from roles");

			while (rs.next()) {
				String s = rs.getString(2);// + "-" + rs.getString(2);
				out.println("<span>"+ s +"</span>");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		out.println("</body>");
        out.println("</html>");
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
