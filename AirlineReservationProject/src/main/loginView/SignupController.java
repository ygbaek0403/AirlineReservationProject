package main.loginView;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;  

public class SignupController extends LoginController {

	private PreparedStatement pstmt;
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
	
	Alert alert = new Alert(AlertType.INFORMATION);

	private String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
	private String id = "root";
	private String pw = "iin";
	
	
	//it allows to sign up as customer account
	@FXML
	private void goSubmit() throws IOException, SQLException {
		
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

		Connection conn = null;

		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();
			String query = "select username, ssn from customers";
			ResultSet rs = stmt.executeQuery(query);
			
			//check if usrname, ssn and email is duplicate
			if(isDuplicate(rs, username, ssn, email) == false) {
				
				if (username.isEmpty() || password.isEmpty() || ssn.isEmpty() || securityQue.isEmpty() || securityAns.isEmpty()) {
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Check your fields.");
					alert.showAndWait();
					
				} else {
					
					String queryInsert = "INSERT INTO `dbo_airline`.`customers` (`isadmin`, `firstname`, `lastname`, `address`, `zip`, `state`, `username`, `password`, `email`, `ssn`, `securityque`, `securityans`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
					pstmt = conn.prepareStatement(queryInsert);
					pstmt.setString(1, isAdmin);
					pstmt.setString(2, firstName);
					pstmt.setString(3, lastName);
					pstmt.setString(4, address);
					pstmt.setString(5, zip);
					pstmt.setString(6, state);
					pstmt.setString(7, username);
					pstmt.setString(8, password);
					pstmt.setString(9, email);
					pstmt.setString(10, ssn);
					pstmt.setString(11, securityQue);
					pstmt.setString(12, securityAns);
					pstmt.executeUpdate();
					conn.close();
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("You are succefully registered");
					alert.showAndWait();
				}
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Check your fields.");
			alert.showAndWait();
			
		} finally {
			
			conn.close();
		}
		
		
	}
	
	public boolean isDuplicate(ResultSet rs, String username, String ssn, String email) {
		
		try {
			
			while (rs.next()) {
				
				//username is duplicate?
				if (username.equals(rs.getString("username"))) {
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your username is already exist");
					alert.showAndWait();
					return true;
				
					
				//ssn is duplicate?
				}  else if (ssn.equals(rs.getString("ssn"))) {
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your ssn is already exist");
					alert.showAndWait();
					return true;
					
				//ssn has correct form?
				} else if (!ssn.matches("^\\d{3}-\\d{2}-\\d{4}$")){
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your ssn form is not correct");
					alert.showAndWait();
					return true;
					
				
				//email has correct form?						
				} else if (!email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your email form is not correct");
					alert.showAndWait();
					return true;
				}
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		
		} 
		
		return false;
	}
}
