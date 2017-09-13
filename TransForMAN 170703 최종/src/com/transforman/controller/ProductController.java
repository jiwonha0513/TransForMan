package com.transforman.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.transforman.bean.Forward;
import com.transforman.service.ProductService;


@WebServlet({"/spBeauty", "/spHair", "/spMakeup", "/spTop", "/spTshirt", 
	"/spShirt", "/spHood", "/spBottoms", "/spJeans", "/spPants", "/spTraning", 
	"/spOuter", "/spAccessory", "/spDetail", "/spInsertComment", "/spDeleteComment", "/spSearchProduct"})
public class ProductController extends HttpServlet 
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
		System.out.println("ProductController : " + url);

		Forward forward = null;
		ProductService ps = null;

		switch(url)
		{
		case "/spBeauty":
			ps = new ProductService(request, response);
			forward = ps.execute(1);
			break;
			
		case "/spHair":
			ps = new ProductService(request, response);
			forward = ps.execute(2);
			break;
			
		case "/spMakeup":
			ps = new ProductService(request, response);
			forward = ps.execute(3);
			break;
			
		case "/spTop":
			ps = new ProductService(request, response);
			forward = ps.execute(4);
			break;
			
		case "/spTshirt":
			ps = new ProductService(request, response);
			forward = ps.execute(5);
			break;
			
		case "/spShirt":
			ps = new ProductService(request, response);
			forward = ps.execute(6);
			break;
			
		case "/spHood":
			ps = new ProductService(request, response);
			forward = ps.execute(7);
			break;
			
		case "/spBottoms":
			ps = new ProductService(request, response);
			forward = ps.execute(8);
			break;
			
		case "/spJeans":
			ps = new ProductService(request, response);
			forward = ps.execute(9);
			break;
			
		case "/spPants":
			ps = new ProductService(request, response);
			forward = ps.execute(10);
			break;
			
		case "/spTraining":
			ps = new ProductService(request, response);
			forward = ps.execute(11);
			break;
			
		case "/spOuter":
			ps = new ProductService(request, response);
			forward = ps.execute(12);
			break;
			
		case "/spCoat":
			ps = new ProductService(request, response);
			forward = ps.execute(13);
			break;
			
		case "/spJacket":
			ps = new ProductService(request, response);
			forward = ps.execute(14);
			break;
			
		case "/spCardigan":
			ps = new ProductService(request, response);
			forward = ps.execute(15);
			break;
			
		case "/spAccessory":
			ps = new ProductService(request, response);
			forward = ps.execute(16);
			break;
			
		case "/spWatch":
			ps = new ProductService(request, response);
			forward = ps.execute(17);
			break;
			
		case "/spSneakers":
			ps = new ProductService(request, response);
			forward = ps.execute(18);
			break;
			
		case "/spShoes":
			ps = new ProductService(request, response);
			forward = ps.execute(19);
			break;

		case "/spDetail":
			ps = new ProductService(request, response);
			forward = ps.execute(20);
			break;

		case "/spInsertComment":
			ps = new ProductService(request, response);
			forward = ps.execute(21);
			break;
			
		case "/spDeleteComment":
			ps = new ProductService(request, response);
			forward = ps.execute(22);
			break;
			
		case "/spSearchProduct":
			ps = new ProductService(request, response);
			forward = ps.execute(23);
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

