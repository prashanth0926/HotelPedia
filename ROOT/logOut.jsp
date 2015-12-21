<%
synchronized(session){
session.invalidate();  	
}
request.getRequestDispatcher("index.jsp").forward(request, response);
%>
