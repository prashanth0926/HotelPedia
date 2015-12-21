<%@ page language="java" 
import="com.mongodb.*"
import="java.net.UnknownHostException"
import="java.util.*"
import="java.text.*"
import="connection.*"
import="beans.RoomBean"
import="java.sql.Array"
import="beans.HotelBean"
import="java.util.ArrayList"
%>

<!DOCTYPE HTML>
<HTML>

<HEAD>

	<META name="viewport" content="width=device-width, initial-scale=1.0" />
	<LINK href = "css/bootstrap.min.css" rel = "stylesheet"></LINK>
	<LINK href = "css/styles.css" rel = "stylesheet"></LINK>
	<link href="css/simple-sidebar.css" rel="stylesheet">
	<link rel="icon" type="image/gif" href="images/H.png" />
	<TITLE>Hotelpedia-Hotels</TITLE>

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

<div class="mt container-fluid bdy">

<div id="wrapper">

		 <a class="cusmt btn btn-default visible-xs hidden-sm hidden-md hidden-lg" href="#filter" data-toggle="modal">
			Filter and Sort
		</a>

		<DIV class="fade modal mt" id="filter" role="dialog">
			<DIV class="modal-dialog">
<DIV class="modal-content">
	<DIV class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<H4 class="text-center">Filter and Sort</H4>
	</DIV>
	<form action="list.jsp">
	<DIV class="modal-body">
           
           <h4 class="bl">Sort by:
        			<small><select name="sortBy" class="bl">
        				<option value="pAsc">Price min to max</option>
        				<option>Price max to min</option>
        				<option>Rating min to max</option>
        				<option>Rating max to min</option>
        			</select>
        		</small></h4>

                  <h4 class="cmt">Filter Hotels By:</h4>
                <ul class="sidebar-nav1"><li>
                    <h5><input type="checkbox" /> Rating</h5>
                    <ul class="sidebar-nav1">
                    	<li class="nm"><div class="rating">
    <input type="radio" id="star51" name="rating" value="5" /><label for="star51" title="Rocks!">&#9733;</label>
    <input type="radio" id="star41" name="rating" value="4" /><label for="star41" title="Pretty good">&#9733;</label>
    <input type="radio" id="star31" name="rating" value="3" /><label for="star31" title="Meh">&#9733;</label>
    <input type="radio" id="star21" name="rating" value="2" /><label for="star21" title="Kinda bad">&#9733;</label>
    <input type="radio" id="star11" name="rating" value="1" /><label for="star11" title="Sucks big time">&#9733;</label>
</div></li>

                    </ul>
                </li>
                <li>
                    <h5><input type="checkbox" /> Price Range</h5><br/>
                    <ul class="sidebar-nav1">
                    	<LI class="nm"><b>$10</b> <input type="range" /><b>$5000</b></LI><br/>
                    	<LI class="nm"><p>per night:</p></LI>
                    </ul>
                </li>
                 <li>
                    <h5 class=""><input type="checkbox" /> Rooms Available</h5><br/>
                    <ul class="sidebar-nav1">
                    	<LI class="nm">Greater than: &nbsp; <select class=""><option>1</option><option>2</option><option>3</option><option>4</option>
                    								<option>5</option><option>6</option><option>7</option><option>8</option><option>9</option>
                    							</select></LI>
                    </ul>
                </li>
            </ul>
        </div>
        <DIV class="modal-footer">
		<input type="submit" class="btn btn-primary" name="sortFilter" value="OK" />
	</DIV>
        </form>
		</div>
	</div>
</div>


        <div id="sidebar-wrapper">

        	<form action="list.jsp">
            <ul class="sidebar-nav">
            	<li class="sidebar-brand">
            	<h4 class="cmt bl tlec"><b>Sort by:</b>
        			<small><select class="bl ml">
        				<option>Price min to max</option>
        				<option>Price max to min</option>
        				<option>Rating min to max</option>
        				<option>Rating max to min</option>
        			</select></small>
        		</h4>
    </li>
                <li class="sidebar-brand tlec">
                    <b>Filter Hotels By:</b>
                </li>
                <li>
                    <h4 class="tlec"><input type="checkbox" />Rating</h4>
                    <ul class="sidebar-nav1">
                       	<li class="nm"><div class="rating">
    <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="Rocks!">&#9733;</label>
    <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="Pretty good">&#9733;</label>
    <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="Meh">&#9733;</label>
    <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="Kinda bad">&#9733;</label>
    <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="Sucks big time">&#9733;</label>
