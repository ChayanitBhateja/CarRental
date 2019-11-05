<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome..</title>
</head>
<body>
<% String name = (String)session.getAttribute("name"); 
	String username = (String)session.getAttribute("username");
%>
<h1>Welcome <%= name %>..</h1>
<br>
<form action="IndexServlet" method="get">
	<h3>Raise a Query</h3>	
	<input type="text" name="queryRaised">
	<input type="hidden" name="userName" value= <%= username %>>
	<input type="submit" name="submit" value="submit">
</form>
</body>
</html>