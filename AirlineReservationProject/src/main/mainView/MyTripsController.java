package main.mainView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import classes.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

public class MyTripsController implements Initializable {

	@FXML
	private TableView<Flight> flightTable;
	@FXML
	private TableColumn<Flight, String> flightNumberColumn;
	@FXML
	private TableColumn<Flight, String> departureCityColumn;
	@FXML
	private TableColumn<Flight, String> departureStateColumn;
	@FXML
	private TableColumn<Flight, String> arrivalCityColumn;
	@FXML
	private TableColumn<Flight, String> arrivalStateColumn;
	@FXML
	private TableColumn<Flight, LocalDate> departureDateColumn;
	@FXML
	private TableColumn<Flight, String> departureTimeColumn;
	@FXML
	private TableColumn<Flight, LocalDate> arrivalDateColumn;
	@FXML
	private TableColumn<Flight, String> arrivalTimeColumn;
	@FXML
	private TableColumn<Flight, String> durationColumn;
	@FXML
	private TableColumn<Flight, String> priceColumn;

	
	private PreparedStatement pstmt;
	
	

	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	private String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
	private String id = "root";
	private String pw = "iin";
	
    public void getFlights() throws SQLException {
    	
		String query = "";
		Connection conn = null;
		
    	ObservableList<Flight> flights = FXCollections.observableArrayList();
    	
    	try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			query = "select * from tickets where ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
				
			while (rs.next()) {
	            
				flights.add(new Flight(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toString(), rs.getString(8), rs.getDate(9).toString(), rs.getString(10), rs.getString(11), rs.getString(12)));
			}

	        } catch (Exception e) {
	        	
	        	e.printStackTrace();
	        	
	        } finally {
	        	
	        	conn.close();
	        }
			
    	flightTable.setItems(flights);
	}	

	@FXML
	private void goBack() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("mainView/AdminView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		//set up columns
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightNumber"));
		departureCityColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureCity"));
		departureStateColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureState"));
		arrivalCityColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalCity"));
		arrivalStateColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalState"));
		departureDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate>("departureDate"));
		departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureTime"));
		arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate>("arrivalDate"));
		arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalTime"));
		durationColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("duration"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("price"));
		
	}

}
