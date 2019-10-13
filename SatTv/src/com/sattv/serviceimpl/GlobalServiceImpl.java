package com.sattv.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;

import com.sattv.model.Account;
import com.sattv.model.Channel;
import com.sattv.model.Pack;
import com.sattv.model.Service;
import com.sattv.model.Subscription;
import com.sattv.model.User;
import com.sattv.service.GlobalService;

public class GlobalServiceImpl implements GlobalService
{

	@Override
	public User validateUser(String userName, String Password) {
		
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		try {

			user = mapper.readValue(new File("DummyData/user.json"), User.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(user.getUserName().equals(user.getEmail()) && user.getPassword().equals(Password))
			return user;
		
		return new User();
		
	}

	public void updateUser(User user,Account account, Subscription sub)
	{
		addUser(user,account, sub);
		
	}

	@Override
	public void addUser(User user, Account account, Subscription sub) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("DummyData/account.json"), account);
			mapper.writeValue(new File("DummyData/user.json"), user);
			mapper.writeValue(new File("DummyData/subscription.json"), sub);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public User rechargeAccount(User user,Double rechargeAmount) 
	{
		Account account = user.getAccount();
		Subscription sub = account.getSubscription();
		account.setBalance(account.getBalance()+rechargeAmount);
		updateUser(user, account, sub);
		
		return user;
	}

	@Override
	public List<Pack> getAllPacks() {
		ObjectMapper mapper = new ObjectMapper();
		List<Pack> packList = new ArrayList<>();
		try {
			List list = mapper.readValue(new File("DummyData/pack.json"), List.class);
			
			Function<Object, Pack> mapPackFunction = obj ->
			{
				HashMap<String, String> map = (HashMap<String, String>) obj;
				Pack p = new Pack();
				for(String key : map.keySet())
				{
					if(key.equals("packId"))
						p.setPackId(Long.valueOf(String.valueOf(map.get(key))));						
					if(key.equals("packAmount"))
						p.setPackAmount(Double.valueOf(String.valueOf(map.get(key))));
					if(key.equals("packName"))
						p.setPackName(map.get(key));
					if(key.equals("channelList"))
					{
						String s = String.valueOf(map.get(key));
						List<Channel> channelList = new ArrayList<>();
						String channelArr[] = s.split("},");
						for(String pack : channelArr)
						{
							Channel c = new Channel();
							String tokens[] = pack.split(",");
							for(String str : tokens)
							{
								String val[] = str.split("=");
								if(val[0].contains("channelId"))
									c.setChannelId(Long.valueOf(val[1]));					
								if(val[0].contains("channelFees"))
									c.setChannelFees(Double.valueOf(val[1]));
								if(val[0].contains("channelName"))
									c.setChannelName(val[1]);
							}
							channelList.add(c);
							
						}
						p.setChannelList(channelList);
					
					}
	
				}
				return p;
				
			};
			
			
			packList = (List<Pack>) list.stream().map(mapPackFunction).collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return packList;
	}

	@Override
	public List<Channel> getAllChannels() {
		ObjectMapper mapper = new ObjectMapper();
		List<Channel> channelList = new ArrayList<>();
		try {
			List list = mapper.readValue(new File("DummyData/channel.json"), List.class);
			
			Function<Object, Channel> mapchannelFunction = obj ->
			{
				HashMap<String, String> map = (HashMap<String, String>) obj;
				Channel c = new Channel();
				for(String key : map.keySet())
				{
					if(key.equals("channelId"))
						c.setChannelId(Long.valueOf(String.valueOf(map.get(key))));						
					if(key.equals("channelFees"))
						c.setChannelFees(Double.valueOf(String.valueOf(map.get(key))));
					if(key.equals("channelName"))
						c.setChannelName(map.get(key));
	
				}
				return c;
				
			};
			
			
			channelList = (List<Channel>) list.stream().map(mapchannelFunction).collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return channelList;
	}

	@Override
	public List<Service> getAllServices() {
		ObjectMapper mapper = new ObjectMapper();
		List<Service> serviceList = new ArrayList<>();
		try {
			List list = mapper.readValue(new File("DummyData/service.json"), List.class);
			
			Function<Object, Service> mapserviceFunction = obj ->
			{
				HashMap<String, String> map = (HashMap<String, String>) obj;
				Service s = new Service();
				for(String key : map.keySet())
				{
					if(key.equals("serviceId"))
						s.setServiceId(Long.valueOf(String.valueOf(map.get(key))));						
					if(key.equals("serviceFees"))
						s.setServiceFees(Double.valueOf(String.valueOf(map.get(key))));
					if(key.equals("serviceName"))
						s.setServiceName(map.get(key));
	
				}
				return s;
				
			};
			
			
			serviceList = (List<Service>) list.stream().map(mapserviceFunction).collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return serviceList;
	}

	@Override
	public Pack getPack(Long packId) {
		List<Pack> packList = getAllPacks();
		
		Optional<Pack> pack = packList.stream().filter(p -> p.getPackId().equals(packId)).findAny();
		if(pack.isPresent())
			return pack.get();
		
		return new Pack();
		
	}

	@Override
	public Channel getChannel(Long channelId) {
		List<Channel> channelList = getAllChannels();
		
		Optional<Channel> channel = channelList.stream().filter(c -> c.getChannelId().equals(channelId)).findAny();
		if(channel.isPresent())
			return channel.get();
		
		return new Channel();
	}

	@Override
	public Service getService(Long serviceId) {
		List<Service> serviceList = getAllServices();
		
		Optional<Service> service = serviceList.stream().filter(s -> s.getServiceId().equals(serviceId)).findAny();
		if(service.isPresent())
			return service.get();
		
		return new Service();
	}

	@Override
	public void updateUser(User user) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("DummyData/user.json"), user);
		} catch (IOException e) {
			e.printStackTrace();
		
	}

	}
}
