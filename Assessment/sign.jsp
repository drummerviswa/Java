<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
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
			String regno = request.getParameter("regno");
			String username = request.getParameter("email");
			String password = request.getParameter("password");
			if(up.containsKey(username)){
			%>
				<h1>User Already found</h1>
			<%
			}else{
				PreparedStatement p = con.prepareStatement("INSERT INTO student VALUES (?,?,?)");
				p.setString(1,regno);
				p.setString(2, username);
				p.setString(3,password);
				int status = p.executeUpdate();
				if(status>0){
				%>
					<h1>User added successfully</h1>
				<%
				}
				else{
					%>
					<h1>Signup failed</h1>
				<%
				}
			}
		}catch(SQLException e){
			System.out.println("Error: "+e);
		}
	%>
</body>
</html>