package pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connect {
	Connection con;
	public Connection connecting() {
		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "password");
		properties.setProperty("useSSL", "false");
		properties.setProperty("autoReconnect", "true");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classDB",properties);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return con;
	}
}
