package com.transforman.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.transforman.bean.Forward;
import com.transforman.service.OrderService;

@WebServlet({"/orderForm", "/insertMyOrder", "/showMyOrder", "/showOrderComplete", "/showOrderList", "/checkOrderForm"})
public class OrderController extends HttpServlet 
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
		System.out.println("OrderController : " + url);
		
		Forward forward = null;
		
		OrderService os = null;
		
		switch(url)
		{
		case "/orderForm":
			os = new OrderService(request, response);
			forward = os.execute(1);
			break;
			
		case "/insertMyOrder":
			os = new OrderService(request, response);
			forward = os.execute(2);
			break;
			
		case "/showMyOrder":
			os = new OrderService(request, response);
			forward = os.execute(3);
			break;
			
		case "/deleteMyOrder":
			os = new OrderService(request, response);
			forward = os.execute(4);
			break;
			
		case "/showOrderComplete":
			os = new OrderService(request, response);
			forward = os.execute(5);
			break;
			
		case "/showOrderList":
			os = new OrderService(request, response);
			forward = os.execute(6);
			break;
			
		case "/checkOrderForm":
			os = new OrderService(request, response);
			forward = os.execute(7);
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
