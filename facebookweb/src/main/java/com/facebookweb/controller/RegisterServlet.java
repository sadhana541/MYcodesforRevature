package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;


public class RegisterServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		FacebookUser fu = new FacebookUser();
		fu.setName(name);
		fu.setPassword(password);
		fu.setEmail(email);
		fu.setAddress(address);
		
		FacebookDAOInterface fd = DAOFactory.createObject();
		int i = fd.createProfileDAO(fu);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body><center><br><br>");
		if(i > 0)
		{
			out.println("<font size=5 color=blue><b>Registration Success <a href=login.html>Click here</a> to continue.</b></font>");
		}
		else {
			out.println("<font size=5 color=red><b>Registration Failed, try again!!!</b></font>");
		}
		out.println("</center></body></html>");
	}

}
