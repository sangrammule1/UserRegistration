<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(event) {
		$("#oldPwd").blur(function() {
			$("#pwrd").text(' ');
			var oldPwd = $("#oldPwd").val();
			$.ajax({
				type : "GET",
				url : "validateTempPwd?pwd=" + oldPwd,
				success : function(data) {
					if (data == "NOTMATCH") {
						$("#pwrd").text('please enter valid password')
						$("#oldPwd").focus();
					}
				}
			});
		});
	});
</script>

<script type="text/javascript">
	function myFunction1() {
		var tpwd = document.unlockForm.oldPwd.value;
		var pwd1 = document.unlockForm.newPwd.value;
		var pwd2 = document.unlockForm.confirmPwd.value;

		if (tpwd == "" || tpwd == null || pwd1 == "" || pwd1 == null
				|| pwd2 == "" || pwd2 == null) {
			alert("field should not be empty");
			return false;
		} else if (pwd1 != pwd2) {
			alert("new password and confirm password should match")
			return false;
		}
	}
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2 style="color: green; text-align: center;">Unlock Account</h2>
	<form:form name="unlockForm" modelAttribute="pwd"
		action="changeAccStatus" onsubmit="return myFunction1()">
		<center>
			<table>
				<tr>
					<td>Email Id:</td>
					<td><form:input path="email" readonly="true" /></td>
				</tr>
				<tr>
					<td>Temp Password:</td>
					<td><form:input path="oldPwd" /></td>
					<td><span id="pwrd" style="color: red"></span></td>
				</tr>
				<tr>
					<td>New Password:</td>
					<td><form:input path="newPwd" /></td>
				</tr>
				<tr>
					<td>Confirm Password:</td>
					<td><form:input path="confirmPwd" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="change" id="subBtn"></td>
				</tr>
			</table>
		</center>
	</form:form>
</body>
</html>