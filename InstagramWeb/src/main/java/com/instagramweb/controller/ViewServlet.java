package com.instagramweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

public class ViewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ViewServlet.class);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = getServletContext();
		Object oo1 = sc.getAttribute("Email");
		
		//HttpSession hs = request.getSession();
		//Object email = hs.getAttribute("Email");
		
		InstagramUser iu = new InstagramUser();
		iu.setEmail(oo1.toString());
		
		InstagramDAOInterface fd = DAOFactory.createObjectHibernate();
		iu = fd.viewProfileDAO(iu);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><title>View User Profile</title>");
		out.println("<meta name=viewport content=width=device-width, initial-scale=1>");
		out.println("<meta http-equiv=Content-Type content=text/html; charset=utf-8/>");
		out.println("<script type=application/x-javascript> addEventListener(load, function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>");
		out.println("<link href=registor.css rel=stylesheet type=text/css media=all />");
		out.println("<link href=//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i rel=stylesheet>");
		out.println("</head><body>");
		out.println("<div class=\"topnav\" style=\"padding-left:40px\">");
		out.println("<a href=\"index.jsp\">Home</a>");
		out.println("<a href=\"success.html\">Dashboard</a>");
		out.println("<a class=\"active\" href=\"ViewServlet\">View Profile</a>");
		out.println("<a href=\"EditServlet\">Edit Profile</a>");
		out.println("</div>");
		out.println("<div class=main-w3layouts wrapper>");
		out.println("<h1>User Profile</h1>");
		out.println("<div class=main-agileinfo>");
		out.println("<div class=agileits-top>");
		out.println("<div style=text-align: left;>");
		if(iu != null)
		{
			log.info("View Profile Successfull");
			
			out.println("<table>");
			out.println("<tr><td>Name</td><td><p>"+ iu.getName() +"</p></td></tr>");
			out.println("<tr><td>Email</td><td><p>"+ iu.getEmail() +"</p></td></tr>");
			out.println("<tr><td>Password</td><td><p>"+ iu.getPassword() +"</p></td></tr>");
			out.println("<tr><td>Address</td><td><p>"+ iu.getAddress() +"</p></td></tr>");
			out.println("</table>");
		}
		else
		{
			log.error("Some error occured in view profile");
			
			out.println("<font sizze=5 color=red><b><i>Failed to get user data, try after some time...!!!</i></b></font>");
		}
		out.println("<br><br><p><a href=success.html class=text style=color: black;>Go to main page</a></p>");
		out.println("</div></div></div></div>");
		out.println("</body></html>");
	}

}
