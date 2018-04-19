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

import classes.Ticket;
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
import main.loginView.LoginController;

public class MyTripsController implements Initializable {

	@FXML
	private TableView<Ticket> flightTable;
	@FXML
	private TableColumn<Ticket, Integer> idTicketColumn;
	@FXML
	private TableColumn<Ticket, String> flightNumberColumn;
	@FXML
	private TableColumn<Ticket, String> departureCityColumn;
	@FXML
	private TableColumn<Ticket, String> departureStateColumn;
	@FXML
	private TableColumn<Ticket, String> arrivalCityColumn;
	@FXML
	private TableColumn<Ticket, String> arrivalStateColumn;
	@FXML
	private TableColumn<Ticket, LocalDate> departureDateColumn;
	@FXML
	private TableColumn<Ticket, String> departureTimeColumn;
	@FXML
	private TableColumn<Ticket, LocalDate> arrivalDateColumn;
	@FXML
	private TableColumn<Ticket, String> arrivalTimeColumn;
	@FXML
	private TableColumn<Ticket, String> durationColumn;
	@FXML
	private TableColumn<Ticket, String> priceColumn;

	private PreparedStatement pstmt;

	Alert alert = new Alert(AlertType.INFORMATION);

	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	private String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
	private String id = "root";
	private String pw = "iin";
	


    public ObservableList<Ticket> getFlights(String query) throws SQLException {
		
    	
		Connection conn = null;
		
    	ObservableList<Ticket> tickets = FXCollections.observableArrayList();
    	
    	try {
			
			conn = DriverManager.getConnection(url, id, pw);
				
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
				
			while (rs.next()) {
	            
				tickets.add(new Ticket(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toString(), rs.getString(8), rs.getDate(9).toString(), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15)));
			}

	        } catch (Exception e) {
	        	
	        	e.printStackTrace();
	        	
	        } finally {
	        	
	        	conn.close();
	        }
			
			return tickets;

    }
	
	@FXML
	private void goDelete() throws SQLException {
		
		int flightNumber = flightTable.getSelectionModel().getSelectedItem().getIdTicket();
		
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			String queryDelete = "DELETE FROM `dbo_airline`.`tickets` WHERE `idticket`= ?";
			pstmt = conn.prepareStatement(queryDelete);
			pstmt.setString(1, "" + flightNumber);
			
			pstmt.executeUpdate();
			
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The flight is succefully deleted");
			alert.showAndWait();

			initialize(null, null);
			
		} catch (Exception e) {

			System.out.println(e.getMessage());

			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Select a flight");
			alert.showAndWait();

		} finally {
	
			conn.close();
		}
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
		idTicketColumn.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("idTicket"));
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("flightNumber"));
		departureCityColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("departureCity"));
		departureStateColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("departureState"));
		arrivalCityColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("arrivalCity"));
		arrivalStateColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("arrivalState"));
		departureDateColumn.setCellValueFactory(new PropertyValueFactory<Ticket, LocalDate>("departureDate"));
		departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("departureTime"));
		arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<Ticket, LocalDate>("arrivalDate"));
		arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("arrivalTime"));
		durationColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("duration"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("price"));
				
		flightTable.setEditable(true);

		int idCustomer = LoginController.getIdCustomer();
		String query = "select * from tickets where ticket_customer = " + idCustomer;

		try {
			flightTable.setItems(getFlights(query));
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}

}
