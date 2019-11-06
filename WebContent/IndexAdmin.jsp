<%@page import="exception.NoBookingAvailableException"%>
<%@page import="entities.Booking"%>
<%@page import="exception.NoQueryAvailableException"%>
<%@page import="entities.Query"%>
<%@page import="java.util.ArrayList"%>
<%@page import="exception.NoBrandAvailableException"%>
<%@page import="entities.Brand"%>
<%@page import="java.util.List"%>
<%@page import="operations.IndexAdminOperations"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Admin</title>
</head>
<body>
	<table>
		<tr>
			<th>
				Total Bookings
			</th>
			<th>
				Total Queries
			</th>
			<th>
				Total users
			</th>
		</tr>
		<tr>
			
			<td>
				<% 
					IndexAdminOperations op = new IndexAdminOperations();
					List<Long> list = IndexAdminOperations.countQueries();
					out.println(list.get(0));
				%>
			</td>
			<td>
				<% 
				out.println(list.get(1));
				%>
			</td>
			<td>
				<% 
				out.println(list.get(2));
				%>
			</td>
		</tr>	
	</table>
	<hr>
	<h2>Add Brand</h2>
	<form action="IndexAdminServlet" method="get">
		<table>
			<tr>
				<td>Brand Name</td>
				<td>
					<input type="text" name="brandname">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="submitbrand" value="submit">
				</td>
			</tr>
		</table>
	</form>
	<h3>${msgbrand}</h3>
	<hr>
	<h2>Add Vehicle</h2>
	<form action="IndexAdminServlet" method="get">
		<table>
			<tr>
				<td>Vehicle Name</td>
				<td>
					<input type="text" name="name">
				</td>
			</tr>

			<tr>
				<td>Vehicle Number</td>
				<td>
					<input type="text" name="number">
				</td>
			</tr>
			<tr>
				<td>Brand</td>
				<td>
					<select name="cars">
						<%
							List<Brand> listBrand = new ArrayList<>();
							try{
							listBrand = op.displayBrand();
							}
						catch(NoBrandAvailableException e){
							out.println("<option value=''</option>");
						}
							for (Brand brand : listBrand) {
								out.println("<option value="+brand.getName()+">"+ brand.getName() +"</option>");		
							}
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="submitvehicle" value="submit">
				</td>
			</tr>
		</table>
	</form>
	<h3>${msgvehicle}</h3>
	<a href="ManagePortal.jsp">Manage Brand and Vehicles here</a>
	<hr>
	<h3>Manage Bookings</h3>
	<table>
		<tr>
		<th>Serial No.</th>
		<th>Booking id</th>
		<th>Booking status</th>
		<th>booked by</th>
	</tr>
	<% 	
	List<Booking> listBooking=null;
		try{
			listBooking = op.getBookings();
		}
		catch(NoBookingAvailableException e){
			out.println("No pending Bookings available right now..");
		}
		if(listBooking!=null){
		int i=0;
	for(Booking booking : listBooking){ 		
			
					out.println("<tr>");
					out.println("<form action='IndexAdmin.jsp' method='get'>");
					out.println("<td>"+(i+1)+"</td>");
					out.println("<td><input type='text' name='id' value="+booking.getId()+"></td>");
					out.println("<td><input type='text' name='status' value="+booking.getStatus()+"></td>");
					out.println("<td><input type='text' name='user' value="+booking.getUser().getName()+"></td>");
					out.println("<td>"+
					"<input type='submit' name='submitBooking' value='Confirm Booking'>"+
					"<input type='submit' name='cancelBooking' value='Cancel Booking'>"+
					"</td>");
					out.println("</form>"); 
					out.println("</tr>");
					i++; 
		}
		}
	String submitBooking = request.getParameter("submitBooking");
	String cancelBooking = request.getParameter("cancelBooking");
	Booking booking=null;
	if(submitBooking!=null && cancelBooking==null){
		String idd = request.getParameter("id");
		int id = Integer.parseInt(idd);
		try{
	booking = op.getBookingById(id);
		}
		catch(Exception e){
			out.println("can't find booking..");
		}
	int flag = op.updateBookingStatus(booking);
	if(flag==1){
		out.println("Booking Done");
	}
	else{
		out.println("Issue in booking..");	
	}
	}
	else if(submitBooking==null && cancelBooking!=null){
		int id  = Integer.parseInt(request.getParameter("id"));
		Booking booking1 = op.getBookingById(id);
		int flag = op.cancelBookingStatus(booking1);
		if(flag==1){
			out.println("booking canceled!");
		}
		else{
			out.println("Issue in cancelling..");
		}
	}
	%>
	
	</table>
	<hr>
	<h3>Manage Queries</h3>
	<table>
	<tr>
		<th>Serial No.</th>
		<th>Query id</th>
		<th>Status</th>
		<th>Description</th>
		<th>raised By</th>
	</tr>
	<% 	
	List<Query> list1=null;
		try{
			list1 = op.getQueries();
		}
		catch(NoQueryAvailableException e){
			out.println("No queries available right now..");
		}
		if(list1!=null){
		int i11=0;
	for(Query query : list1){ 		
			
					out.println("<tr>");
					out.println("<form action='IndexAdmin.jsp' method='get'>");
					out.println("<td>"+(i11+1)+"</td>");
					out.println("<td><input type='text' name='id' value="+query.getId()+"></td>");
					out.println("<td><input type='text' name='status' value="+query.getStatus()+"></td>");
					out.println("<td><input type='text' name='desc' value="+query.getDescription()+"></td>");
					out.println("<td><input type='text' name='user' value="+query.getUser().getName()+"></td>");
					out.println("<td><input type='submit' name='submitquery' value='Mark Resolved'></td>");
					out.println("</form>"); 
					out.println("</tr>");
					i11++; 
		}
		}
	String submitQuery = request.getParameter("submitquery");
	if(submitQuery!=null){
	int id1=Integer.parseInt(request.getParameter("id"));
	Query query = op.getQueryById(id1);
	int flag = op.updateQueryStatus(query);
	if(flag==1){
		out.println("Query Resolved");
	}
	else{
		out.println("Issue in resolving query");	
	}
	}
	%>
	</table>
</body>
</html>