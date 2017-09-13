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
import com.transforman.bean.Styling;
import com.transforman.bean.StylingCategory;

public class StylingDao 
{
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds; //추가

	public StylingDao()
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

	public boolean insertStyling(String id, int num, Styling s)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO STYLING VALUES('styling'||?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setNString(2, s.getStylingName());
			pstmt.setNString(3, s.getExplain());
			pstmt.setNString(4, s.getImageName());
			pstmt.setNString(5, s.getVideoUrl());
			pstmt.setInt(6, s.getMenuId());
			pstmt.setInt(7, s.getCategoryId());
			pstmt.setNString(8, id);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("스타일링 삽입 성공 !");
			}
			else
				System.out.println("스타일링 삽입 실패 !");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("insertStyling 실패");
		}

		return result;
	}

	public boolean insertStylingProduct(String stylingId, String productId)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO STYLING_PRODUCT VALUES(?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, stylingId);
			pstmt.setNString(2, productId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result=true;
				System.out.println("스타일링 상품 삽입 성공 !");
			}
			else
				System.out.println("스타일링 상품 삽입 실패 !");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("insertStylingProduct 실패");
		}

		return result;
	}

	public boolean deleteStyling(String stylingId)
	{
		boolean result = false;

		try
		{
			String sql = "DELETE FROM STYLING WHERE STYLING_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, stylingId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("스타일링 삭제 완료");
			}
			else
				System.out.println("없는 스타일링 ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("deleteStyling 실패");
		}

		return result;
	}

	public boolean deleteStylingProduct(String stylingId)
	{
		boolean result = false;

		try
		{
			String sql = "DELETE FROM STYLING_PRODUCT WHERE STYLING_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, stylingId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("스타일링-상품 삭제 완료");
			}
			else
				System.out.println("없는 스타일링 ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("deleteStylingProduct 실패");
		}

		return result;
	}

	public Styling selectStyling(String stylingId)
	{
		Styling s = null;

		try
		{
			String sql = "SELECT * FROM STYLING WHERE STYLING_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, stylingId);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				s = new Styling();

				s.setStylingId(rs.getNString("STYLING_ID"));
				s.setMemberId(rs.getNString("MEMBER_ID"));
				s.setStylingName(rs.getNString("NAME"));
				s.setExplain(rs.getNString("EXPLAIN"));
				s.setImageName(rs.getNString("IMAGE_NAME"));
				s.setVideoUrl(rs.getNString("VIDEO_URL"));
				s.setMenuId(rs.getInt("MENU_ID"));
				s.setCategoryId(rs.getInt("CATEGORY_ID"));
			}		

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectStyling 실패");
		}

		return s;
	}

	public ArrayList<Styling> selectStylingList()
	{
		ArrayList<Styling> sList = new ArrayList<Styling>();

		int i = 0;

		try
		{
			String sql = "SELECT * FROM STYLING";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Styling s = new Styling();

				s.setStylingId(rs.getNString("STYLING_ID"));
				s.setMemberId(rs.getNString("MEMBER_ID"));
				s.setStylingName(rs.getNString("NAME"));
				s.setExplain(rs.getNString("EXPLAIN"));
				s.setImageName(rs.getNString("IMAGE_NAME"));
				s.setVideoUrl(rs.getNString("VIDEO_URL"));
				s.setMenuId(rs.getInt("MENU_ID"));
				s.setCategoryId(rs.getInt("CATEGORY_ID"));

				sList.add(s);

				System.out.println((i++) + " 번째 상품 호출");
			}		

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectStylingList 실패");
		}

		return sList;
	}

	public ArrayList<Styling> selectStylingList(String id)
	{
		ArrayList<Styling> sList = new ArrayList<Styling>();

		int i = 0;

		try
		{
			String sql = "SELECT * FROM STYLING WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, id);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Styling s = new Styling();

				s.setStylingId(rs.getNString("STYLING_ID"));
				s.setMemberId(rs.getNString("MEMBER_ID"));
				s.setStylingName(rs.getNString("NAME"));
				s.setExplain(rs.getNString("EXPLAIN"));
				s.setImageName(rs.getNString("IMAGE_NAME"));
				s.setVideoUrl(rs.getNString("VIDEO_URL"));
				s.setMenuId(rs.getInt("MENU_ID"));
				s.setCategoryId(rs.getInt("CATEGORY_ID"));

				sList.add(s);

				System.out.println((i++) + " 번째 상품 호출");
			}		

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectStylingList 실패");
		}

		return sList;
	}

	public ArrayList<Styling> selectStylingList(int menuId, int categoryId)
	{
		ArrayList<Styling> sList = new ArrayList<Styling>();

		int i = 0;

		try
		{
			String sql = "SELECT * FROM STYLING WHERE MENU_ID=? AND CATEGORY_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, menuId);
			pstmt.setInt(2, categoryId);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Styling s = new Styling();

				s.setStylingId(rs.getNString("STYLING_ID"));
				s.setMemberId(rs.getNString("MEMBER_ID"));
				s.setStylingName(rs.getNString("NAME"));
				s.setExplain(rs.getNString("EXPLAIN"));
				s.setImageName(rs.getNString("IMAGE_NAME"));
				s.setVideoUrl(rs.getNString("VIDEO_URL"));
				s.setMenuId(rs.getInt("MENU_ID"));
				s.setCategoryId(rs.getInt("CATEGORY_ID"));

				sList.add(s);

				System.out.println((i++) + " 번째 상품 호출");
			}		

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectStylingList 실패");
		}

		return sList;
	}

	public ArrayList<Styling> searchStyling(String keyword) 
	{
		ArrayList<Styling> sList=new ArrayList<Styling>();

		try
		{
			String sql = "SELECT * FROM STYLING S "
					+ "INNER JOIN STYLING_TAG ST ON S.STYLING_ID=ST.STYLING_ID "
					+ "INNER JOIN HASH_TAG T ON ST.TAG_ID=T.TAG_ID "
					+ "WHERE S.STYLING_ID LIKE '%'||?||'%' "
					+ "OR S.EXPLAIN LIKE '%'||?||'%' "
					+ "OR T.NAME LIKE '%'||?||'%'";

			pstmt=con.prepareStatement(sql);

			pstmt.setNString(1, keyword);
			pstmt.setNString(2, keyword);         
			pstmt.setNString(3, keyword);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Styling s=new Styling();

				s.setStylingId(rs.getNString("STYLING_ID"));
				s.setStylingName(rs.getNString("NAME"));
				s.setVideoUrl(rs.getNString("VIDEO_URL"));
				s.setExplain(rs.getNString("EXPLAIN"));
				s.setImageName(rs.getNString("IMAGE_NAME"));
				s.setMenuId(rs.getInt("MENU_ID"));
				s.setCategoryId(rs.getInt("CATEGORY_ID"));
				s.setMemberId(rs.getNString("MEMBER_ID"));

				sList.add(s);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return sList;
	}

	public ArrayList<Product> selectStylingProductList(String stylingId)
	{
		ArrayList<Product> pList = new ArrayList<>();

		try
		{
			String sql = "SELECT P.PRODUCT_ID, P.NAME, P.COUNT, P.PRICE, "
					+ "P.EXPLAIN, P.SALES_VOLUM, P.IMAGE_NAME, "
					+ "P.MENU_ID, P.CATEGORY_ID, P.MEMBER_ID "
					+ "FROM STYLING S INNER JOIN STYLING_PRODUCT SP "
					+ "ON S.STYLING_ID=SP.STYLING_ID INNER JOIN PRODUCT P "
					+ "ON SP.PRODUCT_ID=P.PRODUCT_ID WHERE S.STYLING_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, stylingId);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Product p = new Product();

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

				pList.add(p);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectStylingProductList 실패");
		}

		return pList;
	}

	public ArrayList<StylingCategory> selectStylingCategoryList()
	{
		ArrayList<StylingCategory> sList = new ArrayList<>();

		try
		{
			String sql = "SELECT * FROM STYLING_CATEGORY";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				StylingCategory category = new StylingCategory();
				category.setCategoryId(rs.getInt("CATEGORY_ID"));
				category.setCategoryName(rs.getNString("NAME"));
				category.setMenuId(rs.getInt("MENU_ID"));

				sList.add(category);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectStylingCategoryList 실패");
		}

		return sList;
	}

	public String selectStylingId(String name)
	{
		String id = null;

		try
		{
			String sql = "SELECT * FROM STYLING WHERE NAME=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, name);

			rs = pstmt.executeQuery();

			if(rs.next())
				id = rs.getNString("STYLING_ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectStylingId 실패");
		}

		return id;
	}

	public int selectMenuId(String name)
	{
		int menuId = 0;

		try
		{
			String sql = "SELECT * FROM STYLING_MENU WHERE NAME=?";

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
			String sql = "SELECT * FROM STYLING_MENU WHERE MENU_ID=?";

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
			String sql = "SELECT * FROM STYLING_CATEGORY WHERE NAME=?";

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
			String sql = "SELECT * FROM STYLING_CATEGORY WHERE MENU_ID=? AND CATEGORY_ID=?";

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

	public boolean isStylingName(String name)
	{
		boolean result = false;

		try
		{
			String sql = "SELECT * FROM STYLING WHERE NAME=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, name);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				result = true;
				System.out.println("스타일링 이름 존재");
			}
			else
				System.out.println("스타일링 이름 존재 X");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("isStylingName 실패");
		}

		return result;
	}

	public int getStylingId()
	{
		int re = 0;

		try
		{
			String sql = "SELECT * FROM STYLING ORDER BY STYLING_ID";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
				re = Integer.valueOf(rs.getNString("STYLING_ID").substring(7));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("getStylingId 실패");
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