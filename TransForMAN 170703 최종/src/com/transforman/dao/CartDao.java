package com.transforman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.transforman.bean.Cart;
import com.transforman.bean.Product;

public class CartDao 
{
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds; //추가

	public CartDao()
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

	public boolean insertCart(int num, Cart c)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO CART VALUES('cart'||?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setInt(2, c.getPrice());
			pstmt.setNString(3, c.getMemberId());

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("cart 삽입 성공");
			}
			else
				System.out.println("cart 삽입 실패");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("insertCart 실패");
		}

		return result;
	}

	public boolean insertCartProduct(String cartId, String productId, int count)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO CART_PRODUCT VALUES(?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, cartId);
			pstmt.setNString(2, productId);
			pstmt.setInt(3, count);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("cart_product 삽입 성공");
			}
			else
				System.out.println("cart_product 삽입 실패");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("insertCartProduct 실패");
		}

		return result;
	}

	public boolean updateCart(String memberId, int price)
	{
		boolean result = false;

		try
		{
			String sql = "UPDATE CART SET PRICE=? WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, price);
			pstmt.setNString(2, memberId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("cart 수정 성공");
			}
			else
				System.out.println("cart 수정 실패");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("updateCart 실패");
		}

		return result;
	}

	public boolean updateCartProduct(String productId, int count)
	{
		boolean result = false;

		try
		{
			String sql = "UPDATE CART_PRODUCT SET COUNT=? WHERE PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, count);
			pstmt.setNString(2, productId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("cartProduct 수정 성공");
			}
			else
				System.out.println("cartProduct 수정 실패");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("updateCartProduct 실패");
		}

		return result;
	}

	public boolean deleteCart(String memberId)
	{
		boolean result = false;

		try
		{
			String sql = "DELETE FROM CART WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, memberId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("cart 삭제 완료");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("deleteCart 실패");
		}

		return result;
	}

	public boolean deleteCartProduct(String cartId)
	{
		boolean result = false;

		try
		{
			String sql = "DELETE FROM CART_PRODUCT WHERE CART_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, cartId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("cart_product 삭제 완료");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("deleteCartProduct 실패");
		}

		return result;
	}

	public boolean deleteCartProduct(String cartId, String productId)
	{
		boolean result = false;

		try
		{
			String sql = "DELETE FROM CART_PRODUCT WHERE CART_ID=? AND PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, cartId);
			pstmt.setNString(2, productId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("cart_product 삭제 완료");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("deleteCartProduct 실패");
		}

		return result;
	}

	public Cart selectCart(String memberId)
	{
		Cart c = null;

		try
		{
			String sql = "SELECT * FROM CART WHERE MEMBER_ID =?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, memberId);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				c = new Cart();
				c.setCartId(rs.getNString("CART_ID"));
				c.setPrice(rs.getInt("PRICE"));
				c.setMemberId(rs.getNString("MEMBER_ID"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("selectCart 실패");
		}

		return c;
	}

	public ArrayList<Product> selectCartProductList(String memberId)
	{
		ArrayList<Product> pList = new ArrayList<>();

		try
		{
			String sql = "SELECT P.PRODUCT_ID, P.NAME, P.COUNT, P.PRICE, P.EXPLAIN, "
					+ "P.SALES_VOLUM, P.IMAGE_NAME, P.MENU_ID, P.CATEGORY_ID, P.MEMBER_ID "
					+ "FROM CART C INNER JOIN CART_PRODUCT CP ON C.CART_ID=CP.CART_ID "
					+ "INNER JOIN PRODUCT P ON CP.PRODUCT_ID=P.PRODUCT_ID WHERE C.MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, memberId);

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

	public String selectCartId(String memberId)
	{
		String cartId = null;

		try
		{
			String sql = "SELECT * FROM CART WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, memberId);

			rs = pstmt.executeQuery();

			if(rs.next())
				cartId = rs.getNString("CART_ID");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("selectCartId 실패");
		}

		return cartId;
	}

	public int selectCartProductCount(String memberId, String productId)
	{
		int count = 0;

		try
		{
			String sql = "SELECT CP.COUNT FROM CART C INNER JOIN "
					+ "CART_PRODUCT CP ON C.CART_ID=CP.CART_ID "
					+"WHERE C.MEMBER_ID=? AND CP.PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, memberId);
			pstmt.setNString(2, productId);

			rs = pstmt.executeQuery();

			if(rs.next())
				count = rs.getInt("COUNT");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("selectCartProductCount 실패");
		}

		return count;
	}

	public boolean isCart(String memberId)
	{
		boolean result = false;

		try
		{
			String sql = "SELECT * FROM CART WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, memberId);

			rs = pstmt.executeQuery();

			if(rs.next())
				result = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("isCart 실패");
		}

		return result;
	}

	public boolean isCartProduct(String productId)
	{
		boolean result = false;

		try
		{
			String sql = "SELECT * FROM CART_PRODUCT WHERE PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, productId);

			rs = pstmt.executeQuery();

			if(rs.next())
				result = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("isCartProduct 실패");
		}

		return result;
	}

	public int getCartId()
	{
		int re = 0;

		try
		{
			String sql = "SELECT * FROM CART ORDER BY CART_ID";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
				re = Integer.valueOf(rs.getNString("CART_ID").substring(4));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("getCartId 실패");
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