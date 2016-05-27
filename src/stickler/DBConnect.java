package stickler;

//imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.postgresql.ds.PGSimpleDataSource;

/**
 * Connecting to an external PostgreSQL Database
 * 
 * @author Matthias Stickler
 * @version 1.2
 */
public class DBConnect {
	// Connection
	private Connection connection;
	// Postgres DataSource
	private PGSimpleDataSource ds;

	/**
	 * Creating the Connection to an external Database
	 */
	public DBConnect() {
		// DB Connection
		ds = new PGSimpleDataSource();
		ds.setServerName("192.168.248.135");
		ds.setDatabaseName("schokodb");
		ds.setUser("schokouser");
		ds.setPassword("root");
		ds.setPortNumber(5432);
	}

	/**
	 * Actual Connection to the Database
	 */
	public void connect() {
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			System.err.println("Connection failed!");
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Closing the existing Connection
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Closing Connection failed!");
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Wrapper method for a Prepared Statement 
	 * If there is an Error it will be caught and Displayed
	 * 
	 * @param statement
	 * @return
	 */
	public PreparedStatement preparedStatement(String statement) {
		try {
			return connection.prepareStatement(statement);

		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
		return null;
	}

}
