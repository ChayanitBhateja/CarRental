<!DOCTYPE html>
<%@page import="entities.Brand"%>
<%@page import="exception.NoVehicleException"%>
<%@page import="entities.Vehicle"%>
<%@page import="java.util.List"%>
<%@page import="operations.IndexOperations"%>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome..</title>
</head>
<body>
<% String name = (String)session.getAttribute("name"); 
	String username = (String)session.getAttribute("username");%>
<h1>Welcome <%= name %>..</h1>
<br>
<table>
	<tr>
		<th>id</th>
		<th>Name</th>
		<th>Number</th>
		<th>Brand</th>
		<th></th>
	</tr>
<%
	IndexOperations op = new IndexOperations();
	List<Vehicle> list=null;
	int i=0;
	try{
	list = op.displayVehicle();
	for(Vehicle vehicle : list){ 		
					out.println("<tr>");
					out.println("<form action='IndexServlet' method='get'>");
					out.println("<td>"+(i+1)+"</td>"); 
					out.println("<td><input type='text' name='vName' value="+vehicle.getName()+"></td>");
					out.println("<td><input type='text' name='vNumber' value="+vehicle.getNumber()+"></td>");
					out.println("<td><input type='text' name='vBrand' value="+vehicle.getBrand().getName()+"></td>");
					out.println("<td><input type='hidden' name='vusername' value="+username+"></td>");
					out.println("<td><input type='submit' name='submitbooking' value='submit'></td>");
					out.println("</form>"); 
					out.println("</tr>");
					i++; 
		}
	}
	catch(NoVehicleException | NullPointerException e){
		out.println("No vehicle found..");
	}
%>
</table>

<hr>
<form action="IndexServlet" method="get">
	<h3>Raise a Query</h3>	
	<input type="text" name="queryRaised">
	<input type="hidden" name="userName" value=<%= username %>>
	<input type="submit" name="submit" value="submit">
</form>
</body>
</html>