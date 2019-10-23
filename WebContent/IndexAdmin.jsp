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
			<td>
				<% 
					IndexAdminOperations op = new IndexAdminOperations();
					out.println(IndexAdminOperations.countBookings());
				%>
			</td>
		</tr>
		
		
		<tr>
			<th>
				Total Queries
			</th>
			<td>
				<% 
					out.println(op.countQueries());
				%>
			</td>
		</tr>
		
		
		<tr>
			<th>
				Total users
			</th>
			<td>
				<% 
					out.println(op.countUsers());
				%>
			</td>
		</tr>
	</table>

</body>
</html>