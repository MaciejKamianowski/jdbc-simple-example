package jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDaoDemo {

	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		Student s1 = new Student();
		s1.id = 6;
		s1.name = "Paul";
		dao.addStudent(s1);
		dao.close();

	}

}

class StudentDao {

	private final static String URL = "jdbc:postgresql://localhost:5432/example";
	private final static String USER_NAME = "postgres";
	private final static String PASSWORD = "password";

	private Connection connection;
	private PreparedStatement preparedStatement;

	private final static String GET_NAME_BY_ID_QUERY_STRING = "select s.student_name from student s where s.student_id = ?";

	private final static String INSERT_NEW_STUDENT_QUERY_STRING = "insert into student values(?, ?)";

	public StudentDao() {
		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Student getStudent(int id) {
		Student s = new Student();
		s.id = id;
		try {
			preparedStatement = connection.prepareStatement(GET_NAME_BY_ID_QUERY_STRING);
			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				s.name = resultSet.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s;
	}
	
	public void addStudent(Student s) {
		try {
			preparedStatement = connection.prepareStatement(INSERT_NEW_STUDENT_QUERY_STRING);
			preparedStatement.setLong(1, s.id);
			preparedStatement.setString(2, s.name);

			int rowsAffected = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	public void close() {
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

class Student {
	int id;
	String name;
}