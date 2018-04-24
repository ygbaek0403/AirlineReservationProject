package main;
	
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {


	private static Stage primaryStage;
	private static BorderPane mainLayout;


	SplashScreenController spashScreen = new SplashScreenController();


    @Override
    public void start(Stage primaryStage) {
    	Main.primaryStage = primaryStage;
    	Main.primaryStage.setTitle("Airline Reservation Application");
    	
	
    	spashScreen.showWindow();

    	PauseTransition splashScreenDelay = new PauseTransition(Duration.seconds(1));
    	splashScreenDelay.setOnFinished(f -> {
    		primaryStage.show();
    		spashScreen.hideWindow();
    	});
    	
    	splashScreenDelay.playFromStart();
    	
    	try {
			showLoginView();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
    	
    }

    
    public static void showLoginView() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("loginView/LoginView.fxml"));
    	mainLayout = loader.load();
    	Scene scene = new Scene(mainLayout);
    	primaryStage.setScene(scene);
    	primaryStage.centerOnScreen();
    	
    }
    
    public static void showAdminView() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("mainView/AdminView.fxml"));
		mainLayout = loader.load();
    	Scene scene = new Scene(mainLayout);
    	primaryStage.setScene(scene);
    	primaryStage.centerOnScreen();
    	
    }
    
    public static void showUserView() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("mainView/UserView.fxml"));
		mainLayout = loader.load();
    	Scene scene = new Scene(mainLayout);
    	primaryStage.setScene(scene);
    	primaryStage.centerOnScreen();
    	
    }
    
    public static void showMyTrip() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("mainView/MyTrips.fxml"));
    	mainLayout = loader.load();
    	Scene scene = new Scene(mainLayout);
    	primaryStage.setScene(scene);
    	primaryStage.centerOnScreen();
    	
    }
   
    

    
	public static void main(String[] args) {
		
		launch(args);
	}

}
