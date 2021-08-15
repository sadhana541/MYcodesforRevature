package com.facebookweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.facebookweb.entity.FacebookUser;

public class FacebookDAO implements FacebookDAOInterface {

	private static Connection GetConnection()
	{
		Connection con;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/training";
			con = DriverManager.getConnection(url,"root", "geeta123");
			return con;
		}
		catch(Exception ex)
		{
			
		}
		return null;
	}
	
	public int createProfileDAO(FacebookUser fu) {
		// TODO Auto-generated method stub
		int i = 0;
		try
		{
			
			String query = "insert into facebookuser values (?, ?, ?, ?)";
			
			Connection con = GetConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, fu.getName());
			ps.setString(2, fu.getPassword());
			ps.setString(3, fu.getEmail());
			ps.setString(4, fu.getAddress());
			
			i = ps.executeUpdate();
		}
		catch(Exception ex)
		{
			
		}
		return i;
	}

	public boolean loginUserDAO(String name, String password) {
		// TODO Auto-generated method stub
		boolean status = false;
		
		try
		{
			String query = "select * from facebookuser where name=? and password=?";
			
			Connection con = GetConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				status = true;
			}
		}
		catch(Exception ex)
		{
			
		}
		return status;
	}
	
	public FacebookUser viewProfile(FacebookUser h) {
		FacebookUser iu=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/training","root","geeta123");
			
			PreparedStatement ps=con.prepareStatement("select * from FacebookUser where name=? and password=?");
			ps.setString(1, h.getName());
			ps.setString(2,h.getPassword());
						
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				iu=new FacebookUser();
				iu.setName(res.getString(1));
				iu.setPassword(res.getString(2));
				iu.setEmail(res.getString(3));
				iu.setAddress(res.getString(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 
		return iu;
	}

	public int deleteProfileDAO(FacebookUser iu) {
		
			int i=0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/training","root","geeta123");
				
				PreparedStatement ps=con.prepareStatement("delete from FacebookUser where name=?");
				
				
				ps.setString(1, iu.getName());
				
				i=ps.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return i;
		
	}

	public int editProfileDAO(FacebookUser iu ,String ir) {
		int i=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/training","root","geeta123");
			
			PreparedStatement ps=con.prepareStatement("update FacebookUser set password=?,email=?,address=?,name=? where name=? ");
			
			ps.setString(1, iu.getPassword());
			ps.setString(2, iu.getEmail());
			ps.setString(3, iu.getAddress());
			ps.setString(4, iu.getName());
			ps.setString(5, ir);
			
			
			i=ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}

}
