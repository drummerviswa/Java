package music;

import java.io.IOException;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MusicServletApp
 */
@WebServlet("/MusicServletApp")
public class MusicServletApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicServletApp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicdb", "root", "21679");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM tracks");

                out.println("<h2>Music Tracks</h2><table border='1'><tr><th>ID</th><th>Name</th><th>Artist</th></tr>");
                while(rs.next()) {
                    out.println("<tr><td>" + rs.getInt("id") + "</td><td>" +
                                rs.getString("name") + "</td><td>" +
                                rs.getString("artist") + "</td></tr>");
                }
                out.println("</table>");
                
                con.close();
            } catch(Exception e) {
                out.println("Error: " + e.getMessage());
            }
        }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
