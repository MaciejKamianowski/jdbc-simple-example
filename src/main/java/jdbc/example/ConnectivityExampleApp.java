package jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectivityExampleApp {

	private final static String URL = "jdbc:postgresql://localhost:5432/example";
	private final static String USER_NAME = "postgres";
	private final static String PASSWORD = "password";

	public static void main(String[] args) {

		try {
			connectWithDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void connectWithDB() throws ClassNotFoundException, SQLException {

		Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		

		int studentId = 5;
		String studentName = "Roel";
		String query = "insert into student values (?, ?)";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, studentId);
		statement.setString(2, studentName);
		
		int rowsAffected = statement.executeUpdate();
		System.out.println(rowsAffected + " row/s affected");

		statement.close();
		connection.close();

	}
}
