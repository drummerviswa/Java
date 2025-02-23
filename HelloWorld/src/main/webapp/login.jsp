<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<form action="lCheck.jsp" method="post">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username" required="required"><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required="required"><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>