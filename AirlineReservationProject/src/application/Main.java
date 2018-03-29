package application;
	
import java.io.IOException;
import application.SplashScreenController;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Main extends Application {
	
	SplashScreenController spashScreen = new SplashScreenController();


	private static Stage primaryStage;
	private static BorderPane mainLayout;

	
	 
	private static void showMainView() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("MainView.fxml"));
    	mainLayout = loader.load();
    	Scene scene = new Scene(mainLayout);
    	primaryStage.setScene(scene);

		primaryStage.show();

    		

    }
	 
	@Override
	public void start(Stage primaryStage) {
		try {
			
		  		    	
			
			//Show the SplashScreen
		   	spashScreen.showWindow();

			//I am using the code below so the Primary Stage of the application 
			//doesn't appear for 2 seconds , so the splash screen is displayed
		   	PauseTransition splashScreenDelay = new PauseTransition(Duration.seconds(3));
		   	splashScreenDelay.setOnFinished(f -> {
		   		primaryStage.show();
		   		spashScreen.hideWindow();
		   	});
		   	
		   	splashScreenDelay.playFromStart();
		   	
			Main.primaryStage = primaryStage;
		   	Main.primaryStage.setTitle("Airline Reservation Application");
		   	showMainView();

				} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