</div></li>

                    </ul>
                </li>
                <li>
                    <h4 class="tlec"><input type="checkbox" /> Price Range</h4>
                    <ul class="sidebar-nav1">
                    	<LI class="nm"><b>$10</b> <input type="range" /><b>$5000</b></LI>
                    	<LI class="nm"><p>per night:</p></LI>
                    </ul>
                </li>
                <li>
                    <h4 class="tlec"><input type="checkbox" /> Rooms Available</h4>
                    <ul class="sidebar-nav1">
                    	<LI class="nm">Greater than: &nbsp; <select class="wid2"><option>1</option><option>2</option><option>3</option><option>4</option>
                    								<option>5</option><option>6</option><option>7</option><option>8</option><option>9</option>
                    							</select></LI>
                    </ul>
                </li>
                <li>
                	<input class="ml btn btn-primary" type="submit" name="sortFilter" value="Sort and Filter">
                </li>
            </ul>
       		</form>
        </div>

        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
        
        <%
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date cidt = df.parse((String)session.getAttribute("checkInDate"));
		Date codt1 = df.parse((String)session.getAttribute("checkOutDate"));
		DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        String cind = df1.format(cidt);
        String codt = df1.format(codt1);
        %>

        	<DIV class="row">
		<DIV class="col-md-6">
			<h4 class="bl">Location: <%=session.getAttribute("cityName") %></h4>
		</DIV>
		<DIV class="col-md-6">
			<h4 class="bl">Dates: <%=cind %> to <%=codt %></h4>
		</DIV>
	</DIV>

<%
int i=0;
ArrayList<HotelBean> hotels = (ArrayList<HotelBean>)session.getAttribute("hotels");
if (hotels == null){
	request.getRequestDispatcher("/").forward(request,response);
}
else if(hotels.size() == 0) {%>
	<h2> No Hotels found </h2>
<% 
} else {
for(HotelBean hotel : hotels) {
	i++;
%>
	<div class="bdy">
	<DIV class="sortList">
	<div class="row">
	<DIV class="col-lg-3 col-md-6 col-sm-12">
		
			<div class="row">
					<div class="col-lg-12">
			<div data-interval="false" id="myCarousel<%=i%>"
										class="carousel slide" data-ride="carousel">

										<!-- Wrapper for slides -->
										<div class="carousel-inner" role="listbox">
										
										<div class="item active">	
												<img class="smallImgSz img-responsive" src="<%=hotel.getImages().get(0)%>"
													alt="hotelpedia">
										</div>
										<%
										for(int j=1;j<(hotel.getImages().size()-1);j++) { 
										%>
										<div class="item">	
												<img class="smallImgSz img-responsive" src="<%=hotel.getImages().get(j)%>"
													alt="hotelpedia">
										</div>
										<%} %>
										
										</div>

										<!-- Left and right controls -->
										<a class="left carousel-control" href="#myCarousel<%=i%>"
											role="button" data-slide="prev"> <span
											class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
											<span class="sr-only">Previous</span>
										</a> <a class="right carousel-control" href="#myCarousel<%=i%>"
											role="button" data-slide="next"> <span
											class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
											<span class="sr-only">Next</span>
										</a>
									</div>
								</div>
							</div>  	
							<!-- end of corousel -->   
		
	</DIV>
	<DIV class="col-lg-3 col-md-4">
		<H1 class="bl"> <%=hotel.getHotelName()%> </H1>
  		<small class="bl"><%=hotel.getCity() %></small>
  		<h3 class="bl"> Rating: <%=hotel.getRating()%>/5.0 </H3>  		
  	</DIV>
  	<DIV CLASS="col-lg-3">

  		<h3>Features: </h3>
  		<% for(int j=1;j<(hotel.getFeatures().size()-1);j++) 
  		{ if(i > 2)
  			break;%>
  		
  			<p class="bl">
								<span class="glyphicon glyphicon-ok" style="color: green"></span>
								<%=hotel.getFeatures().get(j) %>
							</p>
					<%} %>
							<SMALL>and more.</SMALL>

  	</DIV>
	<DIV class="col-lg-3 col-md-4">
		<h5 class="ml"><b>Rooms available: <%=hotel.getTotalRooms() %></b></h5>
		<h3 class="bl"> Price: $<%=hotel.getMinPrice()%> <SMALL class="bl">per night</SMALL></H3>
		<br/><br/>
  		<a class="btn btn-success ml" href="reviews?hotelId=<%=hotel.getHotelId() %>" >SELECT</a>
  	</DIV>
	</div>
	</DIV>
<%
}
}
%>
</DIV>
</div>
</div>
</div>
<FOOTER>

	<DIV class="hidden-xs navbar navbar-static-bottom">

		<DIV class="container">

			<p>&copy;2015 <a href="#">www.Hotelpedia.com</a></p>

		</DIV>

	</DIV>
	
</FOOTER>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>

</BODY>

</HTML>