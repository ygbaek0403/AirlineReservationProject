package main.mainView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import classes.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import main.loginView.LoginController;

public class MainViewController implements Initializable {

	@FXML
	private TableView<Flight> flightTable;
	@FXML
	private TableColumn<Flight, Integer> idFlightColumn;
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
	private TableColumn<Flight, String> priceColumn;
	@FXML
	private TableColumn<Flight, Integer> capacityColumn;
	@FXML
	private TextField flyingFromTF;
	@FXML
	private TextField flyingToTF;
	@FXML
	private DatePicker departureDateDP;
	@FXML
	private DatePicker arrivalDateDP;
	
	private static int idFlight;
	  
	private PreparedStatement pstmt;
	
	Alert alert = new Alert(AlertType.INFORMATION);
	Alert alertConfirmation = new Alert(AlertType.CONFIRMATION);



	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	private String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
	private String id = "root";
	private String pw = "iin";
	
	
	public Flight getFlight() {
		
		return flightTable.getSelectionModel().getSelectedItem();
	}
	
    public ObservableList<Flight> getFlights() throws SQLException {

    	String query = "select * from flights";

		Connection conn = null;
		
    	ObservableList<Flight> flights = FXCollections.observableArrayList();
    	
    	try {
			
			conn = DriverManager.getConnection(url, id, pw);
				
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
				
			while (rs.next()) {
	            
				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
			}

	        } catch (Exception e) {
	        	
	        	e.printStackTrace();
	        	
	        } finally {
	        	
	        	conn.close();
	        }
			
			return flights;

    }
	
	@FXML
	private void goSearch() throws SQLException {
		 
		
		Connection conn = null;
		
    	ObservableList<Flight> flights = FXCollections.observableArrayList();
    	
    	if (flyingFromTF.getText().equals("") && flyingToTF.getText().equals("") && departureDateDP.getValue() == null && arrivalDateDP.getValue() == null) {
    		
    		String query = "select * from flights";
    		
        	try {
    			
    			conn = DriverManager.getConnection(url, id, pw);
    				
    			pstmt = conn.prepareStatement(query);
    			ResultSet rs = pstmt.executeQuery();
    				
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}

        	} catch (Exception e) {
    	        	
        		e.printStackTrace();
    	        	
        	} finally {
    	        	
        		conn.close();
        	}
        		
    		flightTable.setItems(flights);
    		
    	} else if (!(flyingFromTF.getText().equals("")) && flyingToTF.getText().equals("") && departureDateDP.getValue() == null && arrivalDateDP.getValue() == null) {
    		
    		String query = "select * from flights where departurecity like ?";
    	    
    		try {
    			
    			String flyingFrom = flyingFromTF.getText() + "%";
        		
    			conn = DriverManager.getConnection(url, id, pw);
    			
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, flyingFrom);
    			ResultSet rs = pstmt.executeQuery();
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    			
    		} finally {
    			
    			conn.close();
    		}
    		
