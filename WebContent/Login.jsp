<!DOCTYPE html>
<html>
<head>
	<%@ page session="false" %>
	<title>Login Form</title>
</head>
<body>
	<h3> ${msg} </h3> <!-- to display any login error -->
	<form action="LoginServlet" method="get">
		<table>
			<tr>
				<td>
					UserName
				</td>
				<td>
					<input type="text" name="username" placeholder="JohnDoe" required="required">
				</td>
			</tr>

			<tr>
				<td>
					Password
				</td>
				<td>
					<input type="Password" name="password" required="required">
				</td>
			</tr>

			<tr>
				<td>
					<input type="submit" name="login" value="Login">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>	