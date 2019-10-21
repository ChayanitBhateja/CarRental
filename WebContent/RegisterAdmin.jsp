<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Admin</title>
</head>
<body>
	<h3> ${msg} </h3>
	<form action="RegisterAdminServlet" method="get">
		<table>
			<tr>
				<td>
					Name
				</td>
				<td>
					<input type="text" name="adminName" placeholder="John Doe" required="required">
				</td>
			</tr>
			<tr>
				<td>
					Username
				</td>
				<td>
					<input type="text" name="adminusername" placeholder="JohnDoe" required="required">
				</td>
			</tr>
			<tr>
				<td>
					Password
				</td>
				<td>
					<input type="password" name="adminpassword" required="required">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="submit" value="Register">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>