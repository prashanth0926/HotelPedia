package controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookingDao;

@WebServlet("/managerPage")
public class GetManager extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		BookingDao bookingDao = new BookingDao();
		HttpSession session = request.getSession();
		session.setAttribute("bookings",bookingDao.getBookingDetails());
		response.sendRedirect("managerPage.jsp");
	}
	
}
