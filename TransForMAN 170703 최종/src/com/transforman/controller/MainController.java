package com.transforman.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.transforman.bean.Forward;
import com.transforman.service.MainService;

@WebServlet({"/index", "/loginForm", "/login", "/logout", "/findInfoForm", "/selectId", "/sendCode", 
	"/updatePwForm", "/updatePw", "/showMyInfo", "/updateInfoForm", "/updateInfo", "/deleteMember", 
	"/joinForm", "/insertMember", "/checkJoinForm", "/checkLoginForm", "/checkFindInfoForm", 
	"/checkUpdatePwForm", "/checkUpdateInfoForm", "/checkDeleteMember"})

public class MainController extends HttpServlet 
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
		System.out.println("MainController : " + url);
		
		Forward forward = null;
		
		MainService ms = null;
		
		switch(url)
		{
		case "/index":
			ms = new MainService(request, response);
			forward = ms.execute(0);
			break;
		
		case "/loginForm":
			ms = new MainService(request, response);
			forward = ms.execute(1);
			break;
			
		case "/login":
			ms = new MainService(request, response);
			forward = ms.execute(2);
			break;
			
		case "/logout":
			ms = new MainService(request, response);
			forward = ms.execute(3);
			break;
			
		case "/findInfoForm":
			ms = new MainService(request, response);
			forward = ms.execute(4);
			break;
			
		case "/selectId":
			ms = new MainService(request, response);
			forward = ms.execute(5);
			break;
			
		case "/sendCode":
			ms = new MainService(request, response);
			forward = ms.execute(6);
			break;
			
		case "/updatePwForm":
			ms = new MainService(request, response);
			forward = ms.execute(7);
			break;
			
		case "/updatePw":
			ms = new MainService(request, response);
			forward = ms.execute(8);
			break;
			
		case "/showMyInfo":
			ms = new MainService(request, response);
			forward = ms.execute(9);
			break;
			
		case "/updateInfoForm":
			ms = new MainService(request, response);
			forward = ms.execute(10);
			break;
			
		case "/updateInfo":
			ms = new MainService(request, response);
			forward = ms.execute(11);
			break;
			
		case "/deleteMember":
			ms = new MainService(request, response);
			forward = ms.execute(12);
			break;
			
		case "/joinForm":
			ms = new MainService(request, response);
			forward = ms.execute(13);
			break;
			
		case "/insertMember":
			ms = new MainService(request, response);
			forward = ms.execute(14);
			break;
			
		case "/checkJoinForm":
			ms = new MainService(request, response);
			forward = ms.execute(15);
			break;
		
		case "/checkLoginForm":
			ms = new MainService(request, response);
			forward = ms.execute(16);
			break;
			
		case "/checkFindInfoForm":
			ms = new MainService(request, response);
			forward = ms.execute(17);
			break;
			
		case "/checkUpdatePwForm":
			ms = new MainService(request, response);
			forward = ms.execute(18);
			break;
			
		case "/checkUpdateInfoForm":
			ms = new MainService(request, response);
			forward = ms.execute(19);
			break;
			
		case "/checkDeleteMember":
			ms = new MainService(request, response);
			forward = ms.execute(20);
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
