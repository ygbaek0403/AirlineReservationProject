package main.loginView;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import java.sql.*;  


public class LoginController {

	
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordPF;
    private static int idCustomer;
    
	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	Alert alert = new Alert(AlertType.INFORMATION);

	private String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
	private String id = "root";
	private String pw = "iin";
	
	
	//it compares id and pw and isadmin to check whether the id is for admin or user
	@FXML
	private void goLogin() throws IOException, SQLException {
		
		String username = usernameTF.getText();
		String password = passwordPF.getText();
		
		Connection conn = null;

		
		
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from customers where username = '" + username + "'");
			
			rs.next();
			
			//if idadmin is 0, display admin view
			if (username.equals(rs.getString("username")) && password.equals(rs.getString("password")) && rs.getInt("isAdmin") == 0) {
		
				idCustomer = Integer.parseInt(rs.getString(1));
				
				Main.showAdminView();
				
			//if idadmin is 1, display user view
			} else if (username.equals(rs.getString("username")) && password.equals(rs.getString("password")) && rs.getInt("isAdmin") == 1) {
				
				idCustomer = Integer.parseInt(rs.getString(1));
				
				Main.showUserView();
				
			} else {
				
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Incorrect username or password");
				alert.showAndWait();

			}

			
		} catch (Exception e) {

			e.printStackTrace();

			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Incorrect username or password");
			alert.showAndWait();

		} finally {
			
			conn.close();
		}
	}
	
	//it returns idcustomer for main view controler
	public static int getIdCustomer() {
		
		return idCustomer;
	}
	
	
	//display sign up screen
	@FXML
	private void goSignup() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("loginView/Signup.fxml"));
		mainLayout = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Sigh up");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(mainLayout);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
	

	//display find password screen
	@FXML
	private void goFindPassword() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("loginView/FindPassword.fxml"));
		mainLayout = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Find Password");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(mainLayout);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
}
