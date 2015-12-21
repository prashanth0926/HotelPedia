package dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;

import beans.HotelBean;
import beans.HotelResultBean;
import beans.RoomBean;
import beans.RoomResultBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class HotelsDao {
	
	MongoClient mongoClient = new MongoClient();
	MongoDatabase database = mongoClient.getDatabase("Hotelpedia");
	MongoCollection<Document> collection = database.getCollection("Hotels");
	
	public void insertOrder(HotelBean hotel) throws JsonProcessingException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(hotel);
		collection.insertOne(Document.parse(json));
	}
	
	public int getCount(){
		return (int) collection.count();
	}
	
	public ArrayList<HotelBean> getHotelDetails(){
		ArrayList<Document> filters = new ArrayList<Document>();
		filters.add(new Document("$match",new Document("hotelId",new Document("$gt",0))));
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<HotelBean> hotels = new ArrayList<>();
		AggregateIterable<Document> cursor = collection.aggregate(filters);
		cursor.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        try {
		        	HotelBean hotel = new HotelBean();
		        	hotel = mapper.readValue(document.toJson(), HotelBean.class);
		        	hotels.add(hotel);
				} catch (Exception e) {
					e.printStackTrace();}
		    }
		});
		return hotels;
	}
	
	public void deleteFromArray(long hotelId,int roomId){
		collection.updateOne(new Document("hotelId",hotelId),
				new Document("$pull",new Document("rooms",new Document("roomId",roomId))));
	}
	
	public ArrayList<HotelBean> findHotel(Long startDate,Long endDate,String cityName){
		ArrayList<Document> filters = new ArrayList<Document>();
		filters.add(new Document("$match",new Document("city",cityName)));
		filters.add(new Document("$unwind","$rooms"));
		filters.add(new Document("$unwind","$rooms.dates"));
		filters.add(new Document("$match",new Document("rooms.dates.startDate",new Document("$lte",startDate))
								.append("rooms.dates.endDate", new Document("$gte",endDate))
								.append("rooms.dates.status", 1)));
		filters.add(new Document("$group",new Document("_id","$hotelId")
					.append("hotelId", new Document("$push","$hotelId"))
					.append("hotelName",new Document("$push","$hotelName"))
					.append("city",new Document("$push","$city"))
					.append("zip",new Document("$push","$zip"))
					.append("address",new Document("$push","$address"))
					.append("url",new Document("$push","$url"))
					.append("cancellationPolicy",new Document("$push","$cancellationPolicy"))
					.append("roomTypes",new Document("$push","$roomTypes"))
					.append("numberOfRooms",new Document("$push","$numberOfRooms"))
					.append("rooms",new Document("$push","$rooms"))
					.append("features",new Document("$push","$features"))
					.append("totalRooms",new Document("$push","$totalRooms"))
					.append("rating",new Document("$push","$rating"))
					.append("description", new Document("$push","$description"))
					.append("images", new Document("$push","$images"))
					.append("minPrice", new Document("$push","$minPrice"))));
					
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<HotelResultBean> hotelsResult = new ArrayList<HotelResultBean>();
		AggregateIterable<Document> cursor = collection.aggregate(filters);
		cursor.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        try {
		        	HotelResultBean hotel = new HotelResultBean();
		        	hotel = mapper.readValue(document.toJson(), HotelResultBean.class);
		        	hotelsResult.add(hotel);
				} catch (Exception e) {
					e.printStackTrace();}
		    }
		});
		ArrayList<HotelBean> hotels = new ArrayList<>();
		for(HotelResultBean hotelResult : hotelsResult)
		{
			int totalRooms = 0;
			if(hotelsResult.size() > 0)
			{
				HotelBean hotel = new HotelBean();
				hotel.setAddress(hotelResult.getAddress().get(0));
				hotel.setCity(hotelResult.getCity().get(0));
				hotel.setDescription(hotelResult.getDescription().get(0));
				hotel.setFeatures(hotelResult.getFeatures().get(0));
				hotel.setHotelId(hotelResult.getHotelId().get(0));
				hotel.setHotelName(hotelResult.getHotelName().get(0));
				hotel.setImages(hotelResult.getImages().get(0));
				hotel.setMinPrice(hotelResult.getMinPrice().get(0));
				hotel.setRating(hotelResult.getRating().get(0));
				ArrayList<RoomBean> rooms = new ArrayList<>();
				HashMap<String, Integer> roomTypes = new HashMap<>();
				String roomIds = "";
				for(RoomResultBean resultRoom : hotelResult.getRooms())
				{
					roomIds = roomIds+resultRoom.getRoomId()+"-";
					totalRooms++;
					if(roomTypes.get(resultRoom.getTypeName()) == null)
					{
						roomTypes.put(resultRoom.getTypeName(), 1);
					}
					else
					{
						roomTypes.put(resultRoom.getTypeName(), roomTypes.get(resultRoom.getTypeName())+1);
					}
					int count =0;
					for(RoomBean tempRoom : rooms)
					{
						if(tempRoom.getTypeName().equals(resultRoom.getTypeName()))
						{
							count++;
						}
					}
					if(count ==0)
					{
						RoomBean room = new RoomBean();
						room.setAddress(resultRoom.getAddress());
						room.setCity(resultRoom.getCity());
						ArrayList<HashMap<String, Long>> dates = new ArrayList<HashMap<String,Long>>();
						dates.add(resultRoom.getDates());
						room.setDates(dates);
						room.setFeatures(resultRoom.getFeatures());
						room.setHotelName(resultRoom.getHotelName());
						room.setImages(resultRoom.getImages());
						room.setPrice(resultRoom.getPrice());
						room.setRoomId(resultRoom.getRoomId());
						room.setTypeName(resultRoom.getTypeName());
						room.setZip(resultRoom.getZip());
						rooms.add(room);
					}
				}
				hotel.setCancellationPolicy(roomIds);
				hotel.setNumberOfRooms(roomTypes);
				hotel.setRooms(rooms);
				hotel.setRoomTypes(hotelResult.getRoomTypes().get(0));
				hotel.setTotalRooms(totalRooms);
				hotel.setUrl(hotelResult.getUrl().get(0));
				hotel.setZip(hotelResult.getZip().get(0));
				hotels.add(hotel);
			}
			
		}
		return hotels;
	}

	RoomBean room = new RoomBean();
	public RoomBean getRoom(long hotelId,int roomId)
	{
		ArrayList<Document> filters = new ArrayList<Document>();
		filters.add(new Document("$match",new Document("hotelId",hotelId)));
		filters.add(new Document("$unwind","$rooms"));
		filters.add(new Document("$match",new Document("rooms.roomId",roomId)));
		filters.add(new Document("$project",new Document("rooms",1)
								.append("_id", 0)));
		ObjectMapper mapper = new ObjectMapper();
		AggregateIterable<Document> cursor = collection.aggregate(filters);
		cursor.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        try {
		        	room = mapper.readValue(document.toJson().substring(11,document.toJson().length()-1), RoomBean.class);
				} catch (Exception e) {
					e.printStackTrace();}
		    }
		});
		return room;
	}
	
	public void updateRoom(long hotelId,String room)
	{
		collection.updateOne(new Document("hotelId",hotelId),
				new Document("$push",new Document("rooms",Document.parse(room))));
	}
	
	public void deleteHotel(int hotelId)
	{
		collection.deleteOne(new Document("hotelId",hotelId));
	}
}
