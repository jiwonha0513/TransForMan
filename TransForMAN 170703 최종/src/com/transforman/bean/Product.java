package com.transforman.bean;

import java.util.ArrayList;

public class Product 
{
	String productId;
	String productName;
	int count;
	int price;
	String explain;
	int salesVolum = 0;
	String imageName;
	int menuId;
	int categoryId;
	ArrayList<HashTag> hashTag;
	String memberId;
	
	public String getProductId() 
	{
		return productId;
	}
	
	public void setProductId(String productId) 
	{
		this.productId = productId;
	}
	
	public String getProductName() 
	{
		return productName;
	}
	
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}
	
	public int getCount() 
	{
		return count;
	}
	
	public void setCount(int count) 
	{
		this.count = count;
	}
	
	public int getPrice() 
	{
		return price;
	}
	
	public void setPrice(int price) 
	{
		this.price = price;
	}
	
	public String getExplain() 
	{
		return explain;
	}
	
	public void setExplain(String explain) 
	{
		this.explain = explain;
	}
	
	public int getSalesVolum() 
	{
		return salesVolum;
	}
	
	public void setSalesVolum(int salesVolum) 
	{
		this.salesVolum = salesVolum;
	}
	
	public String getImageName() 
	{
		return imageName;
	}
	
	public void setImageName(String imageName) 
	{
		this.imageName = imageName;
	}
	
	public int getMenuId() 
	{
		return menuId;
	}
	
	public void setMenuId(int menuId) 
	{
		this.menuId = menuId;
	}
	
	public int getCategoryId() 
	{
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) 
	{
		this.categoryId = categoryId;
	}
	
	public ArrayList<HashTag> getHashTag() 
	{
		return hashTag;
	}
	
	public void setHashTag(ArrayList<HashTag> hashTag) 
	{
		this.hashTag = hashTag;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
