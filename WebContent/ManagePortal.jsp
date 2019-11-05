<!DOCTYPE html>
<%@page import="entities.Vehicle"%>
<%@page import="operations.IndexAdminOperations"%>
<%@page import="exception.NoBrandAvailableException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Brand"%>
<html>
<head>
	<title>Edit Portal</title>
</head>
<body>
	<h1>Edit Portal</h1>
	<a href="IndexAdmin.jsp">Go back to Admin Portal here..</a>
	<br>
	<b>Edit Brand</b>
	<% IndexAdminOperations op = new IndexAdminOperations(); %>
	<form action="ManagePortal.jsp" method="get">
		<select name="brands">
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
						<p>Change to Brand Name: </p>
						<input type="text" name="brandname">
						<input type="submit" name="submit" value="submit">
	</form>
						<% 
							String oldName = request.getParameter("brands");
						   	String newName = request.getParameter("brandname");
						   	if (oldName==null || newName==null) {}
						   	else{
						   		Brand brand = new Brand();
						   		brand.setName(newName);
						   		op.editBrand(brand,oldName);
						   	}
						%>

	<form action="ManagePortal.jsp" method="get">
						<b>Delete Brand: </b>
		<select name="branddel">
						<%		
							List<Brand> listBrand1 = new ArrayList<>();
							try{
							listBrand1 = op.displayBrand();
							}
						catch(NoBrandAvailableException e){
							out.println("<option value=''</option>");
						}
							for (Brand brand : listBrand) {
								out.println("<option value="+brand.getName()+">"+ brand.getName() +"</option>");		
							}
						%>
					</select>
						<input type="submit" name="submitdel" value="submit">
	</form>
						<% 
							String selectedBrand = request.getParameter("branddel");
							String submitbtn = request.getParameter("submitdel");
						   	if (selectedBrand==null || submitbtn==null) {}
						   	else{						   	
						   		op.deleteBrand(selectedBrand);
						   	}
						%>
						
	<hr>
	<form action="ManagePortal.jsp" method="get">
		<b>Edit Vehicle</b>
		<input type="text" name="number">
		<input type="submit" name="submitVupdate" value="submit">
	</form>
		<% String number1 = request.getParameter("number");
			String submitbtn1 = request.getParameter("submitVupdate");
			if(submitbtn1!=null && number1!=null){
			Vehicle vehicle = op.displayVehicleByNumber(number1);%>
			<form action="ManagePortal.jsp" method="get">
				Number
				<input type="text" name="number2" value= <%= vehicle.getNumber() %> >
				Name
				<input type="text" name="name" value= <%= vehicle.getName() %>>
				<input type="submit" name="submitVehUpdate" value="Submit">
			</form>
		<%}
				String number2 = request.getParameter("number2");
				String name2 = request.getParameter("name2");
				String submitVehBtn = request.getParameter("submitVehUpdate");
				if(number2!=null && name2!=null && submitVehBtn!=null){
					 Vehicle vehicle = new Vehicle();
					 vehicle.setNumber(number2);
					 vehicle.setName(name2);
					 int flag=op.editVehicle(vehicle,number1);
					 if(flag==1){
						 out.println("<h3>Vehicle details Updated successfully</h3>");
					 }
					 else{
						 out.println("<h3>Cant update vehicle details..</h3>");
					 }
				}
		%>


	<form action="ManagePortal.jsp" method="get">
						<b>Delete Vehicle: </b>
						<input type="text" name="number">
						<input type="submit" name="submitVdel" value="submit">
	</form>
						<%
							String selectedVehicle = request.getParameter("number");
							String submitVbtn = request.getParameter("submitVdel");
							int flag=0;
							if( submitVbtn!=null){
							if (selectedVehicle==null) {}
						   	else{						   	
						   		flag=op.deleteVehicle(selectedVehicle);
						   	}
						   	if(flag==1){
						   		out.println("<h3>Vehicle deleted Successfully</h3>");
						   	}
						   	else{
						   		out.println("<h3>No vehicle found...</h3>");
						   	}
							}
						%>
			

</body>
</html>