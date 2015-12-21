package dao;

import java.util.ArrayList;

import org.bson.Document;

import beans.ReviewsBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ReviewsDao {

	MongoClient mongoClient = new MongoClient();
	MongoDatabase database = mongoClient.getDatabase("Hotelpedia");
	MongoCollection<Document> collection = database.getCollection("Reviews");
	
	public void insertReview(ReviewsBean review) throws JsonProcessingException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(review);
		collection.insertOne(Document.parse(json));
	}
	
	public ArrayList<ReviewsBean> getReviewsDetails(int hotelId){
		ArrayList<Document> filters = new ArrayList<Document>();
		filters.add(new Document("$match",new Document("hotelId",hotelId)));
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<ReviewsBean> reviews = new ArrayList<>();
		AggregateIterable<Document> cursor = collection.aggregate(filters);
		cursor.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        try {
		        	ReviewsBean review = new ReviewsBean();
		        	review = mapper.readValue(document.toJson(), ReviewsBean.class);
		        	reviews.add(review);
				} catch (Exception e) {
					e.printStackTrace();}
		    }
		});
		return reviews;
	}
	
}
