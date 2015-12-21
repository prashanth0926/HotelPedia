package beans;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookingBean {

	String emailId;
	long hotelId;
	int bookingId;
	ArrayList<Integer> roomId;
	String startDate,endDate,bookingName;
	String bookingDate;
	HotelBean hotel;
	String _id;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getHotelId() {
		return hotelId;
	}
	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
	public ArrayList<Integer> getRoomId() {
		return roomId;
	}
	public void setRoomId(ArrayList<Integer> roomId) {
		this.roomId = roomId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBookingName() {
		return bookingName;
	}
	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public HotelBean getHotel() {
		return hotel;
	}
	public void setHotel(HotelBean hotel) {
		this.hotel = hotel;
	}
	public String get_id() {
		return _id;
	}
	@JsonIgnore
	public void set_id(String _id) {
		this._id = _id;
	}
	
}
