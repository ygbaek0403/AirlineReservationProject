package main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreenController extends StackPane {

    @FXML
    private ImageView imageView;

    //define new stage
    Stage window = new Stage();

  
    //define constructor
    public SplashScreenController() {

	FXMLLoader loader = new FXMLLoader(getClass().getResource("SplashScreen.fxml"));
	loader.setController(this);
	loader.setRoot(this);

	try {
	    loader.load();
	} catch (IOException ex) {
	
	}

	window.setTitle("Splash Screen");
	window.initStyle(StageStyle.TRANSPARENT);
	window.setScene(new Scene(this));

    }

    public void showWindow() {
	window.show();
    }

   
    public void hideWindow() {
	window.hide();
    }


}