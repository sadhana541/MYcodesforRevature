package com.instagramweb.utility;

import org.apache.log4j.Logger;

import com.instagramweb.dao.InstagramDAOHibernate;
import com.instagramweb.dao.InstagramDAOInterface;

public class DAOFactory {
	
	private static Logger log = Logger.getLogger(DAOFactory.class);
	
	public static InstagramDAOInterface createObjectHibernate() {
		// TODO Auto-generated method stub
		return new InstagramDAOHibernate();
	}

}
