WEEK-7

add this part in doGet in java program

PrintWriter pw = response.getWriter(); // ✅ Fixed case
        pw.println("Hello world! Welcome to this page");


<-----------------------------WEEK8----------------------------->
package BookServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // DB connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String USER = "root";        // Change if needed
    private static final String PASS = "password";    // Change if needed

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String query = "SELECT * FROM books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            out.println("<html><head><title>Available Books</title></head><body style='font-family: Arial;'>");
            out.println("<h2>Available Books</h2>");
            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr><th>Title</th><th>Author</th><th>Price (₹)</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("title") + "</td>");
                out.println("<td>" + rs.getString("author") + "</td>");
                out.println("<td>" + rs.getDouble("price") + "</td>");
                out.println("</tr>");
            }

            out.println("</table></body></html>");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}



###SQl

CREATE DATABASE bookstore;

USE bookstore;

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

INSERT INTO books (title, author, price) VALUES 
('Introduction to Java', 'James Gosling', 499.99),
('Learning Python', 'Mark Lutz', 699.00),
('Web Development Basics', 'Tim Berners-Lee', 299.50);
