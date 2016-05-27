package stickler;

//imports
import java.sql.*;

/**
 * Prepared Statements
 * 
 * @author Matthias Stickler
 * @version 1.2
 */
public class PreparedStatements {
	// Prepared Statement
	private static PreparedStatement ps;

	/**
	 * CRUD Create Prepared Statement for an Insert operation
	 * 
	 * @param connection
	 *            to the Database
	 */
	public static void insert(DBConnect connection, int number, String value, int weight) {
		ps = connection.preparedStatement("INSERT INTO produkt Values(?,?,?);");
		try {
			ps.setInt(1, number);
			ps.setString(2, value);
			ps.setInt(3, weight);
			ps.execute();
		} catch (SQLException e) {
			System.err.println("Update failed!");
			e.printStackTrace(System.err);
		}

	}

	/**
	 * CRUD Read Prepared Statement for a Select operation
	 * 
	 * @param connection
	 * @param weight
	 */
	public static void select(DBConnect connection, int weight) {
		ps = connection.preparedStatement("SELECT * FROM produkt WHERE gewicht < ?;");
		try {
			ps.setInt(3, weight);
			ps.execute();
		} catch (SQLException e) {
			System.err.println("Update failed!");
			e.printStackTrace(System.err);
		}
	}

	/**
	 * CRUD Update Prepared Statement for an Update operation
	 * 
	 * @param connection
	 * @param value
	 * @param number
	 */
	public static void update(DBConnect connection, int number, String value) {
		ps = connection.preparedStatement("UPDATE produkt SET bezeichnung = ? WHERE nummer = ?;");
		try {
			ps.setInt(1, number);
			ps.setString(2, value);
			ps.execute();
		} catch (SQLException e) {
			System.err.println("Update failed!");
			e.printStackTrace(System.err);
		}

	}

	/**
	 * CRUD Delete Prepared Statement for a Delete operation
	 * 
	 * @param connection
	 * @param number
	 */
	public static void delete(DBConnect connection, int number) {
		ps = connection.preparedStatement("DELETE FROM produkt WHERE nummer = ?;");
		try {
			ps.setInt(1, number);
			ps.execute();
		} catch (SQLException e) {
			System.err.println("Update failed!");
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Testing all CRUD Methods with a Connection from DBConnect class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DBConnect con = new DBConnect();
		con.connect();

		System.out.println("Inserting into produkt...");
		insert(con, 150, "TestSchokolade", 455);
		insert(con, 151, "tobeDeleted", 777);

		System.out.println("Selecting produkt...");
		select(con, 455);

		System.out.println("Updating produkt...");
		update(con, 150, "UpdatedTestSchokolade");

		System.out.println("Deleting produkt...");
		delete(con, 151);

		con.closeConnection();
	}
}