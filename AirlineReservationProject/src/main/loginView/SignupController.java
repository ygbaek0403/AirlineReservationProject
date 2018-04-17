package main.loginView;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;  

public class SignupController {

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

		String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
		String id = "root";
		String pw = "iin";
		
		Connection conn = null;

		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();
			String query = "select username, ssn from customers";
			ResultSet rs = stmt.executeQuery(query);
			
			if(isDuplicate(rs, username, ssn) == false) {
				
				if (username.isEmpty() || password.isEmpty() || ssn.isEmpty() || securityQue.isEmpty() || securityAns.isEmpty()) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
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
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("You are succefully registered");
					alert.showAndWait();
				}
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Check your fields.");
			alert.showAndWait();
			
		} finally {
			
			conn.close();
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

			System.out.println(e.getMessage());
		
		} 
		
		return false;
	}
	
	/*
	public boolean isSsn(String ssnNum) {
		int len = ssnNum.length();
		
		if(len != 9) {
			errdef = "Number of digits not matching for SSN";
			return false;
		}
		
		if (ssnNum.startsWith("000") || ssnNum.startsWith("666") ||
				ssnNum.startsWith("9") || ssnNum.endsWith("0000") ||
				ssnNum.startsWith("00",3)) {
			errdef = "Invalid digits for SSN";
			return false;
		}
		
		try {
			for (int i=0; i < strlen; i++) 
				 Integer.parseInt(String.valueOf(ssnnumber.charAt(i)));
		} catch (Exception e) {
			errdef = "Non Digit Values for SSN";
			return false;
		}
		errdef = "The SSN is valid";
		return true;
	}
	
	public boolean isEamil(String[] emailChar) {
		final String EMAIL_PATTERN =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			 
		  Matcher matcher = pattern.matcher(emailChar);
		  if (matcher.matches() == false) {
			  errdef = "Unable to read email";
			  return false;
		  }
			  
		errdef = "The email is valid";
		return true;
		
	}
	*/


}
