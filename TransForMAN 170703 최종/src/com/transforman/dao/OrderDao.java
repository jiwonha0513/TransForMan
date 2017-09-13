package com.transforman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.transforman.bean.Order;
import com.transforman.bean.Payment;
import com.transforman.bean.Product;

import oracle.net.aso.p;
import oracle.net.aso.s;

public class OrderDao 
{
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds; //추가

	public OrderDao()
	{
		try
		{
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
			con = ds.getConnection();
			System.out.println("커넥션풀 연결 성공");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("커넥션풀 연결 실패");
		}
	}

	public boolean insertOrder(String sysdate, int id, int price, Order o)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO ORDERS VALUES(?||?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, sysdate);
			pstmt.setInt(2, id);
			pstmt.setInt(3, price);
			pstmt.setNString(4, o.getReceiverName());
			pstmt.setNString(5, o.getReceiverPhone());
			pstmt.setInt(6, o.getReceiverPostcode());
			pstmt.setNString(7, o.getReceiverAddress1());
			pstmt.setNString(8, o.getReceiverAddress2());
			pstmt.setNString(9, o.getMemberId());

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("Orders 삽입 성공");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("insertOrder 실패");
		}

		return result;
	}

	public boolean insertOrdersProduct(String orderId, String productId, int count)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO ORDERS_PRODUCT VALUES(?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, orderId);
			pstmt.setNString(2, productId);
			pstmt.setInt(3, count);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("OrdersProduct 삽입 성공");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("insertOrdersProduct 실패");
		}

		return result;
	}

	public boolean insertPayment(String sysdate, int id, Payment p)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO PAYMENT VALUES(?||?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, sysdate);
			pstmt.setInt(2, id);
			pstmt.setNString(3, p.getKind());
			switch(p.getKind())
			{
			case "phone":
				pstmt.setNString(4, p.getPhoneCompany());
				pstmt.setNString(5, p.getPhoneNumber());
				pstmt.setNString(6, null);
				pstmt.setNString(7, null);
				pstmt.setNString(8, null);
				pstmt.setNString(9, null);
				break;

			case "card":
				pstmt.setNString(4, null);
				pstmt.setNString(5, null);
				pstmt.setNString(6, p.getCardCompany());
				pstmt.setNString(7, p.getCardNumber());
				pstmt.setNString(8, null);
				pstmt.setNString(9, null);
				break;

			case "bank":
				pstmt.setNString(4, null);
				pstmt.setNString(5, null);
				pstmt.setNString(6, null);
				pstmt.setNString(7, null);
				pstmt.setNString(8, p.getBank());
				pstmt.setNString(9, p.getBankbookNumber());
				break;
			}
			pstmt.setNString(10, p.getOrderId());

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("주문 성공");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("insertPayment 실패");
		}

		return result;
	}

	public Order selectOrder(String orderId)
	{
		Order o = null;

		try
		{
			String sql = "SELECT * FROM ORDERS WHERE ORDERS_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, orderId);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				o = new Order();
				o.setOrderId(rs.getNString("ORDERS_ID"));
				o.setReceiverName(rs.getNString("RECEIVER_NAME"));
				o.setReceiverPhone(rs.getNString("RECEIVER_PHONE"));
				o.setReceiverPostcode(rs.getInt("RECEIVER_POSTCODE"));
				o.setReceiverAddress1(rs.getNString("RECEIVER_ADDRESS1"));
				o.setReceiverAddress2(rs.getNString("RECEIVER_ADDRESS2"));
				o.setMemberId(rs.getNString("MEMBER_ID"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("selectOrder 실패");
		}

		return o;
	}
	
	public ArrayList<Order> selectOrderList(String memberId)
	{
		ArrayList<Order> oList = new ArrayList<>();

		try
		{
			String sql = "SELECT O.ORDERS_ID, O.RECEIVER_NAME, O.RECEIVER_PHONE, O.RECEIVER_POSTCODE, "
					+ "O.RECEIVER_ADDRESS1, O.RECEIVER_ADDRESS2, O.MEMBER_ID "
					+ "FROM ORDERS O INNER JOIN ORDERS_PRODUCT OP ON O.ORDERS_ID=OP.ORDERS_ID "
					+ "INNER JOIN PRODUCT P ON OP.PRODUCT_ID=P.PRODUCT_ID WHERE P.MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setNString(1, memberId);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Order o = new Order();
				o.setOrderId(rs.getNString("ORDERS_ID"));
				o.setReceiverName(rs.getNString("RECEIVER_NAME"));
				o.setReceiverPhone(rs.getNString("RECEIVER_PHONE"));
				o.setReceiverPostcode(rs.getInt("RECEIVER_POSTCODE"));
				o.setReceiverAddress1(rs.getNString("RECEIVER_ADDRESS1"));
				o.setReceiverAddress2(rs.getNString("RECEIVER_ADDRESS2"));
				o.setMemberId(rs.getNString("MEMBER_ID"));
				
				oList.add(o);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("selectOrderList 실패");
		}

		return oList;
	}

	public ArrayList<Product> selectOrderProductList(String orderId)
	{
		ArrayList<Product> pList = new ArrayList<>();

		try
		{
			String sql = "SELECT P.PRODUCT_ID, P.NAME, P.COUNT, P.PRICE, P.EXPLAIN, "
					+ "P.SALES_VOLUM, P.IMAGE_NAME, P.MENU_ID, P.CATEGORY_ID, P.MEMBER_ID "
					+ "FROM ORDERS O INNER JOIN ORDERS_PRODUCT OP ON O.ORDERS_ID=OP.ORDERS_ID "
					+ "INNER JOIN PRODUCT P ON OP.PRODUCT_ID=P.PRODUCT_ID WHERE O.ORDERS_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, orderId);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Product p = new Product();
				p.setProductId(rs.getNString("PRODUCT_ID"));
				p.setProductName(rs.getNString("NAME"));
				p.setCount(rs.getInt("COUNT"));
				p.setPrice(rs.getInt("PRICE"));
				p.setExplain(rs.getNString("EXPLAIN"));
				p.setSalesVolum(rs.getInt("SALES_VOLUM"));
				p.setImageName(rs.getNString("IMAGE_NAME"));
				p.setMenuId(rs.getInt("MENU_ID"));
				p.setCategoryId(rs.getInt("CATEGORY_ID"));
				pList.add(p);	
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("selectCartProductList 실패");
		}

		return pList;
	}

	public Payment selectPayment(String orderId)
	{
		Payment p = null;

		try
		{
			String sql = "SELECT *  FROM PAYMENT WHERE ORDERS_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, orderId);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				p = new Payment();

				p.setPaymetnId(rs.getNString("PAYMENT_ID"));
				p.setKind(rs.getNString("KIND"));
				p.setPhoneCompany(rs.getNString("PHONE_COMPANY"));
				p.setPhoneNumber(rs.getNString("PHONE_NUMBER"));
				p.setCardCompany(rs.getNString("CARD_COMPANY"));
				p.setCardNumber(rs.getNString("CARD_NUMBER"));
				p.setBank(rs.getNString("BANK"));
				p.setBankbookNumber(rs.getNString("BANKBOOK_NUMBER"));
				p.setOrderId(rs.getNString("ORDERS_ID"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("selectPayment 실패");
		}

		return p;
	}

	public String selectOrderId(String memberId)
	{
		String orderId = null;

		try
		{
			String sql = "SELECT * FROM ORDERS WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, memberId);

			rs = pstmt.executeQuery();

			if(rs.next())
				orderId = rs.getNString("ORDERS_ID");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("selectOrderId 실패");
		}

		return orderId;
	}
	
	public String selectOrderUserId(String productId)
	{
		String id = null;
		
		try
		{
			String sql = "SELECT O.MEMBER_ID FROM ORDERS O "
					+ "INNER JOIN ORDERS_PRODUCT OP "
					+ "ON O.ORDERS_ID=OP.ORDERS_ID WHERE OP.PRODUCT_ID=?";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, productId);

			rs = pstmt.executeQuery();

			if(rs.next())
				id = rs.getNString("MEMBER_ID");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("selectOrderUserId 실패");
		}
		
		return id;
	}

	public int selectOrderProductCount(String orderId, String productId)
	{
		int count = 0;

		try
		{
			String sql = "SELECT * FROM ORDERS_PRODUCT WHERE ORDERS_ID=? AND PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, orderId);
			pstmt.setNString(2, productId);

			rs = pstmt.executeQuery();

			if(rs.next())
				count = rs.getInt("COUNT");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("selectOrderProductCount 실패");
		}

		return count;
	}

	public String getSysdate()
	{
		String sysdate = null;

		try
		{
			String sql = "SELECT TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') AS SYSTEM_DATE FROM DUAL";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if(rs.next())
				sysdate = rs.getNString("SYSTEM_DATE");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("getSysdate 실패");
		}

		return sysdate;
	}

	public int getOrderId()
	{
		int re = 0;

		try
		{
			String sql = "SELECT * FROM ORDERS ORDER BY ORDERS_ID";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
				re = Integer.valueOf(rs.getNString("ORDERS_ID").substring(14));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("getOrderId 실패");
		}

		return re + 1;
	}

	public int getPaymentId()
	{
		int re = 0;

		try
		{
			String sql = "SELECT * FROM PAYMENT ORDER BY PAYMENT_ID";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
				re = Integer.valueOf(rs.getNString("PAYMENT_ID").substring(14));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("getPaymentId 실패");
		}

		return re + 1;
	}

	public void close()
	{  //커넥션풀에 커넥션 반납
		try 
		{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}