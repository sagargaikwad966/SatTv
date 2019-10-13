package com.sattv;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.sattv.model.Channel;
import com.sattv.model.Pack;
import com.sattv.model.Service;
import com.sattv.model.Subscription;
import com.sattv.model.User;
import com.sattv.service.GlobalService;
import com.sattv.serviceimpl.GlobalServiceImpl;

public class SatTVApp {

	public static void main(String[] args) 
	{
		
		GlobalService globalService = new GlobalServiceImpl();
		List<Channel> channelList = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		User user = new User();
		boolean flag = false;
		do
		{

		
		System.out.println("************* Welcome to SatTv D2H Service ************");
		System.out.println("=================Enter your credential=================");
		
		System.out.println("Enter userName : ");
		String userName = scanner.next();
		System.out.println("Enter password : ");
		String password= scanner.next();
		
		user = globalService.validateUser(userName, password);
		if(user.getUserId()==null)
		{
			flag = true;
			System.out.println("You have entered wrong credential......!\nPlease try again...\n");
		}
		
		}while(flag==true);
		
		String continueFlag = new String();
		Subscription sub = new Subscription();
		Pack basePack = new Pack();
		do
		{
			System.out.println("\n---------------------------------------------\n");
			System.out.println("1. Check Balance");
			System.out.println("2. Recharge Account");
			System.out.println("3. View Available Packs, Channels, Services");
			System.out.println("4. Subscribe to Base pack");
			System.out.println("5. Add Channels to Subscription");
			System.out.println("6. Subscribe to special service");
			System.out.println("7. View Current subscription details");
			System.out.println("8. Update Email & Contact details");
			System.out.println("9. Exit");
			System.out.println("\n---------------------------------------------\n");
			System.out.println("Please enter your choice : ");
			int choice = scanner.nextInt();
			
			switch(choice)
			{
				case 1 :
						System.out.println("Hello "+user.getUserName()+"\nyour current balance is "+user.getAccount().getBalance()+" Rs.");
						break;
						
				case 2 : 
						System.out.println("Enter the amount to recharge the account : ");
						Double rechargeAmount = scanner.nextDouble();
						user = globalService.rechargeAccount(user,rechargeAmount);
						System.out.println("Hello "+user.getUserName()+"\nRecharge completed successfully...\nyour current balance is "+user.getAccount().getBalance()+" Rs.");
						break;
						
				case 3 :
						System.out.println("\nAvailable packs for subscription\n");
						List<Pack> packList = globalService.getAllPacks();
						packList.forEach(p ->
						{
							System.out.println(p.getPackId()+" | "+p.getPackName()+" | "+p.getPackAmount()+" Rs.");
							
						});
						
						System.out.println("\nAvailable Channels for subscription\n");
						channelList = globalService.getAllChannels();
						channelList.forEach(c ->
						{
							System.out.println(c.getChannelId()+" | "+c.getChannelName()+" | "+c.getChannelFees()+" Rs.");
							
						});
						
						System.out.println("\nAvailable Services for subscription\n");
						List<Service> serviceList = globalService.getAllServices();
						serviceList.forEach(s ->
						{
							System.out.println(s.getServiceId()+" | "+s.getServiceName()+" | "+s.getServiceFees()+" Rs.");
							
						});

						break;
				
				case 4 :
					Double packTotalAmount = 0.0;
					Double discount = 0.0;
					System.out.println("\nSubscribe to Base pack");
					System.out.println("\nAvailable packs for subscription");
					List<Pack> basePackList = globalService.getAllPacks();
					basePackList.forEach(p ->
					{
						System.out.println(p.getPackId()+" | "+p.getPackName()+" | "+p.getPackAmount()+" Rs.");
						
					});
					System.out.println("\nEnter the Pack Id to subscribe : ");
					Long packId = scanner.nextLong();
					Pack p = globalService.getPack(packId);
					if(p.getPackId()==null)
						System.out.println("\nYou have enter wrong Pack Id\n");
					else
					{
						System.out.println("\nEnter the month count : ");
						int monthCount = scanner.nextInt();
						if(monthCount<=0)
						{
							System.out.println("\nPlease enter valid month");
							break;
						}
						if(monthCount >= 3)
						{
							discount = (monthCount * p.getPackAmount())*0.1;
							packTotalAmount = (monthCount * p.getPackAmount()) - discount;
						}
						else
							packTotalAmount = monthCount * p.getPackAmount();

						if(user.getAccount().getBalance() < packTotalAmount)
						{
							System.out.println("\nYou have insufficient Balance\nPlease recharge your account");
							break;
						}
						
						sub = user.getAccount().getSubscription();
						sub.setSubscriptionId(user.getUserId()+user.getAccount().getAccountId());
						sub.setAmount(sub.getAmount() + p.getPackAmount());
						sub.setBasePack(p);
						LocalDate date = LocalDate.parse(sub.getExpireDate());
						sub.setExpireDate(date.plusMonths(monthCount).toString());
						
						user.getAccount().setBalance(user.getAccount().getBalance() - packTotalAmount);
						globalService.updateUser(user, user.getAccount(), sub);

						System.out.println("\nYou have successfully subscribed the following pack");
						System.out.println("Subscription Id : "+sub.getSubscriptionId());
						System.out.println("Pack Name : "+sub.getBasePack().getPackName());
						System.out.println("Monthly Subscription Amount : "+sub.getAmount());
						System.out.println("Pack Subscription Amount : "+(packTotalAmount+discount));
						System.out.println("Subscription Discount : "+discount);
						System.out.println("Final Deducted Amount : "+packTotalAmount);
						System.out.println("Subscription Expire Date : "+sub.getExpireDate());
						System.out.println("Channel List : "+sub.getChannelList());
						System.out.println("Service List : "+sub.getServiceList());
						System.out.println("Account Balance : "+user.getAccount().getBalance());
						System.out.println("Email Notification sent successfully");
						System.out.println("SMS Notification sent successfully");
					}

					break;
				
				
				case 5 :
						sub = user.getAccount().getSubscription();
						basePack = sub.getBasePack();
						if(basePack.getPackId()==null)
						{
							System.out.println("\nPlease subscribed base pack first");
							break;
						}
						System.out.println("\nAdd Channels to existing Subscription");
						System.out.println("\nAvailable channels to add");
						channelList = globalService.getAllChannels();
						
						channelList.forEach(c ->
						{
							System.out.println(c.getChannelId()+" | "+c.getChannelName()+" | "+c.getChannelFees()+" Rs.");
							
						});
						
						System.out.println("\n Please enter channel Id [separated by commas] : ");
						String channelIds = scanner.next();
						String channelIdList[] = channelIds.split(",");
						for(String channel : channelIdList)
						{
							Channel ch = globalService.getChannel(Long.valueOf(channel));
							if(ch.getChannelId()==null)
								System.out.println("\nYou have enter wrong Channel Id : "+channel);
							else
							{
								if(user.getAccount().getBalance() < ch.getChannelFees())
								{
									System.out.println("\nYou have insufficient Balance\nPlease recharge your account");
									break;
								}
								List<Channel> chList = sub.getChannelList();
								chList.add(ch);
								sub.setChannelList(chList);
								sub.setAmount(sub.getAmount() + ch.getChannelFees());
								user.getAccount().setBalance(user.getAccount().getBalance() - ch.getChannelFees());
							}
								
						}
						globalService.updateUser(user, user.getAccount(), sub);
						System.out.println("Channels added successfully");
						System.out.println("Account Balance : "+user.getAccount().getBalance());
						System.out.println("Email Notification sent successfully");
						System.out.println("SMS Notification sent successfully");
						
						break;
						
				case 6 :
					sub = user.getAccount().getSubscription();
					basePack = sub.getBasePack();
					if(basePack.getPackId()==null)
					{
						System.out.println("\nPlease subscribed base pack first");
						break;
					}
					System.out.println("\nAdd Special Services to existing Subscription");
					System.out.println("\nAvailable Services to add");
					serviceList = globalService.getAllServices();
					
					serviceList.forEach(s ->
					{
						System.out.println(s.getServiceId()+" | "+s.getServiceName()+" | "+s.getServiceFees()+" Rs.");
						
					});
					
					System.out.println("\n Please enter Service Id : ");
					String serviceId = scanner.next();
					Service ser = globalService.getService(Long.valueOf(serviceId));
						if(ser.getServiceId()==null)
							System.out.println("\nYou have enter wrong Service Id : "+serviceId);
						else
						{
							if(user.getAccount().getBalance() < ser.getServiceFees())
							{
								System.out.println("\nYou have insufficient Balance\nPlease recharge your account");
								break;
							}
							
							List<Service> serList = sub.getServiceList();
							serList.add(ser);
							sub.setServiceList(serList);
							sub.setAmount(sub.getAmount() + ser.getServiceFees());
							user.getAccount().setBalance(user.getAccount().getBalance() - ser.getServiceFees());
						}

					globalService.updateUser(user, user.getAccount(), sub);
					System.out.println("Service added successfully");
					System.out.println("Account Balance : "+user.getAccount().getBalance());
					System.out.println("Email Notification sent successfully");
					System.out.println("SMS Notification sent successfully");
					
					break;
				
				case 7 :
					sub = user.getAccount().getSubscription();
					System.out.println("\nView Current Subscription Details ");
					System.out.println("Subscription Amount : "+sub.getAmount()+" Rs.");
					System.out.println("Base Pack : "+sub.getBasePack().getPackName()+" | "+sub.getBasePack().getPackAmount()+" Rs.");
					System.out.print("Channel List : ");
					sub.getChannelList().stream().forEach(ch -> System.out.print(ch.getChannelName()+" : "+ch.getChannelFees()+" Rs."+" , "));
					System.out.println();
					System.out.print("Service List : ");
					sub.getServiceList().stream().forEach(s -> System.out.print(s.getServiceName()+" : "+s.getServiceFees()+" Rs."+" , "));
					System.out.println();
					break;	
				
				case 8 :
					System.out.println("Update Email & Phone number for notifications");
					System.out.println("Enter the Email : ");
					String email = scanner.next();
					System.out.println("Enter the Phone number : ");
					String contact = scanner.next();
					user.setEmail(email);
					user.setContact(contact);
					globalService.updateUser(user);
					
						break;
				default : 
						System.out.println("Please enter the valid option");
						break;
						
			}
			
			
			
			
			System.out.println("Do you want to continue : (yes|y / no|n)?");
			continueFlag = scanner.next();
		
		}while(continueFlag.equalsIgnoreCase("yes") || continueFlag.equalsIgnoreCase("Y"));
	}

}
