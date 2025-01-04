package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	    private static Connection connection = null;

	    public static Connection getConnection() {
	        if (connection != null)
	            return connection;
	        else {
	            try {
	                String driver = "com.mysql.cj.jdbc.Driver";
	                String url = "jdbc:mysql://localhost:3306/productmanage";
	                String user = "root";
	                String password = "chungkhoa123@";
	                Class.forName(driver);
	                connection = DriverManager.getConnection(url, user, password);
	            } catch (ClassNotFoundException | SQLException e) {
	                e.printStackTrace();
	            }
	            return connection;
	        }
	    }
	}