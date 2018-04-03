package main.loginView;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

public class LoginController {

	private Main main;
	private String username;
	private String password;
	
	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	
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
