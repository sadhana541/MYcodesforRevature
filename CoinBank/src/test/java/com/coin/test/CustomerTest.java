package com.coin.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.coin.exception.BusinessException;
import com.coin.model.*;
import com.coin.service.*;

public class CustomerTest {

	private static UserService userService;
	private static List<Customer> customers;
	private static List<Account> accounts;
	
	@BeforeAll
	public static void SetupCustomerData()
	{
		userService = new UserService();
		
		customers = new ArrayList<Customer>();
		
		customers.add(new Customer("deepa", null," deepa tiwari", 233334444, 32, "lucknoe" ));
		customers.add(new Customer("upasana", null, "upasaana", 1234567890, 21, "Lucknow" ));
		
		accounts = new ArrayList<Account>();
		accounts.add(new Account(9876, "deepa", 900.0));
		accounts.add(new Account(6554, "upasana", 800.0));
	}
	
	@Test
	public void CustomerLoginTest() throws BusinessException
	{
		assertEquals(customers.get(0).getCustomerid(),userService.Login("deepa", "123456").getCustomerid());
	}
	
	@Test
	public void CustomerViewBalanceTest() throws BusinessException
	{
		assertEquals(accounts.get(0).getBalance(), userService.ViewBalance(accounts.get(0).getAccountNumber()));
	}
}
	
	