package controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HotelsDao;

@WebServlet("/removeHotel")
public class RemoveHotel extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		int hotelId = Integer.parseInt(request.getParameter("hotelId"));
		HotelsDao hotelDao = new HotelsDao();
		hotelDao.deleteHotel(hotelId);
		response.sendRedirect("/adminPage");
	}
}
