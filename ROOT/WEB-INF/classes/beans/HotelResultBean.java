package beans;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HotelResultBean {

	ArrayList<String> hotelName,city,zip,address,url,cancellationPolicy,description;
	ArrayList<ArrayList<String>> roomTypes;
	ArrayList<HashMap<String, Integer>> numberOfRooms;
	ArrayList<RoomResultBean> rooms;
	ArrayList<ArrayList<String>> features;
	ArrayList<Integer> hotelId;
	ArrayList<Integer> totalRooms;
	ArrayList<Float> rating,minPrice;
	ArrayList<ArrayList<String>> images;
	String _id;
	
	public ArrayList<String> getHotelName() {
		return hotelName;
	}
	public void setHotelName(ArrayList<String> hotelName) {
		this.hotelName = hotelName;
	}
	public ArrayList<String> getCity() {
		return city;
	}
	public void setCity(ArrayList<String> city) {
		this.city = city;
	}
	public ArrayList<String> getZip() {
		return zip;
	}
	public void setZip(ArrayList<String> zip) {
		this.zip = zip;
	}
	public ArrayList<String> getAddress() {
		return address;
	}
	public void setAddress(ArrayList<String> address) {
		this.address = address;
	}
	public ArrayList<String> getUrl() {
		return url;
	}
	public void setUrl(ArrayList<String> url) {
		this.url = url;
	}
	public ArrayList<String> getCancellationPolicy() {
		return cancellationPolicy;
	}
	public void setCancellationPolicy(ArrayList<String> cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
	public ArrayList<ArrayList<String>> getRoomTypes() {
		return roomTypes;
	}
	public void setRoomTypes(ArrayList<ArrayList<String>> roomTypes) {
		this.roomTypes = roomTypes;
	}
	public ArrayList<HashMap<String, Integer>> getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(ArrayList<HashMap<String, Integer>> numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public ArrayList<RoomResultBean> getRooms() {
		return rooms;
	}
	public void setRooms(ArrayList<RoomResultBean> rooms) {
		this.rooms = rooms;
	}
	public ArrayList<ArrayList<String>> getFeatures() {
		return features;
	}
	public void setFeatures(ArrayList<ArrayList<String>> features) {
		this.features = features;
	}
	public ArrayList<Integer> getHotelId() {
		return hotelId;
	}
	public void setHotelId(ArrayList<Integer> hotelId) {
		this.hotelId = hotelId;
	}
	public ArrayList<Integer> getTotalRooms() {
		return totalRooms;
	}
	public void setTotalRooms(ArrayList<Integer> totalRooms) {
		this.totalRooms = totalRooms;
	}
	public ArrayList<Float> getRating() {
		return rating;
	}
	public void setRating(ArrayList<Float> rating) {
		this.rating = rating;
	}
	public String get_id() {
		return _id;
	}
	@JsonIgnore
	public void set_id(String _id) {
		this._id = _id;
	}
	public ArrayList<String> getDescription() {
		return description;
	}
	public void setDescription(ArrayList<String> description) {
		this.description = description;
	}
	public ArrayList<ArrayList<String>> getImages() {
		return images;
	}
	public void setImages(ArrayList<ArrayList<String>> images) {
		this.images = images;
	}
	public ArrayList<Float> getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(ArrayList<Float> minPrice) {
		this.minPrice = minPrice;
	}
}
