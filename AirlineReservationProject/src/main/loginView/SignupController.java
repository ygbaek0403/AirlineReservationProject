package main.loginView;
//
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	private void goSignup() throws IOException {
		
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
			String query = "INSERT INTO `dbo_airline`.`customers` (`isadmin`, `firstname`, `lastname`, `addr`, `zip`, `state`, `id`, `pw`, `email`, `ssn`, `securityque`, `securityans`) "
					+ "VALUES ('" + isAdmin + "', '" + firstName + "', '" + lastName + "', '" + address + "', '" + zip + "', '" + state + "', '" + username + "', '" + password + "', '" + email + "', '" + ssn + "', '" +  securityQue + "', '" + securityAns + "')";
			
			stmt.executeUpdate(query);
			
			conn.close();
			
			} catch (Exception e) {
			
			e.printStackTrace();
			
		} 
		
		
	}
}
