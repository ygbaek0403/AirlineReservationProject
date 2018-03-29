package application;

import java.sql.*;                                         


public class Test {

	public static void main(String[] args) {
	
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbo_airline", "root", "iin");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from customers");
			
			while (rs.next()) {
				System.out.println(rs.getString("firstname") + rs.getString("lastname"));
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} 
		
		
	}
}
