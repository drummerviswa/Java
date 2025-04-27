<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*, java.util.*" %>
<%
    String DB_URL = "jdbc:mysql://localhost:3306/musical_instruments_db?allowPublicKeyRetrieval=true&useSSL=false";
    String DB_USER = "root";
    String DB_PASSWORD = "21679";

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

    String action = request.getParameter("action");
    if (action == null) {
        action = "list";
    }

    if (action.equals("create")) {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        String sql = "INSERT INTO instruments (name, type, price) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, type);
        pstmt.setDouble(3, price);
        pstmt.executeUpdate();
        pstmt.close();
        response.sendRedirect("index.jsp");
        return;
    }

    if (action.equals("update")) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        String sql = "UPDATE instruments SET name = ?, type = ?, price = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, type);
        pstmt.setDouble(3, price);
        pstmt.setInt(4, id);
        pstmt.executeUpdate();
        pstmt.close();
        response.sendRedirect("index.jsp");
        return;
    }

    if (action.equals("delete")) {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "DELETE FROM instruments WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
        response.sendRedirect("index.jsp");
        return;
    }

    List<Map<String, Object>> instruments = new ArrayList<>();
    String sql = "SELECT id, name, type, price FROM instruments";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();
    while (rs.next()) {
        Map<String, Object> instrument = new HashMap<>();
        instrument.put("id", rs.getInt("id"));
        instrument.put("name", rs.getString("name"));
        instrument.put("type", rs.getString("type"));
        instrument.put("price", rs.getDouble("price"));
        instruments.add(instrument);
    }
    rs.close();
    pstmt.close();
    conn.close();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Musical Instruments</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f0f0f0;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        form {
            margin-top: 20px;
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 5px;
        }
        input[type="text"], input[type="number"] {
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            align-self: center;
        }
        button:hover {
            background-color: #45a049;
        }
         .actions {
            display: flex;
            gap: 10px;
        }
       .edit-button {
            background-color: #008CBA;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 12px;
        }

        .edit-button:hover {
            background-color: #007ba7;
        }

        .delete-button {
            background-color: #f44336;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 12px;
        }

        .delete-button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Musical Instruments</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <% for (Map<String, Object> instrument : instruments) { %>
            <tr>
                <td><%= instrument.get("id") %></td>
                <td><%= instrument.get("name") %></td>
                <td><%= instrument.get("type") %></td>
                <td>$<%= instrument.get("price") %></td>
                <td class="actions">
                    <button class="edit-button" onclick="location.href='edit.jsp?id=<%= instrument.get("id") %>'">Edit</button>
                    <button class="delete-button" onclick="location.href='index.jsp?action=delete&id=<%= instrument.get("id") %>'">Delete</button>
                </td>
            </tr>
            <% } %>
        </table>
        <h2>Add Instrument</h2>
        <form action="index.jsp" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="type">Type:</label>
            <input type="text" id="type" name="type" required>
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" required>
            <input type="hidden" name="action" value="create">
            <button type="submit">Add Instrument</button>
        </form>
    </div>
</body>
</html>
