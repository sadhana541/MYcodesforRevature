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

public class EditServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(EditServlet.class);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = getServletContext();
		Object oo1 = sc.getAttribute("Email");
		
		HttpSession hs = request.getSession();
		//Object oo = hs.getAttribute("Email");
		
		InstagramUser iu = new InstagramUser();
		iu.setEmail(oo1.toString());
		
		InstagramDAOInterface fd = DAOFactory.createObjectHibernate();
		iu = fd.viewProfileDAO(iu);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><title>Edit User Profile</title>");
		out.println("<meta name=viewport content=width=device-width, initial-scale=1>");
		out.println("<meta http-equiv=Content-Type content=text/html; charset=utf-8/>");
		out.println("<script type=application/x-javascript> addEventListener(load, function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>");
		out.println("<link href=registor.css rel=stylesheet type=text/css media=all />");
		out.println("<link href=//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i rel=stylesheet>");
		out.println("</head><body>");
		out.println("<div class=\"topnav\" style=\"padding-left:40px\">");
		out.println("<a href=\"index.jsp\">Home</a>");
		out.println("<a href=\"success.html\">Dashboard</a>");
		out.println("<a href=\"ViewServlet\">View Profile</a>");
		out.println("<a class=\"active\" href=\"EditServlet\">Edit Profile</a>");
		out.println("</div>");
		out.println("<div class=main-w3layouts wrapper>");
		out.println("<h1>Edit User Profile</h1>");
		out.println("<div class=main-agileinfo>");
		out.println("<div class=agileits-top>");
		out.println("<div style=text-align: left;>");
		if(iu != null)
		{
			log.info("Profile Edit successfull");
			
			out.println("<form action=ProfileUpdateServlet method=post>");
			out.println("<div style=\"margin-top: 20px;\"><span>Name</span></div>");
			out.println("<input type=\"text\" name=\"name\" id=\"name\" placeholder=\"Username\" required value="+iu.getName()+" class=\"text\">");
			out.println("<div style=\"margin-top: 20px;\"><span>Password</span></div>");
			out.println("<input class=\"text\" type=\"password\" name=\"pwd\" id=\"pwd\" placeholder=\"Password\"  value="+iu.getPassword()+" required>");
			out.println("<div style=\"margin-top: 20px;\"><span>Address</span></div>");
			out.println("<input class=\"text\" type=\"text\" name=\"address\" id=\"address\" placeholder=\"Address\" value="+iu.getAddress()+" required>");
			out.println("<input type=\"submit\" value=\"SAVE\">");
			out.println("</form></div>");
		}
		else
		{
			log.error("Some error occured during profile edit");
			
			out.println("<font sizze=5 color=red><b><i>Failed to get user data, try after some time...!!!</i></b></font>");
		}
		out.println("<p><a href=\"success.html\" class=text style=\"color: black;\">Go to main page</a></p>");
		out.println("</div></div></div></div>");
		out.println("</body></html>");
	}

}

