package com.coin.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.coin.model.*;
import com.coin.service.*;

public class CoinBankMain {
	
	private static Logger log = Logger.getLogger(CoinBankMain.class);
	private static IUserService userService = new UserService();
	private static IEmployeeService employeeService = new EmployeeService();
	
	public CoinBankMain()
	{
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do
		{
			log.info("\nWelcome to Coin Bank");
			log.info("======================");
			log.info("\n1 - Login As Customer");
			log.info("2 - Login as Employee");
			log.info("3 - Register new Customer");
			log.info("4 - Exit");
			log.info("Enter your choice 1-4 ....");
			
			try 
			{
				choice = Integer.parseInt(sc.nextLine());
				
				switch(choice)
				{
					case 1:  
					{
						try
						{
							log.info("\nEnter your credentials to login as Customer");
							log.info("=============================================");
							log.info("Enter Username ");
							String username = sc.nextLine();
							log.info("Enter Password ");
							String password = sc.nextLine();
							//sc.nextLine();
							Customer customer = userService.Login(username, password);
							
							if(customer != null)
							{
								
								log.info("\nLogin Successfull...!!!");
								int customerChoice = 0;
								Account account = userService.GetAccountByCustomerID(username);
								do
								{
									log.info("\nWelcome " + customer.getName());
									log.info("==============");
									log.info("\n1- View Balance");
									log.info("2- View Statements");
									log.info("3- Withdraw Money");
									log.info("4- Submit Money");
									log.info("5- Transfer To Another Account");
									log.info("6- Apply for Account");
									log.info("7- Logout");
									log.info("Enter your choice 1-7 ....");
									
									try
									{
										customerChoice = Integer.parseInt(sc.nextLine());
										
										switch(customerChoice)
										{
											case 1:
											{
												try
												{
													if(account == null)
													{
														log.info("You don't have any account. Please apply for new account");
													}
													else
													{
														double balance = userService.ViewBalance(account.getAccountNumber());
														log.info("Your Available Balance : " + balance);
													}
												}
												catch(Exception ex)
												{
													log.info("Error In View Balance : " + ex);
												}
												log.info("Please Press Any Key To Continue.");
												sc.nextLine();
												break;
											}
											case 2:
											{
												try
												{
													if(account == null)
													{
														log.info("You don't have any account. Please apply for new account");
													}
													else
													{
														boolean status = userService.ViewStatement(account.getAccountNumber());
														if(!status)
														{
															log.info("Some error occured, please try again");
														}
													}
												}
												catch(Exception ex)
												{
													log.info("Error In View Transactions : " + ex);
												}
												log.info("Please Press Any Key To Continue.");
												sc.nextLine();
												break;
											}
											case 3:
											{
												try
												{
													if(account == null)
													{
														log.info("You don't have any account. Please apply for new account");
													}
													else
													{
														log.info("Enter amount you want to withdraw");
														double amount = Double.parseDouble(sc.next());
														boolean status = userService.Withdraw(account.getAccountNumber(), amount);
														if(status)
														{
															double balance = userService.ViewBalance(account.getAccountNumber());
															log.info("Your Remaining Balance : " + balance);
														}
													}
												}
												catch(Exception ex)
												{
													log.info("Error In Withdraw Amount : " + ex);
												}
												sc.nextLine();
												break;
											}
											case 4:
											{
												try
												{
													if(account == null)
													{
														log.info("You don't have any account. Please apply for new account");
													}
													else
													{
														log.info("Enter amount you want to submit");
														double amount = Double.parseDouble(sc.next());
														boolean status = userService.SubmitAmount(account.getAccountNumber(), amount);
														if(status)
														{
															double balance = userService.ViewBalance(account.getAccountNumber());
															log.info("Your Available Balance : " + balance);
														}
													}
												}
												catch(Exception ex)
												{
													log.info("Error In Submit  Amount : " + ex);
												}
												sc.nextLine();
												break;
											}
											case 5:
											{
												try
												{
													if(account == null)
													{
														log.info("You don't have any account. Please apply for new account");
													}
													else
													{
														log.info("Enter account number to which transfer money");
														long accountNumber = Long.parseLong(sc.next());
														log.info("Enter amount you want to transfer");
														double amount = Double.parseDouble(sc.next());
														boolean status = userService.TransferToAccount(account.getAccountNumber(), accountNumber, amount);
														if(status)
														{
															double balance = userService.ViewBalance(account.getAccountNumber());
															log.info("Your Available Balance : " + balance);
														}
													}
												}
												catch(Exception ex)
												{
													log.info("Error In Transfer  Amount : " + ex);
												}
												sc.nextLine();
												break;
											}
											case 6:
											{
												try
												{
													if(account != null)
													{
														log.info("You already have a account.");
													}
													else
													{
														log.info("Enter initial balance (minimum Rs. 500)");
														double balance = Double.parseDouble(sc.next());
														long applicationID = userService.ApplyForAccount(username, balance);
														if(applicationID > 0)
														{
															log.info("Account Application Successfull. Your Application ID : " + applicationID);
														}
													}
												}
												catch(Exception ex)
												{
													log.info("Error In New Account Apply : " + ex);
												}
												log.info("Please Press Any Key To Continue.");
												sc.nextLine();
												break;
											}
											case 7:
											{
												log.info("Thankyou for using Coin bank.\n");
												break;
											}
											default :
											{
												log.info("Invalid Input, Please try again.\n");
											}
										}
									}
									catch(Exception ex)
									{
										log.error("Customer Option Error : " + ex.getMessage());
									}
								}while(customerChoice != 7);
							}
						}
						catch(Exception ex)
						{
							log.error("Login Error : " + ex.getMessage());
						}
						break;
					}
					case 2:
					{
						try
						{
							log.info("\nEnter your credentials to login as Employee");
							log.info("=============================================");
							log.info("Enter EmployeeID ");
							String username = sc.nextLine();
							log.info("Enter Password ");
							String password = sc.nextLine();
							//sc.nextLine();
							
							Employee employee = employeeService.EmployeeLogin(username, password);
							
							if(employee != null)
							{
								log.info("\nLogin Successfull.");
								int employeeChoice = 0;
								do
								{
									log.info("\nWelcome " + employee.getEmployeeID());
									log.info("====================");
									log.info("1- See All Account Applications");
									log.info("2- View Customer Details");
									log.info("3- View Transactions Done By A Account Number");
									log.info("4- Register A New Customer");
									log.info("5- Logout");
									log.info("Enter your choice between 1-5...");
									
									try
									{
										employeeChoice = Integer.parseInt(sc.next());
										
										switch(employeeChoice)
										{
											case 1:
											{
												try
												{
													boolean status = employeeService.AllAccountRequest();
													if(status)
													{
														log.info("\nEnter 1 to go to approve request or Enter 0 to Go Back");
														int backChoice = Integer.parseInt(sc.next());
														if(backChoice != 0)
														{
															log.info("\nEnter application ID to Approve");
															long applicationID = sc.nextLong();
															log.info("Enter Account Number For This Application");
															long accountNumber = sc.nextLong();
															employeeService.ApproveAccountApplication(applicationID, accountNumber);
														}
													}
												}
												catch(Exception ex)
												{
													log.info("Some error occured in Account Application Request List : " + ex);
												}
												sc.nextLine();
												break;
											}
											case 2:
											{
												try
												{
													sc.nextLine();
													log.info("Enter Customer ID to you details");
													String customerID = sc.nextLine();
													employeeService.CustomerDetails(customerID);
												}
												catch(Exception ex)
												{
													log.info("Some error occured during viewing customer details : " + ex);
												}
												break;
											}
											case 3:
											{
												try
												{
													log.info("Enter Account Number");
													long accountNumber = Long.parseLong(sc.next());
													boolean status = employeeService.CustomerTransaction(accountNumber);
													if(!status)
													{
														log.info("Some error occured, please try again");
													}
												}
												catch(Exception ex)
												{
													log.error("Some error occured, please try again." + ex);
												}
												break;
											}
											case 4:
											{
												try
												{
													sc.nextLine();
													log.info("\nCreate New User Account, Enter required details.");
													log.info("=====================");
													log.info("Enter Username ");
													String userName = sc.nextLine();
													log.info("Enter Password ");
													String passWord = sc.nextLine();
													log.info("Enter Customer Name");
													String name = sc.nextLine();
													log.info("Enter Mobile Number");
													long mobile = Long.parseLong(sc.next());
													log.info("Enter Age");
													int age = Integer.parseInt(sc.next());
													sc.nextLine();
													log.info("Enter City");
													String city = sc.nextLine();
													Customer customer = new Customer(userName, passWord, name, mobile, age, city);
													//sc.nextLine();
													
													boolean status = userService.Register(customer);
													
													if(status)
													{
														log.info("\nCustomer Successfully Registered...!!!");
													}
												}
												catch(Exception ex)
												{
													log.error("Error in User Registeration main method : " + ex);
												}
												break;
											}
											case 5:
											{
												log.info("Thankyou for using Coin bank.\n");
												break;
											}
											default :
											{
												log.info("Invalid Input, Please try again.\n");
												break;
											}
										}
									}
									catch(Exception ex)
									{
										log.error("Employee Option Error : " + ex.getMessage());
									}
								}while(employeeChoice != 5);
							}
						}
						catch(Exception ex)
						{
							log.error("Login Error : " + ex.getMessage());
						}
						sc.nextLine();
						break;
					}
					case 3:
					{
						try
						{
							log.info("\nCreate your account");
							log.info("=====================");
							log.info("Enter Username ");
							String username = sc.nextLine();
							log.info("Enter Password ");
							String password = sc.nextLine();
							log.info("Enter Your Name");
							String name = sc.nextLine();
							log.info("Enter Mobile Number");
							long mobile = Long.parseLong(sc.next());
							log.info("Enter Age");
							int age = Integer.parseInt(sc.next());
							sc.nextLine();
							log.info("Enter City");
							String city = sc.nextLine();
							Customer customer = new Customer(username, password, name, mobile, age, city);
							//sc.nextLine();
							
							boolean status = userService.Register(customer);
							
							if(status)
							{
								log.info("\nCustomer Successfully Registered...!!!");
							}
						}
						catch(Exception ex)
						{
							log.error("Error in User Registeration main method : " + ex.getMessage());
						}
						break;
					}
					case 4:
					{
						log.info("Thankyou for using Coin Bank, please visit again.\n");
						break;
					}
					default :
					{
						log.info("Invalid Input, Please try again.\n");
						break;
					}
				}
			}
			catch(NumberFormatException ex)
			{
				log.error("Invalid input : " + ex.getMessage());
			}

		}while(choice != 4);
		
		sc.close();
	}
}