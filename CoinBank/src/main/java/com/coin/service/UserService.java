package com.coin.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.coin.dao.CustomerDAO;
import com.coin.dao.ICustomerDAO;
import com.coin.exception.BusinessException;
import com.coin.model.Account;
import com.coin.model.Customer;
import com.coin.model.Transaction;

public class UserService implements IUserService {

	private static Logger log = Logger.getLogger(UserService.class);
	
	private ICustomerDAO customerDAO;
	
	public UserService()
	{
		customerDAO = new CustomerDAO();
	}
	
	@Override
	public boolean Register(Customer customer) throws BusinessException 
	{	
	 Customer customerTemp = null;
		
		if(CustomerDetailsValidate(customer))
		{
			if(customer.getPassword().length() > 5)
			{
				customerTemp = customerDAO.RegisterUser(customer);
			}
			else
			{
				log.info("Password should be greater than 5 characters.");
			}
		}
		else
		{
			log.info("Usernamr and Password is required");
		}
		
		return customerTemp != null ? true : false;
	}

	@Override
	public Customer Login(String customerid, String password) throws BusinessException 
	{
		Customer customerTemp = null;
		
		if(customerid != null && password != null)
		{
			customerTemp = customerDAO.LoginUser(customerid, password);
		}
		else
		{
			log.info("CustomerID and Password is required");
		}
		return customerTemp;
	}
	
	private boolean CustomerDetailsValidate(Customer customer)
	{
		if(customer != null)
		{
			if(customer.getCustomerid() != null && customer.getPassword() != null && customer.getName() != null && customer.getMobile() != 0 && customer.getAge() != 0 && customer.getCity() != null)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public double ViewBalance(long accountNumber) throws BusinessException {
		// TODO Auto-generated method stub
		
		double balance = 0;
		
		if(accountNumber != 0)
		{
			balance = customerDAO.ViewBalance(accountNumber);
		}
		else
		{
			log.info("Account Number  required.");
		}
		return balance;
	}

	@Override
	public Account GetAccountByCustomerID(String customerID) throws BusinessException {
		// TODO Auto-generated method stub
		Account account = null;
		
		if(customerID != null && customerID.length() != 0)
		{
			account = customerDAO.GetAccountByCustomerID(customerID);
		}
		else
		{
			log.info("GetAccount : Customer ID Required. " + customerID);
		}
		return account;
	}

	@Override
	public boolean Withdraw(long accountNumber, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		if(amount <= 0)
		{
			log.info("Amount should be greater than 0.");
			return false;
		}
		if(accountNumber != 0)
		{
			if(customerDAO.Withdraw(accountNumber, amount))
			{
				log.info("\n" + amount + " Rs Successfully withdrawn.");
				return true;
			}
			else
			{
				log.info("You don't have enough balance to withdraw this amount. Please give valid amount");
			}
		}
		else
		{
			log.info("Invalid account number");
		}
		return false;
	}

	@Override
	public boolean SubmitAmount(long accountNumber, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		if(amount <= 0)
		{
			log.info("Amount should be grater than 0.");
			return false;
		}
		if(accountNumber != 0)
		{
			if(customerDAO.SubmitAmount(accountNumber, amount))
			{
				log.info("\n" + amount + " Rs Successfully submitted.");
				return true;
			}
			else
			{
				log.info("Some error occured, please try again.");
			}
		}
		else
		{
			log.info("Invalid account number.");
		}
		return false;
	}

	@Override
	public boolean TransferToAccount(long accountNumberFrom, long accountNumberTo, double amount)
			throws BusinessException {
		// TODO Auto-generated method stub
		if(accountNumberFrom == accountNumberTo)
		{
			log.info("Can't transfer into same account, try with different account.");
			return false;
		}
		if(amount <= 0)
		{
			log.info("Amount should be greater than 0.");
			return false;
		}
		if(accountNumberFrom != 0 && accountNumberTo != 0)
		{
			if(customerDAO.TransferToAccount(accountNumberFrom, accountNumberTo, amount))
			{
				log.info("\n" + amount + " Rs successfully transfered from " + accountNumberFrom + " to " + accountNumberTo);
				return true;
			}
			else
			{
				log.info("Not enough balance to transfer " + amount + " Rs.");
			}
		}
		else
		{
			log.info("Invalid account number.");
		}
		return false;
	}

	@Override
	public boolean ViewStatement(long accountNumber) throws BusinessException {
		// TODO Auto-generated method stub
		List<Transaction> transactions = null;
		if(accountNumber > 0)
		{
			transactions = customerDAO.ViewStatements(accountNumber);
			if(transactions != null)
			{
				log.info("Your All Transations");
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

	@Override
	public long ApplyForAccount(String customerID, double initialBalance) throws BusinessException {
		// TODO Auto-generated method stub
		long applicationID = -10;
		if(customerID != null && initialBalance >= 500)
		{
			applicationID = customerDAO.ApplyForAccount(customerID, initialBalance);
			if(applicationID < 0)
			{
				log.info("Failed to apply, please try again...!!!");
			}
			else
			{
				return applicationID;
			}
		}
		else
		{
			log.info("CustomerID and Initial Balance is required(Minimum 500 Rs.)");
		}
		return applicationID;
	}
}
