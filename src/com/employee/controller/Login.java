package com.employee.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.dao.DbConnection;
@WebServlet("/login")
public class Login extends HttpServlet {

   	private static final long serialVersionUID = 63486395741532640L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		try {
			con = DbConnection.initializeDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

        // Create a SQL query to insert data into demo table
        try {
             ps = con.prepareStatement("select * from user_profile where username=? and password_info=?");
             ps.setString(1, username);
             ps.setString(2, password);
             rs = ps.executeQuery();
             boolean st = rs.next();
             request.getSession().setAttribute("loggedInUser", username);
             if(st) {
            	 response.sendRedirect("dashboard");
             } else {
            	 response.sendRedirect("index");
             }
             DbConnection.closeConnections(rs, ps, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        // request.getSession().setAttribute("user", user);
       // 	response.sendRedirect("/dashboard.html");
         	// request.getRequestDispatcher("dashboard").forward(request, response);
        }
        else {
           username= "";
           password= "";
           request.setAttribute("error", "Unknown user, please try again");
           request.getRequestDispatcher("login").forward(request, response);
        }
    }
}
