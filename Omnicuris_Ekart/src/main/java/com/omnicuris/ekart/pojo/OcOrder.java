package com.omnicuris.ekart.pojo;

public class OcOrder extends BusinessContext
{
	private Long orderId;
	
	private String orderStatus;

	public Long getOrderId() 
	{
		return orderId;
	}

	public void setOrderId(Long orderId) 
	{
		this.orderId = orderId;
	}

	public String getOrderStatus() 
	{
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) 
	{
		this.orderStatus = orderStatus;
	}
}
