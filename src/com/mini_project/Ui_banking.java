package com.mini_project;

import java.util.*;


public class Ui_banking {

	public static void main(String[] args) 
	{	
		Scanner scan=new Scanner(System.in);
		int pin;
		String name;
		int ch;
		while(true)
		{
			System.out.println("*****************************************************");
			System.out.println("");
			System.out.println("---------Welcome to Eazy Bank--------->");
			System.out.println("1. Login");
			System.out.println("2. Create New Account");
			System.out.println("3. Exit");
			System.out.println("");
			System.out.println("------------X-------------X------------X-------------");
			System.out.println("");
			try
			{
				System.out.println("*****************************************************");
				System.out.println("Enter your choice->  ");
				ch=scan.nextInt();
				if(ch==1)
				{
					try
					{
						System.out.println("Enter Your Name-> ");
						name=scan.nextLine();
						name+=scan.nextLine();
						
						System.out.println("Enter PIN-> ");
						pin=scan.nextInt();
						Transfer transfer=new Transfer();
						transfer.setUser_name(name);
						transfer.setPin(pin);
						Access access=new Access();
						if(access.loginAccount(transfer))
						{
							System.out.println("Login Successful.");
							System.out.println("------------X-------------X------------X-------------");
							System.out.println("");
						}
						else 
						{
							System.out.println("Login Failed!");
							System.out.println("------------X-------------X------------X-------------");
							System.out.println("");						
						}
						
					}catch(Exception e)
					{
						System.out.println("Please Enter Valid Data, Login Failed! ");
						System.out.println("------------X-------------X------------X-------------");
						System.out.println("");
					}
					
				
				}
				else if(ch==2)
				{
					try
					{
					System.out.println("Enter New Name-> ");
					name=scan.nextLine();
					name+=scan.nextLine();
					System.out.println("Enter New PIN-> ");
					pin=scan.nextInt();
					Access access=new Access();
					Transfer transfer=new Transfer();
					transfer.setUser_name(name);
					transfer.setPin(pin);
					if(access.createAccount(transfer))
					{
						System.out.println("Account Created Sucessfully.");
						System.out.println("------------X-------------X------------X-------------");
						System.out.println("");
						
					}
					else
					{
						System.out.println("Account Creation Failed!");
						System.out.println("------------X-------------X------------X-------------");
						System.out.println("");
						break;
					}
					}catch(Exception e)
					{
						System.out.println("Please Enter Valid Data, Login Failed!");
						System.out.println("------------X-------------X------------X-------------");
						System.out.println("");
						break;
					}
				}
				else if(ch==3)
				{
					System.out.println("Exit Successful, Thank You");
					System.out.println("------------X-------------X------------X-------------");
					System.out.println("");
					break;
				}
				else 
				{
					System.out.println("Invalid Input");
					System.out.println("------------X-------------X------------X-------------");
					System.out.println("");
					break;
				}
				
				
				
				
				
				
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
	}

}