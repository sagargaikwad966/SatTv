package com.sattv.model;

import java.util.List;

public class Pack 
{
	
	private Long packId;
	private Double packAmount;
	private String packName;
	private List<Channel> channelList;


	public Pack() {
		super();
	}
	public Pack(Long packId, Double packAmount, String packName, List<Channel> channelList) {
		super();
		this.packId = packId;
		this.packAmount = packAmount;
		this.packName = packName;
		this.channelList = channelList;
	}
	public List<Channel> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}
	public Long getPackId() {
		return packId;
	}
	public void setPackId(Long packId) {
		this.packId = packId;
	}
	public Double getPackAmount() {
		return packAmount;
	}
	public void setPackAmount(Double packAmount) {
		this.packAmount = packAmount;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	@Override
	public String toString() {
		return "Pack [packId=" + packId + ", packAmount=" + packAmount + ", packName=" + packName + ", channelList="
				+ channelList + "]";
	}
	
	
	

}
