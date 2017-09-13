package com.transforman.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.transforman.bean.Cart;
import com.transforman.bean.Forward;
import com.transforman.bean.Product;
import com.transforman.dao.CartDao;
import com.transforman.dao.ProductDao;

public class CartService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;

	private Forward forward;

	private ProductDao pDao;
	private CartDao cDao;

	public CartService(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request = request;
		this.response = response;
	}

	public Forward execute(int code)
	{
		switch(code)
		{
		case 1:
			showMyCart();
			break;

		case 2:
			insertMyCart();
			break;

		case 3:
			deleteMyCartItem();
			break;

		case 4:
			deleteMyCartAll();
		}

		return forward;
	}

	private void showMyCart()
	{
		pDao = new ProductDao();
		cDao = new CartDao();

		String path = "";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");
			path = "./index";
		}
		else
		{
			String memberId = sessionId.toString();
			
			ArrayList<Product> pList = cDao.selectCartProductList(memberId);
			
			if(pList.size() != 0)
			{
				String myCart = getMyCart(memberId, cDao, pList);
				request.setAttribute("myCart", myCart);
			}
			else
			{
				request.setAttribute("myCart", "장바구니에 담은 상품이 없습니다.");
			}
			path = "showMyCart.jsp";
		}

		cDao.close();
		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void insertMyCart()
	{
		pDao = new ProductDao();
		cDao = new CartDao();

		String path = "";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");
			path = "./index";
		}
		else
		{
			String memberId = sessionId.toString();
			String productId = request.getParameter("data");
			Product p = pDao.selectProduct(productId);
			String strCount = request.getParameter("data2");
			int count = Integer.valueOf(strCount);

			if(count <= p.getCount())
			{
				if(!cDao.isCart(memberId))
				{
					Cart c = new Cart();
					c.setPrice(p.getPrice() * count);
					c.setMemberId(memberId);

					cDao.insertCart(cDao.getCartId(), c);

					String cartId = cDao.selectCartId(memberId);
					cDao.insertCartProduct(cartId, productId, count);
				}
				else
				{
					if(cDao.isCartProduct(productId))
					{
						int currentCount = cDao.selectCartProductCount(memberId, productId);
						if(count + currentCount <= p.getCount())
						{
							Cart c = cDao.selectCart(memberId);
							int price = c.getPrice() + (p.getPrice() * count);
							cDao.updateCart(memberId, price);
							cDao.updateCartProduct(productId, count + currentCount);
						}
						else
						{
							request.setAttribute("msg", "수량 오류");
							request.setAttribute("productId", productId);
						}
					}
					else
					{
						Cart c = cDao.selectCart(memberId);
						int price = c.getPrice() + (p.getPrice() * count);
						cDao.updateCart(memberId, price);
						cDao.insertCartProduct(c.getCartId(), productId, count);
					}
				}

			}
			else
			{
				request.setAttribute("msg", "수량 오류");
				request.setAttribute("productId", productId);
			}
			
			path = "./showMyCart";
		}

		cDao.close();
		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void deleteMyCartItem()
	{
		cDao = new CartDao();
		pDao = new ProductDao();

		String path = "";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");
			path = "./index";
		}
		else
		{
			String memberId = sessionId.toString();
			
			String cartId = cDao.selectCartId(memberId);
			String productId = request.getParameter("data");
			
			int count = cDao.selectCartProductCount(memberId, productId);
			int productPrice = pDao.selectProduct(productId).getPrice();
			int price = cDao.selectCart(memberId).getPrice();
			
			cDao.deleteCartProduct(cartId, productId);
			cDao.updateCart(memberId, price - (productPrice * count));
			
			path = "./showMyCart";
		}
		
		pDao.close();
		cDao.close();
		
		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void deleteMyCartAll()
	{	
		cDao = new CartDao();

		String path = "";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");
			path = "./index";
		}
		else
		{
			String memberId = sessionId.toString();
			
			String cartId = cDao.selectCartId(memberId);
			
			cDao.deleteCartProduct(cartId);
			cDao.deleteCart(memberId);
			
			path = "./showMyCart";
		}
		
		cDao.close();
		
		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private String getMyCart(String memberId, CartDao cDao, ArrayList<Product> pList)
	{
		StringBuilder result = new StringBuilder();

		result.append("<table>");

		for(int i=0; i<pList.size(); i++)
		{
			Product p = pList.get(i);
			int count = cDao.selectCartProductCount(memberId, p.getProductId());

			result.append("<tr>");
			result.append("<td rowspan='2'>");
			result.append("<img id='smallThumbnail' src=\"upload/" + p.getImageName() + "\" />");
			result.append("</td>");
			result.append("<td>" + p.getProductName() + "</td>");
			result.append("<td>" + count + " 개</td>");
			result.append("<td rowspan='2'><input type='button' id='deleteCart' class='btn' onclick=\"deleteMyCartItem('" + p.getProductId() + "')\" value='취소' /></td>");
			result.append("<td rowspan='2'><input type='button' id='orderCart' class='btn' onclick=\"orderProduct('" + p.getProductId() + "')\" value='구매' /></td>");
			result.append("</tr>");

			result.append("<tr>");
			result.append("<td class='cart'></td>");
			result.append("<td class='cart'>" + count * p.getPrice() + " 원</td>");
			result.append("</tr>");
		}

		result.append("<tr>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("<td>총 금액</td>");
		result.append("<td>" + cDao.selectCart(memberId).getPrice() + " 원</td>");
		result.append("</tr>");
		result.append("<tr>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("<td><input type='button' id='indexBtn' class='btn' onclick=\"toIndex()\" value='돌아가기' /></td>");
		result.append("<td><input type='button' id='indexBtn' class='btn' onclick=\"deleteMyCartAll()\" value='전체 취소' /></td>");
		result.append("<td><input type='button' id='orderAll' class='btn' onclick=\"orderAllProduct()\" value='전체 구매' /></td>");
		result.append("</tr>");
		result.append("</table>");

		return result.toString();
	}
}
