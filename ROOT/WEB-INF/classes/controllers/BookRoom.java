package controllers;

import helper.EditDates;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BookingBean;
import beans.HotelBean;
import beans.RoomBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BookingDao;
import dao.HotelsDao;

@WebServlet("/bookRoom")
public class BookRoom extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		int hotelId = (int)session.getAttribute("hotelId");
		int roomId = (int)session.getAttribute("roomId");
		int numOfRooms = (int)session.getAttribute("numOfRooms");
		String checkIn = (String)session.getAttribute("checkInDate");
		String checkOut = (String)session.getAttribute("checkOutDate");
		Long checkInLong = (long)0;
		Long checkOutLong = (long)0;
		
		String typeName = "";
		HotelsDao hotelsDao = new HotelsDao();
		ArrayList<HotelBean> hotels = (ArrayList<HotelBean>) session.getAttribute("hotels");
		HotelBean bookingHotel = new HotelBean();
		RoomBean requiredRoom = new RoomBean();
		ArrayList<RoomBean> finalRooms = new ArrayList<>();
		HashMap<Integer, RoomBean> newRooms = new HashMap<>();
		ArrayList<Integer> roomIds = new ArrayList<>();
		
		EditDates editDates = new EditDates();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date_tem = formatter.parse(checkIn);
			checkInLong = date_tem.getTime()/1000;
			date_tem = formatter.parse(checkOut);
			checkOutLong = date_tem.getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		HotelBean hotel = new HotelBean();
		for(HotelBean temp : hotels)
		{
			if(temp.getHotelId() == hotelId)
			{
				String[] idsTemp = temp.getCancellationPolicy().split("-");
				ArrayList<Integer>  roomIdst = new ArrayList<>();
				for(String i : idsTemp)
					roomIdst.add(Integer.parseInt(i));
				hotel = temp;
				ArrayList<RoomBean> rooms = new ArrayList<>();
				for(int i : roomIdst)
				{
					RoomBean room = hotelsDao.getRoom(hotelId,i);
					rooms.add(room);
				}
				hotel.setRooms(rooms);
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<RoomBean> rooms = hotel.getRooms();
		for(RoomBean room : rooms)
		{
			if(room.getRoomId() == roomId)
			{
				requiredRoom = room;
				break;
			}
		}
		bookingHotel.setTotalRooms(numOfRooms);
		typeName = requiredRoom.getTypeName();
		for(RoomBean room : rooms)
		{
			if(room.getTypeName().equals(requiredRoom.getTypeName()) && (numOfRooms > 0) )
			{
				RoomBean tempRoom = new RoomBean();
				tempRoom.setAddress(room.getAddress());
				tempRoom.setCity(room.getCity());
				tempRoom.setDates(editDates.addDates(checkInLong, checkOutLong,
						room.getDates()));
				tempRoom.setFeatures(room.getFeatures());
				tempRoom.setHotelName(room.getHotelName());
				tempRoom.setImages(room.getImages());
				tempRoom.setPrice(room.getPrice());
				tempRoom.setRoomId(room.getRoomId());
				tempRoom.setTypeName(room.getTypeName());
				tempRoom.setZip(room.getZip());
				numOfRooms--;
				newRooms.put(tempRoom.getRoomId(),tempRoom);
				HashMap<String, Long> datesHash = new HashMap<String, Long>();
				ArrayList<HashMap<String, Long>> dates = new ArrayList<HashMap<String,Long>>();
				try {
					Date date_tem = formatter.parse(checkIn);
					datesHash.put("startDate", date_tem.getTime()/1000);
					date_tem = formatter.parse(checkOut);
					datesHash.put("endDate", date_tem.getTime()/1000);
					datesHash.put("status", (long) 0);
					dates.add(datesHash);
					room.setDates(dates);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				roomIds.add(room.getRoomId());
				finalRooms.add(room);
			}
		}
		bookingHotel = hotel;
		bookingHotel.setRooms(finalRooms);

		
		for (Map.Entry<Integer, RoomBean> entry : newRooms.entrySet()) 
		{
			hotelsDao.deleteFromArray(hotelId, entry.getKey());
			hotelsDao.updateRoom(hotelId, mapper.writeValueAsString(entry.getValue()));
		}
		BookingBean booking = new BookingBean();
		booking.setBookingDate(new Date().toString());
		booking.setBookingId(10000 + new Random( System.currentTimeMillis()).nextInt(20000));
		booking.setBookingName(request.getParameter("bookingName"));
		booking.setStartDate(checkIn);
		booking.setEndDate(checkOut);
		booking.setEmailId((String)session.getAttribute("user"));
		booking.setHotel(bookingHotel);
		booking.setHotelId(bookingHotel.getHotelId());
		booking.setRoomId(roomIds);
		
		BookingDao bookingDao = new BookingDao();
		bookingDao.insertBooking(booking);
		
		response.sendRedirect("index.jsp");
		
	}
}
