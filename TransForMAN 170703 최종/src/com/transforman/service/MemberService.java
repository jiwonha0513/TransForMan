package com.transforman.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.transforman.bean.Forward;
import com.transforman.bean.Member;
import com.transforman.dao.MemberDao;

public class MemberService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;

	private Forward forward;

	private MemberDao dao;

	public MemberService(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request = request;
		this.response = response;
	}

	public Forward execute(int code)
	{
		switch(code)
		{
		case 1:
			showMemberList(); 
			break;

		case 2:
			updateStatus(); 
			break;

		case 3:
			searchMemberList(); 
			break;
		}

		return forward;
	}

	private void showMemberList() {
		dao = new MemberDao();

		String path = "";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");
			path = "./index";
		}
		else if(!sessionId.toString().equals("admin"))
		{
			request.setAttribute("msg", "권한 없음");
			path = "./index";
		}
		else
		{
			ArrayList<Member> mList = dao.selectMemberList();

			if(mList != null)
			{
				String memberList = getMemberList(dao, mList);
				request.setAttribute("memberList", memberList);

				path = "showMemberList.jsp";
			}

		}

		dao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void updateStatus()
	{
		dao = new MemberDao();

		String path = "";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");
			path = "./index";
		}
		else if(!sessionId.toString().equals("admin"))
		{
			request.setAttribute("msg", "권한 없음");

			forward = new Forward();
			forward.setRedirect(false);
			forward.setPath("./index");
		}
		else
		{
			String id = request.getParameter("blackId");

			int statusId = dao.selectMember(id).getStatus();
			if(statusId == 0)
				statusId = 1;
			else
				statusId = 0;

			dao.updateMemberStatus(id, statusId);
			ArrayList<Member> mList = dao.selectMemberList();

			if(mList != null)
			{
				String memberList = getMemberList(dao, mList);

				try 
				{
					response.getWriter().write(memberList);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();

				}
			}
			dao.close();
		}

	}

	private void searchMemberList()
	{
		dao = new MemberDao();

		String path = "";

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
			String keyword = request.getParameter("searchKeyword");

			ArrayList<Member> mList = dao.searchMemberList(keyword);

			if(mList != null)
			{
				String memberList = getMemberList(dao, mList);

				try 
				{
					response.getWriter().write(memberList);
				}
				catch (IOException e) 
				{
					e.printStackTrace();

				}
			}

		}
	}

	private String getMemberList(MemberDao dao, ArrayList<Member> mList)
	{
		StringBuilder result = new StringBuilder();
		result.append("<table id='memberList'>");

		result.append("<tr>");
		result.append("<td rowspan='2'>구매자 아이디</td>");
		result.append("<td>이름</td>");
		result.append("<td>별명</td>");
		result.append("<td>생년월일</td>");
		result.append("<td>핸드폰 번호</td>");
		result.append("<td>이메일</td>");
		result.append("<td>분류</td>");
		result.append("<td rowspan='2'></td>");
		result.append("</tr>");

		result.append("<tr>");
		result.append("<td class='address' colspan='6'>주소</td>");
		result.append("</tr>");

		for(int i=0; i<mList.size(); i++)
		{
			Member m = mList.get(i);

			if(!m.getUserId().equals("admin"))
			{
				int changeStatus;

				if(dao.isBlack(m.getUserId()))
					changeStatus = 1;
				else
					changeStatus = 0;


				result.append("<tr>");
				result.append("<td rowspan='2'>" + m.getUserId() + "</td>");
				result.append("<td>" + m.getUserName() + "</td>");
				result.append("<td>" + m.getNickname() + "</td>");
				result.append("<td>" + m.getBirth() + "</td>");
				result.append("<td>" + m.getPhone() + "</td>");
				result.append("<td>" + m.getEmail() + "</td>");
				result.append("<td rowspan='2'>" + dao.selectKindName(m.getKind()) + "</td>");
				result.append("<td rowspan='2'><input type='button' class='btn' id='toggleBlack' onclick=\"updateStatus('" + m.getUserId() + "')\" value=\"" + dao.selectStatusName(changeStatus) + " 전환\"/></td>");
				result.append("</tr>");

				result.append("<tr>");
				result.append("<td class='address' colspan='6'>" + m.getPostcode() + " " + m.getAddress1() + " &nbsp;" + m.getAddress2() + "</td>");
				result.append("</tr>");

			}
		}

		result.append("</table>");

		return result.toString();
	}
}
