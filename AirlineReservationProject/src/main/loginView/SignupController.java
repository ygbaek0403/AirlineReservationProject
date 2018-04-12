package main.loginView;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;  

public class SignupController {

	@FXML
	private TextField firstNameTF;
	@FXML
	private TextField lastNameTF;
	@FXML
	private TextField addressTF;
	@FXML
	private TextField zipTF;
	@FXML
	private TextField stateTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField ssnTF;
	@FXML
	private TextField securityQueTF;
	@FXML
	private TextField securityAnsTF;
	

	
	
	@FXML
	private void goSubmit() throws IOException {
		
		String isAdmin = "1";
		String firstName = firstNameTF.getText();
		String lastName = lastNameTF.getText();
		String address = addressTF.getText();
		String zip = zipTF.getText();
		String state = stateTF.getText();
		String username = usernameTF.getText();
		String password = passwordPF.getText();
		String email = emailTF.getText();
		String ssn = ssnTF.getText();
		String securityQue = securityQueTF.getText();
		String securityAns = securityAnsTF.getText();

		String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
		String id = "root";
		String pw = "iin";
		
		Connection conn = null;

		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			String query = "select * from customers";
			ResultSet rs = stmt.executeQuery(query);
			
			if(isDuplicate(rs, username, ssn) == false) {
				
				if (username.isEmpty() || password.isEmpty() || ssn.isEmpty() || securityQue.isEmpty() || securityAns.isEmpty()) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Check your fields.");
					alert.showAndWait();
					
				} else {
					
					String queryInsert = "INSERT INTO `dbo_airline`.`customers` (`isadmin`, `firstname`, `lastname`, `address`, `zip`, `state`, `username`, `password`, `email`, `ssn`, `securityque`, `securityans`) "
							+ "VALUES ('" + isAdmin + "', '" + firstName + "', '" + lastName + "', '" + address + "', '" + zip + "', '" + state + "', '" + username + "', '" + password + "', '" + email + "', '" + ssn + "', '" +  securityQue + "', '" + securityAns + "')";
					
					stmt.executeUpdate(queryInsert);
					conn.close();
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("You are succefully registered");
					alert.showAndWait();
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Check your fields.");
			alert.showAndWait();
		} 
		
		
	}
	
	public boolean isDuplicate(ResultSet rs, String username, String ssn) {
		
		try {
			
			while (rs.next()) {
				
				if(username.equals(rs.getString("username"))) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your username is already exist");
					alert.showAndWait();
					return true;
					
				} else if(ssn.equals("\\d{3}-?\\d{2}-?\\d{4}")){
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your ssn is not correct");
					alert.showAndWait();
					return true;
					
				}else if (ssn.equals(rs.getString("ssn"))) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your ssn is already exist");
					alert.showAndWait();
					return true;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
}
