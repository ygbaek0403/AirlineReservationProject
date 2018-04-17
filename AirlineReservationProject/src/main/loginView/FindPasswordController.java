package main.loginView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FindPasswordController {

    @FXML
    private TextField usernameTF;
	@FXML
	private TextField securityAnsTF;
	@FXML
	private Label securityQueLB;
	
	Alert alert = new Alert(AlertType.INFORMATION);

	private String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
	private String id = "root";
	private String pw = "iin";
	
	@FXML
	private void goFind() throws IOException, SQLException {
		
		String username = usernameTF.getText();
		String securityQue;

		
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
			}		
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Check your username.");
			alert.showAndWait();
			
		} finally {
			
			conn.close();
		}
	}
    

	@FXML
	private void goSubmit() throws IOException, SQLException {
		
		String username = usernameTF.getText();
		String securityAns = securityAnsTF.getText();
		
		Connection conn = null;

		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			
			String query = "select * from customers where username = '" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			rs.next();
			
			if (securityAns.equals(rs.getString("securityAns"))) {
				
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Your password: " + rs.getString("password"));
				alert.showAndWait();
								
			} else {
				
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Check your security answer.");
				alert.showAndWait();
				
			}
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Check your security answer.");
			alert.showAndWait();
		
		} finally {
			
			conn.close();
		}
	}

}
