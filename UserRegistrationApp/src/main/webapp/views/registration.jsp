<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration</title>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(
			function(event) {
				$("#datepicker").datepicker({
					dateFormat : "dd/mm/yy"
				});
				$("#email").blur(function() {
					$("#dupEmailId").text(' ');
					var emailId = $("#email").val();
					$.ajax({
						type : "GET",
						url : "validateEmail?email=" + emailId,
						success : function(data) {
							if (data == "DUPLICATE") {
								$("#dupEmailId").text('Duplicate email id')
								$("#email").focus();
							}
						}
					});
				});
				$("#countryId").change(
						function() {
							var countryId = $("#countryId").val();
							$.ajax({
								type : "GET",
								url : "getState?cid=" + countryId,
								success : function(data) {
									$.each(data, function(key, value) {
										$('<option>').val(key).text(value)
												.appendTo("#stateId");
									});
								}
							});
						});
				$("#stateId").change(
						function() {
							var stateId = $("#stateId").val();
							$.ajax({
								type : "GET",
								url : "getCity?sid=" + stateId,
								success : function(data) {
									$.each(data, function(key, value) {
										$('<option>').val(key).text(value)
												.appendTo("#cityId");
									});
								}
							});
						});
			});
</script>

<script type="text/javascript">
	function myFn() {
		var fname = document.reg.fname.value;
		var lname = document.reg.lname.value;
		var email = document.reg.email.value;
		var phno = document.reg.phNo.value;
		var gender = document.reg.gender.value;
		if (fname == "" || fname == null) {
			alert("First Name should not empty");
			return false;
		}else if (lname == "" || lname == null) {
			alert("Last Name should not empty");
			return false;
		}else if (email == "" || email == null) {
			alert("Email should not empty");
			return false;
		}else if (phno == "" || phno == null) {
			alert("Phone Number should not empty");
			return false;
		}else if (gender == "" || gender == null) {
			alert("Gender should not empty");
			return false;
		}
	}
</script>





</head>
<body>
	<h1 style="color: green; text-align: center;">User Registration</h1>
	<form:form name="reg" modelAttribute="userDetails"   onsubmit=" return myFn()">
		<center>
			<table>
				<tr>
					<td>First Name:</td>
					<td><form:input path="fname" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><form:input path="lname" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="email" /></td>
					<td><font color="red"><span id="dupEmailId"></span></font></td>
				</tr>
				<tr>
					<td>Phone No:</td>
					<td><form:input path="phNo" /></td>
				</tr>
				<tr>
					<td>DOB:</td>
					<td><form:input type="text" path="dob" id="datepicker" /></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td>Male<form:radiobutton path="gender" value="Male" />&nbsp;&nbsp;Female<form:radiobutton
							path="gender" value="Female" /></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><form:select path="countryId">
							<form:option value="-" label="-- Select --" />
							<form:options items="${country}" />
						</form:select></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><form:select path="stateId">
							<form:option value=" " label="-- Select --" />
						</form:select></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><form:select path="cityId">
							<form:option value=" " label="-- Select --" />
						</form:select></td>
				</tr>
				<tr>
					<td><input type="reset" name="Reset" /></td>
					<td><input type="submit" name="Register"></td>
				</tr>
			</table>
		</center>
	</form:form>
</body>
</html>