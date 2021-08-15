package com.instagramweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.instagramweb.dao.InstagramDAOInterface;
import com.instagramweb.entity.InstagramUser;
import com.instagramweb.utility.DAOFactory;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("pwd");
		
		InstagramUser fu = new InstagramUser();
		fu.setEmail(email);
		fu.setPassword(password);
		
		InstagramDAOInterface fd = DAOFactory.createObjectHibernate();
		boolean status = fd.loginUserDAO(fu);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Login User</title>");
		out.println("</head><body class=\"d-flex h-100 bg-dark text-secondary\">");
		if(status)
		{
			log.info("User login successfull");
			
			ServletContext sc = getServletContext();
			sc.setAttribute("Email", email);
			
			HttpSession hs = request.getSession();
			hs.setAttribute("Email", email);

			hs.setMaxInactiveInterval(5);// Session will destroy after 10 minutes
			//out.println("<font size=5 color=green><b><i>Welcome</i></b></font><hr>");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/success.html");
			rd.include(request, response);
		}
		else
		{
			log.error("Some error occured in user login");
			
			out.println("<font size=5 color=red><b>Login failed, please check name and password.</b></font>");
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			rd.include(request, response);
		}
		out.println("</body></html>");
	}

}
