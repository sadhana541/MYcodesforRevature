package com.instagramweb.dao;

import java.util.List;

import com.instagramweb.entity.InstagramUser;
import com.instagramweb.exception.BusinessException;

public interface InstagramDAOInterface {
	
	int createProfileDAO(InstagramUser iu);

	boolean loginUserDAO(InstagramUser iu);

	List getTimeLine(InstagramUser iu);

	InstagramUser viewProfileDAO(InstagramUser iu);

	boolean checkEmailDAO(InstagramUser iu);
	
	InstagramUser editProfileDAO(InstagramUser iu);
	
	boolean deleteProfileDAO(InstagramUser iu);

}
