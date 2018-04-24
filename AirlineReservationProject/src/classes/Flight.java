package classes;

import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Flight {

	private SimpleIntegerProperty idFlight;
	private SimpleStringProperty flightNumber;
	private SimpleStringProperty departureCity;
	private SimpleStringProperty departureState; 
	private SimpleStringProperty arrivalCity; 
	private SimpleStringProperty arrivalState;
	private LocalDate departureDate;
	private SimpleStringProperty departureTime;
	private LocalDate arrivalDate;
	private SimpleStringProperty arrivalTime;
	private SimpleStringProperty duration;
	private SimpleStringProperty price;
	private SimpleIntegerProperty capacity;
	
	
	public Flight(int idFlight, String flightNumber, String departureCity, String departureState, String arrivalCity, String arrivalState, LocalDate departureDate, String departureTime, LocalDate arrivalDate, String arrivalTime, String duration, String price, int capacity) {
	
		this.idFlight = new SimpleIntegerProperty(idFlight);
		this.flightNumber = new SimpleStringProperty(flightNumber);
		this.departureCity = new SimpleStringProperty(departureCity);
		this.departureState = new SimpleStringProperty(departureState);
		this.arrivalCity = new SimpleStringProperty(arrivalCity);
		this.arrivalState = new SimpleStringProperty(arrivalState);
		this.departureDate = departureDate;
		this.departureTime = new SimpleStringProperty(departureTime);
		this.arrivalDate = arrivalDate;
		this.arrivalTime = new SimpleStringProperty(arrivalTime);
		this.duration = new SimpleStringProperty(duration);
		this.price = new SimpleStringProperty(price);	
		this.capacity = new SimpleIntegerProperty(capacity);		
	}

	public int getIdFlight() {
		return idFlight.get();
	}

	public void setIdFlight(SimpleIntegerProperty idFlight) {
		this.idFlight = idFlight;
	}
	
	public String getFlightNumber() {
		return flightNumber.get();
	}

	public void setFlightNumber(SimpleStringProperty flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepartureCity() {
		return departureCity.get();
	}

	public void setDepartureCity(SimpleStringProperty departureCity) {
		this.departureCity = departureCity;
	}

	public String getDepartureState() {
		return departureState.get();
	}

	public void setDepartureState(SimpleStringProperty departureState) {
		this.departureState = departureState;
	}

	public String getArrivalCity() {
		return arrivalCity.get();
	}

	public void setArrivalCity(SimpleStringProperty arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public String getArrivalState() {
		return arrivalState.get();
	}

	public void setArrivalState(SimpleStringProperty arrivalState) {
		this.arrivalState = arrivalState;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime.get();
	}

	public void setDepartureTime(SimpleStringProperty departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalTime() {
		return arrivalTime.get();
	}

	public void setArrivalTime(SimpleStringProperty arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDuration() {
		return duration.get();
	}

	public void setDuration(SimpleStringProperty duration) {
		this.duration = duration;
	}

	public String getPrice() {
		return price.get();
	}

	public void setPrice(SimpleStringProperty price) {
		this.price = price;
	}
	
	public int getCapacity() {
		return capacity.get();
	}

	public void setCapacity(SimpleIntegerProperty capacity) {
		this.capacity = capacity;
	}
	
}
