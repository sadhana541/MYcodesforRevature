package com.instagramweb.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.instagramweb.entity.InstagramUser;

public class InstagramDAOHibernate implements InstagramDAOInterface {

	private SessionFactory sf;
	private static Logger log = Logger.getLogger(InstagramDAOHibernate.class);
	
	public InstagramDAOHibernate()
	{
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	public int createProfileDAO(InstagramUser fu) {
		// TODO Auto-generated method stub
		int i = 0;
		try
		{
			Session s = sf.openSession();
			Transaction t = s.beginTransaction();
			
			s.save(fu);
			t.commit(); // Saving changes permanently
			i = 1;
		}
		catch(Exception ex)
		{
			log.error("CreateProfileDAO : " + ex.getStackTrace());
			log.error("CreateProfileDAO : " + ex.getMessage());
		}
		
		return i;
	}

	public boolean loginUserDAO(InstagramUser iu) {
		// TODO Auto-generated method stub
		boolean status = false;
		try
		{
			Session s = sf.openSession();
			Query q = s.createQuery("from InstagramUser iu where iu.email='"+iu.getEmail()+"' and iu.password='"+iu.getPassword()+"' ");
			List<InstagramUser> ff = q.getResultList();
			if(ff.size() > 0)
			{
				status = true;
			}
		}
		catch(Exception ex)
		{
			log.error("LoginUserDAO : " + ex.getStackTrace());
		}
		return status;
	}

	public List getTimeLine(InstagramUser fu) {
		// TODO Auto-generated method stub
		return null;
	}

	public InstagramUser viewProfileDAO(InstagramUser fu) {
		// TODO Auto-generated method stub
		InstagramUser ff = null;
		try
		{
			Session s = sf.openSession();
			ff = s.load(InstagramUser.class, fu.getEmail());
		}
		catch(Exception ex)
		{
			log.error("ViewProfileDAO : " + ex.getStackTrace());
		}
		return ff;
	}

	public boolean checkEmailDAO(InstagramUser fu) {
		// TODO Auto-generated method stub
		InstagramUser ff = null;
		try
		{
			Session s = sf.openSession();
			ff = s.get(InstagramUser.class, fu.getEmail());
		}
		catch(Exception ex)
		{
			log.error("ViewProfileDAO : " + ex.getStackTrace());
		}
		if(ff == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public InstagramUser editProfileDAO(InstagramUser fu) {
		// TODO Auto-generated method stub
		try
		{
			Session s = sf.openSession();
			Transaction t = s.beginTransaction();
			
			s.update(fu);
			t.commit(); // Saving changes permanently
		}
		catch(Exception ex)
		{
			log.error("EditProfileDAO : " + ex.getStackTrace());
		}
		
		return fu;
	}

	public boolean deleteProfileDAO(InstagramUser fu) {
		// TODO Auto-generated method stub
		try
		{
			Session s = sf.openSession();
			Transaction t = s.beginTransaction();
			
			s.delete(fu);
			t.commit();
		}
		catch(Exception ex)
		{
			log.error("DeleteProfileDAO : " + ex.getStackTrace());
		}

		return true;
	}

}
