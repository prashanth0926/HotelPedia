package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import dao.UserDao;


@WebServlet("/user")
public class UserController extends HttpServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		String emailId = request.getParameter("emailId");
		String password = request.getParameter("password");
		String type = request.getParameter("userType");
		UserDao userDao = new UserDao();
		String attr = "";
		if(type.equals("admin"))
		{
			if(emailId.equals("admin@hotelpedia.com"))
				if(password.equals("1234"))
				{
					session.setAttribute("user", emailId);
					response.sendRedirect("/adminPage");
					return;
				}
				else
				{
					attr = "?type=login&status=false";
					response.sendRedirect("index.jsp"+attr);
					return;
				}
			
		}
		else if(type.equals("manager"))
		{
			if(emailId.equals("manager@hotelpedia.com"))
				if(password.equals("1234"))
				{
					session.setAttribute("user", emailId);
					response.sendRedirect("/mangerPage");
					return;
				}
				else
				{
					attr = "?type=login&status=false";
					response.sendRedirect("index.jsp"+attr);
					return;
				}
			
		}
		if(userDao.checkUser(emailId, password,type))
		{
			
			attr = "?type=login&status=true";
			session.setAttribute("user", emailId);
			session.setAttribute("userObj", userDao.getUser(emailId));
			response.sendRedirect("index.jsp"+attr);
			return;
		}
		else
		{
			attr = "?type=login&status=false";
			response.sendRedirect("index.jsp"+attr);
			return;
		}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		UserBean user = new UserBean();
		user.setEmailId(request.getParameter("emailId"));
		user.setFullName(request.getParameter("fullName"));
		user.setPassword(request.getParameter("password"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		user.setAge(Integer.parseInt(request.getParameter("age")));
		user.setGender(request.getParameter("gender"));
		user.setOccupation(request.getParameter("occupation"));
		user.setType("customer");
		UserDao userDao = new UserDao();
		String attr = "";
		if(userDao.findUser(user.getEmailId()))
		{
			attr = "?type=signup&status=true";
			userDao.insertUser(user);
		}
		else
		{
			attr = "?type=login&status=false";
		}
		response.sendRedirect("index.jsp"+attr);
	}
}
