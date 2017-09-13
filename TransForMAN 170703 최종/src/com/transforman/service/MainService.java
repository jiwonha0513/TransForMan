package com.transforman.service;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.transforman.bean.Forward;
import com.transforman.bean.Member;
import com.transforman.dao.MemberDao;

public class MainService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;

	private Forward forward;

	private MemberDao dao;

	public MainService(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request = request;
		this.response = response;
	}

	public Forward execute(int code)
	{
		switch(code)
		{
		case 0:
			index();
			break;
			
		case 1:
			loginForm();
			break;

		case 2:
			login();
			break;

		case 3:
			logout();
			break;

		case 4:
			findInfoForm();
			break;

		case 5:
			selectId();
			break;

		case 6:
			sendCode();
			break;

		case 7:
			updatePwForm();
			break;

		case 8:
			updatePw();
			break;

		case 9:
			showMyInfo();
			break;

		case 10:
			updateInfoForm();
			break;

		case 11:
			updateInfo();
			break;

		case 12:
			deleteMember();
			break; 

		case 13:
			joinForm();
			break;

		case 14:
			insertMember();
			break;

		case 15:
			checkJoinForm();
			break;

		case 16:
			checkLoginForm();
			break;

		case 17:
			checkFindInfoForm();
			break;

		case 18:
			checkUpdatePwForm();
			break;

		case 19:
			checkUpdateInfoForm();
			break;

		case 20:
			checkDeleteMember();
			break;
		}

		return forward;
	}

	private void index()
	{
		HttpSession session = request.getSession();

		Object kind = session.getAttribute("userKind");

		if(session.getAttribute("userId") == null)
		{
			session.invalidate();
			request.setAttribute("login", 0);	// 로그아웃 상태
		}
		else if(kind != null)
		{
			int kindNum = Integer.valueOf(kind.toString());

			if(kindNum == 1)
				session.setAttribute("login", kindNum);	// 관리자 로그인
			else if(kindNum == 2)
				session.setAttribute("login", kindNum);	// 판매자 로그인
			else
				session.setAttribute("login", kindNum);	// 일반회원 로그인
		}

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("index.jsp");
	}

	private void loginForm()
	{
		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("loginForm.jsp");
	}

	private void login()
	{
		HttpSession session = request.getSession();

		dao = new MemberDao();

		String id = request.getParameter("userId");

		int kind = dao.selectKindId(id);

		session.setAttribute("userId", id);
		session.setAttribute("userKind", kind);

		dao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("./index");
	}


	private void logout()
	{
		HttpSession session = request.getSession();
		session.invalidate();

		request.setAttribute("login", 0);

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("./index");
	}

	private void findInfoForm()
	{
		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("findInfoForm.jsp");
	}

	private void selectId()
	{
		dao = new MemberDao();

		String email = request.getParameter("userEmail");
		String birth = request.getParameter("userBirth1");
		String name = request.getParameter("userName");

		String id = dao.selectId(email, birth, name);

		if(id.equals(""))
			request.setAttribute("findInfoMsg", "실패");
		else
		{
			request.setAttribute("findInfoMsg", "성공");

			// 메일 보내기

			MailService mail = new MailService();
			mail.sendId(email, id);
		}

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("./index");

		dao.close();
	}

	private void sendCode()
	{
		dao = new MemberDao();

		int flag = Integer.valueOf(request.getParameter("flag"));
		String email = request.getParameter("userEmail");
		String msgEmail;
		String msgSend = "";

		if(flag == 1)
		{
			// 중복 이메일 체크
			
			if(dao.isEmail(email))
				msgEmail = "중복된 이메일이 있습니다.";
			else
			{
				msgEmail = "";
				msgSend = "이메일의 인증번호를 확인해주세요.";

				// 인증번호 생성

				Random random = new Random();
				int code = random.nextInt(1000000) + 100000;

				if(code > 1000000)
					code = code - 100000;

				// 메일 보내기

				MailService mail = new MailService();
				mail.sendCode(email, code);

				HttpSession session = request.getSession();
				session.setAttribute("code", code);
			}
		}
		else
		{
			if(!dao.isEmail(email))
				msgEmail = "가입된 이메일 정보가 없습니다.";
			else
			{
				msgEmail = "";
				msgSend = "이메일의 인증번호를 확인해주세요.";

				// 인증번호 생성

				Random random = new Random();
				int code = random.nextInt(1000000) + 100000;

				if(code > 1000000)
					code = code - 100000;

				// 메일 보내기

				MailService mail = new MailService();
				mail.sendCode(email, code);

				HttpSession session = request.getSession();
				session.setAttribute("code", code);
			}
		}

		try 
		{
			response.getWriter().write(msgEmail + msgSend);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();

		}

		dao.close();
	}

	private void updatePwForm()
	{
		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("updatePwForm.jsp");
	}

	private void updatePw()
	{
		dao = new MemberDao();

		String msg = null;

		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");

		boolean result = dao.updatePw(id, pw);

		dao.close();

		if(!result)
		{
			msg = "실패";
			request.setAttribute("updatePwMsg", msg);
		}
		else
		{
			msg = "성공";
			request.setAttribute("updatePwMsg", msg);
		}

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("./index");
	}

	private void showMyInfo()
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
		else
		{
			String id = sessionId.toString();
			Member m = dao.selectMember(id);

			if(m != null)
			{
				String myInfo = getMyInfo(m);
				String updateInfo = "<div class='button'><input type='button' class='btn' onclick='updateInfo()' value='수정'/></div>";
				request.setAttribute("myInfo", myInfo);
				request.setAttribute("button", updateInfo);

				path = "showMyInfo.jsp";
			}
		}

		dao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path); 
	}

	private void updateInfoForm()
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
		else
		{
			Member m = dao.selectMember(sessionId.toString());
			
			request.setAttribute("userId", m.getUserId());
			request.setAttribute("userBirth", m.getBirth());
			request.setAttribute("userPostcode", m.getPostcode());
			request.setAttribute("userAddress", m.getAddress1());
			request.setAttribute("userAddress2", m.getAddress2());
			request.setAttribute("userPhone", m.getPhone());
			
			path = "updateInfoForm.jsp";
		}
		
		dao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void updateInfo()
	{
		dao = new MemberDao();

		String path = "";
		String message = "";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");
			path = "./index";
		}
		else
		{
			String id = sessionId.toString();
			String pw = request.getParameter("userPw");
			String birth = request.getParameter("userBirth");
			int postcode = Integer.valueOf(request.getParameter("userPostcode"));
			String address1 = request.getParameter("userAddress");
			String address2 = request.getParameter("userAddress2");
			String phone = request.getParameter("userPhone");
			
			Member m = new Member();
			m.setUserId(id);
			m.setPw(pw);
			m.setBirth(birth);
			m.setPostcode(postcode);
			m.setAddress1(address1);
			m.setAddress2(address2);
			m.setPhone(phone);

			if(dao.updateInfo(m))
			{
				session.invalidate();

				request.setAttribute("login", 0);

				message = "성공";
			}
			else
				message = "실패";

			request.setAttribute("updateInfoMsg", message);
		}

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("./index");
	}

	private void deleteMember()
	{
		dao = new MemberDao();

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");

		}
		else
		{
			String id = sessionId.toString();

			if(dao.updateMemberKind(id, 0) && dao.insertWithdrawal(id))
			{
				session.invalidate();

				request.setAttribute("login", 0);
			}
		}

		dao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("./index");
	}

	private void joinForm()
	{
		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("joinForm.jsp");
	}

	private void insertMember()
	{
		dao = new MemberDao();

		String path = null;
		String message = null;

		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		String name = request.getParameter("userName");
		String nickname = request.getParameter("userNickname");
		String birth = request.getParameter("userBirth");
		String postcode = request.getParameter("userPostcode");
		String address1 = request.getParameter("userAddress");
		String address2 = request.getParameter("userAddress2");
		String phone = request.getParameter("userPhone");
		String email = request.getParameter("userEmail");
		String businessNumber = request.getParameter("userBusiness");

		Member member = new Member();
		member.setUserId(id);
		member.setPw(pw);
		member.setUserName(name);
		member.setNickname(nickname);
		member.setBirth(birth);
		int age = 2017 - Integer.valueOf(birth.substring(0, 4)) + 1;
		member.setAge(age);
		member.setPostcode(Integer.valueOf(postcode));
		member.setAddress1(address1);
		member.setAddress2(address2);
		member.setPhone(phone);
		member.setEmail(email);
		member.setBusinessNumber(businessNumber);

		if(dao.insertMember(member))
			path = "./index";
		else
		{
			message = "실패";
			path = "./joinForm";
		}

		request.setAttribute("joinMsg", message);

		forward = new Forward();
		forward.setRedirect(true);
		forward.setPath(path);
	}

	private void checkJoinForm()
	{
		HttpSession session = request.getSession();

		Object sessionCode = session.getAttribute("code");

		dao = new MemberDao();

		int check = 0;

		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		String conPw = request.getParameter("confirmPw");
		String name = request.getParameter("userName");
		String nickname = request.getParameter("userNickname");
		String birth = request.getParameter("userBirth");
		String postcode = request.getParameter("userPostcode");
		String address1 = request.getParameter("userAddress");
		String address2 = request.getParameter("userAddress2");
		String phone = request.getParameter("userPhone");
		String email = request.getParameter("userEmail");
		String code = request.getParameter("userCode");

		String msgId;
		String msgPw;
		String msgName;
		String msgNick;
		String msgBirth;
		String msgAddress;
		String msgPhone;
		String msgEmail;
		String msgCode;

		if(id.equals("") || id == null)
		{
			msgId = "아이디를 입력해주세요.";
			check++;
		}
		else
		{
			if(dao.isId(id))
			{
				msgId = "중복된 아이디가 있습니다.";
				check++;
			}
			else
				msgId = "";
		}

		if(pw.equals("") || pw == null)
		{
			msgPw = "비밀번호를 입력해주세요.";
			check++;
		}
		else
		{
			if(conPw.equals("") || conPw == null)
			{
				msgPw = "비밀번호 확인을 입력해주세요.";
				check++;
			}

			else if(!pw.equals(conPw))
			{
				msgPw = "비밀번호가 일치하지 않습니다.";
				check++;
			}
			else
				msgPw = "";
		}

		if(name.equals("") || name == null)
		{
			msgName = "이름을 입력해주세요.";
			check++;
		}
		else
			msgName = "";

		if(nickname.equals("") || nickname == null)
		{
			msgNick = "별명을 입력해주세요.";
			check++;
		}
		else
		{
			if(dao.isNickname(nickname))
			{
				msgNick = "중복된 별명이 있습니다.";
				check++;
			}
			else
				msgNick = "";
		}

		if(birth.equals("") || birth == null)
		{
			msgBirth = "생년월일을 입력해주세요.";
			check++;
		}
		else
		{
			if(birth.length() != 8)
			{
				msgBirth = "생년월일의 형식이 틀립니다.";
				check++;
			}
			else
				msgBirth = "8글자로 입력해주세요. ex)20170612";
		}

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

		if(phone.equals("") || phone == null)
		{
			msgPhone = "핸드폰 번호를 입력해주세요.";
			check++;
		}
		else
			msgPhone = "ex)010-0000-0000";

		if(email.equals("") || email == null)
		{
			msgEmail = "이메일을 입력해주세요.";
			check++;
		}
		else
		{
			if(dao.isEmail(email))
			{
				msgEmail = "중복된 이메일이 있습니다.";
				check++;
			}
			else
				msgEmail = "";
		}

		if(code.equals("") || code == null)
		{
			msgCode = "인증번호를 입력해주세요.";
			check++;
		}
		else if(sessionCode == null)
		{
			msgCode = "인증번호 발송을 해주세요.";
			check++;
		}
		else if(!sessionCode.toString().equals(code))
		{
			msgCode = "인증번호가 일치하지 않습니다.";
			check++;
		}
		else
			msgCode = "";

		dao.close();

		try 
		{
			response.getWriter().write(msgId + "," + msgPw + "," + msgName + "," + msgNick + "," +
					msgBirth + "," + msgAddress + "," + msgPhone + "," + msgEmail + "," + msgCode +"," + check);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();

		}
	}

	private void checkLoginForm()
	{
		dao = new MemberDao();

		int check = 0;

		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");

		String msgId = "";
		String msgPw = "";
		String msgCheck = "";

		if(id.equals("") || id == null)
		{
			msgId = "아이디를 입력해주세요.";
			check++;
		}
		else if(!dao.isMember(id, pw))
		{
			msgCheck = "가입한 정보가 없거나 로그인 정보가 일치하지 않습니다.";
			check++;
		}
		else if(dao.isBlack(id))
		{
			System.out.println("탈퇴");
			msgCheck = "정지된 회원이거나 탈퇴한 회원입니다.";
			check++;
		}
		else
			msgId = "";

		if(pw.equals("") || pw == null)
		{
			msgPw = "비밀번호를 입력해주세요.";
			check++;
		}
		else
			msgPw = "";

		try
		{
			response.getWriter().write(msgId + "," + msgPw + "," + check + "," + msgCheck);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private void checkFindInfoForm()
	{
		dao = new MemberDao();

		int check = 0;

		// flag == 1이면 selectIdForm 검사, flag == 2면 updatePwForm 검사
		int flag = Integer.valueOf(request.getParameter("flag"));

		
		String email = request.getParameter("userEmail");

		String msgEmail;

		if(email.equals("") || email == null)
		{
			msgEmail = "이메일을 입력해주세요.";
			check++;
		}
		else 
		{
			if(!dao.isEmail(email))
			{
				msgEmail = "존재하지 않는 이메일입니다.";
				check++;
			}
			else
				msgEmail = "";
		}

		if(flag == 1)
		{
			String birth = request.getParameter("userBirth");
			String msgBirth;
			String name = request.getParameter("userName");
			String msgName;

			if(birth.equals("") || birth == null)
			{
				msgBirth = "생년월일을 입력해주세요.";
				check++;
			}
			else
			{
				if(birth.length() != 8)
				{
					msgBirth = "생년월일의 형식이 틀립니다.";
					check++;
				}
				else
					msgBirth = "8글자로 입력해주세요. ex)20170612";
			}
			
			if(name.equals("") || name == null)
			{
				msgName = "이름을 입력해주세요.";
				check++;
			}
			else
				msgName = "";

			try
			{
				response.getWriter().write(msgName + "," + msgBirth + "," + msgEmail + "," + check);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}

		else if(flag == 2)
		{
			HttpSession session = request.getSession();

			Object sessionCode = session.getAttribute("code");

			String id = request.getParameter("userId");
			String msgId;
			String code = request.getParameter("userCode");
			String msgCode;

			if(id.equals("") || id == null)
			{
				msgId = "아이디를 입력해주세요.";
				check++;
			}
			else 
			{
				if(!dao.isId(id))
				{
					msgId = "존재하지 않는 아이디입니다.";
					check++;
				}
				else
					msgId = "";
			}

			if(code.equals("") || code == null)
			{
				msgCode = "인증번호를 입력해주세요.";
				check++;
			}
			else if(sessionCode == null)
			{
				msgCode = "인증번호 발송을 해주세요.";
				check++;
			}
			else if(!sessionCode.toString().equals(code))
			{
				msgCode = "인증번호가 일치하지 않습니다.";
				check++;
			}
			else
				msgCode = "";

			try
			{
				response.getWriter().write(msgId + "," + msgEmail + "," + msgCode + "," + check);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}

		dao.close();
	}

	private void checkUpdatePwForm()
	{
		int check = 0;

		String pw = request.getParameter("userPw");
		String conPw = request.getParameter("confirmPw");

		String msgPw;

		if(pw.equals("") || pw == null)
		{
			msgPw = "비밀번호를 입력해주세요.";
			check++;
		}
		else
		{
			if(conPw.equals("") || conPw == null)
			{
				msgPw = "비밀번호 확인을 입력해주세요.";
				check++;
			}

			else if(!pw.equals(conPw))
			{
				msgPw = "비밀번호가 일치하지 않습니다.";
				check++;
			}
			else
				msgPw = "";
		}

		try 
		{
			response.getWriter().write(msgPw + "," + check);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();

		}
	}

	private void checkUpdateInfoForm()
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
			dao = new MemberDao();

			int check = 0;

			String pw = request.getParameter("userPw");
			String conPw = request.getParameter("confirmPw");
			String birth = request.getParameter("userBirth");
			String postcode = request.getParameter("userPostcode");
			String address1 = request.getParameter("userAddress");
			String address2 = request.getParameter("userAddress2");
			String phone = request.getParameter("userPhone");

			String msgPw;
			String msgBirth;
			String msgAddress;
			String msgPhone;

			if(pw.equals("") || pw == null)
			{
				msgPw = "비밀번호를 입력해주세요.";
				check++;
			}
			else
			{
				if(conPw.equals("") || conPw == null)
				{
					msgPw = "비밀번호 확인을 입력해주세요.";
					check++;
				}

				else if(!pw.equals(conPw))
				{
					msgPw = "비밀번호가 일치하지 않습니다.";
					check++;
				}
				else
					msgPw = "";
			}

			if(birth.equals("") || birth == null)
			{
				msgBirth = "생년월일을 입력해주세요.";
				check++;
			}
			else
			{
				if(birth.length() != 8)
				{
					msgBirth = "생년월일의 형식이 틀립니다.";
					check++;
				}
				else
					msgBirth = "8글자로 입력해주세요. ex)20170612";
			}

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

			if(phone.equals("") || phone == null)
			{
				msgPhone = "핸드폰 번호를 입력해주세요.";
				check++;
			}
			else
				msgPhone = "ex)010-0000-0000";

			dao.close();

			try 
			{
				response.getWriter().write(msgPw + "," + msgBirth + "," + msgAddress + "," + 
						msgPhone + "," + check);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();

			}
		}
	}

	private void checkDeleteMember()
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
			dao = new MemberDao();

			String id = sessionId.toString();
			String pw = dao.selectMember(id).getPw();
			String deletePW = request.getParameter("deletePw");

			String msgDeletePw = "";

			int check = 0;

			if(!pw.equals(deletePW))
			{
				msgDeletePw = "비밀번호가 일치하지 않습니다.";
				check++;
			}
			else
				msgDeletePw = "";

			try 
			{
				response.getWriter().write(msgDeletePw + "," + check);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();

			}

			dao.close();
		}
	}

	private String getMyInfo(Member m)
	{
		StringBuilder result = new StringBuilder();

		result.append("<table>");

		result.append("<tr>");
		result.append("<td>아이디</td>");
		result.append("<td id='userId'>" + m.getUserId() + "</td>");
		result.append("</tr>");
		result.append("<tr>");
		result.append("<td>이름</td>");
		result.append("<td>" + m.getUserName() + "</td>");
		result.append("</tr>");
		result.append("<tr>");
		result.append("<td>별명</td>");
		result.append("<td>" + m.getNickname() + "</td>");
		result.append("</tr>");
		result.append("<tr>");
		result.append("<td>생년월일</td>");
		result.append("<td>" + m.getBirth() + "</td>");
		result.append("</tr>");
		result.append("<tr>");
		result.append("<td>주소</td>");
		result.append("<td>" + m.getPostcode() + " " + m.getAddress1() + " &nbsp;" + m.getAddress2() + "</td>");
		result.append("</tr>");
		result.append("<tr>");
		result.append("<td>이메일</td>");
		result.append("<td>" + m.getEmail() + "</td>");
		result.append("</tr>");
		result.append("<tr>");
		result.append("<td>핸드폰 번호</td>");
		result.append("<td>" + m.getPhone() + "</td>");
		result.append("</tr>");

		if(m.getBusinessNumber() != null)
		{
			result.append("<tr>");
			result.append("<td>사업자 번호</td>");
			result.append("<td>" + m.getBusinessNumber() + "</td>");
			result.append("</tr>");
		}

		result.append("</table>");

		return result.toString();
	}

}
