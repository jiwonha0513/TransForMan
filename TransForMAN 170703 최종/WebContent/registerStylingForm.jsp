<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style>
		</style>
	</head>
	<body>
		<div><h1>스타일링 등록</h1></div>
		<div id="insertStyling">
			<form name="insertStylingForm" method="post" enctype="multipart/form-data">
				<div id="select">
					<select id="menu" name="menu" onchange="changeSelect(this)">
						<option value="">메뉴 선택</option>
						<option value="1">헤어&메이크업</option>
						<option value="2">코디</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="nonSelect" name="category">
						<option value="">카테고리 선택</option>
					</select>
					
					<select id="hairSelect" name="hairSelect">
						<option value="">카테고리 선택</option>
						<option value="1">헤어</option>
						<option value="2">메이크업</option>
					</select>
					
					<select id="coordiSelect" name="coordiSelect">
						<option value="">카테고리 선택</option>
						<option value="1">데일리룩</option>
						<option value="2">남친룩</option>
						<option value="3">바캉스룩</option>
					</select>
				</div>
				<div class="span"><span id="notiMenu"></span></div>
				<div class="input"><input type="text" id="stylingName" name="stylingName" placeholder="스타일링명" autofocus /></div>
				<div class="span"><span id="notiStylingName"></span></div>
				<div class="input"><textarea id="stylingExplain" name="stylingExplain" placeholder="스타일링 설명" maxlength="1000"></textarea></div>
				<div class="span"><span id="notiStylingExplain"></span></div>
				<div class="input"><input type="text" id="stylingTag" name="stylingTag" placeholder="해시태그 (선택 사항)" /></div>
				<div class="span"><span id="notiStylingTag">태그는 3개까지 가능하며 공백으로 구분됩니다.</span></div>
				<div class="input"><input type="file" id="stylingImage" name="stylingImage"></div>
				<div class="span"><span id="notiStringImage">사진은 한장으로 편집하여 업로드해주세요.</span></div>
				<div class="input"><input type="text" id="stylingVideo" name="stylingVideo" placeholder="유투브 URL (선택 사항)" /></div>
				<div class="span"><span id="notiStylingVideo">유투브 url을 입력해주세요. </span></div>
				<div class="input"><input type="text" id="productName" name="productName" placeholder="상품명 (선택 사항)" /></div>
				<div class="span"><span id="notiProductName">스타일링에 사용된 상품 이름을 입력해주세요. ,로 구분지어 입력해주세요.</span></div>
				
				<div class="button"><input type="button"  class="btn" onClick="insertStyling()" value="등록" /></div>
				<div class="button"><input type="button"  class="btn" onClick="toIndex()" value="돌아가기" /></div>
			</form>
		</div>
	</body>
	<script>
		$(function() {
			$("#hairSelect").css("display", "none");
			$("#coordiSelect").css("display", "none");
		});
	
		function changeSelect(menu) 
		{ 
			if(menu.value == 1)
			{
				$("#nonSelect").css("display", "none");
				$("#hairSelect").css("display", "");
				$("#coordiSelect").css("display", "none");
			}
			else if(menu.value == 2)
			{
				$("#nonSelect").css("display", "none");
				$("#hairSelect").css("display", "none");
				$("#coordiSelect").css("display", "");
			}
			else
			{
				$("#nonSelect").css("display", "");
				$("#hairSelect").css("display", "none");
				$("#coordiSelect").css("display", "none");
			}
		}
		
		function insertStyling()
		{
			var formData = new FormData();
			formData.append("menu", $('#menu').val());
			formData.append("hairCategory", $('#hairSelect').val());
			formData.append("coordiCategory", $('#coordiSelect').val());
			formData.append("stylingName", $('#stylingName').val());
			formData.append("stylingExplain", $('#stylingExplain').val());
			formData.append("stylingTag", $('#stylingTag').val());
			formData.append("stylingImage", $('#stylingImage')[0].files[0]);
			formData.append("stylingVideo", $('#stylingVideo').val());
			formData.append("productName", $('#productName').val());
			
			$.ajax({
				type: 'POST',
				url: './checkRegisterStylingForm',
				data: formData,
				processData: false,
                contentType: false,
				
				success:function(data)
				{
					var msg = data.split(",");
					
					console.log(msg);
					
					$('#notiMenu').html(msg[0]);
					$('#notiStylingName').html(msg[1]);
					$('#notiStylingExplain').html(msg[2]);
					$('#notiStylingTag').html(msg[3]);
					$('#notiStringImage').html(msg[4]);
					if(msg.length == 8)
						$('#notiProductName').html(msg[5] + " ," + msg[6]);
					else
						$('#notiProductName').html(msg[5]);
					
					if(msg[6] == '0' || msg[7] == '0')
					{
						insertStylingMove()
					}
				},
				
				error:function(error)
				{
					
				}
			});
		}
		
		function insertStylingMove()
		{
			var formData = new FormData();
			formData.append("menu", $('#menu').val());
			formData.append("hairSelect", $('#hairSelect').val());
			formData.append("coordiSelect", $('#coordiSelect').val());
			formData.append("stylingName", $('#stylingName').val());
			formData.append("stylingExplain", $('#stylingExplain').val());
			formData.append("stylingTag", $('#stylingTag').val());
			formData.append("stylingImage", $('#stylingImage')[0].files[0]);
			formData.append("stylingVideo", $('#stylingVideo').val());
			formData.append("productName", $('#productName').val());
			
			$.ajax({
				type: 'POST',
				url: './insertStyling',
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
			insertStylingForm.action = "./index";
			insertStylingForm.submit();
		}
	</script>	
</html>