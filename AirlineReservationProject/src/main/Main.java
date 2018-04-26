package main;
	
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

	// create splash screen obj
	SplashScreenController spashScreen = new SplashScreenController();


    @Override
    public void start(Stage primaryStage) {
    	
    	Main.primaryStage = primaryStage;
    	Main.primaryStage.setTitle("Airline Reservation Application");
    	
    	//call splash screen method
    	spashScreen.showWindow();

    	//it delays 3 seconds for splash screen
    	PauseTransition splashScreenDelay = new PauseTransition(Duration.seconds(3));
    	splashScreenDelay.setOnFinished(f -> {
    		primaryStage.show();
    		spashScreen.hideWindow();
    	});
    	
    	splashScreenDelay.playFromStart();
    	
    	
    	//display log view
    	try {
			showLoginView();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
    	
    }


    //method to display login view
    public static void showLoginView() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("loginView/LoginView.fxml"));
    	mainLayout = loader.load();
    	Scene scene = new Scene(mainLayout);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Airline reservation system");
    	primaryStage.centerOnScreen();
    	
    }
    
    
    //method to display admin view
    public static void showAdminView() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("mainView/AdminView.fxml"));
		mainLayout = loader.load();
    	Scene scene = new Scene(mainLayout);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Airline reservation system");
    	primaryStage.centerOnScreen();
    	
    }
    
    
    //method to display user view
    public static void showUserView() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("mainView/UserView.fxml"));
		mainLayout = loader.load();
    	Scene scene = new Scene(mainLayout);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Airline reservation system");
    	primaryStage.centerOnScreen();
    	
    }
    
    
    //method to display my trip 
    public static void showMyTrip() throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("mainView/MyTrips.fxml"));
    	mainLayout = loader.load();
    	Scene scene = new Scene(mainLayout);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("My trip");
    	primaryStage.centerOnScreen();
    	
    }
   
    

    
	public static void main(String[] args) {
		
		launch(args);
	}

}
