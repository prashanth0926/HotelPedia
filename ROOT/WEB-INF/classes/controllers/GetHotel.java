package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HotelsDao;

@WebServlet("/getHotel")
public class GetHotel extends HttpServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HotelsDao hotelDao = new HotelsDao();
		//hotelDao.getHotelDetails("Chicago");
		//hotelDao.deleteFromArray(1);
		String startDate = request.getParameter("checkInDate");
		String endDate = request.getParameter("checkOutDate");
		String cityName = request.getParameter("cityName");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Long start = (long)0,end =(long)0;
		try {
			Date date_tem = formatter.parse(startDate);
			start = date_tem.getTime()/1000;
			date_tem = formatter.parse(endDate);
			end = date_tem.getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("hotels", hotelDao.findHotel(start,end,cityName));
		session.setAttribute("checkInDate", startDate);
		session.setAttribute("checkOutDate", endDate);
		session.setAttribute("cityName", cityName);
		response.sendRedirect("list.jsp");
	}
}
