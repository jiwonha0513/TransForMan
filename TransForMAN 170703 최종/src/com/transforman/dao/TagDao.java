package com.transforman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.transforman.bean.HashTag;

public class TagDao 
{
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds; //추가

	public TagDao()
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

	public boolean insertTag(HashTag tag)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO HASH_TAG VALUES(?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, tag.getTagId());
			pstmt.setNString(2, tag.getTagName());

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result=true;
				System.out.println("태그 삽입 성공 !");
			}
			else
				System.out.println("태그 삽입 실패 !");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("insertTag 실패");
		}

		return result;
	}

	public boolean insertStylingTag(String stylingId, int tagId)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO STYLING_TAG VALUES(?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, stylingId);
			pstmt.setInt(2, tagId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result=true;
				System.out.println("스타일링 태그 삽입 성공 !");
			}
			else
				System.out.println("스타일링 태그 삽입 실패 !");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("insertStylingTag 실패");
		}

		return result;
	}

	public boolean insertProductTag(String productId, int tagId)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO PRODUCT_TAG VALUES(?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, productId);
			pstmt.setInt(2, tagId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result=true;
				System.out.println("상품 태그 삽입 성공 !");
			}
			else
				System.out.println("상품 태그 삽입 실패 !");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("insertProductTag 실패");
		}

		return result;
	}

	public boolean deleteStylingTag(String stylingId)
	{
		boolean result = false;

		try
		{
			String sql = "DELETE FROM STYLING_TAG WHERE STYLING_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, stylingId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("스타일링-태그 삭제 완료");
			}
			else
			{
				System.out.println("없는 스타일링 ID");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("deleteStylingTag 실패");
		}

		return result;
	}

	public boolean deleteProductTag(String productId)
	{
		boolean result = false;

		try
		{
			String sql = "DELETE FROM PRODUCT_TAG WHERE PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, productId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("상품-태그 삭제 완료");
			}
			else
			{
				System.out.println("없는 상품 ID");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("deleteStylingTag 실패");
		}

		return result;
	}

	public ArrayList<HashTag> selectStylingTagList(String stylingId)
	{
		ArrayList<HashTag> hList = new ArrayList<>();

		try
		{
			String sql = "SELECT * FROM STYLING_TAG ST INNER JOIN HASH_TAG H ON ST.TAG_ID=H.TAG_ID WHERE STYLING_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, stylingId);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				HashTag tag = new HashTag();

				tag.setTagId(rs.getInt("TAG_ID"));
				tag.setTagName(rs.getNString("NAME"));

				hList.add(tag);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectStylingTagList 실패");
		}

		return hList;
	}

	public ArrayList<HashTag> selectProductTagList(String productId)
	{
		ArrayList<HashTag> hList = new ArrayList<>();

		try
		{
			String sql = "SELECT * FROM PRODUCT_TAG PT INNER JOIN HASH_TAG H ON PT.TAG_ID=H.TAG_ID WHERE PRODUCT_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, productId);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				HashTag tag = new HashTag();

				tag.setTagId(rs.getInt("TAG_ID"));
				tag.setTagName(rs.getNString("NAME"));

				hList.add(tag);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectProductTagList 실패");
		}

		return hList;
	}

	public int selectTagId(String name)
	{
		int re = 0;

		try
		{
			String sql = "SELECT * FROM HASH_TAG WHERE NAME=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, name);

			rs = pstmt.executeQuery();

			if(rs.next())
				re = rs.getInt("TAG_ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("getTagId 실패");
		}

		return re;
	}

	public boolean isTagName(String name)
	{
		boolean result = false;

		try
		{
			String sql = "SELECT * FROM HASH_TAG WHERE NAME=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, name);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				result = true;
				System.out.println("태그 이름 존재");
			}
			else
				System.out.println("태그 이름 존재 X");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("isTagName 실패");
		}

		return result;
	}

	public int getTagId()
	{
		int re = 0;

		try
		{
			String sql = "SELECT * FROM HASH_TAG ORDER BY TAG_ID";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
				re = rs.getInt("TAG_ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("getTagId 실패");
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
