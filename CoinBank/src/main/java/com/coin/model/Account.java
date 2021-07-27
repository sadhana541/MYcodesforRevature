package com.coin.model;

public class Account {

	private long accountNumber;
	private String customerID;
	private double balance;
	
	public Account()
	{
	}
	
	public Account(long accountNumber, String customerID, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.customerID = customerID;
		this.balance = balance;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [AccountNumber=" + accountNumber + ", CustomerID=" + customerID + ", alance=" + balance + "]";
	}
}
