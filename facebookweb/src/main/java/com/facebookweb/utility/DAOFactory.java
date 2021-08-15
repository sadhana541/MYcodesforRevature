package com.facebookweb.utility;

import com.facebookweb.dao.FacebookDAO;
import com.facebookweb.dao.FacebookDAOInterface;

public class DAOFactory {

	public static FacebookDAOInterface createObject() {
		// TODO Auto-generated method stub
		return new FacebookDAO();
	}

}
