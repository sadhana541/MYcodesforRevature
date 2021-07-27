package com.coin.dao;

import java.util.List;

import com.coin.exception.BusinessException;
import com.coin.model.Account;
import com.coin.model.Customer;
import com.coin.model.Transaction;

public interface ICustomerDAO {
	
	public Customer RegisterUser(Customer customer) throws BusinessException;
	
	public Customer LoginUser(String customerid, String password) throws BusinessException;
	
	public double ViewBalance(long accountNumber) throws BusinessException;
	
	public boolean Withdraw(long accountNumber, double amount) throws BusinessException;
	
	public Account GetAccountByCustomerID(String customerid) throws BusinessException;
	
	public boolean SubmitAmount(long accountNumber, double amount) throws BusinessException;
	
	public boolean TransferToAccount(long accountNumberFrom, long accountNumberTo, double amount) throws BusinessException;
	
	public List<Transaction> ViewStatements(long accountNumber) throws BusinessException;
	
	public long ApplyForAccount(String customerid, double initialBalance) throws BusinessException ;
}
