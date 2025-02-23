<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Current Time</title>
</head>
<body>
	<h1>Time: <%= new java.text.SimpleDateFormat("hh:mm:ss a").format(new java.util.Date()) %></h1>
</body>
</html>