package main.mainView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;


public class AddFlightController {

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
	private TextField capacityTF;
	
	private PreparedStatement pstmt;
	
	@FXML
	private void goSubmit() throws IOException, SQLException {
		
		
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
		String capacity = capacityTF.getText();

		String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
		String id = "root";
		String pw = "iin";
		
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			Statement stmt = conn.createStatement();
			String query = "select flightNumber from flights";
			ResultSet rs = stmt.executeQuery(query);
			
			if(isDuplicate(rs, flightNumber) == false) {
				
				if (flightNumber.isEmpty() || departureCity.isEmpty() || departureState.isEmpty() || arrivalCity.isEmpty() || arrivalState.isEmpty() || departureTime.isEmpty() || arrivalTime.isEmpty() || duration.isEmpty() || capacity.isEmpty()) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Check your fields.");
					alert.showAndWait();
					
				} else {
			
					String queryInsert = "INSERT INTO `dbo_airline`.`flights` (`flightNumber`, `departureCity`, `departureState`, `arrivalCity`, `arrivalState`, `departureDate`, `departureTime`, `arrivalDate`, `arrivalTime`, `duration`, `capacity`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
					pstmt = conn.prepareStatement(queryInsert);
					pstmt.setString(1, flightNumber);
					pstmt.setString(2, departureCity);
					pstmt.setString(3, departureState);
					pstmt.setString(4, arrivalCity);
					pstmt.setString(5, arrivalState);
					pstmt.setString(6, departureDate);
					pstmt.setString(7, departureTime);
					pstmt.setString(8, arrivalDate);
					pstmt.setString(9, arrivalTime);
					pstmt.setString(10, duration);
					pstmt.setString(11, capacity);
					
					pstmt.executeUpdate();
					conn.close();
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("The flight is succefully registered");
					alert.showAndWait();
				}
			
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			Alert alert = new Alert(AlertType.INFORMATION);
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
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("The flight number is already exist");
					alert.showAndWait();
					return true;
					
				} 
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		
		return false;
	}
}