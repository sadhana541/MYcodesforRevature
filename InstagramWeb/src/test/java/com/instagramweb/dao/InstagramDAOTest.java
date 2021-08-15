package com.instagramweb.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

import com.instagramweb.entity.InstagramUser;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InstagramDAOTest {
	
	private InstagramDAOInterface id;
	
	@Before
	public void SetUp()
	{
		id = new InstagramDAOHibernate();
	}
	
	@Test
	public void Test2CreateProfileDAO() {
		
		InstagramUser iu = new InstagramUser(); 
		iu.setName("Swapnil");
		iu.setPassword("ssss");
		iu.setEmail("swapnil@gmail.com");
		iu.setAddress("Kanpur");
		
		int i = id.createProfileDAO(iu);
		
		assert i>0:"Registration fail because i is equal to 0";
	}
	
	@Test
	public void Test3LoginProfile() {
		
		InstagramUser iu = new InstagramUser();
		iu.setEmail("swapnil@gmail.com");
		iu.setPassword("ssss");
		
		boolean status = id.loginUserDAO(iu);
		
		assert status == true:"Login fail, email or password is wrong.";
	}

	@Test
	public void Test4ViewProfile() {
		
		InstagramUser iu = new InstagramUser();
		iu.setEmail("swapnil@gmail.com");
		
		InstagramUser iuu = id.viewProfileDAO(iu);
		
		assert iu.getEmail() == iuu.getEmail(): "View profile fail, email may does not present";
	}

	@Test
	public void Test5EditProfile() {
		
		InstagramUser iu = new InstagramUser();
		iu.setName("Swanil Singh");
		iu.setEmail("swapnil@gmail.com");
		iu.setPassword("ssss");
		iu.setAddress("Lucknow");
		
		InstagramUser iuu = id.editProfileDAO(iu);
		
		assert iuu == iu:"Failed to update user profile";
	}

	@Test
	public void Test6DeleteProfile() {
		
		InstagramUser iu = new InstagramUser();
		iu.setEmail("swapnil@gmail.com");
		
		boolean status = id.deleteProfileDAO(iu);
		
		assert status == true:"Failed to delete user profile";
	}

	@Test
	public void Test1CheckEmailDAO() {
		InstagramUser iu = new InstagramUser();
		iu.setEmail("swapnil@gmail.com");
		
		boolean status = id.checkEmailDAO(iu);
		
		assert status == true:"Email already present in database";
	}
	
	@After
	public void TearDown()
	{
		id = null;
	}
}
