package pkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OpeationsCRUD {
	
	Connect connect  = new Connect();
	Connection con;
	public void connection() {
		con = connect.connecting();
	}
	
	public void insertData(String uid, String name, String password, String mobile, String email, String aadharno, String licenseno) {
		PreparedStatement statement;
		try {
			connection();
			statement = con.prepareStatement("insert into classTable values(?,?)");
			statement.setString(1, uid);
			statement.setString(2, name);
			statement.executeUpdate();
			System.out.println("Values entered");
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteData(String uid) {
		PreparedStatement statement;
		try {
			connection();
			statement=con.prepareStatement("delete from classTable where uid=?");
			statement.setString(1, uid);
			statement.executeUpdate();
			System.out.println("Row Deleted");
			closeConnection();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateData(String uid, String name) {
		PreparedStatement statement;
		try {
			connection();
			statement=con.prepareStatement("update classTable set name=? where uid=?");
			statement.setString(1, name);
			statement.setString(2, uid);
			statement.executeUpdate();
			System.out.println("Row Updated");
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getData() {
		Statement statement;
		try {
			connection();
			statement=con.createStatement();
			ResultSet rs = statement.executeQuery("select * from classTable");
			while(rs.next()) {
				System.out.println("UID: "+rs.getString(1)+"    Name: "+rs.getString(2));
			}
			closeConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			con.close();
			System.out.println("Connection Closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
