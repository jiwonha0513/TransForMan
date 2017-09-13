package com.transforman.bean;

import java.util.ArrayList;

public class Styling 
{
	String stylingId;
	String stylingName;
	String explain;
	String imageName;
	String videoUrl;
	int menuId;
	int categoryId;
	ArrayList<HashTag> hashTag;
	ArrayList<Product> product;
	String memberId;
	
	public String getStylingId() 
	{
		return stylingId;
	}
	
	public void setStylingId(String stylingId) 
	{
		this.stylingId = stylingId;
	}
	
	public String getStylingName() 
	{
		return stylingName;
	}
	
	public void setStylingName(String stylingName)
	{
		this.stylingName = stylingName;
	}
	
	public String getExplain() 
	{
		return explain;
	}
	
	public void setExplain(String explain) 
	{
		this.explain = explain;
	}
	
	public String getImageName() 
	{
		return imageName;
	}
	
	public void setImageName(String imageName) 
	{
		this.imageName = imageName;
	}
	
	public String getVideoUrl() 
	{
		return videoUrl;
	}
	
	public void setVideoUrl(String videoUrl) 
	{
		this.videoUrl = videoUrl;
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
	
	public ArrayList<HashTag> getHashTag() {
		return hashTag;
	}

	public void setHashTag(ArrayList<HashTag> hashTag) {
		this.hashTag = hashTag;
	}

	public ArrayList<Product> getProduct() {
		return product;
	}

	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
