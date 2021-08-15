package com.facebookweb.dao;

import com.facebookweb.entity.FacebookUser;


public interface FacebookDAOInterface {

	int createProfileDAO(FacebookUser fu);

	boolean loginUserDAO(String name, String password);
	public FacebookUser viewProfile(FacebookUser fu);
	public int deleteProfileDAO( FacebookUser iu);
	public int editProfileDAO(FacebookUser iu,String ir) ;
}
