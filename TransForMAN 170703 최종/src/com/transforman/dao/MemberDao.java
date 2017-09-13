package com.transforman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.transforman.bean.Member;

public class MemberDao 
{
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds; //추가

	public MemberDao()
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

	public boolean insertMember(Member mb)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, mb.getUserId());
			pstmt.setNString(2, mb.getPw());
			pstmt.setNString(3, mb.getUserName());
			pstmt.setNString(4, mb.getNickname());
			pstmt.setNString(5, mb.getBirth());
			pstmt.setInt(6, mb.getAge());
			pstmt.setInt(7, mb.getPostcode());
			pstmt.setNString(8, mb.getAddress1());
			pstmt.setNString(9, mb.getAddress2());
			pstmt.setNString(10, mb.getPhone());
			pstmt.setNString(11,mb.getEmail());
			pstmt.setNString(12,mb.getBusinessNumber());

			if(mb.getBusinessNumber().equals(""))
				pstmt.setInt(13, 3);
			else
				pstmt.setInt(13, 2);

			pstmt.setInt(14, 1);

			int re = pstmt.executeUpdate();

			if(re != 0){
				result = true;
				System.out.println("insertMember 성공");
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("insertMember 성공");
		}

		return result;
	}

	public boolean insertWithdrawal(String id)
	{
		boolean result = false;

		try
		{
			String sql = "INSERT INTO MEMBER_WITHDRAWAL VALUES(?, DEFAULT)";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, id);

			int re = pstmt.executeUpdate();

			if(re!=0)
			{
				result=true;
				System.out.println("삽입 성공");
			}
			else
				System.out.println("없는 ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return result;
	}

	public boolean updateInfo(Member m)
	{
		boolean result = false;

		try
		{
			String sql = "UPDATE MEMBER SET PW=?, BIRTH=?, POSTCODE=?, ADDRESS1=?, ADDRESS2=?, PHONE=? WHERE MEMBER_ID=?";

			pstmt=con.prepareStatement(sql);

			pstmt.setNString(1, m.getPw());
			pstmt.setNString(2, m.getBirth());
			pstmt.setInt(3, m.getPostcode());
			pstmt.setNString(4, m.getAddress1());
			pstmt.setNString(5, m.getAddress2());
			pstmt.setNString(6, m.getPhone());
			pstmt.setNString(7, m.getUserId());

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("회원정보 수정 성공");
			}
			else
				System.out.println("없는 ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("updateInfo 실패");
		}

		return result;
	}

	public boolean updatePw(String id, String pw)
	{
		boolean result = false;

		try
		{
			String sql = "UPDATE MEMBER SET PW=? WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, pw);
			pstmt.setNString(2, id);

			int re = pstmt.executeUpdate();

			System.out.println(re);

			if(re != 0)
			{
				result = true;
				System.out.println("비밀번호 변경 성공");
			}
			else
				System.out.println("없는 ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("updatePw 실패");
		}

		return result;
	}

	public boolean updateMemberKind(String id, int kind)
	{
		boolean result = false;

		try
		{
			String sql = "UPDATE MEMBER SET KIND_ID=? WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, kind);
			pstmt.setNString(2, id);

			int re = pstmt.executeUpdate();

			if(re!=0)
			{
				result = true;
				System.out.println("kind 변경");
			}
			else
				System.out.println("없는 ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("updateMemberKind 실패");
		}

		return result;
	}

	public boolean updateMemberStatus(String id, int statusId)
	{
		boolean result = false;

		try
		{
			String sql="UPDATE MEMBER SET STATUS_ID=? WHERE MEMBER_ID=?";

			pstmt=con.prepareStatement(sql);

			pstmt.setInt(1, statusId);
			pstmt.setNString(2, id);

			int re = pstmt.executeUpdate();

			if(re != 0)
			{
				result = true;
				System.out.println("블랙리스트 등록");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("updateMemberStatus 실패");
		}

		return result;
	}

	public Member selectMember(String id)
	{
		Member mb = new Member();

		String sql="SELECT * FROM MEMBER WHERE MEMBER_ID=?";

		try
		{
			pstmt=con.prepareStatement(sql);

			pstmt.setNString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				mb.setUserId(rs.getNString("MEMBER_ID"));
				mb.setPw(rs.getNString("PW"));
				mb.setUserName(rs.getNString("NAME"));
				mb.setNickname(rs.getNString("NICKNAME"));
				mb.setBirth(rs.getNString("BIRTH"));
				mb.setAge(rs.getInt("AGE"));
				mb.setPostcode(rs.getInt("POSTCODE"));
				mb.setAddress1(rs.getNString("ADDRESS1"));
				mb.setAddress2(rs.getNString("ADDRESS2"));
				mb.setPhone(rs.getNString("PHONE"));
				mb.setEmail(rs.getNString("EMAIL"));
				mb.setBusinessNumber(rs.getNString("BUSINESS_NUMBER"));
				mb.setKind(rs.getInt("KIND_ID"));
				mb.setStatus(rs.getInt("STATUS_ID"));
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectMember 실패");
		}

		return mb;
	}

	public ArrayList<Member> selectMemberList()
	{
		ArrayList<Member> mList = new ArrayList<Member>();

		try
		{
			String sql = "SELECT * FROM MEMBER";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Member m = new Member();
				m.setUserId(rs.getNString("MEMBER_ID"));
				m.setPw(rs.getNString("PW"));
				m.setUserName(rs.getNString("NAME"));
				m.setNickname(rs.getNString("NICKNAME"));
				m.setBirth(rs.getNString("BIRTH"));
				m.setAge(rs.getInt("AGE"));
				m.setPostcode(rs.getInt("POSTCODE"));
				m.setAddress1(rs.getNString("ADDRESS1"));
				m.setAddress2(rs.getNString("ADDRESS2"));
				m.setPhone(rs.getNString("PHONE"));
				m.setEmail(rs.getNString("EMAIL"));
				m.setBusinessNumber(rs.getNString("BUSINESS_NUMBER"));
				m.setKind(rs.getInt("KIND_ID"));
				m.setStatus(rs.getInt("STATUS_ID"));

				mList.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return mList;
	}

	public ArrayList<Member> searchMemberList(String keyword)
	{
		ArrayList<Member> mList = new ArrayList<Member>();

		try
		{
			String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID LIKE '%'||?||'%' "
					+ "OR NICKNAME LIKE '%'||?||'%' OR NAME LIKE '%'||?||'%' "
					+ "OR EMAIL LIKE '%'||?||'%'";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, keyword);
			pstmt.setNString(2, keyword);
			pstmt.setNString(3, keyword);
			pstmt.setNString(4, keyword);

			rs = pstmt.executeQuery();

			while(rs.next())
			{
				Member m = new Member();

				m.setUserId(rs.getNString("MEMBER_ID"));
				m.setPw(rs.getNString("PW"));
				m.setUserName(rs.getNString("NAME"));
				m.setNickname(rs.getNString("NICKNAME"));
				m.setBirth(rs.getNString("BIRTH"));
				m.setAge(rs.getInt("AGE"));
				m.setPostcode(rs.getInt("POSTCODE"));
				m.setAddress1(rs.getNString("ADDRESS1"));
				m.setAddress2(rs.getNString("ADDRESS2"));
				m.setPhone(rs.getNString("PHONE"));
				m.setEmail(rs.getNString("EMAIL"));
				m.setBusinessNumber(rs.getNString("BUSINESS_NUMBER"));
				m.setKind(rs.getInt("KIND_ID"));
				m.setStatus(rs.getInt("STATUS_ID"));

				mList.add(m);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return mList;
	}

	public String selectId(String email, String birth, String name)
	{
		String id = "";

		try
		{
			String sql="SELECT * FROM MEMBER WHERE EMAIL=? AND BIRTH=? AND NAME=?";

			pstmt=con.prepareStatement(sql);

			pstmt.setNString(1, email);
			pstmt.setNString(2, birth);
			pstmt.setNString(3, name);

			rs = pstmt.executeQuery();

			if(rs.next())
				id = rs.getNString("MEMBER_ID");

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectMember 실패");
		}

		return id;
	}

	public int selectKindId(String id)
	{
		int kind = 0;

		try
		{
			String sql = "SELECT KIND_ID FROM MEMBER WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				kind = rs.getInt("KIND_ID");
				System.out.println("member_kind : " + kind);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectKindId 실패");
		}

		return kind;
	}

	public String selectKindName(int kindId)
	{
		String kind = null;

		try
		{
			String sql = "SELECT NAME FROM MEMBER_KIND WHERE KIND_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, kindId);

			rs = pstmt.executeQuery();

			if(rs.next())
				kind = rs.getNString("NAME");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectKindName 실패");
		}

		return kind;
	}

	public String selectStatusName(int statusId)
	{
		String status = null;

		try
		{
			String sql = "SELECT NAME FROM MEMBER_STATUS WHERE STATUS_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, statusId);

			rs = pstmt.executeQuery();

			if(rs.next())
				status = rs.getNString("NAME");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("selectStatusName 실패");
		}

		return status;
	}

	public boolean isMember(String id,String pw)
	{
		boolean result = false;

		try
		{
			String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				if(rs.getNString("pw").equals(pw))
				{
					result = true;
					System.out.println("통과");
				}
				else
					System.out.println("비번 틀림");
			}
			else
			{
				System.out.println("없는 ID");
				result = false;
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("isMember 실패");
		}

		return result;
	}

	public boolean isId(String id)
	{
		boolean result = false;

		try
		{
			String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				result = true;
				System.out.println("사용중인 ID");
			}
			else
				System.out.println("사용 할 수 있는 ID");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("isId 실패");
		}
		return result;
	}

	public boolean isNickname(String nickName)
	{
		boolean result = false;

		try
		{
			String sql = "SELECT * FROM MEMBER WHERE NICKNAME=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, nickName);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				result = true;
				System.out.println("사용중인 NICKNAME");
			}
			else
				System.out.println("사용 할 수 있는 NICKNAME");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("isNickname 실패");
		}

		return result;
	}

	public boolean isEmail(String email)
	{
		boolean result = false;

		try
		{
			String sql = "SELECT * FROM MEMBER WHERE EMAIL=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, email);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				result = true;
				System.out.println("사용중인 EMAIL");
			}
			else
				System.out.println("사용 할 수 있는 EMAIL");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("isMember 실패");
		}

		return result;
	}

	public boolean isBlack(String id)
	{
		boolean result = false;

		try
		{
			String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setNString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				System.out.println(rs.getInt("STATUS_ID"));
				if(rs.getInt("STATUS_ID") == 0)
				{
					result = true;
					System.out.println("black");
				}
				else
					System.out.println("white");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("isBlack 실패");
		}

		return result;
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
