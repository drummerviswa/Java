<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculate Age</title>
</head>
<body>
<form action="age.jsp" method="post">
        <label>DOB</label>
        <input type="date" name="dob" />
        <input type="submit" value="submit">
    </form>
    <% 
        String dobStr = request.getParameter("dob");
        if (dobStr != null && !dobStr.isEmpty()) {
            try {
                java.util.Date birthDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
                java.util.Date now = new java.util.Date();

                long diff = now.getTime() - birthDate.getTime();
                long seconds = diff / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;
                long years = days / 365;
                long months = (long) (years * 12 + ((now.getMonth() + 1) - (birthDate.getMonth() + 1)));
                days = (long) (diff / (1000 * 60 * 60 * 24) % 30.4375);

                %>
                <h1>Age: <%= years %> years, <%= months %> months, <%= days %> days, <%= hours %> hours, <%= minutes %> minutes, <%= seconds %> seconds.</h1>
                <% 
            } catch (java.text.ParseException e) {
                out.println("Error parsing date: " + e.getMessage());
            }
        }
    %>
</body>
</html>