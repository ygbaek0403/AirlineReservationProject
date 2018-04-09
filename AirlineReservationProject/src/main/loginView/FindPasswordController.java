package main.loginView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FindPasswordController {

    @FXML
    private TextField usernameTF;
	@FXML
	private TextField securityAnsTF;
	@FXML
	private Label securityQueLB;
	
	
	@FXML
	private void goFind() throws IOException {
		
		String username = usernameTF.getText();
		String securityQue;

		String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
		String id = "root";
		String pw = "iin";
		
		Connection conn = null;

		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			
			String query = "select * from customers where username = '" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			rs.next();
			
			if (username.equals(rs.getString("username"))) {
				
				securityQue = rs.getString("securityQue");
				securityQueLB.setText(securityQue);
				conn.close();
				
			}		
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Check your username.");
			alert.showAndWait();
		} 	
	}
    

	@FXML
	private void goSubmit() throws IOException {
		
		String username = usernameTF.getText();
		String securityAns = securityAnsTF.getText();
	
		String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
		String id = "root";
		String pw = "iin";
		
		Connection conn = null;

		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			
			String query = "select * from customers where username = '" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			rs.next();
			
			if (securityAns.equals(rs.getString("securityAns"))) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Your password: " + rs.getString("securityAns"));
				alert.showAndWait();
				conn.close();
				
			} else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Check your security answer.");
				alert.showAndWait();
				
			}
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Check your security answer.");
			alert.showAndWait();
		} 	 	
	}

}
