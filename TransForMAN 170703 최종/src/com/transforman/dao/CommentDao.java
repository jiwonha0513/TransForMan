package com.transforman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.transforman.bean.Comment;

public class CommentDao 
{
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds; //추가

	public CommentDao()
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

	public boolean insertComment(int commentId, Comment c)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO COMMENTS VALUES(?, ?, ?, DEFAULT, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, commentId);
			pstmt.setNString(2, c.getContent());
			pstmt.setDouble(3, c.getStar());
			pstmt.setNString(4, c.getImageName());
			pstmt.setNString(5, c.getMemberId());
			if(c.getProductId() == null)
			{
				pstmt.setNString(6, null);
				pstmt.setNString(7, c.getStylingId());
			}
			else
			{
				pstmt.setNString(6, c.getProductId());
				pstmt.setNString(7, null);
			}

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("Comment 입력 성공");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("stInsertComment 실패");
		}

		return result;
	}

	public boolean updateComment(Comment c)
	{
		boolean result = false;

		try
		{
			String sql = "UPDATE COMMENTS SET CONTENT=?, STAR=?, COMMENTS_DATE=DEFAULT, IMAGE_NAME=? WHERE COMMENTS_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, c.getContent());
			pstmt.setDouble(2, c.getStar());
			pstmt.setNString(3, c.getImageName());
			pstmt.setInt(4, c.getCommentId());

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("Comment 수정 성공");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("spUpdateComment 실패");
		}

		return result;
	}

	public boolean deleteComment(int commentId)
	{
		boolean result = false;

		try
		{
			String sql = "DELETE FROM COMMENTS WHERE COMMENTS_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, commentId);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("Comment 삭제 성공");
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("deleteComment 실패");
		}

		return result;
	}

	public ArrayList<Comment> selectStCommentList(String stylingId)
	{
		System.out.println(stylingId);
		ArrayList<Comment> cList = new ArrayList<>();

		try
		{
			String sql = "SELECT * FROM COMMENTS WHERE STYLING_ID=? ORDER BY COMMENTS_ID DESC";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, stylingId);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Comment c = new Comment();

				c.setCommentId(rs.getInt("COMMENTS_ID"));
				c.setContent(rs.getNString("CONTENT"));
				c.setStar(rs.getDouble("STAR"));
				c.setDate(rs.getNString("COMMENTS_DATE"));
				c.setImageName(rs.getNString("IMAGE_NAME"));
				c.setMemberId(rs.getNString("MEMBER_ID"));
				c.setProductId(rs.getNString("PRODUCT_ID"));
				c.setStylingId(rs.getNString("STYLING_ID"));

				cList.add(c);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectStCommentList 실패");
		}

		return cList;
	}

	public ArrayList<Comment> selectSpCommentList(String productId)
	{
		ArrayList<Comment> cList = new ArrayList<>();

		try
		{
			String sql = "SELECT * FROM COMMENTS WHERE PRODUCT_ID=? ORDER BY COMMENTS_ID DESC";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, productId);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Comment c = new Comment();

				c.setCommentId(rs.getInt("COMMENTS_ID"));
				c.setContent(rs.getNString("CONTENTS"));
				c.setStar(rs.getDouble("STAR"));
				c.setDate(rs.getNString("COMMENTS_DATE"));
				c.setImageName(rs.getNString("IMAGE_NAME"));
				c.setMemberId(rs.getNString("MEMBER_ID"));
				c.setProductId(rs.getNString("PRODUCT_ID"));
				c.setStylingId(rs.getNString("STYLING_ID"));

				cList.add(c);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectSpCommentList 실패");
		}

		return cList;
	}

	public int getCommentsId()
	{
		int re = 0;

		try
		{
			String sql = "SELECT * FROM COMMENTS ORDER BY COMMENTS_ID";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
				re = rs.getInt("COMMENTS_ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("getCommentsId 실패");
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
