package org.training.issuetracker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionManager {
	/* the default framework is embedded*/
	private Logger log;
	private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_URI = "jdbc:derby:";
	private static final String DB_NAME = "issuetrackerDB";
	//private static final String DB_NAME = "D://Java_Training_Workspace//DerbyProject//derbyDB";
	private Connection connection;

	public ConnectionManager() {
		super();
	}

	public Connection getConnection() {
		loadDriver();
		Properties props = new Properties();
		try {
			connection = DriverManager.getConnection(DB_URI + DB_NAME  + ";create=true", props);
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
            log.error(cnfe.getMessage());
        } catch (InstantiationException ie) {
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            iae.printStackTrace(System.err);
        }
    }

	public void closeResultSets(ResultSet... args){
		for(ResultSet rs : args){
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Resource closing problem: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	public void closeStatemnts(Statement... args){
		for(Statement st : args){
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					System.out.println("Resource closing problem: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	public void closePrepStatemnts(PreparedStatement... args){
		for(PreparedStatement ps : args){
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Resource closing problem: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	public void closeConnection(Connection cn){
		try {
			if(cn != null){
				cn.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
            System.err.println("Can't close connection." + e);
		}
	}

	public void closeAll(Connection connection, PreparedStatement prepStatement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (prepStatement != null) {
				prepStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
            System.err.println("Can't close connection." + e);
		}
	}

}
