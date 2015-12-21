package beans;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HotelResult1Bean {

	String hotelName,city,zip,address,_id,url,cancellationPolicy,description;
	ArrayList<String> roomTypes;
	HashMap<String, Integer> numberOfRooms;
	RoomResult1Bean rooms;
	ArrayList<String> features;
	int hotelId;
	int totalRooms;
	float rating,minPrice;
	ArrayList<String> images;
	Boolean valid;
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
	public String get_id() {
		return _id;
	}
	@JsonIgnore
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCancellationPolicy() {
		return cancellationPolicy;
	}
	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<String> getRoomTypes() {
		return roomTypes;
	}
	public void setRoomTypes(ArrayList<String> roomTypes) {
		this.roomTypes = roomTypes;
	}
	public HashMap<String, Integer> getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(HashMap<String, Integer> numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public RoomResult1Bean getRooms() {
		return rooms;
	}
	public void setRooms(RoomResult1Bean rooms) {
		this.rooms = rooms;
	}
	public ArrayList<String> getFeatures() {
		return features;
	}
	public void setFeatures(ArrayList<String> features) {
		this.features = features;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getTotalRooms() {
		return totalRooms;
	}
	public void setTotalRooms(int totalRooms) {
		this.totalRooms = totalRooms;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	
}
