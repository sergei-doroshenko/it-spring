package org.training.issuetracker.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	private static ConnectionManager manager = new ConnectionManager();
	private static Connection connection = manager.getConnection();
	
	public DBManager() {
		
	}
	
	public static void createUsersTable() {
		try {
			Statement st = connection.createStatement();
			st.execute("create table users (user_name varchar(15) not null primary key, " 
										+ "user_pass varchar(15) not null)");
			
			st.execute("create table users (name varchar(40), password varchar(40))");
			st.execute("insert into users values ('Ivan', 'Ivan')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
