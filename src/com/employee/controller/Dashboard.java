package com.employee.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dashboard extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
        // request.getSession().setAttribute("user", user);
//        	PrintWriter out = response.getWriter();
//         	out.write("<html><body>Employee DashBoard</body></html>");
       // 	response.sendRedirect("/dashboard.html");
         	 request.getRequestDispatcher("dashboard").forward(request, response);
        }
        else {
           request.setAttribute("error", "Unknown user, please try again");
           request.getRequestDispatcher("login").forward(request, response);
        }
    }
}
