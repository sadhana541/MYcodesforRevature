package com.coin.model;

import java.util.Date;

public class Transaction {

	private long from;
	private long to;
	private Date date;
	private double amount;
	private String type;
	private long transactionid;
	
	public Transaction()
	{
	}
	
	public Transaction(long from, long to, Date date, double amount, String type) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
		this.amount = amount;
		this.type = type;
	}
	
	public long getFrom() {
		return from;
	}
	public void setFrom(long from) {
		this.from = from;
	}
	public long getTo() {
		return to;
	}
	public void setTo(long to) {
		this.to = to;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(long transactionid) {
		this.transactionid = transactionid;
	}

	@Override
	public String toString() {
		return "Transaction [From=" + from + ", To=" + to + ", date=" + date + ", Amount=" + amount + ", Type=" + type +"]";
	}
	
}
