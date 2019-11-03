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
</body>
</html>