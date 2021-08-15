package com.instagramweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.instagramweb.dao.InstagramDAOInterface;
import com.instagramweb.entity.InstagramUser;
import com.instagramweb.utility.DAOFactory;

/**
 * Servlet implementation class CheckMailServlet
 */
public class CheckMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		InstagramUser iu = new InstagramUser();
		iu.setEmail(email);
		InstagramDAOInterface fd = DAOFactory.createObjectHibernate();
		  
		boolean result = fd.checkEmailDAO(iu);
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		out.println("<html><body><center><br><br>");
		if(result) {
			out.println("Valid mail");
		}
		else {
			  out.println("Email already exist");
		}
		out.println("</center></body></html>");
	}

}
