package org.training.issuetracker.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.utils.ConnectionManager;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test.do")
public class TestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBCreator creator = new DBCreator();
		
		Connection connection = creator.getConnection();
		
		
//		EmbeddedDataSource ds = new EmbeddedDataSource(); 
//		ds.setDatabaseName(Constants.getRealPath() + "WEB-INF\\classes\\db\\chatterDB");
//		ds.setUser("admin");  
//		ds.setPassword("111");

		PrintWriter out = response.getWriter();
		out.println("<html>");
        out.println("<head>");
        out.println("<title>Sample Servlet interface implementation</title>");
        out.println("</head>");
        out.println("<body><b>Hello Buddy!</b>");
        
		try {
//			connection = ds.getConnection();
			Statement st = connection.createStatement();
			st.execute("create table users (name varchar(40), password varchar(40))");
			
			st.execute("insert into users values ('Ivan', 'Ivan')");
			ResultSet rs = st.executeQuery("select * from users");

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
	
	private class DBCreator {
		/* the default framework is embedded*/
		public final String resourceRealPath = Constants.getRealPath() + "WEB-INF\\classes\\db\\";
		private final Logger logger = Logger.getLogger("org.training.chatterapl.controllers");
		private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
		private static final String DB_URI = "jdbc:derby:";
		//private static final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
		//private static final String DB_URI = "jdbc:derby://localhost:1527/";
		private final String DB_NAME = resourceRealPath + "chatterDB";
		private final String DB_PROPERTIES = Constants.getRealPath() + "WEB-INF\\classes\\db\\derby.properties";
		//private static final String DB_NAME = "D://Java_Training_Workspace//DerbyProject//derbyDB";
		private Connection connection;

		public DBCreator() { }

		public Connection getConnection() {
			loadDriver();
			Properties props = new Properties();

			try {
				props.load(new FileInputStream(DB_PROPERTIES));
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
//				connection = DriverManager.getConnection(DB_URI + DB_NAME  + ";create=true", props);
				connection = DriverManager.getConnection(DB_URI + DB_NAME);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}


		private void loadDriver() {
	        /*
	         *  In an embedded environment, this will also start up the Derby
	         *  engine (though not any databases), since it is not already
	         *  running. In a client environment, the Derby engine is being run
	         *  by the network server framework.
	         *
	         *  In an embedded environment, any static Derby system properties
	         *  must be set before loading the driver to take effect.
	         */
	        try {
	            Class.forName(DB_DRIVER).newInstance();
	        } catch (ClassNotFoundException cnfe) {
	        	cnfe.printStackTrace(System.err);
	            logger.error(cnfe.getMessage());
	        } catch (InstantiationException ie) {
	            ie.printStackTrace(System.err);
	        } catch (IllegalAccessException iae) {
	            iae.printStackTrace(System.err);
	        }
	    }
	}
}