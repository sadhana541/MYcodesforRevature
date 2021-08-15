package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.utility.DAOFactory;

public class LoginServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		FacebookDAOInterface fd = DAOFactory.createObject();
		boolean status = fd.loginUserDAO(name, password);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><boyd><center>");
		if(status)
		{
			// How one servlet will share attribute with other servlet
			// via container
			ServletContext sc = getServletContext();
			sc.setAttribute("Name", name);
			
			//sc.setAttribute("Hello", "Hello ashish Kunar lcuknwo knit google reravtue");
			
			//If we want share information for limited time, then use session
			// Setting up session with required infromation
			HttpSession hs = request.getSession();
			hs.setAttribute("pwd", password);
			
			// By default session will be valid for 30 minutes
			// Set timing by this
			hs.setMaxInactiveInterval(10);// Session will destroy after 10 minutes
			
			out.println("Inside Login Servlet");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/SuccessLogin");
			rd.include(request, response);
			//forword return another servelet
		}
		else
		{
			out.println("<font size=5 color=red><b>Login failed, please check name and password.</b></font>");
			//Includeing login.html page here using RequestDispatcher
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			rd.include(request, response);
		}
		out.println("</center></body></html>");
	}

}
