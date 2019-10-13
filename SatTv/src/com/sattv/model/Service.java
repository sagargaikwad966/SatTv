package com.sattv.model;

public class Service 
{
	private Long serviceId;
	private Double serviceFees;
	private String serviceName;
	
	
	
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Service(Long serviceId, Double serviceFees, String serviceName) {
		super();
		this.serviceId = serviceId;
		this.serviceFees = serviceFees;
		this.serviceName = serviceName;
	}


	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long servicelId) {
		this.serviceId = servicelId;
	}
	public Double getServiceFees() {
		return serviceFees;
	}
	public void setServiceFees(Double serviceFees) {
		this.serviceFees = serviceFees;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	@Override
	public String toString() {
		return "Service [servicelId=" + serviceId + ", serviceFees=" + serviceFees + ", serviceName=" + serviceName
				+ "]";
	}
	
	

}
