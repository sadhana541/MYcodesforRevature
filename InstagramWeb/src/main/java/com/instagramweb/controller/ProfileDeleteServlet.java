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
import com.instagramweb.exception.BusinessException;
import com.instagramweb.utility.DAOFactory;

public class ProfileDeleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ProfileDeleteServlet.class);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = getServletContext();
		Object oo1 = sc.getAttribute("Email");
		
		HttpSession hs = request.getSession();
		Object oo = hs.getAttribute("Email");
		
		InstagramUser iu = new InstagramUser();
		iu.setEmail(oo1.toString());
		
		InstagramDAOInterface fd = DAOFactory.createObjectHibernate();
		boolean status = fd.deleteProfileDAO(iu);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Delete Profile</title>");
		out.println("</head><body>");
		if(status)
		{
			log.info("Profile delete successfull");
			
			out.println("<script>alert(\"Profile Deleted Successfully.\")</script>");
		
			//out.println("Profile Deleted, create new profile or login.");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.include(request, response);
			
		}
		else
		{
			log.error("Some error occured during profile delete");
			
			out.println("<font size=5 color=red><b><i></i></b></font>");
		}
		out.println("</body></html>");
	}

}
