package com.coin.dao;

import java.util.List;

import com.coin.exception.BusinessException;
import com.coin.model.Account;
import com.coin.model.AccountRequest;
import com.coin.model.Customer;
import com.coin.model.Employee;
import com.coin.model.Transaction;

public interface IEmployeeDAO {
	
	public Employee Login(String eomployeeID, String password) throws BusinessException;
	
	public List<AccountRequest> AllAccountApplications() throws BusinessException;
	
	public boolean AccountApprove(long applicationID, long accountNumber, String customerID, double balance) throws BusinessException;
	
	public Customer CustomerDetails(String customerID) throws BusinessException;
	
	public Account AccountDetails(String customerID) throws BusinessException;
	
	public List<Transaction> CustomerTransactions(long accountNumber) throws BusinessException;
}
