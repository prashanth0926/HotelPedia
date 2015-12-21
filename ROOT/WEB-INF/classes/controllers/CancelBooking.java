package controllers;

import helper.EditDates;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BookingBean;
import beans.HotelBean;
import beans.RoomBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BookingDao;
import dao.HotelsDao;

@WebServlet("/cancelBooking")
public class CancelBooking extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		BookingDao bookingDao  = new BookingDao();
		BookingBean booking = bookingDao.getBooking(bookingId);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Long start=(long)0,end=(long)0;
		try{
			Date date_tem = formatter.parse(booking.getStartDate());
			start = date_tem.getTime()/1000;
			date_tem = formatter.parse(booking.getEndDate());
			end = date_tem.getTime()/1000;
		}catch (ParseException e) {
			e.printStackTrace();
		}
		EditDates editDates = new EditDates();
		HotelsDao hotelDao = new HotelsDao();
		ArrayList<RoomBean> originalRooms = new ArrayList<>();
		ObjectMapper mapper =new ObjectMapper();
		for(int i : booking.getRoomId())
		{
			RoomBean room = hotelDao.getRoom(booking.getHotelId(), i);
			editDates.removeDates(start, end, room.getDates());
			hotelDao.deleteFromArray(booking.getHotelId(),i);
			hotelDao.updateRoom(booking.getHotelId(), mapper.writeValueAsString(room));
		}
		bookingDao.deleteBooking(bookingId);
		response.sendRedirect("/getBookings");
	}

}
