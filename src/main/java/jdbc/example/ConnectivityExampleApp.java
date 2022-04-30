package jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
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

	private static void  connectWithDB() throws ClassNotFoundException, SQLException{
//		Class.forName("com.postgresql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		Statement statement = connection.createStatement();
		
		String query = "select student_name from student where student_id = 1";
		
		
		ResultSet executedQueryResultSet = statement.executeQuery(query);
		executedQueryResultSet.next();
		System.out.println(executedQueryResultSet.getString("student_name"));
		statement.close();
		connection.close();
		
	}
}
