package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;




public class Editservelet extends HttpServlet 
{
private static final long serialVersionUID = 1L;

	
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		ServletContext sc = getServletContext();
		Object oo = sc.getAttribute("Name");
		
		HttpSession hs = request.getSession();
		Object oo1 = hs.getAttribute("pwd");
		FacebookUser fr=new FacebookUser();
				fr.setName(oo.toString());
				fr.setPassword(oo1.toString());
	
				FacebookDAOInterface fd = DAOFactory.createObject();
				FacebookUser fg= fd.viewProfile(fr);
				
				String s=fg.getEmail();
				String a=fg.getAddress();
				String n=fg.getName();
				
		
		String name = request.getParameter("user_name");
		String password = request.getParameter("user_password");
		String email = request.getParameter("user_email");
		String address = request.getParameter("user_address");
		
		FacebookUser fu = new FacebookUser();
		fu.setName(name);
		fu.setPassword(password);
		fu.setEmail(email);
		fu.setAddress(address);
		
	    int ff= fd.editProfileDAO(fu,n);
	    
	    response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body><center><br><br>");
		if(ff>0)
		{
			out.println("<div style=padding:10px><font size=8 color=blue><b>PROFILE CHANGED <b</font><br><div>");
		}
		else
		{
		out.println("<div style=padding:10px><font size=8 color=blue><b>COULD NOT EDIT PROFILE<b</font><br><div>");
	    }
		

		
out.println("</center></body></html>");
}
		

}

