<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<%
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		String dEmail = "drummerviswa@gmail.com";
		String dPassword = "Viswa@123";
		String result="";
		if(email.equals(dEmail)&&email!=null&&password.equals(dPassword)&&password!=null){
			result="Login Successful";
		}else if(email.equals(dEmail)&&!password.equals(dPassword)){
			result="Password is Incorrect";
		}else{
			result="Login not found";
		}
	%>
		<h1><%= result %></h1>
</body>
</html>