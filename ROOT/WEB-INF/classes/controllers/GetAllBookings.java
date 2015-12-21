package controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookingDao;

@WebServlet("/getBookings")
public class GetAllBookings extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		BookingDao bookingDao = new BookingDao();
		HttpSession session = request.getSession();
		session.setAttribute("bookings",bookingDao.getBookingDetails((String) session.getAttribute("user")));
		response.sendRedirect("reservations.jsp");
	}

}
