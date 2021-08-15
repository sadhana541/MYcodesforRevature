package com.instagramweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.instagramweb.dao.InstagramDAOInterface;
import com.instagramweb.entity.InstagramUser;
import com.instagramweb.utility.DAOFactory;

public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(RegisterServlet.class);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("pwd");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		InstagramUser iu = new InstagramUser();
		iu.setName(name);
		iu.setPassword(password);
		iu.setEmail(email);
		iu.setAddress(address);

		InstagramDAOInterface id = DAOFactory.createObjectHibernate();
		int i = id.createProfileDAO(iu);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Register</title>");
		out.println("</head><body>");
		if(i > 0)
		{
			log.info("User registered successfully");
			
			out.println("<font size=5 color=blue><b>Registration Success</b></font>");
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			rd.include(request, response);
		}
		else {
			log.error("Some error in user registration");
			
			out.println("<font size=5 color=red><b><i>Registration Failed, try again!!!</i></b></font>");
		}
		out.println("</body></html>");
	}
}
