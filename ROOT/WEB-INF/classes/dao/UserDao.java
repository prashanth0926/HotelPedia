package dao;

import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;

import beans.UserBean;

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

import static com.mongodb.client.model.Filters.*;

public class UserDao {

	MongoClient mongoClient = new MongoClient();
	MongoDatabase database = mongoClient.getDatabase("Hotelpedia");
	MongoCollection<Document> collection = database.getCollection("Users");
	
	public void insertUser(UserBean user) throws JsonProcessingException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(user);
		collection.insertOne(Document.parse(json));
	}
	
	public Boolean status = true;
	
	public boolean findUser(String emailId)
	{
		ArrayList<Document> filters = new ArrayList<Document>();
		filters.add(new Document("$match",new Document("emailId",emailId)));
		AggregateIterable<Document> cursor = collection.aggregate(filters);
		cursor.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        try {
		        	if(document.size() > 0)
		        		status =  false;
		        	else
		        		status = true;
				} catch (Exception e) {
					e.printStackTrace();}
		    }
		});
		return status;
	}
	
	public boolean checkUser(String emailId,String password,String type)
	{
		Document myDoc = collection.find(eq("emailId", emailId)).first();
		if(myDoc != null)
			if(password.equals(myDoc.get("password")) && type.equals(myDoc.get("type")))
				status = true;
			else
				status = false;
		else
			status = false;
		return status;
	}
	
	public UserBean getUser(String emailId) throws JsonParseException, JsonMappingException, IOException
	{
		Document myDoc = collection.find(eq("emailId", emailId)).first();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(myDoc.toJson(), UserBean.class);
	}
	
}
