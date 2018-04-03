package main.loginView;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	private void goLogin() throws IOException {
		
		String username = usernameTF.getText();
		String password = passwordPF.getText();

		String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
		String id = "root";
		String pw = "iin";
		
		Connection conn = null;

		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select pw from customers where id = '" + username + "'");
			
			if (password.equals(rs.getString(0)))
				System.out.print("Wrong password");
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("loginView/Signup.fxml"));
			BorderPane register = loader.load();
			
			Stage addDialogStage = new Stage();
			addDialogStage.setTitle("Sigh up");
			addDialogStage.initModality(Modality.WINDOW_MODAL);
			addDialogStage.initOwner(primaryStage);
			Scene scene = new Scene(register);
			addDialogStage.setScene(scene);
			addDialogStage.showAndWait();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} 	
	}
	
	
	@FXML
	private void goSignup() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("loginView/Signup.fxml"));
		BorderPane register = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Sigh up");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(register);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
	

}
