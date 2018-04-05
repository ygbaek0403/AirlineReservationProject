package main;

import java.sql.*;                                         

//test
public class Test {

	public static void main(String[] args) {
	
		String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
		String id = "root";
		String pw = "iin";
		
		Connection conn = null;
		
		try {
			String username = "a";
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			String query = "select * from customers where username = '" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			rs.next();
			System.out.println(rs.getString("firstname") + rs.getString("lastname"));
			
			while (rs.next()) {
			
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} 
		
		
	}
}
