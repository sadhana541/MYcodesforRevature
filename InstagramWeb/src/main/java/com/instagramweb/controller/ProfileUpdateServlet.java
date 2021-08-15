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

public class ProfileUpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ProfileUpdateServlet.class);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc = getServletContext();
		Object oo1 = sc.getAttribute("Email");
		
		String name = request.getParameter("name");
		String password = request.getParameter("pwd");
		String address = request.getParameter("address");
		
		HttpSession hs = request.getSession();
		Object oo = hs.getAttribute("Email");
		
		System.out.println("From profile update servlet, email : " + oo1.toString());
		
		InstagramUser iu = new InstagramUser();
		iu.setName(name);
		iu.setPassword(password);
		iu.setAddress(address);
		iu.setEmail(oo1.toString());
		
		InstagramDAOInterface id = DAOFactory.createObjectHibernate();
		iu = id.editProfileDAO(iu);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Update Profile</title>");
		out.println("</head><body>");
		if(iu != null)
		{
			log.info("Profile Update successfull");
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/EditServlet");
			rd.include(request, response);
			out.println("<script>alert(\"Profile Updated...!!!\")</script>");
			//out.println("<font size=5 color=blue><b><i>Profile Updated...!!!</i></b></font>");
		}
		else
		{
			log.error("Some error occured during profile updation");
			
			out.println("<font size=5 color=red><b><i>Failed to update user profile, try after sometime...!!!</i></b></font>");
		}
		out.println("</body></html>");
	}

}
