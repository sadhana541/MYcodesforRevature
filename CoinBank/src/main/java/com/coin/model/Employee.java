package com.coin.model;

public class Employee {
	
	private String employeeID;
	private String password;
	
	public Employee()
	{
	}

	public Employee(String employeeID, String password) {
		super();
		this.employeeID = employeeID;
		this.password = password;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", password=" + password + "]";
	}
}