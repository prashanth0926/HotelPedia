package controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HotelsDao;

@WebServlet("/adminPage")
public class AdminPage extends HttpServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		HotelsDao hotelDao = new HotelsDao();
		session.setAttribute("hotels", hotelDao.getHotelDetails());
		response.sendRedirect("admin.jsp");
	}
}
