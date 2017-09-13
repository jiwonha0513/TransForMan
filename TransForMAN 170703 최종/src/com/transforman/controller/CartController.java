package com.transforman.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.transforman.bean.Forward;
import com.transforman.service.CartService;

@WebServlet({"/showMyCart", "/insertMyCart", "/deleteMyCartItem", "/deleteMyCartAll"})
public class CartController extends HttpServlet
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
		System.out.println("CartController : " + url);
		
		Forward forward = null;
		
		CartService cs = null;
		
		switch(url)
		{
		case "/showMyCart":
			cs = new CartService(request, response);
			forward = cs.execute(1);
			break;
		
		case "/insertMyCart":
			cs = new CartService(request, response);
			forward = cs.execute(2);
			break;
			
		case "/deleteMyCartItem":
			cs = new CartService(request, response);
			forward = cs.execute(3);
			break;
			
		case "/deleteMyCartAll":
			cs = new CartService(request, response);
			forward = cs.execute(4);
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
