package main.loginView;

import java.io.IOException;
import javafx.fxml.FXML;
import java.sql.*;  

public class SignupController {

	String firstName;
	String lastName;
    String address;
    String zip;
    String state;
    String username;
    String password;
    String email;
	String ssn;
	String securityQue;
	String securityAns;
	
	
	@FXML
	private void goSignup() throws IOException {
		

		
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
