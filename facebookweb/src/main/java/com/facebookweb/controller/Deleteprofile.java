package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;

public class Deleteprofile extends HttpServlet 
{
private static final long serialVersionUID = 1L;
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{

	ServletContext sc = getServletContext();
	Object oo = sc.getAttribute("Name");
	FacebookUser fr=new FacebookUser();
	fr.setName(oo.toString());
	FacebookDAOInterface fd = DAOFactory.createObject();
	 int m=fd.deleteProfileDAO(fr);
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	out.println("<html><body><center>");
	if(m>0)
	{
		out.println("<font size=5 color=red><b>FINALLY PROFILE DELETED!!!</b></font>");
		
	}
	else
	{
		out.println("<font size=5 color=red><b>something wrong, try again!!!</b></font>");
		
	}
}
}