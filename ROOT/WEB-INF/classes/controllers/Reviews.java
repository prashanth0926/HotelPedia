package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ReviewsBean;
import beans.UserBean;
import dao.ReviewsDao;


@WebServlet("/reviews")
public class Reviews extends HttpServlet{

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		UserBean user = new UserBean();
		HttpSession session = request.getSession();
		user = (UserBean) session.getAttribute("userObj");
		String reviewText = request.getParameter("reviewText");
		String reviewTitle = request.getParameter("reviewTitle");
		int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
		int hotelId = Integer.parseInt(request.getParameter("hotelId"));
		String hotelName = request.getParameter("hotelName");
		
		ReviewsBean review = new ReviewsBean();
		
		review.setAge(user.getAge());
		review.setGender(user.getGender());
		review.setHotelId(hotelId);
		review.setHotelName(hotelName);
		review.setOccupation(user.getOccupation());
		review.setReviewRating((float)reviewRating);
		review.setReviewText(reviewText);
		review.setReviewtitle(reviewTitle);
		review.setUserEmail(user.getEmailId());
		review.setUserName(user.getFullName());
		
		ReviewsDao reviewDao = new ReviewsDao();
		reviewDao.insertReview(review);
		
		response.sendRedirect("index.jsp");
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		ReviewsDao reviewsDao = new ReviewsDao();
		int hotelId = Integer.parseInt(request.getParameter("hotelId"));
		HttpSession session = request.getSession();
		ArrayList<ReviewsBean> reviews =reviewsDao.getReviewsDetails(hotelId); 
		session.setAttribute("reviews", reviews);
		int star5=0,star4=0,star3=0,star2=0,star1=0,total=0;
		for(ReviewsBean review : reviews)
		{
			if(review.getReviewRating() == 1.0)
			{
				star1++;
			}
			else if(review.getReviewRating() == 2.0)
			{
				star2++;
			}
			else if(review.getReviewRating() == 3.0)
			{
				star3++;
			}
			else if(review.getReviewRating() == 4.0)
			{
				star4++;
			}
			else if(review.getReviewRating() == 5.0)
			{
				star5++;
			}
			total++;
		}
		session.setAttribute("star5", star5);
		session.setAttribute("star4", star4);
		session.setAttribute("star3", star3);
		session.setAttribute("star2", star2);
		session.setAttribute("star1", star1);
		session.setAttribute("total", total);
		response.sendRedirect("hotel.jsp?hotelId="+hotelId);
	}
	
}
