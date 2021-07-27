package com.coin.model;

public class Customer {
	
	private String customerid;
	private String password;
	private String name;
	private long mobile;
	private int age;
	private String city;
	
	public Customer()
	{
	}
	
	public Customer(String customerid, String password, String name, long mobile, int age, String city) {
		super();
		this.customerid = customerid;
		this.password = password;
		this.name = name;
		this.mobile = mobile;
		this.age = age;
		this.city = city;
	}
	
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", password=" + password + ", name=" + name + ", mobile=" + mobile
				+ ", age=" + age + ", city=" + city + "]";
	}
}
