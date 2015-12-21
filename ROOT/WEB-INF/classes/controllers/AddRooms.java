package controllers;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import beans.HotelBean;
import beans.RoomBean;
import dao.HotelsDao;

@WebServlet("/addRooms")
public class AddRooms extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HotelsDao hotelDao = new HotelsDao();
		HttpSession session = request.getSession();
		HotelBean hotel = new HotelBean();
		int typesCount =  Integer.parseInt(session.getAttribute("typeCount").toString());
		int featureCount = Integer.parseInt(session.getAttribute("featureCount").toString());
		ArrayList<String> features = new ArrayList<String>();
		int total_rooms = 0;
		Float minPrice = Float.MAX_VALUE;
		ArrayList<RoomBean> rooms_list = new ArrayList<RoomBean>();
		ArrayList<String> roomTypes = new ArrayList<String>();
		HashMap<String, Integer> roomCount = new HashMap<String, Integer>();
		HashMap<String, String> allParameters = new HashMap<String, String>();
		String savePath = request.getServletContext().getRealPath("")+ File.separator + "Hotels"+File.separator+
				session.getAttribute("hotelName").toString().replaceAll("\\s","").replaceAll("\\W", "");
		ArrayList<FileItem> images = new ArrayList<FileItem>();
		ArrayList<String> imageLocation = new ArrayList<>();
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		
		try {items = upload.parseRequest(request);}
		catch (FileUploadException e) {e.printStackTrace();	}
		
		Iterator<FileItem> itr = items.iterator();
		while (itr.hasNext()) 
		{
			FileItem item = (FileItem) itr.next();
			if (item.isFormField()) {
				allParameters.put(item.getFieldName(),item.getString());
			} 
			else 
			{
					images.add(item);
			}
		}
		
	    
		hotel.setHotelName(session.getAttribute("hotelName").toString());
		hotel.setCity(session.getAttribute("city").toString());
		hotel.setZip(session.getAttribute("zip").toString());
		hotel.setAddress(session.getAttribute("address").toString());
		hotel.setUrl(session.getAttribute("url").toString());
		hotel.setCancellationPolicy(session.getAttribute("cancellation").toString());
		hotel.setHotelId(hotelDao.getCount()+1);
		hotel.setRating(0);
		hotel.setDescription(session.getAttribute("description").toString());
		
		for(int i=1;i<=featureCount;i++){
			features.add(session.getAttribute("f"+i).toString());
		}
		hotel.setFeatures(features);
		
		for(int i=1;i<=typesCount;i++)
		{
			for(int j=0;j<((Integer.parseInt(allParameters.get("roomsNum"+i).toString())));j++){
				total_rooms++;
				RoomBean temp_room = new RoomBean();
				temp_room.setHotelName(hotel.getHotelName());
				temp_room.setAddress(hotel.getAddress());
				temp_room.setCity(hotel.getCity());
				temp_room.setZip(hotel.getZip());
				temp_room.setTypeName(allParameters.get("roomType"+i));
				HashMap<String, String> room_feature = new HashMap<String, String>();
				for(int k=1;k<=featureCount;k++){
					room_feature.put(allParameters.get(i+"f"+k+"name"), allParameters.get(i+"f"+k+"status"));
				}
				temp_room.setFeatures(room_feature);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				HashMap<String, Long> datesHash = new HashMap<String, Long>();
				ArrayList<HashMap<String, Long>> dates = new ArrayList<HashMap<String,Long>>();
				try {
					Date date_tem = formatter.parse(allParameters.get("startDate"+i));
					datesHash.put("startDate", date_tem.getTime()/1000);
					date_tem = formatter.parse(allParameters.get("endDate"+i));
					datesHash.put("endDate", date_tem.getTime()/1000);
					datesHash.put("status", (long) 1);
					dates.add(datesHash);
					temp_room.setDates(dates);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				temp_room.setPrice(Integer.parseInt(allParameters.get("roomPrice"+i).toString()));
				temp_room.setRoomId(total_rooms);
				ArrayList<String> tempLocation = new ArrayList<>();
				for(FileItem item : images)
				{
					int count = 1;
					String type = allParameters.get("roomType"+item.getFieldName().substring(6)).replaceAll("\\s","").replaceAll("\\W", "");
					if(type.equals(temp_room.getTypeName()))
					{
						try {
							tempLocation.add(type+File.separator+"image"+(count+1)+".jpg");
							count ++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				temp_room.setImages(tempLocation);
				if(temp_room.getPrice() < minPrice)
					minPrice = temp_room.getPrice();
				rooms_list.add(temp_room);
			}
			roomTypes.add(allParameters.get("roomType"+i));
			roomCount.put(allParameters.get("roomType"+i), Integer.parseInt(allParameters.get("roomsNum"+i).toString()));
		}
		
		hotel.setRooms(rooms_list);
		hotel.setRoomTypes(roomTypes);
		hotel.setNumberOfRooms(roomCount);
		hotel.setTotalRooms(total_rooms);
		hotel.setImages((ArrayList<String>) session.getAttribute("hotelImages"));
		hotel.setMinPrice(minPrice);
		hotel.setValid(true);
		
		for(int i=0;i<typesCount;i++)
		{
			new File(savePath+File.separator+hotel.getRoomTypes().get(i).replaceAll("\\s","").replaceAll("\\W", "")).mkdir();
		}
		
		for(FileItem item : images)
		{
			String type = allParameters.get("roomType"+item.getFieldName().substring(6)).replaceAll("\\s","").replaceAll("\\W", "");
			{
				int count = new File(savePath+File.separator+type).list().length;
				try {
					item.write(new File(savePath+File.separator+type+File.separator+"image"+(count+1)+".jpg"));
					imageLocation.add(type+File.separator+"image"+(count+1)+".jpg");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		for(int i=0;i<hotel.getRooms().size();i++)
		{
			RoomBean room = hotel.getRooms().get(i);
			ArrayList<String> imagesLoc = new ArrayList<>();
 			for(String image : imageLocation)
			{
				if(room.getTypeName().replaceAll("\\s","").replaceAll("\\W", "").equals(image.split("\\\\")[0]))
				{
					imagesLoc.add("Hotels"+File.separator+hotel.getHotelName().replaceAll("\\s","").replaceAll("\\W", "")
							+File.separator+image);
				}
			}
 			hotel.getRooms().get(i).setImages(imagesLoc);
		}
		hotelDao.insertOrder(hotel);
		response.sendRedirect("addHotel.jsp");
	}
}
