package com.transforman.bean;

import java.util.ArrayList;

public class Cart 
{
	String cartId;
	int price;
	ArrayList<Product> product;
	String memberId;
	
	public String getCartId() 
	{
		return cartId;
	}
	
	public void setCartId(String cartId) 
	{
		this.cartId = cartId;
	}
	
	public int getPrice() 
	{
		return price;
	}
	
	public void setPrice(int price) 
	{
		this.price = price;
	}
	
	public ArrayList<Product> getProduct() 
	{
		return product;
	}
	
	public void setProduct(ArrayList<Product> product) 
	{
		this.product = product;
	}
	
	public String getMemberId() 
	{
		return memberId;
	}
	
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}
	
}
