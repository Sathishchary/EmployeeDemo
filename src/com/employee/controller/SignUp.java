package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.dao.DbConnection;


public class SignUp extends HttpServlet {
 private static final long serialVersionUID = 1L;
 
   public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	   response.setContentType("text/html");
	  
	  try {
          // Initialize the database
          Connection con = DbConnection.initializeDatabase();

          // Create a SQL query to insert data into demo table
          PreparedStatement st = con
                 .prepareStatement("insert into user_profile values(?, ?, ?, ?, ? ,?)");

          //SELECT count( * ) as  total_record FROM student
          st.setString(1, request.getParameter("username"));
          st.setString(2, request.getParameter("password"));
          st.setString(3, request.getParameter("email_Id"));
          st.setString(4, request.getParameter("phone_no"));
          st.setString(5, request.getParameter("designation")); 
          st.setString(6, request.getParameter("address"));
          
          st.setInt(1, Integer.valueOf(request.getParameter("id")));

          // Same for second parameter
          st.setString(2, request.getParameter("string"));

          // Execute the insert command using executeUpdate()
          // to make changes in database
          st.executeUpdate();

          // Close all the connections
          st.close();
          con.close();

          // Get a writer pointer 
          // to display the successful result
          PrintWriter out = response.getWriter();
          out.println("<html><body><b>Successfully Signed UP" + "</b></body></html>");
      }
      catch (Exception e) {
          e.printStackTrace();
      }
	  }

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
