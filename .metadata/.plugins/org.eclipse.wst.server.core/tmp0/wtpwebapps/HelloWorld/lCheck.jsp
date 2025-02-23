<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Check</title>
</head>
<body>
	<% 
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

       boolean bFlab =false;
       if(username!=null&&username!=""&&"Viswa".equalsIgnoreCase(username)&&password!=null&&password!=""&&"GoodBye@123".equals(password)){
    	   bFlab=true;
       }
    %>
	<%
	if(bFlab){
	%>
    <h1 style="color:green">Login Successful</h1>
    <p style="color:green">Username: <%= username %></p>
	<%}else{ %>
	<h1 style="color:red">Login Failed</h1>
    <p style="color:red">Username: <%= username %></p>
    <%} %>
</body>
</html>