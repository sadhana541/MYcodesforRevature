package com.coin.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.coin.dao.EmployeeDAO;
import com.coin.dao.IEmployeeDAO;
import com.coin.exception.BusinessException;
import com.coin.model.Account;
import com.coin.model.AccountRequest;
import com.coin.model.Customer;
import com.coin.model.Employee;
import com.coin.model.Transaction;

public class EmployeeService implements IEmployeeService {

	private static IEmployeeDAO employeeDAO;
	private static Logger log = Logger.getLogger(EmployeeService.class);
	
	public EmployeeService()
	{
		employeeDAO = new EmployeeDAO();
	}
	
	@Override
	public Employee EmployeeLogin(String employeeid, String password) throws BusinessException {
		// TODO Auto-generated method stub
		
		Employee employee = null;
		
		if(employeeid != null && password != null)
		{
			employee = employeeDAO.Login(employeeid, password);
		}
		else
		{
			log.info("EmployeeID and Password is required");
		}
		return employee;
	}

	@Override
	public boolean AllAccountRequest() throws BusinessException {
		// TODO Auto-generated method stub
		List<AccountRequest> requests = null;
		try
		{
			requests = employeeDAO.AllAccountApplications();
			log.info("All Account Applications");
			log.info("=======================");
			for(AccountRequest req : requests)
			{
				log.info("Application ID : " + req.getApplicationID() +", CustomerID : " + req.getCustomerID() +", Initial Balance : " + req.getInitialbalance());
			}
			return true;
		}
		catch(Exception ex)
		{
			log.info("Some error occured while processing application list...");
		}
		return false;
	}

	@Override
	public boolean ApproveAccountApplication(long applicationID, long accountNumber) throws BusinessException {
		// TODO Auto-generated method stub
		try
		{
			List<AccountRequest> requests = employeeDAO.AllAccountApplications();
			AccountRequest request = null;
			for(AccountRequest req : requests)
			{
				if(applicationID == req.getApplicationID())
				{
					request = req;
				}
			}
			
			if(request != null)
			{
				boolean status = employeeDAO.AccountApprove(applicationID, accountNumber, request.getCustomerID(), request.getInitialbalance());
				if(status)
				{
					log.info("Account Created Successfully. Account Number Is : " + accountNumber);
					return true;
				}
				else
				{
					log.info("Failed to create account, please try again...!!!");
					return false;
				}
			}
			else
			{
				log.info("Invalid Application ID, please enter valid application ID.");
			}
		}
		catch(Exception ex)
		{
			log.error("Some error occured while creatin account, try again. " + ex);
		}
		return false;
	}

	@Override
	public boolean CustomerDetails(String customerID) throws BusinessException {
		// TODO Auto-generated method stub
		
		if(customerID != null)
		{
			Customer customer = employeeDAO.CustomerDetails(customerID);
			Account account = employeeDAO.AccountDetails(customerID);
			if(customer != null)
			{
				log.info("Customer ID : " + customerID);
				log.info("Customer Name : " + customer.getName());
				log.info("Customer Mobile Number :  " + customer.getMobile());
				log.info("Customer Age : " + customer.getAge());
				log.info("Customer City : " + customer.getCity());
				if(account != null)
				{
					log.info("Customer Account Number : " + account.getAccountNumber());
					log.info("Customer Available Balance : " + account.getBalance());
				}
				else
				{
					log.info("Customer Account Number : Does Not Have Account. Apply for new account.");
				}
				return true;
			}
			else
			{
				log.info("Customer Details not found. Check customer ID.");
				return false;
			}
		}
		else
		{
			log.info("Invalid customer ID.");
		}
		return false;
	}

	@Override
	public boolean CustomerTransaction(long accountNumber) throws BusinessException {
		// TODO Auto-generated method stub
		List<Transaction> transactions = null;
		if(accountNumber > 0)
		{
			transactions = employeeDAO.CustomerTransactions(accountNumber);
			if(transactions != null)
			{
				log.info("All Transations of Account Number : " + accountNumber);
				int i = 1;
				for(Transaction tra : transactions)
				{
					String statement = String.valueOf(i);
					if(tra.getType().equals("T"))
					{
						if(tra.getFrom() == accountNumber)
						{
							statement += ")  Transfer of Rs. " + tra.getAmount()+ " to "+ tra.getTo() + " on " + tra.getDate() + ". Transaction ID is " + tra.getTransactionid() + ".";
						}
						else
						{
							statement += ")  Recieved Rs. " + tra.getAmount()+ " from " + tra.getFrom() + " on " + tra.getDate() + ". Transaction ID is " + tra.getTransactionid() + ".";
						}
					}
					else if(tra.getType().equals("W"))
					{
						statement += ")  " + tra.getAmount() +" Rs. withdrawn on " + tra.getDate() + ". Transaction ID is " + tra.getTransactionid() + "."; 
					}
					else
					{
						statement += ")  " + tra.getAmount() + " Rs. submitted on " + tra.getDate() + ". Transaction ID is " + tra.getTransactionid() + ".";
					}
					log.info(statement);
					i++;
				}
				return true;
			}
		}
		else
		{
			log.info("Invalid Account Number, try again.");
		}
		return false;
	}
}
