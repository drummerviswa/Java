package com.example.servers;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/DomParserInstrumentServlet")
public class DomParserInstrumentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            File xmlFile = new File(getServletContext().getRealPath("/WEB-INF/instruments.xml"));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title>Musical Instruments (DOM)</title>");
            out.println("    <style>");
            out.println("        table { margin: 20px auto; border-collapse: collapse; width: 80%; }");
            out.println("        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("        th { background-color: #f0f0f0; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <h1>Musical Instruments (DOM Parser)</h1>");
            out.println("    <table>");
            out.println("        <tr><th>ID</th><th>Name</th><th>Type</th><th>Price</th></tr>");

            NodeList nList = doc.getElementsByTagName("instrument");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    out.println("        <tr>");
                    out.println("            <td>" + eElement.getElementsByTagName("id").item(0).getTextContent() + "</td>");
                    out.println("            <td>" + eElement.getElementsByTagName("name").item(0).getTextContent() + "</td>");
                    out.println("            <td>" + eElement.getElementsByTagName("type").item(0).getTextContent() + "</td>");
                    out.println("            <td>" + eElement.getElementsByTagName("price").item(0).getTextContent() + "</td>");
                    out.println("        </tr>");
                }
            }
            out.println("    </table>");
            out.println("</body>");
            out.println("</html>");

        } catch (ParserConfigurationException | SAXException | IOException e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
