package com.coin.service;

import com.coin.exception.BusinessException;
import com.coin.model.Employee;

public interface IEmployeeService {
	
	public Employee EmployeeLogin(String eomployeeid, String password) throws BusinessException;
	
	public boolean AllAccountRequest() throws BusinessException;
	
	public boolean ApproveAccountApplication(long applicationID, long accountNumber) throws BusinessException;
	
	public boolean CustomerDetails(String customerID) throws BusinessException;
	
	public boolean CustomerTransaction(long accountNumber) throws BusinessException;
}