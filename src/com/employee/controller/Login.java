package com.employee.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.dao.DbConnection;

public class Login extends HttpServlet {

   	private static final long serialVersionUID = 63486395741532640L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       
        if (username != null && password != null) {
        Connection con = null;
		try {
			con = DbConnection.initializeDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Create a SQL query to insert data into demo table
        try {
			PreparedStatement st = con.prepareStatement("select * from user_profile where password="+password);
			System.out.println(st);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

      
        // request.getSession().setAttribute("user", user);
                	PrintWriter out = response.getWriter();
                	out.write("<html><body>Employee DashBoard</body></html>");
       // 	response.sendRedirect("/dashboard.html");
         	 request.getRequestDispatcher("dashboard").forward(request, response);
        }
        else {
           username= "";
           password= "";
           request.setAttribute("error", "Unknown user, please try again");
           request.getRequestDispatcher("login").forward(request, response);
        }
    }
}
