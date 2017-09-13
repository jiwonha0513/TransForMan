package com.transforman.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.transforman.bean.Forward;
import com.transforman.service.StylingService;

@WebServlet({"/stBeauty", "/stHair", "/stMakeup", "/stCoordi", "/stDaily", 
	"/stBoyfriend", "/stVacance", "/stDetail", "/stInsertComment", "/stDeleteComment", 
	"/stSearchStyling"})
public class StylingController extends HttpServlet 
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
		System.out.println("StylingController : " + url);
		
		Forward forward = null;
		StylingService ss = null;
		
		switch(url)
		{
		case "/stBeauty":
			ss = new StylingService(request, response);
			forward = ss.execute(1);
			break;
			
		case "/stHair":
			ss = new StylingService(request, response);
			forward = ss.execute(2);
			break;
			
		case "/stMakeup":
			ss = new StylingService(request, response);
			forward = ss.execute(3);
			break;
			
		case "/stCoordi":
			ss = new StylingService(request, response);
			forward = ss.execute(4);
			break;
			
		case "/stDaily":
			ss = new StylingService(request, response);
			forward = ss.execute(5);
			break;
			
		case "/stBoyfriend":
			ss = new StylingService(request, response);
			forward = ss.execute(6);
			break;
			
		case "/stVacance":
			ss = new StylingService(request, response);
			forward = ss.execute(7);
			break;
			
		case "/stDetail":
			ss = new StylingService(request, response);
			forward = ss.execute(8);
			break;
			
		case "/stInsertComment":
			ss = new StylingService(request, response);
			forward = ss.execute(9);
			break;
			
		case "/stDeleteComment":
			ss = new StylingService(request, response);
			forward = ss.execute(10);
			break;
			
		case "/stSearchStyling":
			ss = new StylingService(request, response);
			forward = ss.execute(11);
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
