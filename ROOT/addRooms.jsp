<%@ page language="java" import="com.mongodb.*"
	import="java.net.UnknownHostException" import="java.util.*"
	import="java.text.*" import="connection.*"%>


<!DOCTYPE HTML>
<HTML>

<HEAD>

<META name="viewport" content="width=device-width, initial-scale=1.0" />
<LINK href="css/bootstrap.min.css" rel="stylesheet"></LINK>
<LINK href="css/styles.css" rel="stylesheet"></LINK>
<link rel="icon" type="image/gif" href="images/H.png" />
<TITLE>Admin-Add Room</TITLE>

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
DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
Date dt = new Date();
String td = df.format(dt);
Calendar c = Calendar.getInstance();
c.setTime(dt);
c.add(Calendar.DATE, 365);
dt = c.getTime();
String cod = df.format(dt);
String hotelName =(String) session.getAttribute("hotelName");
%>
	<DIV class="img-responsive bdy container-fluid mt inputForm row1">

		<div class="row">
			<div class="col-sm-4 col-sm-offset-4">

				<h3>
					Add Rooms for Hotel:
					<%=hotelName %></h3>

				<form action="addRooms" method="post" enctype="multipart/form-data">
					<%
						int roomCount = Integer.parseInt(session.getAttribute("typeCount").toString());
						int featureCount = Integer.parseInt(session.getAttribute("featureCount").toString());
						for (int i = 1; i <= roomCount; i++) {
					%>

					<div class="form-group cmt">
						<label for="roomType">Room Type: </label> <input type="text"
							class="form-control" id="roomType" name="roomType<%=i%>" required
							placeholder="Enter Room Type" />
					</div>

					<div class="form-group">
						<label for="price">Price per night:</label> <input type="number"
							class="form-control" id="price" name="roomPrice<%=i%>" required
							placeholder="Enter price" />
					</div>

					<div class="form-group">
						<label for="rooms1">Rooms Available: </label> <input type="number"
							class="form-control" id="rooms1" name="roomsNum<%=i%>" required
							placeholder="Enter rooms available" />
					</div>

					<div class="form-group">
						<label for="moveinDate">Available Move in Date: </label> <input
							type="date" class="form-control" id="moveinDate"
							name="startDate<%=i%>" required placeholder="Enter Move in date" value=<%=td %> min=<%=td %> />
					</div>

					<div class="form-group">
						<label for="moveoutDate">End Date: </label> <input type="date"
							class="form-control" id="moveoutDate" name="endDate<%=i%>"
							placeholder="Enter end date" required value=<%=cod %> min=<%=td %> />
					</div>

					<div class="form-group">
						<label for="features">Features: </label>
						<div id="features">
							<%
								for (int j = 1; j <= featureCount; j++) {
										String feature = (String) session.getAttribute("f" + j);
							%>
							<div class="row">
								<div class="col-sm-1">
									<input class="chbxw" type="checkbox" name="<%=i%>f<%=j%>status"
										id="<%=i%>f<%=j%>status" />
								</div>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="<%=i%>f<%=j%>name"
										name="<%=i%>f<%=j%>name" value="<%=feature%>" />
								</div>
							</div>
							<%
								}
							%>
						</div>
					</div>

					<div class="form-group">
						<label for="pics1">Upload Room Images: </label> <input type="file"
							accept='image/*' class="form-control" id="pics1"
							name="images<%=i %>" multiple required /><br />
					</div>

					<%
						}
					%>

					<input class="btn btn-warning" type="submit" name="addRoom"
						value="ADD ROOMS"/>

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

</BODY>

</HTML>