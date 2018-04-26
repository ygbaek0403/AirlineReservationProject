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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import main.loginView.LoginController;

public class MyTripsController extends MainViewController implements Initializable {

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
	private TableColumn<Ticket, String> priceColumn;

	private PreparedStatement pstmt;

	Alert alert = new Alert(AlertType.INFORMATION);
	
	private String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
	private String id = "root";
	private String pw = "iin";
	

	//call method to display main screen
	@FXML
	private void goBack() throws SQLException {
		
		Connection conn = null;

		
		try {
			
			int idCustomer = LoginController.getIdCustomer();
			conn = DriverManager.getConnection(url, id, pw);
			String query = "SELECT isAdmin FROM customers WHERE idcustomer = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "" + idCustomer);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			//if isadmin is 0, display admin view
			if (rs.getInt("isAdmin") == 0) {
			
				Main.showAdminView();
				
			//if isadmin is not 0, display user view
			} else {
				
				Main.showUserView();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	//get ticket from table
    public ObservableList<Ticket> getFlights(String query) throws SQLException {
		
    	
		Connection conn = null;
		
    	ObservableList<Ticket> tickets = FXCollections.observableArrayList();
    	
    	try {
			
			conn = DriverManager.getConnection(url, id, pw);
				
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
				
			while (rs.next()) {
	            
				tickets.add(new Ticket(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toString(), rs.getString(8), rs.getDate(9).toString(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12)), rs.getString(13), rs.getString(14)));
			}

	        } catch (Exception e) {
	        	
	        	e.printStackTrace();
	        	
	        } finally {
	        	
	        	conn.close();
	        }
			
			return tickets;

    }
	
    //allows to delete a flight
	@FXML
	private void goDelete() throws SQLException {
		
		Connection conn = null;
		
		try {

			conn = DriverManager.getConnection(url, id, pw);

			int idTicket = flightTable.getSelectionModel().getSelectedItem().getIdTicket();
			String queryDelete = "DELETE FROM `dbo_airline`.`tickets` WHERE `idticket`= ?";
			pstmt = conn.prepareStatement(queryDelete);
			pstmt.setString(1, "" + idTicket);
			
			pstmt.executeUpdate();			
			
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The flight is succefully deleted");
			alert.showAndWait();
			
			addCapacity();
			initialize(null, null);
			
		} catch (Exception e) {

			e.printStackTrace();
			
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Select a flight");
			alert.showAndWait();

		} finally {
	
			conn.close();
		}
	}

	
	//if delete a flight, it add a capacity to the flight
	private void addCapacity() {
		
	Connection conn = null;
		
		try {

			conn = DriverManager.getConnection(url, id, pw);
			
			String idFlight = flightTable.getSelectionModel().getSelectedItem().getTicket_flight();
			String query = "SELECT capacity FROM `dbo_airline`.`flights` WHERE idFlight = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, idFlight);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			int capacity = rs.getInt("capacity");
			
			capacity = capacity + 1;
			
			String queryUpdate = "UPDATE `dbo_airline`.`flights` SET `capacity`= ? WHERE `idflight`= ?";
			pstmt = conn.prepareStatement(queryUpdate);
			pstmt.setString(1, "" + capacity);
			pstmt.setString(2, "" + idFlight);
			pstmt.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();

		} 
		
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
