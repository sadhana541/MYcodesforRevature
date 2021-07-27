package com.coin.model;

public class AccountRequest {

	private String customerID;
	private double initialbalance;
	
	private long applicationID;
	
	public AccountRequest()
	{
	}
	
	public AccountRequest(String customerID, double initialbalance) {
		super();
		this.customerID = customerID;
		this.initialbalance = initialbalance;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public double getInitialbalance() {
		return initialbalance;
	}
	public void setInitialbalance(double initialbalance) {
		this.initialbalance = initialbalance;
	}
	public long getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(long applicationID) {
		this.applicationID = applicationID;
	}

	@Override
	public String toString() {
		return "AccountRequest [CustomerID=" + customerID + ", Initialbalance=" + initialbalance + "]";
	}
}
