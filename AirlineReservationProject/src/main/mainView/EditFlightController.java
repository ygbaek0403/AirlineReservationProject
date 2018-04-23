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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;


public class EditFlightController implements Initializable {
	
	@FXML
	private TextField idFlightTF;
	@FXML
	private TextField flightNumberTF;
	@FXML
	private TextField departureCityTF;
	@FXML
	private TextField departureStateTF;
	@FXML
	private TextField arrivalCityTF;
	@FXML
	private TextField arrivalStateTF;
	@FXML
	private DatePicker departureDateDP;
	@FXML
	private TextField departureTimeTF;
	@FXML
	private DatePicker arrivalDateDP;
	@FXML
	private TextField arrivalTimeTF;
	@FXML
	private TextField durationTF;
	@FXML
	private TextField priceTF;
	@FXML
	private TextField capacityTF;
	
	private PreparedStatement pstmt;
	
	private String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
	private String id = "root";
	private String pw = "iin";
	
	Alert alert = new Alert(AlertType.INFORMATION);

	AdminViewController avc = new AdminViewController();
	
	
	public void setFlight(int idFlight, String flightNumber, String departureCity, String departureState, String arrivalCity, String arrivalState, LocalDate departureDate, String departureTime, LocalDate arrivalDate, String arrivalTime, String duration, String price, String capacity) {
		
		this.idFlightTF.setText("" + idFlight);
		this.flightNumberTF.setText(flightNumber);
		this.departureCityTF.setText(departureCity);
		this.departureStateTF.setText(departureState);
		this.arrivalCityTF.setText(arrivalCity);
		this.arrivalStateTF.setText(arrivalState);
		this.departureDateDP.setValue(departureDate);
		this.departureTimeTF.setText(departureTime);
		this.arrivalDateDP.setValue(arrivalDate);
		this.arrivalTimeTF.setText(arrivalTime);
		this.durationTF.setText(duration);
		this.priceTF.setText(price);
		this.capacityTF.setText(capacity);
	}
	
	@FXML
	private void goSubmit() throws IOException, SQLException {
		
		
		String idFlight = idFlightTF.getText();
		String flightNumber = flightNumberTF.getText();
		String departureCity = departureCityTF.getText();
		String departureState = departureStateTF.getText();
		String arrivalCity = arrivalCityTF.getText();
		String arrivalState = arrivalStateTF.getText();
		String departureDate = departureDateDP.getValue().toString();
		String departureTime = departureTimeTF.getText();
		String arrivalDate = arrivalDateDP.getValue().toString();
		String arrivalTime = arrivalTimeTF.getText();
		String duration = durationTF.getText();
		String price = priceTF.getText();
		String capacity = capacityTF.getText();
		
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			String query = "select flightNumber from flights";
			ResultSet rs = stmt.executeQuery(query);
			
			if(isDuplicate(rs, flightNumber) == false) {
				
				if (flightNumber.isEmpty() || departureCity.isEmpty() || departureState.isEmpty() || arrivalCity.isEmpty() || arrivalState.isEmpty() || departureTime.isEmpty() || arrivalTime.isEmpty() || duration.isEmpty() || price.isEmpty() || capacity.isEmpty()) {
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Check your fields.");
					alert.showAndWait();
					
				} else {
			
					String queryUpdate = "UPDATE `dbo_airline`.`flights` SET `idflight`= ?, `flightNumber`= ?, `departureCity`= ?, `departureState`= ?, `arrivalCity`= ?, `arrivalState`= ?, `departureDate`= ?, `departureTime`= ?, `arrivalDate`= ?, `arrivalTime`= ?, `duration`= ?, `price`= ?, `capacity`= ? WHERE `idflight`= ?";
					pstmt = conn.prepareStatement(queryUpdate);
					pstmt.setString(1, idFlight);
					pstmt.setString(2, flightNumber);
					pstmt.setString(3, departureCity);
					pstmt.setString(4, departureState);
					pstmt.setString(5, arrivalCity);
					pstmt.setString(6, arrivalState);
					pstmt.setString(7, departureDate);
					pstmt.setString(8, departureTime);
					pstmt.setString(9, arrivalDate);
					pstmt.setString(10, arrivalTime);
					pstmt.setString(11, duration);
					pstmt.setString(12, price);
					pstmt.setString(13, capacity);
					pstmt.setString(14, idFlight);
				//
					pstmt.executeUpdate();
					
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("The flight is succefully registered");
					alert.showAndWait();
					
				}
			
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());

			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Check your fields.");
			alert.showAndWait();
		
		} finally {
			
			conn.close();
		}
		
		
	}
	
	public boolean isDuplicate(ResultSet rs, String flightNumber) {
		
		try {
			
			while (rs.next()) {
				
				if(flightNumber.equals(rs.getString("flightnumber"))) {
					
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
	
	}
}

