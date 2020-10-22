package com.student_crm.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	
    private static final String userName = "springstudent";   
    private static final String password = "SpringStudent@1";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
    private static final String driver = "com.mysql.jdbc.Driver"; //For new version com.mysql.cj.jdbc.Driver
    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			out.println("Establishing The Connection To Database "+jdbcUrl);
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
			out.println("Connection Established Sucesssfully");
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
