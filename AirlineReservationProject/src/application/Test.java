package application;

import java.sql.*;                                         

//test
public class Test {

	public static void main(String[] args) {
	
		String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
		String id = "root";
		String pw = "iin";
		
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
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
