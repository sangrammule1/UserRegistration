<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 style="color: green;text-align: center">User SignIn</h1>
	<form action="signin">
		<center>
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="text" name="pwd"></td>
				</tr>
				<tr>
					<td style="color: red;">${msg }</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit"></td>
				</tr>
				<tr>
					<td><a href="http://localhost:9898/forgotPwd">Forgot password</a></td>
					<td></td>
					<td><a href="http://localhost:9898/loadForm">Sign Up</a></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>