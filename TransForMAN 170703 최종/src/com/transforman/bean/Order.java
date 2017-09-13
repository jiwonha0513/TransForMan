package com.transforman.bean;

public class Order 
{
	String orderId;
	String receiverName;
	String receiverPhone;
	int receiverPostcode;
	String receiverAddress1;
	String receiverAddress2;
	String MemberId;
	
	public String getOrderId() 
	{
		return orderId;
	}
	
	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}
	
	public String getReceiverName() 
	{
		return receiverName;
	}
	
	public void setReceiverName(String receiverName) 
	{
		this.receiverName = receiverName;
	}
	
	public String getReceiverPhone() 
	{
		return receiverPhone;
	}
	
	public void setReceiverPhone(String receiverPhone) 
	{
		this.receiverPhone = receiverPhone;
	}
	
	public int getReceiverPostcode() 
	{
		return receiverPostcode;
	}
	
	public void setReceiverPostcode(int receiverPostcode) 
	{
		this.receiverPostcode = receiverPostcode;
	}
	
	public String getReceiverAddress1() 
	{
		return receiverAddress1;
	}
	
	public void setReceiverAddress1(String receiverAddress1) 
	{
		this.receiverAddress1 = receiverAddress1;
	}
	
	public String getReceiverAddress2() 
	{
		return receiverAddress2;
	}
	
	public void setReceiverAddress2(String receiverAddress2) 
	{
		this.receiverAddress2 = receiverAddress2;
	}
	
	public String getMemberId() 
	{
		return MemberId;
	}
	
	public void setMemberId(String memberId) 
	{
		MemberId = memberId;
	}
}
