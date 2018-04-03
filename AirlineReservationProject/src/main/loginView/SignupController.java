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
<<<<<<< HEAD
				
=======
		
		
>>>>>>> refs/remotes/origin/master
		String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
		String id = "root";
		String pw = "iin";
		
		Connection conn = null;

<<<<<<< HEAD
=======
		

>>>>>>> refs/remotes/origin/master
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO `dbo_airline`.`customers` (`isadmin`, `firstname`, `lastname`, `addr`, `zip`, `state`, `id`, `pw`, `email`, `ssn`, `securityque`, `securityans`) "
<<<<<<< HEAD
					+ "VALUES ('" + isAdmin + "', '" + firstName + "', '" + lastName + "', '" + address + "', '" + zip + "', '" + state + "', '" + username + "', '" + password + "', '" + email + "', '" + ssn + "', '" +  securityQue + "', '" + securityAns + "')";
=======
					+ "VALUES (" + isAdmin + ", '" + firstName + "', '" + lastName + "', '" + address + "', '" + zip + "', '" + state + "', '" + username + "', '" + password + "', '" + email + "', '" + ssn + "', '" +  securityQue + "', '" + securityAns + "')";
>>>>>>> refs/remotes/origin/master
			
			stmt.executeUpdate(query);
			
			conn.close();
			
<<<<<<< HEAD
			} catch (Exception e) {
=======
		} catch (Exception e) {
>>>>>>> refs/remotes/origin/master
			
			e.printStackTrace();
			
		} 
		
		
	}
}
