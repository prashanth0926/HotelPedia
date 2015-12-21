<%@ page language="java" import="com.mongodb.*"
	import="java.net.UnknownHostException" import="java.util.*"
	import="java.text.*" import="connection.*"
	import="beans.RoomBean"
import="java.sql.Array"
import="beans.HotelBean"
import="beans.*"
import="java.util.ArrayList"
	%>
<!DOCTYPE HTML>
<HTML>

<HEAD>

<META name="viewport" content="width=device-width, initial-scale=1.0" />
<LINK href="css/bootstrap.min.css" rel="stylesheet"></LINK>
<LINK href="css/styles.css" rel="stylesheet"></LINK>
<link rel="icon" type="image/gif" href="images/H.png" />
<TITLE>Hotelpedia-Review</TITLE>

</HEAD>

<BODY>
<%
	String emailId = (String) session.getAttribute("user");
%>
	<HEADER>

		<DIV class="navbar navbar-inverse navbar-fixed-top">

			<b><DIV class="container">

					<H1>
						<a href="index.jsp" class="navbar-brand">HOTELPEDIA</a>
					</H1>

				</DIV>

				<DIV class="container">

					<BUTTON class="navbar-toggle" data-toggle="collapse"
						data-target=".navHeaderCollapse">
						<SPAN class="icon-bar"></SPAN> <SPAN class="icon-bar"></SPAN> <SPAN
							class="icon-bar"></SPAN>
					</BUTTON>

					<DIV class="collapse navbar-collapse navHeaderCollapse">

						<UL class="nav navbar-nav navbar-left">

							<LI class="active"><a href="index.jsp">Home</a></LI>
							<LI><a href="#">Daily Deals</a></LI>

						</UL>

						<UL class="nav navbar-nav navbar-right">

							<LI class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#">Support<b class="caret"></b></a>

								<UL class="dropdown-menu">
									<LI><a href="#">File a Complaint</a></LI>
									<LI><a href="contactUs.jsp">Contact Us</a></LI>
									<LI><a href="#">Live Chat</a></LI>
								</UL></LI>
							<LI class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#">Account<b class="caret"></b></a>

								<%
									if (emailId != null) {
								%>
								<UL class="dropdown-menu">
									<LI><a href="profile.jsp">Profile</a></LI>
									<LI><a href="getBookings">My Bookings</a></LI>
									<LI><a href="logOut.jsp">Logout</a></LI>
								</UL> <%
								 	} else {
								 %>
								<UL class="dropdown-menu">
									<LI><a href="#signIn" data-toggle="modal" id="signInButton">Sign In</a></LI>
								</UL> <%
								 	}
								 %></LI>
						</UL>
					</DIV>

				</DIV> </b>
		</DIV>

		<DIV class="modal fade mt" id="signIn" role="dialog">
			<DIV class="modal-dialog">
				<DIV class="modal-content">
					<form action="user">
						<DIV class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<H4 class="text-center">
								Sign In As: <small><select name="userType">
										<option value="customer">Customer</option>
										<option value="manager">Manager</option>
										<option value="admin">Admin</option></select></small>
							</H4>
						</DIV>
						<DIV class="modal-body">
							<div class="form-group">
									<h3 style="color: red;font: verdana;" id="signInMessage"></h3>
							</div>
							<div class="form-group">
								<label for="emailid">Email ID:</label> <input type="email"
									class="form-control" id="emailid" name="emailId" required
									placeholder="example@example.com" />
							</div>
							<div class="form-group">
								<label for="password">Password:</label> <input type="password"
									class="form-control" id="password" name="password"
									pattern=".{4,}" required placeholder="minimum length 4" />
							</div>
						</DIV>
						<DIV class="modal-footer">
							<a class="mr" href="#signUp" data-toggle="modal"
								data-dismiss="modal">New to Hotelpedia? Sign Up</a> <input
								type="submit" class="btn btn-primary" name="signIn"
								value="LOGIN" />
						</DIV>
					</form>
				</DIV>
			</DIV>
		</DIV>

		<DIV class="modal fade mt" id="signUp" role="dialog">
			<DIV class="modal-dialog">
				<DIV class="modal-content">
					<form action="user" method="post" id="signupForm" onsubmit="return verifyPassword()">
						<DIV class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<H4 class="text-center">Customer Sign Up</H4>
						</DIV>
						<DIV class="modal-body">
							<div class="form-group">
									<h3 style="color: red;font: verdana;" id="signUpMessage"></h3>
							</div>
							<div class="form-group">
								<label for="emailid1">Email ID:</label> <input type="email"
									class="form-control" id="emailid1" name="emailId" required
									placeholder="example@example.com" />
							</div>
							<div class="form-group">
								<h3 id="errorMessage" style="color: red;font: verdana;"></h3>
							</div>
							<div class="form-group">
								<label for="name">Full Name:</label> <input type="text"
									class="form-control" id="name" name="fullName" required
									placeholder="First Name Last Name" />
							</div>
							<div class="form-group">
								<label for="phone">Phone Number:</label> <input type="number"
									class="form-control" id="phone" name="phoneNumber" placeholder="Enter phone number" />
							</div>
							<div class="form-group">
								<label for="age">Age:</label> <input type="number"
									class="form-control" id="age" name="age" placeholder="Enter age" />
							</div>
							<div class="form-group">
								<label for="gender">Gender:</label>
								<select class="form-control" id="gender" name="gender">
								<option value="male">Male</option>
								<option value="female">Female</option>
								</select>
							</div>
							<div class="form-group">
								<label for="occupation">Occupation:</label> <input type="text"
									class="form-control" id="occupation" name="occupation" placeholder="Enter occupation" />
							</div>
							<div class="form-group">
								<label for="password1">Password:</label> <input type="password"
									class="form-control" id="password1" name="password"
									pattern=".{4,}" required placeholder="minimum length 4" />
							</div>
							<div class="form-group">
								<label for="rpassword">Retype Password:</label> <input
									type="password" class="form-control" id="rpassword"
									name="rpassword" pattern=".{4,}" required
									placeholder="minimum length 4" />
							</div>
							<div class="form-group">
								<h3 id="passwordMessage" style="color: red;font: verdana;"></h3>
							</div>
						</DIV>
						<DIV class="modal-footer">
							<input type="submit" class="btn btn-primary" name="signUp" value="Sign Up" />
						</DIV>
					</form>
				</DIV>
			</DIV>
		</DIV>

	</HEADER>
	
	<%
	if(emailId != null){
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Date dt = new Date();
	String td = df.format(dt);
	%>

	<DIV class="bdy container-fluid mt inputForm row1">

		<div class="row">
		<div class="col-sm-4 col-sm-offset-4">
		
			<h3>Write Review: </h3>
						
			<%
	ArrayList<HotelBean> hotels = (ArrayList<HotelBean>)session.getAttribute("hotels");
	int hotelId = Integer.parseInt(request.getParameter("hotelId"));
	HotelBean hotel = new HotelBean();
	for(HotelBean Thotel : hotels) {
		if(Thotel.getHotelId() == hotelId){
			hotel = Thotel;
		}
	}
	
	UserBean user = (UserBean)session.getAttribute("userObj");
	if(user.getFullName() == null)
	{
		
	}
	%>
			<form action="reviews" method="post">
			
			<div class="form-group">
				<label for="hotelName">Hotel Name: </label> <input type="text"
					class="form-control" id="hotelName" name="hotelName" readonly value="<%=hotel.getHotelName() %>"
					/>
			</div>
			
			<div class="form-group">
				<label for="city">Location: </label> <input type="text"
					class="form-control" id="city" name="city" readonly value="<%=hotel.getCity() %>"
					/>
			</div>			
						
			<div class="form-group">
				<label for="emailId">Email ID: </label> <input type="email"
					class="form-control" id="emailId" name="emailId"  readonly value="<%=emailId %>"
					/>
			</div>
			
			<div class="form-group">
				<label for="name">Full Name: </label> <input type="text"
					class="form-control" id="name" name="fullName" readonly value="<%=user.getFullName() %>"
					/>
			</div>
			
			<div class="form-group">
				<label for="age">Age: </label> <input type="number"
					class="form-control" id="age" name="age"  readonly value="<%=user.getAge() %>"
					/>
			</div>	
			
			<div class="form-group">
				<label for="gender">Gender: </label> 
				<input type="text" class="form-control" readonly id="gender" name="gender" value="<%=user.getGender() %>" />
			</div>
			
			<div class="form-group">
				<label for="occupation">Occupation: </label> <input type="text"
					class="form-control" id="occupation" name="occupation" readonly value="<%=user.getOccupation() %>"
					/>
			</div>	
			
			<div class="form-group">
				<label for="reviewDate">Review Date: </label> <input type="date"
					class="form-control" id="reviewDate" name="reviewDate" value="<%=td %>" readonly
					/>
			</div>
						
			<input type="hidden" name="hotelId" value="<%=hotel.getHotelId() %>" />
			
			<div class="form-group">
				<label for="reviewTitle">Review Title: </label> <input type="text"
					class="form-control" id="reviewTitle" name="reviewTitle" placeholder="Enter Review Title"
					/>
			</div>
			
			<div class="ml4 form-group">
			<div class="row">
			<label for="reviewRating">Rating: </label>
			</div>
                <div class="rating row" id="reviewRating">
    <input type="radio" id="star51" name="reviewRating" checked value="5" /><label for="star51" title="Rocks!">&#9733;</label>
    <input type="radio" id="star41" name="reviewRating" value="4" /><label for="star41" title="Pretty good">&#9733;</label>
    <input type="radio" id="star31" name="reviewRating" value="3" /><label for="star31" title="Meh">&#9733;</label>
    <input type="radio" id="star21" name="reviewRating" value="2" /><label for="star21" title="Kinda bad">&#9733;</label>
    <input type="radio" id="star11" name="reviewRating" value="1" /><label for="star11" title="Sucks big time">&#9733;</label>
</div>
</div>
<br/>

			<div class="form-group">
				<label for="reviewText">Review Text: </label> <textarea
					class="form-control" id="reviewText" name="reviewText"></textarea>
			</div>
			
			<input class="btn btn-warning" type="submit" name="submitReview" value="SUBMIT REVIEW" />
	
			</form>
						
			</div>	
		</div>

	</DIV>
	<%} %>

	<FOOTER>

		<DIV class="navbar navbar-static-bottom">

			<DIV class="container">

				<p>
					&copy;2015 <a href="#">www.Hotelpedia.com</a>
				</p>

			</DIV>

		</DIV>

	</FOOTER>

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/javascript.js"></script>

</BODY>
<%
if(emailId == null){
	%>
	<script>
		document.getElementById("signInMessage").innerHTML = "Credentials Don't Match";
		document.getElementById("signInButton").click();
	</script>
<%
}
	if(request.getParameter("type") != null)
	{
		if(request.getParameter("type").equals("login"))
		{
			if(request.getParameter("status").equals("false"))
			{
				%>
					<script>
						document.getElementById("signInMessage").innerHTML = "Credentials Don't Match";
						document.getElementById("signInButton").click();
					</script>
				<% 
			}
		}
		else
		{
			if(request.getParameter("status").equals("false"))
			{
				%>
					<script>
						document.getElementById("signUpMessage").innerHTML = "EmailId Already Exists";
						document.getElementById("signUpButton").click();
					</script>
				<% 
			}
		}
	}
	else
	{
		
	}
%>
</HTML>