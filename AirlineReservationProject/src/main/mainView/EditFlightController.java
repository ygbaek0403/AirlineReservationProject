package main.mainView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;


public class EditFlightController extends MainViewController implements Initializable {
	
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
	private TextField priceTF;
	@FXML
	private TextField capacityTF;
	
	private PreparedStatement pstmt;
	
	private String url = "jdbc:mysql://localhost:3306/dbo_airline?useSSL=false";
	private String id = "root";
	private String pw = "iin";
	
	Alert alert = new Alert(AlertType.INFORMATION);

	MainViewController avc = new MainViewController();
	
	
	public void setFlight(int idFlight, String flightNumber, String departureCity, String departureState, String arrivalCity, String arrivalState, LocalDate departureDate, String departureTime, LocalDate arrivalDate, String arrivalTime, String price, int capacity) {
		
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
		this.priceTF.setText(price);
		this.capacityTF.setText("" + capacity);
	}
	
	@FXML
	private void goSubmit() throws IOException, SQLException {
		
		
		String idFlight = idFlightTF.getText();
		String departureCity = departureCityTF.getText();
		String departureState = departureStateTF.getText();
		String arrivalCity = arrivalCityTF.getText();
		String arrivalState = arrivalStateTF.getText();
		String departureDate = departureDateDP.getValue().toString();
		String departureTime = departureTimeTF.getText();
		String arrivalDate = arrivalDateDP.getValue().toString();
		String arrivalTime = arrivalTimeTF.getText();
		String price = priceTF.getText();
		String capacity = capacityTF.getText();
		
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(url, id, pw);
			
			
			
			if (departureCity.isEmpty() || departureState.isEmpty() || arrivalCity.isEmpty() || arrivalState.isEmpty() || departureTime.isEmpty() || arrivalTime.isEmpty() || price.isEmpty() || capacity.isEmpty()) {
				
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Check your fields.");
				alert.showAndWait();
				
			} else {
		
				String queryUpdate = "UPDATE `dbo_airline`.`flights` SET `departureCity`= ?, `departureState`= ?, `arrivalCity`= ?, `arrivalState`= ?, `departureDate`= ?, `departureTime`= ?, `arrivalDate`= ?, `arrivalTime`= ?, `price`= ?, `capacity`= ? WHERE `idflight`= ?";
				pstmt = conn.prepareStatement(queryUpdate);
				pstmt.setString(1, departureCity);
				pstmt.setString(2, departureState);
				pstmt.setString(3, arrivalCity);
				pstmt.setString(4, arrivalState);
				pstmt.setString(5, departureDate);
				pstmt.setString(6, departureTime);
				pstmt.setString(7, arrivalDate);
				pstmt.setString(8, arrivalTime);
				pstmt.setString(9, price);
				pstmt.setString(10, capacity);
				pstmt.setString(11, idFlight);
			
				pstmt.executeUpdate();
				
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("The flight is succefully edited");
				alert.showAndWait();
				
			}
		
			
		} catch (Exception e) {

			e.printStackTrace();
			
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

