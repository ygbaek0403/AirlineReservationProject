package classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ticket {

	private SimpleIntegerProperty idTicket;
	private SimpleStringProperty flightNumber;
	private SimpleStringProperty departureCity;
	private SimpleStringProperty departureState; 
	private SimpleStringProperty arrivalCity; 
	private SimpleStringProperty arrivalState;
	private SimpleStringProperty departureDate;
	private SimpleStringProperty departureTime;
	private SimpleStringProperty arrivalDate;
	private SimpleStringProperty arrivalTime;
	private SimpleStringProperty duration;
	private SimpleStringProperty price;
	private SimpleStringProperty capacity;
	private SimpleStringProperty ticket_customer;
	private SimpleStringProperty ticket_flight;
	
	
	public Ticket (int idTicket, String flightNumber, String departureCity, String departureState, String arrivalCity, String arrivalState, String departureDate, String departureTime, String arrivalDate, String arrivalTime, String duration, String price, String capacity, String ticket_customer, String ticket_flight) {
	
		this.idTicket = new SimpleIntegerProperty(idTicket);
		this.flightNumber = new SimpleStringProperty(flightNumber);
		this.departureCity = new SimpleStringProperty(departureCity);
		this.departureState = new SimpleStringProperty(departureState);
		this.arrivalCity = new SimpleStringProperty(arrivalCity);
		this.arrivalState = new SimpleStringProperty(arrivalState);
		this.departureDate = new SimpleStringProperty(departureDate);
		this.departureTime = new SimpleStringProperty(departureTime);
		this.arrivalDate = new SimpleStringProperty(arrivalDate);
		this.arrivalTime = new SimpleStringProperty(arrivalTime);
		this.duration = new SimpleStringProperty(duration);
		this.price = new SimpleStringProperty(price);	
		this.capacity = new SimpleStringProperty(capacity);	
		this.ticket_customer = new SimpleStringProperty(ticket_customer);	
		this.ticket_flight = new SimpleStringProperty(ticket_flight);		
	}

	public int getIdTicket() {
		return idTicket.get();
	}

	public void setIdTicket(SimpleIntegerProperty idTicket) {
		this.idTicket = idTicket;
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

	public String getDepartureDate() {
		return departureDate.get();
	}

	public void setDepartureDate(SimpleStringProperty departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime.get();
	}

	public void setDepartureTime(SimpleStringProperty departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalDate() {
		return arrivalDate.get();
	}

	public void setArrivalDate(SimpleStringProperty arrivalDate) {
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
	
	public String getCapacity() {
		return capacity.get();
	}

	public void setCapacity(SimpleStringProperty capacity) {
		this.capacity = capacity;
	}

	public String getTicket_customer() {
		return ticket_customer.get();
	}

	public void setTicket_customer(SimpleStringProperty ticket_customer) {
		this.ticket_customer = ticket_customer;
	}	
	
	public String getTicket_flight() {
		return ticket_flight.get();
	}

	public void setTicket_flight(SimpleStringProperty ticket_flight) {
		this.ticket_flight = ticket_flight;
	}
}
