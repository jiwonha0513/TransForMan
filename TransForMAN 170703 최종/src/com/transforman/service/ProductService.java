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
import com.transforman.bean.ProductCategory;
import com.transforman.dao.CommentDao;
import com.transforman.dao.MemberDao;
import com.transforman.dao.ProductDao;
import com.transforman.dao.TagDao;

public class ProductService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;

	private Forward forward;

	private MemberDao mDao;
	private ProductDao pDao;
	private TagDao tDao;
	private CommentDao cDao;

	public ProductService(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request = request;
		this.response = response;
	}

	public Forward execute(int code)
	{
		switch(code)
		{
		case 1:
			spBeauty();
			break;

		case 2:
			spHair();
			break;

		case 3:
			spMakeup();
			break;

		case 4:
			spTop();
			break;

		case 5:
			spTshirt();
			break;

		case 6:
			spShirt();
			break;

		case 7:
			spHood();
			break;

		case 8:
			spBottoms();
			break;

		case 9:
			spJeans();
			break;

		case 10:
			spPants();
			break;

		case 11:
			spTraining();
			break;

		case 12:
			spOuter();
			break;

		case 13:
			spCoat();
			break;

		case 14:
			spJacket();
			break;

		case 15:
			spCardigan();
			break;

		case 16:
			spAccessory();
			break;

		case 17:
			spWatch();
			break;

		case 18:
			spSneakers();
			break;

		case 19:
			spShoes();
			break;

		case 20:
			spDetail();
			break;

		case 21:
			spInsertComment();
			break;

		case 22:
			spDeleteComment();
			break;

		case 23:
			spSearchProduct();
			break;	
		}

		return forward;
	}

	private void spBeauty()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("헤어 메이크업");

		ArrayList<ProductCategory> pList = pDao.selectProductCategoryList();
		if(pList != null)
		{
			String spMenu = getCategoryList(pList, menuId);

			request.setAttribute("spMenu", spMenu);
		}

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spMenu.jsp");
	}

	private void spHair()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("헤어 메이크업");
		int categoryId = pDao.selectCategoryId("헤어");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spMakeup()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("헤어 메이크업");
		int categoryId = pDao.selectCategoryId("메이크업");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spTop()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("상의");

		ArrayList<ProductCategory> pList = pDao.selectProductCategoryList();
		if(pList != null)
		{
			String spMenu = getCategoryList(pList, menuId);

			request.setAttribute("spMenu", spMenu);
		}

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spMenu.jsp");
	}

	private void spTshirt()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("상의");
		int categoryId = pDao.selectCategoryId("티셔츠");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spShirt()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("상의");
		int categoryId = pDao.selectCategoryId("셔츠");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spHood()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("상의");
		int categoryId = pDao.selectCategoryId("후드티");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spBottoms()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("하의");

		ArrayList<ProductCategory> pList = pDao.selectProductCategoryList();
		if(pList != null)
		{
			String spMenu = getCategoryList(pList, menuId);

			request.setAttribute("spMenu", spMenu);
		}

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spMenu.jsp");
	}

	private void spJeans()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("하의");
		int categoryId = pDao.selectCategoryId("청바지");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spPants()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("하의");
		int categoryId = pDao.selectCategoryId("바지");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spTraining()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("하의");
		int categoryId = pDao.selectCategoryId("트레이닝");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spOuter()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("아우터");

		ArrayList<ProductCategory> pList = pDao.selectProductCategoryList();

		if(pList != null)
		{
			String spMenu = getCategoryList(pList, menuId);

			request.setAttribute("spMenu", spMenu);
		}

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spMenu.jsp");
	}

	private void spCoat()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("아우터");
		int categoryId = pDao.selectCategoryId("코트");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spJacket()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("아우터");
		int categoryId = pDao.selectCategoryId("자켓");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spCardigan()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("아우터");
		int categoryId = pDao.selectCategoryId("가디건");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spAccessory()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("악세서리 etc");

		ArrayList<ProductCategory> pList = pDao.selectProductCategoryList();
		if(pList != null)
		{
			String spMenu = getCategoryList(pList, menuId);

			request.setAttribute("spMenu", spMenu);
		}

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spMenu.jsp");
	}

	private void spWatch()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("악세서리 etc");
		int categoryId = pDao.selectCategoryId("시계");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spSneakers()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("악세서리 etc");
		int categoryId = pDao.selectCategoryId("운동화");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spShoes()
	{
		pDao = new ProductDao();

		int menuId = pDao.selectMenuId("악세서리 etc");
		int categoryId = pDao.selectCategoryId("구두");

		ArrayList<Product> pList = pDao.selectProductList(menuId, categoryId);

		if(pList.size() != 0)
		{
			String spCateogry = getProductList(pList);

			request.setAttribute("spCategory", spCateogry);
		}
		else
			request.setAttribute("spCategory", "상품이 존재하지 않습니다.");

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spCategory.jsp");
	}

	private void spDetail()
	{
		pDao = new ProductDao();
		tDao = new TagDao();
		cDao = new CommentDao();

		String productId;
		if(request.getAttribute("data") == null)
			productId = request.getParameter("data");
		else
			productId = request.getAttribute("data").toString();

		System.out.println(productId);

		Product p = pDao.selectProduct(productId);

		request.setAttribute("imagePath", "upload/" + p.getImageName());
		request.setAttribute("productName", p.getProductName());
		request.setAttribute("productPrice", p.getPrice());
		request.setAttribute("productExplain", p.getExplain());

		String buttons = "<input type='button' class='btn detailBtn' onclick=\"insertMyCart('" + p.getProductId() + "')\" value='장바구니' />";
		buttons += "<input type='button' class='btn detailBtn' onclick=\"orderProduct('" + p.getProductId() + "')\" value='구매' />";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId != null)
		{
			mDao = new MemberDao();

			int kindId = mDao.selectMember(sessionId.toString()).getKind();
			String kind = mDao.selectKindName(kindId);

			if(kind.equals("관리자"))
			{
				buttons = "<input type='button' class='btn detailBtn' onclick=\"deleteProduct1('" + productId + "')\" value='삭제' />";
			}
			else if(kind.equals("판매자"))
			{
				buttons = "<input type='button' class='btn detailBtn' onclick=\"updateProductForm('" + productId + "')\"  value='수정' />";
				buttons += "<input type='button' class='btn detailBtn' onclick=\"deleteProduct1('" + productId + "')\" value='삭제' />";
			}
		}

		request.setAttribute("buttons", buttons);

		ArrayList<HashTag> hList = tDao.selectProductTagList(productId);

		if(hList != null)
		{
			String hashTag = getHashTagList(hList);
			request.setAttribute("hashTag", hashTag);
		}

		ArrayList<Comment> cList = cDao.selectStCommentList(productId);

		if(cList.size() != 0)
		{
			String commentList; 
			if(sessionId == null)
				commentList = getCommentList("", cList);
			else
				commentList = getCommentList(sessionId.toString(), cList);
			request.setAttribute("commentList", commentList);
		}

		String msg = request.getParameter("data2");
		if(msg == null)
			msg = "";

		if(!msg.equals(""))
		{
			request.setAttribute("countMsg", msg);
			request.setAttribute("count", p.getCount());
		}

		cDao.close();
		tDao.close();
		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath("spDetail.jsp");
	}

	private void spInsertComment()
	{
		pDao = new ProductDao();
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

				String productName = mr.getParameter("productName");
				String productId = pDao.selectProductId(productName);
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
				c.setProductId(productId);

				cDao.insertComment(cDao.getCommentsId(), c);

				ArrayList<Comment> cList = cDao.selectStCommentList(productId);

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
		pDao.close();
	}

	private void spDeleteComment()
	{
		System.out.println("spDeleteComment 시작");
		pDao = new ProductDao();
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
			String productId = request.getParameter("productId");

			if(cDao.deleteComment(commentId))
				System.out.println("comment 삭제");

			ArrayList<Comment> cList = cDao.selectSpCommentList(productId);

			if(cList.size() != 0)
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

		cDao.close();
		pDao.close();
	}

	private void spSearchProduct()
	{
		pDao = new ProductDao();

		String keyword = request.getParameter("searchKeyword");
				
		ArrayList<Product> pList = pDao.searchProductList(keyword);
		
		if(pList.size() != 0)
		{
			String productList = getProductList(pList);

			try
			{
				response.getWriter().write(productList);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}


		pDao.close();
	}

	private String getCategoryList(ArrayList<ProductCategory> pList, int menuId)
	{
		StringBuilder result = new StringBuilder();

		for(int i=0; i<pList.size(); i++)
		{
			ProductCategory category = pList.get(i);

			if(category.getMenuId() == menuId)
			{
				String imagePath = "";
				String url = "";

				if(category.getCategoryName().equals("헤어"))
				{
					imagePath = "./images/hair2.jpg";
					url = "./spHair";
				}
				else if(category.getCategoryName().equals("메이크업"))
				{
					imagePath = "./images/makeup2.jpg";
					url = "./spMakeup";
				}
				else if(category.getCategoryName().equals("티셔츠"))
				{
					imagePath = "./images/tshirt1.jpg";
					url = "./spTshirt";
				}
				else if(category.getCategoryName().equals("셔츠"))
				{
					imagePath = "./images/shirt1.jpg";
					url = "./spShirt";
				}
				else if(category.getCategoryName().equals("후드티"))
				{
					imagePath = "./images/hood1.jpg";
					url = "./spHood";
				}
				else if(category.getCategoryName().equals("청바지"))
				{
					imagePath = "./images/jeans1.jpg";
					url = "./spJeans";
				}
				else if(category.getCategoryName().equals("바지"))
				{
					imagePath = "./images/pants1.jpg";
					url = "./spPants";
				}
				else if(category.getCategoryName().equals("트레이닝"))
				{
					imagePath = "./images/training1.jpg";
					url = "./spTraining";
				}
				else if(category.getCategoryName().equals("코트"))
				{
					imagePath = "./images/coat1.jpg";
					url = "./spCoat";
				}
				else if(category.getCategoryName().equals("자켓"))
				{
					imagePath = "./images/jacket1.jpg";
					url = "./spJacket";
				}
				else if(category.getCategoryName().equals("가디건"))
				{
					imagePath = "./images/cardigan1.jpg";
					url = "./spCardigan";
				}
				else if(category.getCategoryName().equals("시계"))
				{
					imagePath = "./images/watch1.jpg";
					url = "./spWatch";
				}
				else if(category.getCategoryName().equals("운동화"))
				{
					imagePath = "./images/sneakers1.png";
					url = "./spSneakers";
				}
				else if(category.getCategoryName().equals("구두"))
				{
					imagePath = "./images/shoes1.jpg";
					url = "./spShoes";
				}


				result.append("<a href='#'><img src='" + imagePath + "' id='thumbnail' onclick=\"category('" + url + "', '" + menuId + "', '" + category.getCategoryId() + "')\" style='border:1px' /></a>");				
			}
		}

		return result.toString();
	}

	private String getProductList(ArrayList<Product> pList)
	{
		StringBuilder result = new StringBuilder();

		for(int i = 0; i<pList.size(); i++)
		{
			Product p = pList.get(i);

			result.append("<a href='#'><div class='thumbnail' onclick=\"detail('" + p.getProductId() + "')\">");
			result.append("<img src=\"upload/" + p.getImageName() + "\" id='thumbnail' />");
			result.append("<span>" + p.getProductName() + "</span>");
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
				result.append("<a href='#' onclick=\"deleteProductComment('" + c.getCommentId() + "', '" + c.getProductId() + "')\">삭제</a>");
				result.append("</span>");
			}

			result.append("</div>");
			result.append("</div>");

			result.append("<hr style='border-color: #949494;' />");
		}

		return result.toString();
	}
}
