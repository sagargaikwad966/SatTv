package com.sattv;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.sattv.model.Account;
import com.sattv.model.Channel;
import com.sattv.model.Pack;
import com.sattv.model.Service;
import com.sattv.model.Subscription;
import com.sattv.model.User;
import com.sattv.service.GlobalService;
import com.sattv.serviceimpl.GlobalServiceImpl;

public class SatTVDataBuilder {

	public static void main(String args[])
	{
		GlobalService globalService = new GlobalServiceImpl();

		AddChannel.addChannel();
		AddService.addService();
		AddPack.addPack();
		
		Subscription sub = new Subscription();
		sub.setSubscriptionId(null);
		sub.setBasePack(new Pack());
		LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
		sub.setExpireDate(date.toString());
		sub.setAmount(0.0);
		sub.setChannelList(new ArrayList<Channel>());
		sub.setServiceList(new ArrayList<Service>());
		
		System.out.println(sub.getExpireDate());
		Account account = new Account();
		account.setAccountId(1111L);
		account.setAccountNumber("sagar1111");
		account.setBalance(100.00);
		account.setSubscription(sub);
		
		User user = new User();
		user.setUserId(101L);
		user.setUserName("sagargaikwad966@gmail.com");
		user.setEmail("sagargaikwad966@gmail.com");
		user.setContact("8956223030");
		user.setPassword("sagar123");
		user.setAccount(account);
		
		globalService.addUser(user, account, sub);
		

	}

}
