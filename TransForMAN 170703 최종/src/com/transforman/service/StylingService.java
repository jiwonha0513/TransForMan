package com.transforman.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.transforman.bean.Comment;
import com.transforman.bean.Forward;
import com.transforman.bean.HashTag;
import com.transforman.bean.Product;
import com.transforman.bean.Styling;
import com.transforman.bean.StylingCategory;
import com.transforman.dao.MemberDao;
import com.transforman.dao.StylingDao;
import com.transforman.dao.TagDao;
import com.transforman.dao.CommentDao;

public class StylingService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;

	private Forward forward;

	private MemberDao mDao;
	private StylingDao sDao;
	private TagDao tDao;
	private CommentDao cDao;

	public StylingService(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request = request;
		this.response = response;
	}

	public Forward execute(int code)
	{
		switch(code)
		{
		case 1:
			stBeauty();
			break;

		case 2:
			stHair();
			break;

		case 3:
			stMakeup();
			break;

		case 4:
			stCoordi();
			break;

		case 5:
			stDaily();
			break;

		case 6:
			stBoyfriend();
			break;

		case 7:
			stVacance();
			break;

		case 8:
			stDetail();
			break;

		case 9:
			stInsertComment();
			break;

		case 10:
			stDeleteComment();
			break;

		case 11:
			stSearchStyling();
			break;
		}

		return forward;
	}

	private void stBeauty()
	{
		sDao = new StylingDao();

		int menuId = sDao.selectMenuId("헤어 메이크업");

		ArrayList<StylingCategory> sList = sDao.selectStylingCategoryList();

		if(sList != null)
		{
			String stMenu = getCategoryList(sList, menuId);

			request.setAttribute("stMenu", stMenu);
		}

		sDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("stMenu.jsp");
	}

	private void stHair()
	{
		sDao = new StylingDao();

		int menuId = sDao.selectMenuId("헤어 메이크업");
		int categoryId = sDao.selectCategoryId("헤어");

		ArrayList<Styling> sList = sDao.selectStylingList(menuId, categoryId);

		if(sList.size() != 0)
		{
			String stCategory = getStylingList(sList);

			request.setAttribute("stCategory", stCategory);
		}
		else
			request.setAttribute("stCategory", "스타일링이 존재하지 않습니다.");

		sDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("stCategory.jsp");
	}

	private void stMakeup()
	{
		sDao = new StylingDao();

		int menuId = sDao.selectMenuId("헤어 메이크업");
		int categoryId = sDao.selectCategoryId("메이크업");

		ArrayList<Styling> sList = sDao.selectStylingList(menuId, categoryId);

		if(sList.size() != 0)
		{
			String stCategory = getStylingList(sList);

			request.setAttribute("stCategory", stCategory);
		}
		else
			request.setAttribute("stCategory", "스타일링이 존재하지 않습니다.");

		sDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("stCategory.jsp");
	}

	private void stCoordi()
	{
		sDao = new StylingDao();

		int menuId = sDao.selectMenuId("코디");

		ArrayList<StylingCategory> sList = sDao.selectStylingCategoryList();

		if(sList != null)
		{
			String stMenu = getCategoryList(sList, menuId);

			request.setAttribute("stMenu", stMenu);
		}

		sDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("stMenu.jsp");
	}

	private void stDaily()
	{
		sDao = new StylingDao();

		int menuId = sDao.selectMenuId("코디");
		int categoryId = sDao.selectCategoryId("데일리룩");

		ArrayList<Styling> sList = sDao.selectStylingList(menuId, categoryId);

		if(sList.size() != 0)
		{
			String stCategory = getStylingList(sList);

			request.setAttribute("stCategory", stCategory);
		}
		else
			request.setAttribute("stCategory", "스타일링이 존재하지 않습니다.");

		sDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("stCategory.jsp");
	}

	private void stBoyfriend()
	{
		sDao = new StylingDao();

		int menuId = sDao.selectMenuId("코디");
		int categoryId = sDao.selectCategoryId("남친룩");

		ArrayList<Styling> sList = sDao.selectStylingList(menuId, categoryId);

		if(sList.size() != 0)
		{
			String stCategory = getStylingList(sList);

			request.setAttribute("stCategory", stCategory);
		}
		else
			request.setAttribute("stCategory", "스타일링이 존재하지 않습니다.");

		sDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("stCategory.jsp");
	}

	private void stVacance()
	{
		sDao = new StylingDao();

		int menuId = sDao.selectMenuId("코디");
		int categoryId = sDao.selectCategoryId("바캉스룩");

		ArrayList<Styling> sList = sDao.selectStylingList(menuId, categoryId);

		if(sList.size() != 0)
		{
			String stCategory = getStylingList(sList);

			request.setAttribute("stCategory", stCategory);
		}
		else
			request.setAttribute("stCategory", "스타일링이 존재하지 않습니다.");

		sDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("stCategory.jsp");
	}

	private void stDetail()
	{
		sDao = new StylingDao();
		tDao = new TagDao();
		cDao = new CommentDao();

		String stylingId;
		if(request.getAttribute("data") == null)
			stylingId = request.getParameter("data");
		else
			stylingId = request.getAttribute("data").toString();

		Styling s = sDao.selectStyling(stylingId);

		request.setAttribute("imagePath", "upload/" + s.getImageName());
		request.setAttribute("stylingName", s.getStylingName());
		request.setAttribute("stylingExplain", s.getExplain());

		String buttons = "";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId != null)
		{
			mDao = new MemberDao();

			int kindId = mDao.selectMember(sessionId.toString()).getKind();
			String kind = mDao.selectKindName(kindId);

			if(kind.equals("관리자"))
			{
				buttons = "<input type='button' class='btn detailBtn' onclick=\"deleteStyling1('" + stylingId + "')\" value='삭제' />";
			}
			else if(kind.equals("판매자"))
			{
				buttons = "<input type='button' class='btn detailBtn' onclick=\"updateStylingForm('" + stylingId + "')\" value='수정' />";
				buttons += "<input type='button' class='btn detailBtn' onclick=\"deleteStyling1('" + stylingId + "')\" value='삭제' />";
			}
		}

		request.setAttribute("buttons", buttons);

		String video;
		String url = s.getVideoUrl();

		if(url == null)
			url = "";

		if(!url.equals(""))
		{
			if(url.length() < 30)
				url = url.substring(17);
			else
				url = url.substring(32);

			video = "<iframe width='560' height='315' src='https://www.youtube.com/embed/"+url+"' frameborder='0' allowfullscreen></iframe>";

			request.setAttribute("video", video);
		}

		ArrayList<HashTag> hList = tDao.selectStylingTagList(stylingId);

		if(hList != null)
		{
			String hashTag = getHashTagList(hList);
			request.setAttribute("hashTag", hashTag);
		}

		ArrayList<Product> pList = sDao.selectStylingProductList(stylingId);

		if(pList != null)
		{
			String productList = getProductList(pList);
			request.setAttribute("productList", productList);
		}

		ArrayList<Comment> cList = cDao.selectStCommentList(stylingId);

		if(cList.size() != 0)
		{
			String commentList; 
			if(sessionId == null)
				commentList = getCommentList("", cList);
			else
				commentList = getCommentList(sessionId.toString(), cList);
			request.setAttribute("commentList", commentList);
		}

		cDao.close();
		tDao.close();
		sDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("stDetail.jsp");
	}

	private void stInsertComment()
	{
		sDao = new StylingDao();
		cDao = new CommentDao();

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			try
			{
				response.getWriter().write("로그인을 해주세요.");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else
		{
			try
			{
				int imageSize = 10 * 1024 * 1024;
				String uploadPath = session.getServletContext().getRealPath("upload");

				MultipartRequest mr = new MultipartRequest(request, uploadPath, imageSize, 
						"UTF-8", new DefaultFileRenamePolicy());

				String stylingName = mr.getParameter("stylingName");
				String stylingId = sDao.selectStylingId(stylingName);
				String strStar = mr.getParameter("star");
				String content = mr.getParameter("content");
				String imageName = mr.getFilesystemName("commentImage");

				double star;
				if(strStar == null)
					star = 0.0;
				else
					star = Double.valueOf(strStar);

				Comment c = new Comment();
				c.setContent(content);
				c.setStar(star);
				c.setImageName(imageName);
				c.setMemberId(sessionId.toString());
				c.setStylingId(stylingId);

				cDao.insertComment(cDao.getCommentsId(), c);

				ArrayList<Comment> cList = cDao.selectStCommentList(stylingId);

				if(cList != null)
				{
					String commentList = getCommentList(sessionId.toString(), cList);
					try
					{
						response.getWriter().write(commentList);
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		cDao.close();
		sDao.close();
	}

	private void stDeleteComment()
	{
		System.out.println("stDeleteComment 시작");
		sDao = new StylingDao();
		cDao = new CommentDao();

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			try
			{
				response.getWriter().write("로그인을 해주세요.");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else
		{
			int commentId = Integer.parseInt(request.getParameter("commentId"));
			String stylingId = request.getParameter("stylingId");
			
			System.out.println(stylingId);

			if(cDao.deleteComment(commentId))
				System.out.println("comment 삭제");
			
			ArrayList<Comment> cList = cDao.selectStCommentList(stylingId);

			if(cList.size() != 0)
			{
				String commentList = getCommentList(sessionId.toString(), cList);

				System.out.println(commentList);
				
				try
				{
					response.getWriter().write(commentList);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}

		cDao.close();
		sDao.close();
	}

	private void stSearchStyling()
	{
		sDao = new StylingDao();
		
		String keyword = request.getParameter("searchKeyword");
		
		ArrayList<Styling> sList = sDao.searchStyling(keyword);
		
		if(sList.size() != 0)
		{
			String stylingList = getStylingList(sList);
			
			try
			{
				response.getWriter().write(stylingList);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		sDao.close();
	}

	private String getCategoryList(ArrayList<StylingCategory> sList, int menuId)
	{
		StringBuilder result = new StringBuilder();

		for(int i=0; i<sList.size(); i++)
		{
			StylingCategory category = sList.get(i);

			if(category.getMenuId() == menuId)
			{
				String imagePath = "";
				String url = "";

				if(category.getCategoryName().equals("헤어"))
				{
					imagePath = "./images/hair1.jpg";
					url = "./stHair";
				}
				else if(category.getCategoryName().equals("메이크업"))
				{
					imagePath = "./images/makeup1.jpg";
					url = "./stMakeup";
				}
				else if(category.getCategoryName().equals("데일리룩"))
				{
					imagePath = "./images/daily.jpg";
					url = "./stDaily";
				}
				else if(category.getCategoryName().equals("남친룩"))
				{
					imagePath = "./images/boyfriend.jpg";
					url = "./stBoyfriend";
				}
				else if(category.getCategoryName().equals("바캉스룩"))
				{
					imagePath = "./images/vacance.jpg";
					url = "./stVacance";
				}

				result.append("<a href='#'><img src='" + imagePath + "' id='thumbnail' onclick=\"category('" + url + "', '" + menuId + "', '" + category.getCategoryId() + "')\" /></a>");				
			}
		}

		return result.toString();
	}

	private String getStylingList(ArrayList<Styling> sList)
	{
		System.out.println(sList.size());
		StringBuilder result = new StringBuilder();

		for(int i = 0; i<sList.size(); i++)
		{
			Styling s = sList.get(i);

			result.append("<a href='#'><div class='thumbnail' onclick=\"detail('" + s.getStylingId() + "')\">");
			result.append("<img src=\"upload/" + s.getImageName() + "\" id='thumbnail' />");
			result.append("<span>" + s.getStylingName() + "</span>");
			result.append("</div></a>");
		}

		return result.toString();
	}

	private String getHashTagList(ArrayList<HashTag> hList)
	{
		StringBuilder result = new StringBuilder();

		result.append("해시태그 : ");

		for(int i = 0; i<hList.size(); i++)
		{
			HashTag h = hList.get(i);

			result.append("<a href='#'>" + h.getTagName() + "</a>");
			result.append("&nbsp");
		}

		return result.toString();
	}

	private String getProductList(ArrayList<Product> pList)
	{
		StringBuilder result = new StringBuilder();
		result.append("<h5>&nbsp;&nbsp;&nbsp;&nbsp;관련 상품들</h5>");
		result.append("<table>");

		for(int i=0; i<pList.size(); i++)
		{
			Product p = pList.get(i);

			result.append("<tr>");
			result.append("<td><img id='smallThumbnail' src=\"upload/" + p.getImageName() + "\" /></td>");
			result.append("<td><a href='#' onclick=\"detail('" + p.getProductId() + "')\" style='font-size:10pt;'>" + p.getProductName() + "</a></td>");
			result.append("<td>" + p.getPrice() + "</td>");
			result.append("<td><input type='button' class='btn' onclick=\"insertMyCart('" + p.getProductId() + "')\" value='장바구니'/></td>");
			result.append("<td><input type='button' class='btn' onclick=\"orderProduct('" + p.getProductId() + "')\" value='구매'/></td>");
			result.append("</tr>");
		}

		result.append("</table>");

		return result.toString();
	}

	private String getCommentList(String id, ArrayList<Comment> cList)
	{
		StringBuilder result = new StringBuilder();

		for(int i=0; i<cList.size(); i++)
		{
			Comment c = cList.get(i);

			String imageName;
			if(c.getImageName() == null)
				imageName = "";
			else
				imageName = c.getImageName();

			result.append("<div class='row'>");
			result.append("<div class='col-md-12'>");

			result.append("<p>");
			result.append(c.getMemberId() + "&nbsp;&nbsp;&nbsp;");
			if(c.getStar() >= 5)
				result.append("★★★★★");
			else if(c.getStar() >= 4)
				result.append("★★★★☆");
			else if(c.getStar() >= 3)
				result.append("★★★☆☆");
			else if(c.getStar() >= 2)
				result.append("★★☆☆☆");
			else
				result.append("★☆☆☆☆");
			result.append("<span class='pull-right'>" + c.getDate() + "</span>");
			result.append("</p>");
			result.append("<p>");
			if(!imageName.equals(""))
				result.append("<img id='smallThumbnail' class='commentImage' src=\"upload/" + c.getImageName() + "\" />");
			result.append(c.getContent());
			result.append("</p>");


			if(id.equals(c.getMemberId()) || id.equals("admin"))
			{
				result.append("<span class='pull-right'>");
				result.append("<a href='#' onclick=\"deleteStylingComment('" + c.getCommentId() + "', '" + c.getStylingId() + "')\">삭제</a>");
				result.append("</span>");
			}

			result.append("</div>");
			result.append("</div>");

			result.append("<hr style='border-color: #949494;' />");
		}

		System.out.println(result.toString());
		return result.toString();
	}
}
