package com.sattv.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Subscription 
{
	
	private Long subscriptionId;
	private Double amount;
	private String expireDate;
	private Pack basePack;
	private List<Channel> channelList;
	private List<Service> serviceList;
	
	
	
	
	public Subscription(Long subscriptionId, Double amount, String expireDate, Pack basePack,
			List<Channel> channelList, List<Service> serviceList) {
		super();
		this.subscriptionId = subscriptionId;
		this.amount = amount;
		this.expireDate = expireDate;
		this.basePack = basePack;
		this.channelList = channelList;
		this.serviceList = serviceList;
	}
	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public Long getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Pack getBasePack() {
		return basePack;
	}
	public void setBasePack(Pack basePack) {
		this.basePack = basePack;
	}
	public List<Channel> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}
	public List<Service> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}
	@Override
	public String toString() {
		return "Subscription [subscriptionId=" + subscriptionId + ", amount=" + amount + ", basePack=" + basePack
				+ ", channelList=" + channelList + ", serviceList=" + serviceList + "]";
	}

	
}
