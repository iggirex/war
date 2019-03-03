package com.iggirex.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// setup connection variables (username, password)
		String user = "warplayer";
		String pass = "warplayer";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/war_schema?useSSL=false&serverTimezone=UTC";
		
		// get a connection to db
		String driver = "com.mysql.jdbc.Driver";
		
		try {
		
			PrintWriter out = response.getWriter();
			
			out.println("Connecting to database: " + jdbcUrl);
			
			Class.forName(driver);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			out.println("SUCCESS!!!!");
			
			myConn.close();
			
		} catch(Exception exc) {
			exc.printStackTrace();
			throw new ServletException(exc);
		}
		
	}

}