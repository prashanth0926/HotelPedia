<%@page import="beans.RoomBean"%>
<%@page import="java.sql.Array"%>
<%@page import="beans.HotelBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	ArrayList<HotelBean> hotels = (ArrayList<HotelBean>)session.getAttribute("hotels");
	for(HotelBean hotel : hotels)
	{
		//out.println(hotel.getImages()+"<br/>");
		ArrayList<RoomBean> rooms = hotel.getRooms();
		for(RoomBean room : rooms)
			out.println(room+"<br/>");	
	}
%>
	
Hello In getHotel.