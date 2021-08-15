package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;


public class Viewcustomerservelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		Object oo = sc.getAttribute("Name");
		
		HttpSession hs = request.getSession();
		Object oo1 = hs.getAttribute("pwd");
		FacebookUser fr=new FacebookUser();
				fr.setName(oo.toString());
				fr.setPassword(oo1.toString());
	
		FacebookDAOInterface fd = DAOFactory.createObject();
		FacebookUser fg= fd.viewProfile(fr);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String s=fg.getEmail();
		String a=fg.getAddress();
		String n=fg.getName();
		
		out.println("<html><body><center>");
		if(fg!=null)
		
		{
			out.println("<div style=padding:10px><font size=8 color=blue><b>DETAILS OF    "+ oo + "<b</font><br><div>");
		
				out.println("<font size =5 color =red <b>NAME -    "+n+"<b></font><br>");
				
				out.println("<font size =5 color =red <b>EMAIL-    "+s+"<b></font><br>");
				
				out.println("<font size =5 color =red <b>ADDRESS-   "+a+"<b></font><br>");
			
		}
				
			else 
			{
				out.println("<font size=5 color=red><b>something wrong, try again!!!</b></font>");
				out.println("<font size=5 color=green><b>Welcome    "+ oo +", your password is " + oo1 + "</b></font><hr>");
				
			}
		
		out.println("</center></body></html>");
	}
}


