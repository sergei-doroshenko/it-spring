package org.training.issuetracker.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.training.issuetracker.constants.Constants;

public class ConnectionProvider {
	private static EmbeddedDataSource ds;

	static {
		ds = new EmbeddedDataSource();
		ds.setDatabaseName(Constants.getRealPath() + "WEB-INF\\classes\\db\\issuetrackerDB");
		ds.setUser("admin");
		ds.setPassword("111");
	}

	/**Method for getting connection.
	 * @return Connection.
	 * @throws SQLException.
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void closeConnection(Connection cn){
		try {
			if(cn != null){
				cn.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void closeStatemnts(Statement... args){
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

	public static void closePrepStatemnts(PreparedStatement... args){
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
}
