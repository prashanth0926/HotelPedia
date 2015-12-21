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
<LINK href="css/bootstrap.min.css" rel="stylesheet"></LINK>
<LINK href="css/styles.css" rel="stylesheet"></LINK>
<link rel="icon" type="image/gif" href="images/H.png" />
<TITLE>Admin-Home</TITLE>

</HEAD>

<BODY>

<HEADER>

		<DIV class="navbar navbar-inverse navbar-fixed-top">

			<b><DIV class="container">

					<H1>
						<a href="#" class="navbar-brand">HOTELPEDIA</a>
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

							<LI class="active"><a href="adminPage">Home</a></LI>
							<LI><a href="addHotel.jsp">Add Hotel</a></LI>
							<LI><a href="#">Data Anaytics</a></LI>

						</UL>

						<UL class="nav navbar-nav navbar-right">

							<LI class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#">Account<b class="caret"></b></a>

								<UL class="dropdown-menu">
									<LI><a href="logOut.jsp">Logout</a></LI>
								</UL></LI>

						</UL>

					</DIV>

				</DIV> </b>
		</DIV>

</HEADER>

<div class="mt container-fluid bdy">

<%
int i=0;
ArrayList<HotelBean> hotels = (ArrayList<HotelBean>)session.getAttribute("hotels");
if (hotels == null){
	request.getRequestDispatcher("adminPage").forward(request,response);
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
	<DIV class="col-lg-2 col-md-4">
		<H1 class="bl"> <%=hotel.getHotelName()%> </H1>
  		<small class="bl"><%=hotel.getCity() %></small>
  		<h3 class="bl"> Rating: <%=hotel.getRating()%>/5.0 </H3>  		
  	</DIV>
  	<DIV CLASS="col-lg-2">

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
	<DIV class="col-lg-3">
		<h3 class="bl cmt"> Price: $<%=hotel.getMinPrice()%> <SMALL class="bl">per night</SMALL></H3>
		<h5 class=""><b>Rooms available: <%=hotel.getTotalRooms() %></b></h5>
  	</DIV>
  	<div class="col-lg-1">
  	<form action="addHotel.jsp">
  	<input type="hidden" name="hotelId" value="<%=hotel.getHotelId() %>" />
  	<input type="submit" class="btn btn-danger cmt" name="update" value="UPDATE" /><br/><br/>
  	</form>
  	<a class="btn btn-danger" href="removeHotel?hotelId=<%=hotel.getHotelId() %>" >REMOVE</a>
  	</div>
	</div>
	</DIV>
<%
}
}
%>
</DIV>
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