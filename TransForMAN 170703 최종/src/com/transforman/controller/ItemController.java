package com.transforman.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.transforman.bean.Forward;
import com.transforman.service.ItemService;

@WebServlet({"/registerProductForm", "/registerStylingForm", "/insertProduct", "/insertStyling", 
	"/updateProduct", "/updateStyling", "/deleteProduct", "/deleteStyling",
	"/checkRegisterProductForm", "/checkRegisterStylingForm", "/showStylingList", 
	"/showProductList", "/updateStylingForm", "/updateProductForm"})

public class ItemController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		String url = uri.substring(contextPath.length());
		System.out.println("ItemController : " + url);
		
		Forward forward = null;
		
		ItemService is = null;
		
		switch(url)
		{
		case "/registerProductForm":
			is = new ItemService(request, response);
			forward = is.execute(1);
			break;
			
		case "/registerStylingForm":
			is = new ItemService(request, response);
			forward = is.execute(2);
			break;
			
		case "/insertProduct":
			is = new ItemService(request, response);
			forward = is.execute(3);
			break;
			
		case "/insertStyling":
			is = new ItemService(request, response);
			forward = is.execute(4);
			break;
			
		case "/updateProduct":
			is = new ItemService(request, response);
			forward = is.execute(5);
			break;
			
		case "/updateStyling":
			is = new ItemService(request, response);
			forward = is.execute(6);
			break;
			
		case "/deleteProduct":
			is = new ItemService(request, response);
			forward = is.execute(7);
			break;
			
		case "/deleteStyling":
			is = new ItemService(request, response);
			forward = is.execute(8);
			break;

		case "/checkRegisterProductForm":
			is = new ItemService(request, response);
			forward = is.execute(9);
			break;
			
		case "/checkRegisterStylingForm":
			is = new ItemService(request, response);
			forward = is.execute(10);
			break;
			
		case "/showStylingList":
			is = new ItemService(request, response);
			forward = is.execute(11);
			break;
			
		case "/showProductList":
			is = new ItemService(request, response);
			forward = is.execute(12);
			break;
			
		case "/updateStylingForm":
			is = new ItemService(request, response);
			forward = is.execute(13);
			break;
			
		case "/updateProductForm":
			is = new ItemService(request, response);
			forward = is.execute(14);
			break;
		}
		
		if(forward != null)
		{
			String path = forward.getPath();
			if(forward.isRedirect())
			{
				System.out.println("sendRedirect => path : " + path);
				response.sendRedirect(path);
			}
			else
			{
				System.out.println("forward => path : " + path);
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
			}
		}
	}
}
