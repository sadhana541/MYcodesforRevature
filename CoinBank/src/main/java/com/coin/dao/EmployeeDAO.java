
package com.coin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.coin.dbutil.PostgresConnection;
import com.coin.exception.BusinessException;
import com.coin.model.Account;
import com.coin.model.AccountRequest;
import com.coin.model.Customer;
import com.coin.model.Employee;
import com.coin.model.Transaction;

public class EmployeeDAO implements IEmployeeDAO {

	private static Logger log = Logger.getLogger(EmployeeDAO.class);
	
	@Override
	public Employee Login(String employeeID, String password) throws BusinessException {
		// TODO Auto-generated method stub
		
		Employee employee = new Employee();
		
		try(Connection connection = PostgresConnection.getConnection())
		{
			String query = "SELECT * FROM coinbank.employee  WHERE employeeid = ? and \"password\" = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,employeeID);
			preparedStatement.setString(2, password);
			
			ResultSet result = preparedStatement.executeQuery();
			  
			if(!result.next()) 
			{
				log.info("Inavlid EmployeeID or Password");
				employee = null;
			}
			else
			{
				employee.setEmployeeID(employeeID);
				employee.setPassword(password);
			}
		}
		catch(ClassNotFoundException | SQLException ex)
		{
			throw new BusinessException("Internal error occured... Kindly try again.....");
		}
		return employee;
	}

	@Override
	public List<AccountRequest> AllAccountApplications() throws BusinessException {
		// TODO Auto-generated method stub
		List<AccountRequest> requests = new ArrayList<AccountRequest>();
		try(Connection connection = PostgresConnection.getConnection())
		{
			String query = "SELECT customerid, initialbalance, applicationid FROM coinbank.accountrequest";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next())
			{
				AccountRequest request = new AccountRequest();
				request.setCustomerID(result.getString("customerid"));
				request.setInitialbalance(result.getDouble("initialbalance"));
				request.setApplicationID(result.getLong("applicationid"));
				requests.add(request);
			}
		}
		catch(Exception ex)
		{
			throw new BusinessException("Internal error occured... Kindly try again....." + ex);
		}
		return requests;
	}

	@Override
	public boolean AccountApprove(long applicationID, long accountNumber, String customerID, double balance) throws BusinessException {
		// TODO Auto-generated method stub
		try(Connection connection = PostgresConnection.getConnection())
		{
			String query = "INSERT INTO coinbank.account (accountnumber, customerid, balance) VALUES(?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1,accountNumber);
			preparedStatement.setString(2,  customerID);
			preparedStatement.setDouble(3, balance);
			
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 1)
			{
				query = "DELETE FROM coinbank.accountrequest WHERE applicationid=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setLong(1, applicationID);
				
				rowsAffected = preparedStatement.executeUpdate();
				
				if(rowsAffected == 1)
				{
					return true;
				}
			}
		}
		catch(Exception ex)
		{
			throw new BusinessException("Error In ApproveRequestDAO :- " + ex);
		}
		return false;
	}

	@Override
	public Customer CustomerDetails(String customerID) throws BusinessException {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		try(Connection connection = PostgresConnection.getConnection())
		{
			String query = "SELECT customerid, \"password\", name, mobile, age, city FROM coinbank.customer where customerid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customerID);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if(result.next())
			{
				customer.setName(result.getString("name"));
				customer.setMobile(result.getLong("mobile"));
				customer.setAge(result.getInt("age"));
				customer.setCity(result.getString("city"));
				customer.setCustomerid(customerID);
			}
			else
			{
				customer = null;
			}
		}
		catch(Exception ex)
		{
			throw new BusinessException("Error in CustomerDetails EmployeeDAO : " + ex);
		}
		return customer;
	}

	@Override
	public Account AccountDetails(String customerID) throws BusinessException {
		// TODO Auto-generated method stub
		Account account = new Account();
		try(Connection connection = PostgresConnection.getConnection())
		{
			String query = "SELECT accountnumber, customerid, balance FROM coinbank.account where customerid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customerID);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if(result.next())
			{
				account.setAccountNumber(result.getLong("accountnumber"));
				account.setCustomerID(customerID);
				account.setBalance(result.getDouble("balance"));
			}
			else
			{
				account = null;
			}
		}
		catch(Exception ex)
		{
			throw new BusinessException("Error in AccountDetails EmployeeDAO : " + ex);
		}
		return account;
	}

	@Override
	public List<Transaction> CustomerTransactions(long accountNumber) throws BusinessException {
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
			throw new BusinessException("Internal error occured... Kindly try again...!!!"  + ex);
		}
		
		return transactions;
	}

}
