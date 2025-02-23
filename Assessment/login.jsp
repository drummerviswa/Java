<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Map<String,String> up = new HashMap<String,String>();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","21679");
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery("select * from student");
			while(r.next()){
				up.put(r.getString("Name"), r.getString("Password"));
			}
			String username = request.getParameter("email");
			String password = request.getParameter("password");
			if(up.containsKey(username)){
				if(password.equals(up.get(username))){
					%>
					<h1>Welcome</h1>
				<%
				}else{
					%>
					<h1>Password Incorrect</h1>
					<%
				}
			}else{
				%>
				<h1>User Not found</h1>
				<%
			}
		}catch(SQLException e){
			System.out.println("Error: "+e);
		}
	%>
</body>
</html>