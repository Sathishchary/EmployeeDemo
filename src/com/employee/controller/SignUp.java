package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.dao.DbConnection;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
 private static final long serialVersionUID = 1L;
 
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	   response.setContentType("text/html");
	   System.out.println("post established");
	  
	  try {
          // Initialize the database
          Connection con = DbConnection.initializeDatabase();
          ResultSet rs = null;
          System.out.println("connection established");
          // Create a SQL query to insert data into demo table
          PreparedStatement st = con
                 .prepareStatement("insert into user_profile values(null , ?, ?, ?, ?, ? ,?)");

          //SELECT count( * ) as  total_record FROM student
          st.setString(1, request.getParameter("username"));
          st.setString(2, request.getParameter("password"));
          st.setString(3, request.getParameter("emailId"));
          st.setString(4, request.getParameter("phoneno"));
          st.setString(5, request.getParameter("designation")); 
          st.setString(6, request.getParameter("address"));
          
          // Execute the insert command using executeUpdate()
          // to make changes in database
          
          int i = st.executeUpdate();
          System.out.println("query established");
          if (i > 0) {
              System.out.println("success");
              response.sendRedirect("index?userCreat=successfully created the user");
          } else {
              System.out.println("stuck somewhere");
          }
          DbConnection.closeConnections(rs, st, con);
          
      }
      catch (Exception e) {
          e.printStackTrace();
      }
	  }

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    RequestDispatcher view = req.getRequestDispatcher("signup.html");
	    try {
			view.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
