package com.transforman.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.transforman.bean.Forward;
import com.transforman.bean.HashTag;
import com.transforman.bean.Product;
import com.transforman.bean.Styling;
import com.transforman.dao.MemberDao;
import com.transforman.dao.ProductDao;
import com.transforman.dao.StylingDao;
import com.transforman.dao.TagDao;

public class ItemService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;

	private Forward forward;

	private MemberDao mDao;
	private ProductDao pDao;
	private StylingDao sDao;
	private TagDao tDao;

	public ItemService(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request = request;
		this.response = response;
	}

	public Forward execute(int code)
	{
		switch(code)
		{
		case 1:
			registerProductForm();
			break;

		case 2:
			registerStylingForm();
			break;

		case 3:
			insertProduct();
			break;

		case 4:
			insertStyling();
			break;

		case 5:
			updateProduct();
			break;

		case 6:
			updateStyling();
			break;

		case 7:
			deleteProduct();
			break;

		case 8:
			deleteStyling();
			break;

		case 9:
			checkRegisterProductForm();
			break;

		case 10:
			checkRegisterStylingForm();
			break;

		case 11:
			showStylingList();
			break;

		case 12:
			showProductList();
			break;
			
		case 13:
			updateStylingForm();
			break;
			
		case 14:
			updateProductForm();
			break;
		}

		return forward;
	}

	private void registerProductForm()
	{
		String path = "";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");
			path = "./index";
		}
		else
			path = "registerProductForm.jsp";

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void registerStylingForm()
	{
		String path = "";

		HttpSession session = request.getSession();

		Object sessionId = session.getAttribute("userId");

		if(sessionId == null)
		{
			request.setAttribute("msg", "로그인 필요");
			path = "./index";
		}
		else
			path = "registerStylingForm.jsp";

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	// insertProduct 하고나면 detail로 가도록 
	private void insertProduct()
	{
		pDao = new ProductDao();
		tDao = new TagDao();

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
			try
			{
				int imageSize = 10 * 1024 * 1024;
				String uploadPath = session.getServletContext().getRealPath("upload");

				MultipartRequest mr = new MultipartRequest(request, uploadPath, imageSize, 
						"UTF-8", new DefaultFileRenamePolicy());

				String menu = mr.getParameter("menu");
				String hairCategory = mr.getParameter("hairSelect");
				String topCategory = mr.getParameter("topSelect");
				String bottomsCategory = mr.getParameter("bottomsSelect");
				String outerCategory = mr.getParameter("outerSelect");
				String accessoryCategory = mr.getParameter("accessorySelect");
				String name = mr.getParameter("productName");
				String price = mr.getParameter("productPrice");
				String count = mr.getParameter("productCount");
				String explain = mr.getParameter("productExplain");
				String tag = mr.getParameter("productTag");
				String[] tags = tag.split(" ");
				String imageName = mr.getFilesystemName("productImage");

				if(hairCategory == null)
					hairCategory = "";
				if(topCategory == null)
					topCategory = "";
				if(bottomsCategory == null)
					bottomsCategory = "";
				if(outerCategory == null)
					outerCategory = "";
				if(accessoryCategory == null)
					accessoryCategory = "";

				// 해시태그
				ArrayList<HashTag> hList = getTagList(tDao, tags);

				// 상품
				Product p = new Product();
				p.setProductName(name);
				p.setPrice(Integer.valueOf(price));
				p.setCount(Integer.valueOf(count));
				p.setImageName(imageName);
				p.setExplain(explain);
				p.setMenuId(Integer.valueOf(menu));
				if(!hairCategory.equals(""))
					p.setCategoryId(Integer.valueOf(hairCategory));
				if(!topCategory.equals(""))
					p.setCategoryId(Integer.valueOf(topCategory));
				if(!bottomsCategory.equals(""))
					p.setCategoryId(Integer.valueOf(bottomsCategory));
				if(!outerCategory.equals(""))
					p.setCategoryId(Integer.valueOf(outerCategory));
				if(!accessoryCategory.equals(""))
					p.setCategoryId(Integer.valueOf(accessoryCategory));
				p.setHashTag(hList);

				pDao.insertProduct(sessionId.toString(), pDao.getProductId(), p);

				String productId = pDao.selectProductId(p.getProductName());
				for(int i=0; i<p.getHashTag().size(); i++)
				{
					tDao.insertProductTag(productId, p.getHashTag().get(i).getTagId());
				}

				path = "./spDetail";
				request.setAttribute("data", productId);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		tDao.close();
		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void insertStyling()
	{
		pDao = new ProductDao();
		sDao = new StylingDao();
		tDao = new TagDao();

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
			try
			{
				int imageSize = 10 * 1024 * 1024;
				String uploadPath = session.getServletContext().getRealPath("upload");

				MultipartRequest mr = new MultipartRequest(request, uploadPath, imageSize, 
						"UTF-8", new DefaultFileRenamePolicy());

				String menu = mr.getParameter("menu");
				String hairCategory = mr.getParameter("hairSelect");
				String coordiCategory = mr.getParameter("coordiSelect");
				String name = mr.getParameter("stylingName");
				String explain = mr.getParameter("stylingExplain");
				String tag = mr.getParameter("stylingTag");
				String[] tags = tag.split(" ");
				String imageName = mr.getFilesystemName("stylingImage");
				String videoUrl = mr.getParameter("stylingVideo");
				String productName = mr.getParameter("productName");
				String[] products = productName.split(",");

				if(hairCategory == null)
					hairCategory = "";
				if(coordiCategory == null)
					coordiCategory = "";

				// 해시태그
				ArrayList<HashTag> hList;
				if(tag == null || tag.equals(""))
					hList = null;
				else
					hList = getTagList(tDao, tags);

				// 상품

				ArrayList<Product> pList;
				if(productName.equals("") || productName == null)
					pList = null;
				else
				{
					pList = new ArrayList<>();
					for(int i=0; i<products.length; i++)
					{
						String productId = pDao.selectProductId(products[i].trim());
						Product p = pDao.selectProduct(productId);
						System.out.println(p.getProductId());
						pList.add(p);
					}
				}

				// 스타일링
				Styling s = new Styling();
				s.setStylingName(name);
				s.setExplain(explain);
				s.setImageName(imageName);
				s.setVideoUrl(videoUrl);
				s.setMenuId(Integer.valueOf(menu));
				if(!hairCategory.equals(""))
					s.setCategoryId(Integer.valueOf(hairCategory));
				if(!coordiCategory.equals(""))
					s.setCategoryId(Integer.valueOf(coordiCategory));
				s.setHashTag(hList);
				s.setProduct(pList);

				sDao.insertStyling(sessionId.toString(), sDao.getStylingId(), s);
				String stylingId = sDao.selectStylingId(s.getStylingName());

				if(s.getHashTag() != null)
				{
					for(int i=0; i<s.getHashTag().size(); i++)
					{
						System.out.println(stylingId);
						tDao.insertStylingTag(stylingId, s.getHashTag().get(i).getTagId());
					}
				}

				if(!productName.equals(""))
				{
					System.out.println(s.getProduct().size());
					for(int i=0; i<s.getProduct().size(); i++)
						sDao.insertStylingProduct(stylingId, s.getProduct().get(i).getProductId());
				}

				path = "./stDetail";
				request.setAttribute("data", stylingId);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		tDao.close();
		sDao.close();
		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void updateProduct()
	{
	}

	private void updateStyling()
	{
	}

	private void deleteProduct()
	{
		pDao = new ProductDao();

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
			mDao = new MemberDao();
			tDao = new TagDao();

			int kindId = mDao.selectMember(sessionId.toString()).getKind();
			String kind = mDao.selectKindName(kindId);

			if(kind.equals("일반회원"))
			{
				request.setAttribute("msg", "권한 없음");

				path = "./index";
			}
			else
			{
				String productId = request.getParameter("productId");
				
				pDao.deleteStylingProduct(productId);
				pDao.deleteProduct(productId);
				tDao.deleteProductTag(productId);

				ArrayList<Product> pList;

				if(kind.equals("관리자"))
					pList = pDao.selectProductList();
				else
					pList = pDao.selectProductList(sessionId.toString());

				if(pList != null)
				{
					String productList = getProductList(pDao, pList);
					request.setAttribute("productList", productList);

					path = "showProductList.jsp";
				}
			}
			
			mDao.close();
			tDao.close();
		}
		
		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void deleteStyling()
	{
		sDao = new StylingDao();
		tDao = new TagDao();

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

			mDao = new MemberDao();

			int kindId = mDao.selectMember(sessionId.toString()).getKind();
			String kind = mDao.selectKindName(kindId);

			if(kind.equals("일반회원"))
			{
				request.setAttribute("msg", "권한 없음");

				path = "./index";
			}
			else
			{
				String stylingId = request.getParameter("stylingId");

				sDao.deleteStylingProduct(stylingId);
				sDao.deleteStyling(stylingId);
				tDao.deleteStylingTag(stylingId);

				ArrayList<Styling> sList;

				if(kind.equals("관리자"))
					sList = sDao.selectStylingList();
				else
					sList = sDao.selectStylingList(sessionId.toString());

				if(sList != null)
				{
					String stylingList = getStylingList(sDao, sList);
					request.setAttribute("stylingList", stylingList);

					path = "showStylingList.jsp";
				}
			}

			mDao.close();
			tDao.close();
		}

		sDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void checkRegisterProductForm()
	{
		pDao = new ProductDao();

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
			try
			{
				int check = 0;

				int imageSize = 10 * 1024 * 1024;
				String uploadPath = session.getServletContext().getRealPath("upload");

				MultipartRequest mr = new MultipartRequest(request, uploadPath, imageSize, 
						"UTF-8", new DefaultFileRenamePolicy());

				String menu = mr.getParameter("menu");
				String hairCategory = mr.getParameter("hairCategory");
				String topCategory = mr.getParameter("topCategory");
				String bottomsCategory = mr.getParameter("bottomsCategory");
				String outerCategory = mr.getParameter("outerCategory");
				String accessoryCategory = mr.getParameter("accessoryCategory");
				String name = mr.getParameter("productName");
				String price = mr.getParameter("productPrice");
				String count = mr.getParameter("productCount");
				String explain = mr.getParameter("productExplain");
				String tag = mr.getParameter("productTag");
				String[] tags = tag.split(" ");
				String imageName = mr.getFilesystemName("productImage");

				String msgMenu;
				String msgProductName;
				String msgProductPrice;
				String msgProductCount;
				String msgProductExplain;
				String msgProductTag;
				String msgProductImage;

				boolean category = (hairCategory.equals("") || hairCategory == null) && 
						(topCategory.equals("") || topCategory == null) && 
						(bottomsCategory.equals("") || bottomsCategory == null) &&
						(outerCategory.equals("") || outerCategory == null) &&
						(accessoryCategory.equals("") || accessoryCategory == null);

				if(menu.equals("") || menu == null)
				{
					msgMenu = "메뉴를 선택해주세요.";
					check++;
				}
				else
				{
					if(category)
					{
						msgMenu = "카테고리를 선택해주세요.";
						check++;
					}
					else
						msgMenu = "";
				}

				if(name.equals("") || name == null)
				{
					msgProductName = "상품명을 입력해주세요.";
					check++;
				}
				else
				{
					if(pDao.isProductName(name))
					{
						msgProductName = "중복된 상품명이 있습니다. 다른 상품명을 입력해주세요.";
						check++;
					}
					else
						msgProductName = "";
				}

				if(price.equals("") || price == null)
				{
					msgProductPrice = "상품 가격을 입력해주세요.";
					check++;
				}
				else
				{
					try
					{
						if(Integer.valueOf(price) < 100)
						{
							msgProductPrice = "상품 가격의 최소 단위는 100원입니다.";
							check++;
						}
						else
							msgProductPrice = "";
					}
					catch(NumberFormatException ex)
					{
						msgProductPrice = "숫자를 입력해주세요.";
						check++;
					}
				}

				if(count.equals("") || count == null)
				{
					msgProductCount = "상품 개수를 입력해주세요.";
					check++;
				}
				else
				{
					try
					{
						if(Integer.valueOf(count) < 1)
						{
							msgProductCount = "상품 개수의 최소 단위는 1개입니다.";
							check++;
						}
						else
							msgProductCount = "";
					}
					catch(NumberFormatException ex)
					{
						msgProductCount = "숫자를 입력해주세요.";
						check++;
					}
				}

				if(explain.equals("") || explain == null)
				{
					msgProductExplain = "상품 설명을 입력해주세요.";
					check++;
				}
				else
					msgProductExplain = "";

				if(tags.length > 3)
				{
					msgProductTag = "태그는 3개까지만 가능합니다.";
					check++;
				}
				else
					msgProductTag = "태그는 3개까지 가능하며 공백으로 구분됩니다.";

				try
				{
					if(imageName.equals(""))
					{
						msgProductImage = "상품 이미지를 업로드해주세요.";
						check++;
					}
					else 
						msgProductImage = "사진은 한장으로 편집하여 업로드 해주세요.";
				}
				catch(NullPointerException ex)
				{
					msgProductImage = "상품 이미지를 업로드해주세요.";
					check++;
				}

				try
				{
					response.getWriter().write(msgMenu + "," + msgProductName + "," + msgProductPrice + 
							"," + msgProductCount + "," + msgProductExplain + "," + msgProductTag +
							"," + msgProductImage + "," + check);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		pDao.close();
	}

	private void checkRegisterStylingForm()
	{
		sDao = new StylingDao();
		pDao = new ProductDao();

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
			try
			{
				int check = 0;

				int imageSize = 10 * 1024 * 1024;
				String uploadPath = session.getServletContext().getRealPath("upload");

				MultipartRequest mr = new MultipartRequest(request, uploadPath, imageSize, 
						"UTF-8", new DefaultFileRenamePolicy());

				String menu = mr.getParameter("menu");
				String hairCategory = mr.getParameter("hairCategory");
				String coordiCategory = mr.getParameter("coordiCategory");
				String name = mr.getParameter("stylingName");
				String explain = mr.getParameter("stylingExplain");
				String tag = mr.getParameter("stylingTag");
				String[] tags = tag.split(" ");
				String imageName = mr.getFilesystemName("stylingImage");
				String productName = mr.getParameter("productName");
				String[] products = productName.split(",");

				String msgMenu;
				String msgStylingName;
				String msgStylingExplain;
				String msgStylingTag;
				String msgStylingImage;
				String msgProductName = "스타일링에 사용된 상품 이름을 입력해주세요. ,로 구분지어 입력해주세요.";

				boolean category = (hairCategory.equals("") || hairCategory == null) && 
						(coordiCategory.equals("") || coordiCategory == null);

				if(menu.equals("") || menu == null)
				{
					msgMenu = "메뉴를 선택해주세요.";
					check++;
				}
				else
				{
					if(category)
					{
						msgMenu = "카테고리를 선택해주세요.";
						check++;
					}
					else
						msgMenu = "";
				}

				if(name.equals("") || name == null)
				{
					msgStylingName = "스타일링명을 입력해주세요.";
					check++;
				}
				else
				{
					if(sDao.isStylingName(name))
					{
						msgStylingName = "중복된 스타일링명이 있습니다. 다른 스타일링명을 입력해주세요.";
						check++;
					}
					else
						msgStylingName = "";
				}

				if(explain.equals("") || explain == null)
				{
					msgStylingExplain = "스타일링 설명을 입력해주세요.";
					check++;
				}
				else
					msgStylingExplain = "";

				if(tags.length > 3)
				{
					msgStylingTag = "태그는 3개까지만 가능합니다.";
					check++;
				}
				else
					msgStylingTag = "태그는 3개까지 가능하며 공백으로 구분됩니다.";

				try
				{
					if(imageName.equals(""))
					{
						msgStylingImage = "스타일링 이미지를 업로드해주세요.";
						check++;
					}
					else 
						msgStylingImage = "사진은 한장으로 편집하여 업로드 해주세요.";
				}
				catch(NullPointerException ex)
				{
					msgStylingImage = "스타일링 이미지를 업로드해주세요.";
					check++;
				}

				if(!productName.equals(""))
				{
					for(int i=0; i<products.length; i++)
					{
						if(!products[i].equals(""))
						{
							if(!pDao.isProductName(products[i].trim()))
							{
								msgProductName = products[i] + "가 존재하지 않습니다. 수정해주세요.";
								check++;
							}
						}
					}
				}

				try
				{
					response.getWriter().write(msgMenu + "," + msgStylingName + "," + msgStylingExplain +
							"," + msgStylingTag + "," + msgStylingImage + "," + msgProductName + 
							"," + check);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}

		pDao.close();
		sDao.close();
	}

	private void showStylingList()
	{
		sDao = new StylingDao();

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
			mDao = new MemberDao();

			int kindId = mDao.selectMember(sessionId.toString()).getKind();
			String kind = mDao.selectKindName(kindId);

			if(kind.equals("일반회원"))
			{
				request.setAttribute("msg", "권한 없음");

				path = "./index";
			}
			else
			{
				ArrayList<Styling> sList;

				if(kind.equals("관리자"))
					sList = sDao.selectStylingList();
				else
					sList = sDao.selectStylingList(sessionId.toString());

				if(sList != null)
				{
					String stylingList = getStylingList(sDao, sList);
					request.setAttribute("stylingList", stylingList);

					path = "showStylingList.jsp";
				}
			}
			mDao.close();
		}

		sDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private void showProductList()
	{
		pDao = new ProductDao();

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
			mDao = new MemberDao();

			int kindId = mDao.selectMember(sessionId.toString()).getKind();
			String kind = mDao.selectKindName(kindId);

			if(kind.equals("일반회원"))
			{
				request.setAttribute("msg", "권한 없음");

				path = "./index";
			}
			else
			{
				ArrayList<Product> pList;

				if(kind.endsWith("관리자"))
					pList = pDao.selectProductList();
				else
					pList = pDao.selectProductList(sessionId.toString());

				if(pList != null)
				{
					String productList = getProductList(pDao, pList);
					request.setAttribute("productList", productList);

					path = "showProductList.jsp";
				}
			}

			mDao.close();
		}

		pDao.close();

		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}
	
	private void updateStylingForm()
	{
		sDao = new StylingDao();
		
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
			String stylingId = request.getParameter("data");
			
			Styling s = sDao.selectStyling(stylingId);
			
			request.setAttribute("stylingName", s.getStylingName());
			request.setAttribute("stylingExplain", s.getExplain());
			
			String videoUrl;
			if(s.getVideoUrl() == null)
				videoUrl = "";
			else
				videoUrl = s.getVideoUrl();
			
			if(!videoUrl.equals(""))
				request.setAttribute("videoUrl", videoUrl);
			
			path = "updateStylingForm.jsp";
		}
		
		
		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}
	
	private void updateProductForm()
	{
		pDao = new ProductDao();
		
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
			String productId = request.getParameter("data");
			
			Product p = pDao.selectProduct(productId);
			
			request.setAttribute("productName", p.getProductName());
			request.setAttribute("productPrice", p.getPrice());
			request.setAttribute("productCount", p.getCount());
			request.setAttribute("productExplain", p.getExplain());
			
			path = "updateProductForm.jsp";
		}
		
		
		forward = new Forward();
		forward.setRedirect(false);
		forward.setPath(path);
	}

	private ArrayList<HashTag> getTagList(TagDao tDao, String[] tags)
	{
		ArrayList<HashTag> hList = new ArrayList<>();
		for(int i=0; i<tags.length; i++)
		{
			HashTag hashTag = new HashTag();

			if(!tDao.isTagName(tags[i]))
			{
				hashTag.setTagId(tDao.getTagId());
				hashTag.setTagName(tags[i]);

				tDao.insertTag(hashTag);
			}
			else
			{
				hashTag.setTagId(tDao.selectTagId(tags[i]));
				hashTag.setTagName(tags[i]);
			}

			hList.add(hashTag);
		}

		return hList;
	}

	private String getStylingList(StylingDao sDao, ArrayList<Styling> sList)
	{
		StringBuilder result = new StringBuilder();

		result.append("<table id='stylingList'>");

		result.append("<tr>");
		result.append("<td></td>");
		result.append("<td>스타일링 이름</td>");
		result.append("<td>메뉴</td>");
		result.append("<td>카테고리</td>");
		result.append("<td>판매자 아이디</td>");
		result.append("<td></td>");
		result.append("</tr>");

		for(int i=0; i<sList.size(); i++)
		{
			Styling s = sList.get(i);

			result.append("<tr>");
			result.append("<td><img id='smallThumbnail' src=\"upload/" + s.getImageName() + "\" /></td>");
			result.append("<td>" + s.getStylingName() + "</td>");
			result.append("<td>" + sDao.selectMenuName(s.getMenuId()) + "</td>");
			result.append("<td>" + sDao.selectCategoryName(s.getMenuId(), s.getCategoryId()) + "</td>");
			result.append("<td>" + s.getMemberId() + "</td>");
			result.append("<td><input type='button' class='btn' id='deleteStyling' name='deleteStyling' onclick=\"deleteStyling1('" + s.getStylingId() + "')\" value='스타일링 삭제'/></td>");
			result.append("</tr>");
		}

		result.append("</table>");
		return result.toString();
	}

	private String getProductList(ProductDao pDao, ArrayList<Product> pList)
	{
		StringBuilder result = new StringBuilder();

		result.append("<table id='productList'>");

		result.append("<tr>");
		result.append("<td></td>");
		result.append("<td>상품 이름</td>");
		result.append("<td>상품 재고</td>");
		result.append("<td>상품 가격</td>");
		result.append("<td>팔린 개수</td>");
		result.append("<td>메뉴</td>");
		result.append("<td>카테고리</td>");
		result.append("<td>판매자 아이디</td>");
		result.append("<td></td>");
		result.append("</tr>");

		for(int i=0; i<pList.size(); i++)
		{
			Product p = pList.get(i);

			result.append("<tr>");
			result.append("<td><img id='smallThumbnail' src=\"upload/" + p.getImageName() + "\" /></td>");
			result.append("<td>" + p.getProductName() + "</td>");
			result.append("<td>" + p.getCount() + "</td>");
			result.append("<td>" + p.getPrice() + "</td>");
			result.append("<td>" + p.getSalesVolum() + "</td>");
			result.append("<td>" + pDao.selectMenuName(p.getMenuId()) + "</td>");
			result.append("<td>" + pDao.selectCategoryName(p.getMenuId(), p.getCategoryId()) + "</td>");
			result.append("<td>" + p.getMemberId() + "</td>");
			result.append("<td><input type='button' class='btn' id='deleteProduct' name='deleteProduct' onclick=\"deleteProduct1('" + p.getProductId() + "')\" value='상품 삭제'/></td>");
			result.append("</tr>");
		}

		result.append("</table>");
		return result.toString();
	}
}
