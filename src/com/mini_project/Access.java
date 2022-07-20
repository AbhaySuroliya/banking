package com.mini_project;

import java.sql.*;
import java.util.*;

public class Access
{
	static Connection connect=AConnection.getConnection();
	static String sql="";
	
	public boolean loginAccount(Transfer transfer) throws Exception 
	{
		try
		{
		String u_name=transfer.getUser_name().toUpperCase();
		Integer pin=transfer.getPin();
		if(u_name=="" || pin==0)
		{
			System.out.println("ALl fields Required");
			return false;
		}
		sql="select * from banking_data where user_name='"+u_name+"' and pin="+pin;
		PreparedStatement st=connect.prepareStatement(sql);
		ResultSet rs=st.executeQuery();
		Scanner scan=new Scanner(System.in);
		
		if(rs.next())
		{
			int snt=rs.getInt("account_no");
			int cho=0;
			while(true)
			{
				try
				{
				System.out.println("Loged in successful");
				System.out.println("Hello, "+rs.getString("user_name"));
				System.out.println("1. Check Balance");
				System.out.println("2. Withdraw Amount");
				System.out.println("3. Deposit Amount");
				System.out.println("4. logout");
				System.out.println("Enter your choice : ");
				cho=scan.nextInt();
				if(cho==1)
				{
					Access access=new Access();
					access.getBalance(snt);
				}
				else if(cho==2)
				{ 	
					Access access=new Access();
					access.withdrawAmount(snt);
					
				}
				else if(cho==3)
				{
					Access access=new Access();
					access.depositAmount(snt);
				}
				else
				{
					System.out.println("Thank you, Have a Nice Day :) ");
					break;
				}
				
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				{
					
				}
			}
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean createAccount(Transfer transfer) throws Exception 
	{
		String u_name=transfer.getUser_name().toUpperCase();
		Integer pin=transfer.getPin();
		try 
		{
			if(u_name=="" || pin==0)
			{
				System.out.println("All Fields Required");
				return false;
			}
			
			Statement st=connect.createStatement();
			sql="insert into banking_data(account_no,user_name,acc_balance,pin) values (account_no.nextval,'"+u_name+"','500',"+pin+")";
			if(st.executeUpdate(sql)==1)
			{
				System.out.println(" Welcome, "+u_name);
				return true;
			}
		}catch (SQLIntegrityConstraintViolationException e) 
		{
            System.out.println("Username Not Available!");
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	
	

	
	public void getBalance(int accountNumber) throws Exception 
	{
		try
		{
			sql="select * from banking_data where account_no="+accountNumber;
			PreparedStatement st=connect.prepareStatement(sql);
			ResultSet rs=st.executeQuery(sql);
			System.out.printf("%12s %10s %10s\n","Account No", "Name","Balance");
			while (rs.next()) {
                System.out.printf("%12d %10s %10s\n",rs.getInt("account_no"),rs.getString("user_name"),rs.getString("acc_balance"));
            }
            System.out.println("-----------------------------------------------------------\n");
			
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

	
	public void withdrawAmount(int accountNumber) throws Exception 
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			sql="select * from banking_data where account_no="+accountNumber;
			PreparedStatement st=connect.prepareStatement(sql);
			ResultSet rs=st.executeQuery(sql);
		
			while(rs.next())
			{
				System.out.println("Your Available Balance is : "+rs.getString("acc_balance"));
				System.out.println("Enter the withdrawing amount : ");
				int withdraw=sc.nextInt();
				if(Integer.parseInt(rs.getString("acc_balance"))<withdraw || withdraw<=0)
				{
					System.out.println("Invalid amount, Insufficient Balance");
					return;
				}
				int finalAmount=Integer.parseInt(rs.getString("acc_balance"))-withdraw;
				Statement sta=connect.createStatement();
				sql="update banking_data set acc_balance='"+finalAmount+"' where account_no="+accountNumber;
				sta.executeUpdate(sql);
				System.out.println("Withdrawing Successfull..!");
				System.out.println("Remaining Account Balance");
				System.out.println();
				Access access=new Access();
				access.getBalance(accountNumber);
			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public void depositAmount(int accountNumber) throws Exception 
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			sql="select * from banking_data where account_no="+accountNumber;
			PreparedStatement st=connect.prepareStatement(sql);
			ResultSet rs=st.executeQuery(sql);
		
			while(rs.next())
			{
				System.out.println("Your Available Balance is : "+rs.getString("acc_balance"));
				System.out.println("Enter the deposit amount : ");
				int deposit=sc.nextInt();
				if(deposit<=0)
				{
					System.out.println("Invalid amount");
					return;
				}
				int finalAmount=Integer.parseInt(rs.getString("acc_balance"))+deposit;
				Statement sta=connect.createStatement();
				sql="update banking_data set acc_balance='"+finalAmount+"' where account_no="+accountNumber;
				sta.executeUpdate(sql);
				System.out.println("Deposit Successfull..!");
				System.out.println("Available Account Balance");
				System.out.println();
				Access access=new Access();
				access.getBalance(accountNumber);
			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	}