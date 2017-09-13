<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div><h1>상품 등록</h1></div>
		<div id="insertProduct">
			<form name="insertProductForm" method="post" enctype="multipart/form-data">
				<div id="select">
					<select id="menu" name="menu" onchange="changeSelect(this)">
						<option value="">메뉴 선택</option>
						<option value="1">헤어&메이크업</option>
						<option value="2">상의</option>
						<option value="3">하의</option>
						<option value="4">아우터</option>
						<option value="5">악세서리&etc</option>
					</select>
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<select id="nonSelect" class="category">
						<option value="">카테고리 선택</option>
					</select>
					
					<select id="hairSelect" name="hairSelect">
						<option value="">카테고리 선택</option>
						<option value="1">헤어 용품</option>
						<option value="2">메이크업 용품</option>
					</select>
					
					<select id="topSelect" name="topSelect">
						<option value="">카테고리 선택</option>
						<option value="1">티셔츠</option>
						<option value="2">후드티</option>
					</select>
					
					<select id="bottomsSelect" name="bottomsSelect">
						<option value="">카테고리 선택</option>
						<option value="1">청바지</option>
						<option value="2">바지</option>
						<option value="3">트레이닝</option>
					</select>
					
					<select id="outerSelect" name="outerSelect">
						<option value="">카테고리 선택</option>
						<option value="1">코트</option>
						<option value="2">자켓</option>
						<option value="3">가디건</option>
					</select>
					
					<select id="accessorySelect" name="accessorySelect">
						<option value="">카테고리 선택</option>
						<option value="1">시계</option>
						<option value="2">운동화</option>
						<option value="3">구두</option>
					</select>
				</div>
				<div class="span"><span id="notiMenu"></span></div>
				<div class="input"><input type="text" id="productName" name="productName" placeholder="상품명" autofocus /></div>
				<div class="span"><span id="notiProductName"></span></div>
				<div class="input"><input type="text" id="productPrice" name="productPrice" placeholder="상품 가격" /></div>
				<div class="span"><span id="notiProductPrice"></span></div>
				<div class="input"><input type="text" id="productCount" name="productCount" placeholder="상품 재고" /></div>
				<div class="span"><span id="notiProductCount"></span></div>
				<div class="input"><textarea id="productExplain" name="productExplain" placeholder="상품 설명" maxlength="1000"></textarea></div>
				<div class="span"><span id="notiProductExplain"></span></div>
				<div class="input"><input type="text" id="productTag" name="productTag" placeholder="해시태그 (선택 사항)" /></div>
				<div class="span"><span id="notiProductTag">태그는 3개까지 가능하며 공백으로 구분됩니다.</span></div>
				<div class="input"><input type="file" id="productImage" name="productImage"></div>
				<div class="span"><span id="notiProductImage">사진은 한장으로 편집하여 업로드 해주세요.</span></div>
				
				<div class="button"><input type="button"  class="btn" onClick="insertProduct()" value="등록" /></div>
				<div class="button"><input type="button"  class="btn" onClick="toIndex()" value="돌아가기" /></div>
			</form>
		</div>
	</body>
	<script>
		$(function() {
			$("#hairSelect").css("display", "none");
			$("#topSelect").css("display", "none");
			$("#bottomsSelect").css("display", "none");
			$("#outerSelect").css("display", "none");
			$("#accessorySelect").css("display", "none");
		});
	
		function changeSelect(menu) 
		{ 
			if(menu.value == 1)
			{
				$("#nonSelect").css("display", "none");
				$("#hairSelect").css("display", "");
				$("#topSelect").css("display", "none");
				$("#bottomsSelect").css("display", "none");
				$("#outerSelect").css("display", "none");
				$("#accessorySelect").css("display", "none");
			}
			else if(menu.value == 2)
			{
				$("#nonSelect").css("display", "none");
				$("#hairSelect").css("display", "none");
				$("#topSelect").css("display", "");
				$("#bottomsSelect").css("display", "none");
				$("#outerSelect").css("display", "none");
				$("#accessorySelect").css("display", "none");
			}
			else if(menu.value == 3)
			{
				$("#nonSelect").css("display", "none");
				$("#hairSelect").css("display", "none");
				$("#topSelect").css("display", "none");
				$("#bottomsSelect").css("display", "");
				$("#outerSelect").css("display", "none");
				$("#accessorySelect").css("display", "none");
			}
			else if(menu.value == 4)
			{
				$("#nonSelect").css("display", "none");
				$("#hairSelect").css("display", "none");
				$("#topSelect").css("display", "none");
				$("#bottomsSelect").css("display", "none");
				$("#outerSelect").css("display", "");
				$("#accessorySelect").css("display", "none");
			}
			else if(menu.value == 5)
			{
				$("#nonSelect").css("display", "none");
				$("#hairSelect").css("display", "none");
				$("#topSelect").css("display", "none");
				$("#bottomsSelect").css("display", "none");
				$("#outerSelect").css("display", "none");
				$("#accessorySelect").css("display", "");
			}
			else
			{
				$("#nonSelect").css("display", "");
				$("#hairSelect").css("display", "none");
				$("#topSelect").css("display", "none");
				$("#bottomsSelect").css("display", "none");
				$("#outerSelect").css("display", "none");
				$("#accessorySelect").css("display", "none");
			}
		}
		
		function insertProduct()
		{
			var formData = new FormData();
			formData.append("menu", $('#menu').val());
			formData.append("hairCategory", $('#hairSelect').val());
			formData.append("topCategory", $('#topSelect').val());
			formData.append("bottomsCategory", $('#bottomsSelect').val());
			formData.append("outerCategory", $('#outerSelect').val());
			formData.append("accessoryCategory", $('#accessorySelect').val());
			formData.append("productName", $('#productName').val());
			formData.append("productPrice", $('#productPrice').val());
			formData.append("productCount", $('#productCount').val());
			formData.append("productExplain", $('#productExplain').val());
			formData.append("productTag", $('#productTag').val());
			formData.append("productImage", $('#productImage')[0].files[0]);
			
			$.ajax({
				type: 'POST',
				url: './checkRegisterProductForm',
				data: formData,
				processData: false,
                contentType: false,
				
				success:function(data)
				{
					var msg = data.split(",");
					
					console.log(msg);
					
					$('#notiMenu').html(msg[0]);
					$('#notiProductName').html(msg[1]);
					$('#notiProductPrice').html(msg[2]);
					$('#notiProductCount').html(msg[3]);
					$('#notiProductExplain').html(msg[4]);
					$('#notiProductTag').html(msg[5]);
					$('#notiProductImage').html(msg[6]);
					
					if(msg[7] == '0')
					{
						insertProductMove();
					}
				},
				
				error:function(error)
				{
					
				}
			});
		}
		
		function insertProductMove()
		{
			var formData = new FormData();
			formData.append("menu", $('#menu').val());
			formData.append("hairSelect", $('#hairSelect').val());
			formData.append("topSelect", $('#topSelect').val());
			formData.append("bottomsSelect", $('#bottomsSelect').val());
			formData.append("outerSelect", $('#outerSelect').val());
			formData.append("accessorySelect", $('#accessorySelect').val());
			formData.append("productName", $('#productName').val());
			formData.append("productPrice", $('#productPrice').val());
			formData.append("productCount", $('#productCount').val());
			formData.append("productExplain", $('#productExplain').val());
			formData.append("productTag", $('#productTag').val());
			formData.append("productImage", $('#productImage')[0].files[0]);
			
			$.ajax({
				type: 'POST',
				url: './insertProduct',
				data: formData,
				processData: false,
                contentType: false,
                
                success:function(data)
				{
                	$('#main').html(data);
				},
			
				error:function(error)
				{
					console.log(error);
				}
			});
		}
	
		function toIndex()
		{
			insertProductForm.action = "./index";
			insertProductForm.submit();
		}
	</script>	
</html>