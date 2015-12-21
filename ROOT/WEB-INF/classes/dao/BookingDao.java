package dao;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;

import beans.BookingBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class BookingDao {

	MongoClient mongoClient = new MongoClient();
	MongoDatabase database = mongoClient.getDatabase("Hotelpedia");
	MongoCollection<Document> collection = database.getCollection("Bookings");
	
	
	public void insertBooking(BookingBean booking) throws JsonProcessingException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(booking);
		collection.insertOne(Document.parse(json));
	}
	
	public ArrayList<BookingBean> getBookingDetails(String email){
		ArrayList<Document> filters = new ArrayList<Document>();
		filters.add(new Document("$match",new Document("emailId",email)));
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<BookingBean> bookings = new ArrayList<>();
		AggregateIterable<Document> cursor = collection.aggregate(filters);
		cursor.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        try {
		        	BookingBean booking = new BookingBean();
		        	booking = mapper.readValue(document.toJson(), BookingBean.class);
		        	bookings.add(booking);
				} catch (Exception e) {
					e.printStackTrace();}
		    }
		});
		return bookings;
	}
	
	public BookingBean getBooking(int bookingId) throws JsonParseException, JsonMappingException, IOException
	{
		Document myDoc = collection.find(eq("bookingId", bookingId)).first();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(myDoc.toJson(), BookingBean.class);
	}
	
	public ArrayList<BookingBean> getBookingDetails(){
		ArrayList<Document> filters = new ArrayList<Document>();
		filters.add(new Document("$match",new Document("bookingId",new Document("$gt",0))));
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<BookingBean> bookings = new ArrayList<>();
		AggregateIterable<Document> cursor = collection.aggregate(filters);
		cursor.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        try {
		        	BookingBean booking = new BookingBean();
		        	booking = mapper.readValue(document.toJson(), BookingBean.class);
		        	bookings.add(booking);
				} catch (Exception e) {
					e.printStackTrace();}
		    }
		});
		return bookings;
	}
	
	public void deleteBooking(int bookinId)
	{
		collection.deleteOne(new Document("bookingId", bookinId));
	}
}
