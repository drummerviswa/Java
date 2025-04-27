package com.example.servlet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/SaxParserServlet")
public class SaxParserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            File xmlFile = new File(getServletContext().getRealPath("/WEB-INF/components.xml"));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ComponentHandler handler = new ComponentHandler(out);
            saxParser.parse(xmlFile, handler);

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    private static class ComponentHandler extends DefaultHandler {
        private PrintWriter out;
        private StringBuilder elementValue;
        private boolean inComponent;
        private String id, name, type, price;

        public ComponentHandler(PrintWriter out) {
            this.out = out;
            this.elementValue = new StringBuilder();
            this.inComponent = false;
             out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title>Computer Components (SAX)</title>");
            out.println("    <style>");
            out.println("        table { margin: 20px auto; border-collapse: collapse; width: 80%; }");
            out.println("        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("        th { background-color: #f0f0f0; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <h1>Computer Components (SAX Parser)</h1>");
            out.println("    <table>");
            out.println("        <tr><th>ID</th><th>Name</th><th>Type</th><th>Price</th></tr>");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            elementValue.setLength(0);
            if (qName.equalsIgnoreCase("component")) {
                inComponent = true;
                id = "";
                name = "";
                type = "";
                price = "";
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            elementValue.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("id")) {
                id = elementValue.toString();
            } else if (qName.equalsIgnoreCase("name")) {
                name = elementValue.toString();
            } else if (qName.equalsIgnoreCase("type")) {
                type = elementValue.toString();
            } else if (qName.equalsIgnoreCase("price")) {
                price = elementValue.toString();
            }  else if (qName.equalsIgnoreCase("component")) {
                if (inComponent) {
                    out.println("        <tr>");
                    out.println("            <td>" + id + "</td>");
                    out.println("            <td>" + name + "</td>");
                    out.println("            <td>" + type + "</td>");
                    out.println("            <td>" + price + "</td>");
                    out.println("        </tr>");
                    inComponent = false;
                }
            }
        }

        @Override
        public void endDocument() throws SAXException {
             out.println("    </table>");
             out.println("</body>");
             out.println("</html>");
        }
    }
}

