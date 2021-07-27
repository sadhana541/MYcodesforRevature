package com.coin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.coin.dbutil.PostgresConnection;
import com.coin.exception.BusinessException;
import com.coin.model.Account;
import com.coin.model.Customer;
import com.coin.model.Transaction;

public class CustomerDAO implements ICustomerDAO {
	
private static Logger log = Logger.getLogger(CustomerDAO.class);

	public CustomerDAO()
	{
	}

	@Override
	public Customer RegisterUser(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		try(Connection connection = PostgresConnection.getConnection())
		{
			String sql = "INSERT INTO coinbank.customer (customerid, \"password\", name, mobile, age, city) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatmentCustomer = connection.prepareStatement(sql);
			preparedStatmentCustomer.setString(1, customer.getCustomerid());
			preparedStatmentCustomer.setString(2, customer.getPassword());
			preparedStatmentCustomer.setString(3, customer.getName());
			preparedStatmentCustomer.setLong(4, customer.getMobile());
			preparedStatmentCustomer.setInt(5, customer.getAge());
			preparedStatmentCustomer.setString(6, customer.getCity());
			
			int rowsAffected = preparedStatmentCustomer.executeUpdate();
			
			if(rowsAffected != 1)
			{
				log.error("Error in UserDAO while inserting data into user and customer table");
				throw new BusinessException("User registration failed. Please try again!!!");
			}
		}
		catch(ClassNotFoundException | SQLException ex)
		{
			log.error("Error in UserDAO while inserting data into user and customer table");
			throw new BusinessException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		
		return customer;
	}
	
	public Customer LoginUser(String customerid, String password) throws BusinessException {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		
		try(Connection connection = PostgresConnection.getConnection())
		{
			String query = "SELECT * FROM coinbank.customer WHERE customerid = ? and \"password\" = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customerid);
			preparedStatement.setString(2, password);
			
			ResultSet result = preparedStatement.executeQuery();
			  
			if(!result.next()) 
			{
				log.info("Inavlid Username or Password");
				customer = null;
			}
			else
			{
				customer.setCustomerid(customerid);
				customer.setName(result.getString("name"));
				customer.setMobile(result.getLong("mobile"));
				customer.setAge(result.getInt("age"));
				customer.setCity(result.getString("city"));
			}	
		}
		catch(ClassNotFoundException | SQLException ex)
		{
			throw new BusinessException("Internal error occured... Kindly try again.....");
		}
		return customer;
	}

	@Override
	public double ViewBalance(long accountNumber) throws BusinessException {
		// TODO Auto-generated method stub
		
		double balance = -1;
		try(Connection connection = PostgresConnection.getConnection())
		{
			String query = "SELECT balance FROM coinbank.account where accountnumber = ?";
			PreparedStatement preparedStatment = connection.prepareStatement(query);
			preparedStatment.setLong(1, accountNumber);
			
			ResultSet result = preparedStatment.executeQuery();
			
			if(result.next())
			{
				balance = result.getDouble(1);
			}
		}
		catch(Exception ex)
		{
			throw new BusinessException("Internal error occured... Kindly try again...!!!");
		}
		return balance;
	}

	@Override
	public boolean Withdraw(long accountNumber, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		CustomerDAO customer = new CustomerDAO();
		
		double currentBalance = customer.ViewBalance(accountNumber);
		if(currentBalance > amount)
		{
			try(Connection connection = PostgresConnection.getConnection())
			{
				double remainingBalance = currentBalance - amount;
				String query = "UPDATE coinbank.account SET balance = ? WHERE accountnumber = ?";
				PreparedStatement preStatement = connection.prepareStatement(query);
				preStatement.setDouble(1, remainingBalance);
				preStatement.setLong(2, accountNumber);
				
				int rowsAffected = preStatement.executeUpdate();
				
				if(rowsAffected == 1)
				{
					TransactionUpdate(accountNumber,-10, amount, "W");
					return true;
				}
			}
			catch(Exception ex)
			{
				throw new BusinessException("Error in withdraw dao : " + ex); 
			}
		}
		return false;
	}

	@Override
	public Account GetAccountByCustomerID(String customerid) throws BusinessException {
		// TODO Auto-generated method stub
		Account account = new Account();
		
		try(Connection connection = PostgresConnection.getConnection())
		{
			String query = "SELECT * FROM coinbank.account where customerid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customerid);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if(!result.next())
			{
				account = null;
			}
			else
			{
				account.setAccountNumber(result.getLong(1));
				account.setCustomerID(result.getString(2));
				account.setBalance(result.getDouble(3));
			}
		}
		catch(Exception ex)
		{
			log.error("In Account Details by Customer ID " + ex);
			throw new BusinessException("Error occured while retrieving account details : " + ex);
		}
		return account;
	}
	//credit means submit balance in my account
	@Override
	public boolean SubmitAmount(long accountNumber, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		CustomerDAO customer = new CustomerDAO();
		double currentBalance = customer.ViewBalance(accountNumber);
		
		try(Connection connection = PostgresConnection.getConnection())
		{
			double totalBalance = currentBalance + amount;
			String query = "UPDATE coinbank.account SET balance = ? WHERE accountnumber = ?";
			PreparedStatement preStatement = connection.prepareStatement(query);
			preStatement.setDouble(1, totalBalance);
			preStatement.setLong(2, accountNumber);
			
			int rowsAffected = preStatement.executeUpdate();
			
			if(rowsAffected == 1)
			{
				TransactionUpdate(accountNumber,-10, amount, "C");
				return true;
			}
		}
		catch(Exception ex)
		{
			throw new BusinessException("Error in submitmoney dao : " + ex); 
		}
		
		return false;
	}

	@Override
	public boolean TransferToAccount(long accountNumberFrom, long accountNumberTo, double amount)
			throws BusinessException {
		// TODO Auto-generated method stub
		CustomerDAO customer = new CustomerDAO();
		if(customer.Withdraw(accountNumberFrom, amount))
		{
			if(customer.SubmitAmount(accountNumberTo, amount))
			{
				TransactionUpdate(accountNumberFrom,accountNumberTo, amount, "T");
				return true;
			}
			else
			{
				customer.SubmitAmount(accountNumberFrom, amount);
			}
		}
		return false;
	}

	@Override
	public List<Transaction> ViewStatements(long accountNumber) throws BusinessException {
		// TODO Auto-generated method stub
		List<Transaction> transactions = new ArrayList<Transaction>();
		try(Connection connection = PostgresConnection.getConnection())
		{
			String query = "SELECT * FROM coinbank.\"transaction\" where \"from\" = ? or \"to\" = ?";
			PreparedStatement preStatement = connection.prepareStatement(query);
			preStatement.setLong(1, accountNumber);
			preStatement.setLong(2, accountNumber);
			
			ResultSet result = preStatement.executeQuery();
			
			while(result.next())
			{
				Transaction transaction = new Transaction();
				transaction.setFrom(result.getLong("from"));
				transaction.setTo(result.getLong("to"));
				transaction.setTransactionid(result.getLong("transactionid"));
				transaction.setDate(result.getDate("date"));
				transaction.setType(result.getString("type"));
				transaction.setAmount(result.getDouble("amount"));
				transactions.add(transaction);
			}
		}
		catch(Exception ex)
		{
			throw new BusinessException("Internal error occured... Kindly try again...!!!");
		}
		return transactions;
	}

	@Override
	public long ApplyForAccount(String customerid, double initialBalance) throws BusinessException {
		// TODO Auto-generated method stub
		long applicationID = -10;
		
		try(Connection connection = PostgresConnection.getConnection())
		{
			String query = "INSERT INTO coinbank.accountrequest (customerid, initialbalance) VALUES (?, ?)";
			PreparedStatement preparedStatment = connection.prepareStatement(query);
			preparedStatment.setString(1, customerid);
			preparedStatment.setDouble(2, initialBalance);
			
			int rowsAffected = preparedStatment.executeUpdate();
			
			if(rowsAffected == 1) {
				
				query = "SELECT applicationid FROM coinbank.accountrequest where customerid = ?";
				PreparedStatement preStatment = connection.prepareStatement(query);
				preStatment.setString(1, customerid);
				ResultSet result = preStatment.executeQuery();
				while(result.next())
				{
					applicationID = result.getLong("applicationid");
				}
			}else {
				throw new BusinessException("Application Apply Failure Please Retry!!!");
			}
		}
		catch(Exception ex)
		{
			throw new BusinessException("Internal error occured... Kindly try again...!!!\n" + ex);
		}
		
		return applicationID;
	}
	
	public boolean TransactionUpdate(long from, long to, double amount, String type) throws BusinessException
	{
		Date date = new Date(System.currentTimeMillis());
		LocalDate localDate = date.toLocalDate();
		Date sqldate = Date.valueOf(localDate);
		
        try(Connection connection = PostgresConnection.getConnection())
        {
        	String query;
        	PreparedStatement preStatement;
        	if(to > 0)
        	{
        		query = "INSERT INTO coinbank.\"transaction\" (\"from\", \"date\", \"type\", amount, \"to\") values (?, ?, ?, ?, ?)";
        		preStatement = connection.prepareStatement(query);
        		preStatement.setLong(5, to);
        	}
        	else
        	{
        		query = "INSERT INTO coinbank.\"transaction\" (\"from\", \"date\", \"type\", amount) values (?, ?, ?, ?)";
        		preStatement = connection.prepareStatement(query);
        	}
        	preStatement.setLong(1, from);
        	preStatement.setDate(2, sqldate);
        	preStatement.setString(3, type);
        	preStatement.setDouble(4, amount);
        	
        	int rowsAffected = preStatement.executeUpdate();
        	
        	if(rowsAffected == 1)
        	{
        		return true;
        	}
        	
        }
        catch(Exception ex)
        {
        	throw new BusinessException("Some error occured during transaction saving.");
        }
		return false;
	}
}

