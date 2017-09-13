package com.transforman.bean;

public class Forward
{
	//redirect포워딩:true, dis..포워딩:false
	private boolean isRedirect = false;
	private String path = null;
	
	public boolean isRedirect() 
	{
		return isRedirect;
	}
	
	public void setRedirect(boolean isRedirect) 
	{
		this.isRedirect = isRedirect;
	}
	
	public String getPath() 
	{
		return path;
	}
	
	public void setPath(String path) 
	{
		this.path = path;
	}
	
}