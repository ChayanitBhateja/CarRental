<!DOCTYPE html>
<html>
<head>
	<title>Registeration Form</title>
</head>
<body>
	<h3>${msg}</h3>
	<form action="RegisterationServlet" method="get">
		<TABLE>
			<tr>
				<td>
					Name
				</td>
				<td>
					<input type="text" name="name" required="required" placeholder="John Doe">
				</td>
			</tr>

			<tr>
				<td>
					UserName
				</td>
				<td>
					<input type="text" name="username" required="required" placeholder="johndoe">
				</td>
			</tr>

			<tr>
				<td>
					Password
				</td>

				<td>
					<input type="password" name="password" required="required">
				</td>
			</tr>	

			<tr>
				<td>Mobile number</td>
				<td>
					<input type="text" name="mobileno" required="required" placeholder="10 digit mobileno">
				</td>
			</tr>

			<tr>
				<td>Email id</td>
				<td>
					<input type="email" name="email" placeholder="johndoe@example.com">
				</td>
			</tr>

			<tr>
				<td>Aadhar Number</td>
				<td>
					<input type="text" name="aadhar" required="required" placeholder="12 digit aadhar number">
				</td>
			</tr>
			
			<tr>
				<td>License Number</td>
				<td>
					<input type="text" name="license">
				</td>
			</tr>

			<tr>
				<td>
					<input type="submit" name="submit" value="submit">
				</td>
			</tr>
			<tr>
				<td>
					<a href="/CarRental/Login.html">Already a User?</a>
				</td>
			</tr>
		</TABLE>
	</form>
</body>
</html>