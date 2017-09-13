package com.transforman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.transforman.bean.Product;
import com.transforman.bean.ProductCategory;

public class ProductDao 
{
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds; //추가

	public ProductDao()
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

	public boolean insertProduct(String id, int num, Product p)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO PRODUCT VALUES('product'||?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			System.out.println(p.getMenuId());
			System.out.println(p.getCategoryId());

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setNString(2, p.getProductName());
			pstmt.setInt(3, p.getCount());
			pstmt.setInt(4, p.getPrice());
			pstmt.setNString(5, p.getExplain());
			pstmt.setInt(6, p.getSalesVolum());
			pstmt.setNString(7, p.getImageName());
			pstmt.setInt(8, p.getMenuId());
			pstmt.setInt(9, p.getCategoryId());
			pstmt.setNString(10, id);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("상품 삽입 성공 !");
			}
			else
				System.out.println("상품 삽입 실패 !");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("insertProduct 실패");
		}

		return result;
	}

	public boolean updateProduct(int nextCount, int salesVolum, String productId)
	{
		boolean result = false;

		try
		{
			String sql = "UPDATE PRODUCT SET COUNT=?, SALES_VOLUM=? WHERE PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, nextCount);
			pstmt.setInt(2, salesVolum);
			pstmt.setNString(3, productId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("수정 성공");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("updateProduct 실패");
		}

		return result;
	}

	public boolean deleteProduct(String productId)
	{
		boolean result = false;

		try
		{
			String sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, productId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("상품 삭제 완료");
			}
			else
				System.out.println("없는 상품 ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("deleteProduct 실패");
		}

		return result;
	}



	public boolean deleteStylingProduct(String productId)
	{
		boolean result = false;

		try
		{
			String sql = "DELETE FROM STYLING_PRODUCT WHERE PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, productId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("스타일링-상품 삭제 완료");
			}
			else
				System.out.println("없는 상품 ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("deleteStylingProduct 실패");
		}

		return result;
	}

	public Product selectProduct(String productId)
	{
		Product p = null;

		try
		{
			String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, productId);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				p = new Product();
				p.setProductId(rs.getNString("PRODUCT_ID"));
				p.setProductName(rs.getNString("NAME"));
				p.setCount(rs.getInt("COUNT"));
				p.setPrice(rs.getInt("PRICE"));
				p.setExplain(rs.getNString("EXPLAIN"));
				p.setSalesVolum(rs.getInt("SALES_VOLUM"));
				p.setImageName(rs.getNString("IMAGE_NAME"));
				p.setMenuId(rs.getInt("MENU_ID"));
				p.setCategoryId(rs.getInt("CATEGORY_ID"));
				p.setMemberId(rs.getNString("MEMBER_ID"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("getProductId 실패");
		}

		return p;
	}

	public ArrayList<Product> selectProductList()
	{
		ArrayList<Product> pList = new ArrayList<Product>();

		try
		{
			String sql = "SELECT * FROM PRODUCT";

			pstmt = con.prepareStatement(sql);

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
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectProductList 실패");
		}

		return pList;
	}

	public ArrayList<Product> selectProductList(String id)
	{
		ArrayList<Product> pList = new ArrayList<Product>();

		try
		{
			String sql = "SELECT * FROM PRODUCT WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, id);

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
				p.setMemberId(rs.getNString("MEMBER_ID"));
				p.setCategoryId(rs.getInt("CATEGORY_ID"));
				pList.add(p);	
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectProductList 실패");
		}

		return pList;
	}

	public ArrayList<Product> selectProductList(int menuId, int categoryId)
	{
		ArrayList<Product> pList = new ArrayList<Product>();

		try
		{
			String sql = "SELECT * FROM PRODUCT WHERE MENU_ID=? AND CATEGORY_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, menuId);
			pstmt.setInt(2, categoryId);

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
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectProductList 실패");
		}

		return pList;
	}

	public ArrayList<Product> searchProductList(String keyword)
	{
		ArrayList<Product> pList = new ArrayList<Product>();

		try
		{
			String sql = "SELECT * FROM PRODUCT P "
					+ "INNER JOIN PRODUCT_TAG T ON P.PRODUCT_ID=T.PRODUCT_ID "
					+ "INNER JOIN HASH_TAG H ON T.TAG_ID=H.TAG_ID "
					+ "WHERE P.PRODUCT_ID LIKE '%'||?||'%' "
					+ "OR P.EXPLAIN LIKE '%'||?||'%' "
					+ "OR H.NAME LIKE '%'||?||'%'";

			pstmt=con.prepareStatement(sql);

			pstmt.setNString(1, keyword);
			pstmt.setNString(2, keyword);         
			pstmt.setNString(3, keyword);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Product p=new Product();

				p.setProductId(rs.getNString("PRODUCT_ID"));
				p.setProductName(rs.getNString("NAME"));
				p.setCount(rs.getInt("COUNT"));
				p.setPrice(rs.getInt("PRICE"));
				p.setExplain(rs.getNString("EXPLAIN"));
				p.setSalesVolum(rs.getInt("SALES_VOLUM"));
				p.setImageName(rs.getNString("IMAGE_NAME"));
				p.setMenuId(rs.getInt("MENU_ID"));
				p.setCategoryId(rs.getInt("CATEGORY_ID"));
				p.setMemberId(rs.getNString("MEMBER_ID"));

				pList.add(p);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return pList;
	}

	public ArrayList<ProductCategory> selectProductCategoryList()
	{
		ArrayList<ProductCategory> pList = new ArrayList<>();

		try
		{
			String sql = "SELECT * FROM PRODUCT_CATEGORY";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				ProductCategory category = new ProductCategory();
				category.setCategoryId(rs.getInt("CATEGORY_ID"));
				category.setCategoryName(rs.getNString("NAME"));
				category.setMenuId(rs.getInt("MENU_ID"));

				pList.add(category);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectProductCategoryList 실패");
		}

		return pList;
	}


	public String selectProductId(String name)
	{
		String id = null;

		try
		{
			String sql = "SELECT * FROM PRODUCT WHERE NAME=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, name);

			rs = pstmt.executeQuery();

			if(rs.next())
				id = rs.getNString("PRODUCT_ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("getProductId 실패");
		}

		return id;
	}

	public int selectMenuId(String name)
	{
		int menuId = 0;

		try
		{
			String sql = "SELECT * FROM PRODUCT_MENU WHERE NAME=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, name);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				menuId = rs.getInt("MENU_ID");
				System.out.println("menu_id : " + menuId);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectMenuId 실패");
		}

		return menuId;
	}

	public String selectMenuName(int menuId)
	{
		String name = "";

		try
		{
			String sql = "SELECT * FROM PRODUCT_MENU WHERE MENU_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, menuId);

			rs = pstmt.executeQuery();

			if(rs.next())
				name = rs.getNString("NAME");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectMenuName 실패");
		}

		return name;
	}

	public int selectCategoryId(String name)
	{
		int categoryId = 0;

		try
		{
			String sql = "SELECT * FROM PRODUCT_CATEGORY WHERE NAME=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, name);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				categoryId = rs.getInt("CATEGORY_ID");
				System.out.println("category_id : " + categoryId);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectCategoryId 실패");
		}

		return categoryId;
	}

	public String selectCategoryName(int menuId, int categoryId)
	{
		String name = "";

		try
		{
			String sql = "SELECT * FROM PRODUCT_CATEGORY WHERE MENU_ID=? AND CATEGORY_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, menuId);
			pstmt.setInt(2, categoryId);

			rs = pstmt.executeQuery();

			if(rs.next())
				name = rs.getNString("NAME");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectCategoryName 실패");
		}

		return name;
	}

	public boolean isProductName(String name)
	{
		boolean result = false;

		try
		{
			String sql = "SELECT * FROM PRODUCT WHERE NAME=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, name);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				result = true;
				System.out.println("상품 이름 존재");
			}
			else
				System.out.println("상품 이름 존재 X");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("isProductName 실패");
		}

		return result;
	}

	public int getProductId()
	{
		int re = 0;

		try
		{
			String sql = "SELECT * FROM PRODUCT ORDER BY PRODUCT_ID";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
				re = Integer.valueOf(rs.getNString("PRODUCT_ID").substring(7));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("getProductId 실패");
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