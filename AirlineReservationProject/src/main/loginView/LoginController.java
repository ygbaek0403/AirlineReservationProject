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
    
    
	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	
	@FXML
	private void goLogin() throws IOException, SQLException {
		
		String username = usernameTF.getText();
		String password = passwordPF.getText();

		String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
		String id = "root";
		String pw = "iin";
		
		Connection conn = null;

		
		
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from customers where username = '" + username + "'");
			
			rs.next();
			
			if (username.equals(rs.getString("username")) && password.equals(rs.getString("password")) && rs.getInt("isAdmin") == 0) {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("mainView/AdminView.fxml"));
				mainLayout = loader.load();
				
				Stage addDialogStage = new Stage();
				addDialogStage.setTitle("Admin View");
				addDialogStage.initModality(Modality.WINDOW_MODAL);
				addDialogStage.initOwner(primaryStage);
				Scene scene = new Scene(mainLayout);
				addDialogStage.setScene(scene);
				addDialogStage.showAndWait();
				
			} else if (username.equals(rs.getString("username")) && password.equals(rs.getString("password")) && rs.getInt("isAdmin") == 1) {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("mainView/UserView.fxml"));
				mainLayout = loader.load();
				
				Stage addDialogStage = new Stage();
				addDialogStage.setTitle("User View");
				addDialogStage.initModality(Modality.WINDOW_MODAL);
				addDialogStage.initOwner(primaryStage);
				Scene scene = new Scene(mainLayout);
				addDialogStage.setScene(scene);
				addDialogStage.showAndWait();
				
			} else {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Incorrect username or password");
				alert.showAndWait();

			}

			
		} catch (Exception e) {

			System.out.println(e.getMessage());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Incorrect username or password");
			alert.showAndWait();

		} finally {
			
			conn.close();
		}
	}
	
	
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
