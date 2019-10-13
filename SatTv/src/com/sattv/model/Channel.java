package com.sattv.model;

public class Channel 
{
	private Long channelId;
	private Double channelFees;
	private String channelName;
	
	
	
	
	public Channel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Channel(Long channelId, Double channelFees, String channelName) {
		super();
		this.channelId = channelId;
		this.channelFees = channelFees;
		this.channelName = channelName;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public Double getChannelFees() {
		return channelFees;
	}
	public void setChannelFees(Double channelFees) {
		this.channelFees = channelFees;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	@Override
	public String toString() {
		return "Channel [channelId=" + channelId + ", channelFees=" + channelFees + ", channelName=" + channelName
				+ "]";
	}
	
	
	
	

}