    		flightTable.setItems(flights);
    		
    	} else if (flyingFromTF.getText().equals("") && !(flyingToTF.getText().equals("")) && departureDateDP.getValue() == null && arrivalDateDP.getValue() == null) {
    		
    		String query = "select * from flights where arrivalcity like ?";
    	    
    		try {
    			
    			String flyingTo = flyingToTF.getText() + "%";
        		
    			conn = DriverManager.getConnection(url, id, pw);
    			
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, flyingTo);
    			ResultSet rs = pstmt.executeQuery();
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    			
    		} finally {
    			
    			conn.close();
    		}
    		
    		flightTable.setItems(flights);
    	}  else if (!(flyingFromTF.getText().equals("")) && !(flyingToTF.getText().equals("")) && departureDateDP.getValue() == null && arrivalDateDP.getValue() == null) {
    		
    		String query = "select * from flights where departurecity like ? and arrivalcity like ?";
    	    
    		try {
    			
    			String flyingFrom = flyingFromTF.getText() + "%";
    			String flyingTo = flyingToTF.getText() + "%";
        		
    			conn = DriverManager.getConnection(url, id, pw);
    			
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, flyingFrom);
    			pstmt.setString(2, flyingTo);
    			ResultSet rs = pstmt.executeQuery();
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    			
    		} finally {
    			
    			conn.close();
    		}
    		
    		flightTable.setItems(flights);
    		
    	} else if (flyingFromTF.getText().equals("") && flyingToTF.getText().equals("") && !(departureDateDP.getValue() == null) && arrivalDateDP.getValue() == null) {
    		
    		String query = "select * from flights where departuredate = ?";
    		
        	try {
    			
        		String departureDate = "" + departureDateDP.getValue();
        		
    			conn = DriverManager.getConnection(url, id, pw);
    				
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, departureDate);
    			ResultSet rs = pstmt.executeQuery();    	
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}

        	} catch (Exception e) {
    	        	
        		e.printStackTrace();
    	        	
        	} finally {
    	        	
        		conn.close();
        	}
        		
    		flightTable.setItems(flights);
    		
    	} else if (flyingFromTF.getText().equals("") && flyingToTF.getText().equals("") && departureDateDP.getValue() == null && !(arrivalDateDP.getValue() == null)) {
    		
    		String query = "select * from flights where arrivaldate = ?";
    	    
    		try {
    			
    			String arrivalDate = "" + arrivalDateDP.getValue();
        		
    			conn = DriverManager.getConnection(url, id, pw);
    			
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, arrivalDate);
    			ResultSet rs = pstmt.executeQuery();
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    			
    		} finally {
    			
    			conn.close();
    		}
    		
    		flightTable.setItems(flights);
    		
    	} else if (flyingFromTF.getText().equals("") && flyingToTF.getText().equals("") && !(departureDateDP.getValue() == null) && !(arrivalDateDP.getValue() == null)) {
    		
    		String query = "select * from flights where departuredate = ? and arrivaldate = ?";
    	    
    		try {
    			
    			String departureDate = "" + departureDateDP.getValue();
    			String arrivalDate = "" + arrivalDateDP.getValue();
        		
    			conn = DriverManager.getConnection(url, id, pw);
    			
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, departureDate);
    			pstmt.setString(2, arrivalDate);
    			ResultSet rs = pstmt.executeQuery();
    			
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    			
    		} finally {
    			
    			conn.close();
    		}
    		
    		flightTable.setItems(flights);
    		
    	} else if (flyingFromTF.getText().equals("") && !(flyingToTF.getText().equals("")) && !(departureDateDP.getValue() == null) && !(arrivalDateDP.getValue() == null)) {
    		
    		String query = "select * from flights where arrivalcity like ? and departuredate = ? and arrivaldate = ?";
    	    
    		try {
    			
    			String flyingTo = flyingToTF.getText() + "%";
        	
    			String departureDate = "" + departureDateDP.getValue();
    			String arrivalDate = "" + arrivalDateDP.getValue();
        		
    			conn = DriverManager.getConnection(url, id, pw);
    			
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, flyingTo);
    			pstmt.setString(2, departureDate);
    			pstmt.setString(3, arrivalDate);
    			ResultSet rs = pstmt.executeQuery();
    			
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    			
    		} finally {
    			
    			conn.close();
    		}
    		
    		flightTable.setItems(flights);
    		
    	} else if (!(flyingFromTF.getText().equals("")) && flyingToTF.getText().equals("") && !(departureDateDP.getValue() == null) && !(arrivalDateDP.getValue() == null)) {
    		
    		String query = "select * from flights where departurecity like ? and departuredate = ? and arrivaldate = ?";
    	    
    		try {
    			
    			String flyingFrom = flyingFromTF.getText() + "%";
    			
    			String departureDate = "" + departureDateDP.getValue();
    			String arrivalDate = "" + arrivalDateDP.getValue();
        		
    			conn = DriverManager.getConnection(url, id, pw);
    			
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, flyingFrom);
    			pstmt.setString(2, departureDate);
    			pstmt.setString(3, arrivalDate);
    			ResultSet rs = pstmt.executeQuery();
    			
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    			
    		} finally {
    			
    			conn.close();
    		}
    		
    		flightTable.setItems(flights);
    		
    	} else if (!(flyingFromTF.getText().equals("")) && !(flyingToTF.getText().equals("")) && departureDateDP.getValue() == null && !(arrivalDateDP.getValue() == null)) {
    		
    		String query = "select * from flights where departurecity like ? and arrivalcity like ? and arrivaldate = ?";
    	    
    		try {
    			
    			String flyingFrom = flyingFromTF.getText() + "%";
    			String flyingTo = flyingToTF.getText() + "%";
        	
    			String arrivalDate = "" + arrivalDateDP.getValue();
        		
    			conn = DriverManager.getConnection(url, id, pw);
    			
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, flyingFrom);
    			pstmt.setString(2, flyingTo);
    			pstmt.setString(3, arrivalDate);
    			ResultSet rs = pstmt.executeQuery();
    			
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    			
    		} finally {
    			
    			conn.close();
    		}
    		
    		flightTable.setItems(flights);
    		
    	} else if (!(flyingFromTF.getText().equals("")) && !(flyingToTF.getText().equals("")) && !(departureDateDP.getValue() == null) && arrivalDateDP.getValue() == null) {
    		
    		String query = "select * from flights where departurecity like ? and arrivalcity like ? and departuredate = ?";
    	    
    		try {
    			
    			String flyingFrom = flyingFromTF.getText() + "%";
    			String flyingTo = flyingToTF.getText() + "%";
        	
    			String departureDate = "" + departureDateDP.getValue();
    			
    			conn = DriverManager.getConnection(url, id, pw);
    			
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, flyingFrom);
    			pstmt.setString(2, flyingTo);
    			pstmt.setString(3, departureDate);
    			ResultSet rs = pstmt.executeQuery();
    			
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    			
    		} finally {
    			
    			conn.close();
    		}
    		
    		flightTable.setItems(flights);
    		
    	} else if (!(flyingFromTF.getText().equals("")) && !(flyingToTF.getText().equals("")) && !(departureDateDP.getValue() == null) && !(arrivalDateDP.getValue() == null)) {
    		
    		String query = "select * from flights where departurecity like ? and arrivalcity like ? and departuredate = ? and arrivaldate = ?";
    	    
    		try {
    			
    			String flyingFrom = flyingFromTF.getText() + "%";
    			String flyingTo = flyingToTF.getText() + "%";
        	
    			String departureDate = "" + departureDateDP.getValue();
    			String arrivalDate = "" + arrivalDateDP.getValue();
        		
    			conn = DriverManager.getConnection(url, id, pw);
    			
    			pstmt = conn.prepareStatement(query);
    			pstmt.setString(1, flyingFrom);
    			pstmt.setString(2, flyingTo);
    			pstmt.setString(3, departureDate);
    			pstmt.setString(4, arrivalDate);
    			ResultSet rs = pstmt.executeQuery();
    			
    			
    			while (rs.next()) {
    	            
    				flights.add(new Flight(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11), Integer.parseInt(rs.getString(12))));
    			}
    			
    		} catch (Exception e) {
    			
    			e.printStackTrace();
    			
    		} finally {
    			
    			conn.close();
    		}
    		
    		flightTable.setItems(flights);
    	}            
		
	}	
	 
	@FXML
	private void addFlight() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("mainView/AddFlight.fxml"));
		mainLayout = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Add a flight");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(mainLayout);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
		initialize(null, null);
	}
	
	@FXML
	private void editFlight() throws IOException {
		
		try {

			int idFlight = flightTable.getSelectionModel().getSelectedItem().getIdFlight();
			String flightNumber = flightTable.getSelectionModel().getSelectedItem().getFlightNumber();
			String departureCity = flightTable.getSelectionModel().getSelectedItem().getDepartureCity();
			String departureState = flightTable.getSelectionModel().getSelectedItem().getDepartureState();
			String arrivalCity = flightTable.getSelectionModel().getSelectedItem().getArrivalCity();
			String arrivalState = flightTable.getSelectionModel().getSelectedItem().getArrivalState();
			LocalDate departureDate = flightTable.getSelectionModel().getSelectedItem().getDepartureDate();
			String departureTime =flightTable.getSelectionModel().getSelectedItem().getDepartureTime();
			LocalDate arrivalDate = flightTable.getSelectionModel().getSelectedItem().getArrivalDate();
			String arrivalTime = flightTable.getSelectionModel().getSelectedItem().getArrivalTime();
			String price = flightTable.getSelectionModel().getSelectedItem().getPrice();
			int capacity = flightTable.getSelectionModel().getSelectedItem().getCapacity();
				
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("mainView/EditFlight.fxml"));
			
			loader.load();
			
			EditFlightController efc = loader.getController();
			efc.setFlight(idFlight, flightNumber, departureCity, departureState, arrivalCity, arrivalState, departureDate, departureTime, arrivalDate, arrivalTime, price, capacity);
		
			Parent p = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.showAndWait();
			
		} catch(IOException e) { 
			
			Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, e);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Select a flight");
			alert.showAndWait();
			
		}
		initialize(null, null);
	}
	
	@FXML
	private void deleteFlight() throws SQLException {
	
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			alertConfirmation.setTitle("Confirmation Dialog");
			alertConfirmation.setHeaderText(null);
			alertConfirmation.setContentText("Are you sure to delete? \nIt will affect to customer's tickes");
			Optional <ButtonType> action = alertConfirmation.showAndWait();
			
			if(action.get() == ButtonType.OK) {

				int idFlight = flightTable.getSelectionModel().getSelectedItem().getIdFlight();
				String queryDelete = "DELETE FROM `dbo_airline`.`flights` WHERE `idflight`= ?";
				pstmt = conn.prepareStatement(queryDelete);
				pstmt.setString(1, "" + idFlight);
				
				pstmt.executeUpdate();
				
				queryDelete = "DELETE FROM `dbo_airline`.`tickets` WHERE `ticket_flight`= ?";
				pstmt = conn.prepareStatement(queryDelete);
				pstmt.setString(1, "" + idFlight);
			
				pstmt.executeUpdate();
				
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("The flight is succefully deleted");
				alert.showAndWait();

			}
		} catch (Exception e) {

			e.printStackTrace();

			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Select a flight");
			alert.showAndWait();

		} finally {
	
			conn.close();
		}
		initialize(null, null);
	}
	
	@FXML
	private void goToTrip() throws IOException {
		
		Main.showMyTrip();
	}
	
	@FXML
	private void goLogout() throws IOException {
       
    	Main.showLoginView();
	}
	

	@FXML
	private void addToTrip() throws SQLException {
		
		Connection conn = null;
		String idCustomer = "" + LoginController.getIdCustomer();
		
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			int idFlight = flightTable.getSelectionModel().getSelectedItem().getIdFlight();
			String flightNumber = flightTable.getSelectionModel().getSelectedItem().getFlightNumber();
			String departureCity = flightTable.getSelectionModel().getSelectedItem().getDepartureCity();
			String departureState = flightTable.getSelectionModel().getSelectedItem().getDepartureState();
			String arrivalCity = flightTable.getSelectionModel().getSelectedItem().getArrivalCity();
			String arrivalState = flightTable.getSelectionModel().getSelectedItem().getArrivalState();
			LocalDate departureDate = flightTable.getSelectionModel().getSelectedItem().getDepartureDate();
			String departureTime =flightTable.getSelectionModel().getSelectedItem().getDepartureTime();
			LocalDate arrivalDate = flightTable.getSelectionModel().getSelectedItem().getArrivalDate();
			String arrivalTime = flightTable.getSelectionModel().getSelectedItem().getArrivalTime();
			String price = flightTable.getSelectionModel().getSelectedItem().getPrice();
			int capacity = flightTable.getSelectionModel().getSelectedItem().getCapacity();
			
			idFlight = flightTable.getSelectionModel().getSelectedItem().getIdFlight();
					
			String query = "select ticket_flight from tickets where ticket_customer = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, idCustomer);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (isFlightDuplicate(rs, idFlight) == false) {
				
				if (capacity == 0) {
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("The flight is already full");
					alert.showAndWait();
				
				} else if (isDateDuplicate()) {
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Be aware that you have another flight in the same date");
					alert.showAndWait();
					
					capacity = capacity - 1;
					
					String queryInsert = "INSERT INTO `dbo_airline`.`tickets` (`flightNumber`, `departureCity`, `departureState`, `arrivalCity`, `arrivalState`, `departureDate`, `departureTime`, `arrivalDate`, `arrivalTime`, `price`, `capacity`, `ticket_customer`, `ticket_flight`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
					pstmt = conn.prepareStatement(queryInsert);
					pstmt.setString(1, flightNumber);
					pstmt.setString(2, departureCity);
					pstmt.setString(3, departureState);
					pstmt.setString(4, arrivalCity);
					pstmt.setString(5, arrivalState);
					pstmt.setDate(6, Date.valueOf(departureDate));
					pstmt.setString(7, departureTime);
					pstmt.setDate(8, Date.valueOf(arrivalDate));
					pstmt.setString(9, arrivalTime);
					pstmt.setString(10, price);
					pstmt.setString(11, "" + capacity);
					pstmt.setString(12, idCustomer);
					pstmt.setString(13, "" + idFlight);
					
					pstmt.executeUpdate();
					
					String queryUpdate = "UPDATE `dbo_airline`.`flights` SET `capacity`= ? WHERE `idflight`= ?";
					pstmt = conn.prepareStatement(queryUpdate);
					pstmt.setString(1, "" + capacity);
					pstmt.setString(2, "" + idFlight);
					pstmt.executeUpdate();
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("The flight is succefully added to my trip");
					alert.showAndWait();
					
				} else {
					
					capacity = capacity - 1;
					
					String queryInsert = "INSERT INTO `dbo_airline`.`tickets` (`flightNumber`, `departureCity`, `departureState`, `arrivalCity`, `arrivalState`, `departureDate`, `departureTime`, `arrivalDate`, `arrivalTime`, `price`, `capacity`, `ticket_customer`, `ticket_flight`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
					pstmt = conn.prepareStatement(queryInsert);
					pstmt.setString(1, flightNumber);
					pstmt.setString(2, departureCity);
					pstmt.setString(3, departureState);
					pstmt.setString(4, arrivalCity);
					pstmt.setString(5, arrivalState);
					pstmt.setDate(6, Date.valueOf(departureDate));
					pstmt.setString(7, departureTime);
					pstmt.setDate(8, Date.valueOf(arrivalDate));
					pstmt.setString(9, arrivalTime);
					pstmt.setString(10, price);
					pstmt.setString(11, "" + capacity);
					pstmt.setString(12, idCustomer);
					pstmt.setString(13, "" + idFlight);
					
					pstmt.executeUpdate();
					
					String queryUpdate = "UPDATE `dbo_airline`.`flights` SET `capacity`= ? WHERE `idflight`= ?";
					pstmt = conn.prepareStatement(queryUpdate);
					pstmt.setString(1, "" + capacity);
					pstmt.setString(2, "" + idFlight);
					pstmt.executeUpdate();
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("The flight is succefully added to my trip");
					alert.showAndWait();
				}

			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Select a flight");
			alert.showAndWait();
		
		} finally {
			
			conn.close();
		}				
		initialize(null, null);
	}
	

	public boolean isDateDuplicate() {
		
		try {
			
			Connection conn = null;
			String idCustomer = "" + LoginController.getIdCustomer();
			
			conn = DriverManager.getConnection(url, id, pw);
			LocalDate ld = flightTable.getSelectionModel().getSelectedItem().getDepartureDate();
			
			String query = "select departuredate, arrivaldate from tickets where ticket_customer = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, idCustomer);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
					
				if (rs.getDate("departuredate").toLocalDate().equals(ld) || rs.getDate("arrivaldate").toLocalDate().equals(ld)) {
					
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return false;
		
	}
	

	public boolean isFlightDuplicate(ResultSet rs, int idFlight) {
		
		try {
			
			while (rs.next()) {
				
				if(idFlight == rs.getInt("ticket_flight")) {
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("The flight number is already exist");
					alert.showAndWait();
					return true;
					
				} 
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return false;
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		//set up columns
		idFlightColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("idFlight"));
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightNumber"));
		departureCityColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureCity"));
		departureStateColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureState"));
		arrivalCityColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalCity"));
		arrivalStateColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalState"));
		departureDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate>("departureDate"));
		departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureTime"));
		arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate>("arrivalDate"));
		arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalTime"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("price"));
		capacityColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("capacity"));
		
		flightTable.setEditable(true);

		try {
			flightTable.setItems(getFlights());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
