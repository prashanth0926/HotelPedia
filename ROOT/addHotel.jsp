<%@ page language="java" import="com.mongodb.*"
	import="java.net.UnknownHostException" import="java.util.*"
	import="java.text.*" import="connection.*"
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
<TITLE>Admin-Add Hotel</TITLE>

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

							<LI class=""><a href="adminPage">Home</a></LI>
							<LI class="active"><a href="addHotel.jsp">Add Hotel</a></LI>
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
	
	<%
		String hotelName = "";
		String city = "";
		String zip = "";
		String address = "";
		String url = "";
		String roomTypes = "";
		String hotelDescription = "";
		int i = 0;
		ArrayList<String> features = null;
		ArrayList<String> images = null;
		
	if(request.getParameter("update") != null){
		ArrayList<HotelBean> hotels = (ArrayList<HotelBean>)session.getAttribute("hotels");
		int hotelId = Integer.parseInt(request.getParameter("hotelId"));
		HotelBean hotel = new HotelBean();
		for(HotelBean Thotel : hotels) {
			if(Thotel.getHotelId() == hotelId){
				hotel = Thotel;
			}
		}
		hotelName = "value='"+hotel.getHotelName()+"'";
		city = "value='"+hotel.getCity()+"'";
		zip = "value='"+hotel.getZip()+"'";
		address = "value='"+hotel.getAddress()+"'";
		url = "value='"+hotel.getUrl()+"'";
		roomTypes = "value='"+hotel.getNumberOfRooms().size()+"'";
		hotelDescription = "value='"+hotel.getDescription()+"'";
		features = (ArrayList<String>)hotel.getFeatures();	
		images = (ArrayList<String>)hotel.getImages();
				
	}
	%>

	<DIV class="img-responsive bdy container-fluid mt inputForm row1">

		<div class="row">
			<div class="col-sm-4 col-sm-offset-4">

				<h3>Add a Hotel:</h3>

				<form action="addHotel" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="hotelName">Hotel Name: </label> <input type="text"
							class="form-control" id="hotelName" name="hotelName" required
							placeholder="Enter Hotel Name" <%=hotelName %> />
					</div>

					<div class="form-group">
						<label for="hotelCity">City: </label>
						<div id="hotelCity">
							<input type="text" class="form-control" name="city" id="cityName"
								onkeypress="loadJSON()" list="cityNameList" autocomplete="off"
								required placeholder="Enter City name" <%=city %> />
								<datalist id="cityNameList">
							</datalist>
						</div>
					</div>

					<div class="form-group">
						<label for="hotelZip">Zip Code: </label> <input type="number"
							class="form-control" id="hotelZip" name="zip" required
							placeholder="Enter zip code" <%=zip %> />
					</div>

					<div class="form-group">
						<label for="hotelAddress">Address: </label> <input type="text"
							class="form-control" id="hotelAddress" name="address" required
							placeholder="Enter address" <%=address %> />
					</div>

					<div class="form-group">
						<label for="url">URL: </label> <input type="text"
							class="form-control" id="url" name="url" required
							placeholder="Enter URL" <%=url %> />
					</div>

					<div class="form-group">
						<label for="cancellation">Cancellation Policy: </label> <select
							class="form-control" id="cancellation" name="cancellation">
							<option value="24">24Hrs</option>
							<option value="36">36Hrs</option>
							<option value="48">48Hrs</option>
						</select>
					</div>

					<div class="form-group">
						<label for="rooms">Types of Rooms Available: </label> <input
							type="number" class="form-control" id="rooms" name="typeCount"
							required placeholder="Enter number of room types" <%=roomTypes %> />
					</div>

					<div class="form-group">
						<label for="desc">Hotel Description: </label>
						<textarea class="form-control" id="desc" name="description" <%=hotelDescription %>></textarea>
					</div>

					<div class="form-group">
						<label for="featureDiv">Features: </label>						
						<%
						if(request.getParameter("update") != null){
							for(i=0;i<features.size();i++){
						%>
						<div class='row'><div class='col-sm-10'>
						<input type='text' class='form-control' value="<%=features.get(i) %>" readonly
						<% i++; %>
						name="f<%=i %>" id="f<%=i %>" />
						</div><div class='com-sm-2'>
						<button type='button' class='btn btn-warning' id='b<%=i %>' 
						onclick='removeFeature("<%=i%>")'>
						<span class='glyphicon glyphicon-remove'></span></button></div></div><br/>
						<%} }%>
						
						<div id="featureDiv">
							<input type="text" class="form-control"
								placeholder="Enter feature" name="f1" id="f1" />
						</div>
						<input type="button" onclick='addFeature()' value="ADD FEATURE"
							class="btn btn-warning" /> <input type='hidden' value='0'
							id='featureCount' name='featureCount' />
					</div>


					<div class="form-group">
						<label for="pics">Upload Hotel Images: </label> 
						<input type="file"
							class="form-control" accept="image/*" id="pics" name="images"
							multiple required value="<%=images%>"/>
							<br />
					</div>

					<input class="btn btn-warning" type="submit" name="addHotel"
						value="ADD HOTEL" />

				</form>

			</div>
		</div>

	</DIV>

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
	<script src="js/CityNameAutoFill.js"></script>
</BODY>

</HTML>