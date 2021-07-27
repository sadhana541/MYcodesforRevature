package com.coin.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class PostgresConnection {

	private static Connection connection;
	private static Logger log = Logger.getLogger(PostgresConnection.class);

	private PostgresConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		try
		{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String user = "postgres";
			String password = "Geeta@123";
			connection = DriverManager.getConnection(url, user, password);
		}
		catch(ClassNotFoundException ex)
		{
			log.error("Error during connection with the database : " + ex.getMessage());
			throw new ClassNotFoundException(ex.getMessage());
		}
		catch(SQLException ex)
		{
			log.error("Error during connection with the database : " + ex.getMessage());
			throw new SQLException(ex.getMessage());
		}
		
		return connection;
	}
}
