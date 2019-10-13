package com.sattv.service;

import java.util.List;

import com.sattv.model.Account;
import com.sattv.model.Channel;
import com.sattv.model.Pack;
import com.sattv.model.Service;
import com.sattv.model.Subscription;
import com.sattv.model.User;

public interface GlobalService 
{
	public User validateUser(String userName, String Password);
	public void updateUser(User user, Account account, Subscription sub);
	public void addUser(User user, Account account, Subscription sub);
	public User rechargeAccount(User user,Double rechargeAmount);
	public List<Pack> getAllPacks();
	public List<Channel> getAllChannels();
	public List<Service> getAllServices();
	public Pack getPack(Long packId);
	public Channel getChannel(Long valueOf);
	public Service getService(Long valueOf);
	public void updateUser(User user);

}
