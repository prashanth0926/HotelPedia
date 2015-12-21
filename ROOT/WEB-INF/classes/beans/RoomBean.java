package beans;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomBean {

	String typeName,hotelName,city,zip,address;
	int roomId;
	HashMap<String,String> features;
	ArrayList<HashMap<String, Long>> dates;
	ArrayList<String> images;
	float price;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<HashMap<String, Long>> getDates() {
		return dates;
	}
	public void setDates(ArrayList<HashMap<String, Long>> dates) {
		this.dates = dates;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public HashMap<String, String> getFeatures() {
		return features;
	}
	public void setFeatures(HashMap<String, String> features) {
		this.features = features;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
}
