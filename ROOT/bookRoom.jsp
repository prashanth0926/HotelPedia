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
<TITLE>Hotelpedia-Booking</TITLE>

</HEAD>

<BODY>
<%
	session.setAttribute("hotelId", Integer.parseInt(request.getParameter("hotelId")));
	session.setAttribute("roomId", Integer.parseInt(request.getParameter("roomId")));
	session.setAttribute("numOfRooms", Integer.parseInt(request.getParameter("r"+request.getParameter("roomId"))));
%>
	<HEADER>

		<DIV class="navbar navbar-inverse navbar-fixed-top">

			<DIV class="container">

					<H1>
						<b><a href="#" class="navbar-brand">HOTELPEDIA</a></b>
					</H1>

			</DIV>
				
		</DIV>
		
	</HEADER>

	<DIV class="img-responsive bdy container-fluid mt2 inputForm row1">

		<div class="row">
			<div class="col-sm-4 col-sm-offset-4">

				<h3>Hotel Name: Congress</h3>
				
				<h3>Room Type: Delux</h3>
					
				<form action="bookRoom">
				
					<div class="form-group">
						<label for="bookingName">Booking Name: </label> <input type="text"
							class="form-control" id="bookingName" name="bookingName" required
							placeholder="Enter Booking Name" />
					</div>
					
					<div class="form-group">
						<label for="paymeth">Method of payment: </label> 
						<select class="form-control" id="paymeth" name="paymentMethod">
						<option value="credit">Credit</option>
						<option value="debit">Debit</option>
						</select>
					</div>

					<div class="form-group">
						<label for="cardid">Card Number: </label> <input type="number"
							class="form-control" id="cardid" name="cardNumber" required
							placeholder="Enter Card Number" />
					</div>

					<div class="form-group">
						<label for="cvv">CVV: </label> <input type="number"
							class="form-control" id="cvv" name="cvv" required
							placeholder="Enter CVV" />
					</div>

					<div class="form-group">
						<label for="expdt">Expiration Date: </label> <input type="month"
							class="form-control" id="expdt" name="expirationDate" required 
							/>
					</div>

					<div class="form-group">
						<label for="namecard">Name on Card: </label> <input
							type="text" class="form-control" id="namecard" name="nameOnCard"
							required placeholder="Enter Name on Card" />
					</div>

					<div class="form-group">
						<label for="addrss">Billing Address: </label>
						<input type="text" class="form-control" id="addrss" name="billingAddress" placeholder="Enter Billing Address" />
					</div>
					
					<div class="row"><div class="col-md-8">
					<input class="btn btn-warning" type="submit" name="reserveRoom"
						value="RESERVE" />
						</div>
						<div class="col-md-4">
					<a class="btn btn-warning" href="index.jsp">BACK TO HOME</a>
						</div>
						</div>

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