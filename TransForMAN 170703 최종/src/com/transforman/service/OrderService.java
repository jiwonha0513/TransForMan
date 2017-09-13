package com.transforman.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.transforman.bean.Forward;
import com.transforman.bean.Member;
import com.transforman.bean.Order;
import com.transforman.bean.Payment;
import com.transforman.bean.Product;
import com.transforman.dao.CartDao;
import com.transforman.dao.MemberDao;
import com.transforman.dao.OrderDao;
import com.transforman.dao.ProductDao;

public class OrderService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;

	private ProductDao pDao;
	private CartDao cDao;
	private OrderDao oDao;

	private Forward forward;

	public OrderService(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request = request;
		this.response = response;
	}

	public Forward execute(int code)
	{
		switch(code)
		{
		case 1:
			orderForm();
			break;

		case 2:
			insertMyOrder();
			break;

		case 3:
			showMyOrder();
			break;

		case 4:
			deleteMyOrder();
			break;

		case 5:
			showOrderComplete();
			break;

		case 6:
			showOrderList();
			break;

		case 7:
			checkOrderForm();
			break;
		}

		return forward;
	}

	private void orderForm()
	{
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
			pDao = new ProductDao();
			cDao = new CartDao();
			oDao = new OrderDao();

			String memberId = sessionId.toString();
			String productId = request.getParameter("data");
			String countText = request.getParameter("data2");
			int count;

			if(countText == null || countText.equals(""))
				count = 0;
			else
				count = Integer.valueOf(countText);

			System.out.println("count : " + count);

			String productList = null;

			if(productId == null || productId.equals(""))	// Cart에서 전제 구매를 누른 경우
			{
				ArrayList<Product> pList = cDao.selectCartProductList(memberId);

				if(pList.size() != 0)
					productList = getProductList(memberId, cDao, pList);

			}
			else
			{
				Product p = pDao.selectProduct(productId);
				if(count == 0)
					productList = getProductList(memberId, cDao, p);
				else
				{
					productList = getProductList(count, p);
					request.setAttribute("count", count);
				}

				request.setAttribute("productId", productId);
			}

			oDao.close();
			cDao.close();
			pDao.close();

			request.setAttribute("productList", productList);

			path = "orderForm.jsp";
		}	

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void insertMyOrder()
	{
		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");

			forward = new Forward();
			forward.setRedirect(false);
			forward.setPath("./index");
		}
		else
		{
			pDao = new ProductDao();
			cDao = new CartDao();
			oDao = new OrderDao();

			String memberId = sessionId.toString();
			String productId = request.getParameter("productId");
			String countText = request.getParameter("count");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			int postcode = Integer.valueOf(request.getParameter("postcode"));
			String address1 = request.getParameter("address");
			String address2 = request.getParameter("address2");
			String cardCompany = request.getParameter("cardSelect");
			String phoneCompany = request.getParameter("phoneSelect");
			String bankCompany = request.getParameter("bankSelect");

			if(cardCompany == null)
				cardCompany = "";
			if(phoneCompany == null)
				phoneCompany = "";
			if(bankCompany == null)
				bankCompany = "";

			Order o = new Order();
			o.setReceiverName(name);
			o.setReceiverPhone(phone);
			o.setReceiverPostcode(postcode);
			o.setReceiverAddress1(address1);
			o.setReceiverAddress2(address2);
			o.setMemberId(memberId);

			Payment p = new Payment();
			if(!cardCompany.equals(""))
				p.setKind("card");
			else if(!phoneCompany.equals(""))
				p.setKind(phone);
			else if(!bankCompany.equals(""))
				p.setKind("bank");
			p.setPhoneCompany(phoneCompany);
			p.setCardNumber(request.getParameter("phoneNumber"));
			p.setCardCompany(cardCompany);
			p.setCardNumber(request.getParameter("cardNumber"));
			p.setBank(bankCompany);
			p.setBankbookNumber(request.getParameter("bankBookNumber"));

			if((productId == null || productId.equals("")) && (countText == null || countText.equals("")))	// Cart에서 전제 구매한 경우
			{

				String cartId = cDao.selectCartId(memberId);
				int price = cDao.selectCart(memberId).getPrice();
				ArrayList<Product> pList = cDao.selectCartProductList(memberId);

				oDao.insertOrder(oDao.getSysdate(), oDao.getOrderId(), price, o);

				for(int i=0; i<pList.size(); i++)
				{
					Product product = pList.get(i);
					productId = product.getProductId();

					int count = cDao.selectCartProductCount(memberId, productId);
					oDao.insertOrdersProduct(oDao.selectOrderId(memberId), productId, count);

					int nextCount = product.getCount() - count;
					pDao.updateProduct(nextCount, count, productId);
				}

				p.setOrderId(oDao.selectOrderId(memberId));
				oDao.insertPayment(oDao.getSysdate(), oDao.getOrderId(), p);

				cDao.deleteCartProduct(cartId);
				cDao.deleteCart(memberId);
			}
			else if(countText == null  || countText.equals("")) // Cart에서 개별 구매한 경우
			{
				String cartId = cDao.selectCartId(memberId);
				int count = cDao.selectCartProductCount(memberId, productId);

				Product product = pDao.selectProduct(productId);
				int price = count * product.getPrice();

				oDao.insertOrder(oDao.getSysdate(), oDao.getOrderId(), price, o);
				oDao.insertOrdersProduct(oDao.selectOrderId(memberId), productId, count);

				int nextCount = product.getCount() - count;
				pDao.updateProduct(nextCount, count, productId);

				p.setOrderId(oDao.selectOrderId(memberId));
				oDao.insertPayment(oDao.getSysdate(), oDao.getOrderId(), p);

				cDao.deleteCartProduct(cartId, productId);
			}
			else
			{
				int count = Integer.valueOf(countText);
				Product product = pDao.selectProduct(productId);
				int price = count * product.getPrice();

				oDao.insertOrder(oDao.getSysdate(), oDao.getOrderId(), price, o);
				oDao.insertOrdersProduct(oDao.selectOrderId(memberId), productId, count);

				int nextCount = product.getCount() - count;
				pDao.updateProduct(nextCount, count, productId);

				p.setOrderId(oDao.selectOrderId(memberId));
				oDao.insertPayment(oDao.getSysdate(), oDao.getOrderId(), p);
			}

			try
			{
				response.getWriter().write(oDao.selectOrderId(memberId));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

			oDao.close();
			cDao.close();
			pDao.close();
		}
	}

	private void showMyOrder()
	{
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
			oDao = new OrderDao();

			String memberId = sessionId.toString();
			String orderId = oDao.selectOrderId(memberId);
			ArrayList<Product> pList = oDao.selectOrderProductList(orderId);

			if(pList.size() != 0)
			{
				String productList = getProductList(orderId, oDao, pList);

				request.setAttribute("orderList", productList);
			}

			oDao.close();
			path = "showMyOrder.jsp";
		}

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void deleteMyOrder()
	{

	}

	private void showOrderComplete()
	{
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
			pDao = new ProductDao();
			oDao = new OrderDao();

			String orderId = request.getParameter("data");
			ArrayList<Product> pList = oDao.selectOrderProductList(orderId);
			Order o = oDao.selectOrder(orderId);
			Payment p = oDao.selectPayment(orderId);
			System.out.println(p);

			if(pList.size() != 0)
			{
				String productList = getProductList(orderId, oDao, pList);
				String orderInfo = getOrderInfo(o);
				String paymentInfo = getPaymentInfo(p);

				request.setAttribute("productList", productList);
				request.setAttribute("orderInfo", orderInfo);
				request.setAttribute("paymentInfo", paymentInfo);
			}

			oDao.close();
			pDao.close();

			path = "showOrderComplete.jsp";
		}

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void showOrderList()
	{
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
			oDao = new OrderDao();

			String memberId = sessionId.toString();
			ArrayList<Order> oList = oDao.selectOrderList(memberId);

			if(oList.size() != 0)
			{
				String productList = getOrderList(oList);

				request.setAttribute("orderList", productList);
			}

			oDao.close();
			path = "showOrderList.jsp";
		}

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void checkOrderForm()
	{
		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");

			forward = new Forward();
			forward.setRedirect(false);
			forward.setPath("./index");
		}
		else
		{
			int check = 0;

			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String postcode = request.getParameter("postcode");
			String address1 = request.getParameter("address");
			String address2 = request.getParameter("address2");
			String cardSelect = request.getParameter("cardSelect");
			String cardNumber = request.getParameter("cardNumber");
			String phoneSelect = request.getParameter("phoneSelect");
			String phoneNumber = request.getParameter("phoneNumber");
			String bankSelect = request.getParameter("bankSelect");

			String msgName;
			String msgPhone;
			String msgAddress;
			String msgPayment = "";

			boolean payment = (cardSelect.equals("") || cardSelect == null) &&
					(phoneSelect.equals("") || phoneSelect == null) &&
					(bankSelect.equals("") || bankSelect == null);

			if(name.equals("") || name == null)
			{
				msgName = "이름을 입력해주세요.";
				check++;
			}
			else
				msgName = "";

			if(phone.equals("") || phone == null)
			{
				msgPhone = "핸드폰 번호를 입력해주세요.";
				check++;
			}
			else
				msgPhone = "ex)010-0000-0000";

			if(postcode.equals("") || postcode == null)
			{
				msgAddress = "우편번호를 입력해주세요.";
				check++;
			}
			else if(address1.equals("") || address1 == null)
			{
				msgAddress = "주소를 입력해주세요.";
				check++;
			}
			else if(address2.equals("") || address2 == null)
			{
				msgAddress = "상세주소를 입력해주세요.";
				check++;
			}
			else
				msgAddress = "";

			if(payment)
			{
				msgPayment = "결제 방식을 선택해주세요.";
				check++;
			}
			else
			{
				if(cardSelect.length() != 0)
				{
					if(cardNumber.equals("") || cardNumber == null)
					{
						msgPayment = "카드 번호를 입력해주세요.";
						check++;
					}
					else
						msgPayment = "";
				}
				if(phoneSelect.length() != 0)
				{
					if(phoneNumber.equals("") || phoneNumber == null)
					{
						msgPayment = "핸드폰 번호를 입력해주세요.";
						check++;
					}
					else
						msgPayment = "";
				}
			}

			try
			{
				response.getWriter().write(msgName + "," + msgPhone + "," + msgAddress + 
						"," + msgPayment + "," + check);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private String getProductList(String memberId, CartDao cDao, ArrayList<Product> pList)
	{
		StringBuilder result = new StringBuilder();
		int price = 0;

		result.append("<table>");

		for(int i=0; i<pList.size(); i++)
		{
			Product p = pList.get(i);
			int count =  cDao.selectCartProductCount(memberId, p.getProductId());

			result.append("<tr>");
			result.append("<td><img id='smallThumbnail' src=\"upload/" + p.getImageName() + "\" /></td>");
			result.append("<td>" + p.getProductName() + "</td>");
			result.append("<td>" + count + " 개</td>");
			result.append("<td>" + count * p.getPrice() + " 원</td>");
			result.append("</tr>");

			price += (count * p.getPrice());
		}

		result.append("<tr>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("<td>총 금액</td>");
		result.append("<td>" + price + " 원</td>");
		result.append("</tr>");

		result.append("</table>");

		return result.toString();
	}

	private String getProductList(String memberId, CartDao cDao, Product p)
	{
		StringBuilder result = new StringBuilder();

		result.append("<table>");

		int count =  cDao.selectCartProductCount(memberId, p.getProductId());

		result.append("<tr>");
		result.append("<td><img id='smallThumbnail' src=\"upload/" + p.getImageName() + "\" /></td>");
		result.append("<td>" + p.getProductName() + "</td>");
		result.append("<td>" + count + " 개</td>");
		result.append("<td>" + count * p.getPrice() + " 원</td>");
		result.append("</tr>");
		result.append("<tr>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("<td>총 금액</td>");
		result.append("<td>" + count * p.getPrice() + " 원</td>");
		result.append("</tr>");

		result.append("</table>");

		return result.toString();
	}

	private String getProductList(int count, Product p)
	{
		StringBuilder result = new StringBuilder();

		result.append("<table>");

		result.append("<tr>");
		result.append("<td><img id='smallThumbnail' src=\"upload/" + p.getImageName() + "\" /></td>");
		result.append("<td>" + p.getProductName() + "</td>");
		result.append("<td>" + count + " 개</td>");
		result.append("<td>" + count * p.getPrice() + " 원</td>");
		result.append("</tr>");
		result.append("<tr>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("<td>총 금액</td>");
		result.append("<td>" + count * p.getPrice() + " 원</td>");
		result.append("</tr>");

		result.append("</table>");

		return result.toString();
	}

	private String getProductList(String orderId, OrderDao oDao, ArrayList<Product> pList)
	{
		StringBuilder result = new StringBuilder();
		int price = 0;

		result.append("<table>");

		result.append("<tr>");
		result.append("<td>주문번호</td>");
		result.append("<td>" + orderId + "</td>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("</tr>");

		for(int i=0; i<pList.size(); i++)
		{
			Product p = pList.get(i);
			int count =  oDao.selectOrderProductCount(orderId, p.getProductId());

			result.append("<tr>");
			result.append("<td><img id='smallThumbnail' src=\"upload/" + p.getImageName() + "\" /></td>");
			result.append("<td>" + p.getProductName() + "</td>");
			result.append("<td>" + count + " 개</td>");
			result.append("<td>" + count * p.getPrice() + " 원</td>");
			result.append("</tr>");

			price += (count * p.getPrice());
		}

		result.append("<tr>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("<td>총 금액</td>");
		result.append("<td>" + price + " 원</td>");
		result.append("</tr>");

		result.append("</table>");

		return result.toString();
	}

	private String getOrderInfo(Order o)
	{
		StringBuilder result = new StringBuilder();

		result.append("<table>");

		result.append("<tr>");
		result.append("<td>주문 정보</td>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("</tr>");

		result.append("<tr>");
		result.append("<td>" + o.getReceiverName() + "</td>");
		result.append("<td>" + o.getReceiverPhone() + "</td>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("</tr>");
		result.append("<tr>");
		result.append("<td class='order'></td>");
		result.append("<td class='order'>" + o.getReceiverPostcode() + "</td>");
		result.append("<td class='order'>" + o.getReceiverAddress1() + "</td>");
		result.append("<td class='order'>" + o.getReceiverAddress2() + "</td>");
		result.append("</tr>");

		result.append("</table>");

		return result.toString();
	}

	private String getPaymentInfo(Payment p)
	{
		StringBuilder result = new StringBuilder();

		String card = (p.getCardCompany() == null) ? "" : p.getCardCompany();
		String cardNumber = (p.getCardNumber() == null) ? "" : p.getCardNumber();
		String phone = (p.getPhoneCompany() == null) ? "" : p.getPhoneCompany();
		String phoneNumber = (p.getPhoneNumber() == null) ? "" : p.getPhoneNumber();
		String bank = (p.getBank() == null) ? "" : p.getBank();
		String bankBookNumber = (p.getBankbookNumber() == null) ? "" : p.getBankbookNumber();

		result.append("<table>");

		result.append("<tr>");
		result.append("<td>결제 정보</td>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("<td></td>");
		result.append("</tr>");

		result.append("<tr>");
		result.append("<td>" + p.getPaymetnId() + "</td>");
		result.append("<td>" + p.getKind() + "</td>");

		if(!card.equals(""))
		{
			result.append("<td>" + p.getCardCompany() + "</td>");
			result.append("<td>" + p.getCardNumber() + "</td>");
		}
		else if(!phone.equals(""))
		{
			result.append("<td>" + p.getPhoneCompany() + "</td>");
			result.append("<td>" + p.getPhoneNumber() + "</td>");
		}
		else if(!bank.equals(""))
		{
			result.append("<td>" + p.getBank() + "</td>");
			result.append("<td>" + p.getBankbookNumber() + "</td>");
		}

		result.append("</tr>");

		result.append("</table>");

		return result.toString();
	}

	private String getOrderList(ArrayList<Order> oList)
	{
		StringBuilder result = new StringBuilder();
		result.append("<table id='memberList'>");

		result.append("<tr>");
		result.append("<td>주문번호</td>");
		result.append("<td>아이디</td>");
		result.append("<td>이름</td>");
		result.append("<td>핸드폰 번호</td>");
		result.append("<td colspan='3'>주소</td>");
		result.append("</tr>");

		for(int i=0; i<oList.size(); i++)
		{
			Order o = oList.get(i);

			result.append("<tr>");
			result.append("<td>" + o.getOrderId() + "</td>");
			result.append("<td>" + o.getMemberId() + "</td>");
			result.append("<td>" + o.getReceiverName() + "</td>");
			result.append("<td>" + o.getReceiverPhone() + "</td>");
			result.append("<td colspan='3'>" + o.getReceiverPostcode() + " " + o.getReceiverAddress1() + " &nbsp;" + o.getReceiverAddress2() + "</td>");
			result.append("</tr>");
		}

		result.append("</table>");

		return result.toString();
	}
}
