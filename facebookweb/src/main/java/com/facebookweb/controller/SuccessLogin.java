package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuccessLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = getServletContext();
		Object oo = sc.getAttribute("Name");
		
		HttpSession hs = request.getSession();
		Object oo1 = hs.getAttribute("pwd");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><boyd><center>");
		out.println("<font size=5 color=green><b>Welcome "+ oo +", your password is " + oo1 + "</b></font><hr>");
		out.println("<a href=Viewcustomerservelet>View Profile</a><br>");
		out.println("<a href=Deleteprofile>Delete Profile</a><br>");
		out.println("<a href=editprofile.html>Edit Profile</a><br>");
		out.println("</center></body></html>");
	}

}

