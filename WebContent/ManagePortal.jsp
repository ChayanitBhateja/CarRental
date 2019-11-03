<%@page import="exception.NoBrandAvailableException"%>
<%@page import="operations.IndexAdminOperations"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Brand"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Manage Portal</title>
</head>
<body>
	<h2>Manage Portal</h2>
	<form action="ManagePortal.jsp" method="get"> 
		<p>Edit Options:</p>
		<select name="edit">
		<option value="">Choose Value</option>
		<option value="brand">brand</option>
		<option value="Vehicle">Vehicle</option>
		</select>
		<button type="submit" name="submitedit" value="submit"></button>
	 </form>
	<% String option = request.getParameter("edit");
		IndexAdminOperations op = new IndexAdminOperations();
		if(option==null){
		}
		else if (option.equals("brand")) { %>
			<form action="ManagePortal.jsp" method="get">
			<select name="brands">
						<%	List<Brand> listBrand = new ArrayList<>();
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
					<button type="button" onclick="ManagePortal.jsp"></button>
			</form>
			<% String brands=request.getParameter("brands");
			%>
			<form action="ManagePortal.jsp" method="get">	
			Change BrandName to:
			<input type="text" name="brandname" value= "<%= brands %>" >
			<button type="submit" value="submit" name="submitupdate"></button>
			</form>
			<%
				String brandname = request.getParameter("brandname");				
		     	Brand brand = new Brand();
				brand.setName(brandname);
				op.editBrand(brand, brands);			
			%>

	<%
		}
	%>	
	<!-- <form action="ManageServlet" method="get"> 
		<p>Delete Options:</p>
		<select name="delete">
		<option value="brand">brand</option>
		<option value="Vehicle">Vehicle</option>
		</select>
		<button type="submit" name="submitdelete" value="submit"></button>
	</form>
	-->
</body>
</html>