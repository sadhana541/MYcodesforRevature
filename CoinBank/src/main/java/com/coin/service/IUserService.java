package com.coin.service;

import com.coin.exception.BusinessException;
import com.coin.model.Account;
import com.coin.model.Customer;

public interface IUserService {
	
	public boolean Register(Customer customer) throws BusinessException;
	
	public Customer Login(String customerid, String password) throws BusinessException;
	
	public double ViewBalance(long accountNumber) throws BusinessException;
	
	public boolean Withdraw(long accountNumber, double amount) throws BusinessException;
	
	public boolean SubmitAmount(long accountNumber, double amount) throws BusinessException;
	
	public boolean TransferToAccount(long accountNumberFrom, long accountNumberTo, double amount) throws BusinessException;
	
	public Account GetAccountByCustomerID(String customerID) throws BusinessException;
	
	public boolean ViewStatement(long accountNumber) throws BusinessException;
	
	public long ApplyForAccount(String customerID, double initialBalance) throws BusinessException;
}
